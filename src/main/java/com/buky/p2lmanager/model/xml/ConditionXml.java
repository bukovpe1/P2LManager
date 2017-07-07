/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.xml;

import com.buky.p2lmanager.model.MyParser;
import com.buky.p2lmanager.model.conditions.Condition;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author bukov
 */
public class ConditionXml {
    
    private int STATES_NUM = 8;
    
    private HashMap<Integer, Condition> conditions;

    public ConditionXml() {
        conditions = new HashMap<>();
    }
        
    public boolean readConfiguration(String fileName){
        
        try {
            
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            Element pElement;
            String string;
                        
            for (int i = 0; i < STATES_NUM; i++) {
                
                pElement = (Element) doc.getElementsByTagName("program").item(i);
                
                string = pElement.getElementsByTagName("id").item(0).getTextContent();
                int id = new MyParser(string,0).parseInt();
                
                string = pElement.getElementsByTagName("nextState").item(0).getTextContent();
                int nextState = new MyParser(string,false).parseInt();
                
                string = pElement.getElementsByTagName("relativeValue").item(0).getTextContent();
                boolean relativeValue = new MyParser(string,false).parseBoolean();
                
                string = pElement.getElementsByTagName("edge").item(0).getTextContent();
                int edge = new MyParser(string,false).parseInt();
                
                string = pElement.getElementsByTagName("ledSegmentEvent").item(0).getTextContent();
                int ledSegmentEvent = new MyParser(string,false).parseInt();
                
                Condition c = new Condition(id, nextState, relativeValue, edge, ledSegmentEvent);
                
                conditions.put(id, c);   
                
            }
            return true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(BlinkProgramsXml.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public HashMap<Integer, Condition> getConditions() {
        return conditions;
    }
    
}
