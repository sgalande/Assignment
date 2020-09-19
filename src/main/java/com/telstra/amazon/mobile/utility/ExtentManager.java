package com.telstra.amazon.mobile.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

private ExtentReports extentReports;
	
	/**
	 * Create report html file
	 * @param filePath
	 * @return
	 */
	public ExtentReports createInstance(String filePath) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	    htmlReporter.config().setChartVisibilityOnOpen(true);
	    htmlReporter.config().setTheme(Theme.DARK);
	    htmlReporter.config().setDocumentTitle("");
	    htmlReporter.config().setEncoding("utf-8");
	    htmlReporter.config().setReportName("EmailUtility");
	    
	   
	    extentReports = new ExtentReports();
	    extentReports.attachReporter(htmlReporter);
	    
	    return extentReports;
		
	}
}
