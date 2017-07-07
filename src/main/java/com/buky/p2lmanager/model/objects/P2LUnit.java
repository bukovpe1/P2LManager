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
public class P2LUnit {
    
    private int id;
    private int idChannel;
    private String name;
    private int idType;

    public P2LUnit(int id, int idChannel, String name, int idType) {
        this.id = id;
        this.idChannel = idChannel;
        this.name = name;
        this.idType = idType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChannel() {
        return idChannel;
    }

    public void setIdChannel(int idChannel) {
        this.idChannel = idChannel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

}
