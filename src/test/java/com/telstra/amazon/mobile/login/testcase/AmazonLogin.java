package com.telstra.amazon.mobile.login.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.telstra.amazon.mobile.drivercreation.TestBase;
import com.telstra.amazon.mobile.utility.DataproviderRegistry;



public class AmazonLogin extends TestBase {
	
	@Test(dataProviderClass = DataproviderRegistry.class ,dataProvider="loginData", description = "To verify login functionality of application")
	public void verify_Login(String username,String password) {	
		
		pageFactory.getAmazonLoginPage().doLogin(username, password);
		pageFactory.getAmazonLoginPage().clickNavigationPannel();
		Assert.assertTrue(pageFactory.getAmazonLoginPage().isuserLoggedIn(),"Login Not successful");
	
	}
}
