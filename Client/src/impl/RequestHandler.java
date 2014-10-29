package impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.StringTokenizer;

public class RequestHandler {
	private String indexFileName="index.html";  
	public byte[] processRequest(byte[] bytes) {
		if(null==bytes) return "noDate".getBytes();
		String request = new String(bytes); 
//		StringBuffer response = new StringBuffer();
		  StringBuffer response = new StringBuffer();
		//记录日志  
        System.out.println(request); 
        StringTokenizer st=new StringTokenizer(request);  
        String method=st.nextToken();  
        String version=""; 
        System.out.println("method: "+method);
      
	
	if (method.equals("GET")) { 
		
			String fileName = st.nextToken();
			fileName = "." + fileName;
			// 打开所请求的文件
			FileInputStream fis = null;
			boolean fileExists = true;
			try {
				fis = new FileInputStream(fileName);
			} catch (FileNotFoundException e) {
				fileExists = false;
			}

        if (st.hasMoreTokens()) {  
            version=st.nextToken();  
        }  
			// 完成回应消息
			String serverLine = "Server: a simple java httpServer";
			String statusLine = null;
			String contentTypeLine = null;
			String entityBody = null;
			String contentLengthLine = "error";
			if (fileExists) {
				if (version.startsWith("HTTP ")) {
					response.append("HTTP/1.0 200 OK\r\n");
					Date now = new Date();
					response.append("Date: " + now + "\r\n");
					response.append("Server: HTTP 1.0\r\n");
				}
			} else {
				if (version.startsWith("HTTP ")) {
					response.append("HTTP/1.0 404 File Not Found\r\n");
					Date now = new Date();
					response.append("Date: " + now + "\r\n");
					response.append("Server: HTTP 1.0\r\n");
					response.append("Content-Type: text/html\r\n\r\n");
				}
			}
		} else {// 方法不等于"GET"
			response.append("HTTP/1.0 501 GET Not Implemented\r\n\r\n");
			try {
				return response.toString().getBytes("US-ASCII");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	
	
	
	if (method.equals("HEAD")) { 
		
		String fileName = st.nextToken();
		fileName = "." + fileName;
		// 打开所请求的文件
		FileInputStream fis = null;
		boolean fileExists = true;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			fileExists = false;
		}
		
		if (st.hasMoreTokens()) {  
			version=st.nextToken();  
		}  
		// 完成回应消息
		String serverLine = "Server: a simple java httpServer";
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		String contentLengthLine = "error";
		if (fileExists) {
			if (version.startsWith("HTTP ")) {
				response.append("HTTP/1.0 200 OK\r\n");
				Date now = new Date();
				response.append("Date: " + now + "\r\n");
				response.append("Server: HTTP 1.0\r\n");
			}
		} else {
			if (version.startsWith("HTTP ")) {
				response.append("HTTP/1.0 404 File Not Found\r\n");
				Date now = new Date();
				response.append("Date: " + now + "\r\n");
				response.append("Server: HTTP 1.0\r\n");
				response.append("Content-Type: text/html\r\n\r\n");
			}
		}
	} else {// 方法不等于"HEAD"
		response.append("HTTP/1.0 501 HEAD Not Implemented\r\n\r\n");
		try {
			return response.toString().getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	if (method.equals("POST")) { 
		
		String fileName = st.nextToken();
		fileName = "." + fileName;
		// 打开所请求的文件
		FileInputStream fis = null;
		boolean fileExists = true;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			fileExists = false;
		}
		
		if (st.hasMoreTokens()) {  
			version=st.nextToken();  
		}  
		// 完成回应消息
		String serverLine = "Server: a simple java httpServer";
		String statusLine = null;
		String contentTypeLine = null;
		String entityBody = null;
		String contentLengthLine = "error";
		if (fileExists) {
			if (version.startsWith("HTTP ")) {
				response.append("HTTP/1.0 200 OK\r\n");
				Date now = new Date();
				response.append("Date: " + now + "\r\n");
				response.append("Server: HTTP 1.0\r\n");
			}
		} else {
			if (version.startsWith("HTTP ")) {
				response.append("HTTP/1.0 404 File Not Found\r\n");
				Date now = new Date();
				response.append("Date: " + now + "\r\n");
				response.append("Server: HTTP 1.0\r\n");
				response.append("Content-Type: text/html\r\n\r\n");
			}
		}
	} else {// 方法不等于"POST"
		response.append("HTTP/1.0 501 POST Not Implemented\r\n\r\n");
		try {
			return response.toString().getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
		return null; 
	}


}
