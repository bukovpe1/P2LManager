/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.view;

import com.buky.p2lmanager.model.objects.P2LChannel;
import com.buky.p2lmanager.model.objects.P2LUnit;
import com.buky.p2lmanager.model.objects.P2LValue;
import java.util.List;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;

/**
 *
 * @author bukov
 */
public class Tree {
    
    private String gateway;
    private List<P2LChannel> channels;
    private List<P2LUnit> units;
    private List<P2LValue> values;

    public Tree(String gateway, List<P2LChannel> channels, List<P2LUnit> units, List<P2LValue> values) {
        this.gateway = gateway;
        this.channels = channels;
        this.units = units;
        this.values = values;
    }

    public TreeView createTree(){
        CheckBoxTreeItem<String> rootItem = new CheckBoxTreeItem<String>(gateway);
        rootItem.setExpanded(true);
        TreeView tree = new TreeView(rootItem);  
        tree.setEditable(true);
        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());    
        for (P2LChannel channel : channels) {
            CheckBoxTreeItem<String> channelCheckBox = new CheckBoxTreeItem<>(channel.getName());
            rootItem.getChildren().add(channelCheckBox);
            for(P2LUnit unit : units){
                if(unit.getIdChannel() == channel.getId()){
                    CheckBoxTreeItem<String> unitCheckBox = new CheckBoxTreeItem<>(channel.getName());
                    channelCheckBox.getChildren().add(unitCheckBox);
                    for(P2LValue value : values){
                        if(value.getIdUnit() == value.getId()){
                            TreeItem<String> valueItem = new TreeItem<>(value.getId()+" "+value.getValue());
                            unitCheckBox.getChildren().add(valueItem);
                            values.remove(value);
                        }
                    }
                    units.remove(unit);
                }
            }             
        }
        tree.setRoot(rootItem);
        tree.setShowRoot(true);    
        return tree;
    }
    
}
