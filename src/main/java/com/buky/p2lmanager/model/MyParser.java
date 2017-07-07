/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model;

/**
 *
 * @author bukov
 */
public class MyParser {
    
    private int i;
    private double d;
    private boolean b;
    private String input;
    private boolean exception;
    

    public MyParser(String input, double d) {
        this.input = input;
        this.d = d;
    }    

    public MyParser(String input, int i) {
        this.i = i;
        this.input = input;
    }

    public MyParser(String input, boolean b) {
        this.b = b;
        this.input = input;
    }

    public boolean isException() {
        return exception;
    }
    
    public double parseDouble(){
        try{
            exception = false;
            return Double.parseDouble(input);
        }
        catch(Exception e){
            exception = true;
            return d;
        }
    }
    
    public int parseInt(){
        try{
            exception = false;
            return Integer.parseInt(input);
        }
        catch(Exception e){
            exception = true;
            return i;
        }
    }
    
    public boolean parseBoolean(){
        try{
            exception = false;
            return Boolean.parseBoolean(input);
        }
        catch(Exception e){
            exception = true;
            return b;
        }
    }
}
