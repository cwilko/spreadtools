/*
 * Created on 30 Nov 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

public class Results {
  
  ArrayList<Result> results;
  
  public Results (ArrayList<String> data)
  {
    results = new ArrayList<Result>();
    
    for (String s : data)
    {
      Result result = getResult(s);
      if (result != null)
        results.add(result);
    }
  }

  
  private Result getResult(String s) 
  {
    Result result = null;
    try
    {
      StringTokenizer strTok = new StringTokenizer(s.substring(30), " ");
    
      result = new Result();
      result.name = s.substring(0, 30).trim();
      String bid = strTok.nextToken().trim();
      if (bid.endsWith("."))
        bid += strTok.nextToken();
      String offer = strTok.nextToken().trim();
      if (offer.endsWith("."))
        offer += strTok.nextToken();
      
      result.bid = Float.parseFloat(bid);
      result.offer = Float.parseFloat(offer);
      
    }
    catch (Exception e)
    {
      // Could not parse a result
    }
    return result;
  }


  class Result 
  {
    String name;
    Float bid;
    Float offer;
    
    public String toString()
    {
      return name + " - Bid : "+bid+" , Offer : "+offer;
    }
    
  }
  
  public String toString()
  {
    String s = "Results : \n";
    for (Result r: results)
    {
      s += r + "\n";
    }
    return s;
  }


  public Result findResult(String searchString) 
  {
    for (Result result : results)
    {
     
      if (result.name.indexOf(searchString) >= 0)
        return result;            
    }    
    return null;
  }
}
