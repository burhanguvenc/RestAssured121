package day1;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import org.testng.annotations.Test;

	//given()  Content Type,Set cookies, Add authorization, Add parameters, set headers info etc..
	//when()  get, post, put, delete
	//then()  validate status code, extract response, extract headers cookies, response body...

public class HttpRequests {

	int userId;
	
	@Test(priority=1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	
	@Test(priority=2)
	void createUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "rebo");
		data.put("job", "student");
		
		userId = given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
			
/*		.then()
			.statusCode(201)
			.log().all();*/
	}
	
	@Test(priority=3, dependsOnMethods= {"createUser"})
	void updateUser() {
		
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "rebo");
		data.put("job", "pilot");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+userId)
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority=4, dependsOnMethods= {"createUser"})
	void deleteUser() {
	
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+userId)
			
		.then()
			.statusCode(204)
			.log().all();
	}
	
	
	
	
	
}
