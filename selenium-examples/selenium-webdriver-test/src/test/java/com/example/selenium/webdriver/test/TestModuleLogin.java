package com.example.selenium.webdriver.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestModuleLogin {

	public String baseUrl = "http://demo.guru99.com/test/newtours/";
	public WebDriver driver;
	public String browserName;

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) throws Exception {
		browserName = browser;
		
		switch(browser.toLowerCase()) {
			case "firefox":	{
				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "../geckodriver.exe");
				driver = new FirefoxDriver();
				break;
			}
			case "chrome": {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "../chromedriver.exe");
				driver = new ChromeDriver();
				break;
			}
			case "edge": {
				System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, "../msedgedriver.exe");
				driver = new EdgeDriver();
				break;
			}
			default: {
				System.out.println("Unsupported browser!");
				throw new Exception("Unsupported browser!");
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
	public void verifyTitle() {
		System.out.println(String.format("launching browser %s", browserName));
		driver.get(baseUrl);
		String expectedTitle = "Welcome: Mercury Tours";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

}
