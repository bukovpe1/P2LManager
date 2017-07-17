/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.controller;

import com.buky.p2lmanager.model.blinking.BlinkingProgram;
import com.buky.p2lmanager.model.conditions.Condition;
import java.net.URL;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author bukov
 */
public class SimulatorController implements Initializable {

    private boolean run = true;
    private int programID = 1;
    private HashMap<Integer, Condition> conditions;
    private HashMap<Integer, BlinkingProgram> programs;
    
    @FXML private Rectangle rectSignalization;
    @FXML private ChoiceBox choiceBoxStates;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
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

    private void setChoiceBox(){
        for (int i = 1; i <= 8; i++) {
            choiceBoxStates.getItems().add("Stav " + i);
        }
        choiceBoxStates.getSelectionModel().select(0);
    }
    
    private void addChoiceBoxListener(){
        choiceBoxStates.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                programID = newValue.intValue()+1;
            }
        });
    }
    
    public void setConditions(HashMap<Integer, Condition> conditions) {
        this.conditions = conditions;
    }

    public void setPrograms(HashMap<Integer, BlinkingProgram> programs) {
        this.programs = programs;
    }
        
    private Color getColor(double time){
        
        
        
        return Color.ALICEBLUE;
    }
    
    private class ColorChanger  extends Observable implements Runnable{
        
        public void startChangingColor(){
            Thread t = new Thread(this);
            t.start();
        }
        
        @Override
        public void run() {
            Random rnd = new Random();
            int i = 0;
            double time;
            while(run){
                time = (double) i * 50 / 1000;
                
                getColor(time);
                
                rectSignalization.setFill(Color.color(rnd.nextDouble(), rnd.nextDouble(), rnd.nextDouble()));
                i++;
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulatorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
