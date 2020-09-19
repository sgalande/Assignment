package com.telstra.amazon.mobile.utility;

import java.io.IOException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.telstra.amazon.mobile.drivercreation.DriverHelperFactory;
import com.telstra.amazon.mobile.drivercreation.TestBase;


public class ReportGenerator extends TestBase implements ISuiteListener,ITestListener {
	
	
	/**
	 * This method is called on start of test
	 */
	public void onTestStart(ITestResult result) {
		
	  extentTest.set(getExtentReport().createTest(result.getMethod().getMethodName()+"<h6>","<br/>" +"<h5 style=\"color:DodgerBlue;\">"+result.getMethod().getDescription()+"<h5>"));
	}

	/**
	 * This method is called on success of test case
	 */
	public void onTestSuccess(ITestResult result) {
		
		getExtentTest().log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName() +" is successful",ExtentColor.GREEN));
	}

	/**
	 * This method is called on failure of test case
	 */
	public void onTestFailure(ITestResult result) {
		
		getExtentTest().log(Status.FAIL, result.getThrowable().getLocalizedMessage());
		getExtentTest().log(Status.FAIL, MarkupHelper.createLabel("Failed to "+result.getMethod().getMethodName(), ExtentColor.RED));
		try {
		
			String screenshotName = result.getMethod().getMethodName();
			DriverHelperFactory.getDriver().captureScreenShot(result.getMethod().getMethodName());  
			getExtentTest().log(Status.FAIL, result.getMethod().getMethodName(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotName+".png").build());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	/**
	 * This method is called on test skipped
	 */
	public void onTestSkipped(ITestResult result) {
		
		getExtentTest().log(Status.SKIP, result.getMethod().getMethodName());
		getExtentTest().log(Status.SKIP, result.getThrowable().getLocalizedMessage());
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

    /**
     * This method is called on finish of test cases	
     */
	public void onFinish(ITestContext context) {
		try {
			
			getExtentReport().flush();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	
	public void onFinish(ISuite suite) {
		
	}

}
