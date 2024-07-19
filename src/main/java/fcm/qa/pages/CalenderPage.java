package fcm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fcm.qa.base.TestBase;

public class CalenderPage extends TestBase {

	@FindBy(xpath = "//button[contains(text(),'Week')]")
	WebElement week;

	@FindBy(xpath = "//span[contains(text(),'Year')]")
	WebElement year;

	@FindBy(xpath = "//a[@aria-label='December 25, 2024']")
	WebElement date;

	@FindBy(xpath = "//div[@class='modal-content']")
	List<WebElement> addtitlebox;
	
	@FindBy(xpath = "//input[@placeholder='Add title']")
	WebElement addtitle;

	@FindBy(xpath = "//input[@id='allday_0']")
	WebElement checkBox;

	@FindBy(xpath = "//button[@class='btn btn-primary o_form_button_save']")
	WebElement saveMeeting;

	@FindBy(xpath = "//div[@class='o_cw_body popover-body d-flex flex-column gap-2']")
	List<WebElement> createBox;
	
	@FindBy(xpath = "//a[@class='btn-link o_cw_popover_create']")
	WebElement create;

	public CalenderPage() {
		PageFactory.initElements(driver, this);
	}

	public String validatePageTitle() {
		return driver.getTitle();
	}

	public void bookMeeting(String meetingTitle) {
		week.click();
		year.click();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		System.out.println(date.getLocation());
		jse.executeScript("window.scrollBy(1070, 1062);");
		date.click();
	
		if(createBox.size()>0) {
			create.click();
		}
		if(addtitlebox.size()>0)  {
			
		addtitle.click();
		}
		addtitle.sendKeys(meetingTitle);
		checkBox.click();
		saveMeeting.click();
	}


	public void verifyMeetingBooked(String meetingTitle) {
		week.click();
		year.click();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		System.out.println(date.getLocation());
		jse.executeScript("window.scrollBy(1070, 1062);");
		date.click();
		WebElement sentMessage = driver.findElement(By.xpath("//span[contains(text(),'" + meetingTitle + "')]"));
		if(createBox.size()>0) {
			System.out.println(sentMessage.isDisplayed());
		}
		if(addtitlebox.size()>0)  {
			
			System.out.println("Meeting Not Found.");
			
			}
			
		
		
		
		
	}


}