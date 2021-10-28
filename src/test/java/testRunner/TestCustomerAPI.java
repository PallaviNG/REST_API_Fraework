package testRunner;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import setUp.BaseTest;
import stripeAPIFramework.Customer;
import utils.DataUtil;
import utils.TestUtil;

public class TestCustomerAPI extends BaseTest {
		@Test(dataProvider = "TC", dataProviderClass = DataUtil.class)
		public void CreateCustomer(Hashtable<String,String> data) {
			Response response = Customer.setPostReqToCreateCustomerAPIWithValidAuth(data);
			Assert.assertEquals(response.statusCode(), 200);
		}
		
		@Test(dataProvider="TC", dataProviderClass = DataUtil.class) 
		public void VerifyObjectValue(Hashtable<String,String> data) {
				Response response = Customer.setPostReqToCreateCustomerAPIWithValidAuth(data);
				System.out.println(response.asString());
				String value = TestUtil.jsonHasValue(response.asString(), "object");
				System.out.println("value: "+value);
				Assert.assertEquals(value, "customer");
		}
		
		@Test(dataProvider="TC",dataProviderClass = DataUtil.class)
		public void DeleteCustomer(Hashtable<String,String> data) {
			System.out.println("In Delete");
			Response response = Customer.setDeleteReqToDeleteCustomerAPIWithValidAuth(data);
			System.out.println(response.asString());
			Assert.assertEquals(response.statusCode(), 200);
		}
		
		@Test(dataProvider = "TC", dataProviderClass = DataUtil.class)
		public void VerifyObjectKeyDeleteCustomer(Hashtable<String,String> data) {
			Response response = Customer.setDeleteReqToDeleteCustomerAPIWithValidAuth(data);
			System.out.println(response.asString());
			boolean b = TestUtil.jsonHasKey(response.asString(), "object");
			Assert.assertEquals(b, true);
		}
		
		@Test(dataProvider="TC",dataProviderClass = DataUtil.class)
		public void VerifyObjectValueDeleteCustomer(Hashtable<String,String> data) {
			Response response = Customer.setDeleteReqToDeleteCustomerAPIWithValidAuth(data);
			System.out.println(response.asString());
			String value = TestUtil.jsonHasValue(response.asString(), "deleted");
			System.out.println("value: "+value);
			Assert.assertEquals(value, "true");
		}
}