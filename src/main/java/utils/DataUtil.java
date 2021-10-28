package utils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class DataUtil {
		public static String excelFileName = "E:\\Software Engineering\\Projects\\Selenium Workspace\\APIAutomation\\src\\test\\resources\\TestCases.xlsx";
		public static ExcelReader excel = new ExcelReader(excelFileName);
		
		@DataProvider(name= "TC")
		public static Object[][] getData(Method method){
			int rows = excel.getRowCount("testData");
			System.out.println("Number of rows are: "+rows);
			
			String testName = method.getName();
			
			int testCaseRowNum = -1;	//Initialize with default value
			for(testCaseRowNum=1; testCaseRowNum<=rows; testCaseRowNum++) {
				String testCaseName = excel.getCellData("testData",0, testCaseRowNum);
				if(testCaseName.equalsIgnoreCase(testName))
					break;
			}
			
			int dataStartRowNum = testCaseRowNum +2;
			int testRows = 	0;
			while(!excel.getCellData("testData",0, dataStartRowNum + testRows).equals("")) {
				testRows++;
			}
			
			int colStartColNum = testCaseRowNum +1;
			int testCols = 0;
			while(!excel.getCellData("testData", testCols, colStartColNum).equals("")) {
				testCols++;
			}	
			
			Object[][] data = new Object[testRows][1];
			int i=0;
			for(int rNum = dataStartRowNum; rNum<(dataStartRowNum+testRows);rNum++) {
				Hashtable<String, String> table = new Hashtable<String,String>();
				for(int cNum = 0; cNum < testCols; cNum++) {
					String testData = excel.getCellData("testData", cNum, rNum);
					String colName = excel.getCellData("testData", cNum, colStartColNum);
					table.put(colName, testData);
				}
				data[i][0] = table;	
				i++;
			}
			return data;
		}
}