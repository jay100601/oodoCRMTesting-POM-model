package fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fcm.qa.base.TestBase;
import fcm.qa.pages.HomePage;
import fcm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage login;
	HomePage home;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		openBrowser();
		login = new LoginPage();
	}
	
	@Test
	public void verifyForgetLink() {
		boolean link = login.validateForgetPasswordLink();
		Assert.assertTrue(link);
	}
	
	@Test
	public void loginUser() {
		home = login.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
