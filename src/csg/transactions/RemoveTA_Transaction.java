/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class RemoveTA_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    TeachingAssistantPrototype ta;
    HashMap<TimeSlot, ArrayList<DayOfWeek>> ohs;
    
    public RemoveTA_Transaction(CourseSiteGeneratorData initData, TeachingAssistantPrototype initTA) {
        data = initData;
        ta = initTA;
        ohs = new HashMap<>();
        HashMap<TimeSlot, ArrayList<DayOfWeek>> oldOH = initTA.getOH();
        for(Map.Entry<TimeSlot, ArrayList<DayOfWeek>> entry: oldOH.entrySet()){
            ohs.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    @Override
    public void doTransaction() {
        data.removeTA(ta);
    }

    @Override
    public void undoTransaction() {
        data.addTA(ta, ta.getType());
        for(Map.Entry<TimeSlot, ArrayList<DayOfWeek>> oh: ohs.entrySet()){
            for(DayOfWeek dow: oh.getValue()){
                oh.getKey().addTA(ta, dow.toString());
            }
        }
    }
}
