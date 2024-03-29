/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.foolproof;

import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_ADD_TA_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_ALL_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HOME_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HWS_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SCHEDULE_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SYLLABUS_CB;
import csg.data.CourseSiteGeneratorData;
import static djf.AppPropertyType.EXPORT_BUTTON;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorFoolproofDesign implements FoolproofDesign {
    CourseSiteGeneratorApp app;
    
    public CourseSiteGeneratorFoolproofDesign(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox homeCB = ((CheckBox)gui.getGUINode(CSG_SITE_HOME_CB));
        CheckBox syllabusCB = ((CheckBox)gui.getGUINode(CSG_SITE_SYLLABUS_CB));
        CheckBox scheduleCB = ((CheckBox)gui.getGUINode(CSG_SITE_SCHEDULE_CB));
        CheckBox hwsCB = ((CheckBox)gui.getGUINode(CSG_SITE_HWS_CB));
        int numOfPages = 0;
        CheckBox selectedCheckBox = null;
        if(homeCB.isSelected()){
            numOfPages++;
            selectedCheckBox = homeCB;
        }
        if(syllabusCB.isSelected()){
            numOfPages++;
            selectedCheckBox = syllabusCB;
        }
        if(scheduleCB.isSelected()){
            numOfPages++;
            selectedCheckBox = scheduleCB;
        }
        if(hwsCB.isSelected()){
            numOfPages++;
            selectedCheckBox = hwsCB;
        }
        if(numOfPages == 1){
            selectedCheckBox.setDisable(true);
        }
        else{
            homeCB.setDisable(false);
            syllabusCB.setDisable(false);
            scheduleCB.setDisable(false);
            hwsCB.setDisable(false);
        }
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                if(app.getFileModule().getWorkFile() == null)
                    ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(true);
                else
                    ((Button) gui.getGUINode(EXPORT_BUTTON)).setDisable(false);            
            }
        });
        TextField nameTF = (TextField) gui.getGUINode(CSG_OH_TAS_NAME_TEXT_FIELD);
        TextField emailTF = (TextField) gui.getGUINode(CSG_OH_TAS_EMAIL_TEXT_FIELD);            
        Button addBt = (Button) gui.getGUINode(CSG_OH_TAS_ADD_TA_BUTTON);        
        RadioButton allRB = (RadioButton) gui.getGUINode(CSG_OH_TAS_ALL_RB);
        String name = nameTF.getText();
        String email = emailTF.getText();
        final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        boolean nameEmpty = nameTF.getText().isEmpty();
        boolean emailEmpty = emailTF.getText().isEmpty();
        if(nameEmpty){
            nameTF.setStyle(null);
        }
        if(emailEmpty){
            emailTF.setStyle(null);
        }
        if(emailEmpty && nameEmpty){
            addBt.setDisable(true);
            return;
        }
        boolean containsTA  = ((CourseSiteGeneratorData)app.getDataComponent()).containsTA(name);
        boolean containsEmail = ((CourseSiteGeneratorData) app.getDataComponent()).containsEmail(email);
        boolean matcherFind = matcher.find();
        boolean allRBSelected = allRB.isSelected();
        if(containsTA){
            nameTF.setStyle("-fx-text-fill: red ;");
        }
        else
            nameTF.setStyle(null);
        
        if(!emailEmpty && (containsEmail || !matcherFind)){
            emailTF.setStyle("-fx-text-fill: red ;");     
        }
        else
            emailTF.setStyle(null);
        
        if(!nameEmpty && !emailEmpty && !allRBSelected && !containsTA && matcherFind && !containsEmail){
                 addBt.setDisable(false);
        }
        else
                 addBt.setDisable(true);
        }
}
