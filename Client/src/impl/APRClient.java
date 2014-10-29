package impl;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import client.IAPRClient;


/* You will need to use this class to build your APRClient.
 * It needs to implement the interface IAPRClient and so it
 * will need to have the methods for httpGet, httpHead and httpPost
 */ 
public class APRClient implements IAPRClient {
	
	// Constructor to make APRClient object.
	public APRClient() {

	}

	@Override
	public String httpGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url); 
			httpGet.setProtocolVersion(HttpVersion.HTTP_1_0);
			CloseableHttpResponse response = httpclient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			if(status == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				return null!=entity?EntityUtils.toString(entity):null;
			}else {
				return response.getStatusLine().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String httpHead(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpHead httpHead = new HttpHead(url);
			httpHead.setProtocolVersion(HttpVersion.HTTP_1_0);
			// 访问服务器（这里为本机4444端口）执行GET方法，返回内容
			CloseableHttpResponse response = httpclient.execute(httpHead);
			int status = response.getStatusLine().getStatusCode();// 返回状态码
			/**
			 * 状态码在200,请求已成功，请求所希望的响应头或数据体将随此响应返回。 参见
			 * http://tool.oschina.net/commons?type=5
			 */
			if (status == HttpStatus.SC_OK) {
                Header[] headers = response.getAllHeaders(); 
                StringBuffer headerFormat  = new StringBuffer();
                for(Header header:headers){
                	headerFormat.append(header.getName());
                	headerFormat.append(":");
                	headerFormat.append(header.getValue());
                	headerFormat.append("\n");
                }
				return headerFormat.toString();//返回所有符合接口格式要求的headers
			} else {
				return response.getStatusLine().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String httpPost(String url, String body) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url); 
			httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
			httpPost.setEntity(new StringEntity(body));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if(status == HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				return null!=entity?EntityUtils.toString(entity):null;
			}else {
				return response.getStatusLine().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String httpPost(String url, ArrayList<NameValuePair> nameValuePairs) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();// 返回状态码
			/**
			 * 状态码在200 - 300之间，则表明访问服务器返回数据成功
			 * 状态码在200,请求已成功，请求所希望的响应头或数据体将随此响应返回。 参见
			 * http://tool.oschina.net/commons?type=5
			 */
			if (status == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				// 这里抛出异常的状态码，访问服务器不成功
				throw new ClientProtocolException(
						"Unexpected response status: " + status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		APRClient test = new APRClient();
//		 String url = "http://www.baidu.com";
//		 String url = "http://www.ifeng.com";
//		 String url = "http://www.zhihu.com";
		String url = "http://127.0.0.1:4444/index.html";
//		System.out.println(test.httpHead(url));
		System.out.println(test.httpGet(url));

		// String url = "http://posttestserver.com/post.php";
		String body = "hehe,this is a post body";
//		System.out.println(test.httpPost(url, body));
	}
}
