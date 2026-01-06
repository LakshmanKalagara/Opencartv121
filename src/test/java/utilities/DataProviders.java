package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path=".\\testData\\OpenCart_TestData.xlsx";
		ExcelUtils xlutil=new ExcelUtils(path);
		
		int rowCount=xlutil.getRowCount("Sheet1");
		int colCount=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
	return logindata;
		
		
	
		
	}

}
