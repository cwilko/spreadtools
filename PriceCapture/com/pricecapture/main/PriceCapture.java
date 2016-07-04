/*
 * Created on 28 Nov 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.main;

import java.util.Calendar;
import java.util.Timer;


public class PriceCapture {

  public static final String OUTPUT_FILE = "DJI_Captured";
  
  public static void main(String args[]) throws Exception
  {
    Thread.sleep(5000);
    
    Timer timer = new Timer();
    
    Calendar date = Calendar.getInstance();    
    date.add(Calendar.MINUTE, 1);
    date.set(Calendar.SECOND, 0);
    date.set(Calendar.MILLISECOND, 0);
     
    timer.scheduleAtFixedRate(new CaptureTask(OUTPUT_FILE), date.getTime(), (60 * 1000));
    
    //new CaptureTask().run();
    
    
  }
}
