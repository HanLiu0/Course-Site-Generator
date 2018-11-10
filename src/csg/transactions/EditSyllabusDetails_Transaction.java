/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.scene.control.TextArea;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditSyllabusDetails_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    TextArea textArea;
    int index;
    String text;
    String oldText;
    
    public EditSyllabusDetails_Transaction(CourseSiteGeneratorData data, TextArea textArea, 
         int index, String text){
        this.data = data;
        this.textArea = textArea;
        this.index = index;
        this.text = text;
        this.oldText = data.getSyllabusText(index);
    }
            
    public void doTransaction(){
        data.setSyllabusText(index, text);
        data.setTriggerListener(false);
        ((TextArea)textArea).setText(text);
        data.setTriggerListener(true);
    }
    
    public void undoTransaction(){
        data.setSyllabusText(index, oldText);
        data.setTriggerListener(false);
        ((TextArea)textArea).setText(oldText);        
        data.setTriggerListener(true);
    }
    
    public int getIndex(){
        return index;
    }
    
    public void updateTransaction(String newText){
        text = newText;
        doTransaction();
    }
}
