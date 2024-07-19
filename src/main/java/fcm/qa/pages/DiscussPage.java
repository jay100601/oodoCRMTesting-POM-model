package fcm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fcm.qa.base.TestBase;

public class DiscussPage extends TestBase {

	@FindBy(xpath = "//i[@title='Start a conversation']")
	WebElement plus;
	
	@FindBy(xpath = "//input[@placeholder='Start a conversation']")
	WebElement findContact;
	
	@FindBy(xpath = "//strong[@class='px-2 py-1 align-self-center text-truncate']")
	WebElement clickName;
	
	@FindBy(xpath = "//input[@placeholder='Press Enter to start']")
	WebElement startChat;
	
	@FindBy(xpath = "//textarea[@placeholder='Message Jaykumar Patel…']")
	WebElement message;	
	
	public DiscussPage() {
		PageFactory.initElements(driver, this);
	}
		
	public String validatePageTitle() {
		return driver.getTitle();
	}
	
	public boolean sendMessage(String name,String mess) {
		plus.click();
		findContact.sendKeys(name);
		clickName.click();
		startChat.sendKeys(Keys.ENTER);
		message.sendKeys(mess);
		message.sendKeys(Keys.ENTER);
		WebElement sentMessage = driver.findElement(By.xpath("//p[contains(text(),'"+mess+"')]"));
		return sentMessage.isDisplayed();
	}
	

	
}
