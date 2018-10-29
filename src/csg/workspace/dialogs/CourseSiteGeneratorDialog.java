/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.dialogs;

import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_GRA;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_UNDERGRA;
import csg.transactions.EditTA_Transaction;
import static djf.AppPropertyType.EDIT_DIALOG_EMAIL;
import static djf.AppPropertyType.EDIT_DIALOG_EMAIL_PROMPT;
import static djf.AppPropertyType.EDIT_DIALOG_GRADUATE;
import static djf.AppPropertyType.EDIT_DIALOG_NAME;
import static djf.AppPropertyType.EDIT_DIALOG_NAME_PROMPT;
import static djf.AppPropertyType.EDIT_DIALOG_TITLE;
import static djf.AppPropertyType.EDIT_DIALOG_TYPE;
import static djf.AppPropertyType.EDIT_DIALOG_UNDERGRADUATE;
import djf.AppTemplate;
import djf.ui.dialogs.AppDialogsFacade;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import properties_manager.PropertiesManager;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorDialog extends AppDialogsFacade{
    public static void showEditDialog(AppTemplate app, TeachingAssistantPrototype TA){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Dialog<ButtonType> dialog = new Dialog();
        dialog.getDialogPane().setStyle("-fx-background-color : #DDE9E8;");
        dialog.setTitle("");
        ButtonType OKButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OKButton, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setStyle("-fx-padding:15;  -fx-font-weight: bold; -fx-font-size: 17px");
        Label titleLbl = new Label(props.getProperty(EDIT_DIALOG_TITLE));
        titleLbl.setStyle("-fx-font-size: 25px");
        TextField nameTF = new TextField();
        nameTF.setPrefColumnCount(20);
        nameTF.setPromptText(props.getProperty(EDIT_DIALOG_NAME_PROMPT));
        TextField emailTF = new TextField(); 
        emailTF.setPrefColumnCount(20);
        emailTF.setPromptText(props.getProperty(EDIT_DIALOG_EMAIL_PROMPT));
        Label nameLbl = new Label(props.getProperty(EDIT_DIALOG_NAME));
        Label emailLbl = new Label(props.getProperty(EDIT_DIALOG_EMAIL));
        Label typeLbl = new Label(props.getProperty(EDIT_DIALOG_TYPE));
        ToggleGroup typeTG = new ToggleGroup();
        RadioButton gradRB = new RadioButton(props.getProperty(EDIT_DIALOG_GRADUATE));
        RadioButton undergradRB = new RadioButton(props.getProperty(EDIT_DIALOG_UNDERGRADUATE));
        gradRB.setToggleGroup(typeTG);
        undergradRB.setToggleGroup(typeTG);
        grid.add(titleLbl, 0, 0, 2, 1);
        grid.add(nameLbl, 0, 2);
        grid.add(emailLbl, 0, 4);
        grid.add(nameTF, 1, 2, 2, 1);
        grid.add(emailTF, 1, 4, 2, 1);
        grid.add(typeLbl, 0, 6);
        grid.add(gradRB, 1, 6);
        grid.add(undergradRB, 2, 6);
        nameTF.setText(TA.getName());
        emailTF.setText(TA.getEmail());
        String taType = TA.getType();
        if(taType.equals(TA_TYPE_UNDERGRA))
            undergradRB.setSelected(true);
        else
            gradRB.setSelected(true);
        
        
        ChangeListener listener = new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String name = nameTF.getText();
                String email = emailTF.getText();
                Node addBt = dialog.getDialogPane().lookupButton(OKButton);
                final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
                boolean nameEmpty = nameTF.getText().isEmpty();
                boolean emailEmpty = emailTF.getText().isEmpty();
                if(nameEmpty || name.equals(TA.getName())){
                    nameTF.setStyle(null);
                }
                if(emailEmpty || email.equals(TA.getEmail())){
                    emailTF.setStyle(null);
                }
                if(email.equals(TA.getEmail()) && name.equals(TA.getName())){
                    addBt.setDisable(false);
                    return;
                }
                if(emailEmpty && nameEmpty){
                        addBt.setDisable(true);
                    return;
                }
                boolean containsTA  = ((CourseSiteGeneratorData)app.getDataComponent()).containsTA(name) && !name.equals(TA.getName());
                boolean containsEmail = ((CourseSiteGeneratorData) app.getDataComponent()).containsEmail(email) && !email.equals(TA.getEmail());
                boolean matcherFind = matcher.find();
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

                if(!nameEmpty && !emailEmpty && !containsTA && matcherFind && !containsEmail){
                         addBt.setDisable(false);
                }
                else
                         addBt.setDisable(true);
            }
            
        };
        
        nameTF.textProperty().addListener(listener);
        emailTF.textProperty().addListener(listener);
        dialog.getDialogPane().setContent(grid);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.get() == OKButton){
            String newType = undergradRB.isSelected()? TA_TYPE_UNDERGRA : TA_TYPE_GRA;
            EditTA_Transaction editTransaction = new EditTA_Transaction(app, TA, nameTF.getText(), emailTF.getText(), newType);            
            app.processTransaction(editTransaction);
        }
    }
}
