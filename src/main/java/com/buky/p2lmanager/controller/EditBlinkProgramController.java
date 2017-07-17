/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.controller;

import com.buky.p2lmanager.model.ColorOperations;
import com.buky.p2lmanager.model.blinking.BlinkGraphValues;
import com.buky.p2lmanager.model.blinking.BlinkingProgram;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

/**
 *
 * @author bukov
 */
public class EditBlinkProgramController implements Initializable{
    
    
    @FXML private Slider sliderROn1, sliderGOn1, sliderBOn1, sliderROn2, sliderGOn2, sliderBOn2, sliderROff1, sliderGOff1, sliderBOff1, sliderROff2, sliderGOff2, sliderBOff2;
    @FXML private Rectangle rectOn1, rectOn2, rectOff1, rectOff2;
    @FXML private Spinner spinnerDelay1, spinnerDelay2, spinnerTOff1, spinnerTOff2, spinnerTOn1, spinnerTOn2, spinnerRep1, spinnerRep2, spinnerTEnd;
    @FXML private AreaChart<Number, Number> blinkPlot;
    @FXML private CheckBox checkBoxStartOn1, checkBoxStartOn2, checkBoxInfRep1, checkBoxInfRep2;
    @FXML private ChoiceBox choiceBoxStates;
    @FXML private NumberAxis xAxis;
    
    private Color rgbOn1, rgbOn2, rgbOff1, rgbOff2;
    private Color rgbOnOn, rgbOnOff, rgbOffOn, rgbOffOff;
    private int r1on, g1on, b1on, r2on, g2on, b2on, r1off, g1off, b1off, r2off, g2off, b2off;

    private int rep1, rep2;    
    private double delay1, tOn1, tOff1, delay2, tOn2, tOff2; 
    private boolean startOn1, startOn2, infRep1, infRep2;
    
    private HashMap<Integer, BlinkingProgram> blinkingPrograms;
    
    private int programID = 1;
    
    boolean settingProgramDone = false;
    
    private int tEndGraph = 20;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    //    setSliderValues();
        addSliderListeners();
        addSpinnerListeners();
        spinnerExceptionHandler();
        addCheckBoxListeners();
        setChoiceBox();
        addChoiceBoxListener();
        xAxis.setAutoRanging(false);
        spinnerTEnd.getValueFactory().setValue(20);
//        initColors();

    }
    
    @FXML
    private void setRGBColor(ActionEvent event) {
        Button o = (Button)event.getSource();
        String name = o.getId();
        setSelectedColor(name);
    }
    
    @FXML
    private void actualizeGraph(ActionEvent event) {
        BlinkGraphValues bgv = new BlinkGraphValues(delay1, tOff1, tOn1, rep1, startOn1, infRep1, delay2, tOff2, tOn2, rep2, startOn2, infRep2);
        bgv.compute();
        ArrayList<ArrayList> graphValues = bgv.getGraphValues();
        drawGraph(graphValues);
    }

    public void actualizeProgram(){
        settingProgramDone = false;
        setSpinnerValues();
        setSliderValues();
        settingProgramDone = true;
        actualizeGraph();
    }
    
    private void setSpinnerValues(){
        spinnerDelay1.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence1().getDelay());
        spinnerTOff1.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence1().gettOff());
        spinnerTOn1.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence1().gettOn());
        spinnerRep1.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence1().getRep());
        spinnerDelay2.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence2().getDelay());
        spinnerTOff2.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence2().gettOff());
        spinnerTOn2.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence2().gettOn());
        spinnerRep2.getValueFactory().setValue(blinkingPrograms.get(programID).getSequence2().getRep());
    }
    
    private void setSliderValues(){
        sliderROff1.setValue(blinkingPrograms.get(programID).getSequence1().getColorOff().getRed()/17);
        sliderGOff1.setValue(blinkingPrograms.get(programID).getSequence1().getColorOff().getGreen()/17);
        sliderBOff1.setValue(blinkingPrograms.get(programID).getSequence1().getColorOff().getBlue()/17);
        
        sliderROn1.setValue(blinkingPrograms.get(programID).getSequence1().getColorOn().getRed()/17);
        sliderGOn1.setValue(blinkingPrograms.get(programID).getSequence1().getColorOn().getGreen()/17);
        sliderBOn1.setValue(blinkingPrograms.get(programID).getSequence1().getColorOn().getBlue()/17);
        
        sliderROff2.setValue(blinkingPrograms.get(programID).getSequence2().getColorOff().getRed()/17);
        sliderGOff2.setValue(blinkingPrograms.get(programID).getSequence2().getColorOff().getGreen()/17);
        sliderBOff2.setValue(blinkingPrograms.get(programID).getSequence2().getColorOff().getBlue()/17);
        
        sliderROn2.setValue(blinkingPrograms.get(programID).getSequence2().getColorOn().getRed()/17);
        sliderGOn2.setValue(blinkingPrograms.get(programID).getSequence2().getColorOn().getGreen()/17);
        sliderBOn2.setValue(blinkingPrograms.get(programID).getSequence2().getColorOn().getBlue()/17);
    }
    
    private void setChoiceBox(){
        for (int i = 1; i <= 8; i++) {
            choiceBoxStates.getItems().add("Stav " + i);
        }
        choiceBoxStates.getSelectionModel().select(0);
    }
    
    private void addChoiceBoxListener(){
        choiceBoxStates.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                programID = newValue.intValue()+1;
                actualizeProgram();
            }
        });
    }
    
    public HashMap<Integer, BlinkingProgram> getBlinkingPrograms() {
        return blinkingPrograms;
    }

    public void setBlinkingPrograms(HashMap<Integer, BlinkingProgram> blinkingPrograms) {
        this.blinkingPrograms = blinkingPrograms;
    }
    
    private void actualizeGraph(){
        if(settingProgramDone){
            BlinkGraphValues bgv = new BlinkGraphValues(delay1, tOff1, tOn1, rep1, startOn1, infRep1, delay2, tOff2, tOn2, rep2, startOn2, infRep2);
            bgv.compute();
            ArrayList<ArrayList> graphValues = bgv.getGraphValues();
            drawGraph(graphValues);
         //   tEndGraph = bgv.getEndTime()+0.01;
            setGraphLimits();
        }
        
//        tStartGraph = 0;
        
//        spinnerTStart.getValueFactory().setValue(tStartGraph);
//        spinnerTEnd.getValueFactory().setValue(tEndGraph);
//        
        
        
    }
    
    private void drawGraph(ArrayList<ArrayList> graphValuesList){
        
        blinkPlot.getData().clear();
        
//        int j = 0;
        for (ArrayList<double[]> graphValues : graphValuesList) {
//            System.out.println(j++);
            XYChart.Series<Number, Number> series= new XYChart.Series<>();
            for (double[] graphValue : graphValues) {
                series.getData().add(new XYChart.Data<>(graphValue[0], 0));
                series.getData().add(new XYChart.Data<>(graphValue[0], 1));
                series.getData().add(new XYChart.Data<>(graphValue[1], 1));
                series.getData().add(new XYChart.Data<>(graphValue[1], 0));
//                System.out.print(graphValue[0] + " " + graphValue[1] + " ; ");                
            } 
            blinkPlot.getData().add(series);
        }
//        System.out.println("");
        setGraphColors();
    }
    
    private void setGraphColors(){
        if(settingProgramDone){
            combineColors();
            ArrayList<Color> colorList = new ArrayList<>();
            colorList.add(ColorOperations.correction(rgbOff1));
            colorList.add(ColorOperations.correction(rgbOn1));
            colorList.add(ColorOperations.correction(rgbOff2));
            colorList.add(ColorOperations.correction(rgbOn2));
            colorList.add(ColorOperations.correction(rgbOnOn));
            colorList.add(ColorOperations.correction(rgbOnOff));
            colorList.add(ColorOperations.correction(rgbOffOn));
            colorList.add(ColorOperations.correction(rgbOffOff));
            
            for(int i=0; i < 8; i++){
                for(Node n : blinkPlot.lookupAll(".series"+i)){
                    n.setStyle("-fx-background-color: rgb(" + colorList.get(i).getRed()*255 + "," + colorList.get(i).getGreen()*255 + "," + colorList.get(i).getBlue()*255 + "), white; "
                            + "-fx-stroke: rgb(" + colorList.get(i).getRed()*255 + "," + colorList.get(i).getGreen()*255 + "," + colorList.get(i).getBlue()*255 + "); "
                            + "-fx-fill: rgba(" + colorList.get(i).getRed()*255 + "," + colorList.get(i).getGreen()*255 + "," + colorList.get(i).getBlue()*255 + "," + colorList.get(i).getOpacity() + "); ");
                }
            }   
        }
    }
 
    private void combineColors(){
        
        rgbOnOn = Color.rgb((r1on*17)|(r2on*17), (g1on*17)|(g2on*17), (b1on*17)|(b2on*17));
        rgbOnOff = Color.rgb((r1on*17)|(r2off*17), (g1on*17)|(g2off*17), (b1on*17)|(b2off*17));
        rgbOffOn = Color.rgb((r1off*17)|(r2on*17), (g1off*17)|(g2on*17), (b1off*17)|(b2on*17));
        rgbOffOff = Color.rgb((r1off*17)|(r2off*17), (g1off*17)|(g2off*17), (b1off*17)|(b2off*17));
       
    }
    
    private void setGraphLimits(){
        xAxis.setUpperBound(tEndGraph);
    }
    
    private void addCheckBoxListeners(){
        checkBoxStartOn1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            startOn1 = checkBoxStartOn1.isSelected();
            actualizeGraph();
        });
        checkBoxStartOn2.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            startOn2 = checkBoxStartOn2.isSelected();
            actualizeGraph();
        });
        checkBoxInfRep1.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            infRep1 = checkBoxInfRep1.isSelected();
            actualizeGraph();
        });
        checkBoxInfRep2.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
            infRep2 = checkBoxInfRep2.isSelected();
            actualizeGraph();
        });
    }
    
    private void setSelectedColor(String btn){
        
        Color rgbTemp = Color.rgb(0, 0, 0);
        
        if(btn.endsWith("R")){rgbTemp = Color.rgb(255, 0, 0);}
        else if(btn.endsWith("G")){rgbTemp = Color.rgb(0, 255, 0);}
        else if(btn.endsWith("B")){rgbTemp = Color.rgb(0, 0, 255);}
        else if(btn.endsWith("W")){rgbTemp = Color.rgb(255, 255, 255);}
        else if(btn.endsWith("Y")){rgbTemp = Color.rgb(255, 255, 0);}
        else if(btn.endsWith("M")){rgbTemp = Color.rgb(255, 0, 255);}
        else if(btn.endsWith("C")){rgbTemp = Color.rgb(0, 255, 255);}
        else if(btn.endsWith("Bk")){rgbTemp = Color.rgb(0, 0, 0);}
        
        if(btn.contains("1On")){
            rgbOn1 = rgbTemp;
            r1on = (int) rgbOn1.getRed() * 15;
            g1on = (int) rgbOn1.getGreen() * 15;
            b1on = (int) rgbOn1.getBlue() * 15;
            sliderROn1.setValue(r1on);
            sliderGOn1.setValue(g1on);
            sliderBOn1.setValue(b1on);
        }
        else if(btn.contains("2On")){
            rgbOn2 = rgbTemp;
            r2on = (int) rgbOn2.getRed() * 15;
            g2on = (int) rgbOn2.getGreen() * 15;
            b2on = (int) rgbOn2.getBlue() * 15;
            sliderROn2.setValue(r2on);
            sliderGOn2.setValue(g2on);
            sliderBOn2.setValue(b2on);
        }
        else if(btn.contains("1Off")){
            rgbOff1 = rgbTemp;
            r1off = (int) rgbOff1.getRed() * 15;
            g1off = (int) rgbOff1.getGreen() * 15;
            b1off = (int) rgbOff1.getBlue() * 15;
            sliderROff1.setValue(r1off);
            sliderGOff1.setValue(g1off);
            sliderBOff1.setValue(b1off);
        }
        else if(btn.contains("2Off")){
            rgbOff2 = rgbTemp;
            r2off = (int) rgbOff2.getRed() * 15;
            g2off = (int) rgbOff2.getGreen() * 15;
            b2off = (int) rgbOff2.getBlue() * 15;
            sliderROff2.setValue(r2off);
            sliderGOff2.setValue(g2off);
            sliderBOff2.setValue(b2off);
        }
    }
    
    private void addSliderListeners(){
        
        sliderROn1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                r1on = (int) sliderROn1.getValue();
                rgbOn1 = Color.rgb(r1on*17, g1on*17, b1on*17);
                rectOn1.setFill(ColorOperations.correction(rgbOn1));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence1().getColorOn().setRed(r1on*17);
            }
        });
        
        sliderGOn1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                g1on = (int) sliderGOn1.getValue();
                rgbOn1 = Color.rgb(r1on*17, g1on*17, b1on*17);
                rectOn1.setFill(ColorOperations.correction(rgbOn1));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence1().getColorOn().setGreen(g1on*17);
            }
        });
        
        sliderBOn1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                b1on = (int) sliderBOn1.getValue();
                rgbOn1 = Color.rgb(r1on*17, g1on*17, b1on*17);
                rectOn1.setFill(ColorOperations.correction(rgbOn1));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence1().getColorOn().setBlue(b1on*17);
            }
        });
        
        sliderROn2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                r2on = (int) sliderROn2.getValue();
                rgbOn2 = Color.rgb(r2on*17, g2on*17, b2on*17);
                rectOn2.setFill(ColorOperations.correction(rgbOn2));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence2().getColorOn().setRed(r2on*17);
            }
        });
        
        sliderGOn2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                g2on = (int) sliderGOn2.getValue();
                rgbOn2 = Color.rgb(r2on*17, g2on*17, b2on*17);
                rectOn2.setFill(ColorOperations.correction(rgbOn2));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence2().getColorOn().setGreen(g2on*17);
            }
        });
        
        sliderBOn2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                b2on = (int) sliderBOn2.getValue();
                rgbOn2 = Color.rgb(r2on*17, g2on*17, b2on*17);
                rectOn2.setFill(ColorOperations.correction(rgbOn2));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence2().getColorOn().setBlue(b2on*17);
            }
        });
        
        sliderROff1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                r1off = (int) sliderROff1.getValue();
                rgbOff1 = Color.rgb(r1off*17, g1off*17, b1off*17);
                rectOff1.setFill(ColorOperations.correction(rgbOff1));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence1().getColorOff().setRed(r1off*17);
            }
        });
        
        sliderGOff1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                g1off = (int) sliderGOff1.getValue();
                rgbOff1 = Color.rgb(r1off*17, g1off*17, b1off*17);
                rectOff1.setFill(ColorOperations.correction(rgbOff1));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence1().getColorOff().setGreen(g1off*17);
            }
        });
        
        sliderBOff1.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                b1off = (int) sliderBOff1.getValue();
                rgbOff1 = Color.rgb(r1off*17, g1off*17, b1off*17);
                rectOff1.setFill(ColorOperations.correction(rgbOff1));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence1().getColorOff().setBlue(b1off*17);
            }
        });
        
        sliderROff2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                r2off = (int) sliderROff2.getValue();
                rgbOff2 = Color.rgb(r2off*17, g2off*17, b2off*17);
                rectOff2.setFill(ColorOperations.correction(rgbOff2));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence2().getColorOff().setRed(r2off*17);
            }
        });
        
        sliderGOff2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                g2off = (int) sliderGOff2.getValue();
                rgbOff2 = Color.rgb(r2off*17, g2off*17, b2off*17);
                rectOff2.setFill(ColorOperations.correction(rgbOff2));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence2().getColorOff().setGreen(g2off*17);
            }
        });
        
        sliderBOff2.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                b2off = (int) sliderBOff2.getValue();
                rgbOff2 = Color.rgb(r2off*17, g2off*17, b2off*17);
                rectOff2.setFill(ColorOperations.correction(rgbOff2));
                setGraphColors();
                blinkingPrograms.get(programID).getSequence2().getColorOff().setBlue(b2off*17);
            }
        });
    
    }
    
    private void spinnerExceptionHandler(){
        StringConverter sci = new StringConverter(){
            @Override
            public String toString(Object object) {
                try{
                    int i = (int) object;
                    return i+"";  
                }
                catch(Exception e){
                    return "0";
                }
            }

            @Override
            public Object fromString(String string) {
                try{
                    return Integer.parseInt(string);
                }
                catch(Exception e){
                    return 0;
                }
            }
        };
        spinnerRep1.getValueFactory().setConverter(sci);
        spinnerRep2.getValueFactory().setConverter(sci);
        StringConverter scd = new StringConverter(){
            @Override
            public String toString(Object object) {
                
                try{
                    double d = (double) object;
                    return String.format("%.2f",d);
                }
                catch(Exception e){
                    return "0,00";
                }
            }

            @Override
            public Object fromString(String string) {
                try{
                    return Double.parseDouble(string);
                }
                catch(Exception e){
                    return 0;
                }
            }
        };
        spinnerDelay1.getValueFactory().setConverter(scd);
        spinnerDelay2.getValueFactory().setConverter(scd);
        spinnerTOff1.getValueFactory().setConverter(scd);
        spinnerTOff2.getValueFactory().setConverter(scd);
        spinnerTOn1.getValueFactory().setConverter(scd);
        spinnerTOn2.getValueFactory().setConverter(scd);
    }
    
    private void addSpinnerListeners(){
        spinnerDelay1.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerDelay1.getValueFactory();
            delay1 = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
            spinnerDelay1.getValueFactory().setValue(delay1);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence1().setDelay(delay1);
        });
        spinnerTOff1.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerTOff1.getValueFactory();
            tOff1 = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
            spinnerTOff1.getValueFactory().setValue(tOff1);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence1().settOff(tOff1);
        });
        spinnerTOn1.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerTOn1.getValueFactory();
            tOn1 = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
            spinnerTOn1.getValueFactory().setValue(tOn1);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence1().settOn(tOn1);
        });
        spinnerDelay2.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerDelay2.getValueFactory();
            delay2 = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
            spinnerDelay2.getValueFactory().setValue(delay2);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence2().setDelay(delay2);
        });
        spinnerTOff2.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerTOff2.getValueFactory();
            tOff2 = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
            spinnerTOff2.getValueFactory().setValue(tOff2);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence2().settOff(tOff2);
        });
        spinnerTOn2.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerTOn2.getValueFactory();
            tOn2 = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
            spinnerTOn2.getValueFactory().setValue(tOn2);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence2().settOn(tOn2);
        });
        spinnerRep1.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            rep1 = setSpinnerValue(newValue, oldValue);
            spinnerRep1.getValueFactory().setValue(rep1);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence1().setRep(rep1);
        });
        spinnerRep2.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            rep2 = setSpinnerValue(newValue, oldValue);
            spinnerRep2.getValueFactory().setValue(rep2);
            actualizeGraph();
            blinkingPrograms.get(programID).getSequence2().setRep(rep2);
        });
//        spinnerTStart.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
//            SpinnerValueFactory.DoubleSpinnerValueFactory dblFactory = (SpinnerValueFactory.DoubleSpinnerValueFactory) spinnerTStart.getValueFactory();
//            tStartGraph = setSpinnerValue(newValue, oldValue, dblFactory.getAmountToStepBy());
//            spinnerTStart.getValueFactory().setValue(tStartGraph);
//            setGraphLimits();
//        });
        spinnerTEnd.valueProperty().addListener((ObservableValue obs, Object oldValue, Object newValue) -> {
            tEndGraph = setSpinnerValue(newValue, oldValue);
            spinnerTEnd.getValueFactory().setValue(tEndGraph);
            setGraphLimits();
        });
    }
    
    private double setSpinnerValue(Object newValue, Object oldValue, double dec) {
        double val;
        try{
            val = (double) newValue;
        }
        catch(Exception e1){
            try{
                val = (double) oldValue;
            }
            catch(Exception e2){
                val = 0;
            }
        }
        return roundSpinnerValue(val, dec);
    }

    private int setSpinnerValue(Object newValue, Object oldValue) {
        int val;
        try{
            val = Integer.parseInt(newValue.toString());
        }
        catch(Exception e1){
            try{
                val = Integer.parseInt(oldValue.toString());
            }
            catch(Exception e2){
                val = 0;
            }
        }
        return val;
    }
    
    private double roundSpinnerValue(double val, double dec) {
        double roundedVal = Math.round(val * (1/dec)) / 20.0f;
        return roundedVal;
    }
}
    /*
    private void colorCorrection(){
        
        double r1 = 190;
        double g1 = 190;
        double b1 = 190;
        
        double r2 = rgbOn1.getRed()*255;
        double g2 = rgbOn1.getGreen()*255;
        double b2 = rgbOn1.getBlue()*255;
        
        double op = computeOpacity(rgbOn1);
        
        double r = r1 * (1-op) + r2 * op;
        double g = g1 * (1-op) + g2 * op;
        double b = b1 * (1-op) + b2 * op;
        
        r = Math.exp(rgbOn1.getRed());
        
        rgbOn1 = Color.color(Math.pow(r2/255,0.35), Math.pow(g2/255,0.35), Math.pow(b2/255,0.35), op*0.85+0.15);
        
    }
    
    private double computeOpacity(Color c){
        
        double op = c.getRed();
        if(op < c.getGreen()){
            op = c.getGreen();
        }
        if(op < c.getBlue()){
            op = c.getBlue();
        }
        
        //double op = ( c.getRed() + c.getGreen() + c.getBlue() ) / 3;
        return op;
    }
*/   