package com.sph.webOnline;

import org.openqa.selenium.WebDriver;

public class DriverScript {
	
	static WebDriver wd;
	/**
	 * <b> Main method to start execution </b>
	  @return  Webdriver
	 *
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try { 
			boolean value = false;
			wd = BrowserClass.createBrowser(Config.BROWSER, Config.URL);
			if (login(wd)) {
				value = verifyImage(wd);
			if(value==false) {
				verifyVideo(wd);
			}
			
			Runner.runStep(wd, Config.LNK_LOGOUT, "click", null);
			BrowserClass.closeBrowser(wd);
			}else {
				System.out.println("Unable to login hence terminating execution ");
				Runner.takeScreenShot(wd,"login");
				BrowserClass.closeBrowser(wd);
			}
		}catch(Exception e){
			
			BrowserClass.closeBrowser(wd);
			}
		System.out.println("Execution completed please find evidences"+Config.SCREENSHOTS_PATH);
	}
	
	private static boolean login(WebDriver wd) {
		boolean decision=false;
		String currentTestCase=Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
		wd.navigate().refresh();
		Runner.takeScreenShot(wd, currentTestCase);
	//	Runner.runStep(wd, Config.BTN_CLOSEADD, "click", "");
		Runner.runStep(wd, Config.LNK_LOGIN, "click", "");
		Runner.runStep(wd, Config.TXT_USERNAME,"sendkeys" , Config.USERNAME);
		Runner.runStep(wd, Config.TXT_PASSWORD,"sendkeys" , Config.PASSWORD);
		Runner.runStep(wd, Config.BTN_SIGNIN,"click" , "");
		if(Runner.findElement(wd, Config.LNK_LOGGEDINUSERNAME).isDisplayed()) {
			System.out.println("User Logged in successfully");
			Runner.takeScreenShot(wd, currentTestCase);
			decision = true;
		}
		else
		{
			System.err.println("User login failed");
			Runner.takeScreenShot(wd, currentTestCase);
			decision = false;
		}
		}catch(Exception e) {
		
			}
		
		return decision;
		
	}
	
	private static boolean verifyImage(WebDriver wd) {
		String currentTestCase=Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean decision = false;
		try {
			Runner.runStep(wd, Config.LNK_MAINSTORY, "click", "");
			System.out.println("Clicked on main article");
			if(Runner.findElement(wd, Config.IMG_MAINSTORY).isDisplayed()) {
				Runner.takeScreenShot(wd, currentTestCase);
				System.out.println("Navigated to Main article page and its an Image");
				decision = true;
			}else {
				System.out.println("Main article is not an image checking for video");
				decision = false;
			}
			
		}catch(Exception e) {
			decision = false;
		}
		return decision;
		
		
	}
	private static boolean verifyVideo(WebDriver wd) {
		String currentTestCase=Thread.currentThread().getStackTrace()[1].getMethodName();
		boolean decision = false;
		try {
			Runner.runStep(wd, Config.LNK_MAINSTORY, "click", "");
			System.out.println("Clicked on main article");
			if(Runner.findElement(wd, Config.VID_MAINSTORY).isDisplayed()) {
				System.out.println("avigated to Main article page and its a video");
				Runner.takeScreenShot(wd, currentTestCase);
				decision = true;
		}else {
				System.out.println("Main article is not an video checking for image");
				decision = false;
			}
			
		}catch(Exception e) {
			decision = false;
		}
		return decision;
		
	}
}
