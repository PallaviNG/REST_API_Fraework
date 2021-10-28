package hamCrestDemo;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HamcrestTestDemo {
	
		@Test
		public void VerifyFirstName() {
			Response response = given().when().get("https://reqres.in/api/users/2");
			response.then().body("data.first_name", equalToIgnoringCase("Janet"));
		}
		
		@Test
		public void VerifyPageNo() {
			RestAssured.given().when().get("https://reqres.in/api/users?page=2").then().body("page",equalTo(2));	
		}
		
		@Test
		public void VerifyHasItem() {
//			RestAssured.given().when().get("https://reqres.in/api/users?page=2").then().body("data.first_name", hasItem("Tobias")).body("data[1].id",equalTo(8));
			RestAssured.given().when().get("https://reqres.in/api/users?page=2").then().body("$",hasItems(2,6));//body("$.data[0].first_name", containsString("Michael")).body("$.data[1].id",equalTo(8));
		}
}


//9137439654
