package day3;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class LoginDemo {

	@Test(priority=1)
	void testLogging() {
		
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.log().all()
			.log().headers()
			.log().cookies();
		
	}
	
}
