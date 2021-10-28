package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public String filePath;
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	public ExcelReader(String filePath) {
		this.filePath = filePath;
		try {
				fis = new FileInputStream(filePath);
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);
				
				fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static XSSFWorkbook getWorkBook() {
		return workbook;
	}
	
	public static XSSFSheet getSpreadSheet() {
		return sheet;
	}
	
	public static Object[][] getData(Method method) {
		return null;
	}
	
	public int getRowCount(XSSFSheet sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else {
			sheet= workbook.getSheetAt(index);
			int number = sheet.getLastRowNum()+1;
			return number;
		}
	}
	
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int rowCount = sheet.getLastRowNum();
			return rowCount;
		} 
	}

	/**
	 * Reads and returns test data
	 * @param sheetName
	 * @param colName
	 * @param testCaseRowNum
	 * @return
	 */
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
				if(rowNum < 0) 
					return "";
				int index = workbook.getSheetIndex(sheetName);
				int colNum = -1;
				if(index==-1)	
					return "";
				
				sheet = workbook.getSheetAt(index);
				row = sheet.getRow(0);
				for(int i=0;i<row.getLastCellNum();i++) {
					if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
						colNum = i;
					}
				}
				if(colNum ==-1) {
					return "";
				}
				
				sheet = workbook.getSheetAt(index);
				row=sheet.getRow(rowNum-1);
				if(row==null)
					return "";
				
				cell = row.getCell(colNum);
				if(cell == null) {
					return "";
				}
				
				if(cell.getCellType() == CellType.STRING)
					return cell.getStringCellValue();
				
				else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() ==CellType.FORMULA) {
					String cellText =  String.valueOf(cell.getNumericCellValue());
					if(HSSFDateUtil.isCellDateFormatted(cell)) {
						double d = cell.getNumericCellValue();
						Calendar cal = Calendar.getInstance();
						cal.setTime(HSSFDateUtil.getJavaDate(d));
						cellText = String.valueOf(cal.get(Calendar.YEAR)).substring(2);
						cellText = cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+1+"/"+cellText;
					}
					return cellText;
				}
				
				else if(cell.getCellType()==CellType.BLANK) {
					return "";
				}
				else 
					  return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}

	public String getCellData(String sheetName,int colNum, int rowNum) {
		try {
			if(rowNum <= 0) return "";
			int index = workbook.getSheetIndex(sheetName);
			if(index ==-1)	return "";
			
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row == null) return "";
			
			cell = row.getCell(colNum);
			if(cell ==null)	return "";
			
			if(cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText  = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
			           // format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal =Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH)+1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
				}
				return cellText;
			}
			else if(cell.getCellType()==CellType.BLANK)
				return "";
			else 
				return String.valueOf(cell.getBooleanCellValue());
			}
			catch(Exception e){
				e.printStackTrace();
				return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
			}
	}
}