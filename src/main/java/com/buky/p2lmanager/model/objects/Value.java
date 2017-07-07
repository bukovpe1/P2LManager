/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.objects;

/**
 *
 * @author bukov
 */
public class Value {
    
    private int id;
    private int name;
    private int abbreviation;

    public Value(int id, int name, int abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(int abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    
    
}
