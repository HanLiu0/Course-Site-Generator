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
public class AddRecitation_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    RecitationItem recitation;
    
    public AddRecitation_Transaction(CourseSiteGeneratorData data) {
        this.data = data;
        recitation = new RecitationItem("?", "?", "?", "?", "?");
    }

    @Override
    public void doTransaction() {
        data.addRecitation(recitation);
    }

    @Override
    public void undoTransaction() {
        data.removeRecitation(recitation);
    }
    
}
