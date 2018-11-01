/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditBanner_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    Node node;
    Label exportDirLbl;
    String oldDir;
    int index;
    String bannerText;
    String oldBannerText;
    
    public EditBanner_Transaction(CourseSiteGeneratorData data, Node node, Label exportDirLbl,
         int index, String bannerText){
        this.data = data;
        this.node = node;
        this.exportDirLbl = exportDirLbl;
        this.oldDir = exportDirLbl.getText();
        this.index = index;
        this.bannerText = bannerText;
        this.oldBannerText = data.getBannerText(index);
    }
            
    public void doTransaction(){
        data.setBannerText(index, bannerText);
        data.updateExportDir();
        if(data.getExportDir() != "")
            exportDirLbl.setText(data.getExportDir());
        else
            exportDirLbl.setText(oldDir);            
        data.setTriggerListener(false);
        if(index <4){
            ((ComboBox)node).setValue(bannerText);
            ((ComboBox)node).getEditor().setText(bannerText);
        }
        else
            ((TextField)node).setText(bannerText);      
        data.setTriggerListener(true);
    }
    
    public void undoTransaction(){
        data.setBannerText(index, oldBannerText);
        data.updateExportDir();
        if(data.getExportDir() != "")
            exportDirLbl.setText(data.getExportDir());
        else
            exportDirLbl.setText(oldDir);            
        data.setTriggerListener(false);
        if(index <4){
            ((ComboBox)node).setValue(oldBannerText);
            ((ComboBox)node).getEditor().setText(oldBannerText);
        }
        else
            ((TextField)node).setText(oldBannerText);            
        data.setTriggerListener(true);
    }
    
    public int getIndex(){
        return index;
    }
    
    public void updateTransaction(String newText){
        bannerText = newText;
        doTransaction();
    }
}
