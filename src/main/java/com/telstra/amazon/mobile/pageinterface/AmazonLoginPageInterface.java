package com.telstra.amazon.mobile.pageinterface;

public interface AmazonLoginPageInterface {
	
	void enterUserName(String username);
	void enterPassword(String password);
	void clickSignInButton();
	void clickContinueButton();
	void clickLoginButton();
	void doLogin(String username, String password);
	

}
