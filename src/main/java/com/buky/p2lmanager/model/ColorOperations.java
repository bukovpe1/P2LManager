/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model;

import javafx.scene.paint.Color;

/**
 *
 * @author bukov
 */
public class ColorOperations {
    
    public static Color correction(Color c){
        
        double op = c.getRed();
        if(op < c.getGreen()){
            op = c.getGreen();
        }
        if(op < c.getBlue()){
            op = c.getBlue();
        }
        
        double r = c.getRed();
        double g = c.getGreen();
        double b = c.getBlue();
        
        double gamma = 0.35;
        
        // double offset; // TODO
        
        return Color.color(Math.pow(r,gamma), Math.pow(g,gamma), Math.pow(b,gamma), op*0.85+0.15);
    }
    
}
