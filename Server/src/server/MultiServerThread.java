package server;

import impl.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MultiServerThread extends Thread{
	private Socket socket = null;

    public MultiServerThread(Socket socket) {
        super("MultiServerThread");
        this.socket = socket;
    }
    
    public void run() {
    	System.out.println("socket:"+socket);
        try{
        	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        	
        	//获取客户端请求数据 即request
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
            String inputLine, outputLine;
            RequestHandler requestHandler = new RequestHandler();
            outputLine = new String(requestHandler.processRequest(null));
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = new String(requestHandler.processRequest(inputLine.getBytes("US-ASCII")));
                System.out.println("outputLine: "+outputLine);
                out.println(outputLine);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
