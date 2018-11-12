/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.files;

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Instructor;
import csg.data.LabItem;
import csg.data.LectureItem;
import csg.data.RecitationItem;
import csg.data.ScheduleItem;
import csg.data.TeachingAssistantPrototype;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_GRA;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_UNDERGRA;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.workspace.CourseSiteGeneratorWorkspace;
import static djf.AppPropertyType.APP_CHOICES;
import static djf.AppPropertyType.APP_PATH_SETTING;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import properties_manager.PropertiesManager;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorFiles implements AppFileComponent {
        CourseSiteGeneratorApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_SUBJECT = "subject";
    static final String JSON_NUMBER = "number";
    static final String JSON_SEMESTER = "semester";
    static final String JSON_YEAR = "year";
    static final String JSON_TITLE = "title";
    static final String JSON_SUBJECT_OPTIONS = "subjects";
    static final String JSON_NUMBER_OPTIONS = "numbers";
    static final String JSON_SEMESTER_OPTIONS = "semesters";
    static final String JSON_YEAR_OPTIONS = "years";
    static final String JSON_PAGES = "pages";
    static final String JSON_STYLESHEET = "stylesheet";
    static final String JSON_LOGOS = "logos";
    static final String JSON_FAVICON = "favicon";
    static final String JSON_NAVBAR = "navbar";
    static final String JSON_BOTTOM_LEFT = "bottom_left";
    static final String JSON_BOTTOM_RIGHT = "bottom_right";
    static final String JSON_SRC = "src";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_LINK = "link";
    static final String JSON_ROOM = "room";
    static final String JSON_DESCRIPTION = "courseDescription";
    static final String JSON_TOPICS = "courseTopics";
    static final String JSON_PREREQUISITES = "prerequisites";
    static final String JSON_OUTCOMES = "courseOutcomes";
    static final String JSON_TEXTBOOKS = "textbooks";
    static final String JSON_GRADED_COMPONENETS = "courseComponents";
    static final String JSON_GRADING_NOTE = "gradingNote";
    static final String JSON_ACADEMIC_DISHONESTY = "academicDishonestyNote";
    static final String JSON_SPECIAL_ASSISTANCE = "specialAssistanceNote";
    static final String JSON_LECTURES = "lectures";
    static final String JSON_SECTION = "section";
    static final String JSON_DAYS = "days";
    static final String JSON_TIME = "time";
    static final String JSON_LABS = "labs";
    static final String JSON_RECITATIONS = "recitations";
    static final String JSON_DAYS_TIME = "day_time";
    static final String JSON_LOCATION = "day_time";
    static final String JSON_TA1 = "ta_1";
    static final String JSON_TA2 = "ta_2";    
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_TIME = "time";
    static final String JSON_DAY_OF_WEEK = "day";
    static final String JSON_MONDAY = "monday";
    static final String JSON_TUESDAY = "tuesday";
    static final String JSON_WEDNESDAY = "wednesday";
    static final String JSON_THURSDAY = "thursday";
    static final String JSON_FRIDAY = "friday";
    static final String JSON_STARTING_MONDAY = "starting_monday";
    static final String JSON_ENDING_FRIDAY = "ending_friday";
    static final String JSON_SCHEDULE = "schedule";
    static final String JSON_TYPE = "type";
    static final String JSON_DATE = "date";
    static final String JSON_TOPIC = "topic";

    public CourseSiteGeneratorFiles(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException{
        // GET THE DATA
        CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;

        // NOW BUILD THE TA JSON OBJCTS TO SAVE
                    JsonArrayBuilder pagesArrayBuilder = Json.createArrayBuilder();
                    for(int i = 0 ; i < 4; i++){
                        pagesArrayBuilder.add(dataManager.getPages(i));
                    }
                    JsonArray pagesArray = pagesArrayBuilder.build();
                    JsonObjectBuilder faviconObject = Json.createObjectBuilder().add(JSON_SRC, dataManager.getStyleImages(0));
                    JsonObjectBuilder navbarObject = Json.createObjectBuilder().add(JSON_SRC, dataManager.getStyleImages(1));
                    JsonObjectBuilder bottomLeftObject = Json.createObjectBuilder().add(JSON_SRC, dataManager.getStyleImages(2));
                    JsonObjectBuilder bottomRightObject = Json.createObjectBuilder().add(JSON_SRC, dataManager.getStyleImages(3));
                    JsonObject logosObject = Json.createObjectBuilder().add(JSON_FAVICON, faviconObject).add(JSON_NAVBAR, navbarObject).add(JSON_BOTTOM_LEFT, bottomLeftObject)
                                .add(JSON_BOTTOM_RIGHT, bottomRightObject).build();
                    Instructor instructor = dataManager.getInstructor();
                    JsonObject instructorObject = Json.createObjectBuilder().add(JSON_NAME, instructor.getName())
                                .add(JSON_ROOM, instructor.getRoom()).add(JSON_EMAIL, instructor.getEmail()).add(JSON_LINK, instructor.getHomepage())
                                .add(JSON_OFFICE_HOURS, instructor.getOhs()).build();

                    JsonArrayBuilder lecturesArrayBuilder = Json.createArrayBuilder();
                    Iterator<LectureItem> lecturesIterator = dataManager.lecturesIterator();
                    while(lecturesIterator.hasNext()){
                        LectureItem lecture = lecturesIterator.next();
                        JsonObject lectureJson = Json.createObjectBuilder().add(JSON_SECTION, lecture.getSection())
                                .add(JSON_DAYS, lecture.getDays()).add(JSON_TIME, lecture.getTime())
                                .add(JSON_ROOM, lecture.getRoom()).build();
                        lecturesArrayBuilder.add(lectureJson);
                    }
                    JsonArray lecturesArray = lecturesArrayBuilder.build();

                    JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();
                    Iterator<RecitationItem> recitationsIterator = dataManager.recitationsIterator();
                    while(recitationsIterator.hasNext()){
                        RecitationItem recitation = recitationsIterator.next();
                        JsonObject recitationJson = Json.createObjectBuilder().add(JSON_SECTION, recitation.getSection())
                                .add(JSON_DAYS_TIME, recitation.getDaysAndTime()).add(JSON_ROOM, recitation.getRoom())
                                .add(JSON_TA1, recitation.getTa1()).add(JSON_TA2, recitation.getTa2()).build();
                        recitationsArrayBuilder.add(recitationJson);
                    }
                    JsonArray recitationsArray = recitationsArrayBuilder.build();

                    JsonArrayBuilder labsArrayBuilder = Json.createArrayBuilder();
                    Iterator<LabItem> labsIterator = dataManager.labsIterator();
                    while(labsIterator.hasNext()){
                        LabItem lab = labsIterator.next();
                        JsonObject labJson = Json.createObjectBuilder().add(JSON_SECTION, lab.getSection())
                                .add(JSON_DAYS_TIME, lab.getDaysAndTime()).add(JSON_ROOM, lab.getRoom())
                                .add(JSON_TA1, lab.getTa1()).add(JSON_TA2, lab.getTa2()).build();
                        labsArrayBuilder.add(labJson);
                    }
                    JsonArray labsArray = labsArrayBuilder.build();             

        JsonArrayBuilder graduateTaArrayBuilder = Json.createArrayBuilder();
        Iterator<TeachingAssistantPrototype> graduaTasIterator = dataManager.graduateTeachingAssistantsIterator();
                    while (graduaTasIterator.hasNext()) {
                        TeachingAssistantPrototype ta = graduaTasIterator.next();
            JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
            graduateTaArrayBuilder.add(taJson);
        }                    
        JsonArray gradTAsArray = graduateTaArrayBuilder.build();
        JsonArrayBuilder undergraduateTaArrayBuilder = Json.createArrayBuilder();
        Iterator<TeachingAssistantPrototype> undergraduateTasIterator = dataManager.undergraduateTeachingAssistantsIterator();
                    while (undergraduateTasIterator.hasNext()) {
                        TeachingAssistantPrototype ta = undergraduateTasIterator.next();
            JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName()).add(JSON_EMAIL, ta.getEmail()).build();
            undergraduateTaArrayBuilder.add(taJson);
        }
        JsonArray undergradTAsArray = undergraduateTaArrayBuilder.build();        
                JsonArrayBuilder ohArrayBuilder = Json.createArrayBuilder();
                Iterator<TimeSlot> ohIterator = dataManager.allOfficeHoursIterator();
                    String[] dayOfWeek = new String[5];
                    int index = 0;
                    for(DayOfWeek dow: DayOfWeek.values())
                        dayOfWeek[index++] = dow.toString();
                    while (ohIterator.hasNext()) {
                        TimeSlot oh = ohIterator.next();
                        String[] ohs = new String[]{oh.getMonday(), oh.getTuesday(), oh.getWednesday(), oh.getThursday(), oh.getFriday()};
                        for(int i = 0 ; i < ohs.length; i++){
                            if(ohs[i] == null)
                                continue;
                            String[] taNames = ohs[i].split("\n");
                            for(int j = 0 ; j < taNames.length; j++){
                                JsonObject ohJson = Json.createObjectBuilder()
                                        .add(JSON_START_TIME, oh.getStartTime().replace(":", "_")).add(JSON_DAY_OF_WEEK, dayOfWeek[i]).add(JSON_NAME, taNames[j]).build();
                                ohArrayBuilder.add(ohJson);
                            }
                        }
        }
        JsonArray ohArray = ohArrayBuilder.build();

                    JsonArrayBuilder schedulesArrayBuilder = Json.createArrayBuilder();
                    Iterator<ScheduleItem> schedulesIterator = dataManager.schedulesIterator();
                    while(schedulesIterator.hasNext()){
                        ScheduleItem schedule = schedulesIterator.next();
                        JsonObject scheduleJson = Json.createObjectBuilder()
                                .add(JSON_TYPE, schedule.getType()).add(JSON_DATE, schedule.getDate())
                                .add(JSON_TITLE, schedule.getTitle()).add(JSON_TOPIC, schedule.getTopic())
                                .add(JSON_LINK, schedule.getLink()).build();
                        schedulesArrayBuilder.add(scheduleJson);
                    }
                    JsonArray schedulesArray = schedulesArrayBuilder.build();             

        // THEN PUT IT ALL TOGETHER IN A JsonObject
        JsonObject dataManagerJSO = Json.createObjectBuilder()
                                        .add(JSON_SUBJECT, dataManager.getBannerText(0))
                                        .add(JSON_NUMBER, dataManager.getBannerText(1))
                                        .add(JSON_SEMESTER, dataManager.getBannerText(2))
                                        .add(JSON_YEAR, dataManager.getBannerText(3))
                                        .add(JSON_TITLE, dataManager.getBannerText(4))
                                        .add(JSON_PAGES, pagesArray)
                                        .add(JSON_LOGOS, logosObject)
                                        .add(JSON_STYLESHEET, dataManager.getStylesheet())
                                        .add(JSON_INSTRUCTOR, instructorObject)
                                        .add(JSON_DESCRIPTION, dataManager.getSyllabusText(0))
                                        .add(JSON_TOPICS, dataManager.getSyllabusText(1))
                                        .add(JSON_PREREQUISITES, dataManager.getSyllabusText(2))
                                        .add(JSON_OUTCOMES, dataManager.getSyllabusText(3))
                                        .add(JSON_TEXTBOOKS, dataManager.getSyllabusText(4))
                                        .add(JSON_GRADED_COMPONENETS, dataManager.getSyllabusText(5))
                                        .add(JSON_GRADING_NOTE, dataManager.getSyllabusText(6))
                                        .add(JSON_ACADEMIC_DISHONESTY, dataManager.getSyllabusText(7))
                                        .add(JSON_SPECIAL_ASSISTANCE, dataManager.getSyllabusText(8))
                                        .add(JSON_LECTURES, lecturesArray)
                                        .add(JSON_RECITATIONS, recitationsArray)
                                        .add(JSON_LABS, labsArray)
                .add(JSON_START_HOUR, "" + dataManager.getStartTime())
                .add(JSON_END_HOUR, "" + dataManager.getEndTime())
                                        .add(JSON_GRAD_TAS, gradTAsArray)
                                        .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                                        .add(JSON_OFFICE_HOURS, ohArray)
                                        .add(JSON_STARTING_MONDAY, dataManager.getStartingMonday() != null ?  dataManager.getStartingMonday().toString() : "")
                                        .add(JSON_ENDING_FRIDAY, dataManager.getEndingFriday() != null? dataManager.getEndingFriday().toString(): "")
                                        .add(JSON_SCHEDULE, schedulesArray)
                .build();

        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(filePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(filePath);
        pw.write(prettyPrinted);
        pw.close();    
        
        // For the choice settings
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        dataManager.addSubjectOptions(dataManager.getBannerText(0));
        dataManager.addNumberOptions(dataManager.getBannerText(1));
        dataManager.addSemesterOptions(dataManager.getBannerText(2));
        dataManager.addYearOptions(dataManager.getBannerText(3));
        Iterator<String> subjects = dataManager.subjectsIterator();
        Iterator<String> years = dataManager.yearsIterator();
        Iterator<String> numbers = dataManager.numbersIterator();
        Iterator<String> semesters = dataManager.semestersIterator();
        
        JsonArrayBuilder subjectsArrayBuilder = Json.createArrayBuilder();
        while(subjects.hasNext()){
            subjectsArrayBuilder.add(subjects.next());
        }
        JsonArray subjectArray = subjectsArrayBuilder.build();

        JsonArrayBuilder yearsArrayBuilder = Json.createArrayBuilder();
        while(years.hasNext()){
            yearsArrayBuilder.add(years.next());
        }
        JsonArray yearArray = yearsArrayBuilder.build();

        JsonArrayBuilder numbersArrayBuilder = Json.createArrayBuilder();
        while(numbers.hasNext()){
            numbersArrayBuilder.add(numbers.next());
        }
        JsonArray numberArray = numbersArrayBuilder.build();

        JsonArrayBuilder semestersArrayBuilder = Json.createArrayBuilder();
        while(semesters.hasNext()){
            semestersArrayBuilder.add(semesters.next());
        }
        JsonArray semesterArray = semestersArrayBuilder.build();       
        
        JsonObject settingObject = Json.createObjectBuilder().add(JSON_SUBJECT_OPTIONS, subjectArray)
                .add(JSON_YEAR_OPTIONS, yearArray).add(JSON_NUMBER_OPTIONS, numberArray)
                .add(JSON_SEMESTER_OPTIONS, semesterArray).build();

        Map<String, Object> properties1 = new HashMap<>(1);
        properties1.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory1 = Json.createWriterFactory(properties1);
        StringWriter sw1 = new StringWriter();
        JsonWriter jsonWriter1 = writerFactory.createWriter(sw1);
        jsonWriter1.writeObject(settingObject);
        jsonWriter1.close();

        // INIT THE WRITER
        OutputStream os1 = new FileOutputStream((props.getProperty(APP_PATH_SETTING) + props.getProperty(APP_CHOICES)));
        JsonWriter jsonFileWriter1 = Json.createWriter(os1);
        jsonFileWriter1.writeObject(settingObject);
        String prettyPrinted1 = sw1.toString();
        PrintWriter pw1 = new PrintWriter(props.getProperty(APP_PATH_SETTING) + props.getProperty(APP_CHOICES));
        pw1.write(prettyPrinted1);
        pw1.close();    

    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
        CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        dataManager.reset();

        // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(filePath);

        String subject = json.getString(JSON_SUBJECT);
        String number = json.getString(JSON_NUMBER);
        String semester = json.getString(JSON_SEMESTER);
        String year = json.getString(JSON_YEAR);
        String title = json.getString(JSON_TITLE);
        dataManager.setBannerText(0, subject);
        dataManager.setBannerText(1, number);
        dataManager.setBannerText(2, semester);
        dataManager.setBannerText(3, year);
        dataManager.setBannerText(4, title);
        dataManager.updateExportDir();
        JsonArray jsonPagesArray = json.getJsonArray(JSON_PAGES);
        for(int i = 0 ; i < jsonPagesArray.size(); i++)
            dataManager.setPages(i, jsonPagesArray.getBoolean(i));
        JsonObject jsonIogos= json.getJsonObject(JSON_LOGOS);
        String favicon = jsonIogos.getJsonObject(JSON_FAVICON).getString(JSON_SRC);
        String navbar = jsonIogos.getJsonObject(JSON_NAVBAR).getString(JSON_SRC);
        String leftFooter = jsonIogos.getJsonObject(JSON_BOTTOM_LEFT).getString(JSON_SRC);
        String rightFooter = jsonIogos.getJsonObject(JSON_BOTTOM_RIGHT).getString(JSON_SRC);
        dataManager.setStyleImages(favicon, 0);
        dataManager.setStyleImages(navbar, 1);
        dataManager.setStyleImages(leftFooter, 2);
        dataManager.setStyleImages(rightFooter, 3);
        String stylesheet = json.getString(JSON_STYLESHEET);
        dataManager.setStylesheet(stylesheet);
        JsonObject jsonInstructor = json.getJsonObject(JSON_INSTRUCTOR);
        dataManager.getInstructor().setName(jsonInstructor.getString(JSON_NAME));
        dataManager.getInstructor().setEmail(jsonInstructor.getString(JSON_EMAIL));
        dataManager.getInstructor().setRoom(jsonInstructor.getString(JSON_ROOM));
        dataManager.getInstructor().setHomepage(jsonInstructor.getString(JSON_LINK));
        dataManager.getInstructor().setOhs(jsonInstructor.getString(JSON_OFFICE_HOURS));
        dataManager.setSyllabusText(0, json.getString(JSON_DESCRIPTION));
        dataManager.setSyllabusText(1, json.getString(JSON_TOPICS));
        dataManager.setSyllabusText(2, json.getString(JSON_PREREQUISITES));
        dataManager.setSyllabusText(3, json.getString(JSON_OUTCOMES));
        dataManager.setSyllabusText(4, json.getString(JSON_TEXTBOOKS));
        dataManager.setSyllabusText(5, json.getString(JSON_GRADED_COMPONENETS));
        dataManager.setSyllabusText(6, json.getString(JSON_GRADING_NOTE));
        dataManager.setSyllabusText(7, json.getString(JSON_ACADEMIC_DISHONESTY));
        dataManager.setSyllabusText(8, json.getString(JSON_SPECIAL_ASSISTANCE));
        
        JsonArray jsonLecturesArray = json.getJsonArray(JSON_LECTURES);
        for(int i = 0 ; i  < jsonLecturesArray.size(); i++){
            JsonObject jsonLecture = jsonLecturesArray.getJsonObject(i);
            LectureItem lecture = new LectureItem(jsonLecture.getString(JSON_SECTION), jsonLecture.getString(JSON_DAYS),
                jsonLecture.getString(JSON_TIME), jsonLecture.getString(JSON_ROOM));
            dataManager.addLecture(lecture);
        }

        JsonArray jsonRecitationsArray = json.getJsonArray(JSON_RECITATIONS);
        for(int i = 0 ; i  < jsonLecturesArray.size(); i++){
            JsonObject jsonRecitation = jsonRecitationsArray.getJsonObject(i);
            RecitationItem recitation = new RecitationItem(jsonRecitation.getString(JSON_SECTION), jsonRecitation.getString(JSON_DAYS_TIME),
                jsonRecitation.getString(JSON_ROOM), jsonRecitation.getString(JSON_TA1), jsonRecitation.getString(JSON_TA2));
            dataManager.addRecitation(recitation);
        }

        JsonArray jsonLabsArray = json.getJsonArray(JSON_LABS);
        for(int i = 0 ; i  < jsonLabsArray.size(); i++){
            JsonObject jsonLab = jsonLabsArray.getJsonObject(i);
            LabItem lab = new LabItem(jsonLab.getString(JSON_SECTION), jsonLab.getString(JSON_DAYS_TIME),
                jsonLab.getString(JSON_ROOM), jsonLab.getString(JSON_TA1), jsonLab.getString(JSON_TA2));
            dataManager.addLab(lab);
        }        
        
        // LOAD THE START AND END HOURS
        String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
       dataManager.initOhHours(startHour, endHour);

        // NOW LOAD ALL THE GRAD TAs
        JsonArray jsonGradTAArray = json.getJsonArray(JSON_GRAD_TAS);
        for (int i = 0; i < jsonGradTAArray.size(); i++) {
            JsonObject jsonTA = jsonGradTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name, email, TA_TYPE_GRA);
            dataManager.addTA(ta, TA_TYPE_GRA);
        }
        
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonUndergradTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonUndergradTAArray.size(); i++) {
            JsonObject jsonTA = jsonUndergradTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name, email, TA_TYPE_UNDERGRA);
            dataManager.addTA(ta, TA_TYPE_UNDERGRA);
        }
        
        JsonArray jsonOHArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOHArray.size(); i++) {
            JsonObject jsonOH = jsonOHArray.getJsonObject(i);
            String day = jsonOH.getString(JSON_DAY_OF_WEEK);
            String name = jsonOH.getString(JSON_NAME);
            String time = jsonOH.getString(JSON_START_TIME);
            TeachingAssistantPrototype TA = dataManager.getTAWithName(name);
            TimeSlot t = dataManager.getTimeSlot(time);
            if(TA != null)
                t.addTA(TA, day);
        }
        
        String startingMonday = json.getString(JSON_STARTING_MONDAY);
        if(startingMonday != null && !startingMonday.isEmpty())
            dataManager.setStartingMonday(LocalDate.parse(startingMonday));
        String endingFriday = json.getString(JSON_ENDING_FRIDAY);
        if(endingFriday != null && !endingFriday.isEmpty())
            dataManager.setEndingFriday(LocalDate.parse(endingFriday));
        
        JsonArray jsonScheduleArray = json.getJsonArray(JSON_SCHEDULE);
        for(int i = 0 ; i < jsonScheduleArray.size(); i++){
            JsonObject jsonSchedule = jsonScheduleArray.getJsonObject(i);
            String type = jsonSchedule.getString(JSON_TYPE);
            String date = jsonSchedule.getString(JSON_DATE);
            String title1 = jsonSchedule.getString(JSON_TITLE);
            String topic = jsonSchedule.getString(JSON_TOPIC);
            String link = jsonSchedule.getString(JSON_LINK);
            ScheduleItem schedule = new ScheduleItem(type, date, title1, topic, link);
            dataManager.addSchedule(schedule);
        }
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        JsonObject settings = loadJSONFile(props.getProperty(APP_PATH_SETTING) + props.getProperty(APP_CHOICES));
        JsonArray subjects = settings.getJsonArray(JSON_SUBJECT_OPTIONS);
        JsonArray numbers = settings.getJsonArray(JSON_NUMBER_OPTIONS);
        JsonArray semesters = settings.getJsonArray(JSON_SEMESTER_OPTIONS);
        JsonArray years = settings.getJsonArray(JSON_YEAR_OPTIONS);
        for(int i = 0 ; i < subjects.size(); i++){
            dataManager.addSubjectOptions(subjects.getString(i));
        }
        for(int i = 0 ; i < numbers.size(); i++){
            dataManager.addNumberOptions(numbers.getString(i));
        }
        for(int i = 0 ; i < semesters.size(); i++){
            dataManager.addSemesterOptions(semesters.getString(i));
        }
        for(int i = 0 ; i < years.size(); i++){
            dataManager.addYearOptions(years.getString(i));
        }        
        
        app.getFoolproofModule().updateAll();
        ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).loadDataToUI();
    }
    
        // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
