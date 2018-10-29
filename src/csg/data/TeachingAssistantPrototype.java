package csg.data;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import csg.data.TimeSlot.DayOfWeek;

/**
 * This class represents a Teaching Assistant for the table of TAs.
 * 
 * @author Richard McKenna
 */
public class TeachingAssistantPrototype {
    // THE TABLE WILL STORE TA NAMES AND EMAILS
    public static final String TA_TYPE_UNDERGRA = "Undergraduate";
    public static final String TA_TYPE_GRA = "Graduate";
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty timeslots;
    private final StringProperty type;
    private HashMap<TimeSlot, ArrayList<DayOfWeek>> ohs;

    /**
     * Constructor initializes both the TA name and email.
     */
    public TeachingAssistantPrototype(String initName, String initEmail, String initType) {
        name = new SimpleStringProperty(initName);
        email = new SimpleStringProperty(initEmail);
        timeslots = new SimpleStringProperty("0");
        type = new SimpleStringProperty(initType);
        ohs = new HashMap<>();
    }

    // ACCESSORS AND MUTATORS FOR THE PROPERTIES

    public void addOH(TimeSlot time, DayOfWeek dow){
        if(ohs.get(time) == null)
            ohs.put(time, new ArrayList<DayOfWeek>());
        ohs.get(time).add(dow);
    }
    
    public void removeOH(TimeSlot time, DayOfWeek dow){
        ohs.get(time).remove(dow);
    }
    
    public HashMap<TimeSlot, ArrayList<DayOfWeek>> getOH(){
        return ohs;
    }
    
    public void updateInfo(){
        for(TimeSlot oh: ohs.keySet()){
            oh.updateName(this);
        }
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String initName) {
        name.set(initName);
        updateInfo();
    }
    
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String initEmail) {
        email.set(initEmail);
    }
    
    public StringProperty emailProperty(){
        return email;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public String getType(){
        return type.get();
    }
    
    public void setType(String initType){
        type.set(initType);
    }
    
    public StringProperty typeProperty(){
        return type;
    }
    
    public void setTimeslots(String initTimeslots){
        timeslots.set(initTimeslots);
    }
    
    public String getTimeslots(){
        return timeslots.get();
    }
    
    public StringProperty timeslotsProperty(){
        return timeslots;
    }
    
    @Override
    public String toString() {
        return name.getValue();
    }
}