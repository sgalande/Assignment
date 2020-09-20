package com.telstra.amazon.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telstra.amazon.mobile.drivercreation.DriverHelperFactory;
import com.telstra.amazon.mobile.pageinterface.AmazonProductDetailInterface;

public class AmazonProductDetailsPage implements AmazonProductDetailInterface {

	private static By PRODUCT_NAME = By.xpath(".//android.view.View[@resource-id='title_feature_div']/android.view.View[1]");
	private static By PRODUCT_PRICE = By.xpath(".//android.widget.EditText[contains(@text,'rupees')]");
	private static By ADD_TO_CART = By.xpath(".//android.widget.Button[@resource-id='add-to-cart-button']");
	private static By PROCEED_TO_CHECKOUT = By.xpath(".//android.widget.Button[@text='Proceed to checkout']");
	private static By SELECT_DELIVERY_ADDRESS = By.xpath(".//android.widget.Button[contains(@text,'Deliver to this address') or contains(@text,'Use this address') ]");
	private static By CONTINIUE = By.xpath(".//android.widget.Button[@text='Continue']");
	
	private static By GO_TO_CART = By.xpath(".//android.widget.ImageView[@content-desc='Cart']");
	private static By PROCEED_TO_BUY = By.xpath(".//android.widget.Button[@text='Proceed to Buy']");
	
	
	
	@Override
	public String getProductName() {
		String productname = "";
		 WebElement element = DriverHelperFactory.getDriver().findelement(PRODUCT_NAME);
		 if(element != null) {
			productname = element.getText();
		 }
		return productname;
	}

	@Override
	public String getProductPrice() {
		
		String productprice = "";
		 WebElement element = DriverHelperFactory.getDriver().findelement(PRODUCT_PRICE);
		 if(element != null) {
			productprice = element.getText().split(" ")[1]+".00";
			
		 }
		 
		return productprice;
	}

	@Override
	public void clickAddToCart() {
		DriverHelperFactory.getDriver().scrollToElement("Add to Cart");
		DriverHelperFactory.getDriver().click(ADD_TO_CART);
		
	}

	@Override
	public void clickProceedToCheckout() {
		
		WebElement checkOutButton = DriverHelperFactory.getDriver().findelement(PROCEED_TO_CHECKOUT);
		if(checkOutButton != null) {
			checkOutButton.click();
		} else {
			goToCart();
			clickProceedToBuy();
		}
	}

	@Override
	public void clickDeliverToThisAddress() {
		DriverHelperFactory.getDriver().click(SELECT_DELIVERY_ADDRESS);
		
		
	}

	@Override
	public void clickContinueButton() {
		DriverHelperFactory.getDriver().click(CONTINIUE);
		
	}

	@Override
	public void goToCart() {
		DriverHelperFactory.getDriver().click(GO_TO_CART);
		
	}

	@Override
	public void clickProceedToBuy() {
		DriverHelperFactory.getDriver().click(PROCEED_TO_BUY);
		
	}

	

	@Override
	public boolean isProductnamePresentOnCheckoutPage(String productName) {
		DriverHelperFactory.getDriver().scrollToElement(productName);
		if(DriverHelperFactory.getDriver().findelement(By.xpath(".//android.view.View[contains(@text,'"+productName+"')]"))!= null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isProductPricePresentOnCheckoutPage(String productPrice) {
		if(DriverHelperFactory.getDriver().findelement(By.xpath(".//android.view.View[@text='"+productPrice+"']"))!= null) {
			return true;
		}
		return false;
	}

	
	

}
