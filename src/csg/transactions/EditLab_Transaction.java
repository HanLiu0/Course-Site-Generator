/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.LabItem;
import csg.data.RecitationItem;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditLab_Transaction implements jTPS_Transaction {
    TableView<RecitationItem> labTable;
    LabItem lab;
    String newData;
    String oldData;
    int index;
    
    public EditLab_Transaction(TableView<RecitationItem> labTable, LabItem lab, String newData, String oldData, int index) {
        this.labTable = labTable;
        this.lab = lab;
        this.newData = newData;
        this.oldData = oldData;
        this.index = index;
    }

    @Override
    public void doTransaction() {
        if(index == 0){
            lab.setSection(newData);
        }
        else if(index ==1)
            lab.setDaysAndTime(newData);
        else if(index ==2)
            lab.setRoom(newData);
        else if(index ==3)
            lab.setTa1(newData);
        else if(index ==4)
            lab.setTa2(newData);
        labTable.refresh();
    }

    @Override
    public void undoTransaction() {
        if(index == 0){
            lab.setSection(oldData);
        }
        else if(index ==1)
            lab.setDaysAndTime(oldData);
        else if(index ==2)
            lab.setRoom(oldData);
        else if(index ==3)
            lab.setTa1(oldData);
        else if(index ==4)
            lab.setTa2(oldData);
        labTable.refresh();
    }
}
