package com.telstra.amazon.mobile.pageinterface;

public interface AmazonProductDetailInterface {

	String getProductName();
	String getProductPrice();
	void clickAddToCart();
	void clickProceedToCheckout();
	void clickDeliverToThisAddress();
	void clickContinueButton();
	void selectPaymentOptionAsNetBanking(String bankName);
	boolean isPaymentScreenPresent();
	void clickNetBanking();
	void selectbankOption();
	void selectBank(String bankName);
	void selectDeliveryAndInstallationTimeSlots();
	void goToCart();
	void clickProceedToBuy();
	boolean isAppointmentTimeTextPreset();
	boolean isProductnamePresentOnCheckoutPage(String productName);
	boolean isProductPricePresentOnCheckoutPage(String productPrice);
	boolean isItemDeliverableToAddress();
}
