package fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fcm.qa.base.TestBase;
import fcm.qa.pages.DiscussPage;
import fcm.qa.pages.HomePage;
import fcm.qa.pages.LoginPage;

public class DiscussPageTest extends TestBase{

	LoginPage login;
	HomePage home;
	DiscussPage discuss;
	
	public DiscussPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		openBrowser();
		login = new LoginPage();
		discuss = new DiscussPage();
		home = login.login(prop.getProperty("username"), prop.getProperty("password"));
		discuss = home.clickOnDiscussLink();
	}
	
	@Test(priority = 1)
	public void verifyPageTitle() {
		String s = discuss.validatePageTitle();
		Assert.assertEquals(s, "Discuss");
	}
	
	@Test(priority = 2)
	public void createMessage() {
		
	boolean b = discuss.sendMessage(prop.getProperty("find"), prop.getProperty("message"));
	Assert.assertTrue(b);
	}
	

	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	
	
}
