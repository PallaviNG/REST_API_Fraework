package stripeAPIFramework;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import io.restassured.response.Response;
import setUp.BaseTest;

public class Customer extends BaseTest {
		public static Response setPostReqToCreateCustomerAPIWithValidAuth(Hashtable<String,String> data) {
				Response response = given().auth().basic(config.getProperty("validSecretKey"),"")
					.formParam("name",data.get("name"))
					.formParam("email",data.get("email"))
					.formParam("description",data.get("description"))
					.post(config.getProperty("customerAPIEndPoint"));
			return response;
		}
		
		public static Response setDeleteReqToDeleteCustomerAPIWithValidAuth(Hashtable<String,String> data) {
				String id= data.get("customerID");
				Response response =given().auth().basic(config.getProperty("validSecretKey"), "")
					.pathParam("id",id).when()
					.delete(config.getProperty("deleteCustomerAPIEndPoint").concat(config.getProperty("deleteCustomerEndPointQueryString")));
				System.out.println("Response (method): "+response.asString());
				return response;
		}
}
