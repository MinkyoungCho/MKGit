package kr.ac.kaist.iot.service.n1.seat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import kr.ac.kaist.iot.common.ContextType;
import kr.ac.kaist.iot.mqtt.pdu.CommandType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TCPServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(TCPServer.class);

	private int count = 0;
	ServerSocket myServerSocket;
    boolean ServerOn = true;

    public TCPServer() 
    { 
        try 
        { 
            myServerSocket = new ServerSocket(11111); 
        } 
        catch(IOException e) 
        { 
        	LOGGER.error("Could not create server socket on port 11111. Quitting.", e);
            System.exit(-1); 
        } 

        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        LOGGER.info("It is now : " + formatter.format(now.getTime()));

        // Successfully created Server Socket. Now wait for connections. 
        while(ServerOn) 
        {                        
            try 
            { 
                // Accept incoming connections. 
                Socket clientSocket = myServerSocket.accept(); 

                // For each client, we will start a service thread to 
                // service the client requests. This is to demonstrate a 
                // Multi-Threaded server. Starting a thread also lets our 
                // MultiThreadedSocketServer accept multiple connections simultaneously. 

                // Start a Service thread 
                ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
                cliThread.start(); 

            } 
            catch(IOException e) 
            { 
                LOGGER.error("Exception encountered on accept. Ignoring. Stack Trace :", e);
                e.printStackTrace(); 
            }
        }

        try 
        { 
            myServerSocket.close(); 
            LOGGER.info("Server Stopped");
        } 
        catch(Exception e) 
        { 
            System.out.println("Problem stopping server socket"); 
            System.exit(-1); 
        } 

    } 


    class ClientServiceThread extends Thread 
    { 
        Socket clientSocket;
        boolean m_RunThread = true; 
        private String clientIP = null;
        
        public ClientServiceThread() 
        { 
            super(); 
        } 

        ClientServiceThread(Socket s) 
        { 
            clientSocket = s; 

        } 

        public void run() 
        {            
    
            BufferedReader in = null; 
            PrintWriter out = null; 
            int i = 0;

            // Print out details of this connection 
            LOGGER.debug("Accepted Client Address - " + clientSocket.getInetAddress().getHostName());
            
            try 
            {                                
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
                out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream())); 

                // At this point, we can read for input and reply with appropriate output. 

                // Run in a loop until m_RunThread is set to false 
                while(m_RunThread) 
                {                    
                    String clientCommand = in.readLine(); 
                    if(clientCommand == null) break;
                    System.out.println("Client Says :" + clientCommand);

                    if(!ServerOn) 
                    { 
                        LOGGER.info("Server has already stopped");
                        m_RunThread = false;   

                    } 

                    // Process it 
                    for (i = 0; i < SeatAgent.allSeatCount.length; i++) {
                    	clientIP = clientSocket.getInetAddress().getHostName();
                    	if (clientIP.equalsIgnoreCase(SeatAgent.arrayIP[i])) {
                    		SeatAgent.allSeatCount[i] = Integer.parseInt(clientCommand);
                    		break;
                    	}
                    }
                    
                    for (i = 0; i < SeatAgent.allSeatCount.length; i++) {
                    	count += SeatAgent.allSeatCount[i];
                    }

                    LOGGER.info("Total Seat Count Updated: " + Arrays.toString(SeatAgent.allSeatCount));
		        	SeatAgent.agent.publish(CommandType.Context, ContextType.Occupied, count); 
                } 
            } 
            catch(Exception e) 
            { 
                e.printStackTrace(); 
            } 
            finally 
            { 
                // Clean up 
                try 
                {                    
                    in.close(); 
                    out.close(); 
                    clientSocket.close(); 
                    System.out.println("...Stopped"); 
                } 
                catch(IOException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        } 


    } 
}
