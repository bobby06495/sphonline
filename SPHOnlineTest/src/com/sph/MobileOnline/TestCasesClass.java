package com.sph.MobileOnline;

import com.sph.webOnline.Config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TestCasesClass {

	@SuppressWarnings("rawtypes")
	static AppiumDriver driver;
	
	@SuppressWarnings("rawtypes")
	public TestCasesClass (AppiumDriver driver) {
		
		TestCasesClass.driver = driver;
	}
	
	/**
	 * <b> used to runtestcases in a specified flow </b>
	 
	 *
	 */
	public void runTestCases() {
		
		if(downloadStraiTimesApp()) {
			System.out.println("Download test case passed");
			if(loginToApp()) {
				System.out.println("Login test case passed");
				checkLatestnews();
				logout();
			}else {
				System.out.println("Login test case failed hence not checking for latest news");
			}
			uninstallApp();
		}else {
			System.out.println("Unable to download the app hence terminating the execution");
		}
		
	}
	
	/**
	 * <b> TestCase to downloadStraiTimesApp </b>
	  @return  boolean decision
	 *
	 */
	
	private static boolean downloadStraiTimesApp() {
		
		boolean decision = false;
		MobileElement mb = null;
		
		try {
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		Action.performAction(driver, UtilConstants.IMG_STORESEARCH, "click", null);
		mb = Action.findElement(driver, UtilConstants.BTN_STORECLEAR);
		
		if (mb!=null &&Action.findElement(driver, UtilConstants.BTN_STORECLEAR).isEnabled()) {
			Action.performAction(driver, UtilConstants.BTN_STORECLEAR, "click", null);
		}
			Action.performAction(driver, UtilConstants.TXT_STORESEARCH, "sendkeys", "straittimes");
			//((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			Action.performAction(driver, UtilConstants.TXT_STRAITTIMESAPP, "click", null);
			Action.performAction(driver, UtilConstants.BTN_INSTALL, "click", null);
			Action.performAction(driver, UtilConstants.BTN_OPEN, "click", null);
			Action.performAction(driver, UtilConstants.BTN_IAGREE, "click", null);
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			System.out.println("App download and launched succesfully");
			decision = true;
			
		}catch (Exception e) {
			System.err.println("Error in download the app"+e.getLocalizedMessage());
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			decision = false ;
		}
		
		
		return decision;
	}
	/**
	 * <b> TestCase to Login to App </b>
	  @return  boolean decision
	 *
	 */
	private static boolean loginToApp() {
		boolean decision = false;
		MobileElement mb = null;
		
		try {
			
		Action.SwipeRtoL(driver, 2);
		Action.performAction(driver, UtilConstants.BTN_SKIP, "click", null);
		if(Action.findElement(driver, UtilConstants.BTN_ADDCLOSE)!=null) {
			Action.performAction(driver, UtilConstants.BTN_ADDCLOSE, "click", null);
		}
		
		//Action.findElementbyID(driver, UtilConstants.ID_BTN_GETSTARTED).click();
		Action.performAction(driver, UtilConstants.BTN_MENU, "click", null);
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		Thread.sleep(2000);
		Action.performAction(driver, UtilConstants.BTN_LOGIN, "click", null);
		Action.performAction(driver, UtilConstants.TXT_USERNAME, "sendkeys", UtilConstants.SPH_USERNAME);
		Action.performAction(driver, UtilConstants.TXT_PASSWORD, "sendkeys", UtilConstants.SPH_PASSWORD);
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		Action.performAction(driver, UtilConstants.BTN_CONTINUE, "click", null);
		Thread.sleep(2000);
		Action.performAction(driver, UtilConstants.BTN_MENU, "click", null);
		Thread.sleep(2000);
		mb = Action.findElement(driver, UtilConstants.LBL_LOGGEDINUSER);
		if (mb!=null) {
			
			System.out.println("Login successful with UID : "+mb.getText());
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		}
		decision = true;
		
		}catch (Exception e) {
			System.err.println("Error while logging in "+e.getLocalizedMessage());
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			decision = false ;
			
		}
		return decision;
	
	  
	}
	/**
	 * <b> TestCase to check latest news </b>
	  @return  boolean decision
	 *
	 */
	private static boolean checkLatestnews() {
		boolean decision = false;
		MobileElement mb = null;
	
		try {
		Thread.sleep(3000);
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		Action.findElementbyID(driver, UtilConstants.BID_BTN_HOME).click();
		Action.performAction(driver, UtilConstants.LNK_LATESTNEWS, "click", null);
		Action.performAction(driver, UtilConstants.LNK_FIRSTARTICLE, "click", null);
		Thread.sleep(2000);
		Action.performAction(driver, UtilConstants.OVERLAY, "click", null);
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		mb =Action.findElementbyID(driver, UtilConstants.ID_IMG_ARTICLE);
		if (mb!=null) {
			System.out.println("Checked Latest news and Image loaded for first article");
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			mb.click();
		}
		decision = true;
		
		}catch (Exception e) {
			System.err.println("Error in checking latest news "+e.getLocalizedMessage());
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			decision = false ;
		}
		return decision;
	}
	/**
	 * <b> TestCase to logout </b>
	  @return  boolean decision
	 *
	 */
	private static boolean logout() {
		boolean decision = false;
		MobileElement mb = null;
		try {
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		Action.performAction(driver, UtilConstants.BTN_BACK, "click", null);
		Action.performAction(driver, UtilConstants.BTN_MENU, "click", null);
		Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		Action.performAction(driver, UtilConstants.BTN_LOGOUT, "click", null);
		mb = Action.findElement(driver, UtilConstants.BTN_LOGIN);
		
		if(mb!=null) {
			System.out.println("User logged out succesfully");
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
		}
		decision = true;
		
		}catch (Exception e) {
			System.err.println("Error in logging out "+e.getLocalizedMessage());
			Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
			decision = false ;
		}
		return decision;
	}
	/**
	 * <b> Used to clear the uninstall the app </b>
	 
	 *
	 */
	private static  void uninstallApp() {
		
		System.out.println("Execution completed not uninstalling the app");
		driver.closeApp();
		driver.removeApp(UtilConstants.SPH_PACKAGE);
	}
	
	
}
