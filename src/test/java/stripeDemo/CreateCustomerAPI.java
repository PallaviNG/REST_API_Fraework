package stripeDemo;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.DataUtil;

public class CreateCustomerAPI {
	@Test(dataProvider = "TC", dataProviderClass = DataUtil.class)
	public void validateCreateCustomer(Hashtable<String, String> data) {
		Response res = given().auth().basic("sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S","")
				.formParam("name",data.get("name")).formParam("email", data.get("email")).formParam("description", data.get("description"))
				.post("https://api.stripe.com/v1/customers");
		res.prettyPrint();
	}
	
	@Test(dataProvider="TC", dataProviderClass = DataUtil.class)
	public void GetCustomer(Hashtable<String, String> data) {
		Response response = given().auth().basic("sk_test_51JeYHOSCH3ZKL05WfpbnYGSuAto3sESrAcDrZfbw03cDaR72Hr3VS5SruLCQKAmqBv7O4OmZKJSGA22rlBDzqLnj00UpCl5W5S","")
				.get("https://api.stripe.com/v1/customers");
		response.prettyPrint();
	}
}
