package kr.ac.kaist.iot.service.n1.seat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import kr.ac.kaist.iot.common.ContextValue;
import kr.ac.kaist.iot.service.n1.seat.config.SeatAgentConfig;
import kr.ac.kaist.iot.service.n1.seat.config.SeatConfig;
import kr.ac.kaist.iot.service.n1.seat.config.TableConfig;
import kr.ac.kaist.iot.smartobject.SmartObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;

public class SeatAgent extends SmartObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(SeatAgent.class);

	/* Agent Number ют╥б*/
	public static final int agent_ID = 5;
	public static int seatCount = 0;
	public static int[] allSeatCount = new int[5];
	
	//for TCP communication
	public static String [] arrayIP = {"192.168.0.120", "192.168.0.190", "192.168.0.146", "192.168.0.115", "192.168.0.132"};
	
	public static Socket socket = null;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, PhidgetException {
		LOGGER.info("SeatAgent " + agent_ID + " is being executed.");
		
		// Shutdown hook
		attachShutdownHook();

		// Read configuration file path from args
		String configFilepath = null;
		if (args.length < 1) {
			System.out.println("WARN: No configuration file provided.");
			System.out.println("Usage: java " + SeatAgent.class.getSimpleName() + " configuration_file_path");
			configFilepath = DEFAULT_CONFIG_FILEPATH;
		} else {
			configFilepath = args[0].trim();
		}
		
		// Read configuration file
		config = new SeatAgentConfig();
		try {
			config.load(configFilepath);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Failed to load configuration file: " + configFilepath, e);
			return;
		}
		
		agent = new SeatAgent(
				kr.ac.kaist.iot.common.Constants.OBJ_TYPE_SmartObject, 
				"./resource/SeatAgent" + agent_ID + ".owl");

		agent.initialize(null);
		
		seatMonitoringThreads = new HashMap<String, SeatMonitoringThread>();

		agent.start();
		
		if (agent_ID == 5) {
			new TCPServer();
		}

	}

	/****************************************************************************************
	 * AGENT IMPLEMNTATION CODE
	 ****************************************************************************************/
	public static final String DEFAULT_CONFIG_FILEPATH = "./src/kr/ac/kaist/iot/service/n1/seat/seat.conf";
	public static final String LOG4J_PROPERTIES_PATH = "./src/kr/ac/kaist/iot/service/n1/seat/log4j2.xml";
	
	private static SeatAgentConfig config = null;
	static SeatAgent agent = null;
	private static Map<String, SeatMonitoringThread> seatMonitoringThreads = null;
	
	public SeatAgent(int objectType, String owlPath) {
		super(objectType, owlPath);
	}


	@Override
	protected String getRuleURL() {
		return null;
	}

	private static final int TIMEOUT_PHIDGET_ATTACHMENT = 10000;
	
	public String isOccupied(String seatName) {
		SeatMonitoringThread thread = this.seatMonitoringThreads.get(seatName);
		if (thread != null && thread.isAlive()) {
			
			return thread.getSeatStatus().isOccupied() ? ContextValue.Occupied : ContextValue.Empty;
		} else {
			return ContextValue.Empty;
		}
	}

	public void start() {
		Iterator<TableConfig> tableIterator = this.config.getTableConfigs().iterator();
		while (tableIterator.hasNext()) {
			TableConfig tableConfig = tableIterator.next();

			InterfaceKitPhidget phidgetBoard = null;
			try {
				phidgetBoard = loadPhidgetBoard(tableConfig.getPhidgetBoardSerial());
			} catch (PhidgetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Iterator<SeatConfig> seatIterator = tableConfig.getSeatConfigs().iterator();
			while (seatIterator.hasNext()) {
				SeatConfig seatConfig = seatIterator.next();
				SeatMonitoringThread seatMonitoringThread = new SeatMonitoringThread(seatConfig, phidgetBoard);
				this.seatMonitoringThreads.put(seatConfig.getUniqueName(), seatMonitoringThread);
				seatMonitoringThread.start();
			}
		}
	}

	private InterfaceKitPhidget loadPhidgetBoard(int phidgetBoardSerial) throws PhidgetException {
		InterfaceKitPhidget phidgetBoard = new InterfaceKitPhidget();
		phidgetBoard.addAttachListener(new AttachListener() {
			@Override
			public void attached(AttachEvent ae) {
				LOGGER.info("AttachEvent: " + ae.toString());
			}
		});

		phidgetBoard.addDetachListener(new DetachListener() {
			@Override
			public void detached(DetachEvent de) {
				LOGGER.info("DetachEvent: " + de.toString());
			}
		});

		phidgetBoard.addErrorListener(new ErrorListener() {

			@Override
			public void error(ErrorEvent ee) {
				LOGGER.error("ErrorEvent: " + ee.toString());
			}
		});

		if (phidgetBoardSerial < 0) {
			LOGGER.info("Opening any for phidget interface kit...");
			phidgetBoard.openAny();
		} else {
			LOGGER.info("Opening " + phidgetBoardSerial + " phidget interface kit...");
			phidgetBoard.open(phidgetBoardSerial);
		}

		LOGGER.info("Waiting for phidget interface kit attachment...");

		phidgetBoard.waitForAttachment();
		return phidgetBoard;
	}

	public void terminate() {
		Thread.currentThread().interrupt();

		Iterator<SeatMonitoringThread> threadIterator = this.seatMonitoringThreads.values().iterator();
		while (threadIterator.hasNext()) {
			SeatMonitoringThread t = threadIterator.next();
			if (t != null && t.isAlive()) {
				t.terminate();
			}
		}

		LOGGER.info("SeatAgentImpl terminated.");
	}

	// Shutdown hook to terminate gracefully
	private static void attachShutdownHook() {
		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (agent != null) {
					System.out.println("Terminating " + agent.toString());
					agent.terminate();
				}

				try {
					mainThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Terminated");
			}
		});
	}
}
