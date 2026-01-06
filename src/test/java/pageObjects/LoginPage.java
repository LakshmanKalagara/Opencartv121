package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	
@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;

@FindBy(xpath="//input[@value='Login']") WebElement lnkLoginbtn;


public void clickLogin() {
	lnkLoginbtn.click();
	
}
public void setEmail(String email) {
	txtEmail.sendKeys(email);
}

public void setPassword(String pwd) {
	txtPassword.sendKeys(pwd);
}




}
