package fcm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fcm.qa.base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(name = "login")
	WebElement email;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement submit;
	
	@FindBy(xpath = "//a[contains(text(),'Reset Password')]")
	WebElement forget;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateForgetPasswordLink() {
		return forget.isDisplayed();
	}
	
	public HomePage login(String a, String b) {
		email.sendKeys(a);
		password.sendKeys(b);
		submit.click();
		
		return new HomePage();
	}
	
	
}
