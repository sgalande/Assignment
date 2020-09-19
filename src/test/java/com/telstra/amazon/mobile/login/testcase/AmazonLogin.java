package com.telstra.amazon.mobile.login.testcase;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.telstra.amazon.mobile.drivercreation.TestBase;
import com.telstra.amazon.mobile.utility.DataproviderRegistry;
import com.telstra.amazon.mobile.utility.ReportGenerator;



@Listeners(ReportGenerator.class)
public class AmazonLogin extends TestBase {
	
	@Test(dataProviderClass = DataproviderRegistry.class , 
			dataProvider="loginData",groups = {"Login","Sanity" }, 
			description = "To verify login functionality of application")
	public void verify_Login(String username,String password) {	
		
		pageFactory.getAmazonLoginPage().doLogin(username, password);
		pageFactory.getAmazonSearchPage().isHomePageDisplayed();
		pageFactory.getAmazonSearchPage().clickSearchTextBox();
		pageFactory.getAmazonSearchPage().searchForProduct("65-inch tv");
		pageFactory.getAmazonSearchPage().selectProductFromDropDown();
		pageFactory.getAmazonSearchPage().selectProductFromSearchResults();
		String productName = pageFactory.getAmazonProductDetailPage().getProductName();
		String productPrice = pageFactory.getAmazonProductDetailPage().getProductPrice();
		System.out.println(productName + "     "+productPrice);
		pageFactory.getAmazonProductDetailPage().clickAddToCart();
		pageFactory.getAmazonProductDetailPage().clickProceedToCheckout();
		pageFactory.getAmazonProductDetailPage().clickDeliverToThisAddress();
		if(pageFactory.getAmazonProductDetailPage().isItemDeliverableToAddress()) {
			Assert.fail("Selected Product is not deleiverable to my address");
		} else {
			pageFactory.getAmazonProductDetailPage().clickContinueButton();
			pageFactory.getAmazonProductDetailPage().selectPaymentOptionAsNetBanking("HDFC Bank");
			pageFactory.getAmazonProductDetailPage().clickContinueButton();
			Assert.assertTrue(pageFactory.getAmazonProductDetailPage().isProductnamePresentOnCheckoutPage(productName),
					"product name is not present on checkout page");
			Assert.assertTrue(pageFactory.getAmazonProductDetailPage().isProductPricePresentOnCheckoutPage(productPrice),
					"product price is not present on checkout page");
		}
		
	}
}
