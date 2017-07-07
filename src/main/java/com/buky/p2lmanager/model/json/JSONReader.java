/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.json;

import com.buky.p2lmanager.model.MyParser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author bukov
 */
public class JSONReader {
    
    private JSONObject obj;

    public JSONReader(String inputString) {
        obj = new JSONObject(inputString);
    }
    
    public int getInteger(String name){
        return new MyParser(obj.getString(name),0).parseInt();
    }
    
    public int[] getIntegerArray(String name){
        JSONArray arr = obj.getJSONArray(name);
        int[] intArr = new int[arr.length()-1];
        for (int i = 0; i < arr.length(); i++) {
            intArr[i] = new MyParser(arr.getString(i),0).parseInt();
        }
        return intArr;
    }
}
