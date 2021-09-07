package com.qa.bugzilla.tests;

import org.testng.annotations.Test;

import com.qa.bugzilla.pages.LoginPage;

public class LoginPageTest extends BaseTest {
	
//	@Test(priority = 1)
	public void getLoginPageTitleTest() {
		String title = page.getInstance(LoginPage.class).getLoginPageTitle();
		System.out.println(title);
	}
	
	@Test(priority = 2)
	public void doLoginTest() {
		page.getInstance(LoginPage.class).doLogin(prop.getProperty("emailId"), prop.getProperty("password"));
	}

}
