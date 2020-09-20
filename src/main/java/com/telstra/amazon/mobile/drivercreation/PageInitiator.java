package com.telstra.amazon.mobile.drivercreation;

import com.telstra.amazon.pageobjects.AmazonLoginPage;
import com.telstra.amazon.pageobjects.AmazonPaymentPage;
import com.telstra.amazon.pageobjects.AmazonProductDetailsPage;
import com.telstra.amazon.pageobjects.AmazonSearchPage;
import com.telstra.amazon.pageobjects.AmazonSelectDeliveryTimeSlot;

public class PageInitiator {

	
	private AmazonLoginPage amazonLoginPage;
	private AmazonSearchPage amazonSearchPage;
	private AmazonProductDetailsPage amazonProductDetailPage;
	private AmazonSelectDeliveryTimeSlot amazonSelectdeliveryTimeSlotPage;
	private AmazonPaymentPage amazonPaymentpage;
	
	public AmazonSelectDeliveryTimeSlot getAmazonSelectdeliveryTimeSlotPage() {
		return amazonSelectdeliveryTimeSlotPage;
	}

	public void setAmazonSelectdeliveryTimeSlotPage(AmazonSelectDeliveryTimeSlot amazonSelectdeliveryTimeSlotPage) {
		this.amazonSelectdeliveryTimeSlotPage = amazonSelectdeliveryTimeSlotPage;
	}

	public AmazonPaymentPage getAmazonPaymentpage() {
		return amazonPaymentpage;
	}

	public void setAmazonPaymentpage(AmazonPaymentPage amazonPaymentpage) {
		this.amazonPaymentpage = amazonPaymentpage;
	}

	
	
	public AmazonProductDetailsPage getAmazonProductDetailPage() {
		return amazonProductDetailPage;
	}

	public void setAmazonProductDetailPage(AmazonProductDetailsPage amazonProductDetailPage) {
		this.amazonProductDetailPage = amazonProductDetailPage;
	}

	public AmazonSearchPage getAmazonSearchPage() {
		return amazonSearchPage;
	}

	public void setAmazonSearchPage(AmazonSearchPage amazonSearchPage) {
		this.amazonSearchPage = amazonSearchPage;
	}

	public AmazonLoginPage getAmazonLoginPage() {
		return amazonLoginPage;
	}

	public void setAmazonLoginPage(AmazonLoginPage amazonLoginPage) {
		this.amazonLoginPage = amazonLoginPage;
	}

	public PageInitiator() {
		
		setAmazonLoginPage(new AmazonLoginPage());
		setAmazonSearchPage(new AmazonSearchPage());
		setAmazonProductDetailPage(new AmazonProductDetailsPage());
		setAmazonPaymentpage(new AmazonPaymentPage());
		setAmazonSelectdeliveryTimeSlotPage(new AmazonSelectDeliveryTimeSlot());
	}

}
