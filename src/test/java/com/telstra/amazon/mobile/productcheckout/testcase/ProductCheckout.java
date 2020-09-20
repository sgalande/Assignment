package com.telstra.amazon.mobile.productcheckout.testcase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.telstra.amazon.mobile.drivercreation.TestBase;

public class ProductCheckout extends TestBase {
	
	@Test(description = "To verify product checkout functionality of application")
	public void verifycheckout() {	
	
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
		if(pageFactory.getAmazonSelectdeliveryTimeSlotPage().isItemDeliverableToAddress()) {
			Assert.fail("Selected Product is not deleiverable to my address");
		} else {
			pageFactory.getAmazonProductDetailPage().clickContinueButton();
			pageFactory.getAmazonPaymentpage().selectPaymentOptionAsNetBanking("Kotak Bank");
			pageFactory.getAmazonProductDetailPage().clickContinueButton();
			Assert.assertTrue(pageFactory.getAmazonProductDetailPage().isProductnamePresentOnCheckoutPage(productName),
					"product name is not present on checkout page");
			Assert.assertTrue(pageFactory.getAmazonProductDetailPage().isProductPricePresentOnCheckoutPage(productPrice),
					"product price is not present on checkout page");
		}
		
		
	}

}
