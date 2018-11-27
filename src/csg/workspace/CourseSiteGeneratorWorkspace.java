/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorPropertyType;
import static csg.CourseSiteGeneratorPropertyType.CSG_END_TIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_FOOLPROOF_SETTINGS;
import static csg.CourseSiteGeneratorPropertyType.CSG_FRIDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_ADD_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_DAYSTIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_REMOVE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_ROOM_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_SECTION_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_TA1_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_TA2_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LABS_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_ADD_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_DAYS_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_REMOVE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_ROOM_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_SECTION_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_LECTURES_TIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIME_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIME_TAB;
import static csg.CourseSiteGeneratorPropertyType.CSG_MONDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_NEW_EDIT_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_END_TIME_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_END_TIME_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_START_TIME_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_START_TIME_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_ADD_TA_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_ADD_TA_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_ALL_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_EMAIL_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_GRAD_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_NAME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_REMOVE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_SLOTS_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_TYPE_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_OH_TAS_UNDERGRA_RB;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_ADD_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_DAYSTIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_REMOVE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_ROOM_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_SECTION_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_TA1_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_TA2_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_REC_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ADD_EDIT_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ADD_UPDATE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_BOUNDARIES_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_BOUNDARIES_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_CLEAR_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_DP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ENDING_FRIDAY_DP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ENDING_FRIDAY_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ITEMS_HEADER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ITEMS_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ITEM_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_LINK_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_LINK_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_REMOVE_BT;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_STARTING_MONDAY_DP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_STARTING_MONDAY_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TAB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TABLEVIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TITLE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TITLE_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TITLE_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TOPIC_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TOPIC_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TOPIC_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_BANNER_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_BANNER_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_DIR_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_EMAIL_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_EMAIL_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_EXPORT_DIR_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_FAVICON_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_FAVICON_IMAGE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HOMEPAGE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HOMEPAGE_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HOME_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HWS_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_INSTRUCTORINFO_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_INSTRUCTOR_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_INSTRUCTOR_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_LEFT_FOOTER_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_LEFT_FOOTER_IMAGE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NAME_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NAME_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NAVBAR_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NAVBAR_IMAGE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NUMBER_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NUMBER_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_OH_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_OH_TITLEDPANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PAGES_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PAGES_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_RIGHT_FOOTER_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_RIGHT_FOOTER_IMAGE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_ROOM_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_ROOM_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SCHEDULE_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SEMESTER_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SEMESTER_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLESHEET_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLESHEET_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLESHEET_NOTE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SUBJECT_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SUBJECT_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SYLLABUS_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_TAB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_TITLE_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_TITLE_TF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_YEAR_CB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_YEAR_LABEL;
import static csg.CourseSiteGeneratorPropertyType.CSG_START_TIME_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_ACADEMIC_DISHONESTY_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_ACADEMIC_DISHONESTY_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_DESCRIPTION_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_DESCRIPTION_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_GRADED_COMPONENTS_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_GRADED_COMPONENTS_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_GRADING_NOTE_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_GRADING_NOTE_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_OUTCOMES_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_OUTCOMES_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_PREREQUISITES_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_PREREQUISITES_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_SPECIAL_ASSISTANCE_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_SPECIAL_ASSISTANCE_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TAB;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TEXTBOOKS_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TEXTBOOKS_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TEXT_AREA_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TOPICS_TA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TOPICS_TP;
import static csg.CourseSiteGeneratorPropertyType.CSG_TAB_PANE;
import static csg.CourseSiteGeneratorPropertyType.CSG_THURSDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_TUESDAY_TABLE_COLUMN;
import static csg.CourseSiteGeneratorPropertyType.CSG_WEDNESDAY_TABLE_COLUMN;
import csg.data.CourseSiteGeneratorData;
import csg.data.Instructor;
import csg.data.LabItem;
import csg.data.LectureItem;
import csg.data.RecitationItem;
import csg.data.ScheduleItem;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.workspace.controllers.CourseSiteGeneratorController;
import csg.workspace.foolproof.CourseSiteGeneratorFoolproofDesign;
import static csg.workspace.style.CSGStyle.CLASS_CSG_BOLD_LABEL;
import static csg.workspace.style.CSGStyle.CLASS_CSG_BOX;
import static csg.workspace.style.CSGStyle.CLASS_CSG_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_CSG_CB;
import static csg.workspace.style.CSGStyle.CLASS_CSG_CENTER_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_CHECKBOX;
import static csg.workspace.style.CSGStyle.CLASS_CSG_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_DAY_OF_WEEK_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_DP;
import static csg.workspace.style.CSGStyle.CLASS_CSG_HEADER_LABEL;
import static csg.workspace.style.CSGStyle.CLASS_CSG_IMAGE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_LARGE_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_CSG_OFFICE_HOURS_TABLE_VIEW;
import static csg.workspace.style.CSGStyle.CLASS_CSG_PANE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_RB;
import static csg.workspace.style.CSGStyle.CLASS_CSG_SMALL_BUTTON;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TAB;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TABLE_VIEW;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TABPANE;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TEXT_AREA;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TEXT_FIELD;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TIME_COLUMN;
import static csg.workspace.style.CSGStyle.CLASS_CSG_TITLEDPANE;
import static djf.AppPropertyType.APP_PATH_IMAGES;
import static djf.AppPropertyType.APP_PATH_STYLESHEET;
import static djf.AppPropertyType.IMAGE_PLACEHOLDER_ICON;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppLanguageModule.FILE_PROTOCOL;
import djf.ui.AppNodesBuilder;
import java.io.File;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;

/**
 *
 * @author Han Liu
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
            AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();

            // BOTH PANES WILL NOW GO IN A SPLIT PANE
            TabPane tPane = csgBuilder.buildTabPane(CSG_TAB_PANE);
            workspace = new BorderPane();
            Tab siteTab = csgBuilder.buildTab(CSG_SITE_TAB, tPane, CLASS_CSG_TAB, ENABLED);
            initSiteTab(siteTab);
            Tab syllabusTab = csgBuilder.buildTab(CSG_SYLLABUS_TAB, tPane, CLASS_CSG_TAB, ENABLED);
            initSyllabusTab(syllabusTab);
            Tab meetingTimeTab = csgBuilder.buildTab(CSG_MEETING_TIME_TAB, tPane, CLASS_CSG_TAB, ENABLED);
            initMeetingTimeTab(meetingTimeTab);
            Tab ohTab = csgBuilder.buildTab(CSG_OH_TAB, tPane, CLASS_CSG_TAB, ENABLED);
            initOfficeHoursTab(ohTab);
            Tab scheduleTab = csgBuilder.buildTab(CSG_SCHEDULE_TAB, tPane, CLASS_CSG_TAB, ENABLED);
            initScheduleTab(scheduleTab);
            
            tPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
            tPane.tabMinWidthProperty().bind(tPane.widthProperty().divide(5).subtract(25));
            tPane.tabMaxWidthProperty().bind(tPane.widthProperty().divide(6).subtract(25));

            // AND PUT EVERYTHING IN THE WORKSPACE
            ((BorderPane)workspace).setCenter(tPane);
        }
        
        private void initSiteTab(Tab siteTab){
            PropertiesManager props = PropertiesManager.getPropertiesManager();

            AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();    
            ScrollPane sp = new ScrollPane();
            VBox sitePane = csgBuilder.buildVBox(CSG_SITE_PANE, null, CLASS_CSG_TABPANE, ENABLED);
            sitePane.setSpacing(10);
            VBox.setVgrow(sitePane, Priority.ALWAYS);
            sp.setContent(sitePane);
            sp.setFitToHeight(true);
            sp.setFitToWidth(true);
            // Banner
            GridPane bannerPane = csgBuilder.buildGridPane(CSG_SITE_BANNER_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
            bannerPane.setHgap(10);
            bannerPane.setVgap(10);
            csgBuilder.buildLabel(CSG_SITE_BANNER_LABEL, bannerPane, 0, 0, 1, 1, CLASS_CSG_HEADER_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_SUBJECT_LABEL, bannerPane, 0, 1, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_SEMESTER_LABEL, bannerPane, 0, 2, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_TITLE_LABEL, bannerPane, 0, 3, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_EXPORT_DIR_LABEL, bannerPane, 0, 4, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_NUMBER_LABEL, bannerPane, 4, 1, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_YEAR_LABEL, bannerPane, 4, 2, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            Label dirLabel = csgBuilder.buildLabel(CSG_SITE_DIR_LABEL, bannerPane, 1, 4, 6, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            dirLabel.setText(".\\export\\[subject]_[number]_[semester]_[year]\\public_html");
            csgBuilder.buildComboBox(CSG_SITE_SUBJECT_CB, bannerPane, 1, 1, 2, 1, CLASS_CSG_CB, ENABLED,  true);
            csgBuilder.buildComboBox(CSG_SITE_SEMESTER_CB, bannerPane, 1, 2, 2, 1, CLASS_CSG_CB, ENABLED, true);
            csgBuilder.buildComboBox(CSG_SITE_NUMBER_CB, bannerPane, 5, 1, 3, 1, CLASS_CSG_CB, ENABLED, true);
            csgBuilder.buildComboBox(CSG_SITE_YEAR_CB, bannerPane, 5, 2, 3, 1, CLASS_CSG_CB, ENABLED,  true);
            csgBuilder.buildTextField(CSG_SITE_TITLE_TF, bannerPane, 1, 3, 5, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            
            //Pages
            HBox pagesPane = csgBuilder.buildHBox(CSG_SITE_PAGES_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
            pagesPane.setSpacing(10);
            csgBuilder.buildLabel(CSG_SITE_PAGES_LABEL, pagesPane, CLASS_CSG_HEADER_LABEL, ENABLED);
            csgBuilder.buildCheckBox(CSG_SITE_HOME_CB, pagesPane, CLASS_CSG_CHECKBOX, ENABLED);
            csgBuilder.buildCheckBox(CSG_SITE_SYLLABUS_CB, pagesPane, CLASS_CSG_CHECKBOX, ENABLED);
            csgBuilder.buildCheckBox(CSG_SITE_SCHEDULE_CB, pagesPane, CLASS_CSG_CHECKBOX, ENABLED);
            csgBuilder.buildCheckBox(CSG_SITE_HWS_CB, pagesPane, CLASS_CSG_CHECKBOX, ENABLED);
            
            // Style
            GridPane stylePane = csgBuilder.buildGridPane(CSG_SITE_STYLE_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
            stylePane.setHgap(10);
            stylePane.setVgap(10);            
            csgBuilder.buildLabel(CSG_SITE_STYLE_LABEL, stylePane, 0, 0, 1, 1, CLASS_CSG_HEADER_LABEL, ENABLED);
            Button bt1 = csgBuilder.buildTextButton(CSG_SITE_FAVICON_BUTTON, stylePane, 0, 1, 2, 1, CLASS_CSG_LARGE_BUTTON, ENABLED);
            Button bt2 =  csgBuilder.buildTextButton(CSG_SITE_NAVBAR_BUTTON, stylePane, 0, 2, 2, 1, CLASS_CSG_LARGE_BUTTON, ENABLED);
            Button bt3 = csgBuilder.buildTextButton(CSG_SITE_LEFT_FOOTER_BUTTON, stylePane, 0, 3, 2, 1, CLASS_CSG_LARGE_BUTTON, ENABLED);
            Button bt4 = csgBuilder.buildTextButton(CSG_SITE_RIGHT_FOOTER_BUTTON, stylePane, 0, 4, 2, 1, CLASS_CSG_LARGE_BUTTON, ENABLED);
            bt1.setPrefWidth(200);
            bt2.setPrefWidth(200);
            bt3.setPrefWidth(200);
            bt4.setPrefWidth(200);
            csgBuilder.buildImageView(CSG_SITE_FAVICON_IMAGE, stylePane, 3, 1, 2, 1, CLASS_CSG_IMAGE, ENABLED);
            csgBuilder.buildImageView(CSG_SITE_NAVBAR_IMAGE, stylePane, 3, 2, 2, 1, CLASS_CSG_IMAGE, ENABLED);
            csgBuilder.buildImageView(CSG_SITE_LEFT_FOOTER_IMAGE, stylePane, 3, 3, 2, 1, CLASS_CSG_IMAGE, ENABLED);
            csgBuilder.buildImageView(CSG_SITE_RIGHT_FOOTER_IMAGE, stylePane, 3, 4, 2, 1, CLASS_CSG_IMAGE, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_STYLESHEET_LABEL, stylePane, 0, 5, 2, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_STYLESHEET_NOTE_LABEL, stylePane, 0, 6, 7, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            ComboBox stylesheet = csgBuilder.buildComboBox(CSG_SITE_STYLESHEET_CB, stylePane, 2, 5, 2, 1, CLASS_CSG_CB, ENABLED,  false);
            File f = new File(props.getProperty(APP_PATH_STYLESHEET));
            ObservableList ol = FXCollections.observableArrayList();
            for(String file: f.list()){
                if(file.endsWith(".css"))
                    ol.add(file);
            }
            stylesheet.setItems(ol);
            
            //Instructor
            VBox instuctorPane = csgBuilder.buildVBox(CSG_SITE_INSTRUCTOR_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
            instuctorPane.setSpacing(10);
            GridPane instuctorInfoPane = csgBuilder.buildGridPane(CSG_SITE_INSTRUCTORINFO_PANE, instuctorPane, null, ENABLED);
            instuctorInfoPane.setHgap(10);
            instuctorInfoPane.setVgap(10);
            csgBuilder.buildLabel(CSG_SITE_INSTRUCTOR_LABEL, instuctorInfoPane, 0, 0, 1, 1, CLASS_CSG_HEADER_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_NAME_LABEL, instuctorInfoPane, 0, 1, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_EMAIL_LABEL, instuctorInfoPane, 0, 2, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_ROOM_LABEL, instuctorInfoPane, 5, 1, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SITE_HOMEPAGE_LABEL, instuctorInfoPane, 5, 2, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildTextField(CSG_SITE_NAME_TF, instuctorInfoPane, 1, 1, 4, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextField(CSG_SITE_EMAIL_TF, instuctorInfoPane, 1, 2, 4, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextField(CSG_SITE_ROOM_TF, instuctorInfoPane, 6, 1, 4, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextField(CSG_SITE_HOMEPAGE_TF, instuctorInfoPane, 6, 2, 4, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTitledPaneWithTextArea(CSG_SITE_OH_TITLEDPANE, CSG_SITE_OH_TA, instuctorPane,  CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);
            siteTab.setContent(sp);
        }
        
        private void initSyllabusTab(Tab syllabusTab){
            PropertiesManager props = PropertiesManager.getPropertiesManager();

            // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
            AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();               
            ScrollPane sp = new ScrollPane();

            VBox syllabusPane = csgBuilder.buildVBox(CSG_SYLLABUS_PANE, null, CLASS_CSG_TABPANE, ENABLED);
            VBox.setVgrow(syllabusPane, Priority.ALWAYS);
            sp.setContent(syllabusPane);
            sp.setFitToHeight(true);
            sp.setFitToWidth(true);
            VBox textAreaPane = csgBuilder.buildVBox(CSG_SYLLABUS_TEXT_AREA_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
            textAreaPane.setSpacing(10);
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_DESCRIPTION_TP, CSG_SYLLABUS_DESCRIPTION_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_TOPICS_TP, CSG_SYLLABUS_TOPICS_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);             
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_PREREQUISITES_TP, CSG_SYLLABUS_PREREQUISITES_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_OUTCOMES_TP, CSG_SYLLABUS_OUTCOMES_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);            
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_TEXTBOOKS_TP, CSG_SYLLABUS_TEXTBOOKS_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);   
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_GRADED_COMPONENTS_TP, CSG_SYLLABUS_GRADED_COMPONENTS_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);            
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_GRADING_NOTE_TP, CSG_SYLLABUS_GRADING_NOTE_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);            
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TP, CSG_SYLLABUS_ACADEMIC_DISHONESTY_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);
            csgBuilder.buildTitledPaneWithTextArea(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TP, CSG_SYLLABUS_SPECIAL_ASSISTANCE_TA, textAreaPane,  
                     CLASS_CSG_TITLEDPANE, CLASS_CSG_TEXT_AREA, ENABLED);
            syllabusTab.setContent(sp);
        }
        
        private void initMeetingTimeTab(Tab meetingTimeTab){
            PropertiesManager props = PropertiesManager.getPropertiesManager();

            // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
            AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();   
            ScrollPane sp = new ScrollPane();
            VBox meetingTimePane = csgBuilder.buildVBox(CSG_MEETING_TIME_PANE, null, CLASS_CSG_TABPANE, ENABLED);
            meetingTimePane.setSpacing(10);
            sp.setContent(meetingTimePane);
            sp.setFitToHeight(true);
            sp.setFitToWidth(true);
            VBox lecturePane = csgBuilder.buildVBox(CSG_LECTURES_PANE, meetingTimePane, CLASS_CSG_PANE, ENABLED);
            lecturePane.setSpacing(10);
            HBox lectureHeaderPane = csgBuilder.buildHBox(CSG_LECTURES_HEADER_PANE, lecturePane, null, ENABLED);
            lectureHeaderPane.setSpacing(10);
            csgBuilder.buildTextButton(CSG_LECTURES_ADD_BT, lectureHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildTextButton(CSG_LECTURES_REMOVE_BT, lectureHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildLabel(CSG_LECTURES_LABEL, lectureHeaderPane, CLASS_CSG_BOLD_LABEL, ENABLED);
            
            TableView<LectureItem> lecturesTable = csgBuilder.buildTableView(CSG_LECTURES_TABLEVIEW, lecturePane, CLASS_CSG_TABLE_VIEW, ENABLED);
            lecturesTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            lecturesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            lecturesTable.setEditable(true);
            TableColumn sectionColumn = csgBuilder.buildTableColumn(CSG_LECTURES_SECTION_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
            sectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
            sectionColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0/4.0));
            sectionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn daysColumn = csgBuilder.buildTableColumn(CSG_LECTURES_DAYS_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
            daysColumn.setCellValueFactory(new PropertyValueFactory<String, String>("days"));
            daysColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.2));
            daysColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn timeColumn = csgBuilder.buildTableColumn(CSG_LECTURES_TIME_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
            timeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("time"));
            timeColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.3));        
            timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn roomColumn = csgBuilder.buildTableColumn(CSG_LECTURES_ROOM_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
            roomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
            roomColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0/4.0));
            roomColumn.setCellFactory(TextFieldTableCell.forTableColumn());

            VBox recPane = csgBuilder.buildVBox(CSG_REC_PANE, meetingTimePane, CLASS_CSG_PANE, ENABLED);  
            recPane.setSpacing(10);
            HBox recHeaderPane = csgBuilder.buildHBox(CSG_REC_HEADER_PANE, recPane, null, ENABLED);
            recHeaderPane.setSpacing(10);
            csgBuilder.buildTextButton(CSG_REC_ADD_BT, recHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildTextButton(CSG_REC_REMOVE_BT, recHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildLabel(CSG_REC_LABEL, recHeaderPane, CLASS_CSG_BOLD_LABEL, ENABLED);

            TableView<RecitationItem> recTable = csgBuilder.buildTableView(CSG_REC_TABLEVIEW, recPane, CLASS_CSG_TABLE_VIEW, ENABLED);
            recTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            recTable.setEditable(true);
            recTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            TableColumn sectionColumn1 = csgBuilder.buildTableColumn(CSG_REC_SECTION_TABLE_COLUMN, recTable, CLASS_CSG_COLUMN);
            sectionColumn1.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
            sectionColumn1.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.1));
            sectionColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn daysAndTimeColumn = csgBuilder.buildTableColumn(CSG_REC_DAYSTIME_TABLE_COLUMN, recTable, CLASS_CSG_COLUMN);
            daysAndTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysAndTime"));
            daysAndTimeColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.25));
            daysAndTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn roomColumn1 = csgBuilder.buildTableColumn(CSG_REC_ROOM_TABLE_COLUMN, recTable, CLASS_CSG_COLUMN);
            roomColumn1.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
            roomColumn1.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.15));
            roomColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn ta1Column = csgBuilder.buildTableColumn(CSG_REC_TA1_TABLE_COLUMN, recTable, CLASS_CSG_COLUMN);
            ta1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
            ta1Column.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.25));
            ta1Column.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn ta2Column = csgBuilder.buildTableColumn(CSG_REC_TA2_TABLE_COLUMN, recTable, CLASS_CSG_COLUMN);
            ta2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
            ta2Column.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.25));      
            ta2Column.setCellFactory(TextFieldTableCell.forTableColumn());
            
            VBox labsPane = csgBuilder.buildVBox(CSG_LECTURES_PANE, meetingTimePane, CLASS_CSG_PANE, ENABLED);            
            labsPane.setSpacing(10);
            HBox labsHeaderPane = csgBuilder.buildHBox(CSG_LABS_HEADER_PANE, labsPane, null, ENABLED);
            labsHeaderPane.setSpacing(10);
            csgBuilder.buildTextButton(CSG_LABS_ADD_BT, labsHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildTextButton(CSG_LABS_REMOVE_BT, labsHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildLabel(CSG_LABS_LABEL, labsHeaderPane, CLASS_CSG_BOLD_LABEL, ENABLED);
            TableView<LabItem> labTable = csgBuilder.buildTableView(CSG_LABS_TABLEVIEW, labsPane, CLASS_CSG_TABLE_VIEW, ENABLED);
            labTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            labTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            labTable.setEditable(true);
            TableColumn sectionColumn2 = csgBuilder.buildTableColumn(CSG_LABS_SECTION_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
            sectionColumn2.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
            sectionColumn2.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.20));
            sectionColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn daysAndTimeColumn1 = csgBuilder.buildTableColumn(CSG_LABS_DAYSTIME_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
            daysAndTimeColumn1.setCellValueFactory(new PropertyValueFactory<String, String>("daysAndTime"));
            daysAndTimeColumn1.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.35));
            daysAndTimeColumn1.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn roomColumn2 = csgBuilder.buildTableColumn(CSG_LABS_ROOM_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
            roomColumn2.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
            roomColumn2.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.15));
            roomColumn2.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn ta1Column1 = csgBuilder.buildTableColumn(CSG_LABS_TA1_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
            ta1Column1.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
            ta1Column1.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.15));
            ta1Column1.setCellFactory(TextFieldTableCell.forTableColumn());
            TableColumn ta2Column2 = csgBuilder.buildTableColumn(CSG_LABS_TA2_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
            ta2Column2.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
            ta2Column2.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(0.15));  
            ta2Column2.setCellFactory(TextFieldTableCell.forTableColumn());
            
            meetingTimeTab.setContent(sp);
        }        
        
        private void initOfficeHoursTab(Tab officeHoursTab){
            // FIRST LOAD THE FONT FAMILIES FOR THE COMBO BOX
            PropertiesManager props = PropertiesManager.getPropertiesManager();

            // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
            AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();       
            ScrollPane sp = new ScrollPane();
            
            VBox ohPane = csgBuilder.buildVBox(CSG_OH_PANE, null, CLASS_CSG_TABPANE, ENABLED);
            ohPane.setSpacing(10);
            sp.setContent(ohPane);
            sp.setFitToHeight(true);
            sp.setFitToWidth(true);
            // INIT THE HEADER ON THE LEFT
            VBox taPane = csgBuilder.buildVBox(CSG_OH_TAS_PANE, ohPane, CLASS_CSG_PANE, ENABLED);
            taPane.setSpacing(10);
            HBox tasHeaderBox = csgBuilder.buildHBox(CSG_OH_TAS_HEADER_PANE, taPane, CLASS_CSG_BOX, ENABLED);
            tasHeaderBox.setSpacing(10);
            csgBuilder.buildTextButton(CourseSiteGeneratorPropertyType.CSG_OH_TAS_REMOVE_BT, tasHeaderBox, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildLabel(CourseSiteGeneratorPropertyType.CSG_OH_TAS_LABEL, tasHeaderBox, CLASS_CSG_HEADER_LABEL, ENABLED);
            ToggleGroup tgTA = new ToggleGroup();
            csgBuilder.buildRB(CourseSiteGeneratorPropertyType.CSG_OH_TAS_ALL_RB, tasHeaderBox, tgTA,CLASS_CSG_RB, ENABLED);
            csgBuilder.buildRB(CourseSiteGeneratorPropertyType.CSG_OH_TAS_GRAD_RB, tasHeaderBox, tgTA, CLASS_CSG_RB, ENABLED);
            csgBuilder.buildRB(CourseSiteGeneratorPropertyType.CSG_OH_TAS_UNDERGRA_RB, tasHeaderBox, tgTA, CLASS_CSG_RB, ENABLED);

            // MAKE THE TABLE AND SETUP THE DATA MODEL
            TableView<TeachingAssistantPrototype> taTable = csgBuilder.buildTableView(CSG_OH_TAS_TABLE_VIEW, taPane, CLASS_CSG_TABLE_VIEW, ENABLED);
            taTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            taTable.prefHeightProperty().bind(ohPane.heightProperty().multiply(0.2));
            TableColumn nameColumn = csgBuilder.buildTableColumn(CSG_OH_TAS_NAME_TABLE_COLUMN, taTable, CLASS_CSG_COLUMN);
            nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
            nameColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/5.0));
            TableColumn emailColumn = csgBuilder.buildTableColumn(CSG_OH_TAS_EMAIL_TABLE_COLUMN, taTable, CLASS_CSG_COLUMN);
            emailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
            emailColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(2.0/5.0));
            TableColumn timeslotsColumn = csgBuilder.buildTableColumn(CSG_OH_TAS_SLOTS_TABLE_COLUMN, taTable, CLASS_CSG_CENTER_COLUMN);
            timeslotsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("timeslots"));
            timeslotsColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/5.0));        
            TableColumn typeColumn = csgBuilder.buildTableColumn(CSG_OH_TAS_TYPE_TABLE_COLUMN, taTable, CLASS_CSG_COLUMN);
            typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
            typeColumn.prefWidthProperty().bind(taTable.widthProperty().multiply(1.0/5.0));

            // ADD BOX FOR ADDING A TA
            HBox taBox = csgBuilder.buildHBox(CSG_OH_TAS_ADD_TA_PANE, taPane, null, ENABLED);
            taBox.setSpacing(10);
            csgBuilder.buildTextField(CSG_OH_TAS_NAME_TEXT_FIELD, taBox, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextField(CSG_OH_TAS_EMAIL_TEXT_FIELD, taBox, CLASS_CSG_TEXT_FIELD , ENABLED);
            csgBuilder.buildTextButton(CSG_OH_TAS_ADD_TA_BUTTON, taBox, CLASS_CSG_BUTTON, ENABLED);

            // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
            VBox.setVgrow(taTable, Priority.ALWAYS);

            // INIT THE HEADER ON THE BUTTOM
            VBox ohsPane = csgBuilder.buildVBox(CSG_OH_PANE, ohPane, CLASS_CSG_PANE, ENABLED);
            ohsPane.setSpacing(10);
            HBox officeHoursHeaderBox = csgBuilder.buildHBox(CSG_OH_HEADER_PANE, ohsPane, null, ENABLED);
            officeHoursHeaderBox.setSpacing(10);
            csgBuilder.buildLabel(CSG_OH_LABEL, officeHoursHeaderBox, CLASS_CSG_HEADER_LABEL, ENABLED);
            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);
            officeHoursHeaderBox.getChildren().add(region1);            
            csgBuilder.buildLabel(CSG_OH_START_TIME_LABEL, officeHoursHeaderBox, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildComboBox(CSG_OH_START_TIME_CB,  officeHoursHeaderBox, CLASS_CSG_CB, ENABLED, false);
            csgBuilder.buildLabel(CSG_OH_END_TIME_LABEL, officeHoursHeaderBox, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildComboBox(CSG_OH_END_TIME_CB,  officeHoursHeaderBox, CLASS_CSG_CB, ENABLED, false);
            Region region2 = new Region();
            HBox.setHgrow(region2, Priority.ALWAYS);
            officeHoursHeaderBox.getChildren().add(region2);            
            // SETUP THE OFFICE HOURS TABLE
            TableView<TimeSlot> officeHoursTable = csgBuilder.buildTableView(CSG_OH_TABLEVIEW, ohsPane, CLASS_CSG_OFFICE_HOURS_TABLE_VIEW, ENABLED);
            officeHoursTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            officeHoursTable.prefHeightProperty().bind(ohPane.heightProperty().multiply(0.6));
            TableColumn startTimeColumn = csgBuilder.buildTableColumn(CSG_START_TIME_TABLE_COLUMN, officeHoursTable, CLASS_CSG_TIME_COLUMN);
            TableColumn endTimeColumn = csgBuilder.buildTableColumn(CSG_END_TIME_TABLE_COLUMN, officeHoursTable, CLASS_CSG_TIME_COLUMN);
            TableColumn mondayColumn = csgBuilder.buildTableColumn(CSG_MONDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn tuesdayColumn = csgBuilder.buildTableColumn(CSG_TUESDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn wednesdayColumn = csgBuilder.buildTableColumn(CSG_WEDNESDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn thursdayColumn = csgBuilder.buildTableColumn(CSG_THURSDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
            TableColumn fridayColumn = csgBuilder.buildTableColumn(CSG_FRIDAY_TABLE_COLUMN, officeHoursTable, CLASS_CSG_DAY_OF_WEEK_COLUMN);
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
                            this.setStyle("-fx-background-color: #ffe9b7");
                        else
                            this.setStyle(null);
                   }

                });
            }
            // MAKE SURE IT'S THE TABLE THAT ALWAYS GROWS IN THE LEFT PANE
            VBox.setVgrow(officeHoursTable, Priority.ALWAYS);
            officeHoursTab.setContent(sp);
        }

        private void initScheduleTab(Tab scheduleTab){
            PropertiesManager props = PropertiesManager.getPropertiesManager();

            // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
            AppNodesBuilder csgBuilder = app.getGUIModule().getNodesBuilder();     
            ScrollPane sp = new ScrollPane();
            
            VBox schedulePane = csgBuilder.buildVBox(CSG_SCHEDULE_PANE, null, CLASS_CSG_TABPANE, ENABLED);
            schedulePane.setSpacing(10);
            sp.setContent(schedulePane);
            sp.setFitToHeight(true);
            sp.setFitToWidth(true);            
            VBox calendarPane = csgBuilder.buildVBox(CSG_SCHEDULE_BOUNDARIES_PANE, schedulePane, CLASS_CSG_PANE, ENABLED);
            calendarPane.setSpacing(10);
            csgBuilder.buildLabel(CSG_SCHEDULE_BOUNDARIES_LABEL, calendarPane, CLASS_CSG_HEADER_LABEL, ENABLED);
            HBox datePane = csgBuilder.buildHBox(CSG_SCHEDULE_DATE_PANE, calendarPane, null, ENABLED);
            datePane.setSpacing(10);
            csgBuilder.buildLabel(CSG_SCHEDULE_STARTING_MONDAY_LABEL, datePane, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildDatePicker(CSG_SCHEDULE_STARTING_MONDAY_DP, datePane, CLASS_CSG_DP, ENABLED); 
            csgBuilder.buildLabel(CSG_SCHEDULE_ENDING_FRIDAY_LABEL, datePane, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildDatePicker(CSG_SCHEDULE_ENDING_FRIDAY_DP, datePane, CLASS_CSG_DP, ENABLED);

            VBox scheduleItemsPane = csgBuilder.buildVBox(CSG_SCHEDULE_ITEMS_PANE, schedulePane, CLASS_CSG_PANE, ENABLED);
            scheduleItemsPane.setSpacing(10);
            HBox scheduleHeaderPane = csgBuilder.buildHBox(CSG_SCHEDULE_ITEMS_HEADER_PANE, scheduleItemsPane, null, ENABLED);
            scheduleHeaderPane.setSpacing(10);
            csgBuilder.buildTextButton(CSG_SCHEDULE_REMOVE_BT, scheduleHeaderPane, CLASS_CSG_SMALL_BUTTON, ENABLED);
            csgBuilder.buildLabel(CSG_SCHEDULE_ITEM_LABEL, scheduleHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);

            TableView<ScheduleItem> scheduleTable = csgBuilder.buildTableView(CSG_SCHEDULE_TABLEVIEW, scheduleItemsPane, CLASS_CSG_TABLE_VIEW, ENABLED);
            scheduleTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); 
            scheduleTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            TableColumn typeColumn = csgBuilder.buildTableColumn(CSG_SCHEDULE_TYPE_TABLE_COLUMN, scheduleTable, CLASS_CSG_COLUMN);
            typeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
            typeColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.15));
            TableColumn dateColumn = csgBuilder.buildTableColumn(CSG_SCHEDULE_DATE_TABLE_COLUMN, scheduleTable, CLASS_CSG_COLUMN);
            dateColumn.setCellValueFactory(new PropertyValueFactory<String, String>("date"));
            dateColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.15));
            TableColumn titleColumn = csgBuilder.buildTableColumn(CSG_SCHEDULE_TITLE_TABLE_COLUMN, scheduleTable, CLASS_CSG_COLUMN);
            titleColumn.setCellValueFactory(new PropertyValueFactory<String, String>("title"));
            titleColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.2));
            TableColumn topicColumn = csgBuilder.buildTableColumn(CSG_SCHEDULE_TOPIC_TABLE_COLUMN, scheduleTable, CLASS_CSG_COLUMN);
            topicColumn.setCellValueFactory(new PropertyValueFactory<String, String>("topic"));
            topicColumn.prefWidthProperty().bind(scheduleTable.widthProperty().multiply(0.5));       
            
            GridPane addEditPane = csgBuilder.buildGridPane(CSG_NEW_EDIT_PANE, schedulePane, CLASS_CSG_PANE, ENABLED);
            addEditPane.setHgap(10);
            addEditPane.setVgap(10);
            csgBuilder.buildLabel(CSG_SCHEDULE_ADD_EDIT_LABEL, addEditPane, 0, 0, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SCHEDULE_TYPE_LABEL, addEditPane, 0, 1, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SCHEDULE_DATE_LABEL, addEditPane, 0, 2, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SCHEDULE_TITLE_LABEL, addEditPane, 0, 3, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SCHEDULE_TOPIC_LABEL, addEditPane, 0, 4, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildLabel(CSG_SCHEDULE_LINK_LABEL, addEditPane, 0, 5, 1, 1, CLASS_CSG_BOLD_LABEL, ENABLED);
            csgBuilder.buildComboBox(CSG_SCHEDULE_TYPE_CB, addEditPane, 1, 1, 3, 1, CLASS_CSG_CB, ENABLED, false);
            csgBuilder.buildDatePicker(CSG_SCHEDULE_DATE_DP, addEditPane, 1, 2, 3, 1, CLASS_CSG_DP, ENABLED);
            csgBuilder.buildTextField(CSG_SCHEDULE_TITLE_TF, addEditPane, 1, 3, 3, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextField(CSG_SCHEDULE_TOPIC_TF, addEditPane, 1, 4, 3, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextField(CSG_SCHEDULE_LINK_TF, addEditPane, 1, 5, 3, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
            csgBuilder.buildTextButton(CSG_SCHEDULE_ADD_UPDATE_BT, addEditPane, 0, 6, 2, 1, CLASS_CSG_BUTTON, ENABLED);
            csgBuilder.buildTextButton(CSG_SCHEDULE_CLEAR_BT, addEditPane, 3, 6, 2, 1, CLASS_CSG_BUTTON, ENABLED);
            scheduleTab.setContent(sp);
        }        
        
        private void initControllers() {
            CourseSiteGeneratorController controller = new CourseSiteGeneratorController((CourseSiteGeneratorApp) app);
            AppGUIModule gui = app.getGUIModule();        
            ((ComboBox) gui.getGUINode(CSG_SITE_SUBJECT_CB)).getEditor().textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditBanner(0, CSG_SITE_SUBJECT_CB);
            });
            ((ComboBox) gui.getGUINode(CSG_SITE_NUMBER_CB)).getEditor().textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditBanner(1, CSG_SITE_NUMBER_CB);
            });
            ((ComboBox) gui.getGUINode(CSG_SITE_SEMESTER_CB)).getEditor().textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditBanner(2, CSG_SITE_SEMESTER_CB);
            });
            ((ComboBox) gui.getGUINode(CSG_SITE_YEAR_CB)).getEditor().textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditBanner(3, CSG_SITE_YEAR_CB);
            });           
            ((TextField) gui.getGUINode(CSG_SITE_TITLE_TF)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditBanner(4, CSG_SITE_TITLE_TF);
            });
            ((CheckBox) gui.getGUINode(CSG_SITE_HOME_CB)).setOnAction(e->{
                controller.processEditPages(0, CSG_SITE_HOME_CB);
                app.getFoolproofModule().updateAll();
            });
            ((CheckBox) gui.getGUINode(CSG_SITE_SYLLABUS_CB)).setOnAction(e->{
                controller.processEditPages(1, CSG_SITE_SYLLABUS_CB);
                app.getFoolproofModule().updateAll();
            });
            ((CheckBox) gui.getGUINode(CSG_SITE_SCHEDULE_CB)).setOnAction(e->{
                controller.processEditPages(2, CSG_SITE_SCHEDULE_CB);
                app.getFoolproofModule().updateAll();
            });
            ((CheckBox) gui.getGUINode(CSG_SITE_HWS_CB)).setOnAction(e->{
                controller.processEditPages(3, CSG_SITE_HWS_CB);
                app.getFoolproofModule().updateAll();
            });
            ((Button) gui.getGUINode(CSG_SITE_FAVICON_BUTTON)).setOnAction(e->{
                controller.processEditStyleImages(0, CSG_SITE_FAVICON_IMAGE);
            });
            ((Button) gui.getGUINode(CSG_SITE_NAVBAR_BUTTON)).setOnAction(e->{
                controller.processEditStyleImages(1, CSG_SITE_NAVBAR_IMAGE);
            });
            ((Button) gui.getGUINode(CSG_SITE_LEFT_FOOTER_BUTTON)).setOnAction(e->{
                controller.processEditStyleImages(2, CSG_SITE_LEFT_FOOTER_IMAGE);
            });
            ((Button) gui.getGUINode(CSG_SITE_RIGHT_FOOTER_BUTTON)).setOnAction(e->{
                controller.processEditStyleImages(3, CSG_SITE_RIGHT_FOOTER_IMAGE);
            });
            ((TextField) gui.getGUINode(CSG_SITE_NAME_TF)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditInstructor(0, CSG_SITE_NAME_TF);
            });
            ((TextField) gui.getGUINode(CSG_SITE_ROOM_TF)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditInstructor(1, CSG_SITE_ROOM_TF);
            });
            ((TextField) gui.getGUINode(CSG_SITE_EMAIL_TF)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditInstructor(2, CSG_SITE_EMAIL_TF);
            });
            ((TextField) gui.getGUINode(CSG_SITE_HOMEPAGE_TF)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditInstructor(3, CSG_SITE_HOMEPAGE_TF);
            });
            ((TextArea) gui.getGUINode(CSG_SITE_OH_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditInstructor(4, CSG_SITE_OH_TA);
            });
            ((ComboBox) gui.getGUINode(CSG_SITE_STYLESHEET_CB)).setOnAction(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditStyleSheet(CSG_SITE_STYLESHEET_CB);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(0, CSG_SYLLABUS_DESCRIPTION_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_TOPICS_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(1, CSG_SYLLABUS_TOPICS_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(2, CSG_SYLLABUS_PREREQUISITES_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(3, CSG_SYLLABUS_OUTCOMES_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(4, CSG_SYLLABUS_TEXTBOOKS_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(5, CSG_SYLLABUS_GRADED_COMPONENTS_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(6, CSG_SYLLABUS_GRADING_NOTE_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(7, CSG_SYLLABUS_ACADEMIC_DISHONESTY_TA);
            });
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TA)).textProperty().addListener(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditSyllabusDetails(8, CSG_SYLLABUS_SPECIAL_ASSISTANCE_TA);
            });
            TableView lectureTableView = (TableView) gui.getGUINode(CSG_LECTURES_TABLEVIEW);
            ObservableList<TableColumn> lectureList = lectureTableView.getColumns();
            for(int i = 0; i < lectureList.size(); i++){
                int index = i;
                lectureList.get(i).setOnEditCommit(            
                    new EventHandler<TableColumn.CellEditEvent<LectureItem, String>>() {
                        @Override 
                        public void handle(TableColumn.CellEditEvent<LectureItem, String> t) {
                            controller.processEditLecture(t.getRowValue(), t.getNewValue(), t.getOldValue(), index);
                        }
                });
            }
            
            TableView recitationTableView = (TableView) gui.getGUINode(CSG_REC_TABLEVIEW);
            ObservableList<TableColumn> recitationList = recitationTableView.getColumns();
            for(int i = 0; i < recitationList.size(); i++){
                recitationList.get(i).setEditable(true);
                int index = i;
                recitationList.get(i).setOnEditCommit(            
                    new EventHandler<TableColumn.CellEditEvent<RecitationItem, String>>() {
                        @Override 
                        public void handle(TableColumn.CellEditEvent<RecitationItem, String> t) {
                            controller.processEditRecitation(t.getRowValue(), t.getNewValue(), t.getOldValue(), index);
                        }
                });
            }
            
            TableView labTableView = (TableView) gui.getGUINode(CSG_LABS_TABLEVIEW);
            ObservableList<TableColumn> labList = labTableView.getColumns();
            for(int i = 0; i < labList.size(); i++){
                int index = i;
                labList.get(i).setOnEditCommit(            
                    new EventHandler<TableColumn.CellEditEvent<LabItem, String>>() {
                        @Override 
                        public void handle(TableColumn.CellEditEvent<LabItem, String> t) {
                            controller.processEditLab(t.getRowValue(), t.getNewValue(), t.getOldValue(), index);
                        }
                });
            }
            TableView taTableView = (TableView) gui.getGUINode(CSG_OH_TAS_TABLE_VIEW);
            ((Button) gui.getGUINode(CSG_OH_TAS_REMOVE_BT)).setOnAction(e->{
                controller.processRemoveTA();
            });
            TableView officeHoursTableView = (TableView) gui.getGUINode(CSG_OH_TABLEVIEW);
            ((TextField) gui.getGUINode(CSG_OH_TAS_NAME_TEXT_FIELD)).setOnAction(e -> {
                if(!((Button) gui.getGUINode(CSG_OH_TAS_ADD_TA_BUTTON)).isDisabled())
                    controller.processAddTA();
            });
            ((TextField) gui.getGUINode(CSG_OH_TAS_EMAIL_TEXT_FIELD)).setOnAction(e -> {
                if(!((Button) gui.getGUINode(CSG_OH_TAS_ADD_TA_BUTTON)).isDisabled())
                    controller.processAddTA();
            });
            ((Button) gui.getGUINode(CSG_OH_TAS_ADD_TA_BUTTON)).setOnAction(e -> {
                controller.processAddTA();
            });
            ((TextField) gui.getGUINode(CSG_OH_TAS_NAME_TEXT_FIELD)).textProperty().addListener(e -> {
                app.getFoolproofModule().updateAll();
            });
            ((TextField) gui.getGUINode(CSG_OH_TAS_EMAIL_TEXT_FIELD)).textProperty().addListener(e -> {
                app.getFoolproofModule().updateAll();
            });
            ((RadioButton) gui.getGUINode(CSG_OH_TAS_ALL_RB)).setOnAction(e -> {
                taTableView.getSelectionModel().clearSelection();
                app.getFoolproofModule().updateAll();
                controller.processSetAllTA();
            });
            ((RadioButton) gui.getGUINode(CSG_OH_TAS_ALL_RB)).setSelected(true);
            ((RadioButton) gui.getGUINode(CSG_OH_TAS_GRAD_RB)).setOnAction(e -> {
                taTableView.getSelectionModel().clearSelection();
                app.getFoolproofModule().updateAll();
                controller.processSetGraduateTA();
            });
            ((RadioButton) gui.getGUINode(CSG_OH_TAS_UNDERGRA_RB)).setOnAction(e -> {
                taTableView.getSelectionModel().clearSelection();
               app.getFoolproofModule().updateAll();
               controller.processSetUndergraduateTA();
            });        
            taTableView.setOnMouseClicked(e->{
               app.getFoolproofModule().updateAll();
               officeHoursTableView.refresh();
                if(e.getButton().equals(MouseButton.PRIMARY)){
                    if(e.getClickCount() == 2 && ((CourseSiteGeneratorData)app.getDataComponent()).isTASelected()){
                        controller.processEditTA();
                    }
                }
            });
            ((ComboBox) gui.getGUINode(CSG_OH_START_TIME_CB)).setOnAction(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processSelectTimeRange(0, CSG_OH_START_TIME_CB);
            });
            ((ComboBox) gui.getGUINode(CSG_OH_END_TIME_CB)).setOnAction(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processSelectTimeRange(1, CSG_OH_END_TIME_CB);
            });
            // DON'T LET ANYONE SORT THE TABLES
            for (int i = 0; i < officeHoursTableView.getColumns().size(); i++) {
                ((TableColumn)officeHoursTableView.getColumns().get(i)).setSortable(false);
            }
            officeHoursTableView.setOnMouseClicked(e -> {
                controller.processAddOrRemoveOH();
            });
            ((Button) gui.getGUINode(CSG_LECTURES_ADD_BT)).setOnAction(e->{
                controller.processAddLecture();
            });
            ((Button) gui.getGUINode(CSG_REC_ADD_BT)).setOnAction(e->{
                controller.processAddRecitation();
            });
            ((Button) gui.getGUINode(CSG_LABS_ADD_BT)).setOnAction(e->{
                controller.processAddLab();
            });            
            ((Button) gui.getGUINode(CSG_LECTURES_REMOVE_BT)).setOnAction(e->{
                controller.processRemoveLecture();
            });
            ((Button) gui.getGUINode(CSG_REC_REMOVE_BT)).setOnAction(e->{
                controller.processRemoveRecitation();
            });
            ((Button) gui.getGUINode(CSG_LABS_REMOVE_BT)).setOnAction(e->{
                controller.processRemoveLab();
            });
            
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_STARTING_MONDAY_DP)).setOnAction(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())                
                    controller.processEditStartEndDate(CSG_SCHEDULE_STARTING_MONDAY_DP, 0);
            });
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_ENDING_FRIDAY_DP)).setOnAction(e->{
                if(((CourseSiteGeneratorData)app.getDataComponent()).getTriggerListener())
                    controller.processEditStartEndDate(CSG_SCHEDULE_ENDING_FRIDAY_DP, 1);
            });
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_STARTING_MONDAY_DP)).setDayCellFactory(
                                    (final DatePicker datePicker) -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate endDate = ((CourseSiteGeneratorData)app.getDataComponent()).getEndingFriday();
                        if (endDate != null && item.isAfter(endDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); 
                        }
                    }
            });
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_ENDING_FRIDAY_DP)).setDayCellFactory(
                                    (final DatePicker datePicker) -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate startDate = ((CourseSiteGeneratorData)app.getDataComponent()).getStartingMonday();
                        if (startDate != null && item.isBefore(startDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); 
                        }
                    }
            });
            ((Button) gui.getGUINode(CSG_SCHEDULE_CLEAR_BT)).setOnAction(e->{
                controller.processClearEntryFieldData();
            });
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_DATE_DP)).setDayCellFactory(
                                    (final DatePicker datePicker) -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate startDate = ((CourseSiteGeneratorData)app.getDataComponent()).getStartingMonday();
                        LocalDate endDate = ((CourseSiteGeneratorData)app.getDataComponent()).getEndingFriday();
                        if (startDate != null && item.isBefore(startDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); 
                        }
                        if (endDate != null && item.isAfter(endDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); 
                        }
                    }
            });            
            ((Button) gui.getGUINode(CSG_SCHEDULE_ADD_UPDATE_BT)).setOnAction(e->{
                controller.processAddOrEditSchedule();
            });
            ((TableView) gui.getGUINode(CSG_SCHEDULE_TABLEVIEW)).setOnMouseClicked(e->{
                controller.processLoadEntryFieldData();
            });
            ((Button) gui.getGUINode(CSG_SCHEDULE_REMOVE_BT)).setOnAction(e->{
                controller.processRemoveSchedule();
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
        
        public void loadDataToUI(){
            AppGUIModule gui = app.getGUIModule();       
            CourseSiteGeneratorData data = ((CourseSiteGeneratorData)app.getDataComponent());
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            
            ((CourseSiteGeneratorData)app.getDataComponent()).setTriggerListener(false);
            ((ComboBox) gui.getGUINode(CSG_SITE_SUBJECT_CB)).setValue(data.getBannerText(0));
            ((ComboBox) gui.getGUINode(CSG_SITE_NUMBER_CB)).setValue(data.getBannerText(1));
            ((ComboBox) gui.getGUINode(CSG_SITE_SEMESTER_CB)).setValue(data.getBannerText(2));
            ((ComboBox) gui.getGUINode(CSG_SITE_YEAR_CB)).setValue(data.getBannerText(3));
            ((TextField) gui.getGUINode(CSG_SITE_TITLE_TF)).setText(data.getBannerText(4));
            ((Label) gui.getGUINode(CSG_SITE_DIR_LABEL)).setText(data.getExportDir());
            ((CheckBox) gui.getGUINode(CSG_SITE_HOME_CB)).setSelected(data.getPages(0));
            ((CheckBox) gui.getGUINode(CSG_SITE_SYLLABUS_CB)).setSelected(data.getPages(1));
            ((CheckBox) gui.getGUINode(CSG_SITE_SCHEDULE_CB)).setSelected(data.getPages(2));
            ((CheckBox) gui.getGUINode(CSG_SITE_HWS_CB)).setSelected(data.getPages(3));
            ((ImageView) gui.getGUINode(CSG_SITE_FAVICON_IMAGE)).setImage
                (new Image(FILE_PROTOCOL + data.getStyleImages(0)));
            ((ImageView) gui.getGUINode(CSG_SITE_NAVBAR_IMAGE)).setImage
                (new Image(FILE_PROTOCOL+data.getStyleImages(1)));            
            ((ImageView) gui.getGUINode(CSG_SITE_LEFT_FOOTER_IMAGE)).setImage
                (new Image(FILE_PROTOCOL+data.getStyleImages(2)));            
            ((ImageView) gui.getGUINode(CSG_SITE_RIGHT_FOOTER_IMAGE)).setImage
                (new Image(FILE_PROTOCOL+data.getStyleImages(3)));     
            ((ComboBox) gui.getGUINode(CSG_SITE_STYLESHEET_CB)).getSelectionModel().select(data.getStylesheet());
            Instructor instructor = data.getInstructor();
            ((TextField) gui.getGUINode(CSG_SITE_NAME_TF)).setText(instructor.getName());
            ((TextField) gui.getGUINode(CSG_SITE_ROOM_TF)).setText(instructor.getRoom());
            ((TextField) gui.getGUINode(CSG_SITE_EMAIL_TF)).setText(instructor.getEmail());
            ((TextField) gui.getGUINode(CSG_SITE_HOMEPAGE_TF)).setText(instructor.getHomepage());
            ((TextArea) gui.getGUINode(CSG_SITE_OH_TA)).setText(instructor.getOhs());
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TA)).setText(data.getSyllabusText(0));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_TOPICS_TA)).setText(data.getSyllabusText(1));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TA)).setText(data.getSyllabusText(2));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TA)).setText(data.getSyllabusText(3));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TA)).setText(data.getSyllabusText(4));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TA)).setText(data.getSyllabusText(5));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TA)).setText(data.getSyllabusText(6));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TA)).setText(data.getSyllabusText(7));
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TA)).setText(data.getSyllabusText(8));
            ((RadioButton) gui.getGUINode(CSG_OH_TAS_ALL_RB)).setSelected(true);
            data.setAllTA();
            ((ComboBox) gui.getGUINode(CSG_OH_START_TIME_CB)).setValue(data.getStartTime());
            ((ComboBox) gui.getGUINode(CSG_OH_END_TIME_CB)).setValue(data.getEndTime());
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_STARTING_MONDAY_DP)).setValue(data.getStartingMonday());
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_ENDING_FRIDAY_DP)).setValue(data.getEndingFriday());
            ((Button) gui.getGUINode(CSG_SCHEDULE_ADD_UPDATE_BT)).setText(props.getProperty(CSG_SCHEDULE_ADD_UPDATE_BT + "_TEXT"));
            ((CourseSiteGeneratorData)app.getDataComponent()).setTriggerListener(true);            
        }
        
        public void resetUserInterface(){
            AppGUIModule gui = app.getGUIModule();       
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            ((CourseSiteGeneratorData)app.getDataComponent()).setTriggerListener(false);
            ((TabPane) gui.getGUINode(CSG_TAB_PANE)).getSelectionModel().select(0);
            ((ComboBox) gui.getGUINode(CSG_SITE_SUBJECT_CB)).setValue("");
            ((ComboBox) gui.getGUINode(CSG_SITE_NUMBER_CB)).setValue("");
            ((ComboBox) gui.getGUINode(CSG_SITE_SEMESTER_CB)).setValue("");
            ((ComboBox) gui.getGUINode(CSG_SITE_YEAR_CB)).setValue("");
            ((TextField) gui.getGUINode(CSG_SITE_TITLE_TF)).clear();
            ((Label) gui.getGUINode(CSG_SITE_DIR_LABEL)).setText(".\\export\\[subject]_[number]_[semester]_[year]\\public_html");
            ((CheckBox) gui.getGUINode(CSG_SITE_HOME_CB)).setSelected(true);
            ((CheckBox) gui.getGUINode(CSG_SITE_SYLLABUS_CB)).setSelected(true);
            ((CheckBox) gui.getGUINode(CSG_SITE_SCHEDULE_CB)).setSelected(true);
            ((CheckBox) gui.getGUINode(CSG_SITE_HWS_CB)).setSelected(true);
            ((ImageView) gui.getGUINode(CSG_SITE_FAVICON_IMAGE)).setImage
                (new Image(FILE_PROTOCOL + props.getProperty(APP_PATH_IMAGES) + props.getProperty(IMAGE_PLACEHOLDER_ICON)));
            ((ImageView) gui.getGUINode(CSG_SITE_NAVBAR_IMAGE)).setImage
                (new Image(FILE_PROTOCOL + props.getProperty(APP_PATH_IMAGES) + props.getProperty(IMAGE_PLACEHOLDER_ICON)));            
            ((ImageView) gui.getGUINode(CSG_SITE_LEFT_FOOTER_IMAGE)).setImage
                (new Image(FILE_PROTOCOL + props.getProperty(APP_PATH_IMAGES) + props.getProperty(IMAGE_PLACEHOLDER_ICON)));            
            ((ImageView) gui.getGUINode(CSG_SITE_RIGHT_FOOTER_IMAGE)).setImage
                (new Image(FILE_PROTOCOL + props.getProperty(APP_PATH_IMAGES) + props.getProperty(IMAGE_PLACEHOLDER_ICON)));     
            ((ComboBox) gui.getGUINode(CSG_SITE_STYLESHEET_CB)).getSelectionModel().clearSelection();
            ((ComboBox) gui.getGUINode(CSG_SITE_STYLESHEET_CB)).setValue("");
            ((TextField) gui.getGUINode(CSG_SITE_NAME_TF)).clear();
            ((TextField) gui.getGUINode(CSG_SITE_ROOM_TF)).clear();
            ((TextField) gui.getGUINode(CSG_SITE_EMAIL_TF)).clear();
            ((TextField) gui.getGUINode(CSG_SITE_HOMEPAGE_TF)).clear();
            ((TextArea) gui.getGUINode(CSG_SITE_OH_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_TOPICS_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TA)).clear();
            ((TextArea) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TA)).clear();
            ((TitledPane) gui.getGUINode(CSG_SITE_OH_TITLEDPANE)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_TOPICS_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TP)).setExpanded(false);
            ((TitledPane) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TP)).setExpanded(false);
            ((RadioButton) gui.getGUINode(CSG_OH_TAS_ALL_RB)).setSelected(true);
            ((TextField) gui.getGUINode(CSG_OH_TAS_NAME_TEXT_FIELD)).clear();
            ((TextField) gui.getGUINode(CSG_OH_TAS_EMAIL_TEXT_FIELD)).clear();
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_STARTING_MONDAY_DP)).setValue(null);
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_ENDING_FRIDAY_DP)).setValue(null);
            ((ComboBox) gui.getGUINode(CSG_SCHEDULE_TYPE_CB)).getSelectionModel().clearSelection();
            ((DatePicker) gui.getGUINode(CSG_SCHEDULE_DATE_DP)).setValue(null);
            ((TextField) gui.getGUINode(CSG_SCHEDULE_TITLE_TF)).clear();
            ((TextField) gui.getGUINode(CSG_SCHEDULE_TOPIC_TF)).clear();
            ((TextField) gui.getGUINode(CSG_SCHEDULE_LINK_TF)).clear();
            ((Button) gui.getGUINode(CSG_SCHEDULE_ADD_UPDATE_BT)).setText(props.getProperty(CSG_SCHEDULE_ADD_UPDATE_BT + "_TEXT"));
            ((CourseSiteGeneratorData)app.getDataComponent()).setTriggerListener(true);
        }
}
