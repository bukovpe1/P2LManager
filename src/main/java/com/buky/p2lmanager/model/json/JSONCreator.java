/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.json;

import java.util.Arrays;
import org.json.*;

/**
 *
 * @author bukov
 */
public class JSONCreator {
        
    private JSONObject obj;

    public JSONCreator() {
        obj = new JSONObject();
    }
    
    public void addToJSON(String name, int i){
        obj.put(name, i);
    }
    
    public void addToJSON(String name, String s){
        obj.put(name, s);
    }
    
    public void addToJSON(String name, int[] intArray){
        obj.put(name, Arrays.toString(intArray));
    }

    public String getJSONString(){
        return obj.toString();
    }
    
}
