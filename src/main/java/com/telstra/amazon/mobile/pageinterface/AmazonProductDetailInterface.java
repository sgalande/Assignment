package com.telstra.amazon.mobile.pageinterface;

public interface AmazonProductDetailInterface {

	String getProductName();
	String getProductPrice();
	void clickAddToCart();
	void clickProceedToCheckout();
	void clickDeliverToThisAddress();
	void clickContinueButton();
	
	void goToCart();
	void clickProceedToBuy();
	boolean isProductnamePresentOnCheckoutPage(String productName);
	boolean isProductPricePresentOnCheckoutPage(String productPrice);
}
