/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.Instructor;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author hanli
 */
public class EditInstructor_Transaction {
    CourseSiteGeneratorData data;
    Node node;
    int index;
    String text;
    String oldText;
    Instructor instructor;
    
    public EditInstructor_Transaction(CourseSiteGeneratorData data, Node node, 
         int index, String text){
        this.data = data;
        this.node = node;
        this.instructor = data.getInstructor();
        this.index = index;
        this.text = text;
        this.oldText = instructor.getInfo(index);
    }
            
    public void doTransaction(){
        instructor.setInfo(index, text);
        if(index <4)
            ((TextField)node).setText(text);
        else
            ((TextArea)node).setText(text);      
    }
    
    public void undoTransaction(){
        instructor.setInfo(index, oldText);
        if(index <4)
            ((TextField)node).setText(oldText);
        else
            ((TextArea)node).setText(oldText);            
    }
    
    public int getIndex(){
        return index;
    }
    
    public void updateTransaction(String newText){
        text = newText;
        doTransaction();
    }
}
