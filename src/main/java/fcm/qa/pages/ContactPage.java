package fcm.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import fcm.qa.base.TestBase;

public class ContactPage extends TestBase {

	@FindBy(xpath = "//div[@class='d-none d-xl-inline-flex gap-1']//button[@type='button'][contains(text(),'New')]")
	WebElement newContact;
	
	@FindBy(xpath = "//input[@id='radio_field_0_person']")
	WebElement radio;
	
	@FindBy(xpath = "//input[@id='name_1']")
	WebElement con_name;
	
	@FindBy(xpath = "//input[@id='parent_id_0']")
	WebElement companyname;
	
	@FindBy(xpath = "//input[@id='function_0']")
	WebElement postion;
	
	@FindBy(xpath = "//input[@id='phone_0']")
	WebElement phone;
	
	@FindBy(xpath = "//input[@id='mobile_0']")
	WebElement mobile;
	
	@FindBy(xpath = "//input[@id='email_0']")
	WebElement email;
	
	@FindBy(xpath = "//select[@id='type_0']")
	WebElement dropdown;
	
	@FindBy(xpath = "//button[contains(text(),'Add')]")
	WebElement add;
	
	@FindBy(xpath = "//button[@class='btn btn-primary o_form_button_save']")
	WebElement save;
	
	public ContactPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyPageTitle() {
		return driver.getTitle();
	}
	
	public void createNewContact(String a,String b,String c,String d,String e,String f) {
		newContact.click();
		radio.click();
		con_name.sendKeys(a);
		companyname.sendKeys(b+Keys.ENTER);
		Select s = new Select(dropdown);
		s.selectByIndex(2);
		postion.sendKeys(c);
		phone.sendKeys(d);
		mobile.sendKeys(e);
		email.sendKeys(f);
		add.click();
		save.click();
		
		
	}
	
	
	
	
	
	
	
}
