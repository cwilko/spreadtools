/*
 * Created on 28 Jun 2010
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.stockcalculator.main;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SpringLayout;


public class StakeCalculator extends JFrame {

  // Sim : £8187.59
  // Difference : £1181
  public static final float INITIAL_BALANCE = 7006.27f;
  public static final float PERCENT_STAKE = 0.01f;

  public static void main(String args[])
  {
    new StakeCalculator();
  }
  
  public StakeCalculator() 
  {
    super("Stake Calculator 2.0");
    
    setSize(640,300);
    setDefaultLookAndFeelDecorated(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setAlwaysOnTop(true);
    setResizable(false);
    
    Container content = getContentPane();
    content.setLayout(new BorderLayout());
        

    // Stock Pane
    
    StakePanel mainPanel = new StakePanel(new SpringLayout(), this);
    
    // Content Pane
    content.add(mainPanel, BorderLayout.CENTER);
    
    setVisible(true);
  }
}