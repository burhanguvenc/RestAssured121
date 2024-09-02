package day4;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;



public class ParsingJSONResponseData {

	@Test(priority=1)
	void testJsonResponse() {
		
		//Approach1
		
		/*
		 * given() .contentType(ContentType.JSON)
		 * 
		 * .when() .get("http://localhost:3000/store")
		 * 
		 * .then() .statusCode(200) .header("Content-Type", "application/json")
		 * .body("book[3].title", equalTo("Pretty Model"));
		 */
		
		// Approach 2 (Most used approach)
		
		Response res = 		 
				given() 
					.contentType(ContentType.JSON)
				  
				.when() 
					.get("http://localhost:3000/store");
					
				Assert.assertEquals(res.statusCode(), 200);
				Assert.assertEquals(res.header("Content-Type"), "application/json");
				
				String bookName = res.jsonPath().get("book[3].title").toString();
				Assert.assertEquals(bookName, "Pretty Model");
	}
	
	@Test(priority=2)
	void testJsonResponseBodyData() {
		
		//Approach1
		
		Response res = 		 
				given() 
					.contentType(ContentType.JSON)
				  
				.when() 
					.get("http://localhost:3000/store");
					
		// JSONObject Class
		
		JSONObject jo =new JSONObject(res.toString());	// Converting response to Json Object Type
		
		
		// Print all titles of books
		/*
		 * for(int i=0; i<jo.getJSONArray("book").length(); i++) { String bookTitle =
		 * jo.getJSONArray("book").getJSONObject(i).get("title").toString();
		 * System.out.println(bookTitle); }
		 */
		
		// Search for title of the book in json
		boolean status = false;
		for(int i=0; i<jo.getJSONArray("book").length(); i++) 
		{
			String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(bookTitle.equals("Pretty Model")) {
				status=true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		
		// Validate total price of books
		
		  double totalPrice = 0;
		  for(int i=0; i<jo.getJSONArray("book").length(); i++) 
		  { 
			  String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			  
			  totalPrice = totalPrice+Double.parseDouble(price);
			  
		  }
		  
		  System.out.println("Total price of books is    "+totalPrice);
		  Assert.assertEquals(totalPrice, 53.94);
		 
		
	}
	
}
	

