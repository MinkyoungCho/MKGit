package kr.ac.kaist.iot.service.n1.seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(TCPClient.class);
	private String serverIP = "192.168.0.132";
	Socket s = null;
	PrintWriter out = null;

	public TCPClient() {
		try {
			s = new Socket(serverIP, 11111);
		} catch (UnknownHostException e) {
			LOGGER.error("Unknown Host :" + serverIP, e);
			s = null;
		} catch (IOException e) {
			LOGGER.error("Cant connect to server at 11111. Make sure it is running.", e);
			s = null;
		}

		if (s == null)
			System.exit(-1);
	}
	
	
	public void send() {
		try {
			out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));

			// Since this is the client, we will initiate the talking.
			// Send a string data and flush
			out.println(SeatAgent.seatCount);
			out.flush();
			LOGGER.debug("SeatAgent" + SeatAgent.agent_ID + " has just sent its count : " + SeatAgent.seatCount);

		} catch (IOException e) {
			LOGGER.error("Exception during communication. Server probably closed connection.", e);
		} 	
	}
	
	public void terminate() {
		try {
			out.close();
			// Close the socket before quitting
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
