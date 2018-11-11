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
public class ScheduleItem {
    private final StringProperty type;
    private final StringProperty date;
    private final StringProperty title;
    private final StringProperty topic;
    private final StringProperty link;
    
    public ScheduleItem(String type, String date, String title, String topic, String link){
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleStringProperty(date);
        this.title = new SimpleStringProperty(title);
        this.topic = new SimpleStringProperty(topic);
        this.link = new SimpleStringProperty(link);
    }

    /**
     * @return the name
     */
    public String getType() {
        return type.get();
    }

    /**
     * @param name the name to set
     */
    public void setType(String type) {
        this.type.set(type);
    }
    
    /**
     * @return the email
     */
    public String getTitle() {
        return title.get();
    }

    /**
     * @param email the email to set
     */
    public void setTitle(String title) {
        this.title.set(title);
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date.set(date);
    }

    /**
     * @return the homepage
     */
    public String getTopic() {
        return topic.get();
    }

    /**
     * @param homepage the homepage to set
     */
    public void setTopic(String topic) {
        this.topic.set(topic);
    }

    /**
     * @return the ohs
     */
    public String getLink() {
        return link.get();
    }

    /**
     * @param ohs the ohs to set
     */
    public void setLink(String link) {
        this.link.set(link);
    }
}