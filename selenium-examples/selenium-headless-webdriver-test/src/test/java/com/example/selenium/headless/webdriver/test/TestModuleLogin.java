package com.example.selenium.headless.webdriver.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestModuleLogin {

	public String baseUrl = "http://demo.guru99.com/test/newtours/";
	public WebDriver driver;

	@BeforeTest
	@Parameters("headlessBrowser")
	public void beforeTest(String headlessBrowser) throws Exception {
		
		switch(headlessBrowser) {
			case "HtmlUnit":	{
				driver = new HtmlUnitDriver();
				break;
			}
			// PhantomJS + Ghost
			case "PhantomJS": {
				System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "../headless-browser/phantomjs.exe");
				driver = new PhantomJSDriver();
				break;
			}
			case "Chrome-Headless": {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "../chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				driver = new ChromeDriver(options);
				break;
			}
			case "Firefox-Headless": {
				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "../geckodriver.exe");
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				   
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setBinary(firefoxBinary);
				driver = new FirefoxDriver(firefoxOptions);
				break;
			}
			case "Edge-Headless": {
				System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, "../msedgedriver.exe");
				EdgeOptions options = new EdgeOptions();
				options.addArguments("headless");
				options.addArguments("disable-gpu");
				driver = new EdgeDriver(options);
				break;
			}
			/*
			case "ZombieJS": {
				break;
			}
			case "Watir-webdriver": {
				break;
			}
			*/
			default: {
				System.out.println("Unsupported headless browser!");
				throw new Exception("Unsupported headless browser!");
			}
		}
	}

	@AfterTest
	public void afterTest() {
		if( driver != null ) {
			driver.close();
		}
	}
	
	@Test
	public void verifyTitle() throws Exception {
		driver.get(baseUrl);
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		
		// Take a screenshot of the current page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("screenshot.png"));
        
		Assert.assertEquals(actualTitle, expectedTitle);
	}

}
