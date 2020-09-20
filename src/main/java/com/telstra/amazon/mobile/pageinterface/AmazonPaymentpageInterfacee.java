package com.telstra.amazon.mobile.pageinterface;

public interface AmazonPaymentpageInterfacee {

	void selectPaymentOptionAsNetBanking(String bankName);
	boolean isPaymentScreenPresent();
	void clickNetBanking();
	void selectbankOption();
	void selectBank(String bankName);

}
