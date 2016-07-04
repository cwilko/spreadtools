/*
 * Created on 28 Nov 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class IOHandler {

    public static ArrayList<String> getResponse(InputStream is) throws Exception
    {
      
      ArrayList<String> response = new ArrayList<String>();
      
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      
      String line = null;
      while((line = br.readLine()) != null)
        response.add(line.replace("l", "1").replace("O", "0").replace("I", "l").replace("_","i").trim());
      
      return response;
    }
    
}
