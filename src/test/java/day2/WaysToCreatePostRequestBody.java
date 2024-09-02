package day2;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;


/*
 * Different ways to create POST request body
 * Post request body using HashMap
 * Post request body creation using org.JSON
 * Post request body creation using POJO class
 * Post request body using external JSON file data
 */

public class WaysToCreatePostRequestBody {
	
	 // Post request body using HashMap

	String userId;
	
		//@Test(priority=1) 
		void testPostUsingHashMap() { 
			  
		HashMap<Object, Object> data = new HashMap<Object, Object>(); 
			  data.put("name", "Scott");
			  data.put("location", "France"); 
			  data.put("phone", "5632547854");
			  data.put("id", "4"); 
		  String courseArr[] = {"C","C++"}; 
		  	data.put("courses",courseArr);
		  
		Response response = given() 
		  	.contentType("application/json") 
		  	.body(data)
		  
		  .when() 
		  	.post("http://localhost:3000/students")
		  
		  .then() 
		  	.assertThat()
		  	.statusCode(201) 
		  	.body("name",equalTo("Scott")) 
		  	.body("location",equalTo("France")) 
		  	.body("phone", equalTo("5632547854")) 
		  	.body("courses[0]", equalTo("C")) 
		  	.body("courses[1]", equalTo("C++"))
		    .header("Content-Type","application/json")
		    .extract().response();
		  
		userId = response.jsonPath().getString("id");
		  
		  }
		 
	// Post request body creation using org.JSON
	//@Test(priority=1)
	void testPostUsinnJsonLibrary() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Scott");
		data.put("location", "France"); 
		data.put("phone", "5632547854");
		String courseArr[] = {"C","C++"}; 
		data.put("courses",courseArr);
		
		Response response = given()
			.contentType("application/json")
			.body(data.toString())  //converting json to string
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("5632547854"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type","application/json")
			.extract().response();
		
		userId = response.jsonPath().getString("id");
	}
	
	//@Test(priority=1)
		void testPostUsingPojoClass() {
			
			Pojo_PostRequest data = new Pojo_PostRequest();
			data.setName("Scott");
			data.setLocation("France");
			data.setPhone("5632547854");
			String coursesArr[] = {"C","C++"};
			data.setCourses(coursesArr);

			Response response = given()
				.contentType("application/json")
				.body(data) 
			
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.body("name",equalTo("Scott"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("5632547854"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++"))
				.header("Content-Type","application/json")
				.extract().response();
			
			userId = response.jsonPath().getString("id");
		}
		
		@Test(priority=1)
		void testPostUsingExternalJsonFile() throws FileNotFoundException {
			
			File f = new File(".\\body.json");
			FileReader fr = new FileReader(f);
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data = new JSONObject(jt); 

			Response response = given()
				.contentType("application/json")
				.body(data.toString())  //converting json to string
			
			.when()
				.post("http://localhost:3000/students")
			
			.then()
				.statusCode(201)
				.body("name",equalTo("Scott"))
				.body("location", equalTo("France"))
				.body("phone", equalTo("5632547854"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++"))
				.header("Content-Type","application/json")
				.extract().response();
			
			userId = response.jsonPath().getString("id");
		}
	
	//Deleting Student Record
	
	@Test(priority=2)
	void testDelete() {
		given()
		
		.when()
			.delete("http://localhost:3000/students/"+userId)
			
		.then()
			.statusCode(200);
	}

}
