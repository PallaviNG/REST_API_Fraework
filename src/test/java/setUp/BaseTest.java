package setUp;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import utils.ExcelReader;

public class BaseTest {
		public static Properties config = new Properties();
		private FileInputStream fis;
		public static ExcelReader excel = new ExcelReader("E:\\Software Engineering\\Projects\\Selenium Workspace\\APIAutomation\\src\\test\\resources\\TestCases.xlsx");
		
		@BeforeSuite
		public void setUp() {
			try {
				fis = new FileInputStream(".\\src\\test\\resources\\config.properties");
				config.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RestAssured.baseURI = config.getProperty("baseURI");
			System.out.println(RestAssured.baseURI);
		}
}
