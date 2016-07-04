/*
 * Created on 28 Jun 2010
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.stockcalculator.main;

import java.awt.Font;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.pricecapture.timeapp.PriceGrabber;
import com.pricecapture.timeapp.PriceGrabberImpl;



public class StakePanel extends JPanel implements ActionListener, PriceGrabber 
{

  float balance = StakeCalculator.INITIAL_BALANCE;
  float percent = StakeCalculator.PERCENT_STAKE;
  float risk = 0;
  float stops = 10;
  float minPrice = Integer.MAX_VALUE;
  float maxPrice = 0;
  float stake = 0;
  
  float balance_old = StakeCalculator.INITIAL_BALANCE;
  float percent_old = StakeCalculator.PERCENT_STAKE;
  float risk_old = 0;
  float stops_old = 10;
  float minPrice_old = Integer.MAX_VALUE;
  float maxPrice_old = 0;
  float stake_old = 0;
  
  float currentPrice = 0;
  
  JTextField balanceField;
  JTextField percentField;
  JTextField riskField;
  JTextField stopsField;
  JTextField minField;
  JTextField maxField;
  JTextField stakeField;
  
  JTextField balanceField_old;
  JTextField percentField_old;
  JTextField riskField_old;
  JTextField stopsField_old;
  JTextField minField_old;
  JTextField maxField_old;
  JTextField stakeField_old;
  
  Font font = new Font("Tahoma Bold", Font.PLAIN, 12);
  
  private static final long serialVersionUID = 1L;
  private static final float LIMIT = 10f;
  StakeCalculator parent;
  
  
  public StakePanel(LayoutManager2 lm, StakeCalculator parent)
  {
    super(lm);
    this.parent = parent;
    
    new PriceGrabberImpl(this);
    
    refresh();
      
    
  }
  
  public void refresh()
  {
    removeAll();
    
    setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
              
    JLabel balanceLabel_old = new JLabel("Previous Balance");
    balanceLabel_old.setFont(font);
    balanceField_old = new JTextField(new DecimalFormat("##.##").format(balance_old));
    balanceField_old.addActionListener(this);    
    add(balanceLabel_old);    
    add(balanceField_old);
    
    JLabel balanceLabel = new JLabel("Current Balance");
    balanceLabel.setFont(font);
    balanceField = new JTextField(new DecimalFormat("##.##").format(balance));
    balanceField.addActionListener(this);
    add(balanceLabel);    
    add(balanceField);
    
    JLabel percentLabel_old = new JLabel("Stake Percentage");
    percentLabel_old.setFont(font);
    percentField_old = new JTextField(new DecimalFormat("##.##").format(percent_old));
    percentField_old.addActionListener(this);
    add(percentLabel_old);
    add(percentField_old);
    
    JLabel percentLabel = new JLabel("Stake Percentage");
    percentLabel.setFont(font);
    percentField = new JTextField(new DecimalFormat("##.##").format(percent));
    percentField.addActionListener(this);
    add(percentLabel);    
    add(percentField);  
    
    JLabel riskLabel_old = new JLabel("Risk");
    riskLabel_old.setFont(font);
    riskField_old = new JTextField(new DecimalFormat("##.##").format(risk_old));
    riskField_old.addActionListener(this);
    add(riskLabel_old);
    add(riskField_old);
    
    JLabel riskLabel = new JLabel("Risk");
    riskLabel.setFont(font);
    riskField = new JTextField(new DecimalFormat("##.##").format(risk));
    riskField.addActionListener(this);
    add(riskLabel);
    add(riskField);
    
    JLabel maxLabel_old = new JLabel("Max. Price");
    maxLabel_old.setFont(font);
    maxField_old = new JTextField(Float.toString(maxPrice_old));
    maxField_old.addActionListener(this);
    add(maxLabel_old);    
    add(maxField_old);
    
    JLabel maxLabel = new JLabel("Max. Price");
    maxLabel.setFont(font);
    maxField = new JTextField(Float.toString(maxPrice));
    maxField.addActionListener(this);
    add(maxLabel);    
    add(maxField);
    
    JLabel minLabel_old = new JLabel("Min. Price");
    minLabel_old.setFont(font);
    minField_old = new JTextField(Float.toString(minPrice_old));
    minField_old.addActionListener(this);
    add(minLabel_old);
    add(minField_old);
    
    JLabel minLabel = new JLabel("Min. Price");
    minLabel.setFont(font);
    minField = new JTextField(Float.toString(minPrice));
    minField.addActionListener(this);
    add(minLabel);
    add(minField);
    
    JLabel stopsLabel_old = new JLabel("Stops");
    stopsLabel_old.setFont(font);
    stopsField_old = new JTextField(Float.toString(stops_old));
    stopsField_old.addActionListener(this);
    add(stopsLabel_old);
    add(stopsField_old);
    
    JLabel stopsLabel = new JLabel("Stops");
    stopsLabel.setFont(font);
    stopsField = new JTextField(Float.toString(stops));
    stopsField.addActionListener(this);
    add(stopsLabel);
    add(stopsField);
    
    JLabel stakeLabel_old = new JLabel("Stake");
    stakeLabel_old.setFont(font);
    stakeField_old = new JTextField(new DecimalFormat("##.##").format(stake_old));
    stakeField_old.addActionListener(this);
    add(stakeLabel_old);    
    add(stakeField_old);
    
    JLabel stakeLabel = new JLabel("Stake");
    stakeLabel.setFont(font);
    stakeField = new JTextField(new DecimalFormat("##.##").format(stake));
    stakeField.addActionListener(this);
    add(stakeLabel);    
    add(stakeField);
          
    SpringUtilities.makeCompactGrid(this, //parent
        7, 4,
        10, 10,  //initX, initY
        20, 10); //xPad, yPad
    
    revalidate();
    
    
  }
  
  private synchronized void update() {

    risk = balance * percent;
    
    if (minPrice == Integer.MAX_VALUE || maxPrice == 0)
      stops = Float.parseFloat(stopsField.getText());
    else
      stops = 2 + (maxPrice - minPrice);
    
    stake = balance * percent / (float)stops;
   
  }
  
  private synchronized void update_old() {
    
    risk_old = balance_old * percent_old;
    
    if (minPrice_old == Integer.MAX_VALUE || maxPrice_old == 0)
      stops_old = Float.parseFloat(stopsField_old.getText());
    else
      stops_old = 2 + (maxPrice_old - minPrice_old);
    
    stake_old = balance_old * percent_old / (float)stops_old;
  }

  public synchronized void actionPerformed(ActionEvent e) {
    
    balance = Float.parseFloat(balanceField.getText());
    percent = Float.parseFloat(percentField.getText());
    
    minPrice = Float.parseFloat(minField.getText());
    maxPrice = Float.parseFloat(maxField.getText());
    
    update();
    
    balance_old = Float.parseFloat(balanceField_old.getText());
    percent_old = Float.parseFloat(percentField_old.getText());
    
    minPrice_old = Float.parseFloat(minField_old.getText());
    maxPrice_old = Float.parseFloat(maxField_old.getText());
    
    update_old();
    
    refresh();
  }

  public synchronized void setCurrentPrice(float currentPrice) {
    boolean update = false; 
    
    if (currentPrice < minPrice)
    {
      if (minPrice == Integer.MAX_VALUE || currentPrice >= (minPrice - LIMIT))
      {
        minPrice = currentPrice;
        update = true;
      }
    }
    
    if (currentPrice > maxPrice)
    {
      if (maxPrice == 0 || currentPrice <= (maxPrice + LIMIT))
      {
        maxPrice = currentPrice;
        update = true;
      }
    }
    
//    if (this.currentPrice != currentPrice)
//      System.out.println("UPDATE :  " + currentPrice);
    this.currentPrice = currentPrice;
    
    if (update)
    {
      update();
      refresh();
    }
  }

  public synchronized void resetTimer(Date newTime) {
    
    System.out.println("RESET @ " + newTime + ", min=" + (int) minPrice + " max=" + (int) maxPrice + " stops=" + (int) stops + " stake=" + new DecimalFormat("##.##").format(stake));
    
    balance_old = balance;
    percent_old = percent;
    risk_old = risk;
    stops_old = stops;
    minPrice_old = minPrice;
    maxPrice_old = maxPrice;
    stake_old = stake;    
    
    minPrice = Integer.MAX_VALUE;
    maxPrice = 0;
    
    update();
    
  }

}

