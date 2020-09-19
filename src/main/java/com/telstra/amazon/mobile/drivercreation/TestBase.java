package com.telstra.amazon.mobile.drivercreation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.telstra.amazon.mobile.utility.ExtentManager;


public class TestBase {

	protected PageInitiator pageFactory;
	ExtentManager extentmanager = new ExtentManager();
	protected static ThreadLocal<ExtentReports> extentReports = new ThreadLocal<ExtentReports>();
	protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private String currentDirectory = System.getProperty("user.dir");

	/**
	 * This method used to get extent report object
	 * 
	 * @return Extent report object
	 */
	public synchronized static ExtentReports getExtentReport() {
		return extentReports.get();
	}

	/**
	 * This method used to get extent test object
	 * 
	 * @return
	 */
	public synchronized static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	/**
	 * This is called before suite to set log4jProperty
	 * @throws IOException 
	 */
	@BeforeSuite(alwaysRun = true)
	public void setUpBeforeSuite() throws IOException {
		PropertyConfigurator.configure("./log4j.properties");
		FileUtils.cleanDirectory(new File(currentDirectory+"/TestReport/"));
	}

	/**
	 * This is called before test to create automation report
	 */
	@BeforeTest(alwaysRun = true)
	public void setUpBeforeTest() {
		extentReports.set(extentmanager
				.createInstance(System.getProperty("user.dir") + File.separator+"TestReport"+File.separator + "AutomationReport.html"));
		DriverHelperFactory.init();

	}

	/**
	 * Depending upon platform name pageFactory object will be initialised
	 */
	@BeforeClass(alwaysRun = true)
	public void setUpBeforeClass() {
		pageFactory = new PageInitiator();
	}

	/**
	 * This method quites the driver after test
	 */
	@AfterTest(alwaysRun = true)
	public void setUpAfterTest() {
		DriverHelperFactory.getDriver().quit();
	}
}
