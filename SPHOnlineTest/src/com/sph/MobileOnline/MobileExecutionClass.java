package com.sph.MobileOnline;

import java.io.File;

import com.sph.webOnline.Config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class MobileExecutionClass {
	static AppiumServer server = new AppiumServer();
	@SuppressWarnings("rawtypes")
	static AppiumDriver driver ;
	
	/**
	 * <b> Main method to run create driver and run test cases </b>
	 * 
	 
	 */
	
	public static void main(String[] args) {
	
		TestCasesClass tc =new TestCasesClass(createDriver(Config.PLATFORM));
		tc.runTestCases();
		server.stopServer();
		System.out.println("Evidence are stored in "+UtilConstants.SCREENSHOTSPATH+File.separator+"ScreenShots"+File.separator);
	}
	
	/**
	 * <b> Create Appium driver instance  </b>
	 * 
	 * @param platform
	 *           mobile platform
	 *
	 * @return returns appium driver
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private static AppiumDriver createDriver(String platform) {
		try {
			if (platform.equalsIgnoreCase("android"))
				driver = new AndroidDriver<>(server.startServer(), server.setDC());
			else
				driver = new IOSDriver<>(server.startServer(), server.setDC());
		} catch (Exception e) {
			System.out.println("failed to create a driver " + e.getLocalizedMessage());
		}
	return driver;
	}
	

}
