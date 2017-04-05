
package rp6.gui;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.utils.HexUtils;

public class SendAsync {
        
   static boolean first = true;
    
        public static void main(String args){    

            XBeeDevice myXBeeDevice = new XBeeDevice("COM6", 9600);
            try
            {    
                
                    myXBeeDevice.open();

                    
                    myXBeeDevice.addDataListener(new MyDataReceiveListener());
                    first = false;
                    System.out.println(first);
                    
               
                    System.out.println("Verbinden met XBee is succesvol tot stand gebracht!");
                    
               
                   
                

                // Instantiate an XBee device object.
                // Get the XBee Network object from the XBee device.
                XBeeNetwork network = myXBeeDevice.getNetwork();

                // Instantiate a remote XBee device.
                XBee64BitAddress remoteAddress = new XBee64BitAddress("0013A2004146A180");
                RemoteXBeeDevice remoteDevice = new RemoteXBeeDevice(myXBeeDevice, remoteAddress);

                // Add the remote XBee device to the network.
                network.addRemoteDevice(remoteDevice);

                byte[] dataToSend = args.getBytes();

                // myXBeeDevice.sendDataAsync(remoteDevice, dataToSend);
                myXBeeDevice.sendData(remoteDevice, dataToSend);
                System.out.println("Succesvol geconverteerd!");
                
              //  myXBeeDevice.close();
                
                
           }

           catch(XBeeException e)
            {
                e.printStackTrace();
            }           	

        }                       
}
