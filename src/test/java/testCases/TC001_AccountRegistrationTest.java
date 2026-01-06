package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("****TC001_AccountRegistrationTest****");
		try {
		HomePage HP=new HomePage(driver);
		HP.clickMyAccount();
		logger.info("Clicked My Account");
		HP.clickRegister();
		logger.info("Clicked Register");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		
		logger.info("providing register details");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		regpage.setEmail(randomString()+"@gmail.com");
		regpage.setTelephone(randomNumString());
		String pwd=randomPwdString();
		regpage.setPassword(pwd);
		regpage.setConfirmPassword(pwd);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		String msg=regpage.getConfirmationMsg();
		if(msg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed: ");
			logger.debug("debug Logs");
			Assert.assertFalse(false);
		}
	
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("****Finished TC001_AccountRegistrationTest****");
	}
	
}

