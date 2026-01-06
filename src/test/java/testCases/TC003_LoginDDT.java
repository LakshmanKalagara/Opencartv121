package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")
	public void verfiy_LoginDDT(String email,String pwd,String exp)
	{
		logger.info("***Test TC003_LoginDDT Started***");
		try {
		
		HomePage HP=new HomePage(driver);
		HP.clickMyAccount();
		logger.info("***Account clicked***");
		HP.clickLogin();
		logger.info("***Login clicked***");
		
		LoginPage LP=new LoginPage(driver);
		LP.setEmail(email);
		logger.info("***Email set***");
		LP.setPassword(pwd);
		logger.info("***password set***");
		LP.clickLogin();
		logger.info("***Logged In***");
        MyAccountPage macc=new MyAccountPage(driver);
		
		boolean TargetPage=macc.isMyAccountPageExist();
		
		if(exp.equalsIgnoreCase("valid")) {
			if(TargetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(TargetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}		
		}
	
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("***Test TC003_LoginDDT Finished***");
		
	}
	

}
