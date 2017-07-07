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
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author bukov
 */
public class ConditionTabController implements Initializable {

    private HashMap<Integer, Condition> conditions = new HashMap<>();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void actualize(){
        
    }
    
    public HashMap<Integer, Condition> getConditions() {
        return conditions;
    }

    public void setConditions(HashMap<Integer, Condition> conditions) {
        this.conditions = conditions;
    }
    
    
    
}
