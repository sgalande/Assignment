package com.telstra.amazon.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import com.telstra.amazon.mobile.drivercreation.DriverHelperFactory;
import com.telstra.amazon.mobile.pageinterface.AmazonSearchPageInterface;

public class AmazonSearchPage implements AmazonSearchPageInterface {

	private static By SEARCH_TEXT_BOX = By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
	private static By SEARCH_PRODUCT_DROPDOWN =By.
			id("com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_suggestions");
	private static By COUNT_OF_SERACH_PRODUCTS = By.id("com.amazon.mShop.android.shopping:id/rs_results_count_text");
	
	public void clickSearchTextBox() {
		DriverHelperFactory.getDriver().click(SEARCH_TEXT_BOX);
		
	}

	public void searchForProduct(String productName) {
		DriverHelperFactory.getDriver().sendKeys(SEARCH_TEXT_BOX, productName);
		
	}

	
	public boolean isHomePageDisplayed() {
		
		return DriverHelperFactory.getDriver().findelement(SEARCH_TEXT_BOX).isDisplayed();
	}

	public void selectProductFromDropDown() {
		
		 List<WebElement> dropDownList = DriverHelperFactory.getDriver().findelements(SEARCH_PRODUCT_DROPDOWN);
		 
		 System.out.println(dropDownList.size());
		 
		 int index = getRandomInteger(1, dropDownList.size());
		 
		 DriverHelperFactory.getDriver().click(dropDownList.get(index-1));
	}
	
	public  int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	

	public int getCountOfSearchProducts() {
		WebElement element = DriverHelperFactory.getDriver().findelement(COUNT_OF_SERACH_PRODUCTS);
		int count = 0; 
		if(element != null) {
			count  = Integer.parseInt(element.getText().split(" ")[0]);
		}
		return count;
	}

	public void selectProductFromSearchResults() {
		int searchresult = getCountOfSearchProducts();
		
		if(searchresult >0) {
			getRandomInteger(1, searchresult);
		}
		
		 Dimension size = DriverHelperFactory.getDriver().getScreenDimension();
		 
		WebElement listView = DriverHelperFactory.getDriver().findelement(By.id("com.amazon.mShop.android.shopping:id/rs_vertical_stack_view"));
		int startX = listView.getSize().getWidth()/2;
		System.out.println(size.getWidth() + " "+size.getHeight());
		int startY = size.getHeight() - listView.getLocation().getY()-346;
		int endX = startX;
		int endY = listView.getLocation().getY();
		DriverHelperFactory.getDriver().swipeUsingCoordinates(startX, startY, endX, endY);
		
		DriverHelperFactory.getDriver().findelements(By.id("com.amazon.mShop.android.shopping:id/list_product_linear_layout")).get(1).click();
		
	}
	
	

}
