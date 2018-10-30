/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hanli
 */
public class LectureItem {
    private final StringProperty section;
    private final StringProperty days;
    private final StringProperty time;
    private final StringProperty room;
    
    public LectureItem(String section, String days, String time, String room){
        this.section = new SimpleStringProperty(section);
        this.room = new SimpleStringProperty(room);
        this.days = new SimpleStringProperty(days);
        this.time = new SimpleStringProperty(time);
    }

    /**
     * @return the name
     */
    public String getSection() {
        return section.get();
    }

    /**
     * @param name the name to set
     */
    public void setSection(String section) {
        this.section.set(section);
    }
    
    /**
     * @return the email
     */
    public String getDays() {
        return days.get();
    }

    /**
     * @param email the email to set
     */
    public void setDays(String days) {
        this.days.set(days);
    }

    /**
     * @return the email
     */
    public String getTimes() {
        return time.get();
    }

    /**
     * @param email the email to set
     */
    public void setTime(String time) {
        this.time.set(time);
    }
    
    /**
     * @return the room
     */
    public String getRoom() {
        return room.get();
    }

    /**
     * @param room the room to set
     */
    public void setRoom(String room) {
        this.room.set(room);
    }

}
