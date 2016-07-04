/*
 * Created on 2 Dec 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.pricecapture.main.Results.Result;
import com.stockscanner.utils.ConfigFile;

public class StockTracker 
{
  
  float open = -1;
  float high = 0;
  float low = Float.MAX_VALUE;
  float close = -1;
  
  private ConfigFile stockData;

  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy,HHmm");
  
  StockTracker(ConfigFile stockData)
  {
    this.stockData = stockData;
  }

  public void processResults(Results results, String searchString) 
  {
    Date currentTimeStamp = new Date(System.currentTimeMillis());
    Result result = results.findResult(searchString);
    
    System.out.println("[" + currentTimeStamp + "]  Bid : "+result.bid+"  Offer : "+result.offer);
    
    String timestamp = sdf.format(currentTimeStamp);
//    outputFile.write(timestamp + "," + result.name + "," + result.bid + "," + result.offer);
//    outputFile.flush();
    
    // Get the minute value
    int minute = Calendar.getInstance().get(Calendar.MINUTE);
    
    if (minute % 5 == 0)
    {
      close = result.bid;        
      System.out.println(currentTimeStamp + "," +
          open + "," +
          high + "," +
          low + "," +
          close);
      
      stockData.write(timestamp + "," + 
          result.name + "," + 
          open + "," + 
          high + "," + 
          low + "," + 
          close);
      
      stockData.flush();
      
      high = 0;
      low = Float.MAX_VALUE;
      open = result.bid;
      close = 0;
    }
    else
    {
      if (result.bid > high) high = result.bid;
      if (result.bid < low)  low = result.bid;
    } 
  }
  
}
