/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.LectureItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class RemoveLecture_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    LectureItem lecture;
    
    public RemoveLecture_Transaction(CourseSiteGeneratorData data, LectureItem lecture) {
        this.data = data;
        this.lecture = lecture;
    }

    @Override
    public void doTransaction() {
        data.removeLecture(lecture);
    }

    @Override
    public void undoTransaction() {
        data.addLecture(lecture);
    }
    
}
