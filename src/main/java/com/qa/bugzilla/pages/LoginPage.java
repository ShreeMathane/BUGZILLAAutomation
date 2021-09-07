package com.qa.bugzilla.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	By loginLink = By.linkText("Log In");
	By email = By.id("Bugzilla_login_top");
	By passWord = By.id("Bugzilla_password_top");
	By loginBtn = By.id("log_in_top");
	By idList = By.xpath("//td[@class='first-child bz_id_column']/a");
	By selectDrop = By.xpath("//select[@id='bug_status']");
	By commitBtn = By.xpath("//input[@id='commit']");
	By getBugId = By.xpath("//*[@id=\"changeform\"]/div[1]/a/b");
	By getBugStatus = By.xpath("//select[@id='bug_status' ]/option[@selected='selected']");

	public By getGetBugStatus() {
		return getBugStatus;
	}

	public By getCommitBtn() {
		return commitBtn;
	}

	public By getGetBugId() {
		return getBugId;
	}

	public By getIdList() {
		return idList;
	}

	public By getLoginLink() {
		return loginLink;
	}

	public By getEmail() {
		return email;
	}

	public By getPassWord() {
		return passWord;
	}

	public By getLoginBtn() {
		return loginBtn;
	}

	public By getSelectDrop() {
		return selectDrop;
	}

	public void setSelectDrop(By selectDrop) {
		this.selectDrop = selectDrop;
	}

	public String getLoginPageTitle() {
		return getPageTitle();
	}

	public void doLogin(String username, String password) {
		try {
			getWebElement(getLoginLink()).click();
			getWebElement(getEmail()).sendKeys(username);
			getWebElement(getPassWord()).sendKeys(password);
			getWebElement(getLoginBtn()).click();
			System.out.println("Login Done");
			List<WebElement> lists = driver.findElements(getIdList());
			System.out.println(lists.size());
			for (int i = 1; i <= lists.size(); i++) {
				driver.findElement(By.xpath("//td[@class='first-child bz_id_column']/a")).click();
				String bug_status = getWebElement(getBugStatus).getText();
				if (bug_status.equals("DEFERRED")) {
					selectFromDropDown(getSelectDrop(), "CONFIRMED");
					String id = getWebElement(getBugId).getText();
					driver.findElement(getCommitBtn()).click();
					System.out.println(i + ". " + id + " CONFIRMED Sucesfully");
					driver.navigate().back();
					driver.navigate().refresh();
					selectFromDropDown(getSelectDrop(), "CLOSED");
					String id2 = getWebElement(getBugId).getText();
					driver.findElement(getCommitBtn()).click();
					System.out.println(i + ". " + id2 + " Closed Sucesfully");
					driver.navigate().back();
					driver.navigate().back();
					driver.navigate().refresh();
				} else {
					selectFromDropDown(getSelectDrop(), "CLOSED");
					String id2 = getWebElement(getBugId).getText();
					driver.findElement(getCommitBtn()).click();
					System.out.println(i + ". " + id2 + " Closed Sucesfully");
					driver.navigate().back();
					driver.navigate().back();
					driver.navigate().refresh();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
