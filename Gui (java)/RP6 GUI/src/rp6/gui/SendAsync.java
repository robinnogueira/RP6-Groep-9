/**
 * Copyright 2017, Digi International Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES 
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR 
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES 
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN 
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF 
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package rp6.gui;

import com.digi.xbee.api.RemoteXBeeDevice;
import com.digi.xbee.api.XBeeDevice;
import com.digi.xbee.api.exceptions.XBeeException;
import com.digi.xbee.api.models.XBee64BitAddress;
import com.digi.xbee.api.utils.HexUtils;

/**
 * XBee Java Library Send Data Asynchronously sample application.
 * 
 * <p>This example sends data asynchronously to a remote device whose Node 
 * Identifier is 'REMOTE'.</p>
 * 
 * <p>For a complete description on the example, refer to the 'ReadMe.txt' file
 * included in the root directory.</p>
 */
public class SendAsync {
	
	/* Constants */
	
	// TODO Replace with the serial port where your sender module is connected to.
	private static final String PORT = "COM6";
	// TODO Replace with the baud rate of your sender module.
	private static final int BAUD_RATE = 9600;
	
	private static final String REMOTE_NODE_IDENTIFIER = "RP6";
	

	//public static void main(String[] args) {
         public static void main(String args) {    
             
             
            int deviceOpen = 0;
                
		//System.out.println(" +---------------------------------------------------+");
		//System.out.println(" |  XBee Java Library Send Data Asynchrously Sample  |");
		// System.out.println(" +---------------------------------------------------+\n");
		
		XBeeDevice myDevice = new XBeeDevice(PORT, BAUD_RATE);
                                

		byte[] dataToSend = args.getBytes();
		                
		try {
                        if(deviceOpen == 0){
			myDevice.open();
                        myDevice.addDataListener(new MyDataReceiveListener());
                        deviceOpen = 1;
                        
                }
			                System.out.println("device opent");

			// Obtain the remote XBee device from the XBee network.
			//XBeeNetwork xbeeNetwork = myDevice.getNetwork();
                        //xbeeNetwork.discoverDevice(REMOTE_NODE_IDENTIFIER);
                        
                        
                        //XBeeDevice myLocalXBeeDevice = new XBeeDevice("COM6", 9600);
			RemoteXBeeDevice remoteDevice = new RemoteXBeeDevice(myDevice,
  				   new XBee64BitAddress("0013A20040E51E49"));
                                
			if (remoteDevice == null) {
				System.out.println("Couldn't find the remote XBee device with '" + REMOTE_NODE_IDENTIFIER + "' Node Identifier.");
				System.exit(1);
			}
			
			System.out.format("Sending data to %s >> %s | %s... ", remoteDevice.get64BitAddress(), 
					HexUtils.prettyHexString(HexUtils.byteArrayToHexString(dataToSend)), 
					new String(dataToSend));
			
			myDevice.sendDataAsync(remoteDevice, dataToSend);
			
			System.out.println("Success");
			
		} catch (XBeeException e) {

			System.out.println("Error: niet verzonden");
			//e.printStackTrace();
			//System.exit(1);
		} finally {
			//myDevice.close();
		}
	}
}
