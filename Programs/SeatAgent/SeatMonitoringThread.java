package kr.ac.kaist.iot.service.n1.seat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import kr.ac.kaist.iot.common.ContextType;
import kr.ac.kaist.iot.mqtt.pdu.CommandType;
import kr.ac.kaist.iot.service.n1.seat.config.SeatConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phidgets.InterfaceKitPhidget;

public class SeatMonitoringThread extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(SeatMonitoringThread.class);

	private final SeatConfig seatConfig;
	private final InterfaceKitPhidget phidgetBoard;
	private final SeatStatus status;

	private boolean isRunning = false;

	private int agent_mode = 0;
	private String serverIP = "192.168.0.";
	private TCPClient client = null;
	
	public SeatMonitoringThread(SeatConfig seatConfig, InterfaceKitPhidget phidgetBoard) {
		super(seatConfig.getUniqueName());

		InetAddress inetaddr = null;
		
		try {
			inetaddr = InetAddress.getByName(serverIP);
		}catch(UnknownHostException e) {
			logger.error(e.getMessage(), e);
		}
		
		this.status = new SeatStatus();

		this.seatConfig = seatConfig;
		this.phidgetBoard = phidgetBoard;
		
		//Select Mode
		if(SeatAgent.agent_ID == 5) {
			agent_mode = 1; // MODE 1: SERVER		
		}
		else {
			agent_mode = 2; // MODE 2: CLIENsT
			client = new TCPClient();
		}
	}

	@Override
	public void run() {
		int[] readValues = new int[this.seatConfig.getSensorIndex().length];
		int count = 0 ;

		// Loop
		isRunning = true;
		while (isRunning) {
			
			//Server

			try {
				for (int i = 0; i < this.seatConfig.getSensorIndex().length; i++) {
					int readIndex = this.seatConfig.getSensorIndex()[i];
					readValues[i] = this.phidgetBoard.getSensorRawValue(readIndex);
				}

				
				logger.debug("Read Values: " + Arrays.toString(readValues) + " [" + seatConfig.getUniqueName() + "]"
						+ " isOccupied: " + this.status.isOccupied());
				

				
				boolean isUpdated = status.update(readValues, this.seatConfig.getSensorDetectThresholdMinValue(),
						this.seatConfig.getSensorDetectThresholdMaxValue(),
						this.seatConfig.getSensorDetectThresholdTime());

				if (isUpdated) { // Seat State º¯È­½Ã					
					if (this.seatConfig.getUniqueName().contains("A")) { //Seat A checking          
						
						if (this.status.isOccupied() == true) {
							SeatAgent.seatCount++;
							logger.debug("[" + this.seatConfig.getUniqueName() + "]" + "Seat A - Occupied #" + SeatAgent.seatCount);
						}
						else {
							SeatAgent.seatCount--;
							logger.debug("[" + this.seatConfig.getUniqueName() + "]" + "Seat A - Empty #" + SeatAgent.seatCount);
						}
					}
					
					else { //Seat B checking
						if (this.status.isOccupied() == true) {
								SeatAgent.seatCount++;
							logger.debug("[" + seatConfig.getUniqueName() + "]" + "Seat B - Occupied #" + SeatAgent.seatCount);
						}
						else {
							SeatAgent.seatCount--;
							logger.debug("[" + seatConfig.getUniqueName() + "]" + "Seat B - Empty #" + SeatAgent.seatCount);

						}
					}
					
					//Client
					if (agent_mode ==2) {
						client.send();
						SeatAgent.agent.publish(CommandType.Context, ContextType.Occupied, SeatAgent.seatCount); // TODO: update
					}
					else {
						SeatAgent.allSeatCount[SeatAgent.agent_ID - 1] = SeatAgent.seatCount;
	                    for (int i = 0; i < SeatAgent.allSeatCount.length; i++) {
	                    	count = 0;
	                    	count += SeatAgent.allSeatCount[i];
	                    }

	                    logger.info("Total Seat Count Updated: " + Arrays.toString(SeatAgent.allSeatCount));
			        	SeatAgent.agent.publish(CommandType.Context, ContextType.Occupied, count); 
					}
					logger.debug("Updated by SeatAgent" + SeatAgent.agent_ID);
					
					logger.info("Seat Status Updated: " + this.seatConfig.getUniqueName() + " / "
							+ this.seatConfig.getContextName() + " / " + this.status.toString());
				}

			} catch (Exception e) {
				// Print exception stack
				e.printStackTrace();
				logger.error(e.getMessage(), e);
				
			} finally {
				try {
					// Sleep
					Thread.sleep(this.seatConfig.getSensorDetectInterval());
				} catch (InterruptedException e1) {
					e1.printStackTrace();
					logger.error(e1.getMessage(), e1);
				}
			}
		}
	}

	public void terminate() {
		isRunning = false;

		if (agent_mode ==2) {
			client.terminate();
		}	
	}

	public SeatStatus getSeatStatus() {
		return status;
	}
	
	public String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
	
	 public int arraySum(int[] array) {
		 int sum = 0;
		 int i = 0;
		 
		 for (i = 0; i < array.length; i++) {
			 sum += array[i];
		 }
			
		 
		 logger.debug("############ array[0] = " + array[0] + ", array[1] = " + array[1] + "SUM OF ARRAY = " + sum);
		 
		 return sum;
		 
	 }
}
