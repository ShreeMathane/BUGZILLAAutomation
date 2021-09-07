package com.qa.bugzilla.tests;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.qa.bugzilla.pages.BasePage;
import com.qa.bugzilla.pages.Page;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;
	public Page page;
	Properties prop;

	@BeforeMethod
	@Parameters(value = { "browser" })
	public void startup(String browser) {
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/com/qa/bugzilla/configs/config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (browser.equals("chrome")) {
			String isHeadless = prop.getProperty("isHeadless");
			if(isHeadless.equals("true")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("disable-gpu");
				driver = new ChromeDriver(chromeOptions);
			} else {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Provide valid Parameters in XML File");
		}
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		page = new BasePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
