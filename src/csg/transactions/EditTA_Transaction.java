package csg.transactions;

import djf.AppTemplate;
import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_GRA;
import static csg.data.TeachingAssistantPrototype.TA_TYPE_UNDERGRA;

/**
 *
 * @author McKillaGorilla
 */
public class EditTA_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData app;
    TeachingAssistantPrototype TA;
    String originalName;
    String originalEmail;
    String originalType;    
    String newName;
    String newEmail;
    String newType;
    
    public EditTA_Transaction(AppTemplate app, TeachingAssistantPrototype initTA, String newName, String newEmail, String newType) {
        this.app = ((CourseSiteGeneratorData)app.getDataComponent());
        TA = initTA;
        originalName = initTA.getName();
        originalEmail = initTA.getEmail();
        originalType = initTA.getType();
        this.newName = newName;
        this.newEmail = newEmail;
        this.newType = newType;
    }

    @Override
    public void doTransaction() {            
        if(!originalName.equals(newName)){
                TA.setName(newName);
        }
        if(!originalEmail.equals(newEmail))
            TA.setEmail(newEmail);
        if(!originalType.equals(newType)){
                TA.setType(newType);
                app.doTypeEdited(TA);
         }
    }

    @Override
    public void undoTransaction() {        
        if(!originalName.equals(TA.getName())){
                TA.setName(originalName);
        }
        if(!originalEmail.equals(TA.getEmail()))
            TA.setEmail(originalEmail);
        if(!originalType.equals(TA.getType())){
                TA.setType(originalType);                
                app.doTypeEdited(TA);
         }
    }
}
