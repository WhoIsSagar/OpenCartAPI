package com.petstore.api.testdata;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class FetchExcelData {

	
	@DataProvider(name = "fetchAllData")
	public Object[][] allDataFetching() throws IOException{
		
		FileInputStream ip = new FileInputStream("/Users/simran/eclipse-workspace/PetStore_Api/src/test/java/com/petstore/api/testdata/book1.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(ip);
		XSSFSheet sheet = book.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		int cells = sheet.getRow(0).getLastCellNum();	
		Object[][] data = new Object[rows][cells];
		for(int i=0; i<rows; i++) {
			XSSFRow currentRow =  sheet.getRow(i+1);
			for (int j=0; j<cells; j++) {
				XSSFCell currentCell = currentRow.getCell(j);
				if (currentCell != null) {
	                CellType celltype = currentCell.getCellType();
	                
	                switch (celltype) {
	                    case STRING:
	                        data[i][j] = currentCell.getStringCellValue();
	                        break;
	                    case NUMERIC:
	                        data[i][j] = currentCell.getNumericCellValue();
	                        break;
	                    case BOOLEAN:
	                        data[i][j] = currentCell.getBooleanCellValue();
	                        break;
	                }
	            } else {
	               
	                data[i][j] = ""; 
	            }
			
			}
			
		}
		return data;
		
		
	}
	@DataProvider(name = "fetchUserName")
	public Object[][] userNameFetch() throws IOException {
	    FileInputStream ip = new FileInputStream("/Users/simran/eclipse-workspace/PetStore_Api/src/test/java/com/petstore/api/testdata/book1.xlsx");
	    XSSFWorkbook book = new XSSFWorkbook(ip);
	    XSSFSheet sheet = book.getSheet("Sheet1");
	    int rows = sheet.getLastRowNum();
	    Object[][] data = new Object[rows][1]; // One column for the username
	    
	    for (int i = 0; i < rows; i++) {
	        XSSFRow currentRow = sheet.getRow(i + 1);
	        XSSFCell currentCell = currentRow.getCell(1); // Fetching from the 2nd column
	        if (currentCell != null) {
	            CellType celltype = currentCell.getCellType();
	            
	            switch (celltype) {
	                case STRING:
	                    data[i][0] = currentCell.getStringCellValue();
	                    break;
	                case NUMERIC:
	                    data[i][0] = Double.toString(currentCell.getNumericCellValue());
	                    break;
	                case BOOLEAN:
	                    data[i][0] = currentCell.getBooleanCellValue();
	                    break;
	                default:
	                    data[i][0] = ""; // Handle other cell types as needed
	                    break;
	            }
	        } else {
	            data[i][0] = ""; // Handle null cell values here
	        }
	    }
	    return data;
	}
	
}

