package rp6.gui;

import javax.swing.JFrame;

/**
 *
 * @author Jop Veth
 * @author Chris Eijken
 */

public class RP6GUI {

    public static void main(String[] args) {
        
        // stuurt een Stop code door voor de robot en start de listener
        handmatig.conv(127);
        
        /* open GUI */
        JFrame frame = new GUI(); 
        frame.setVisible(true);  
    }
    
}
