/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.RecitationItem;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditRecitation_Transaction implements jTPS_Transaction {
    TableView<RecitationItem> recTable;
    RecitationItem recitation;
    String newData;
    String oldData;
    int index;
    
    public EditRecitation_Transaction(TableView<RecitationItem> recTable, RecitationItem recitation, String newData, String oldData, int index) {
        this.recTable = recTable;
        this.recitation = recitation;
        this.newData = newData;
        this.oldData = oldData;
        this.index = index;
    }

    @Override
    public void doTransaction() {
        if(index == 0){
            recitation.setSection(newData);
        }
        else if(index ==1)
            recitation.setDaysAndTime(newData);
        else if(index ==2)
            recitation.setRoom(newData);
        else if(index ==3)
            recitation.setTa1(newData);
        else if(index ==4)
            recitation.setTa2(newData);
        recTable.refresh();
    }

    @Override
    public void undoTransaction() {
        if(index == 0){
            recitation.setSection(oldData);
        }
        else if(index ==1)
            recitation.setDaysAndTime(oldData);
        else if(index ==2)
            recitation.setRoom(oldData);
        else if(index ==3)
            recitation.setTa1(oldData);
        else if(index ==4)
            recitation.setTa2(oldData);
        recTable.refresh();
    }
}
