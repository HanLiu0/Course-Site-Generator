/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.LectureItem;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditLecture_Transaction implements jTPS_Transaction {
    TableView<LectureItem> lectureTable;
    LectureItem lecture;
    String newData;
    String oldData;
    int index;
    
    public EditLecture_Transaction(TableView<LectureItem> lectureTable, LectureItem lecture, String newData, String oldData, int index) {
        this.lectureTable = lectureTable;
        this.lecture = lecture;
        this.newData = newData;
        this.oldData = oldData;
        this.index = index;
    }

    @Override
    public void doTransaction() {
        if(index == 0){
            lecture.setSection(newData);
        }
        else if(index ==1)
            lecture.setDays(newData);
        else if(index ==2)
            lecture.setTime(newData);
        else if(index ==3)
            lecture.setRoom(newData);
        lectureTable.refresh();
    }

    @Override
    public void undoTransaction() {
        if(index == 0){
            lecture.setSection(oldData);
        }
        else if(index ==1)
            lecture.setDays(oldData);
        else if(index ==2)
            lecture.setTime(oldData);
        else if(index ==3)
            lecture.setRoom(oldData);    
        lectureTable.refresh();
    }
           
}
