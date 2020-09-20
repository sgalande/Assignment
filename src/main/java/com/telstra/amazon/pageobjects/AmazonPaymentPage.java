package com.telstra.amazon.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telstra.amazon.mobile.drivercreation.DriverHelperFactory;
import com.telstra.amazon.mobile.pageinterface.AmazonPaymentpageInterfacee;

public class AmazonPaymentPage implements AmazonPaymentpageInterfacee {
	
	private static By NET_BANKING = By.xpath(".//android.view.View[@text='Net Banking']");
	private static By CHOOSE_AN_BANK_OPTION = By.xpath(".//android.widget.Spinner[@text='Choose an Option']");
///	private static By SELECT_TIME_SLOT = By.xpath(".//android.view.View[@resource-id='AQdjx6gEAAAAAD5BelEAAAAAXUgAAAAAAAA=;ATS;0^0^INR;']");
	private static By SELECT_PAYMENT_SCREEN = By.xpath(".//android.webkit.WebView[@text='Select a Payment Method']");
	private AmazonSelectDeliveryTimeSlot selectTimeSlot = new AmazonSelectDeliveryTimeSlot();
	private AmazonProductDetailsPage detailpage = new AmazonProductDetailsPage();
	
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
	public void selectPaymentOptionAsNetBanking(String bankName) {
		if(isPaymentScreenPresent()) {
			clickNetBanking();
			selectbankOption();
			selectBank(bankName);
			DriverHelperFactory.getDriver().waitForTimeOut(3);
			DriverHelperFactory.getDriver().swipeVertical_bottomToTop();
			DriverHelperFactory.getDriver().waitForTimeOut(2);
			clickNetBanking();
			

		} else {
			selectTimeSlot.selectDeliveryAndInstallationTimeSlots();
			detailpage.clickContinueButton();
			clickNetBanking();
			selectbankOption();
			selectBank(bankName);
			DriverHelperFactory.getDriver().waitForTimeOut(3);
			DriverHelperFactory.getDriver().swipeVertical_bottomToTop();
			DriverHelperFactory.getDriver().waitForTimeOut(2);
			clickNetBanking();
			
		}
		
	}

	@Override
	public boolean isPaymentScreenPresent() {
	if(DriverHelperFactory.getDriver().findelement(SELECT_PAYMENT_SCREEN)!=null) {
		return true;
	} 
	return false;
	
	}
}
