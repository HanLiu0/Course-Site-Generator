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
public class SelectTimeRange_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    ComboBox cb;
    String time;
    String oldTime;
    int index;
    
    public SelectTimeRange_Transaction(CourseSiteGeneratorData data, ComboBox cb, String time, int index){
        this.data = data;
        this.cb = cb;
        this.time = time;
        if(index == 0)
            this.oldTime = data.getStartTime();
        else
            this.oldTime = data.getEndTime();
        this.index = index;
    }
            
    public void doTransaction(){        
        data.setTriggerListener(false);
        if(index==0){
            data.setStartTime(time);
            data.resetTimeRange();
        }
        else{
            data.setEndTime(time);
            data.resetTimeRange();
        }
        cb.getSelectionModel().select(time);
        data.setTriggerListener(true);
    }
    
    public void undoTransaction(){
        data.setTriggerListener(false);
        if(index==0){
            data.setStartTime(oldTime);
            data.resetTimeRange();
        }
        else{
            data.setEndTime(oldTime);
            data.resetTimeRange();
        }
        cb.getSelectionModel().select(oldTime);
        data.setTriggerListener(true);
    }
    
}
