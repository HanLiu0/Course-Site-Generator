/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorPropertyType;
import static csg.CourseSiteGeneratorPropertyType.CSG_ADD_TA_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_ADD_TA_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_EMAIL_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_END_TIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_FOOLPROOF_SETTINGS;
import static csg.CourseSiteGeneratorPropertyType.CSG_FRIDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LEFT_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_MONDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_NAME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_HEADER_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_RIGHT_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SLOTS_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_START_TIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAS_ALL_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAS_GRADUATE_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAS_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAS_UNDERGRADUATE_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_THURSDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_TUESDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_TYPE_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_WEDNESDAY_TABLE_COLUMN;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.workspace.controllers.CourseSiteGeneratorController;
import csg.workspace.foolproof.CourseSiteGeneratorFoolproofDesign;
import static csg.workspace.style.CSGStyle.CLASS_CSG_BOX;
import static csg.workspace.style.CSGStyle.CLASS_CSG_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_CSG_CENTER_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_DAY_OF_WEEK_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_HEADER_LABEL;
import static csg.workspace.style.CSGStyle.CLASS_CSG_OFFICE_HOURS_TABLE_VIEW;
import static csg.workspace.style.CSGStyle.CLASS_CSG_PANE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_RB;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TABLE_VIEW;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TEXT_FIELD;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TIME_COLUMN;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author hanli
 */
public class CourseSiteGeneratorWorkspace extends AppWorkspaceComponent{
        public CourseSiteGeneratorWorkspace(CourseSiteGeneratorApp app) {
            super(app);

            // LAYOUT THE APP
            initLayout();

            // INIT THE EVENT HANDLERS
            initControllers();

            // SETUP FOOLPROOF DESIGN FOR THIS APP
            initFoolproofDesign();
        }


        // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
        private void initLayout() {
            // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
            PropertiesManager props = PropertiesManager.getPropertiesManager();

            // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
            AppNodesBuilder ohBuilder = app.getGUIModule().getNodesBuilder();

            // INIT THE HEADER ON THE LEFT
            VBox leftPane = ohBuilder.buildVBox(CSG_LEFT_PANE, null, CLASS_CSG_PANE, ENABLED);
            HBox tasHeaderBox = ohBuilder.buildHBox(CSG_TAS_HEADER_PANE, leftPane, CLASS_CSG_BOX, ENABLED);
            ohBuilder.buildLabel(CourseSiteGeneratorPropertyType.CSG_TAS_HEADER_LABEL, tasHeaderBox, CLASS_CSG_HEADER_LABEL, ENABLED);
            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);
            tasHeaderBox.getChildren().add(region1);
            ToggleGroup tgTA = new ToggleGroup();
            ohBuilder.buildRB(CourseSiteGeneratorPropertyType.CSG_TAS_ALL_RB, tasHeaderBox, tgTA,CLASS_CSG_RB, ENABLED);
            ohBuilder.buildRB(CourseSiteGeneratorPropertyType.CSG_TAS_GRADUATE_RB, tasHeaderBox, tgTA, CLASS_CSG_RB, ENABLED);
            ohBuilder.buildRB(CourseSiteGeneratorPropertyType.CSG_TAS_UNDERGRADUATE_RB, tasHeaderBox, tgTA, CLASS_CSG_RB, ENABLED);
            Region region2 = new Region();
            HBox.setHgrow(region2, Priority.ALWAYS);
            tasHeaderBox.getChildren().add(region2);

            // MAKE THE TABLE AND SETUP THE DATA MODEL
            TableView<TeachingAssistantPrototype> taTable = ohBuilder.buildTableView(CSG_TAS_TABLE_VIEW, leftPane, CLASS_CSG_TABLE_VIEW, ENABLED);
            taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            TableColumn nameColumn = ohBuilder.buildTableColumn(CSG_NAME_TABLE_COLUMN, taTable, CLASS_CSG_COLUMN);
            nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
            nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/5.0));
            nameColumn.setSortable(false);
            TableColumn emailColumn = ohBuilder.buildTableColumn(CSG_EMAIL_TABLE_COLUMN, taTable, CLASS_CSG_COLUMN);
            emailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
            emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(2.0/5.0));
            emailColumn.setSortable(false);
            TableColumn timeslotsColumn = ohBuilder.buildTableColumn(CSG_SLOTS_TABLE_COLUMN, taTable, CLASS_CSG_CENTER_COLUMN);
            timeslotsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("timeslots"));
            timeslotsColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/5.0));        
            timeslotsColumn.setSortable(false);
            TableColumn typeColumn = ohBuilder.buildTableColumn(CSG_TYPE_TABLE_COLUMN, taTable, CLASS_CSG_COLUMN);
            typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
            typeColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/5.0));
            typeColumn.setSortable(false);

            // ADD BOX FOR ADDING A TA
            HBox taBox = ohBuilder.buildHBox(CSG_ADD_TA_PANE, leftPane, CLASS_CSG_PANE, ENABLED);
            ohBuilder.buildTextField(CSG_NAME_TEXT_FIELD, taBox, CLASS_CSG_TEXT_FIELD, ENABLED);
            ohBuilder.buildTextField(CSG_EMAIL_TEXT_FIELD, taBox, CLASS_CSG_TEXT_FIELD , ENABLED);
            ohBuilder.buildTextButton(CSG_ADD_TA_BUTTON, taBox, CLASS_CSG_BUTTON, ENABLED);

            // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
            VBox.setVgrow(taTable, Priority.ALWAYS);

            // INIT THE HEADER ON THE RIGHT
            VBox rightPane = ohBuilder.buildVBox(CSG_RIGHT_PANE, null, CLASS_CSG_PANE, ENABLED);
            HBox officeHoursHeaderBox = ohBuilder.buildHBox(CSG_OFFICE_HOURS_HEADER_PANE, rightPane, CLASS_CSG_PANE, ENABLED);
            ohBuilder.buildLabel(CSG_OFFICE_HOURS_HEADER_LABEL, officeHoursHeaderBox, CLASS_CSG_HEADER_LABEL, ENABLED);

            // SETUP THE OFFICE HOURS TABLE
            TableView<TimeSlot> officeHoursTable = ohBuilder.buildTableView(CSG_OFFICE_HOURS_TABLE_VIEW, rightPane, CLASS_CSG_OFFICE_HOURS_TABLE_VIEW, ENABLED);
            TableColumn startTimeColumn = ohBuilder.buildTableColumn(CSG_START_TIME_TABLE_COLUMN, officeHoursTable, CLASS_CSG_TIME_COLUMN);
            TableColumn endTimeColumn = ohBuilder.buildTableColumn(CSG_END_TIME_TABLE_COLUMN, officeHoursTable, CLASS_CSG_TIME_COLUMN);
            TableColumn mondayColumn = ohBuilder.buildTableColumn(CSG_MONDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn tuesdayColumn = ohBuilder.buildTableColumn(CSG_TUESDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn wednesdayColumn = ohBuilder.buildTableColumn(CSG_WEDNESDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn thursdayColumn = ohBuilder.buildTableColumn(CSG_THURSDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn fridayColumn = ohBuilder.buildTableColumn(CSG_FRIDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            startTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("startTime"));
            endTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("endTime"));
            mondayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("monday"));
            tuesdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("tuesday"));
            wednesdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("wednesday"));
            thursdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("thursday"));
            fridayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("friday"));
            for (int i = 0; i < officeHoursTable.getColumns().size(); i++) {
                ((TableColumn)officeHoursTable.getColumns().get(i)).prefWidthProperty().bind(officeHoursTable.widthProperty().multiply(1.0/7.0));
                if(i <= 1)
                    continue;
                ((TableColumn)officeHoursTable.getColumns().get(i)).setCellFactory(e -> new TableCell<String, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        TeachingAssistantPrototype ta = ((CourseSiteGeneratorData)app.getDataComponent()).getSelectedTA();
                        setText(item);
                        if (item == null || empty || ta == null) {
                            this.setStyle(null);
                            return;
                        } 
                        if(!((CourseSiteGeneratorData)app.getDataComponent()).isTASelected())
                            return;
                        boolean containsTA = false;
                        String[] names = item.split("\n");
                        for(String taName: names){
                            containsTA = ta.getName().equals(taName);
                            if(containsTA)
                                break;
                        }
                        if(containsTA)
                            this.setStyle("-fx-background-color: #FFB6C1");
                        else
                            this.setStyle(null);
                   }

                });
            }


            // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
            VBox.setVgrow(officeHoursTable, Priority.ALWAYS);

            // BOTH PANES WILL NOW GO IN A SPLIT PANE
            SplitPane sPane = new SplitPane(leftPane, rightPane);
            sPane.setDividerPositions(.4);
            workspace = new BorderPane();

            // AND PUT EVERYTHING IN THE WORKSPACE
            ((BorderPane)workspace).setCenter(sPane);
        }

        private void initControllers() {
            CourseSiteGeneratorController controller = new CourseSiteGeneratorController((CourseSiteGeneratorApp) app);
            AppGUIModule gui = app.getGUIModule();        
            TableView taTableView = (TableView) gui.getGUINode(CSG_TAS_TABLE_VIEW);
            TableView officeHoursTableView = (TableView) gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
            ((TextField) gui.getGUINode(CSG_NAME_TEXT_FIELD)).setOnAction(e -> {
                if(!((Button) gui.getGUINode(CSG_ADD_TA_BUTTON)).isDisabled())
                    controller.processAddTA();
            });
            ((TextField) gui.getGUINode(CSG_EMAIL_TEXT_FIELD)).setOnAction(e -> {
                if(!((Button) gui.getGUINode(CSG_ADD_TA_BUTTON)).isDisabled())
                    controller.processAddTA();
            });
            ((Button) gui.getGUINode(CSG_ADD_TA_BUTTON)).setOnAction(e -> {
                controller.processAddTA();
            });
            ((TextField) gui.getGUINode(CSG_NAME_TEXT_FIELD)).textProperty().addListener(e -> {
                app.getFoolproofModule().updateAll();
            });
            ((TextField) gui.getGUINode(CSG_EMAIL_TEXT_FIELD)).textProperty().addListener(e -> {
                app.getFoolproofModule().updateAll();
            });
            ((RadioButton) gui.getGUINode(CSG_TAS_ALL_RB)).setOnAction(e -> {
                taTableView.getSelectionModel().clearSelection();
                app.getFoolproofModule().updateAll();
                controller.setAllTA();
            });
            ((RadioButton) gui.getGUINode(CSG_TAS_ALL_RB)).setSelected(true);
            ((RadioButton) gui.getGUINode(CSG_TAS_GRADUATE_RB)).setOnAction(e -> {
                taTableView.getSelectionModel().clearSelection();
                app.getFoolproofModule().updateAll();
                controller.setGraduateTA();
            });
            ((RadioButton) gui.getGUINode(CSG_TAS_UNDERGRADUATE_RB)).setOnAction(e -> {
                taTableView.getSelectionModel().clearSelection();
               app.getFoolproofModule().updateAll();
               controller.setUndergraduateTA();
            });        
            taTableView.setOnMouseClicked(e->{
               app.getFoolproofModule().updateAll();
               officeHoursTableView.refresh();
                if(e.getButton().equals(MouseButton.PRIMARY)){
                    if(e.getClickCount() == 2 && ((CourseSiteGeneratorData)app.getDataComponent()).isTASelected()){
                        controller.showEditDialog();
                    }
                }
            });
            // DON'T LET ANYONE SORT THE TABLES
            for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
                ((TableColumn)officeHoursTableView.getColumns().get(i)).setSortable(false);
            }
            officeHoursTableView.setOnMouseClicked(e -> {
                controller.processAddOrRemoveCSG();
            });
        }

        private void initFoolproofDesign() {
            AppGUIModule gui = app.getGUIModule();
            AppFoolproofModule foolproofSettings = app.getFoolproofModule();
            foolproofSettings.registerModeSettings(CSG_FOOLPROOF_SETTINGS,
                    new CourseSiteGeneratorFoolproofDesign((CourseSiteGeneratorApp) app));
        }

        @Override
        public void showNewDialog() {
            // WE AREN'T USING THIS FOR THIS APPLICATION
        }
}
