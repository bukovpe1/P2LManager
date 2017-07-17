/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.blinking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

/**
 *
 * @author bukov
 */
public class BlinkGraphValues {
    
    private double delay1, tOff1, tOn1, delay2, tOff2, tOn2;
    private int rep1, rep2;
    private boolean startWithOn1, infRep1, startWithOn2, infRep2;
    
    private ArrayList<double[]> g1On, g1Off, g2On, g2Off, gOnOn, gOnOff, gOffOn, gOffOff; 
    
    private ArrayList<Double> timeAxis;
    private TreeMap<Double, Integer> seq1, seq2;
    
    private int MAX_REP = 20;
    
    private double endTime;
    
    public BlinkGraphValues(double delay1, double tOff1, double tOn1, int rep1, boolean startWithOn1, boolean infRep1, double delay2, double tOff2, double tOn2, int rep2, boolean startWithOn2, boolean infRep2) {
        this.delay1 = delay1;
        this.tOff1 = tOff1;
        this.tOn1 = tOn1;
        this.rep1 = rep1;
        this.delay2 = delay2;
        this.tOff2 = tOff2;
        this.tOn2 = tOn2;
        this.rep2 = rep2;
        this.startWithOn1 = startWithOn1;
        this.startWithOn2 = startWithOn2;
        if(infRep1){this.rep1 = Integer.MAX_VALUE;}
        if(infRep2){this.rep2 = Integer.MAX_VALUE;}
        computeEndTime();
        initArrayLists();
        
        timeAxis = new ArrayList<>();
        seq1 = new TreeMap<>();
        seq2 = new TreeMap<>();
    }

    public double getEndTime() {
        return endTime;
    }

    public ArrayList<Double> getTimeAxis() {
        return timeAxis;
    }

    public void compute(){
        computeTimeAxis();
        computeGraphValues();
    }
    
    private void initArrayLists(){
        g1On = new ArrayList<>();
        g1Off = new ArrayList<>();
        g2On = new ArrayList<>();
        g2Off = new ArrayList<>();
        gOnOn = new ArrayList<>();
        gOnOff = new ArrayList<>();
        gOffOn = new ArrayList<>();
        gOffOff = new ArrayList<>();
    }
    
    private int checkInterval(Double t, TreeMap<Double, Integer> seq){
        if(seq.lowerKey(t) != null){
            return seq.lowerEntry(t).getValue();
        }
        return -1;
    }
    
    private ArrayList<Double> computeTimeSeparately(double delay, double tOff, double tOn, int rep, boolean startOn, int seqIndex){
        ArrayList<Double> ta = new ArrayList<>();
        ta.add(delay);
        if(seqIndex == 1){
            if(startOn){
                seq1.put(delay, 1);
            }
            else{
                seq1.put(delay, 0);
            }
        }
        if(seqIndex == 2){
            if(startOn){
                seq2.put(delay, 1);
            }
            else{
                seq2.put(delay, 0);
            }
        }
        for (int i = 1; i <= rep; i++) {
            double tNew = ta.get(ta.size()-1);
            if(startOn){
                // on
                if(i%2 == 0){
                    tNew = tNew + tOff;
                    if(seqIndex == 1){
                        seq1.put(tNew, 1);
                    }
                    else{
                        seq2.put(tNew, 1);
                    }
                }
                // off
                else{
                    tNew = tNew + tOn;
                    if(seqIndex == 1){
                        seq1.put(tNew, 0);
                    }
                    else{
                        seq2.put(tNew, 0);
                    }
                }
            }
            else{
                // off
                if(i%2 == 0){
                    tNew = tNew + tOn;
                    if(seqIndex == 1){
                        seq1.put(tNew, 0);
                    }
                    else{
                        seq2.put(tNew, 0);
                    }
                }
                // on
                else{
                    tNew = tNew + tOff;
                    if(seqIndex == 1){
                        seq1.put(tNew, 1);
                    }
                    else{
                        seq2.put(tNew, 1);
                    }
                }
            }
            
            
            if(tNew >= 100){
                break;
            }
            else{
                ta.add(tNew);
            }
            
        }
        
        // add last time - 100 s
        if(seqIndex == 1){
            ta.add(100.0);
            if(seq1.lastEntry().getValue() == 0){
                seq1.put(100.0, 1);
            }
            else{
                seq1.put(100.0, 0);
            }
        }
        if(seqIndex == 2){
            if(seq2.lastEntry().getValue() == 0){
                seq2.put(100.0, 1);
            }
            else{
                seq2.put(100.0, 0);
            }
        }
        
        
        return ta;
    }
    
    private void computeTimeAxis(){
        
        ArrayList<Double> ta1 = computeTimeSeparately(delay1, tOff1, tOn1, rep1, startWithOn1, 1);
        ArrayList<Double> ta2 = computeTimeSeparately(delay2, tOff2, tOn2, rep2, startWithOn2, 2);
        
        timeAxis.addAll(ta1);
        
        for(Double t : ta2){
            if(!timeAxis.contains(t)){
                timeAxis.add(t);
            }
        }
        Collections.sort(timeAxis);
    }
    
    private void computeGraphValues(){
        for (int i = 0; i < timeAxis.size()-1; i++) {
            double tStart = timeAxis.get(i);
            double tEnd = timeAxis.get(i+1);
            double[] a = new double[2];
            a[0] = tStart;
            a[1] = tEnd;
            
            if(seq1.containsKey(tStart) && seq2.containsKey(tStart)){
                int s1 = seq1.get(tStart);
                int s2 = seq2.get(tStart);
                addToList(a, s1, s2);
            }
            else if(seq1.containsKey(tStart) && !seq2.containsKey(tStart)){
                int s1 = seq1.get(tStart);
                int s2 = checkInterval(tStart , seq2);
                addToList(a, s1, s2);
            }
            else if(!seq1.containsKey(tStart) && seq2.containsKey(tStart)){
                int s1 = checkInterval(tStart , seq1);
                int s2 = seq2.get(tStart);
                addToList(a, s1, s2);
            }
        }
    }
    
    private void addToList(double[] a, int s1, int s2) {
        if(s1 == 0 && s2 == -1){g1Off.add(a);}
        else if(s1 == 0 && s2 == 0){gOffOff.add(a);}
        else if(s1 == 0 && s2 == 1){gOffOn.add(a);}
        else if(s1 == 1 && s2 == -1){g1On.add(a);}
        else if(s1 == 1 && s2 == 0){gOnOff.add(a);}
        else if(s1 == 1 && s2 == 1){gOnOn.add(a);}
        else if(s1 == -1 && s2 == 0){g2Off.add(a);}
        else if(s1 == -1 && s2 == 1){g2On.add(a);}
    }
    
    private void computeEndTime(){
        
//        int rep1temp = rep1;
//        if(rep1>MAX_REP){rep1temp = MAX_REP;}
//        double tEnd1 = delay1 + (tOn1 + tOff1)*rep1temp;
//        
//        int rep2temp = rep2;
//        if(rep2>MAX_REP){rep2temp = MAX_REP;}
//        double tEnd2 = delay2 + (tOn2 + tOff2)*rep2temp;
//        
//        if(tEnd1 > tEnd2){
//            endTime = tEnd1;
//        }
//        else{
//            endTime = tEnd2;
//        }
        endTime = 100;
        
    }
    
    public ArrayList<ArrayList> getGraphValues(){
        ArrayList<ArrayList> gv = new ArrayList<>();
        gv.add(g1Off);
        gv.add(g1On);
        gv.add(g2Off);
        gv.add(g2On);
        gv.add(gOnOn);
        gv.add(gOnOff);
        gv.add(gOffOn);
        gv.add(gOffOff);
        return gv;
    }
}
