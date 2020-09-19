package com.telstra.amazon.mobile.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.testng.log4testng.Logger;

public class ConfigurationReader {

	
	public static Properties prop = new Properties();
	public static InputStream input = null;

	private static Logger logger = Logger.getLogger(ConfigurationReader.class);

	/**
	 * Load the property file mentioned in filepath
	 * @param filePath
	 * @return
	 */
	public static Properties loadProperty(String filePath) {
		try {
			logger.info("Loading property File");
			input = new FileInputStream(filePath);
			prop.load(input);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}

}
