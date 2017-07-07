/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buky.p2lmanager.model.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bukov
 */
public class HttpRequests {
    
    private String urlString;
    private String outputMsg;

    public HttpRequests(String urlString) {
        this.urlString = urlString;
    }
    
    public boolean put(String inputMsg){
        URL url;
        HttpURLConnection httpURLConnection = null;
        OutputStreamWriter osw = null;
        try {
            url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(2000);
            osw = new OutputStreamWriter(httpURLConnection.getOutputStream());
            
//            inputMsg = inputMsg.replaceAll("\\s","");
//            inputMsg = inputMsg.replace("\u0000", ""); // removes NUL chars
//            inputMsg = inputMsg.replace("\\u0000", "");
            
            osw.write(inputMsg);            
            osw.flush();
 //           osw.close();
            
          //  System.out.println(getStringFromInputStream(httpURLConnection.getInputStream()));
            
          // System.out.println(httpURLConnection.getResponseCode());
            return httpURLConnection.getResponseCode()==200;
        } catch (Exception exception) {
 //           exception.printStackTrace();
            return false;
        }  finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException ex) {
                    Logger.getLogger(HttpRequests.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean get(){
        HttpURLConnection httpURLConnection = null;
        BufferedReader rd = null;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
       //     System.out.println(urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(2000);
            rd = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
//            rd.close();
            outputMsg = result.toString();
      //      System.out.println(msg);
            return httpURLConnection.getResponseCode()==200;
        } catch (Exception ex) {
      //      Logger.getLogger(HttpReq.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
            }
            if(rd != null){
                try {
                    rd.close();
                } catch (IOException ex) {
                    Logger.getLogger(HttpRequests.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getMsg() {
        return outputMsg;
    }
    
}
