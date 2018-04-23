package com.sph.MobileOnline;

import java.io.File;

public class UtilConstants {

	//utility contants
	public static final String OS_NAME = System.getProperty("os.name");
	public static final long WAITCOUNTER  = 7;
	public static final long RETRYCOUNTER  = 5;
	public static final long WAITTIMER_MILLSEC = 5000;
	public static final String SCREENSHOTSPATH = System.getProperty("user.dir");
	public static final String LOG_PATH = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"appiumserverlogs";
	public static final String APPIUM_JS_PATH="/usr/local/lib/node_modules/appium/build/lib/main.js";
	
	
	//Object Repository
	
	public static final String  IMG_STORESEARCH = "//*[@content-desc=\"Search\"]";//-image view of store txt field
	public static final String TXT_STORESEARCH = "//*[@text='Search Google Play']"; //- play store search text field
	public static final String BTN_STORECLEAR = "//android.widget.ImageView[@content-desc=\"Clear\"]"; // clear button
	public static final String TXT_STRAITTIMESAPP = "//*[contains(@text,'Straits')]"; // - strait times app
	public static final String BTN_INSTALL = "//*[contains(@text,'INSTALL')]"; // install button
	public static final String PROGRESSBAR = "//*[@class='android.widget.ProgressBar']";
	public static final String BTN_OPEN = "//*[contains(@text,'OPEN')]";
	public static final String BTN_SKIP="//*[@class='android.widget.RelativeLayout']/android.widget.ImageView[1]";
	public static final String ID_BTN_GETSTARTED = "com.buuuk.st:id/get_started";
	public static final String BTN_UNINSTALL = "//*[contains(@text,'UNINSTALL')]";
	public static final String BTN_IAGREE = "//*[contains(@text,'I Agree')]";
	public static final String BTN_ADDCLOSE  = "//*[@content-desc=\"Interstitial close button\"]";
	//public static final String BTN_GETSTARTED = "//*[@content-desc=\"GET STARTED\"]";
	public static final String BTN_MENU = "//*[@content-desc=\"Navigate up\"]";
	public static final String BTN_LOGIN = "//*[@text='LOGIN']";
	public static final String TXT_USERNAME = "//*[@text='Login Id']";
	public static final String TXT_PASSWORD = "//*[@text='Password']";
	public static final String BTN_CONTINUE = "//*[@text='Continue']";
	public static final String LBL_LOGGEDINUSER = "//*[contains(@text,'digitaltest1')]";
	public static final String BID_BTN_HOME = "com.buuuk.st:id/iv_home";
	public static final String LBL_MAINHEADER = "//*[@content-desc=\"THE STRAITS TIMES\"]";
	public static final String LNK_LATESTNEWS = "//*[@text='LATEST']";
	public static final String LNK_FIRSTARTICLE = "//*[@class='android.support.v7.widget.RecyclerView']/android.widget.LinearLayout[1]";
	public static final String ID_IMG_ARTICLE  = "com.buuuk.st:id/article_image";
	public static final String OVERLAY = "//*[@class='android.support.v4.view.ViewPager']";
	public static final String BTN_BACK = "//*[@text='LATEST']/../android.widget.ImageView[1]"; //- back button
	public static final String BTN_LOGOUT  = "//*[@text='LOGOUT']";
	
	//App properties
	
	public static final String SPH_PACKAGE = "com.buuuk.st";
	public static final String SPH_ACTIVITY = "com.sph.straitstimes.views.activities.MainContainerActivity";
	public static final String PLAYSTOREPACKAGE = "com.android.vending";
	public static final String PLAYSTOREACTIVITY = "com.android.vending.AssetBrowserActivity";
	public static final String SPH_USERNAME = "digitaltest1";
	public static final String SPH_PASSWORD = "Password1";
	
	
}
