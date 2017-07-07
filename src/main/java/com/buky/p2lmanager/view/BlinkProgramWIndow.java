/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.view;

import com.buky.p2lmanager.controller.EditBlinkProgramController;
import com.buky.p2lmanager.model.blinking.BlinkingProgram;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author bukov
 */
public class BlinkProgramWIndow {
    
    private EditBlinkProgramController blinkTableController;
    private HashMap<Integer, BlinkingProgram> blinkingPrograms;
    private HashMap<Integer, BlinkingProgram> tmp;
    int i = -1;
    public BlinkProgramWIndow(HashMap<Integer, BlinkingProgram> blinkingPrograms) {
        this.blinkingPrograms = blinkingPrograms;
    }
        
    public void display(){
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditBlinkProgramWindow.fxml"));
            stage.setScene(new Scene((Pane) fxmlLoader.load()));
            blinkTableController = fxmlLoader.getController();
            blinkTableController.setBlinkingPrograms(blinkingPrograms);
            blinkTableController.actualizeProgram();
            stage.initModality(Modality.APPLICATION_MODAL);
           // File f = new File("Icons\\alvat.png");
           // stage.getIcons().add(new Image(f.toURI().toString()));
            stage.setOnCloseRequest(e -> {
                tmp = new HashMap<>(blinkTableController.getBlinkingPrograms());
                stage.close();
            });      
            
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(BlinkProgramWIndow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public EditBlinkProgramController getTableController(){
        return blinkTableController;
    }

    public HashMap<Integer, BlinkingProgram> getBlinkingPrograms() {
        return tmp;
    }    
}
