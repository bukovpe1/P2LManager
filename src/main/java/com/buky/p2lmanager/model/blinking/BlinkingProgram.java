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
public class BlinkingProgram {
    
    private int id;
    private boolean isSelected;
    private BlinkingSequence sequence1;
    private BlinkingSequence sequence2;

    public BlinkingProgram(int id, boolean isSelected, BlinkingSequence sequence1, BlinkingSequence sequence2) {
        this.id = id;
        this.isSelected = isSelected;
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public BlinkingSequence getSequence1() {
        return sequence1;
    }

    public void setSequence1(BlinkingSequence sequence1) {
        this.sequence1 = sequence1;
    }

    public BlinkingSequence getSequence2() {
        return sequence2;
    }

    public void setSequence2(BlinkingSequence sequence2) {
        this.sequence2 = sequence2;
    }

    @Override
    public String toString() {
        return "BlinkingProgram{" + "id=" + id + ", isSelected=" + isSelected + ", sequence1=" + sequence1.toString() + ", sequence2=" + sequence2.toString() + '}';
    }
    
    
    
}
