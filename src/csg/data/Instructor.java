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
public class Instructor {
    private final StringProperty name;
    private final StringProperty room;
    private final StringProperty email;
    private final StringProperty homepage;
    private final StringProperty ohs;
    
    public Instructor(String name, String room, String email, String homepage, String ohs){
        this.name = new SimpleStringProperty(name);
        this.room = new SimpleStringProperty(room);
        this.email = new SimpleStringProperty(email);
        this.homepage = new SimpleStringProperty(homepage);
        this.ohs = new SimpleStringProperty(ohs);
    }
    
    public String getInfo(int i){
        if(i == 0)
            return getName();
        else if(i == 1)
            return getRoom();
        else if(i==2)
            return getEmail();
        else if (i==3)
            return getHomepage();
        else
            return getOhs();
    }

    public void setInfo(int i, String text){
        if(i == 0)
            setName(text);
        else if(i == 1)
            setRoom(text);
        else if(i==2)
            setEmail(text);
        else if (i==3)
            setHomepage(text);
        else
            setOhs(text);
    }
        
    /**
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.set(name);
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
     * @return the email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * @return the homepage
     */
    public String getHomepage() {
        return homepage.get();
    }

    /**
     * @param homepage the homepage to set
     */
    public void setHomepage(String homepage) {
        this.homepage.set(homepage);
    }

    /**
     * @return the ohs
     */
    public String getOhs() {
        return ohs.get();
    }

    /**
     * @param ohs the ohs to set
     */
    public void setOhs(String ohs) {
        this.ohs.set(ohs);
    }
}
