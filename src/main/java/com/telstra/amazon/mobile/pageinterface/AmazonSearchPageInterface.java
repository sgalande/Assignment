package com.telstra.amazon.mobile.pageinterface;

public interface AmazonSearchPageInterface {
	
	void clickSearchTextBox();
	void searchForProduct(String productName);
	boolean isHomePageDisplayed();
	void selectProductFromDropDown();
	void selectProductFromSearchResults();
	int getCountOfSearchProducts();

}
