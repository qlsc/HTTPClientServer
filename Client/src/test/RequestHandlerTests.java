package test;

// make sure to add JUnit to your project to run this class.
import impl.RequestHandler;

import org.junit.Assert;
import org.junit.Test;

/*
 * 3 tests are provided for you, these WILL be part of the marking criteria for functionality tests.
 * It's a good idea to make sure they pass, and add some more tests here.
 * You should not modify the existing tests.
 */
public class RequestHandlerTests {

	private static final String NO_IMPL_GET_1_0 = "HTTP/1.0 501 GET Not Implemented\r\n\r\n";
	private static final String NO_IMPL_HEAD_1_0 = "HTTP/1.0 501 HEAD Not Implemented\r\n\r\n";
	private static final String BAD_REQUEST_1_0 = "HTTP/1.0 400 Bad Request\r\n\r\n";

	@Test
	public void testGetRequestHttp1() {
		String request = "GET / HTTP/1.0\r\n\r\n";
		String expected;
		String actual;
		
		// Request handler needs to have a constructor with no arguments.
		RequestHandler requestHandler = new RequestHandler();
		expected = NO_IMPL_GET_1_0;
		actual = new String(requestHandler.processRequest(request.getBytes()));
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testHeadRequestHttp1() {
		String request = "HEAD / HTTP/1.0\r\n\r\n";
		String expected;
		String actual;
		
		RequestHandler requestHandler = new RequestHandler();
		expected = NO_IMPL_HEAD_1_0;
		actual = new String(requestHandler.processRequest(request.getBytes()));
		Assert.assertEquals(expected, actual);
	}
		
	@Test
	public void testInvalidRequest() {
		
		String request = "Rabble rabble";
		String expected;
		String actual;
		
		RequestHandler requestHandler = new RequestHandler();
		expected = BAD_REQUEST_1_0;
		actual = new String(requestHandler.processRequest(request.getBytes()));
		Assert.assertEquals(expected, actual);
	}
	public static void main(String[] args) {
//		String request = "GET / HTTP/1.0\r\n\r\n";
		String request = "HEAD / HTTP/1.0\r\n\r\n";
		RequestHandler requestHandler = new RequestHandler();
		System.out.println(new String(requestHandler.processRequest(request.getBytes())));
	}
}
