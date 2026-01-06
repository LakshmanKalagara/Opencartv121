package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	WebDriver driver;
	public MyAccountPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHeading;
	
	@FindBy(xpath="//a[@title='My Account']") WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout;
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public boolean isMyAccountPageExist() {
		try {
			return msgHeading.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
		
	}

}
