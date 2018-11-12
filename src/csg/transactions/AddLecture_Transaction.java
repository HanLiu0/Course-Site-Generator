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
public class AddLecture_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    LectureItem lecture;
    
    public AddLecture_Transaction(CourseSiteGeneratorData data) {
        this.data = data;
        lecture = new LectureItem("?", "?", "?", "?");
    }

    @Override
    public void doTransaction() {
        data.addLecture(lecture);
    }

    @Override
    public void undoTransaction() {
        data.removeLecture(lecture);
    }
    
}
