package com.sph.webOnline;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserClass {

	static WebDriver wd;
	/**
	 * <b> used to create Browser instance based on Platform </b>
	  @return  Webdriver
	 *
	 */
	public static WebDriver createBrowser(String browser, String url) {

		String os = System.getProperty("os.name").toLowerCase();
		WebDriver wd = null;

		if (os.contains("windows")) {
			wd = returnDriver(browser, ".exe");

		} else if (os.contains("mac")) {
			wd = returnDriver(browser, "");

		}
		wd.manage().window().maximize();
		wd.manage().deleteAllCookies();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wd.get(url);
		return wd;
	}
	/**
	 * <b> used to return Browser instance </b>
	  @return  Webdriver
	 *
	 */
	private static WebDriver returnDriver(String browser, String holder) {
		WebDriver wd = null;

		try {
			switch (browser.toLowerCase()) {

			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						Config.CURRENT_DIR + File.separator + Config.DRIVER_PATH + "geckodriver" + holder);
				wd = new FirefoxDriver();
				break;
				
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						Config.CURRENT_DIR + File.separator + Config.DRIVER_PATH + "chromedriver" + holder);
					wd = new ChromeDriver();
				break;
				
			case "safari":
				System.setProperty("webdriver.safari.driver",
						Config.CURRENT_DIR + File.separator + Config.DRIVER_PATH + "safaridriver" + holder);
				wd = new SafariDriver();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wd;
	}
	/**
	 * <b> used to close Browser instance </b>
	  @return  null
	 *
	 */
	public static void closeBrowser(WebDriver wd) {
		try {
			Thread.sleep(3000);
			Runner.takeScreenShot(wd, "login");
			wd.close();
			wd.quit();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	

}

