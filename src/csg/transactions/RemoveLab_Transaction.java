/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.LabItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class RemoveLab_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    LabItem lab;
    
    public RemoveLab_Transaction(CourseSiteGeneratorData data, LabItem lab) {
        this.data = data;
        this.lab = lab;
    }

    @Override
    public void doTransaction() {
        data.removeLab(lab);
    }

    @Override
    public void undoTransaction() {
        data.addLab(lab);
    }
    
}
