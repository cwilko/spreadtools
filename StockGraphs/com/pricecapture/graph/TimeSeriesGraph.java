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

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

@SuppressWarnings("serial")
public class TimeSeriesGraph extends AbstractGraph {
  
  HashMap<String, TimeSeries> seriesMap;
  TimeSeriesCollection dataset;
  
  ChartPanel panel;
  
  int x=0;
  
  public TimeSeriesGraph(String title, int width, int height)
  {
    super(title);
    seriesMap = new HashMap<String, TimeSeries>();
    dataset = new TimeSeriesCollection();
    
    // Generate the graph
    chart = ChartFactory.createTimeSeriesChart(title, // Title
            "Time", // x-axis Label
            "Ratio", // y-axis Label
            dataset, // Dataset
            true, // Show Legend
            true, // Use tooltips
            false // Configure chart to generate URLs?
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
    
    NumberAxis acis = (NumberAxis)chart.getXYPlot().getRangeAxis();
    acis.setAutoRangeIncludesZero(false);
    //chart.getXYPlot().getRangeAxis().setRange(0.501, 0.510);
    //chart.getXYPlot().getRangeAxis().setRange(0.508, 0.5125);
      
    
    
  }

  public void addEntry(String seriesKey, float entry, Date timestamp, boolean nullGaps)
  {
    TimeSeries series = seriesMap.get(seriesKey);
    if (series == null)
    {
      seriesMap.put(seriesKey, (series = new TimeSeries(seriesKey)));
      dataset.addSeries(series);
    }
    
    RegularTimePeriod expectedMinute = null;
    RegularTimePeriod gap = null;
    if (nullGaps && series.getItemCount() > 0)
    {
      expectedMinute = series.getDataItem(series.getItemCount()-1).getPeriod().next();
      gap = expectedMinute
        .next()
        .next()
        .next()
        .next()
        .next()
        .next()
        .next()
        .next()
        .next();
    }
    RegularTimePeriod minute = new Minute(timestamp);
    if (expectedMinute != null && 
        minute.compareTo(gap) > 0)
      series.addOrUpdate(expectedMinute, null);
    
    series.addOrUpdate(minute, entry);
    

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
