package rp6.gui;

import javax.swing.JFrame;

/**
 *
 * @author Jop Veth
 */

public class RP6GUI {

    public static void main(String[] args) {
        
        /* open GUI */
        int test1234 = 127;
        handmatig.conv(test1234);
        JFrame frame = new GUI(); 
        frame.setVisible(true);  
    }
    
}
