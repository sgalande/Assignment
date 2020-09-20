package com.telstra.amazon.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.telstra.amazon.mobile.drivercreation.DriverHelperFactory;
import com.telstra.amazon.mobile.pageinterface.AmazonDeliveryTimeSlotInterface;

public class AmazonSelectDeliveryTimeSlot implements AmazonDeliveryTimeSlotInterface {
	
	private static By APPOINTMET_TIME_TEXT = By.xpath(".//android.view.View[@text='Please choose appointment times for your service order(s)']"); 
	private static By ITEMDELIVERABLETOADDRESS = By.xpath(".//android.view.View[contains(@text,'Some items in your order are not deliverable to the selected address.')]");
	private static By DELETEFROMCART = By.xpath(".//android.widget.Button[contains(@text,'Delete')]");
	private static By SELECT_INSTALLATION_TIME = By.xpath(".//android.view.View[contains(@resource-id,'s-1600')]");
	private static By ANDROID_OK_BUTTON = By.id("android:id/button1");
	
	@Override
	public void selectDeliveryAndInstallationTimeSlots() {
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
	public boolean isItemDeliverableToAddress() {

		if (DriverHelperFactory.getDriver().findelement(ITEMDELIVERABLETOADDRESS) != null) {
			DriverHelperFactory.getDriver().click(DELETEFROMCART);
			DriverHelperFactory.getDriver().click(ANDROID_OK_BUTTON);
			return true;
		}
		return false;
	}
	

	@Override
	public boolean isAppointmentTimeTextPreset() {
		
		if(DriverHelperFactory.getDriver().findelement(APPOINTMET_TIME_TEXT)!=null) {
			return true;
		}
		return false;
	}


}
