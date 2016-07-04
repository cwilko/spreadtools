/*
 * Created on 9 Dec 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.graph;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class MyChartPanel extends ChartPanel
{

  BufferedImage image;
  JFreeChart chart;
  
  MyChartPanel(JFreeChart chart)
  {
    super(chart);
    this.chart = chart;
  }

  /**
   * Drawing an image can allow for more
   * flexibility in processing/editing.
   */
//  public void paintComponent(Graphics g) {
//    
//      this.image = chart.createBufferedImage(this.getParent().getWidth(),this.getParent().getHeight());
//      
//      // Center image in this component.
//      g.drawImage(image, 0, 0, this);
//  }
  
  public void updateImage(BufferedImage image)
  {
    repaint();
  }
  

}
