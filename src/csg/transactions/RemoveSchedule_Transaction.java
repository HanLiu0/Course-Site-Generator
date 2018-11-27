/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.ScheduleItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class RemoveSchedule_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    ScheduleItem schedule;
    
    public RemoveSchedule_Transaction(CourseSiteGeneratorData data, ScheduleItem schedule) {
        this.data = data;
        this.schedule = schedule;
    }

    @Override
    public void doTransaction() {
        data.removeSchedule(schedule);
    }

    @Override
    public void undoTransaction() {
        data.addSchedule(schedule);
    }
}
