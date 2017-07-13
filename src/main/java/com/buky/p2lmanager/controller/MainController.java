package com.buky.p2lmanager.controller;

import com.buky.p2lmanager.model.blinking.BlinkingProgram;
import com.buky.p2lmanager.model.conditions.Condition;
import com.buky.p2lmanager.model.objects.P2LChannel;
import com.buky.p2lmanager.model.objects.P2LUnit;
import com.buky.p2lmanager.model.objects.P2LValue;
import com.buky.p2lmanager.model.xml.BlinkProgramsXml;
import com.buky.p2lmanager.model.xml.ConditionXml;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML private TreeView p2lTree;
    @FXML private Tab tabBlinkProgram, tabCondition;
    
    private String gateway;
    private List<P2LChannel> channels = new ArrayList<>();
    private List<P2LUnit> units = new ArrayList<>();
    private List<P2LValue> values = new ArrayList<>();
    
    private HashMap<Integer, BlinkingProgram> blinkingPrograms;
    private HashMap<Integer, Condition> conditions;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        initBlinkProgramTab();
        initConditionTab();       
//        
//        gateway = "192.168.1.1";
//        channels.add(new P2LChannel(1, "Channel 1"));
//        channels.add(new P2LChannel(2, "Channel 2"));
//        channels.add(new P2LChannel(3, "Channel 3"));
//        units.add(new P2LUnit(1, 1, "Unit 1", 0));
//        units.add(new P2LUnit(2, 1, "Unit 2", 0));
//        units.add(new P2LUnit(3, 1, "Unit 3", 0));
//        units.add(new P2LUnit(4, 2, "Unit 4", 0));
//        units.add(new P2LUnit(5, 3, "Unit 5", 0));
//        units.add(new P2LUnit(6, 3, "Unit 6", 0));
//        values.add(new P2LValue(12, 1, 10));
//        values.add(new P2LValue(13, 1, 10));
//        values.add(new P2LValue(14, 2, 10));
//        values.add(new P2LValue(15, 4, 10));
//        values.add(new P2LValue(16, 6, 10));
//        createTree();
//        
//        EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {handleMouseClicked(event);};
//        p2lTree.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle); 
    }    

    @FXML
    private void openSimulator(ActionEvent event) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Simulator.fxml"));
            Pane p = (Pane) fxmlLoader.load();
            SimulatorController sc = fxmlLoader.<SimulatorController>getController();
            sc.setConditions(conditions);
            sc.setPrograms(blinkingPrograms);
            stage.setScene(new Scene(p));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        stage.show();
    }
    
    private void initBlinkProgramTab(){
        BlinkProgramsXml bpx = new BlinkProgramsXml();
        bpx.readConfiguration("configuration\\blinkingPrograms.xml");
        blinkingPrograms = bpx.getBlinkingPrograms();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/BlinkProgramTab.fxml"));
            AnchorPane ap = (AnchorPane) fxmlLoader.load();
            BlinkProgramTabController bptc = fxmlLoader.<BlinkProgramTabController>getController();    
            bptc.setBlinkingPrograms(blinkingPrograms);
            bptc.actualize();
            tabBlinkProgram.setContent(ap);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initConditionTab(){
        ConditionXml cx = new ConditionXml();
        cx.readConfiguration("configuration\\conditions.xml");
        conditions = cx.getConditions();
        FXMLLoader fxmlLoader;
        try {
            fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ConditionTab.fxml"));
            AnchorPane ap = (AnchorPane) fxmlLoader.load();
            ConditionTabController ctc = fxmlLoader.<ConditionTabController>getController();    
            ctc.setConditions(conditions);
            ctc.actualize();
            tabCondition.setContent(ap);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void handleMouseClicked(MouseEvent event) {
        Node node = event.getPickResult().getIntersectedNode();
        // Accept clicks only on node cells, and not on empty spaces of the TreeView
        if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
            String name = (String) ((TreeItem)p2lTree.getSelectionModel().getSelectedItem()).getValue();
            System.out.println("Node click: " + name);
        }
    }
        
    public void createTree(){
        CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<>(gateway);
        rootItem.setExpanded(true);
        p2lTree.setEditable(true);
        p2lTree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());    
        for (P2LChannel channel : channels) {
            CheckBoxTreeItem<String> channelCheckBox = new CheckBoxTreeItem<>(channel.getName());
            rootItem.getChildren().add(channelCheckBox);
            channelCheckBox.setExpanded(true);
            for(P2LUnit unit : units){
                if(unit.getIdChannel() == channel.getId()){
                    CheckBoxTreeItem<String> unitCheckBox = new CheckBoxTreeItem<>(unit.getName());
                    channelCheckBox.getChildren().add(unitCheckBox);
                    
//                    for(P2LValue value : values){
//                        if(value.getIdUnit() == unit.getId()){
//                            TreeItem<String> valueItem = new TreeItem<>(value.getId()+" "+value.getValue());
//                            unitCheckBox.getChildren().add(valueItem);
//                      //      values.remove(value);
//                        }
//                    }
                 //   units.remove(unit);
                }
            }             
        }
        p2lTree.setRoot(rootItem);
        p2lTree.setShowRoot(true);       
    }
    
    
}
