package fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fcm.qa.base.TestBase;
import fcm.qa.pages.CalenderPage;
import fcm.qa.pages.HomePage;
import fcm.qa.pages.LoginPage;

public class CalenderPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	CalenderPage calenderPage;

	public CalenderPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		openBrowser();
		loginPage = new LoginPage();
		calenderPage = new CalenderPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		calenderPage = homePage.clickOnCalenderLink();
	}

	@Test(priority = 1)
	public void validatePageTitleTest() throws InterruptedException {

		Thread.sleep(2000);
		String s = calenderPage.validatePageTitle();
		Assert.assertEquals(s, "Meetings", "Page Title Not Found");
	}

	@Test(priority = 2)
	public void bookMeetingTest() {
		 calenderPage.bookMeeting(prop.getProperty("meetTitle"));
		
	}
	
	@Test(priority = 3)
	public void validatebookedMeetingTest() {
		calenderPage.verifyMeetingBooked(prop.getProperty("meetTitle"));
		
	}

	@AfterMethod
	public void closeCalenederPage() {
		driver.quit();
	}

}
