/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.files;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_HWS_PAGE;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_INDEX_PAGE;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_SCHEDULE_PAGE;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_SYLLABUS_PAGE;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_CSS;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_IMAGES;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_JS;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_OFFICE_HOURS_DATA_JSON;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_PAGE_DATA_JSON;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_PUBLIC_HTML;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_SCHEDULE_DATA_JSON;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_SECTIONS_DATA_JSON;
import static csg.CourseSiteGeneratorPropertyType.EXPORT_PATH_SYLLABUS_DATA_JSON;
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
import csg.workspace.dialogs.CourseSiteGeneratorDialog;
import static djf.AppPropertyType.APP_CHOICES;
import static djf.AppPropertyType.APP_PATH_SETTING;
import static djf.AppPropertyType.APP_PATH_STYLESHEET;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
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
import org.apache.commons.io.FileUtils;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorFiles implements AppFileComponent {
        CourseSiteGeneratorApp app;
    static final String ORIGINAL_CSS = "sea_wolf.css";
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
    static final String JSON_SCHEDULE_OPTIONS = "schedules";
    static final String JSON_PAGES = "pages";
    static final String JSON_STYLESHEET = "stylesheet";
    static final String JSON_LOGOS = "logos";
    static final String JSON_FAVICON = "favicon";
    static final String JSON_NAVBAR = "navbar";
    static final String JSON_BOTTOM_LEFT = "bottom_left";
    static final String JSON_BOTTOM_RIGHT = "bottom_right";
    static final String JSON_HREF = "href";
    static final String JSON_SRC = "src";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_LINK = "link";
    static final String JSON_ROOM = "room";
    static final String JSON_DESCRIPTION = "description";
    static final String JSON_TOPICS = "topics";
    static final String JSON_PREREQUISITES = "prerequisites";
    static final String JSON_OUTCOMES = "outcomes";
    static final String JSON_TEXTBOOKS = "textbooks";
    static final String JSON_GRADED_COMPONENTS = "gradedComponents";
    static final String JSON_GRADING_NOTE = "gradingNote";
    static final String JSON_ACADEMIC_DISHONESTY = "academicDishonesty";
    static final String JSON_SPECIAL_ASSISTANCE = "specialAssistance";
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
    static final String JSON_STARTING_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_ENDING_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_STARTING_MONDAY_DAY = "startingMondayDay";
    static final String JSON_ENDING_FRIDAY_DAY = "endingFridayDay";
    static final String JSON_SCHEDULE = "schedule";
    static final String JSON_TYPE = "type";
    static final String JSON_DATE = "date";
    static final String JSON_TOPIC = "topic";
    static final String JSON_HOURS = "hours";
    static final String JSON_HOLIDAY = "holidays";
    static final String JSON_REFERENCES = "references";
    static final String JSON_HWS = "hws";
    static final String JSON_MONTH = "month";
    static final String JSON_DAY = "day";
    static final String JSON_CRITERIA = "criteria";
    
    

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
                                        .add(JSON_GRADED_COMPONENTS, dataManager.getSyllabusText(5))
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
        Iterator<String> schedules = dataManager.scheduleOptionsIterator();
        
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
        
        JsonArrayBuilder scheduleOptionsArrayBuilder = Json.createArrayBuilder();
        while(schedules.hasNext()){
            scheduleOptionsArrayBuilder.add(schedules.next());
        }
        JsonArray scheduleOptionsArray = scheduleOptionsArrayBuilder.build();      
        
        JsonObject settingObject = Json.createObjectBuilder().add(JSON_SUBJECT_OPTIONS, subjectArray)
                .add(JSON_YEAR_OPTIONS, yearArray).add(JSON_NUMBER_OPTIONS, numberArray)
                .add(JSON_SEMESTER_OPTIONS, semesterArray).add(JSON_SCHEDULE_OPTIONS, scheduleOptionsArray).build();

        Map<String, Object> properties1 = new HashMap<>(1);
        properties1.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory1 = Json.createWriterFactory(properties1);
        StringWriter sw1 = new StringWriter();
        JsonWriter jsonWriter1 = writerFactory1.createWriter(sw1);
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
        dataManager.setSyllabusText(5, json.getString(JSON_GRADED_COMPONENTS));
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
        for(int i = 0 ; i  < jsonRecitationsArray.size(); i++){
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
        
        loadSettings(dataManager);
        
        app.getFoolproofModule().updateAll();
        ((CourseSiteGeneratorWorkspace)app.getWorkspaceComponent()).loadDataToUI();
        dataManager.countTaOh();
    }
    
    public void loadSettings(CourseSiteGeneratorData dataManager){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        JsonObject settings = null;
        try {
            settings = loadJSONFile(props.getProperty(APP_PATH_SETTING) + props.getProperty(APP_CHOICES));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JsonArray subjects = settings.getJsonArray(JSON_SUBJECT_OPTIONS);
        JsonArray numbers = settings.getJsonArray(JSON_NUMBER_OPTIONS);
        JsonArray semesters = settings.getJsonArray(JSON_SEMESTER_OPTIONS);
        JsonArray years = settings.getJsonArray(JSON_YEAR_OPTIONS);
        JsonArray schedules = settings.getJsonArray(JSON_SCHEDULE_OPTIONS);
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
        for(int i = 0 ; i < schedules.size(); i++){
            dataManager.addScheduleOptions(schedules.getString(i));
        }        
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
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        //Create expot directory
        File exportDir = new File(((CourseSiteGeneratorData)data).getExportDir());
        try{
           FileUtils.deleteDirectory(exportDir);
        }
        catch(Exception e){
            
        }
        exportDir.mkdirs();
        
        String sourceDirPath = props.getProperty(APP_PATH_SETTING) + props.getProperty(EXPORT_PATH_PUBLIC_HTML);
        File sourceDir = new File(sourceDirPath);
        
        try{
            FileUtils.copyDirectory(sourceDir, exportDir);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        String cssFilePath = props.getProperty(APP_PATH_STYLESHEET) + ((CourseSiteGeneratorData) data).getStylesheet();
        String destCssDirPath = ((CourseSiteGeneratorData)data).getExportDir() + props.getProperty(EXPORT_PATH_CSS);
        File cssFile = new File(cssFilePath);
        File cssDestDir = new File(destCssDirPath);
        try{
            FileUtils.copyFileToDirectory(cssFile, cssDestDir);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        
        //For CourseData.json
        File faviconImageFile = new File(((CourseSiteGeneratorData)data).getStyleImages(0));
        File navbarImageFile = new File(((CourseSiteGeneratorData)data).getStyleImages(1));;
        File leftFooterImageFile = new File(((CourseSiteGeneratorData)data).getStyleImages(2));;
        File rightFooterImageFile = new File(((CourseSiteGeneratorData)data).getStyleImages(3));;
        File destImageFile = new File(((CourseSiteGeneratorData)data).getExportDir() + props.getProperty(EXPORT_PATH_IMAGES));
        try{
            FileUtils.copyFileToDirectory(faviconImageFile, destImageFile);
            FileUtils.copyFileToDirectory(navbarImageFile, destImageFile);
            FileUtils.copyFileToDirectory(leftFooterImageFile, destImageFile);
            FileUtils.copyFileToDirectory(rightFooterImageFile, destImageFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        JsonArray ohsJA;
        String instructorOh = dataManager.getInstructor().getOhs();
        if(instructorOh != null && !instructorOh.isEmpty()){
              JsonReader ohJsonReader = Json.createReader(new StringReader(instructorOh));
              ohsJA = ohJsonReader.readArray();
              ohJsonReader.close();        
        }
        else
            ohsJA = Json.createArrayBuilder().build();
            
        Instructor instructor = dataManager.getInstructor();
        JsonObject instructorObject = Json.createObjectBuilder().add(JSON_NAME, instructor.getName())
            .add(JSON_ROOM, instructor.getRoom()).add(JSON_EMAIL, instructor.getEmail()).add(JSON_LINK, instructor.getHomepage())
            .add(JSON_HOURS, ohsJA).build();
            
        JsonArrayBuilder pagesArrayBuilder = Json.createArrayBuilder();
        if(dataManager.getPages(0)){
            pagesArrayBuilder.add(Json.createObjectBuilder().add(JSON_NAME, "Home").add(JSON_LINK, "index.html"));
        }
        if(dataManager.getPages(1)){
            pagesArrayBuilder.add(Json.createObjectBuilder().add(JSON_NAME, "Syllabus").add(JSON_LINK, "syllabus.html"));
        }
        if(dataManager.getPages(2)){
            pagesArrayBuilder.add(Json.createObjectBuilder().add(JSON_NAME, "Schedule").add(JSON_LINK, "schedule.html"));
        }
        if(dataManager.getPages(3)){
            pagesArrayBuilder.add(Json.createObjectBuilder().add(JSON_NAME, "HWs").add(JSON_LINK, "hws.html"));
        }
        JsonArray pagesArray = pagesArrayBuilder.build();
        JsonObjectBuilder faviconObject = Json.createObjectBuilder().add(JSON_HREF, props.getProperty(EXPORT_PATH_IMAGES) + faviconImageFile.getName());
        JsonObjectBuilder navbarObject = Json.createObjectBuilder().add(JSON_SRC, props.getProperty(EXPORT_PATH_IMAGES) + navbarImageFile.getName());
        JsonObjectBuilder bottomLeftObject = Json.createObjectBuilder().add(JSON_SRC, props.getProperty(EXPORT_PATH_IMAGES) + leftFooterImageFile.getName());
        JsonObjectBuilder bottomRightObject = Json.createObjectBuilder().add(JSON_SRC, props.getProperty(EXPORT_PATH_IMAGES) + rightFooterImageFile.getName());
        JsonObject logosObject = Json.createObjectBuilder().add(JSON_FAVICON, faviconObject).add(JSON_NAVBAR, navbarObject).add(JSON_BOTTOM_LEFT, bottomLeftObject)
                    .add(JSON_BOTTOM_RIGHT, bottomRightObject).build();

        JsonObject courseDataJSO = Json.createObjectBuilder()
                                        .add(JSON_SUBJECT, dataManager.getBannerText(0))
                                        .add(JSON_NUMBER, dataManager.getBannerText(1))
                                        .add(JSON_SEMESTER, dataManager.getBannerText(2))
                                        .add(JSON_YEAR, dataManager.getBannerText(3))
                                        .add(JSON_TITLE, dataManager.getBannerText(4))
                                        .add(JSON_LOGOS, logosObject)
                                        .add(JSON_INSTRUCTOR, instructorObject)
                                       .add(JSON_PAGES, pagesArray).build();
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(courseDataJSO);
        jsonWriter.close();
        String courseDataPath = dataManager.getExportDir() + props.getProperty(EXPORT_PATH_JS) + props.getProperty(EXPORT_PATH_PAGE_DATA_JSON);
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(courseDataPath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(courseDataJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(courseDataPath);
        pw.write(prettyPrinted);
        pw.close();    
        
        // For syllabus data
        if(dataManager.getPages(1)){
            String topicsText = dataManager.getSyllabusText(1);
            JsonReader topicsJsonReader = Json.createReader(new StringReader(topicsText));
            JsonArray topicsJA;
            if(!topicsText.isEmpty())
                    topicsJA = topicsJsonReader.readArray();
            else 
                topicsJA = Json.createArrayBuilder().build();
            topicsJsonReader.close();
            String outcomesText = dataManager.getSyllabusText(3);
            JsonReader outcomesJsonReader = Json.createReader(new StringReader(outcomesText));
            JsonArray outcomesJA;
            if(!outcomesText.isEmpty())
                outcomesJA = outcomesJsonReader.readArray();
            else
                outcomesJA = Json.createArrayBuilder().build();
            outcomesJsonReader.close();
            String textbooksText = dataManager.getSyllabusText(4);
            JsonReader textbooksJsonReader = Json.createReader(new StringReader(textbooksText));
            JsonArray textbooksJA;
            if(!textbooksText.isEmpty())
                textbooksJA = textbooksJsonReader.readArray();
            else 
                textbooksJA = Json.createArrayBuilder().build();
            textbooksJsonReader.close();        
            String componenetsText = dataManager.getSyllabusText(5);
            JsonReader componentsJsonReader = Json.createReader(new StringReader(componenetsText));
            JsonArray componentsJA;
            if(!componenetsText.isEmpty())
                componentsJA = componentsJsonReader.readArray();
            else
                componentsJA = Json.createArrayBuilder().build();
            componentsJsonReader.close();        
            
            JsonObject syllabusDataJSO = Json.createObjectBuilder()
                                             .add(JSON_DESCRIPTION, dataManager.getSyllabusText(0))
                                            .add(JSON_TOPICS, topicsJA)
                                            .add(JSON_PREREQUISITES, dataManager.getSyllabusText(2))
                                            .add(JSON_OUTCOMES, outcomesJA)
                                            .add(JSON_TEXTBOOKS, textbooksJA)
                                            .add(JSON_GRADED_COMPONENTS, componentsJA)
                                            .add(JSON_GRADING_NOTE, dataManager.getSyllabusText(6))
                                            .add(JSON_ACADEMIC_DISHONESTY, dataManager.getSyllabusText(7))
                                            .add(JSON_SPECIAL_ASSISTANCE, dataManager.getSyllabusText(8))
                                                    .build();
            Map<String, Object> properties1 = new HashMap<>(1);
            properties1.put(JsonGenerator.PRETTY_PRINTING, true);
            JsonWriterFactory writerFactory1 = Json.createWriterFactory(properties1);
            StringWriter sw1 = new StringWriter();
            JsonWriter jsonWriter1 = writerFactory1.createWriter(sw1);
            jsonWriter1.writeObject(syllabusDataJSO);
            jsonWriter1.close();
            String syllabusDataPath = dataManager.getExportDir() + props.getProperty(EXPORT_PATH_JS) + props.getProperty(EXPORT_PATH_SYLLABUS_DATA_JSON);
            // INIT THE WRITER
            OutputStream os1 = new FileOutputStream(syllabusDataPath);
            JsonWriter jsonFileWriter1 = Json.createWriter(os1);
            jsonFileWriter1.writeObject(syllabusDataJSO);
            String prettyPrinted1 = sw1.toString();
            PrintWriter pw1 = new PrintWriter(syllabusDataPath);
            pw1.write(prettyPrinted1);
            pw1.close();    
        }
        //For Schedule Data
        if(dataManager.getPages(2)){
            JsonArrayBuilder scheduleHolidaysArrayBuilder = Json.createArrayBuilder();
            JsonArrayBuilder scheduleLecturesArrayBuilder = Json.createArrayBuilder();
            JsonArrayBuilder scheduleReferencesArrayBuilder = Json.createArrayBuilder();
            JsonArrayBuilder scheduleRecitationsArrayBuilder = Json.createArrayBuilder();
            JsonArrayBuilder scheduleHwsArrayBuilder = Json.createArrayBuilder();
            Iterator<ScheduleItem> schedulesIterator = dataManager.schedulesIterator();
            while(schedulesIterator.hasNext()){
                ScheduleItem schedule = schedulesIterator.next();
                String month = schedule.getDate().substring(schedule.getDate().indexOf("-") + 1, schedule.getDate().lastIndexOf("-"));
                String day = schedule.getDate().substring(schedule.getDate().lastIndexOf("-") + 1);
                if(month.startsWith("0"))
                    month = month.substring(1);
                if(day.startsWith("0"))
                    day = day.substring(1);
                if(schedule.getType().equals("Holiday")){
                    JsonObject scheduleJson = Json.createObjectBuilder()
                            .add(JSON_MONTH, month)
                            .add(JSON_DAY, day)
                            .add(JSON_TITLE, schedule.getTitle())
                            .add(JSON_LINK, schedule.getLink()).build();
                    scheduleHolidaysArrayBuilder.add(scheduleJson);
                }
                else if(schedule.getType().equals("Lecture")){
                    JsonObject scheduleJson = Json.createObjectBuilder()
                            .add(JSON_MONTH, month)
                            .add(JSON_DAY, day)
                            .add(JSON_TITLE, schedule.getTitle()).add(JSON_TOPIC, schedule.getTopic())
                            .add(JSON_LINK, schedule.getLink()).build();
                    scheduleLecturesArrayBuilder.add(scheduleJson);
                }
                else if(schedule.getType().equals("Reference")){
                    JsonObject scheduleJson = Json.createObjectBuilder()
                            .add(JSON_MONTH, month)
                            .add(JSON_DAY, day)
                            .add(JSON_TITLE, schedule.getTitle()).add(JSON_TOPIC, schedule.getTopic())
                            .add(JSON_LINK, schedule.getLink()).add(JSON_TIME, "")
                            .add(JSON_CRITERIA, "none").build();
                    scheduleReferencesArrayBuilder.add(scheduleJson);
                }
                else if(schedule.getType().equals("Recitation")){
                    JsonObject scheduleJson = Json.createObjectBuilder()
                            .add(JSON_MONTH, month)
                            .add(JSON_DAY, day)
                            .add(JSON_TITLE, schedule.getTitle()).add(JSON_TOPIC, schedule.getTopic())
                            .add(JSON_LINK, schedule.getLink()).build();
                    scheduleRecitationsArrayBuilder.add(scheduleJson);
                }
                else if(schedule.getType().equals("HW")){
                    JsonObject scheduleJson = Json.createObjectBuilder()
                            .add(JSON_MONTH, month)
                            .add(JSON_DAY, day)
                            .add(JSON_TITLE, schedule.getTitle()).add(JSON_TOPIC, schedule.getTopic())
                            .add(JSON_LINK, schedule.getLink()).build();
                    scheduleHwsArrayBuilder.add(scheduleJson);
                }
            }         
            JsonArray scheduleHolidaysArray = scheduleHolidaysArrayBuilder.build();
            JsonArray scheduleLecturesArray =  scheduleLecturesArrayBuilder.build();
            JsonArray scheduleReferencesArray = scheduleReferencesArrayBuilder.build();
            JsonArray scheduleRecitationsArray =  scheduleRecitationsArrayBuilder.build();
            JsonArray scheduleHwsArray =  scheduleHwsArrayBuilder.build(); 
            JsonObject scheduleDataJSO = Json.createObjectBuilder()
                                            .add(JSON_STARTING_MONDAY_MONTH, dataManager.getStartingMonday().getMonthValue() + "")
                                            .add(JSON_STARTING_MONDAY_DAY, dataManager.getStartingMonday().getDayOfMonth() + "")
                                            .add(JSON_ENDING_FRIDAY_MONTH, dataManager.getEndingFriday().getMonthValue() + "")
                                            .add(JSON_ENDING_FRIDAY_DAY, dataManager.getEndingFriday().getDayOfMonth() + "")
                                            .add(JSON_HOLIDAY, scheduleHolidaysArray)
                                            .add(JSON_LECTURES, scheduleLecturesArray)
                                            .add(JSON_REFERENCES, scheduleReferencesArray)
                                            .add(JSON_RECITATIONS, scheduleRecitationsArray)
                                            .add(JSON_HWS, scheduleHwsArray)
                                                    .build();    
            Map<String, Object> properties2 = new HashMap<>(1);
            properties2.put(JsonGenerator.PRETTY_PRINTING, true);
            JsonWriterFactory writerFactory2 = Json.createWriterFactory(properties2);
            StringWriter sw2 = new StringWriter();
            JsonWriter jsonWriter2 = writerFactory2.createWriter(sw2);
            jsonWriter2.writeObject(scheduleDataJSO);
            jsonWriter2.close();
            String scheduleDataPath = dataManager.getExportDir() + props.getProperty(EXPORT_PATH_JS) + props.getProperty(EXPORT_PATH_SCHEDULE_DATA_JSON);
            // INIT THE WRITER
            OutputStream os2 = new FileOutputStream(scheduleDataPath);
            JsonWriter jsonFileWriter2 = Json.createWriter(os2);
            jsonFileWriter2.writeObject(scheduleDataJSO);
            String prettyPrinted2 = sw2.toString();
            PrintWriter pw2 = new PrintWriter(scheduleDataPath);
            pw2.write(prettyPrinted2);
            pw2.close();            
        }
        // Section Data
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
        JsonObject sectionsDataJSO = Json.createObjectBuilder()
                                        .add(JSON_LECTURES, lecturesArray)
                                        .add(JSON_RECITATIONS, recitationsArray)
                                        .add(JSON_LABS, labsArray)
                                                .build();    
        Map<String, Object> properties3 = new HashMap<>(1);
        properties3.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory3 = Json.createWriterFactory(properties3);
        StringWriter sw3 = new StringWriter();
        JsonWriter jsonWriter3 = writerFactory3.createWriter(sw3);
        jsonWriter3.writeObject(sectionsDataJSO);
        jsonWriter3.close();
        String sectionsDataPath = dataManager.getExportDir() + props.getProperty(EXPORT_PATH_JS) + props.getProperty(EXPORT_PATH_SECTIONS_DATA_JSON);
        // INIT THE WRITER
        OutputStream os3 = new FileOutputStream(sectionsDataPath);
        JsonWriter jsonFileWriter3 = Json.createWriter(os3);
        jsonFileWriter3.writeObject(sectionsDataJSO);
        String prettyPrinted3 = sw3.toString();
        PrintWriter pw3 = new PrintWriter(sectionsDataPath);
        pw3.write(prettyPrinted3);
        pw3.close();            
        
        // Office Hours Data
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

            String startTime = dataManager.getStartTime();
            String startTimeHour = startTime.substring(0, dataManager.getStartTime().indexOf(":"));
            if(startTime.endsWith("pm")){
                startTimeHour = Integer.parseInt(startTimeHour) + 12 + "";
                if(startTimeHour.equals("24"))
                    startTimeHour = "0";
            }
            String endTime = dataManager.getEndTime();
            String endTimeHour = endTime.substring(0, dataManager.getEndTime().indexOf(":"));
            if(endTime.endsWith("pm")){
                endTimeHour = Integer.parseInt(endTimeHour) + 12 + "";
                if(endTimeHour.equals("24"))
                    endTimeHour = "0";
            }
            JsonObject ohsDataJSO = Json.createObjectBuilder()
                                            .add(JSON_START_HOUR, startTimeHour)
                                            .add(JSON_END_HOUR, endTimeHour)
                                            .add(JSON_INSTRUCTOR, instructorObject)
                                            .add(JSON_GRAD_TAS, gradTAsArray)
                                            .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                                            .add(JSON_OFFICE_HOURS, ohArray)
                                                    .build();
            Map<String, Object> properties4 = new HashMap<>(1);
            properties4.put(JsonGenerator.PRETTY_PRINTING, true);
            JsonWriterFactory writerFactory4 = Json.createWriterFactory(properties4);
            StringWriter sw4 = new StringWriter();
            JsonWriter jsonWriter4 = writerFactory4.createWriter(sw4);
            jsonWriter4.writeObject(ohsDataJSO);
            jsonWriter4.close();
            String ohsDataPath = dataManager.getExportDir() + props.getProperty(EXPORT_PATH_JS) + props.getProperty(EXPORT_PATH_OFFICE_HOURS_DATA_JSON);
            // INIT THE WRITER
            OutputStream os4 = new FileOutputStream(ohsDataPath);
            JsonWriter jsonFileWriter4 = Json.createWriter(os4);
            jsonFileWriter4.writeObject(ohsDataJSO);
            String prettyPrinted4 = sw4.toString();
            PrintWriter pw4 = new PrintWriter(ohsDataPath);
            pw4.write(prettyPrinted4);
            pw4.close();            
        
            if(dataManager.getPages(0)){
                String indexHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_INDEX_PAGE);
                File indexHtml = new File(indexHtmlPath);
                BufferedReader input = new BufferedReader(new FileReader(indexHtml));
                String fileString = "";
                String newLine;
                while((newLine = input.readLine()) != null)
                    fileString += newLine + "\n";
                input.close();
                fileString = fileString.replace(ORIGINAL_CSS, dataManager.getStylesheet());
                BufferedWriter output = new BufferedWriter(new FileWriter(indexHtml));
                output.write(fileString, 0, fileString.length());
                output.flush();
                output.close();
            }
            else{
                String indexHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_INDEX_PAGE);
                File indexHtml = new File(indexHtmlPath);
                indexHtml.delete();
            }
            if(dataManager.getPages(1)){
                String syllabusHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_SYLLABUS_PAGE);
                File syllabusHtml = new File(syllabusHtmlPath);
                BufferedReader input = new BufferedReader(new FileReader(syllabusHtml));
                String fileString = "";
                String newLine;
                while((newLine = input.readLine()) != null)
                    fileString += newLine + "\n";
                input.close();
                fileString = fileString.replace(ORIGINAL_CSS, dataManager.getStylesheet());
                BufferedWriter output = new BufferedWriter(new FileWriter(syllabusHtml));
                output.write(fileString, 0, fileString.length());
                output.flush();
                output.close();                
            }
            else{
                String syllabusHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_SYLLABUS_PAGE);
                File syllabusHtml = new File(syllabusHtmlPath);
                syllabusHtml.delete();
            }
            if(dataManager.getPages(2)){
                String scheduleHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_SCHEDULE_PAGE);
                File scheduleHtml = new File(scheduleHtmlPath);
                BufferedReader input = new BufferedReader(new FileReader(scheduleHtml));
                String fileString = "";
                String newLine;
                while((newLine = input.readLine()) != null)
                    fileString += newLine + "\n";
                input.close();
                fileString = fileString.replace(ORIGINAL_CSS, dataManager.getStylesheet());
                BufferedWriter output = new BufferedWriter(new FileWriter(scheduleHtml));
                output.write(fileString, 0, fileString.length());
                output.flush();
                output.close();      
            }
            else{
                String scheduleHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_SCHEDULE_PAGE);
                File scheduleHtml = new File(scheduleHtmlPath);
                scheduleHtml.delete();
            }
            if(dataManager.getPages(3)){
                String hwsHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_HWS_PAGE);
                File hwsHtml = new File(hwsHtmlPath);
                BufferedReader input = new BufferedReader(new FileReader(hwsHtml));
                String fileString = "";
                String newLine;
                while((newLine = input.readLine()) != null)
                    fileString += newLine + "\n";
                input.close();
                fileString = fileString.replace(ORIGINAL_CSS, dataManager.getStylesheet());
                BufferedWriter output = new BufferedWriter(new FileWriter(hwsHtml));
                output.write(fileString, 0, fileString.length());
                output.flush();
                output.close();      
            }
            else{
                String hwsHtmlPath = dataManager.getExportDir() + props.getProperty(APP_EXPORT_HWS_PAGE);
                File hwsHtml = new File(hwsHtmlPath);
                hwsHtml.delete();
            }
        CourseSiteGeneratorDialog.showExportDialog(app, dataManager);
    }

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
