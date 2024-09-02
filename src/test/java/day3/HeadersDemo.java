package day3;



import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;




public class HeadersDemo {
	
	@Test(priority=1)
	void testHeaders() {
		given()
			
		.when()
			.get("https://www.google.com/")
				
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws")
			.log().all();
		
	}
	
	@Test(priority=2)
	void getHeaders() {
		
	Response res =	given()
			
		.when()
			.get("https://www.google.com/");
	
	//Get single header info
	String headerValue = res.getHeader("Content-Type");
	System.out.println("The value of content type header is:  "+headerValue);
				
	//Get All Headers Info 
	Headers myheaders = res.getHeaders();
		for(Header hd:myheaders) {
			System.out.println(hd.getName()+"  "+hd.getValue());
		}
	}
}
