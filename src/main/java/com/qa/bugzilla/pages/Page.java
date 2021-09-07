package com.qa.bugzilla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
	
	WebDriver driver;
	WebDriverWait wait;
	Select select;
	
	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(this.driver, 15);
	}
	
	abstract String getPageTitle();
	
	abstract String getPageHeader(By locator);
	
	abstract public WebElement getWebElement(By locator);
	
	abstract void waitUntilTitlePresense(String title);
	
	abstract void waitUntilElementIsClickable(By locator);
	
	abstract void waitUntilElementIsPresense(By locator);
	
	abstract void selectFromDropDown(By locator,String value);
	
	public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
