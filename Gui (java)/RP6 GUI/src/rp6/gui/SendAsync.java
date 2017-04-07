
package rp6.gui;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.XBeeNetwork;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;

public class SendAsync {
        
    
        public static void main(String args){    
            
            //defineeer de xbee settings
            XBeeDevice myXBeeDevice = new XBeeDevice("COM6", 9600);
            try
            {    
                // opent xbee
                myXBeeDevice.open();
                
                // start de listener 
                myXBeeDevice.addDataListener(new MyDataReceiveListener());
                
                
                System.out.println("Verbinden met XBee is succesvol tot stand gebracht!");
                    
                // Instantiate an XBee device object.
                // Get the XBee Network object from the XBee device.
                XBeeNetwork network = myXBeeDevice.getNetwork();

                // Instantiate a remote XBee device.
                XBee64BitAddress remoteAddress = new XBee64BitAddress("0013A2004146A180");
                RemoteXBeeDevice remoteDevice = new RemoteXBeeDevice(myXBeeDevice, remoteAddress);

                // Add the remote XBee device to the network.
                network.addRemoteDevice(remoteDevice);
                
                // zet de waardes die zijn ontvangen in de parameter in een byte
                byte[] dataToSend = args.getBytes();
                
                // code voor Async te verzenden
                // myXBeeDevice.sendDataAsync(remoteDevice, dataToSend);
                
                // verstuurd de data die is ontvangen
                myXBeeDevice.sendData(remoteDevice, dataToSend);
                
                System.out.println("Succesvol geconverteerd!");
                  
           }

           catch(XBeeException e)
           {
               // e.printStackTrace(); // debug
               System.out.println("Niet verzonden");
           }           	

        }                       
}
