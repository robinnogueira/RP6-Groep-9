package rp6.gui;

import javax.swing.JFrame;

/**
 *
 * @author Jop Veth
 */

public class RP6GUI {

    public static void main(String[] args) {
        
        handmatig.conv(127);
        
        /* open GUI */
        JFrame frame = new GUI(); 
        frame.setVisible(true);  
    }
    
}
