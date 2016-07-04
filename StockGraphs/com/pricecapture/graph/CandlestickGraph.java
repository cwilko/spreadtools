/*
 * Created on 9 Dec 2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.pricecapture.graph;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.ohlc.OHLCItem;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;


@SuppressWarnings("serial")
public class CandlestickGraph extends AbstractGraph {
  
  HashMap<String, OHLCSeries> seriesMap;
  OHLCSeriesCollection dataset;
  JFreeChart chart;
  
  ChartPanel panel;
  
  int x=0;
  
  public CandlestickGraph(String title, int width, int height) 
  {
    super(title);
    
    seriesMap = new HashMap<String, OHLCSeries>();
    dataset = new OHLCSeriesCollection();
    
    // Generate the graph
    chart = ChartFactory.createCandlestickChart(title, // Title
            "Time", // x-axis Label
            "Ratio", // y-axis Label
            dataset, // Dataset
            true
        );
    
    chart.getXYPlot().setRangePannable(true);
    chart.getXYPlot().setDomainPannable(true);
    
    final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
    final StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(
        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
        new SimpleDateFormat("dd-MM-yyy, HH:mm"), new DecimalFormat("0.00000")
    );
    renderer.setToolTipGenerator(g);

    
    // Create the graphics objects
    panel = new ChartPanel(chart);
    
    panel.setPreferredSize(new java.awt.Dimension(800, 350));
    panel.setMouseWheelEnabled(true);
    panel.setMouseZoomable(true, true);
    //panel.setVerticalAxisTrace(true);
    //panel.setHorizontalAxisTrace(true);
    panel.setRangeZoomable(false);
    
    //JScrollPane pane = new JScrollPane(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel);
    setSize(800,350);
    setLocation(200,600);
    
    // Fix autoranging
    NumberAxis acis = (NumberAxis)chart.getXYPlot().getRangeAxis();
    acis.setAutoRangeIncludesZero(false);
    //chart.getXYPlot().getRangeAxis().setRange(0.501, 0.510);
    //chart.getXYPlot().getRangeAxis().setRange(0.508, 0.5125);
    
    
  }

  public void addEntry(String seriesKey, float open, float high, float low, float close, Date timestamp)
  {
    OHLCSeries series = seriesMap.get(seriesKey);
    if (series == null)
    {
      seriesMap.put(seriesKey, (series = new OHLCSeries(seriesKey)));
      dataset.addSeries(series);
    }
    
    RegularTimePeriod minute = RegularTimePeriod.createInstance(Minute.class, timestamp, TimeZone.getDefault());

    
    RegularTimePeriod lastMinute = null;
    if (series.getItemCount() > 0)
    {
      OHLCItem item = (OHLCItem)series.getDataItem(series.getItemCount()-1);
      lastMinute = item.getPeriod();
      if (minute.equals(lastMinute))
        series.remove(lastMinute);
    }

    series.add(minute, open, high, low, close );
    
  }
    
  public void display()
  {
    panel.repaint();

    setVisible(true);
  }
  
  public void remove()
  {
    setVisible(false);
    panel.repaint();
  }
  

}
