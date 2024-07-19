package fcm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fcm.qa.base.TestBase;
import fcm.qa.pages.ContactPage;
import fcm.qa.pages.HomePage;
import fcm.qa.pages.LoginPage;
import fcm.qa.testdata.DataFile;




public class ContactPageTest extends TestBase {
	
	DataFile data;
	LoginPage login;
	HomePage home;
	ContactPage contact;
	
	public ContactPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		openBrowser();
		login = new LoginPage();
		data = new DataFile();
		contact = new 	ContactPage();
		home = login.login(prop.getProperty("username"), prop.getProperty("password"));
		contact = home.ClickOnContactPage();


	}
	
	@Test
	public void validatePageTitle() throws InterruptedException {
		Thread.sleep(2000);
		String s = contact.verifyPageTitle();
		Assert.assertEquals(s, "Contacts");
	}
	
	@Test
	public void verifyContactCreated() {
		contact.createNewContact(data.name, data.companyName,
				data.jobPosition, data.phone, data.mobile,data.email);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}
