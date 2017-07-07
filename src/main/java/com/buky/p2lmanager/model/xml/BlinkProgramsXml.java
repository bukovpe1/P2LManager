/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.xml;

import com.buky.p2lmanager.model.MyParser;
import com.buky.p2lmanager.model.blinking.BlinkingColor;
import com.buky.p2lmanager.model.blinking.BlinkingProgram;
import com.buky.p2lmanager.model.blinking.BlinkingSequence;
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
public class BlinkProgramsXml {
    
    private int STATES_NUM = 8;
    
    private HashMap<Integer, BlinkingProgram> blinkingPrograms;

    public BlinkProgramsXml() {
        blinkingPrograms = new HashMap<>();
    }
        
    public boolean readConfiguration(String fileName){
        
        try {
            
            File xmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            Element pElement, sElement, cElement;
            String string;
                        
            for (int i = 0; i < STATES_NUM; i++) {
                
                pElement = (Element) doc.getElementsByTagName("program").item(i);
                
                string = pElement.getElementsByTagName("id").item(0).getTextContent();
                int id = new MyParser(string,0).parseInt();
                
                string = pElement.getElementsByTagName("isSelected").item(0).getTextContent();
                boolean isSelected = new MyParser(string,false).parseBoolean();
                
                sElement = (Element) pElement.getElementsByTagName("sequence").item(0);
                BlinkingSequence bs1 = readSequence(sElement);
                
                sElement = (Element) pElement.getElementsByTagName("sequence").item(1);
                BlinkingSequence bs2 = readSequence(sElement);
                
                BlinkingProgram bp = new BlinkingProgram(id, isSelected, bs1, bs2);
                
                System.out.println(bp.toString());
                
                blinkingPrograms.put(id, bp);   
                
                
            }
            return true;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(BlinkProgramsXml.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private BlinkingSequence readSequence(Element sElement){
        BlinkingSequence bs;
        String string;
        Element cOffElement, cOnElement;
        
        string = sElement.getElementsByTagName("delay").item(0).getTextContent();
        double delay = new MyParser(string,0).parseDouble();

        string = sElement.getElementsByTagName("tOff").item(0).getTextContent();
        double tOff = new MyParser(string,0).parseDouble();

        string = sElement.getElementsByTagName("tOn").item(0).getTextContent();
        double tOn = new MyParser(string,0).parseDouble();

        string = sElement.getElementsByTagName("rep").item(0).getTextContent();
        int rep = new MyParser(string,0).parseInt();

        string = sElement.getElementsByTagName("startWithOn").item(0).getTextContent();
        boolean startWithOn = new MyParser(string,false).parseBoolean();

        cOffElement = (Element) sElement.getElementsByTagName("colorOff").item(0);
        
        string = cOffElement.getElementsByTagName("red").item(0).getTextContent();
        int redOff = new MyParser(string,0).parseInt();

        string = cOffElement.getElementsByTagName("green").item(0).getTextContent();
        int greenOff = new MyParser(string,0).parseInt();

        string = cOffElement.getElementsByTagName("blue").item(0).getTextContent();
        int blueOff = new MyParser(string,0).parseInt();
        
        cOnElement = (Element) sElement.getElementsByTagName("colorOn").item(0);
        
        string = cOnElement.getElementsByTagName("red").item(0).getTextContent();
        int redOn = new MyParser(string,0).parseInt();

        string = cOnElement.getElementsByTagName("green").item(0).getTextContent();
        int greenOn = new MyParser(string,0).parseInt();

        string = cOnElement.getElementsByTagName("blue").item(0).getTextContent();
        int blueOn = new MyParser(string,0).parseInt();
        
        bs = new BlinkingSequence(delay, tOff, tOn, rep, startWithOn, new BlinkingColor(redOff, greenOff, blueOff), new BlinkingColor(redOn, greenOn, blueOn));
        
        return bs;
    }

    public HashMap<Integer, BlinkingProgram> getBlinkingPrograms() {
        return blinkingPrograms;
    }
    
    
}
