package com.testingshastra.day1;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;

public class GetCustomers {
	public static void main(String[] args) {
		Response res = given().auth().basic("sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S","")
								.get("https://api.stripe.com/v1/customers");
		System.out.println(res.prettyPrint());
		
		Response resPost = given().auth().basic("sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S","" )
										.formParam("name", "Rest Assured").formParam("description", "Rest Assured Description").formParam("phone", "9325493115").formParam("address[line1]","Solapur")
										.post("https://api.stripe.com/v1/customers");
		System.out.println(resPost.prettyPrint());
		
		
	}
}
