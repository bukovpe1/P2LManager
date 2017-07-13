package com.buky.p2lmanager.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.buky.p2lmanager.model.conditions.Condition;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author bukov
 */
public class ConditionTabController implements Initializable {


    @FXML private ChoiceBox choiceBoxNextState1, choiceBoxNextState2, choiceBoxNextState3, choiceBoxNextState4, 
                            choiceBoxNextState5, choiceBoxNextState6, choiceBoxNextState7, choiceBoxNextState8,
                            choiceBoxEdge1, choiceBoxEdge2, choiceBoxEdge3, choiceBoxEdge4,
                            choiceBoxEdge5, choiceBoxEdge6, choiceBoxEdge7, choiceBoxEdge8,
                            choiceBoxLed1, choiceBoxLed2, choiceBoxLed3, choiceBoxLed4,
                            choiceBoxLed5, choiceBoxLed6, choiceBoxLed7, choiceBoxLed8;
    @FXML private CheckBox checkBoxRelativeVal1, checkBoxRelativeVal2, checkBoxRelativeVal3, checkBoxRelativeVal4,
                           checkBoxRelativeVal5, checkBoxRelativeVal6, checkBoxRelativeVal7, checkBoxRelativeVal8;
    
    private HashMap<Integer, HashMap<Integer, ChoiceBox>> choiceBoxes = new HashMap<>();
    private HashMap<Integer, CheckBox> checkBoxes = new HashMap<>();
    
    private HashMap<Integer, Condition> conditions = new HashMap<>();
    
    private boolean initDone = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addChoiceBoxesToMap();
        addCheckBoxesToMap();
        addValuesToChoiceBoxes();
        addListenersToChoiceBoxes();
        addListenersToCheckBoxes();
    }    

    public void actualize(){
        setChoiceBoxes();
        setCheckBoxes();
        initDone = true;
    }
    
    private void addListenersToCheckBoxes(){
        for(CheckBox cb : checkBoxes.values()){
            cb.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                setRelativeValues();
            });
        }
    }
    
    private void setRelativeValues(){
        if(initDone){
            for(Condition c : conditions.values()){
                c.setRelativeValue(checkBoxes.get(c.getId()).isSelected());
            }
        }
    }
    
    private void setCheckBoxes(){
        for(Condition c : conditions.values()){
            checkBoxes.get(c.getId()).setSelected(c.isRelativeValue());
        }
    }
    
    private void addChoiceBoxesToMap(){
        HashMap<Integer, ChoiceBox> cb1 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb2 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb3 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb4 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb5 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb6 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb7 = new HashMap<>();
        HashMap<Integer, ChoiceBox> cb8 = new HashMap<>();
        
        cb1.put(1, choiceBoxNextState1);
        cb1.put(2, choiceBoxEdge1);
        cb1.put(3, choiceBoxLed1);
       
        cb2.put(1, choiceBoxNextState2);
        cb2.put(2, choiceBoxEdge2);
        cb2.put(3, choiceBoxLed2);
        
        cb3.put(1, choiceBoxNextState3);
        cb3.put(2, choiceBoxEdge3);
        cb3.put(3, choiceBoxLed3);
        
        cb4.put(1, choiceBoxNextState4);
        cb4.put(2, choiceBoxEdge4);
        cb4.put(3, choiceBoxLed4);
        
        cb5.put(1, choiceBoxNextState5);
        cb5.put(2, choiceBoxEdge5);
        cb5.put(3, choiceBoxLed5);
        
        cb6.put(1, choiceBoxNextState6);
        cb6.put(2, choiceBoxEdge6);
        cb6.put(3, choiceBoxLed6);
        
        cb7.put(1, choiceBoxNextState7);
        cb7.put(2, choiceBoxEdge7);
        cb7.put(3, choiceBoxLed7);
        
        cb8.put(1, choiceBoxNextState8);
        cb8.put(2, choiceBoxEdge8);
        cb8.put(3, choiceBoxLed8);
        
        choiceBoxes.put(1, cb1);
        choiceBoxes.put(2, cb2);
        choiceBoxes.put(3, cb3);
        choiceBoxes.put(4, cb4);
        choiceBoxes.put(5, cb5);
        choiceBoxes.put(6, cb6);
        choiceBoxes.put(7, cb7);
        choiceBoxes.put(8, cb8);
    }
    
    private void addValuesToChoiceBoxes(){
        for(HashMap<Integer, ChoiceBox> cbs : choiceBoxes.values()){
            cbs.get(1).getItems().addAll(FXCollections.observableArrayList("Stav 1", "Stav 2", "Stav 3", "Stav 4", "Stav 5", "Stav 6", "Stav 7", "Stav 8"));
            cbs.get(2).getItems().addAll(FXCollections.observableArrayList("Žádná", "Vzestupná", "Sestupná", "Vzestupná nebo sestupná"));
            cbs.get(3).getItems().addAll(FXCollections.observableArrayList("Žádná", "První sekvence", "Druhá sekvence", "První nebo druhá sekvence"));
        }
    }
    
    private void addCheckBoxesToMap(){
        checkBoxes.put(1, checkBoxRelativeVal1);
        checkBoxes.put(2, checkBoxRelativeVal2);
        checkBoxes.put(3, checkBoxRelativeVal3);
        checkBoxes.put(4, checkBoxRelativeVal4);
        checkBoxes.put(5, checkBoxRelativeVal5);
        checkBoxes.put(6, checkBoxRelativeVal6);
        checkBoxes.put(7, checkBoxRelativeVal7);
        checkBoxes.put(8, checkBoxRelativeVal8);
    }
    
    private void addListenersToChoiceBoxes(){
        for(HashMap<Integer, ChoiceBox> cbs : choiceBoxes.values()){
            for(ChoiceBox cb : cbs.values()){
                cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        setConditionsValues();
                    }
                });
            }
        }
    }
    
    private void setChoiceBoxes(){
        for(Condition c : conditions.values()){
            choiceBoxes.get(c.getId()).get(1).getSelectionModel().select(c.getNextState()-1);
            choiceBoxes.get(c.getId()).get(2).getSelectionModel().select(c.getEdge()-1);
            choiceBoxes.get(c.getId()).get(3).getSelectionModel().select(c.getLedSegmentEvent()-1);
        }
    }
    
    private void setConditionsValues(){
        if(initDone){
            for(Condition c : conditions.values()){
                c.setNextState(choiceBoxes.get(c.getId()).get(1).getSelectionModel().getSelectedIndex());
                c.setEdge(choiceBoxes.get(c.getId()).get(2).getSelectionModel().getSelectedIndex());
                c.setLedSegmentEvent(choiceBoxes.get(c.getId()).get(3).getSelectionModel().getSelectedIndex());
            }
        }
    }
    
    public HashMap<Integer, Condition> getConditions() {
        return conditions;
    }

    public void setConditions(HashMap<Integer, Condition> conditions) {
        this.conditions = conditions;
    }
        
}
