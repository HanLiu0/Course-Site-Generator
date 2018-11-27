/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.ScheduleItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditSchedule_Transaction implements jTPS_Transaction {
    ScheduleItem schedule;
    String oldType;
    String oldDate;
    String oldTitle;
    String oldTopic;
    String oldLink;
    String  type;
    String date;
    String title;
    String topic;
    String link;
    
    public EditSchedule_Transaction(ScheduleItem schedule, String type, String date, String title, String topic, String link) {
        this.schedule = schedule;
        this.type = type;
        this.date = date;
        this.title = title;
        this.topic = topic;
        this.link = link;
        oldType = schedule.getType();
        oldDate = schedule.getDate();
        oldTitle = schedule.getTitle();
        oldTopic = schedule.getTopic();
        oldLink = schedule.getLink();
    }

    @Override
    public void doTransaction() {
        schedule.setType(type);
        schedule.setDate(date);
        schedule.setTitle(title);
        schedule.setTopic(topic);
        schedule.setLink(link);
    }

    @Override
    public void undoTransaction() {
        schedule.setType(oldType);
        schedule.setDate(oldDate);
        schedule.setTitle(oldTitle);
        schedule.setTopic(oldTopic);
        schedule.setLink(oldLink);
    }
}
