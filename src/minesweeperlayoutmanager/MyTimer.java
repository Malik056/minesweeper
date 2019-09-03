/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeperlayoutmanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Taha
 */
public class MyTimer{
    
    int currentValue;
    JLabel c;
    Timer timer;
    
    public void cancel()
    {
        timer.stop();
    }
    
    ActionListener updateClockListener;
    public MyTimer(final JLabel c) {
        
        updateClockListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentValue = Integer.valueOf(c.getText());
            currentValue++;
            c.setText(String.valueOf(currentValue)); 
            
            }
        };
    
        timer = new Timer(1000,updateClockListener);
        timer.start();
        this.c = c;
        
    }
    
    
    
    
};
