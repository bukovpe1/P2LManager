/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.conditions;

/**
 *
 * @author bukov
 */
public class Condition {
    
    private int id;
    private int nextState;
    private boolean relativeValue;
    private int edge;
    private int ledSegmentEvent;

    public Condition(int id, int nextState, boolean relativeValue, int edge, int ledSegmentEvent) {
        this.id = id;
        this.nextState = nextState;
        this.relativeValue = relativeValue;
        this.edge = edge;
        this.ledSegmentEvent = ledSegmentEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getNextState() {
        return nextState;
    }

    public void setNextState(int nextState) {
        this.nextState = nextState;
    }

    public boolean isRelativeValue() {
        return relativeValue;
    }

    public void setRelativeValue(boolean relativeValue) {
        this.relativeValue = relativeValue;
    }

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }

    public int getLedSegmentEvent() {
        return ledSegmentEvent;
    }

    public void setLedSegmentEvent(int ledSegmentEvent) {
        this.ledSegmentEvent = ledSegmentEvent;
    }
    
    
    
}
