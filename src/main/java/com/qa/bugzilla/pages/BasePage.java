package com.qa.bugzilla.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);
	}

	@Override
	String getPageTitle() {
		return driver.getTitle();

	}

	@Override
	String getPageHeader(By locator) {
		return getWebElement(locator).getText();
	}

	@Override
	public WebElement getWebElement(By locator) {
		WebElement element = null;
		try {
           element = driver.findElement(locator);
           return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	void waitUntilTitlePresense(String title) {
	    try {
	    	wait.until(ExpectedConditions.titleContains(title));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

	@Override
	void waitUntilElementIsClickable(By locator) {
		try {
	    	wait.until(ExpectedConditions.elementToBeClickable(locator));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

	@Override
	void waitUntilElementIsPresense(By locator) {
		try {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}

	@Override
	public void selectFromDropDown(By locator,String value) {
	 select =  new Select(getWebElement(locator));
	 select.selectByValue(value);
	}

}
