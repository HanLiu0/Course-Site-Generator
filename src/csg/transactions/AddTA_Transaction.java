package csg.transactions;

import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class AddTA_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    TeachingAssistantPrototype ta;
    ObservableList<TeachingAssistantPrototype> taList;
    
    public AddTA_Transaction(CourseSiteGeneratorData initData, TeachingAssistantPrototype initTA, ObservableList<TeachingAssistantPrototype> taList) {
        data = initData;
        ta = initTA;
        this.taList = taList;
    }

    @Override
    public void doTransaction() {
        taList.add(ta);        
        taList.sort((TeachingAssistantPrototype o1, TeachingAssistantPrototype o2) -> o1.getName().compareTo(o2.getName()));
    }

    @Override
    public void undoTransaction() {
        taList.remove(ta);
    }
}
