/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.controllers;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ADD_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ADD_UPDATE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_DP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_LINK_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TITLE_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TOPIC_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_DIR_LABEL;
import csg.data.CourseSiteGeneratorData;
import csg.data.LabItem;
import csg.data.LectureItem;
import csg.data.RecitationItem;
import csg.data.ScheduleItem;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.transactions.AddLab_Transaction;
import csg.transactions.AddLecture_Transaction;
import csg.transactions.AddOH_Transaction;
import csg.transactions.AddRecitation_Transaction;
import csg.transactions.AddSchedule_Transaction;
import csg.transactions.AddTA_Transaction;
import csg.transactions.EditBanner_Transaction;
import csg.transactions.EditInstructor_Transaction;
import csg.transactions.EditLab_Transaction;
import csg.transactions.EditLecture_Transaction;
import csg.transactions.EditPages_Transaction;
import csg.transactions.EditRecitation_Transaction;
import csg.transactions.EditStartEndDate_Transaction;
import csg.transactions.EditStyleImages_Transaction;
import csg.transactions.EditStyleSheet_Transaction;
import csg.transactions.EditSyllabusDetails_Transaction;
import csg.transactions.RemoveLab_Transaction;
import csg.transactions.RemoveLecture_Transaction;
import csg.transactions.RemoveOH_Transaction;
import csg.transactions.RemoveRecitation_Transaction;
import csg.transactions.RemoveTA_Transaction;
import csg.transactions.SelectTimeRange_Transaction;
import csg.workspace.dialogs.CourseSiteGeneratorDialog;
import djf.modules.AppGUIModule;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import jtps.jTPS;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorController {
    
    CourseSiteGeneratorApp app;

    public CourseSiteGeneratorController(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }
    
    public void processEditBanner(int index, Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        Node node = gui.getGUINode(nodeId);
        String text;
        if(index < 4)
            text = (String)((ComboBox)node).getEditor().getText();
        else
            text = (String)((TextField)node).getText();
        Label exportDIrLabel = (Label)gui.getGUINode(CSG_SITE_DIR_LABEL);
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        jTPS jtps = app.getTPS();
        jTPS_Transaction t = jtps.getMostRecentTransaction();
        if(t != null && t instanceof EditBanner_Transaction){
            if (((EditBanner_Transaction)t).getIndex() == index){
                ((EditBanner_Transaction)t).updateTransaction(text);
                return;
            }
        }
        EditBanner_Transaction transaction = new EditBanner_Transaction(data, node, exportDIrLabel, index, text);
        app.processTransaction(transaction);
    }

    public void processEditPages(int index, Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        CheckBox cb = (CheckBox)gui.getGUINode(nodeId);
        boolean selected = cb.isSelected();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        EditPages_Transaction transaction = new EditPages_Transaction(data, cb, index, selected);
        app.processTransaction(transaction);
    }
    
    public void processEditStyleImages(int index, Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        ImageView imageview = (ImageView)gui.getGUINode(nodeId);
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        String path = CourseSiteGeneratorDialog.showChooseImageDialog(gui.getWindow());
        if(path == null)
            return;
        EditStyleImages_Transaction transaction = new EditStyleImages_Transaction(data, imageview, path, index);
        app.processTransaction(transaction);    
    }

    public void processEditStyleSheet(Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        ComboBox cb = (ComboBox)gui.getGUINode(nodeId);
        String text = (String)cb.getValue();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        EditStyleSheet_Transaction transaction = new EditStyleSheet_Transaction(data, cb, text);
        app.processTransaction(transaction);    
    }
    
    public void processEditInstructor(int index, Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        Node node = gui.getGUINode(nodeId);
        String text;
        if(index < 4)
            text = ((TextField)node).getText();
        else
            text = ((TextArea)node).getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        jTPS jtps = app.getTPS();
        jTPS_Transaction t = jtps.getMostRecentTransaction();
        if(t != null && t instanceof EditInstructor_Transaction){
            if (((EditInstructor_Transaction)t).getIndex() == index){
                ((EditInstructor_Transaction)t).updateTransaction(text);
                return;
            }
        }
        EditInstructor_Transaction transaction = new EditInstructor_Transaction(data, node, index, text);
        app.processTransaction(transaction);      
    }
    
    public void processEditSyllabusDetails(int index, Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        TextArea textArea = (TextArea)gui.getGUINode(nodeId);
        String text = textArea.getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        jTPS jtps = app.getTPS();
        jTPS_Transaction t = jtps.getMostRecentTransaction();
        if(t != null && t instanceof EditSyllabusDetails_Transaction){
            if (((EditSyllabusDetails_Transaction)t).getIndex() == index){
                ((EditSyllabusDetails_Transaction)t).updateTransaction(text);
                return;
            }
        }
        EditSyllabusDetails_Transaction transaction = new EditSyllabusDetails_Transaction(data, textArea, index, text);
        app.processTransaction(transaction);      
    }

    public void processAddTA() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(CSG_OH_TAS_NAME_TEXT_FIELD);
        String name = nameTF.getText();
        TextField emailTF = (TextField) gui.getGUINode(CSG_OH_TAS_EMAIL_TEXT_FIELD);
        String email = emailTF.getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name,email, data.getCurrentTAType());
        AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta, data.getCurrentList());
        app.processTransaction(addTATransaction);
        // NOW CLEAR THE TEXT FIELDS
        nameTF.setText("");       
        emailTF.setText("");
        nameTF.requestFocus();
    }
    
    public void processEditTA(){        
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> taTable = (TableView<TeachingAssistantPrototype>)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        TeachingAssistantPrototype selectedTA = taTable.getSelectionModel().getSelectedItem();
        CourseSiteGeneratorDialog.showEditDialog(app, selectedTA);
    }    
    
    public void processRemoveTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<TeachingAssistantPrototype> taTable = (TableView<TeachingAssistantPrototype>)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        TableView<TimeSlot> ohTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OH_TABLEVIEW);        
        TeachingAssistantPrototype selectedTA = taTable.getSelectionModel().getSelectedItem();
        if(selectedTA == null)
            return;
        RemoveTA_Transaction removeTATransaction = new RemoveTA_Transaction(data, selectedTA, ohTable);
        app.processTransaction(removeTATransaction);        
    }
    
    public void processSelectTimeRange(int index, Object nodeId){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        ComboBox cb = (ComboBox)gui.getGUINode(nodeId);
        String time = (String)cb.getSelectionModel().getSelectedItem();
        SelectTimeRange_Transaction transaction = new SelectTimeRange_Transaction(data, cb, time, index);
        app.processTransaction(transaction);            
    }
    
    public void processAddOrRemoveOH(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<TeachingAssistantPrototype> taTable = (TableView<TeachingAssistantPrototype>)gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
        TeachingAssistantPrototype selectedTA = taTable.getSelectionModel().getSelectedItem();
        //User didn't choose a TA
        if(selectedTA == null)
            return;        
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OH_TABLEVIEW);
        ObservableList<TablePosition> tablePosition = officeHoursTable.getSelectionModel().getSelectedCells();
        TablePosition selectedCell = null;
        if(tablePosition.size() > 0)
            selectedCell = tablePosition.get(0);
        else
            return;
        //User didn't choose a day
        if(!data.isDayOfWeekColumn(selectedCell.getColumn()))
            return;
        TimeSlot selectedTimeSlot = officeHoursTable.getSelectionModel().getSelectedItem();
        DayOfWeek day = data.getColumnDayOfWeek(selectedCell.getColumn());
        if(selectedTimeSlot.containsTA(selectedTA, day)){
            RemoveOH_Transaction removeCSGTransaction = new RemoveOH_Transaction(officeHoursTable, selectedTimeSlot, selectedTA, day);
            app.processTransaction(removeCSGTransaction);
        }
        else{
            AddOH_Transaction addCSGTransaction = new AddOH_Transaction(officeHoursTable, selectedTimeSlot, selectedTA, day);
            app.processTransaction(addCSGTransaction);
        }
    }
    
    public void processSetGraduateTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        ohData.setGraduateTA();            
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OH_TABLEVIEW);
        officeHoursTable.refresh();
    }
    
    public void processSetUndergraduateTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        ohData.setUndergraduateTA();          
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OH_TABLEVIEW);
        officeHoursTable.refresh();

    }
        
    public void processSetAllTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        ohData.setAllTA();               
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OH_TABLEVIEW);
        officeHoursTable.refresh();
    }

    public void processAddLecture(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AddLecture_Transaction transaction = new AddLecture_Transaction(data);
        app.processTransaction(transaction);
    }

    public void processAddLab(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AddLab_Transaction transaction = new AddLab_Transaction(data);
        app.processTransaction(transaction);
    }
    
    public void processAddRecitation(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AddRecitation_Transaction transaction = new AddRecitation_Transaction(data);
        app.processTransaction(transaction);
    }    

    public void processEditLecture(LectureItem lecture, String newData, String oldData, int index){
        AppGUIModule gui = app.getGUIModule();
        TableView lectureTableView = (TableView) gui.getGUINode(CSG_LECTURES_TABLEVIEW);
        EditLecture_Transaction transaction = new EditLecture_Transaction(lectureTableView, lecture, newData, oldData, index);
        app.processTransaction(transaction);
    }

    public void processEditRecitation(RecitationItem recitation, String newData, String oldData, int index){
        AppGUIModule gui = app.getGUIModule();
        TableView recitationTableView = (TableView) gui.getGUINode(CSG_REC_TABLEVIEW);
        EditRecitation_Transaction transaction = new EditRecitation_Transaction(recitationTableView, recitation, newData, oldData, index);
        app.processTransaction(transaction);
    }
    
    public void processEditLab(LabItem lab, String newData, String oldData, int index){
        AppGUIModule gui = app.getGUIModule();
        TableView labTableView = (TableView) gui.getGUINode(CSG_LABS_TABLEVIEW);
        EditLab_Transaction transaction = new EditLab_Transaction(labTableView, lab, newData, oldData, index);
        app.processTransaction(transaction);
    }
    
    public void processRemoveLecture(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<LectureItem> lecturesTable = (TableView<LectureItem>)gui.getGUINode(CSG_LECTURES_TABLEVIEW);
        LectureItem lecture = lecturesTable.getSelectionModel().getSelectedItem();
        if(lecture != null){
            RemoveLecture_Transaction transaction = new RemoveLecture_Transaction(data, lecture);
            app.processTransaction(transaction);
        }        
    }
    
    public void processRemoveRecitation(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<RecitationItem> lecturesTable = (TableView<RecitationItem>)gui.getGUINode(CSG_REC_TABLEVIEW);
        RecitationItem recitation = lecturesTable.getSelectionModel().getSelectedItem();
        if(recitation != null){
            RemoveRecitation_Transaction transaction = new RemoveRecitation_Transaction(data, recitation);
             app.processTransaction(transaction);           
        }        
    }

    public void processRemoveLab(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<LabItem> lecturesTable = (TableView<LabItem>)gui.getGUINode(CSG_LABS_TABLEVIEW);
        LabItem lab = lecturesTable.getSelectionModel().getSelectedItem();
        if(lab != null){
            RemoveLab_Transaction transaction = new RemoveLab_Transaction(data, lab);
             app.processTransaction(transaction);           
        }        
    }
    
    public void processEditStartEndDate(Object nodeId, int index){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();        
        DatePicker dp = (DatePicker)gui.getGUINode(nodeId);
        LocalDate oldDate = index == 0 ? data.getStartingMonday() : data.getEndingFriday();
        EditStartEndDate_Transaction transaction = new EditStartEndDate_Transaction(data, dp, oldDate, dp.getValue(), index);
         app.processTransaction(transaction);              
    }
    
    public void processAddSchedule(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();        
        String type = (String)((ComboBox) gui.getGUINode(CSG_SCHEDULE_TYPE_CB)).getValue();
        LocalDate date = ((DatePicker) gui.getGUINode(CSG_SCHEDULE_DATE_DP)).getValue();
        String title = ((TextField) gui.getGUINode(CSG_SCHEDULE_TITLE_TF)).getText();
        String topic = ((TextField) gui.getGUINode(CSG_SCHEDULE_TOPIC_TF)).getText();
        String link = ((TextField) gui.getGUINode(CSG_SCHEDULE_LINK_TF)).getText();
        ScheduleItem schedule = new ScheduleItem(type, date.toString(), title, topic, link);
        AddSchedule_Transaction transaction = new AddSchedule_Transaction(data, schedule);
         app.processTransaction(transaction);            
    }
    
    public void processClearEntryFieldData(){
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ((TableView) gui.getGUINode(CSG_SCHEDULE_TABLEVIEW)).getSelectionModel().clearSelection();
        ((ComboBox) gui.getGUINode(CSG_SCHEDULE_TYPE_CB)).selectionModelProperty().setValue(null);
        ((DatePicker) gui.getGUINode(CSG_SCHEDULE_DATE_DP)).setValue(null);
        ((TextField) gui.getGUINode(CSG_SCHEDULE_TITLE_TF)).setText("");
        ((TextField) gui.getGUINode(CSG_SCHEDULE_TOPIC_TF)).setText("");
        ((TextField) gui.getGUINode(CSG_SCHEDULE_LINK_TF)).setText("");
        ((Button) gui.getGUINode(CSG_SCHEDULE_ADD_UPDATE_BT)).setText(props.getProperty(CSG_SCHEDULE_ADD_BT + "_TEXT"));
    }
}
