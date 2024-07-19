package fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fcm.qa.base.TestBase;
import fcm.qa.pages.CalenderPage;
import fcm.qa.pages.ContactPage;
import fcm.qa.pages.DiscussPage;
import fcm.qa.pages.HomePage;
import fcm.qa.pages.LoginPage;

public class HomePageTest extends TestBase {

	LoginPage login;
	HomePage home;
	DiscussPage discuss;
	CalenderPage calender;
	ContactPage contact;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		openBrowser();
		login = new LoginPage();
		discuss = new DiscussPage();
		calender = new CalenderPage();
		contact = new ContactPage();
		home = login.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void verifyPageTitle() throws InterruptedException {
		Thread.sleep(3000);
		String s = home.validatePageTitle();
		System.out.println(s);
		Assert.assertEquals(s, "Home");
	}
	
	@Test(priority = 2)
	public void verifyDiscussLink() {
		discuss = home.clickOnDiscussLink();
	}

	@Test(priority = 3)
	public void verifyCalenderClick() {
		calender = home.clickOnCalenderLink();
	}
	
	@Test(priority = 4)
	public void verifyContactLink() {
		contact = home.ClickOnContactPage();
	}

	@Test(priority = 5)
	public void VerifyCrmLink() {
		boolean crm = home.validateCrmLink();
		Assert.assertTrue(crm);
	}
	

	@Test(priority = 6)
	public void VerifyAppLink() {
		boolean App = home.validateAppLink();
		Assert.assertTrue(App);
	}

	@Test(priority = 7)
	public void VerifydashLink() {
		boolean dash = home.validateDashLink();
		Assert.assertTrue(dash);
	}

	@Test(priority = 8)
	public void VerifySettingLink() {
		boolean setting = home.validateSettingLink();
		Assert.assertTrue(setting);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
