/*
 * Created on 11 Jun 2010
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.graph;

import java.io.File;
import javax.swing.JFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;

@SuppressWarnings("serial")
public class AbstractGraph extends JFrame {
  
  protected JFreeChart chart;
  
  AbstractGraph(String title)
  {
    super(title);
  }
  
  public void save(String filename)
  {
    try
    {
      ChartUtilities.saveChartAsJPEG(new File(filename), chart, 800, 400);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
  }

}
