package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","master"})
	public void verify_login() {
		logger.info("***Test TC002_LoginTest Started***");
		try {
		HomePage HP=new HomePage(driver);
		HP.clickMyAccount();
		logger.info("***Account clicked***");
		HP.clickLogin();
		logger.info("***Login clicked***");
		
		LoginPage LP=new LoginPage(driver);
		LP.setEmail(p.getProperty("email"));
		logger.info("***Email set***");
		LP.setPassword(p.getProperty("password"));
		logger.info("***password set***");
		LP.clickLogin();
		logger.info("***Logged In***");
		
		MyAccountPage macc=new MyAccountPage(driver);
		
		boolean TargetPage=macc.isMyAccountPageExist();
		
		Assert.assertEquals(TargetPage, true,"Login Failed");//first two parameters it will compare if pass np
		//else it fails it will give Login Failed.
		}
		catch(Exception e) {
			Assert.fail();
			
		}
		
		
		
			
		}
	}


