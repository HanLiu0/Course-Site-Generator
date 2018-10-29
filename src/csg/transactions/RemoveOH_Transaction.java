/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;

/**
 *
 * @author hanliu
 */
public class RemoveOH_Transaction implements jTPS_Transaction {
    TableView<TimeSlot> officeHoursTable;
    TeachingAssistantPrototype TA;
    TimeSlot.DayOfWeek day;
    TimeSlot timeSlot;
    
    public RemoveOH_Transaction(TableView<TimeSlot> officeHoursTable, TimeSlot timeslot, TeachingAssistantPrototype TA, TimeSlot.DayOfWeek day) {
        this.officeHoursTable = officeHoursTable;
        this.timeSlot = timeslot;
        this.TA = TA;
        this.day = day;
    }

    @Override
    public void doTransaction() {
        timeSlot.removeTA(TA, day);        
        officeHoursTable.refresh();
    }

    @Override
    public void undoTransaction() {
        timeSlot.addTA(TA, day.toString());
        officeHoursTable.refresh();
    }
    
}
