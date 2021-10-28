package com.testingshastra.day2;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateCustomer {
		public static void main(String[] args) {
				Response res = given().auth().basic("sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S","")
										.formParam("name","Deepak").formParam("email", "deepak@gmail.com").formParam("address[line1]", "B-503, WhiteHouse")
										.formParam("preferred_locales[0]", "No").formParam("address[city]", "Solapur").formParam("address[country]", "India")
										.formParam("address[state]", "Maharashtra").formParam("address[line2]", "B-305, White House")
										.formParam("phone", "9325493115").formParam("balance","15000000000")
										.post("https://api.stripe.com/v1/customers");
				System.out.println(res.prettyPrint());
				
				JsonPath jp = res.jsonPath();
				String str = jp.get("id");
				System.out.println(str);
				System.out.println("Name :" +jp.get("name"));
				System.out.println("Email :" +jp.get("email"));
				System.out.println("Address :" +jp.get("address"));
				System.out.println("Address :" +jp.get("address.line1"));
				System.out.println("Address (State):" +jp.get("address.state"));
				System.out.println("Phone :" +jp.get("phone"));
				System.out.println("Preferred_locales :" +jp.get("preferred_locales"));
				System.out.println("Balance :" +jp.get("balance"));
				System.out.println("Size: " +jp.getMap("address").size());
				
				String j = "{\"name\" :\"morpheus\", \"job\":\"leader\"} ";
				Response resReqResIn = given().contentType("application/json").body(j).post("https://reqres.in/api/users");
				System.out.println(resReqResIn.prettyPrint());
				System.out.println(resReqResIn.statusCode());
				System.out.println(resReqResIn.statusLine());
		}
}
