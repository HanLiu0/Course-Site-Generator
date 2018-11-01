/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.scene.control.CheckBox;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditPages_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    CheckBox checkBox;
    int index;
    boolean selected;
    
    public EditPages_Transaction(CourseSiteGeneratorData data, CheckBox checkBox, int index, boolean selected){
        this.data = data;
        this.checkBox = checkBox;
        this.index = index;
        this.selected = selected;
    }
            
    public void doTransaction(){
        data.setPages(index, selected);
        checkBox.setSelected(selected);
    }
    
    public void undoTransaction(){
        data.setPages(index, !selected);
        checkBox.setSelected(!selected);
    }
}
