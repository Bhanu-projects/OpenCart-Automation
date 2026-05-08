package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import net.datafaker.Faker;
import pages.HeaderSection;
import pages.LoginPage;

public class LoginTest extends BaseTest{

	Faker fake;
	LoginPage lp;
	HeaderSection hs;
	
	@BeforeMethod
	public void initializeClass() {
		fake = new Faker();
		lp = new LoginPage(driver);
		hs = new HeaderSection(driver);
	}
	
//	@Test(description = "TC-LG-001: Login Valid Credentials")
	public void verifySuccessfullLogin() {
		Assert.assertTrue(lp.verifyHomePage(), "!?Home Page is Not Loaded Properly?!");
		hs.navigateTo("My Account", "Login");
		Assert.assertTrue(lp.verifyLoginPage(), "!?Login Page is Not Loaded Properly?!");
		lp.login("admin@gmail.com", "admin@123");
		Assert.assertEquals(lp.verifySuccessfullLogin(), "My Account", "!?Login Email or Password Incorrect.Please Check and Try Again?!");
	}
	
	@Test(description = "TC-LG-002: Login Invalid Credentials")
	public void verifyInvalidlLogin() {
		Assert.assertTrue(lp.verifyHomePage(), "!?Home Page is Not Loaded Properly?!");
		hs.navigateTo("My Account", "Login");
		Assert.assertTrue(lp.verifyLoginPage(), "!?Login Page is Not Loaded Properly?!");
		lp.login(fake.internet().emailAddress(), fake.internet().password());
		Assert.assertEquals(lp.getAlertMsg(), "Warning: No match for E-Mail Address and/or Password.", "!?Login Email and Password Not Registered. Please Register and Try Again?!");
	}
}
