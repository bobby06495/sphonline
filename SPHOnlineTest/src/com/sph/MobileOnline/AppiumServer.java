package com.sph.MobileOnline;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sph.webOnline.Config;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private DesiredCapabilities cap;
	private URL appiumInstanceURL;
	
	/**
	 * <b> used to get appium server url </b>
	 * 
	 * 
	 * @return returns URL
	 * 
	 */
	
	public URL getAppiumInstanceURL() {
		return this.appiumInstanceURL;
	}
	
	/**
	 * <b> used to start appium server with desired capabilities </b>
	 
	 *
	 */
	
	public URL startServer() {
		try {
			
		
			// Build the Appium service
			builder = new AppiumServiceBuilder();
			
			System.err.println(UtilConstants.OS_NAME);
			
			if(UtilConstants.OS_NAME.contains("Mac")) {
				builder.withAppiumJS(new File(UtilConstants.APPIUM_JS_PATH));
			}else if(UtilConstants.OS_NAME.contains("Windows")) {
				builder.withAppiumJS(new File(Config.WINDOWS_APPIUM_JS_PATH));
			}
			builder.withIPAddress("127.0.0.1");
			
			//builder.usingPort(4723);
			builder.usingAnyFreePort();
			// builder.usingDriverExecutable(new File(UtilConstants.APPIUM_JS_WIN));
			builder.withCapabilities(cap);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withArgument(GeneralServerFlag.LOG_LEVEL, "debug");
			
			// Start the server with the builder
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
			this.appiumInstanceURL=service.getUrl();
			System.out.println("Server URL : "+appiumInstanceURL);
		}catch(Exception e) {
			e.printStackTrace();
			stopServer();
		}
		return appiumInstanceURL;

	}
	/**
	 * <b> used to stop appium server  </b>
	 
	 *
	 */
	public void stopServer() {
		service.stop();
		System.out.println("Stopping Appium server");
		try {
			FileUtils.writeStringToFile(new File(UtilConstants.LOG_PATH), service.getStdOut());
		}catch(Exception e) { 
			e.printStackTrace();
		}
	}

	/**
	 * <b> used to check if the sever is running on  a specifi port
	 *  appium server with desired capabilities </b>
	 @param int port number
	 *
	 */
	
	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			System.out.println("Port is in use - " + port);
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	/**
	 * <b> used to set capabilities to start server </b>
	 
	 *
	 */
	public DesiredCapabilities setDC() {
	
	cap = new DesiredCapabilities();
	cap.setCapability("noReset", false);
	cap.setCapability("udid",Config.ANDROIDDEVICEUDID);
	cap.setCapability("platformName",Config.PLATFORM);
	cap.setCapability("appPackage", UtilConstants.PLAYSTOREPACKAGE);
	cap.setCapability("appActivity", UtilConstants.PLAYSTOREACTIVITY);
	cap.setCapability(CapabilityType.BROWSER_NAME, "");
	cap.setCapability("clearSystemFiles", true);
	cap.setCapability("automationName", "uiautomator2");
	cap.setCapability("newCommandTimeout", 180);
	cap.setCapability("deviceName", Config.ANDROIDDEVICENAME);
	return cap;
	
	}
}