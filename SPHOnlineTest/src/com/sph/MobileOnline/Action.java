package com.sph.MobileOnline;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.sph.webOnline.Config;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;

public class Action {

	/**
	 * <b> Peforms action by verifying the presence of elements </b>
	 * 
	 * @param driver
	 *            AppiumDriver object
	 * @param locator
	 *            Element locator by Xpath
	 * @param action
	 *            Valid action keywords are "CLICK", "SENDKEYS", "CLEAR",
	 *            "SCROLL","SWIPE_RIGHT", "SWIPE_LEFT", "SWIPE_UP", "SWIPE_DOWN"
	 * @param parm
	 *            Any number of arguments to be passed for doing the actions.
	 *            Multiple parms supported by calling the method {"","",""}
	 * @return returns true if the action is success. false if the element is not
	 *         available for action.
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public static boolean performAction(AppiumDriver driver, String locator, String action, String parm) throws Exception {
		MobileElement mbElement = null;
		try {
			switch (action.toUpperCase()) {

			case "CLICK":
				
				mbElement = findElement(driver, locator);
				mbElement.click();
				System.out.println("Clicking on the "+mbElement);
				break;

			case "SENDKEYS":
				mbElement = findElement(driver, locator);
				mbElement.sendKeys(parm);
				System.out.println("Entered "+parm+" to the "+mbElement);
				break;

			case "CLEAR":
				mbElement = findElement(driver, locator);
				mbElement.clear();
				System.out.println("Clearing the content of the "+mbElement);
				break;
			case "SWIPE4TIMES":
				SwipeRtoL(driver, Integer.parseInt(parm));
				break;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception ("Element not found");
		}
		return true;
	}
	/**
	 * <b> used to find mobile elements with a wait conditions </b>
	 * 
	 * @param driver
	 *            AppiumDriver object
	 * @param locator
	 *            Element locator by Xpath
	 
	 * @return returns mobile element
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("rawtypes")
	public static MobileElement findElement(AppiumDriver driver, String locator) throws InterruptedException {
		MobileElement mbElement = null;
		int i = 0;

		if (locator.contains("OPEN") && findElement(driver, UtilConstants.PROGRESSBAR) != null) {
			boolean blnFound = true;

			do {
				try {
				mbElement = (MobileElement) driver.findElement(MobileBy.xpath(locator));
				if (mbElement != null) {
					blnFound=false;
					break;
				}
				}catch(Exception e ) {
					System.out.println("Downloading ...");
					Thread.sleep(UtilConstants.WAITTIMER_MILLSEC);
				}
			} while (blnFound);
			
		} else {

			for (i = 0; i <= UtilConstants.WAITCOUNTER; i++) {
				try {
					mbElement = (MobileElement) driver.findElement(MobileBy.xpath(locator));
					if (mbElement != null) {
						break;
					}
				} catch (Exception e) {
					if (i < UtilConstants.WAITCOUNTER) {
						Thread.sleep(UtilConstants.WAITTIMER_MILLSEC);
						System.out.println("Unable to find " + locator + "  for " + i + " times tryng again ...");
					}

				}
			}
		}
		if (mbElement == null)
			return null;

		return mbElement;
	}
	/**
	 * <b> used to find mobile elements by ID with a wait conditions </b>
	 * 
	 * @param driver
	 *            AppiumDriver object
	 * @param locator
	 *            Element locator by Xpath
	 
	 * @return returns mobile element
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("rawtypes")
	public static MobileElement findElementbyID(AppiumDriver driver, String locator) throws InterruptedException {
		MobileElement mbElement = null;
		int i = 0;

		
			for (i = 0; i <= UtilConstants.WAITCOUNTER; i++) {
				try {
					mbElement = (MobileElement) driver.findElement(MobileBy.id(locator));
					if (mbElement != null) {
						break;
					}
				} catch (Exception e) {
					if (i < UtilConstants.WAITCOUNTER) {
						Thread.sleep(UtilConstants.WAITTIMER_MILLSEC);
						System.out.println("Unable to find " + locator + "  for " + i + " times tryng again ...");
					}

				}
			}
		
		if (mbElement == null)
			return null;

		return mbElement;
	}
	/**
	 * <b> used to find mobile elements with a wait conditions </b>
	 * 
	 * @param driver
	 *            AppiumDriver object
	 * @param times
	 *            no of times to swipe
	 
	 *
	 */
	
	@SuppressWarnings("rawtypes")
	public static void SwipeRtoL(AppiumDriver driver , int times) { // Android ONLY!
        for (int i =0; i<=times;i++) {
        	Action.takeScreenshot(driver, Config.ANDROIDDEVICENAME);
        new TouchAction((PerformsTouchActions) driver).press(900, 689).waitAction(Duration.ofMillis(2000)).moveTo(39, 879).release().perform();
        }
	}
	
	/**
	 * <b> used to take screenshots and store in a directory </b>
	 * 
	 * @param driver
	 *            AppiumDriver object
	 * @param device
	 *        	device name    
	 
	 * @return returns mobile element
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("rawtypes")
	public static  void takeScreenshot(AppiumDriver driver, String Device) {
		
		try {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String uploadPath = UtilConstants.SCREENSHOTSPATH + File.separator + "ScreenShots" + File.separator+Device+File.separator+ System.currentTimeMillis() + ".png";
			System.out.println(uploadPath);
			FileUtils.copyFile(scrFile, new File(uploadPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
