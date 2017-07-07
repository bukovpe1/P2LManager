/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.controller;

import java.net.URL;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author bukov
 */
public class SimulatorController implements Initializable {

    private boolean run = true;
    @FXML private Rectangle rectSignalization;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    private void changeColor(){
        
    }
    
    @FXML
    private void startSimulation(ActionEvent event) {
        run = true;
        ColorChanger changer = new ColorChanger();
        changer.startChangingColor();
    }
    
    @FXML
    private void stopSimulation(ActionEvent event) {
        run = false;
    }
    
    private class ColorChanger  extends Observable implements Runnable{
        
        public void startChangingColor(){
            Thread t = new Thread(this);
            t.start();
        }
        
        @Override
        public void run() {
            Random rnd = new Random();
            while(run){
                rectSignalization.setFill(Color.color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble()));
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulatorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
