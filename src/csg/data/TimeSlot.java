package csg.data;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_UNDERGRA;

/**
 * This class stores information for a single row in our
 * office hours table.
 * 
 * @author Richard McKenna
 */
public class TimeSlot {

    public enum DayOfWeek {   
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY
    }   
    private StringProperty startTime;
    private StringProperty endTime;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> tas;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> alltas;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> undergratas;
    private HashMap<DayOfWeek, ArrayList<TeachingAssistantPrototype>> gratas;
    private HashMap<DayOfWeek, StringProperty> dayText;
    private HashMap<DayOfWeek, StringProperty> alldayText;
    private HashMap<DayOfWeek, StringProperty> undergradayText;
    private HashMap<DayOfWeek, StringProperty> gradayText;

    public TimeSlot(String initStartTime, String initEndTime) {
        startTime = new SimpleStringProperty(initStartTime);
        endTime = new SimpleStringProperty(initEndTime);
        alltas = new HashMap();
        undergratas = new HashMap();
        gratas = new HashMap();
        alldayText = new HashMap();
        undergradayText = new HashMap();
        gradayText = new HashMap();
        dayText = alldayText;
        tas = alltas;
        for (DayOfWeek dow : DayOfWeek.values()) {
            alltas.put(dow, new ArrayList());
            undergratas.put(dow, new ArrayList());
            gratas.put(dow, new ArrayList());
            alldayText.put(dow, new SimpleStringProperty());
            undergradayText.put(dow, new SimpleStringProperty());
            gradayText.put(dow, new SimpleStringProperty());
        }
    }
    
    public boolean containsTA(TeachingAssistantPrototype TA, DayOfWeek day){
        return alltas.get(day).contains(TA);
    }
    
    public void removeTA(TeachingAssistantPrototype TA){
        for(DayOfWeek dow: DayOfWeek.values()){
            if(TA.getType().equals(TA_TYPE_UNDERGRA)){
                if(undergratas.get(dow).contains(TA)){
                    alltas.get(dow).remove(TA);
                    updateAllTAOH(dow);            
                    undergratas.get(dow).remove(TA);
                    updateUndergraTAOH(dow);        
                    TA.removeOH(this, dow);
                    TA.setTimeslots("" + (Integer.parseInt(TA.getTimeslots())-1));
                }
            }
            else
                if(gratas.get(dow).contains(TA)){
                    alltas.get(dow).remove(TA);
                    updateAllTAOH(dow);            
                    gratas.get(dow).remove(TA);
                    updateGraTAOH(dow);                   
                    TA.removeOH(this, dow);
                    TA.setTimeslots("" + (Integer.parseInt(TA.getTimeslots())-1));
                }
        }
    }
    
    public void removeTA(TeachingAssistantPrototype TA, DayOfWeek day){
        TA.removeOH(this, day);
        alltas.get(day).remove(TA);
        updateAllTAOH(day);
        if(TA.getType().equals(TA_TYPE_UNDERGRA)){
            undergratas.get(day).remove(TA);
            updateUndergraTAOH(day);
        }
        else{
            gratas.get(day).remove(TA);
            updateGraTAOH(day);
        }
        TA.setTimeslots("" + (Integer.parseInt(TA.getTimeslots())-1));
    }
    
    public void addTA(TeachingAssistantPrototype TA, String day){
        DayOfWeek dow = DayOfWeek.valueOf(day);
        TA.addOH(this, dow);
        alltas.get(dow).add(TA);        
        updateAllTAOH(dow);
        if(TA.getType().equals(TA_TYPE_UNDERGRA)){
            undergratas.get(dow).add(TA);
            updateUndergraTAOH(dow);
        }
        else{
            gratas.get(dow).add(TA);
            updateGraTAOH(dow);
        }
        TA.setTimeslots("" + (Integer.parseInt(TA.getTimeslots())+1));
    }
    
    // ACCESSORS AND MUTATORS
    
    public String getStartTime() {
        return startTime.getValue();
    }
    
    public void setStartTime(String initStartTime) {
        startTime.setValue(initStartTime);
    }
    
    public StringProperty startTimeProperty() {
        return startTime;
    }
    
    public String getEndTime() {
        return endTime.getValue();
    }
    
    public void setEndTime(String initEndTime) {
        endTime.setValue(initEndTime);
    }
    
    public StringProperty endTimeProperty() {
        return endTime;
    }
    
    public String getMonday() {
        return dayText.get(DayOfWeek.MONDAY).getValue();
    }
    
    public void setMonday(String initMonday) {
        dayText.get(DayOfWeek.MONDAY).setValue(initMonday);
    }
    
    public StringProperty mondayProperty() {
        return this.dayText.get(DayOfWeek.MONDAY);
    }
    
    public String getTuesday() {
        return dayText.get(DayOfWeek.TUESDAY).getValue();
    }
    
    public void setTuesday(String initTuesday) {
        dayText.get(DayOfWeek.TUESDAY).setValue(initTuesday);
    }
    
    public StringProperty tuesdayProperty() {
        return this.dayText.get(DayOfWeek.TUESDAY);
    }
    
    public String getWednesday() {
        return dayText.get(DayOfWeek.WEDNESDAY).getValue();
    }
    
    public void setWednesday(String initWednesday) {
        dayText.get(DayOfWeek.WEDNESDAY).setValue(initWednesday);
    }
    
    public StringProperty wednesdayProperty() {
        return this.dayText.get(DayOfWeek.WEDNESDAY);
    }
    
    public String getThursday() {
        return dayText.get(DayOfWeek.THURSDAY).getValue();
    }
    
    public void setThursday(String initThursday) {
        dayText.get(DayOfWeek.THURSDAY).setValue(initThursday);
    }
    
    public StringProperty thursdayProperty() {
        return this.dayText.get(DayOfWeek.THURSDAY);
    }
    
    public String getFriday() {
        return dayText.get(DayOfWeek.FRIDAY).getValue();
    }
    
    public void setFriday(String initFriday) {
        dayText.get(DayOfWeek.FRIDAY).setValue(initFriday);
    }
    
    public StringProperty fridayProperty() {
        return this.dayText.get(DayOfWeek.FRIDAY);
    }
    
    public void reset() {
        for (DayOfWeek dow : DayOfWeek.values()) {
            alltas.get(dow).clear();
            undergratas.get(dow).clear();
            gratas.get(dow).clear();
            alldayText.get(dow).setValue("");
            undergradayText.get(dow).setValue("");
            gradayText.get(dow).setValue("");
        }
    }
    
    public void updateName(TeachingAssistantPrototype ta){
        for(DayOfWeek dow: DayOfWeek.values()){
            if(alltas.get(dow).contains(ta)){
                updateAllTAOH(dow);
                if(ta.getType().equals(TA_TYPE_UNDERGRA))
                    updateUndergraTAOH(dow);
                else
                    updateGraTAOH(dow);
            }
        }
    }
    
    public void updateAllTAOH(DayOfWeek day){        
        String newOHNames = "";
        for(TeachingAssistantPrototype ta: alltas.get(day)){
            if(newOHNames.isEmpty())
                newOHNames += ta.getName();
            else
                newOHNames += "\n" + ta.getName();
        }
        if(newOHNames == "")
            alldayText.get(day).set(null);
        else
            alldayText.get(day).set(newOHNames);
    }
    
        public void updateUndergraTAOH(DayOfWeek day){        
        String newOHNames = "";
        for(TeachingAssistantPrototype ta: undergratas.get(day)){
            if(newOHNames.isEmpty())
                newOHNames += ta.getName();
            else
                newOHNames += "\n" + ta.getName();
        }
        if(newOHNames == "")
            undergradayText.get(day).set(null);
        else
            undergradayText.get(day).set(newOHNames);
    }
        
     public void updateGraTAOH(DayOfWeek day){        
        String newOHNames = "";
        for(TeachingAssistantPrototype ta: gratas.get(day)){
            if(newOHNames.isEmpty())
                newOHNames += ta.getName();
            else
                newOHNames += "\n" + ta.getName();
        }
        if(newOHNames == "")
            gradayText.get(day).set(null);
        else
            gradayText.get(day).set(newOHNames);
    }
     
     public void fromGraToUndergra(TeachingAssistantPrototype ta){
         for(DayOfWeek dow: DayOfWeek.values()){
            if(gratas.get(dow).contains(ta)){
                gratas.get(dow).remove(ta);
                undergratas.get(dow).add(ta);
                updateUndergraTAOH(dow);
                updateGraTAOH(dow);
            }
        }
     }
     
     public void fromUndergraToGra(TeachingAssistantPrototype ta){
         for(DayOfWeek dow: DayOfWeek.values()){
            if(undergratas.get(dow).contains(ta)){
                undergratas.get(dow).remove(ta);
                gratas.get(dow).add(ta);
                updateUndergraTAOH(dow);
                updateGraTAOH(dow);
            }
        }
     }
     
     public void setAllTA(){
         tas = alltas;
         dayText = alldayText;
     }

     public void setGradTA(){
         tas = gratas;
         dayText = gradayText;
     }
     
     public void setUndergraTA(){
         tas = undergratas;
         dayText = undergradayText;
     }
}