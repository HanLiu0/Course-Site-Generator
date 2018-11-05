/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.controllers;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_DIR_LABEL;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.transactions.AddOH_Transaction;
import csg.transactions.AddTA_Transaction;
import csg.transactions.EditBanner_Transaction;
import csg.transactions.EditInstructor_Transaction;
import csg.transactions.EditPages_Transaction;
import csg.transactions.EditStyleImages_Transaction;
import csg.transactions.EditStyleSheet_Transaction;
import csg.transactions.RemoveOH_Transaction;
import csg.transactions.RemoveTA_Transaction;
import csg.transactions.SelectTimeRange_Transaction;
import csg.workspace.dialogs.CourseSiteGeneratorDialog;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import jtps.jTPS;
import jtps.jTPS_Transaction;

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
        TeachingAssistantPrototype selectedTA = taTable.getSelectionModel().getSelectedItem();
        if(selectedTA == null)
            return;
        RemoveTA_Transaction removeTATransaction = new RemoveTA_Transaction(data, selectedTA);
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

}
