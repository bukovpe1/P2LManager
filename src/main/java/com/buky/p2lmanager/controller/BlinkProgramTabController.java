/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.controller;

import com.buky.p2lmanager.model.ColorCorrection;
import com.buky.p2lmanager.model.MyParser;
import com.buky.p2lmanager.model.blinking.BlinkingProgram;
import com.buky.p2lmanager.view.BlinkProgramWIndow;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author bukov
 */
public class BlinkProgramTabController implements Initializable {
    
    @FXML private Label labelSeq1_1, labelDelay1_1, labelTOff1_1, labelTOn1_1, labelStartOn1_1, labelRep1_1,
                        labelSeq2_1, labelDelay2_1, labelTOff2_1, labelTOn2_1, labelStartOn2_1, labelRep2_1,
                        labelSeq1_2, labelDelay1_2, labelTOff1_2, labelTOn1_2, labelStartOn1_2, labelRep1_2,
                        labelSeq2_2, labelDelay2_2, labelTOff2_2, labelTOn2_2, labelStartOn2_2, labelRep2_2,
                        labelSeq1_3, labelDelay1_3, labelTOff1_3, labelTOn1_3, labelStartOn1_3, labelRep1_3,
                        labelSeq2_3, labelDelay2_3, labelTOff2_3, labelTOn2_3, labelStartOn2_3, labelRep2_3,
                        labelSeq1_4, labelDelay1_4, labelTOff1_4, labelTOn1_4, labelStartOn1_4, labelRep1_4,
                        labelSeq2_4, labelDelay2_4, labelTOff2_4, labelTOn2_4, labelStartOn2_4, labelRep2_4,
                        labelSeq1_5, labelDelay1_5, labelTOff1_5, labelTOn1_5, labelStartOn1_5, labelRep1_5,
                        labelSeq2_5, labelDelay2_5, labelTOff2_5, labelTOn2_5, labelStartOn2_5, labelRep2_5,
                        labelSeq1_6, labelDelay1_6, labelTOff1_6, labelTOn1_6, labelStartOn1_6, labelRep1_6,
                        labelSeq2_6, labelDelay2_6, labelTOff2_6, labelTOn2_6, labelStartOn2_6, labelRep2_6,
                        labelSeq1_7, labelDelay1_7, labelTOff1_7, labelTOn1_7, labelStartOn1_7, labelRep1_7,
                        labelSeq2_7, labelDelay2_7, labelTOff2_7, labelTOn2_7, labelStartOn2_7, labelRep2_7,
                        labelSeq1_8, labelDelay1_8, labelTOff1_8, labelTOn1_8, labelStartOn1_8, labelRep1_8,
                        labelSeq2_8, labelDelay2_8, labelTOff2_8, labelTOn2_8, labelStartOn2_8, labelRep2_8;
    @FXML private CheckBox checkBoxState1, checkBoxState2, checkBoxState3, checkBoxState4, checkBoxState5, checkBoxState6, checkBoxState7, checkBoxState8;
    @FXML private Rectangle rectOff1_1, rectOn1_1, rectOff2_1, rectOn2_1,
                            rectOff1_2, rectOn1_2, rectOff2_2, rectOn2_2,
                            rectOff1_3, rectOn1_3, rectOff2_3, rectOn2_3,
                            rectOff1_4, rectOn1_4, rectOff2_4, rectOn2_4,
                            rectOff1_5, rectOn1_5, rectOff2_5, rectOn2_5,
                            rectOff1_6, rectOn1_6, rectOff2_6, rectOn2_6,
                            rectOff1_7, rectOn1_7, rectOff2_7, rectOn2_7,
                            rectOff1_8, rectOn1_8, rectOff2_8, rectOn2_8;
    
    private HashMap<Integer, HashMap<Integer, Label>> labels = new HashMap<>();    
    private HashMap<Integer, HashMap<Integer, Rectangle>> rectangles = new HashMap<>();
    private HashMap<Integer, CheckBox> checkBoxes = new HashMap<>();
    
    private HashMap<Integer, BlinkingProgram> blinkingPrograms = new HashMap<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addLabelsToMap();
        addRectanglesToMap();
        addCheckBoxesToMap();
        
    }    
    
    @FXML
    private void editBlinkProgram(ActionEvent event) {
        
        BlinkProgramWIndow bpw = new BlinkProgramWIndow(blinkingPrograms);
        bpw.display();
        blinkingPrograms.clear();
        blinkingPrograms.putAll(bpw.getBlinkingPrograms());
//        System.out.println("------");
//        for (int i = 1; i <= 8; i++) {
//            System.out.println(blinkingPrograms.get(i).toString());
//        }
        actualize();
        
    }
    
    @FXML
    private void writeBlinkProgram(ActionEvent event) {
        
    }
    
    @FXML
    private void readBlinkProgram(ActionEvent event) {
        
    }
    
    @FXML
    private void saveBlinkProgram(ActionEvent event) {
        
    }
    
    @FXML
    private void loadBlinkProgram(ActionEvent event) {
        
    }
    
    @FXML
    private void changeSelected(ActionEvent event){
        CheckBox cb = (CheckBox)event.getSource();
        String name = cb.getId();
        String idString = name.substring(name.length()-1);
        int idCb = new MyParser(idString, 0).parseInt();
        Boolean isSelected = cb.isSelected();
        changeTextColor(idCb, isSelected);
        actualizeRectangleColor(idCb, isSelected);
    }

    public void actualize(){
        actualizeLabelText();
        actualizeCheckBoxes();
    }
    
    public HashMap<Integer, BlinkingProgram> getBlinkingPrograms() {
        return blinkingPrograms;
    }

    public void setBlinkingPrograms(HashMap<Integer, BlinkingProgram> blinkingPrograms) {
        this.blinkingPrograms = blinkingPrograms;
    }
    
    private void actualizeLabelText(){
        
        for(BlinkingProgram program : blinkingPrograms.values()){
            HashMap<Integer, Label> labelToChange = labels.get(program.getId());
            labelToChange.get(1).setText(program.getSequence1().getDelay()+"");
            labelToChange.get(2).setText(program.getSequence1().gettOff()+"");
            labelToChange.get(3).setText(program.getSequence1().gettOn()+"");
            labelToChange.get(4).setText(program.getSequence1().isStartWithOn()+"");
            labelToChange.get(5).setText(program.getSequence1().getRep()+"");
            labelToChange.get(7).setText(program.getSequence2().getDelay()+"");
            labelToChange.get(8).setText(program.getSequence2().gettOff()+"");
            labelToChange.get(9).setText(program.getSequence2().gettOn()+"");
            labelToChange.get(10).setText(program.getSequence2().isStartWithOn()+"");
            labelToChange.get(11).setText(program.getSequence2().getRep()+"");
        }
        
    }

    private void actualizeCheckBoxes(){
        for(Map.Entry<Integer, CheckBox> checkBoxMap : checkBoxes.entrySet()){
            int id = checkBoxMap.getKey();
            CheckBox cb = checkBoxMap.getValue();
            boolean isSelected = blinkingPrograms.get(id).isIsSelected();
            changeTextColor(id, isSelected);
            actualizeRectangleColor(id, isSelected);
            cb.selectedProperty().setValue(isSelected);
        }
    }
    
    private void changeTextColor(int id, boolean isSelected){
        HashMap<Integer, Label> labelsTemp = labels.get(id);
        for(Label l : labelsTemp.values()){
            if(isSelected){
                l.setTextFill(Color.BLACK);
            }
            else{
                l.setTextFill(Color.web("#9b9b9b"));
            }
        }
    }
    
    private void actualizeRectangleColor(int id, Boolean isSelected) {
        HashMap<Integer, Rectangle> rectangleTemp = rectangles.get(id);
        Rectangle rectangle1 = rectangleTemp.get(1);
        Rectangle rectangle2 = rectangleTemp.get(2);
        Rectangle rectangle3 = rectangleTemp.get(3);
        Rectangle rectangle4 = rectangleTemp.get(4);
//        double opacity1 = 1;
//        double opacity2 = 1;
//        double opacity3 = 1;
//        double opacity4 = 1;
//        
//        if(!isSelected){
//            
//            
//            
//            opacity1 = 0.20;
//            opacity2 = 0.20;
//            opacity3 = 0.20;
//            opacity4 = 0.20;
//        }
        
        rectangle1.setFill(ColorCorrection.correction(Color.rgb(
                blinkingPrograms.get(id).getSequence1().getColorOff().getRed(), 
                blinkingPrograms.get(id).getSequence1().getColorOff().getGreen(), 
                blinkingPrograms.get(id).getSequence1().getColorOff().getBlue())));
        rectangle2.setFill(ColorCorrection.correction(Color.rgb(
                blinkingPrograms.get(id).getSequence1().getColorOn().getRed(), 
                blinkingPrograms.get(id).getSequence1().getColorOn().getGreen(), 
                blinkingPrograms.get(id).getSequence1().getColorOn().getBlue())));
        rectangle3.setFill(ColorCorrection.correction(Color.rgb(
                blinkingPrograms.get(id).getSequence2().getColorOff().getRed(), 
                blinkingPrograms.get(id).getSequence2().getColorOff().getGreen(), 
                blinkingPrograms.get(id).getSequence2().getColorOff().getBlue())));
        rectangle4.setFill(ColorCorrection.correction(Color.rgb(
                blinkingPrograms.get(id).getSequence2().getColorOn().getRed(), 
                blinkingPrograms.get(id).getSequence2().getColorOn().getGreen(), 
                blinkingPrograms.get(id).getSequence2().getColorOn().getBlue())));
        if(!isSelected){
            rectangle1.setOpacity(0.25);
            rectangle2.setOpacity(0.25);
            rectangle3.setOpacity(0.25);
            rectangle4.setOpacity(0.25);
        }
        else{
            rectangle1.setOpacity(0.75);
            rectangle2.setOpacity(0.75);
            rectangle3.setOpacity(0.75);
            rectangle4.setOpacity(0.75);
        }
    }
 
    private void addRectanglesToMap(){
        HashMap<Integer, Rectangle> rectangles1 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles2 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles3 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles4 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles5 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles6 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles7 = new HashMap<>();
        HashMap<Integer, Rectangle> rectangles8 = new HashMap<>();
        
        rectangles1.put(1, rectOff1_1);
        rectangles1.put(2, rectOn1_1);
        rectangles1.put(3, rectOff2_1);
        rectangles1.put(4, rectOn2_1);
        
        rectangles2.put(1, rectOff1_2);
        rectangles2.put(2, rectOn1_2);
        rectangles2.put(3, rectOff2_2);
        rectangles2.put(4, rectOn2_2);
        
        rectangles3.put(1, rectOff1_3);
        rectangles3.put(2, rectOn1_3);
        rectangles3.put(3, rectOff2_3);
        rectangles3.put(4, rectOn2_3);
        
        rectangles4.put(1, rectOff1_4);
        rectangles4.put(2, rectOn1_4);
        rectangles4.put(3, rectOff2_4);
        rectangles4.put(4, rectOn2_4);
        
        rectangles5.put(1, rectOff1_5);
        rectangles5.put(2, rectOn1_5);
        rectangles5.put(3, rectOff2_5);
        rectangles5.put(4, rectOn2_5);
        
        rectangles6.put(1, rectOff1_6);
        rectangles6.put(2, rectOn1_6);
        rectangles6.put(3, rectOff2_6);
        rectangles6.put(4, rectOn2_6);
        
        rectangles7.put(1, rectOff1_7);
        rectangles7.put(2, rectOn1_7);
        rectangles7.put(3, rectOff2_7);
        rectangles7.put(4, rectOn2_7);
       
        rectangles8.put(1, rectOff1_8);
        rectangles8.put(2, rectOn1_8);
        rectangles8.put(3, rectOff2_8);
        rectangles8.put(4, rectOn2_8);
       
        rectangles.put(1, rectangles1);
        rectangles.put(2, rectangles2);
        rectangles.put(3, rectangles3);
        rectangles.put(4, rectangles4);
        rectangles.put(5, rectangles5);
        rectangles.put(6, rectangles6);
        rectangles.put(7, rectangles7);
        rectangles.put(8, rectangles8);
    }
    
    private void addLabelsToMap(){
        HashMap<Integer, Label> labels1 = new HashMap<>();
        HashMap<Integer, Label> labels2 = new HashMap<>();
        HashMap<Integer, Label> labels3 = new HashMap<>();
        HashMap<Integer, Label> labels4 = new HashMap<>();
        HashMap<Integer, Label> labels5 = new HashMap<>();
        HashMap<Integer, Label> labels6 = new HashMap<>();
        HashMap<Integer, Label> labels7 = new HashMap<>();
        HashMap<Integer, Label> labels8 = new HashMap<>();
        
        labels1.put(0,labelSeq1_1);
        labels1.put(1,labelDelay1_1);
        labels1.put(2,labelTOff1_1);
        labels1.put(3,labelTOn1_1);
        labels1.put(4,labelStartOn1_1);
        labels1.put(5,labelRep1_1);
        labels1.put(6,labelSeq2_1);
        labels1.put(7,labelDelay2_1);
        labels1.put(8,labelTOff2_1);
        labels1.put(9,labelTOn2_1);
        labels1.put(10,labelStartOn2_1);
        labels1.put(11,labelRep2_1);
        
        labels2.put(0,labelSeq1_2);
        labels2.put(1,labelDelay1_2);
        labels2.put(2,labelTOff1_2);
        labels2.put(3,labelTOn1_2);
        labels2.put(4,labelStartOn1_2);
        labels2.put(5,labelRep1_2);
        labels2.put(6,labelSeq2_2);
        labels2.put(7,labelDelay2_2);
        labels2.put(8,labelTOff2_2);
        labels2.put(9,labelTOn2_2);
        labels2.put(10,labelStartOn2_2);
        labels2.put(11,labelRep2_2);
        
        labels3.put(0,labelSeq1_3);
        labels3.put(1,labelDelay1_3);
        labels3.put(2,labelTOff1_3);
        labels3.put(3,labelTOn1_3);
        labels3.put(4,labelStartOn1_3);
        labels3.put(5,labelRep1_3);
        labels3.put(6,labelSeq2_3);
        labels3.put(7,labelDelay2_3);
        labels3.put(8,labelTOff2_3);
        labels3.put(9,labelTOn2_3);
        labels3.put(10,labelStartOn2_3);
        labels3.put(11,labelRep2_3);
       
        labels4.put(0,labelSeq1_4);
        labels4.put(1,labelDelay1_4);
        labels4.put(2,labelTOff1_4);
        labels4.put(3,labelTOn1_4);
        labels4.put(4,labelStartOn1_4);
        labels4.put(5,labelRep1_4);
        labels4.put(6,labelSeq2_4);
        labels4.put(7,labelDelay2_4);
        labels4.put(8,labelTOff2_4);
        labels4.put(9,labelTOn2_4);
        labels4.put(10,labelStartOn2_4);
        labels4.put(11,labelRep2_4);
       
        labels5.put(0,labelSeq1_5);
        labels5.put(1,labelDelay1_5);
        labels5.put(2,labelTOff1_5);
        labels5.put(3,labelTOn1_5);
        labels5.put(4,labelStartOn1_5);
        labels5.put(5,labelRep1_5);
        labels5.put(6,labelSeq2_5);
        labels5.put(7,labelDelay2_5);
        labels5.put(8,labelTOff2_5);
        labels5.put(9,labelTOn2_5);
        labels5.put(10,labelStartOn2_5);
        labels5.put(11,labelRep2_5);
        
        labels6.put(0,labelSeq1_6);
        labels6.put(1,labelDelay1_6);
        labels6.put(2,labelTOff1_6);
        labels6.put(3,labelTOn1_6);
        labels6.put(4,labelStartOn1_6);
        labels6.put(5,labelRep1_6);
        labels6.put(6,labelSeq2_6);
        labels6.put(7,labelDelay2_6);
        labels6.put(8,labelTOff2_6);
        labels6.put(9,labelTOn2_6);
        labels6.put(10,labelStartOn2_6);
        labels6.put(11,labelRep2_6);
        
        labels7.put(0,labelSeq1_7);
        labels7.put(1,labelDelay1_7);
        labels7.put(2,labelTOff1_7);
        labels7.put(3,labelTOn1_7);
        labels7.put(4,labelStartOn1_7);
        labels7.put(5,labelRep1_7);
        labels7.put(6,labelSeq2_7);
        labels7.put(7,labelDelay2_7);
        labels7.put(8,labelTOff2_7);
        labels7.put(9,labelTOn2_7);
        labels7.put(10,labelStartOn2_7);
        labels7.put(11,labelRep2_7);
        
        labels8.put(0,labelSeq1_8);
        labels8.put(1,labelDelay1_8);
        labels8.put(2,labelTOff1_8);
        labels8.put(3,labelTOn1_8);
        labels8.put(4,labelStartOn1_8);
        labels8.put(5,labelRep1_8);
        labels8.put(6,labelSeq2_8);
        labels8.put(7,labelDelay2_8);
        labels8.put(8,labelTOff2_8);
        labels8.put(9,labelTOn2_8);
        labels8.put(10,labelStartOn2_8);
        labels8.put(11,labelRep2_8);
     
        labels.put(1, labels1);
        labels.put(2, labels2);
        labels.put(3, labels3);
        labels.put(4, labels4);
        labels.put(5, labels5);
        labels.put(6, labels6);
        labels.put(7, labels7);
        labels.put(8, labels8);
    }

    private void addCheckBoxesToMap(){
        checkBoxes.put(1, checkBoxState1);
        checkBoxes.put(2, checkBoxState2);
        checkBoxes.put(3, checkBoxState3);
        checkBoxes.put(4, checkBoxState4);
        checkBoxes.put(5, checkBoxState5);
        checkBoxes.put(6, checkBoxState6);
        checkBoxes.put(7, checkBoxState7);
        checkBoxes.put(8, checkBoxState8);
    }
}
