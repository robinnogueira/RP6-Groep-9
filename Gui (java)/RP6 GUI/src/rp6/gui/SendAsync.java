
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
                // opent xbee met waardes die worden meegeven
                myXBeeDevice.open();
                
                // start de listener om data te ontvangne
                myXBeeDevice.addDataListener(new MyDataReceiveListener());
                
                
                System.out.println("Verbinden met XBee is succesvol tot stand gebracht!");
                    
                //Maakt een instantie aan en haalt het netwerk gegevens op van de xbee
                XBeeNetwork network = myXBeeDevice.getNetwork();

                // Geeft het remoteAddress door waar de data heen moet worden verzonden
                XBee64BitAddress remoteAddress = new XBee64BitAddress("0013A2004146A180"); // het MAC addres van de andere XBee
                RemoteXBeeDevice remoteDevice = new RemoteXBeeDevice(myXBeeDevice, remoteAddress); // stopt deze in de instantie

                // Voegt hem toe aan het netwerk
                network.addRemoteDevice(remoteDevice);
                
                // zet de waardes die zijn ontvangen in de parameter in een byte
                byte[] dataToSend = args.getBytes();
     
                // verstuurd de data die is ontvangen
                myXBeeDevice.sendData(remoteDevice, dataToSend);
                
                System.out.println("Succesvol geconverteerd!");
                  
           }
            // wanneer er iets misgaat met de xbee
           catch(XBeeException e)
           {
               // e.printStackTrace(); // Debug only: om errors te worden omgevangen weer te geven 
               System.out.println("Niet verzonden");
           }           	

        }                       
}
