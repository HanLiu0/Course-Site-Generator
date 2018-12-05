/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace.dialogs;

import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_HWS_PAGE;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_INDEX_PAGE;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_SCHEDULE_PAGE;
import static csg.CourseSiteGeneratorPropertyType.APP_EXPORT_SYLLABUS_PAGE;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_EMAIL;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_EMAIL_PROMPT;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_GRADUATE;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_NAME;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_NAME_PROMPT;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_TITLE;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_TYPE;
import static csg.CourseSiteGeneratorPropertyType.CSG_EDIT_DIALOG_UNDERGRADUATE;
import static csg.CourseSiteGeneratorPropertyType.IMAGE_CHOOSER_TITLE;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_GRA;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_UNDERGRA;
import csg.transactions.EditTA_Transaction;
import static djf.AppPropertyType.APP_EXPORT_PAGE;
import djf.AppTemplate;
import djf.ui.dialogs.AppDialogsFacade;
import djf.ui.dialogs.AppWebDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorDialog extends AppDialogsFacade{
    
    public static String showChooseImageDialog(Stage window){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(props.getProperty(IMAGE_CHOOSER_TITLE));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
            );
        File selectedFile = fileChooser.showOpenDialog(window);
        if(selectedFile == null)
            return null;
        else
            return selectedFile.getAbsolutePath();
    }
    public static void showEditDialog(AppTemplate app, TeachingAssistantPrototype TA){
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Dialog<ButtonType> dialog = new Dialog();
        dialog.getDialogPane().setStyle("-fx-background-color : #fff5ee;");
        dialog.setTitle("");
        ButtonType OKButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OKButton, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(10);
        grid.setStyle("-fx-padding:15;  -fx-font-weight: bold; -fx-font-size: 17px");
        Label titleLbl = new Label(props.getProperty(CSG_EDIT_DIALOG_TITLE));
        titleLbl.setStyle("-fx-font-size: 25px");
        TextField nameTF = new TextField();
        nameTF.setPrefColumnCount(20);
        nameTF.setPromptText(props.getProperty(CSG_EDIT_DIALOG_NAME_PROMPT));
        TextField emailTF = new TextField(); 
        emailTF.setPrefColumnCount(20);
        emailTF.setPromptText(props.getProperty(CSG_EDIT_DIALOG_EMAIL_PROMPT));
        Label nameLbl = new Label(props.getProperty(CSG_EDIT_DIALOG_NAME));
        Label emailLbl = new Label(props.getProperty(CSG_EDIT_DIALOG_EMAIL));
        Label typeLbl = new Label(props.getProperty(CSG_EDIT_DIALOG_TYPE));
        ToggleGroup typeTG = new ToggleGroup();
        RadioButton gradRB = new RadioButton(props.getProperty(CSG_EDIT_DIALOG_GRADUATE));
        RadioButton undergradRB = new RadioButton(props.getProperty(CSG_EDIT_DIALOG_UNDERGRADUATE));
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
    
    public static void showExportDialog (AppTemplate app, CourseSiteGeneratorData dataManager) throws IOException {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String filePath = dataManager.getExportDir();
        if(dataManager.getPages(0))
            filePath += props.getProperty(APP_EXPORT_INDEX_PAGE);
        else if(dataManager.getPages(1))
            filePath += props.getProperty(APP_EXPORT_SYLLABUS_PAGE);
        else if(dataManager.getPages(2))
            filePath += props.getProperty(APP_EXPORT_SCHEDULE_PAGE);
        else if(dataManager.getPages(3))
            filePath += props.getProperty(APP_EXPORT_HWS_PAGE);

        WebView webView = new WebView();
        Stage webViewStage = new Stage();
        Scene scene = new Scene(webView);
        webViewStage.setScene(scene);
        // MAKE IT MODAL
        webViewStage.initOwner(app.getGUIModule().getWindow());
        webViewStage.initModality(Modality.APPLICATION_MODAL);        
        WebEngine webEngine = webView.getEngine();
        webEngine.documentProperty().addListener(e->{
            // THE PAGE WILL LOAD ASYNCHRONOUSLY, SO MAKE
            // SURE TO GRAB THE TITLE FOR THE WINDOW
            // ONCE IT'S BEEN LOADED
            String title = webEngine.getTitle();
            webViewStage.setTitle(title);
        });
        URL pageURL = new File(filePath).toURI().toURL();
        String pagePath = pageURL.toExternalForm();
        webView.getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            boolean first = true;
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                if(t1.equals(State.SUCCEEDED) && first) {
                    first = false;
                    webView.getEngine().reload();
                }
            }
        });        webEngine.load(pagePath);
        webViewStage.showAndWait();        

    }
}
