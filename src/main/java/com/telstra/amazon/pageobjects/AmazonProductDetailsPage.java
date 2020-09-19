package com.telstra.amazon.pageobjects;

import java.util.List;

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
	private static By CONTINIUE = By.xpath(".//android.widget.Button[contains(@text,'Continue')]");
	private static By NET_BANKING = By.xpath(".//android.view.View[@text='Net Banking']");
	private static By CHOOSE_AN_BANK_OPTION = By.xpath(".//android.widget.Spinner[@text='Choose an Option']");
	private static By SELECT_TIME_SLOT = By.xpath(".//android.view.View[@resource-id='AQdjx6gEAAAAAD5BelEAAAAAXUgAAAAAAAA=;ATS;0^0^INR;']");
	private static By SELECT_INSTALLATION_TIME = By.xpath(".//android.view.View[contains(@resource-id,'s-1600')]");
	private static By GO_TO_CART = By.xpath(".//android.widget.ImageView[@content-desc='Cart']");
	private static By PROCEED_TO_BUY = By.xpath(".//android.widget.Button[@text='Proceed to Buy']");
	private static By APPOINTMET_TIME_TEXT = By.xpath(".//android.view.View[@text='Please choose appointment times for your service order(s)']"); 
	private static By SELECT_PAYMENT_SCREEN = By.xpath(".//anroid.view.View[@text='Select a payment method']");
	private static By ITEMDELIVERABLETOADDRESS = By.xpath(".//android.view.View[contains(@text,'Some items in your order are not deliverable to the selected address.')]");
	private static By DELETEFROMCART = By.xpath(".//android.widget.Button[contains(@text,'Delete')]");
	
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
	public void clickNetBanking() {
		DriverHelperFactory.getDriver().scrollToElement("Net Banking");
		DriverHelperFactory.getDriver().click(NET_BANKING);
		
	}

	@Override
	public void selectbankOption() {
		DriverHelperFactory.getDriver().click(CHOOSE_AN_BANK_OPTION);
		
	}

	@Override
	public void selectBank(String bankName) {
		DriverHelperFactory.getDriver().click(By.xpath(".//android.view.View[@text='"+bankName+"']"));
		
	}

	@Override
	public void selectDeliveryAndInstallationTimeSlots() {
		
		DriverHelperFactory.getDriver().click(SELECT_TIME_SLOT);
		DriverHelperFactory.getDriver().scrollToElement("Continue");
		List<WebElement> availableTimeslots = DriverHelperFactory.getDriver().findelements(SELECT_INSTALLATION_TIME);
		
		if(availableTimeslots!=null) {
			int count = 0;
			int i = 0;
			do {
				
				DriverHelperFactory.getDriver().click(availableTimeslots.get(i));
				if(count == availableTimeslots.size()) {
					break;
				}
				i++;
			} while (isAppointmentTimeTextPreset());
			
		}
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
	public boolean isAppointmentTimeTextPreset() {
		
		if(DriverHelperFactory.getDriver().findelement(APPOINTMET_TIME_TEXT)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void selectPaymentOptionAsNetBanking(String bankName) {
		if(isPaymentScreenPresent()) {
			clickNetBanking();
			selectbankOption();
			selectBank(bankName);
		} else {
			selectDeliveryAndInstallationTimeSlots();
			clickContinueButton();
			clickNetBanking();
			selectbankOption();
			selectBank(bankName);
		}
		
	}

	@Override
	public boolean isPaymentScreenPresent() {
		
	if(DriverHelperFactory.getDriver().findelement(SELECT_PAYMENT_SCREEN)!=null) {
		return true;
	} 
	return false;
	
	}

	@Override
	public boolean isProductnamePresentOnCheckoutPage(String productName) {
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

	@Override
	public boolean isItemDeliverableToAddress() {

		if (DriverHelperFactory.getDriver().findelement(ITEMDELIVERABLETOADDRESS) != null) {
			DriverHelperFactory.getDriver().click(DELETEFROMCART);
			return true;
		}
		return false;
	}
	
	

}
