/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditStyleSheet_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    ComboBox styleSheetComboBox;
    String stylesheet;
    String oldStylesheet;
    
    public EditStyleSheet_Transaction(CourseSiteGeneratorData data, ComboBox comboBox, String stylesheet){
        this.data = data;
        this.styleSheetComboBox = comboBox;
        this.stylesheet = stylesheet;
        this.oldStylesheet = data.getStylesheet();
    }
            
    public void doTransaction(){
        data.setStylesheet(stylesheet);
        data.setTriggerListener(false);
        styleSheetComboBox.getSelectionModel().select(stylesheet);
        data.setTriggerListener(true);
    }
    
    public void undoTransaction(){
        data.setStylesheet(oldStylesheet);
        data.setTriggerListener(false);
        styleSheetComboBox.getSelectionModel().select(oldStylesheet);
        data.setTriggerListener(true);
    }
    
}
