/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_ALL_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_GRAD_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_UNDERGRA_RB;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_GRA;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_UNDERGRA;
import csg.data.TimeSlot.DayOfWeek;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorData  implements AppDataComponent{
        CourseSiteGeneratorApp app;
        
        // Site
        private String[] bannerText;
        private String exportDir;
        private boolean pages[];
        private String[] styleImages;
        private String stylesheet;
        private Instructor instructor;
        private ObservableList<String> subjectOptions;
        private ObservableList<String> numberOptions;
        private ObservableList<String> semesterOptions;
        private ObservableList<String> yearOptions;
        
        
        private String[] syllabusText;

        // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
        // DATA IN THE ROWS OF THE TABLE VIEW
        private ObservableList<TeachingAssistantPrototype> teachingAssistants;
        private ObservableList<TeachingAssistantPrototype> graduateTeachingAssistants;
        private ObservableList<TeachingAssistantPrototype> undergraduateTeachingAssistants;

        private ObservableList<TimeSlot> officeHours;

        // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
        // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
        // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
        // NO MEANS FOR CHANGING THESE VALUES
        private int startHour;
        private int endHour;

        // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
        public static final int MIN_START_HOUR = 9;
        public static final int MAX_END_HOUR = 20;
    
        public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp) {
            // KEEP THIS FOR LATER
            app = initApp;
            AppGUIModule gui = app.getGUIModule();
            bannerText = new String[5];
            exportDir = "";
            pages = new boolean[4];
            styleImages = new String[4];
            instructor = new Instructor("", "", "", "", "");
            subjectOptions = FXCollections.observableArrayList();
            numberOptions = FXCollections.observableArrayList();
            semesterOptions = FXCollections.observableArrayList();
            yearOptions = FXCollections.observableArrayList();
            
            syllabusText = new String[9];
            
            graduateTeachingAssistants = FXCollections.observableArrayList();
            undergraduateTeachingAssistants = FXCollections.observableArrayList();

            // CONSTRUCT THE LIST OF TAs FOR THE TABLE
            TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
            teachingAssistants = taTableView.getItems();
            graduateTeachingAssistants.addListener(new ListChangeListener<TeachingAssistantPrototype>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends TeachingAssistantPrototype> change) {
                    getTeachingAssistants().clear();
                    getTeachingAssistants().addAll(getGraduateTeachingAssistants());
                    getTeachingAssistants().addAll(getUndergraduateTeachingAssistants());              
                }
            });
            undergraduateTeachingAssistants.addListener(new ListChangeListener<TeachingAssistantPrototype>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends TeachingAssistantPrototype> change) {
                    getTeachingAssistants().clear();
                    getTeachingAssistants().addAll(getGraduateTeachingAssistants());
                    getTeachingAssistants().addAll(getUndergraduateTeachingAssistants());                
                }
            });

            // THESE ARE THE DEFAULT OFFICE HOURS
            startHour = MIN_START_HOUR;
            endHour = MAX_END_HOUR;

            resetOfficeHours();
        }
        
    private void resetOfficeHours() {
        //THIS WILL STORE OUR OFFICE HOURS
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(CSG_OH_TABLEVIEW);
        setOfficeHours(officeHoursTableView.getItems()); 
        getOfficeHours().clear();
        for (int i = getStartHour(); i <= getEndHour(); i++) {
            TimeSlot timeSlot = new TimeSlot(   this.getTimeString(i, true),
                                                this.getTimeString(i, false));
            getOfficeHours().add(timeSlot);
            
            TimeSlot halfTimeSlot = new TimeSlot(   this.getTimeString(i, false),
                                                    this.getTimeString(i+1, true));
            getOfficeHours().add(halfTimeSlot);
        }
    }
    
    private String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if (initStartHour <= initEndHour) {
            // THESE ARE VALID HOURS SO KEEP THEM
            // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
            setStartHour(initStartHour);
            setEndHour(initEndHour);
        }
        resetOfficeHours();
    }
        
    /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
    @Override
    public void reset() {
        setStartHour(MIN_START_HOUR);
        setEndHour(MAX_END_HOUR);
        getUndergraduateTeachingAssistants().clear();
        getGraduateTeachingAssistants().clear();
        getTeachingAssistants().clear();
        resetOfficeHours();
//        
//        for (int i = 0; i < officeHours.size(); i++) {
//            TimeSlot timeSlot = officeHours.get(i);
//            timeSlot.reset();
//        }
    }
    
    // ACCESSOR METHODS

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
    
    public boolean isTASelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView tasTable = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        return tasTable.getSelectionModel().getSelectedItem() != null;
    }
    
    public TeachingAssistantPrototype getSelectedTA(){
        AppGUIModule gui = app.getGUIModule();
        TableView tasTable = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        return (TeachingAssistantPrototype) tasTable.getSelectionModel().getSelectedItem();        
    }
    
    public void addTA(TeachingAssistantPrototype ta, String type) {        
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> officeHoursTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW); 
        if (type.equals(TA_TYPE_UNDERGRA) && !undergraduateTeachingAssistants.contains(ta)){
            getUndergraduateTeachingAssistants().add(ta);        
            getUndergraduateTeachingAssistants().sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
        }
        else if(!graduateTeachingAssistants.contains(ta))
            getGraduateTeachingAssistants().add(ta);
            getGraduateTeachingAssistants().sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
    }
    
    public void removeTA(TeachingAssistantPrototype ta) {
        // REMOVE THE TA FROM THE LIST OF TAs
        if(ta.getType().equals(TA_TYPE_UNDERGRA)){
            getUndergraduateTeachingAssistants().remove(ta);    
            for(TimeSlot oh: ta.getOH().keySet()){
                oh.removeTA(ta);
            }
        }else{
            getGraduateTeachingAssistants().remove(ta);        
            for(TimeSlot oh: ta.getOH().keySet()){                
                oh.removeTA(ta);
            }
        }
        
        // AND REMOVE THE TA FROM ALL THEIR OFFICE HOURS
    }
    
    public boolean isDayOfWeekColumn(int columnNumber) {
        return columnNumber >= 2;
    }
    
    public DayOfWeek getColumnDayOfWeek(int columnNumber) {
        return TimeSlot.DayOfWeek.values()[columnNumber-2];
    }

    public Iterator<TeachingAssistantPrototype> teachingAssistantsIterator() {
        return getTeachingAssistants().iterator();
    }
    
    public Iterator<TimeSlot> officeHoursIterator() {
        return getOfficeHours().iterator();
    }

    public TeachingAssistantPrototype getTAWithName(String name) {
        Iterator<TeachingAssistantPrototype> taIterator = getTeachingAssistants().iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getName().equals(name))
                return ta;
        }
        return null;
    }

    public TimeSlot getTimeSlot(String startTime) {
        Iterator<TimeSlot> timeSlotsIterator = getOfficeHours().iterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotStartTime = timeSlot.getStartTime().replace(":", "_");
            if (timeSlotStartTime.equals(startTime))
                return timeSlot;
        }
        return null;
    }
    
    public boolean containsTA(String name){
        for(TeachingAssistantPrototype ta: getTeachingAssistants()){
            if(ta.getName().equals(name))
                return true;
        }
        return false;
    }
    
    public boolean containsEmail(String email){
        for(TeachingAssistantPrototype ta: getTeachingAssistants()){
            if(ta.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public String getCurrentTAType(){
        AppGUIModule gui = app.getGUIModule();
        RadioButton allRB = (RadioButton)gui.getGUINode(CSG_OH_TAS_ALL_RB);
        RadioButton underg = (RadioButton)gui.getGUINode(CSG_OH_TAS_UNDERGRA_RB);
        RadioButton graduate = (RadioButton)gui.getGUINode(CSG_OH_TAS_GRAD_RB);
        if (underg.isSelected())
            return TA_TYPE_UNDERGRA;
        else
            return TA_TYPE_GRA;
    }
    
    public boolean isAllRBSelected(){        
        AppGUIModule gui = app.getGUIModule();
        return ((RadioButton)gui.getGUINode(CSG_OH_TAS_ALL_RB)).isSelected();
    }
    
    public void setGraduateTA(){        
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        taTableView.setItems(getGraduateTeachingAssistants());
        for(TimeSlot oh: getOfficeHours())
            oh.setGradTA();
    }
        
    public void setUndergraduateTA(){
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        taTableView.setItems(getUndergraduateTeachingAssistants());
        for(TimeSlot oh: getOfficeHours())
            oh.setUndergraTA();
    }
        
    public void setAllTA(){
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        getTeachingAssistants().clear();
        getTeachingAssistants().addAll(getGraduateTeachingAssistants());
        getTeachingAssistants().addAll(getUndergraduateTeachingAssistants());
        taTableView.setItems(getTeachingAssistants());
        for(TimeSlot oh: getOfficeHours()){
            oh.setAllTA();
        }
    }
    
    public ObservableList<TeachingAssistantPrototype> getCurrentList(){
        AppGUIModule gui = app.getGUIModule();
        return ((TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW)).getItems();
    }
    
    public void doTypeEdited(TeachingAssistantPrototype TA){
        if(TA.getType().equals(TA_TYPE_UNDERGRA)){
            getGraduateTeachingAssistants().remove(TA);
            getUndergraduateTeachingAssistants().add(TA);            
            getUndergraduateTeachingAssistants().sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
            for(TimeSlot oh: TA.getOH().keySet()){
                oh.fromGraToUndergra(TA);
            }
        }else{
            getUndergraduateTeachingAssistants().remove(TA);
            getGraduateTeachingAssistants().add(TA);            
            getGraduateTeachingAssistants().sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
            for(TimeSlot oh: TA.getOH().keySet()){
                oh.fromUndergraToGra(TA);
            }
        }
    }

    
    /**
     * @return the bannerText
     */
    public String getBannerText(int i) {
        return bannerText[i];
    }

    /**
     * @param bannerText the bannerText to set
     */
    public void setBannerText(int i, String bannerText) {
        this.bannerText[i] = bannerText;
    }

    public boolean hasBannerText(){
        for(int i = 0 ; i < 4; i++)
            if(bannerText[i] == "")
                return false;
        return true;
    }
    /**
     * @return the exportDir
     */
    public String getExportDir() {
        return exportDir;
    }

    public void updateExportDir(){
        if(hasBannerText())
            exportDir = ".\\export\\" + bannerText[0] + "_ "+bannerText[1] + "_ "+ bannerText[2] + "_ "+ 
                bannerText[3] + "\\public_html";
    }
    /**
     * @param exportDir the exportDir to set
     */
    public void setExportDir(String exportDir) {
        this.exportDir = exportDir;
    }

    /**
     * @return the pages
     */
    public boolean getPages(int i) {
        return pages[i];
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(int i, boolean pages) {
        this.pages[i] = pages;
    }

    /**
     * @return the styleImages
     */
    public String getStyleImages(int i) {
        return styleImages[i];
    }

    /**
     * @param styleImages the styleImages to set
     */
    public void setStyleImages(String styleImages, int i) {
        this.styleImages[i] = styleImages;
    }

    /**
     * @return the instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * @return the subjectOptions
     */
    public ObservableList<String> getSubjectOptions() {
        return subjectOptions;
    }

    /**
     * @return the numberOptions
     */
    public ObservableList<String> getNumberOptions() {
        return numberOptions;
    }

    /**
     * @return the semesterOptions
     */
    public ObservableList<String> getSemesterOptions() {
        return semesterOptions;
    }


    /**
     * @return the yearOptions
     */
    public ObservableList<String> getYearOptions() {
        return yearOptions;
    }

    /**
     * @return the syllabusText
     */
    public String[] getSyllabusText(int i) {
        return syllabusText;
    }

    /**
     * @param syllabusText the syllabusText to set
     */
    public void setSyllabusText(int i, String syllabusText) {
        this.syllabusText[i] = syllabusText;
    }

    /**
     * @return the teachingAssistants
     */
    public ObservableList<TeachingAssistantPrototype> getTeachingAssistants() {
        return teachingAssistants;
    }

    /**
     * @return the graduateTeachingAssistants
     */
    public ObservableList<TeachingAssistantPrototype> getGraduateTeachingAssistants() {
        return graduateTeachingAssistants;
    }

    /**
     * @return the undergraduateTeachingAssistants
     */
    public ObservableList<TeachingAssistantPrototype> getUndergraduateTeachingAssistants() {
        return undergraduateTeachingAssistants;
    }

    /**
     * @return the officeHours
     */
    public ObservableList<TimeSlot> getOfficeHours() {
        return officeHours;
    }

    /**
     * @param startHour the startHour to set
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
    
    /**
     * @param officeHours the officeHours to set
     */
    public void setOfficeHours(ObservableList<TimeSlot> officeHours) {
        this.officeHours = officeHours;
    }
    
    
    /**
     * @return the stylesheet
     */
    public String getStylesheet() {
        return stylesheet;
    }

    /**
     * @param stylesheet the stylesheet to set
     */
    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

}

