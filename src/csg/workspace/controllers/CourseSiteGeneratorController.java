/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.controllers;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAS_TABLE_VIEW;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.transactions.AddOH_Transaction;
import csg.transactions.AddTA_Transaction;
import csg.transactions.RemoveOH_Transaction;
import csg.workspace.dialogs.CourseSiteGeneratorDialog;
import djf.modules.AppGUIModule;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorController {
    
    CourseSiteGeneratorApp app;

    public CourseSiteGeneratorController(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    public void processAddTA() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(CSG_NAME_TEXT_FIELD);
        String name = nameTF.getText();
        TextField emailTF = (TextField) gui.getGUINode(CSG_EMAIL_TEXT_FIELD);
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
    
    public void processAddOrRemoveCSG(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<TeachingAssistantPrototype> taTable = (TableView<TeachingAssistantPrototype>)gui.getGUINode(CSG_TAS_TABLE_VIEW);
        TeachingAssistantPrototype selectedTA = taTable.getSelectionModel().getSelectedItem();
        //User didn't choose a TA
        if(selectedTA == null)
            return;        
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        TablePosition selectedCell = officeHoursTable.getSelectionModel().getSelectedCells().get(0);
        //User didn't choose a day
        if(!ohData.isDayOfWeekColumn(selectedCell.getColumn()))
            return;
        TimeSlot selectedTimeSlot = officeHoursTable.getSelectionModel().getSelectedItem();
        DayOfWeek day = ohData.getColumnDayOfWeek(selectedCell.getColumn());
        if(selectedTimeSlot.containsTA(selectedTA, day)){
            RemoveOH_Transaction removeCSGTransaction = new RemoveOH_Transaction(officeHoursTable, selectedTimeSlot, selectedTA, day);
            app.processTransaction(removeCSGTransaction);
        }
        else{
            AddOH_Transaction addCSGTransaction = new AddOH_Transaction(officeHoursTable, selectedTimeSlot, selectedTA, day);
            app.processTransaction(addCSGTransaction);
        }
    }
    
    public void setGraduateTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        ohData.setGraduateTA();            
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTable.refresh();
    }
    
    public void setUndergraduateTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        ohData.setUndergraduateTA();          
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTable.refresh();

    }
        
    public void setAllTA(){
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        ohData.setAllTA();               
        TableView<TimeSlot> officeHoursTable = (TableView<TimeSlot>)gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTable.refresh();
    }
    
    public void showEditDialog(){        
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData ohData = (CourseSiteGeneratorData)app.getDataComponent();
        TableView<TeachingAssistantPrototype> taTable = (TableView<TeachingAssistantPrototype>)gui.getGUINode(CSG_TAS_TABLE_VIEW);
        TeachingAssistantPrototype selectedTA = taTable.getSelectionModel().getSelectedItem();
        CourseSiteGeneratorDialog.showEditDialog(app, selectedTA);
    }
}
