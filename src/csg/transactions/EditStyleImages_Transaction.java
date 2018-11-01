/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jtps.jTPS_Transaction;

/**
 *
 * @author hanli
 */
public class EditStyleImages_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    ImageView imageView;
    Image newImage;
    Image oldImage;
    String path;
    String oldPath;
    int index;
    
    public EditStyleImages_Transaction(CourseSiteGeneratorData data, ImageView imageView, String path, int index){
        this.data = data;
        this.index = index;
        this.imageView = imageView;
        oldImage = imageView.getImage();
        newImage = new Image("file:" + path);
        this.path = path;
        oldPath = data.getStyleImages(index);
    }
            
    public void doTransaction(){
        data.setStyleImages(path, index);
        imageView.setImage(newImage);
    }
    
    public void undoTransaction(){
        data.setStyleImages(oldPath, index);
        imageView.setImage(oldImage);
    }    
}
