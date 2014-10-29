package server;

import java.io.IOException;
import java.net.ServerSocket;

public class MultiServer {
	public static String WEBROOT = "";//默认目录
	 public static String defaultPage = "index.html";//默认文件
	public static void main(String[] args) throws IOException {
		/*args[0] = "4444";
	    if (args.length != 1) {
	        System.err.println("Usage: java MultiServer <port number>");
	        System.exit(1);
	    }*/

	        int portNumber = 4000;//Integer.parseInt(args[0]);
	        boolean listening = true;
	        try{ 
	        	System.out.println ("Server starting...\n"); 
	        	ServerSocket serverSocket = new ServerSocket(portNumber);
	        	System.out.println("serverSocket===== "+serverSocket);
	        	if(null!=serverSocket){
	        		 while (listening) {
	        			 //阻塞，直到有客户连接,并启动服务线程
	 		            new MultiServerThread(serverSocket.accept()).start();
	 		        }
	        	}
		    } catch (IOException e) {
	            System.err.println("Could not listen on port " + portNumber);
	            System.exit(-1);
	        }
	    }
}
