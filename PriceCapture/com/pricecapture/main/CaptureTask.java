/*
 * Created on 30 Nov 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.main;

import java.awt.Color;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import com.pricecapture.main.Results.Result;
import com.stockscanner.utils.ConfigFile;


public class CaptureTask extends TimerTask {
  
  ConfigFile outputFile = null;
  ConfigFile stockData = null;
  ConfigFile stockData2 = null;
  
  StockTracker tracker1 = null;
  StockTracker tracker2 = null;

  
  CaptureTask(String filename)
  {
     //outputFile = new ConfigFile(filename + "_1min.csv"); 
     stockData = new ConfigFile(filename + "_DJI_5min.csv");
     stockData2 = new ConfigFile(filename + "_FTSE_5min.csv");
     
     tracker1 = new StockTracker(stockData);
     tracker2 = new StockTracker(stockData2);
  }

  
  @Override
  public void run() {
    
    
    try
    {
      ArrayList<String> resultString = OCR.recognize(
          new Point(250,290), 
          new Point(1120,570), 
          new Color(11, 51, 60), 
          true);
      
      Results results = new Results(resultString);
      
      tracker1.processResults(results, "Wall");
      tracker2.processResults(results, "nSE");
      
      
    }
    catch (Exception e)
    {
      System.out.println(new Date(System.currentTimeMillis()) + " : Could not obtain results");
    }
    
    
    
  }




}
