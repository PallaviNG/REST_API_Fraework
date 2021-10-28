package com.testingshastra.day2;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.response.Response;

public class CreateCustomerUsingHashMap {
	public static void main(String[] args) {
//		Response res = given().auth().basic(
//				"sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S",
//				"").formParam("name", "Deepak").formParam("email", "deepak@gmail.com")
//				.formParam("address[line1]", "B-503, WhiteHouse").formParam("preferred_locales[0]", "No")
//				.formParam("address[city]", "Solapur").formParam("address[country]", "India")
//				.formParam("address[state]", "Maharashtra").formParam("address[line2]", "B-305, White House")
//				.formParam("phone", "9325493115").formParam("balance", "15000000000")
//				.post("https://api.stripe.com/v1/customers");
//		System.out.println(res.prettyPrint());
		
		HashMap<String, Object> hm = new HashMap<String,Object>();
		hm.put("email", "Shekhar@gmail.com");
		hm.put("name", "Shekhar");
		hm.put("phone","9325493115");
		hm.put("description", "none");
		
		HashMap< String, Object> hm_address = new HashMap<String, Object>();
		hm.put("address[line1]", "Jodbhavi Peth");
		hm.put("address[city]", "Pune");
		hm.put("address[state]", "Bangalore");
		hm.put("address[country]", "Paris");
		Response res = given().auth().basic("sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S", "")
							.formParams(hm).formParams( hm_address)
							.post("https://api.stripe.com/v1/customers");
		System.out.println(res.prettyPrint());
	}

}
