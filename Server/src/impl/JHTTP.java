package impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import server.RequestProcessor;


public class JHTTP{  
	  
    private File documentRootDirectory;  
    private String indexFileName="index.html";  
    private ServerSocket serverSocket;  
    private int numThreads=50;  
      
    public JHTTP(File documentRootDirectory,int port , String indexFileName)throws IOException {  
        if (!documentRootDirectory.isDirectory()) {  
            throw new IOException(documentRootDirectory+" does not exist as a directory ");  
        }  
        this.documentRootDirectory=documentRootDirectory;  
        this.indexFileName=indexFileName;  
        this.serverSocket=new ServerSocket(port);  
    }  
      
    private JHTTP(File documentRootDirectory, int port)throws IOException {  
        this(documentRootDirectory, port, "index.html");  
    }  
      
    public void startServer(){  
        for (int i = 0; i < numThreads; i++) {  
            Thread t=new Thread(new RequestProcessor(documentRootDirectory, indexFileName));  
            t.start();  
        }  
          
        System.out.println("Accepting connection on port "  
                +serverSocket.getLocalPort());  
        System.out.println("Document Root: "+documentRootDirectory);  
        while (true) {  
            try {  
                Socket clientSocket=serverSocket.accept(); 
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                    
                RequestProcessor.processRequest(clientSocket);  
            } catch (IOException e) {  
                // TODO: handle exception  
            }  
        }  
    }  
      
      
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        File docroot;  
        try {  
            docroot=new File("E:\\Workspaces2013\\eclipse43\\Server");  
        } catch (ArrayIndexOutOfBoundsException e) {  
            System.out.println("Usage: java JHTTP docroot port indexfile");  
            return;  
        }  
          
        int port;  
        try {  
            port=Integer.parseInt(args[1]);  
            if (port<0||port>65535) {  
                port=80;  
            }  
        } catch (Exception e) {  
            port=80;  
        }  
          
        try {  
            JHTTP webserver=new JHTTP(docroot, port);  
            webserver.startServer();  
        } catch (IOException e) {  
            System.out.println("Server could not start because of an "+e.getClass());  
            System.out.println(e);  
        }  
          
    }  
  
}  
