package com.sph.webOnline;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Runner {

	static JavascriptExecutor jse;
	
	/**
	 * <b> Peforms action by verifying the presence of elements </b>
	 * 
	 * @author Sashikiran
	 * @param driver
	 *            AppiumDriver object
	 * @param locator
	 *            Element locator by Xpath
	 * @param action
	 *            Valid action keywords are "CLICK", "SENDKEYS",
	 *            "CLEAR","GETTEXT","GETCLASS"
	 * @param parm
	 *            Any number of arguments to be passed for doing the actions.
	 *            Multiple parms supported by calling the method {"","",""}
	 * @return returns true if the action is success. false if the element is not
	 *         available for action.
	 */

	public static boolean runStep(WebDriver wd, String locator, String action, String params) {

		boolean blnDecision;
		try {
			WebElement element;
			switch (action.toUpperCase()) {

			case "CLICK":
				element = findElement(wd, locator);
				element.click();
				break;

			case "SENDKEYS":
				element = findElement(wd, locator);
				element.sendKeys(params);
				break;

			case "CLEAR":
				element = findElement(wd, locator);
				element.clear();
				break;

			case "GETTEXT":
				element = findElement(wd, locator);
				element.getText();
				break;

			case "GETCLASS":
				element = findElement(wd, locator);
				element.getClass();
				break;
			}
			blnDecision = true;

		} catch (Exception e) {
			blnDecision = false;
			System.out.println("Failed while exeucting action please check locator details"+locator);
		}
		return blnDecision;
	}
	/**
	 * <b> used to find mobile elements with a wait conditions </b>
	 * 
	 * @param driver
	 *            WebElement object
	 * @param locator
	 *            Element locator by Xpath
	 
	 * @return returns web element
	 * @throws InterruptedException 
	 */
	public static WebElement findElement (WebDriver wd , String locator ) throws InterruptedException {
		
		WebElement element = null ;
		WebDriverWait wait = new WebDriverWait(wd, 2);
		int i=0;
		for(i=0;i<=Config.WAITCOUNTER;i++) {
		try {
		jse = (JavascriptExecutor) wd;
		element =	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		if (locator.contains("Logout")) {
			jse.executeScript("window.scrollBy(0,-1000)", "");
		}else {
		jse.executeScript("arguments[0].scrollIntoView(false)", element);
		element = 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		
		}
		
		if(element!=null)
			break;
		}catch (Exception e) {
			if(i<=Config.WAITCOUNTER) {
				Thread.sleep(Config.WAITTIMEMILLSEC);
				System.err.println("Error while identifying element trying again ..."+ locator);
			}
			System.err.println("Unable to find the element "+e.getLocalizedMessage());
		}
		}
		highlightElement(element);
		return 	element;
		
	}
	/**
	 * <b> used to highlight elements </b>
	 * 
	 * 
	 * @param WebElement
	
	 */
	private static void highlightElement(WebElement element) {
		try {
			if (element==null) {
			
				System.err.println("Element is returned as null please chcek locator details "+element);
		}
		else {
			
			for(int i = 0;i<2;i++) {
				jse.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,"color:blue; border:2px solid blue");
				Thread.sleep(250);
				jse.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,"");
		}
		}
		}catch (Exception e) {
			System.err.println("Error while highlighting element : "+e.getLocalizedMessage());
		}
	}
	/**
	 * <b> used to take screenshots and store in a directory </b>
	 * 
	 * @param driver
	 *            Webdriver object
	 * @param testcase name
	 *        	
	 * 
	 */
	public static void takeScreenShot(WebDriver wd, String testCaseName) {
		
		try {
			File scrFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
			String filePath = Config.SCREENSHOTS_PATH +File.separator+testCaseName+File.separator+ System.currentTimeMillis() + ".png";
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}