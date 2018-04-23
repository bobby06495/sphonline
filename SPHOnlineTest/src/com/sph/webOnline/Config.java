package com.sph.webOnline;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public static Properties appConfig = new Properties();
	private static final String fp = File.separator;
	public static String appConfigPath = System.getProperty("user.dir") +fp+"Resources"+fp
			+ "config.properties";
	
	
	static {
		try {
			FileInputStream confFileIS = new FileInputStream(new File(appConfigPath));
			appConfig.load(confFileIS);
			confFileIS.close();
		} catch (FileNotFoundException f) {
			appConfigPath = System.getProperty("user.dir") + fp+"Resources"+fp
					+ "config.properties";
			
			try {
				FileInputStream confFileIS = new FileInputStream(new File(appConfigPath));
				appConfig.load(confFileIS);
				confFileIS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static final String CURRENT_DIR = System.getProperty("user.dir");
	public static final String URL = appConfig.getProperty("url");
	public static String BROWSER = appConfig.getProperty("browser");
	public static final String DRIVER_PATH = appConfig.getProperty("DriverPath");
	public static final String USERNAME = appConfig.getProperty("username");
	public static final String PASSWORD = appConfig.getProperty("password");
	public static final String CHROME_DRIVER_PATH = appConfig.getProperty("chromeDriverPath");
	public static final String FIREFOX_DRIVER_PATH = appConfig.getProperty("firefoxDriverPath");
	public static final String FIREFOX_DRIVER = appConfig.getProperty("firefoxDriver");
	public static final String IE_DRIVER = appConfig.getProperty("IEDriver");
	public static final String IE_DRIVER_NAME = appConfig.getProperty("IEDriverName");
	public static final String CHROME_DRIVER = appConfig.getProperty("chromeDriver");
	public static final long WAITCOUNTER = Long.parseLong(appConfig.getProperty("waitcounter"));
	public static final long WAITTIMEMILLSEC = Long.parseLong(appConfig.getProperty("waittime"));
	public static final String SCREENSHOTS_PATH = System.getProperty("user.dir") +fp+appConfig.getProperty("screenshotspath");
	public static final String ANDROIDDEVICEUDID = appConfig.getProperty("androiddeviceudid");
	public static final String ANDROIDDEVICENAME = appConfig.getProperty("devicename");
	public static final String PLATFORM = appConfig.getProperty("platform");
	public static final String WINDOWS_APPIUM_JS_PATH  = appConfig.getProperty("windowsAppiumJSpath");

	/**
	 * <h4>Object Repository</h4> This method retrieves the WebElement from the browser
	 * by using the input locator(like an XPath) repository.
	 *
	 * */
	public static final String LNK_LOGIN = "//*[text()='Login']";
	public static final String TXT_USERNAME = "//INPUT[@id='j_username']";
	public static final String TXT_PASSWORD = "//INPUT[@id='j_password']";
	public static final String BTN_CLOSEADD = "//*[@id=\"close-button\"]";
	public static final String BTN_SIGNIN = "//BUTTON[text()='Sign in!']";
	public static final String LNK_MAINSTORY = "(//A[@class='block-link'])[1]";
	public static final String DRP_MAINPAGE = "//*[@class='sph-sites-toggle dropdown-toggle']";
	public static final String IMG_MAINSTORY = "(//IMG[@class='img-responsive'])[1]";
	public static final String VID_MAINSTORY = "//VIDEO[@id='myPlayerID_html5_api']";
	public static final String LNK_LOGGEDINUSERNAME = "//*[text()='digitaltest1']";
	public static final String LNK_LOGOUT = "//*[text()='Logout']";
	
	
	
	public static String getProp(String var) {
		reloadProperties();
		return appConfig.getProperty(var);
	}

	public static void reloadProperties() {
		try {
			FileInputStream confFileIS = new FileInputStream(new File(appConfigPath));
			appConfig.load(confFileIS);
			confFileIS.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}