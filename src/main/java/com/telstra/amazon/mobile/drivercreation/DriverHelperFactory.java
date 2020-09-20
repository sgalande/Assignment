package com.telstra.amazon.mobile.drivercreation;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public abstract class DriverHelperFactory {

	public static DriverHelperFactory driverHelperFactory;

	public static DriverHelperFactory getDriver() {
		return driverHelperFactory;
	}

	public static void init() {
		driverHelperFactory = new MobileController();
	}

	public abstract WebElement findelement(By by);

	public abstract void click(WebElement element);

	public abstract void click(By by);

	public abstract void quit();

	public abstract String captureScreenShot(String screenShotName);

	public abstract void sendKeys(WebElement element, String inputString);

	public abstract void sendKeys(By by, String inputString);

	public abstract List<WebElement> findelements(By by);

	public abstract void swipeUsingCoordinates(int startx, int starty, int endx, int endy);

	public abstract void waitForInvibilityOfElement(By by);

	public abstract void scrollToElement(String label);

	public abstract void waitForTimeOut(int timeout);

	public abstract Dimension getScreenDimension();

	public abstract void swipeVertical_bottomToTop();
	
	public abstract void pressDeviceBackButton();
	
}
