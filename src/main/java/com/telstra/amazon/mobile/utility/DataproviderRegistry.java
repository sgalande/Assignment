package com.telstra.amazon.mobile.utility;

import org.testng.annotations.DataProvider;


public class DataproviderRegistry {
	
	private final String excelSheetPath = System.getProperty("user.dir") + "/resources/Data.xlsx";

	/**
	 * Read the data for login function from 'Login' excel sheet
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws Exception {

		Object[][] testObjArray = ExcelUtils.getTableArray(excelSheetPath, "AppLoginData");

		return (testObjArray);

	}
	
}
