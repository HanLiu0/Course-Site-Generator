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

        // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
        // DATA IN THE ROWS OF THE TABLE VIEW
        ObservableList<TeachingAssistantPrototype> teachingAssistants;
        ObservableList<TeachingAssistantPrototype> graduateTeachingAssistants;
        ObservableList<TeachingAssistantPrototype> undergraduateTeachingAssistants;

        ObservableList<TimeSlot> officeHours;


        // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
        // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
        // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
        // NO MEANS FOR CHANGING THESE VALUES
        int startHour;
        int endHour;

        // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
        public static final int MIN_START_HOUR = 9;
        public static final int MAX_END_HOUR = 20;
    
        public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp) {
            // KEEP THIS FOR LATER
            app = initApp;
            AppGUIModule gui = app.getGUIModule();

            graduateTeachingAssistants = FXCollections.observableArrayList();
            undergraduateTeachingAssistants = FXCollections.observableArrayList();

            // CONSTRUCT THE LIST OF TAs FOR THE TABLE
            TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
            teachingAssistants = taTableView.getItems();
            graduateTeachingAssistants.addListener(new ListChangeListener<TeachingAssistantPrototype>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends TeachingAssistantPrototype> change) {
                    teachingAssistants.clear();
                    teachingAssistants.addAll(graduateTeachingAssistants);
                    teachingAssistants.addAll(undergraduateTeachingAssistants);              
                }
            });
            undergraduateTeachingAssistants.addListener(new ListChangeListener<TeachingAssistantPrototype>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends TeachingAssistantPrototype> change) {
                    teachingAssistants.clear();
                    teachingAssistants.addAll(graduateTeachingAssistants);
                    teachingAssistants.addAll(undergraduateTeachingAssistants);                
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
        officeHours = officeHoursTableView.getItems(); 
        officeHours.clear();
        for (int i = startHour; i <= endHour; i++) {
            TimeSlot timeSlot = new TimeSlot(   this.getTimeString(i, true),
                                                this.getTimeString(i, false));
            officeHours.add(timeSlot);
            
            TimeSlot halfTimeSlot = new TimeSlot(   this.getTimeString(i, false),
                                                    this.getTimeString(i+1, true));
            officeHours.add(halfTimeSlot);
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
            startHour = initStartHour;
            endHour = initEndHour;
        }
        resetOfficeHours();
    }
        
    /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
    @Override
    public void reset() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        undergraduateTeachingAssistants.clear();
        graduateTeachingAssistants.clear();
        teachingAssistants.clear();
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
            undergraduateTeachingAssistants.add(ta);        
            undergraduateTeachingAssistants.sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
        }
        else if(!graduateTeachingAssistants.contains(ta))
            graduateTeachingAssistants.add(ta);
            graduateTeachingAssistants.sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
    }
    
    public void removeTA(TeachingAssistantPrototype ta) {
        // REMOVE THE TA FROM THE LIST OF TAs
        if(ta.getType().equals(TA_TYPE_UNDERGRA)){
            undergraduateTeachingAssistants.remove(ta);    
            for(TimeSlot oh: ta.getOH().keySet()){
                oh.removeTA(ta);
            }
        }else{
            graduateTeachingAssistants.remove(ta);        
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
        return teachingAssistants.iterator();
    }
    
    public Iterator<TimeSlot> officeHoursIterator() {
        return officeHours.iterator();
    }

    public TeachingAssistantPrototype getTAWithName(String name) {
        Iterator<TeachingAssistantPrototype> taIterator = teachingAssistants.iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getName().equals(name))
                return ta;
        }
        return null;
    }

    public TimeSlot getTimeSlot(String startTime) {
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotStartTime = timeSlot.getStartTime().replace(":", "_");
            if (timeSlotStartTime.equals(startTime))
                return timeSlot;
        }
        return null;
    }
    
    public boolean containsTA(String name){
        for(TeachingAssistantPrototype ta: teachingAssistants){
            if(ta.getName().equals(name))
                return true;
        }
        return false;
    }
    
    public boolean containsEmail(String email){
        for(TeachingAssistantPrototype ta: teachingAssistants){
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
        taTableView.setItems(graduateTeachingAssistants);
        for(TimeSlot oh: officeHours)
            oh.setGradTA();
    }
        
    public void setUndergraduateTA(){
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        taTableView.setItems(undergraduateTeachingAssistants);
        for(TimeSlot oh: officeHours)
            oh.setUndergraTA();
    }
        
    public void setAllTA(){
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        teachingAssistants.clear();
        teachingAssistants.addAll(graduateTeachingAssistants);
        teachingAssistants.addAll(undergraduateTeachingAssistants);
        taTableView.setItems(teachingAssistants);
        for(TimeSlot oh: officeHours){
            oh.setAllTA();
        }
    }
    
    public ObservableList<TeachingAssistantPrototype> getCurrentList(){
        AppGUIModule gui = app.getGUIModule();
        return ((TableView)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW)).getItems();
    }
    
    public void doTypeEdited(TeachingAssistantPrototype TA){
        if(TA.getType().equals(TA_TYPE_UNDERGRA)){
            graduateTeachingAssistants.remove(TA);
            undergraduateTeachingAssistants.add(TA);            
            undergraduateTeachingAssistants.sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
            for(TimeSlot oh: TA.getOH().keySet()){
                oh.fromGraToUndergra(TA);
            }
        }else{
            undergraduateTeachingAssistants.remove(TA);
            graduateTeachingAssistants.add(TA);            
            graduateTeachingAssistants.sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
            for(TimeSlot oh: TA.getOH().keySet()){
                oh.fromUndergraToGra(TA);
            }
        }
    }

}
