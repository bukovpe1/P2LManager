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
public class P2LValue {
    
    private int id;
    private int idUnit;
    private int value;

    public P2LValue(int id, int idUnit, int value) {
        this.id = id;
        this.idUnit = idUnit;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUnit() {
        return idUnit;
    }

    public void setIdUnit(int idUnit) {
        this.idUnit = idUnit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}
