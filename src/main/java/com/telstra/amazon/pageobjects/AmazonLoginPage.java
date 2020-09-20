package com.telstra.amazon.pageobjects;

import org.openqa.selenium.By;

import com.telstra.amazon.mobile.drivercreation.DriverHelperFactory;
import com.telstra.amazon.mobile.pageinterface.AmazonLoginPageInterface;


public class AmazonLoginPage implements AmazonLoginPageInterface {
	
	private static By SIGN_INBUTTON = By.id("com.amazon.mShop.android.shopping:id/sign_in_button");
	private static By USER_NAME_TEXTBOX = By.xpath(".//android.widget.EditText[@resource-id='ap_email_login']");
	private static By CONTINUE_BUTTON = By.xpath(".//android.widget.Button[@resource-id='continue']");
	private static By PASSWORD_TEXTBOX = By.xpath(".//android.widget.EditText[@resource-id='ap_password']");
	private static By LOGIN_BUTTON = By.xpath(".//android.widget.Button[@resource-id='signInSubmit']");
	private static By SIGNED_IN_GREETING = By.id("com.amazon.mShop.android.shopping:id/gno_greeting_text_view");
	private static By NAVIGATION_PANNEL = By.id(".//android.widget.ImageView[@content-desc='Navigation panel']");
	
	
	public void enterUserName(String username) {
		DriverHelperFactory.getDriver().sendKeys(USER_NAME_TEXTBOX, username);
		
	}

	public void enterPassword(String password) {
		DriverHelperFactory.getDriver().sendKeys(PASSWORD_TEXTBOX, password);
		
	}

	public void clickSignInButton() {
		DriverHelperFactory.getDriver().click(SIGN_INBUTTON);
		
	}

	public void clickContinueButton() {
		DriverHelperFactory.getDriver().click(CONTINUE_BUTTON);
		
	}

	public void doLogin(String username, String password) {
		clickSignInButton();
		enterUserName(username);
		clickContinueButton();
		enterPassword(password);
		clickLoginButton();
		
	}

	public void clickLoginButton() {
		DriverHelperFactory.getDriver().click(LOGIN_BUTTON);
		
	}

	@Override
	public void clickNavigationPannel() {
		DriverHelperFactory.getDriver().click(NAVIGATION_PANNEL);
		
	}

	@Override
	public boolean isuserLoggedIn() {
		if(DriverHelperFactory.getDriver().findelement(SIGNED_IN_GREETING)!=null) {
			DriverHelperFactory.getDriver().pressDeviceBackButton();
			return true;
		}
		return false;
	}

}
