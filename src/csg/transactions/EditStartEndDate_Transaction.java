/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditStartEndDate_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    DatePicker datePicker;
    LocalDate oldDate;
    LocalDate newDate;
    int index;
    
    public EditStartEndDate_Transaction(    CourseSiteGeneratorData data, DatePicker datePicker, LocalDate oldDate,
            LocalDate newDate, int index)
    {
        this.data = data;
        this.datePicker = datePicker;
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.index = index;
    }
    
    @Override
    public void doTransaction() {
        data.setTriggerListener(false);
        if(index == 0 ){
            data.setStartingMonday(newDate);
            datePicker.setValue(newDate);
        }
        else if(index == 1){
            data.setEndingFriday(newDate);
            datePicker.setValue(newDate);
        }
        data.setTriggerListener(true);
    }

    @Override
    public void undoTransaction() {
        data.setTriggerListener(false);
        if(index == 0 ){
            data.setStartingMonday(oldDate);
            datePicker.setValue(oldDate);
        }
        else if(index == 1){
            data.setEndingFriday(oldDate);
            datePicker.setValue(oldDate);
        }
        data.setTriggerListener(true);    
    }
    
}
