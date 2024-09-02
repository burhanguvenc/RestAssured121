package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


import java.util.Map;

public class CookiesDemo {
	
	//@Test(priority=1)
	void testCookies() {
		
		given()
		
		
		.when()
			.get("https://www.google.com/")
		
		
		.then()
			.cookie("AEC", "AVYB7cruB8JUQQeQjQyJ91dS2gERgAyV8vkEuINv7IU2It4lfMYd_BOeNA4")
			.log().all();
		
	}
	
	@Test(priority=2)
	void getCookieInfo() {
		
	Response res=given()	
		
	.when()
		.get("https://www.google.com/");
		
	//Get single cookie info
	String cookie_value = res.getCookie("AEC");
	System.out.println("Value of cookie is=====>  "+cookie_value);
	
	//Get all cookies info
	Map<String,String> cookies_value = res.getCookies();
	
	//System.out.println(cookies_value.keySet());
	
	for(String key:cookies_value.keySet()) {
		String cookie_v = res.getCookie(key);
		System.out.println(key+"  "+cookie_v);
	}
	
	
	
		
	}

}
