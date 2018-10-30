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
public class RecitationItem {
    private final StringProperty section;
    private final StringProperty daysAndTime;
    private final StringProperty room;
    private final StringProperty ta1;
    private final StringProperty ta2;
    
    public RecitationItem(String section, String daysAndTime, String room, String ta1, String ta2){
        this.section = new SimpleStringProperty(section);
        this.room = new SimpleStringProperty(room);
        this.daysAndTime = new SimpleStringProperty(daysAndTime);
        this.ta1 = new SimpleStringProperty(ta1);
        this.ta2 = new SimpleStringProperty(ta2);
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
    public String getDaysAndTime() {
        return daysAndTime.get();
    }

    /**
     * @param email the email to set
     */
    public void setDaysAndTime(String daysAndTime) {
        this.daysAndTime.set(daysAndTime);
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

    /**
     * @return the homepage
     */
    public String getTa1() {
        return ta1.get();
    }

    /**
     * @param homepage the homepage to set
     */
    public void setTa1(String ta1) {
        this.ta1.set(ta1);
    }

    /**
     * @return the ohs
     */
    public String getTa2() {
        return ta2.get();
    }

    /**
     * @param ohs the ohs to set
     */
    public void setTa2(String ta2) {
        this.ta2.set(ta2);
    }
}
