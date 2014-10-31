package impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.StringTokenizer;

public class RequestHandler {

	public byte[] processRequest(byte[] bytes) {
		if(null==bytes) return "noDate".getBytes();
		String request = new String(bytes); 
		StringBuffer response = new StringBuffer();
		//记录日志  
        System.out.println(request); 
        String wwwroot = System.getProperty("user.dir");
        StringTokenizer st=new StringTokenizer(request);  
        String method=st.nextToken();  
        String version=""; 
        System.out.println("method: "+method);
        if (method=="GET") {  
            if (st.hasMoreTokens()) {  
                version=st.nextToken();  
            }  
            if (version.startsWith("HTTP ")) {  
            	response.append("HTTP/1.0 200 OK\r\n");  
                    Date now=new Date();  
                    response.append("Date: "+now+"\r\n");  
                    response.append("Server: HTTP 1.0\r\n");  
//                    response.append("Content-length: "+theData.length+"\r\n");  
                    response.append("Content-Type: "+contentType+"\r\n\r\n");  
            }else {  
                if (version.startsWith("HTTP ")) {  
                    response.append("HTTP/1.0 404 File Not Found\r\n");  
                    Date now=new Date();  
                    response.append("Date: "+now+"\r\n");  
                    response.append("Server: HTTP 1.0\r\n");  
                    response.append("Content-Type: text/html\r\n\r\n");  
                }  
                response.append("<HTML>\r\n");  
                response.append("<HEAD><TITLE>File Not Found</TITLE></HRAD>\r\n");  
                response.append("<BODY>\r\n");  
                response.append("<H1>HTTP Error 404: File Not Found</H1>");  
                response.append("</BODY></HTML>\r\n");  
            }  
        }else {//方法不等于"GET"  
            if (version.startsWith("HTTP ")) {  
                response.append("HTTP/1.0 501 Not Implemented\r\n");  
                Date now=new Date();  
                response.append("Date: "+now+"\r\n");  
                response.append("Server: HTTP 1.0\r\n");  
                response.append("Content-Type: text/html\r\n\r\n");  
            }  
           /* response.append("<HTML>\r\n");  
            response.append("<HEAD><TITLE>Not Implemented</TITLE></HRAD>\r\n");  
            response.append("<BODY>\r\n");  
            response.append("<H1>HTTP Error 501: Not Implemented</H1>");  
            response.append("</BODY></HTML>\r\n");  */
        }
		if(method=="HEAD"){
			if (st.hasMoreTokens()) {  
                version=st.nextToken();  
            }  
            if (version.startsWith("HTTP ")) {  
            	response.append("HTTP/1.0 200 OK\r\n");  
                    Date now=new Date();  
                    response.append("Date: "+now+"\r\n");  
                    response.append("Server: HTTP 1.0\r\n");  
//                    response.append("Content-length: "+theData.length+"\r\n");  
                    response.append("Content-Type: "+contentType+"\r\n\r\n");   
                 
            }
		}
		if(method=="POST"){
			if (st.hasMoreTokens()) {  
                version=st.nextToken();  
            }  
            if (version.startsWith("HTTP ")) {  
            	response.append("HTTP/1.0 200 OK\r\n");  
                 
            }
		}

			
		try {
			return response.toString().getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null; 
	}
public static void main(String[] args) {
	RequestHandler test = new RequestHandler();
	String request = "GET / HTTP/1.0\r\n\r\n";
	String response = new String(test.processRequest(request.getBytes()));
	System.out.println(response);
}
}
