package com.telstra.amazon.mobile.drivercreation;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.telstra.amazon.mobile.utility.ConfigurationReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MobileController extends DriverHelperFactory {

	AppiumDriver<MobileElement> driver;
	DesiredCapabilities capabilities = new DesiredCapabilities();
	FluentWait<AppiumDriver<MobileElement>> wait;
	private static Logger logger = Logger.getLogger(MobileController.class);
	public static String platformName;
	public static String appPackagename;
	public static String appActivityName;
	public static String deviceOs;
	public static String isToBeExecutedOnBrowserStack;
	public static String deviceId;
	public static String deviceName;
	public static String bitrisePlatform;

	/**
	 * This contructor will decide to start the session on which device
	 */
	public MobileController() {
		bitrisePlatform = System.getenv("PLATFORM_NAME");
		Properties configProp = ConfigurationReader.loadProperty("./configuration.properties");

		logger.info("Reading property from local config.property file ");

		platformName = configProp.getProperty("platformName");
		deviceId = configProp.getProperty("deviceId");
		deviceName = configProp.getProperty("deviceName");
		appPackagename = configProp.getProperty("appPackageName");
		appActivityName = configProp.getProperty("appLaunchActivity");
		deviceOs = configProp.getProperty("deviceOs");

		if (platformName.equalsIgnoreCase("Android")) {
			logger.info("Executing Android Test cases on RealDevices");
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("os_version", deviceOs);
			capabilities.setCapability("device_id", deviceId);
			capabilities.setCapability("newCommandTimeout", 12000);
			capabilities.setCapability("appPackage", appPackagename);
			capabilities.setCapability("appActivity", appActivityName);
			// capabilities.setCapability("fullReset", false);
			// capabilities.setCapability("noReset", true);
			try {
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
				logger.info("Session Started successfully");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			wait = new FluentWait<AppiumDriver<MobileElement>>(driver).withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
					.ignoring(TimeoutException.class);
		}
	}

	/**
	 * 
	 * @Description : This method is used to find element
	 * @param by : Element identifier
	 * @return : returns element if found or null if not found
	 */
	@Override
	public WebElement findelement(By by) {
		WebElement element = null;

		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(by));

		} catch (Exception e) {
			logger.info("Unable to find element ::" + by);
		}
		return element;
	}

	/**
	 * 
	 * @Description : This method is used to click on element
	 * @param element : Element on which we need to click
	 */
	@Override
	public void click(WebElement element) {

		if (element != null) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
	}

	/**
	 * 
	 * @Description : Quits the driver
	 */
	@Override
	public void quit() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * 
	 * @Description : This method capture screenshot when test cases fails
	 * @param screenshotName : Screenshot name
	 * @return : Return destination file
	 */
	@Override
	public String captureScreenShot(String screenshotName) {

		logger.info("Capturing Screenshot");
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = "TestReport/" + screenshotName + ".png";
		try {
			FileUtils.copyFile(scr, new File(destination));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return destination;
	}

	/**
	 * 
	 * @Description : This method used to send keys to text box
	 * @param element     : Webelement for which we need to send keys
	 * @param inputString :text to send in textbox
	 */
	@Override
	public void sendKeys(WebElement element, String inputString) {
		if (element != null) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.sendKeys(inputString);
		}
	}

	/**
	 * 
	 * @Description : This method is used to find elements
	 * @param by : Element identifier
	 * @return : Return list of element
	 */
	@Override
	public List<WebElement> findelements(By by) {
		List<WebElement> elements = null;

		try {
			elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (Exception e) {
			logger.info("Unable to find elements :" + by);
		}

		return elements;

	}

	/**
	 * 
	 * @Description : This method is used to swipe device screen using co-ordinated
	 * @param startx : Starting x point
	 * @param starty : Starting y point
	 * @param endx   : End x point
	 * @param endy   : End y point
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void swipeUsingCoordinates(int startx, int starty, int endx, int endy) {

		new TouchAction(driver).press(point(startx, starty)).waitAction(waitOptions(Duration.ofSeconds(2)))
				.moveTo(point(endx, endy)).release().perform();

	}

	/**
	 * 
	 * @Description : This method wait for invisibility of element
	 * @param by : Element identifier
	 */
	@Override
	public void waitForInvibilityOfElement(By by) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

		} catch (Exception e) {
			logger.info("Element is still visible");
		}

	}

	/**
	 * 
	 * @Description : This method is used to scroll till element
	 * @param label : Text till to scroll
	 */
	@Override
	public void scrollToElement(String label) {

		try {
			if (platformName.equalsIgnoreCase("iOS")) {
				RemoteWebElement parent = (RemoteWebElement) findelement(By.className("XCUIElementTypeTable"));
				String parentID = parent.getId();
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", parentID);
				scrollObject.put("predicateString", "label == '" + label + "'");
				driver.executeScript("mobile:scroll", scrollObject);
			} else {
				try {
					((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""
									+ label + "\").instance(0))");
				} catch (Exception e) {
					logger.warn("Failed to scroll");
				}
			}

		} catch (Exception e) {
			logger.info(label + " is not present");
		}

	}

	/**
	 * 
	 * @Description : This method used to click on element
	 * @param by : Element identifier
	 */
	@Override
	public void click(By by) {
		WebElement element = findelement(by);
		if (element != null) {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
	}

	/**
	 * 
	 * @Description : This method used wait for particular time
	 * @param timeout :
	 */
	@Override
	public void waitForTimeOut(int timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			logger.info("Unable to wait for time");
		}
	}

	/**
	 * 
	 * @Description : Used to get the dimension of screen
	 * @return : Return device screen size
	 */
	@Override
	public Dimension getScreenDimension() {
		Dimension size = driver.manage().window().getSize();
		return size;
	}

	/**
	 * @Description : This method used to send key to element
	 */
	@Override
	public void sendKeys(By by, String inputString) {
		WebElement element = findelement(by);
		if (element != null) {
			element.clear();
			element.sendKeys(inputString);
		}

	}
	
	/**
	 * @Description : This method is used to tap on element
	 */
	@Override
	public void tapOnElement(WebElement element) {
		@SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction(driver);
		
		action.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();

	}
}
