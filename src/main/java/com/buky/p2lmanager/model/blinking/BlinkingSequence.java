/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.blinking;

/**
 *
 * @author bukov
 */
public class BlinkingSequence {
    
    private double delay;
    private double tOff;
    private double tOn;
    private int rep;
    private boolean startWithOn;
    private BlinkingColor colorOff;
    private BlinkingColor colorOn;

    public BlinkingSequence(double delay, double tOff, double tOn, int rep, boolean startWithOn, BlinkingColor colorOff, BlinkingColor colorOn) {
        this.delay = delay;
        this.tOff = tOff;
        this.tOn = tOn;
        this.rep = rep;
        this.startWithOn = startWithOn;
        this.colorOff = colorOff;
        this.colorOn = colorOn;
    }    

    public boolean isStartWithOn() {
        return startWithOn;
    }

    public void setStartWithOn(boolean startWithOn) {
        this.startWithOn = startWithOn;
    }
    
    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        this.delay = delay;
    }

    public double gettOff() {
        return tOff;
    }

    public void settOff(double tOff) {
        this.tOff = tOff;
    }

    public double gettOn() {
        return tOn;
    }

    public void settOn(double tOn) {
        this.tOn = tOn;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public BlinkingColor getColorOff() {
        return colorOff;
    }

    public void setColorOff(BlinkingColor colorOff) {
        this.colorOff = colorOff;
    }

    public BlinkingColor getColorOn() {
        return colorOn;
    }

    public void setColorOn(BlinkingColor colorOn) {
        this.colorOn = colorOn;
    }

    @Override
    public String toString() {
        return "BlinkingSequence{" + "delay=" + delay + ", tOff=" + tOff + ", tOn=" + tOn + ", rep=" + rep + ", startWithOn=" + startWithOn + ", colorOff=" + colorOff + ", colorOn=" + colorOn + '}';
    }

    

        
}
