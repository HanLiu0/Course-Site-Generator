/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.RecitationItem;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class RemoveRecitation_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    RecitationItem recitation;
    
    public RemoveRecitation_Transaction(CourseSiteGeneratorData data, RecitationItem recitation) {
        this.data = data;
        this.recitation = recitation;
    }

    @Override
    public void doTransaction() {
        data.removeRecitation(recitation);
    }

    @Override
    public void undoTransaction() {
        data.addRecitation(recitation);
    }
    
}
