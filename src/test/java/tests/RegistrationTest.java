package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import net.datafaker.Faker;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest{
	
	RegistrationPage rp;
	Faker fake;
	
	@BeforeMethod
	public void initializeClass() {
		rp = new RegistrationPage(driver);
		fake = new Faker();
	}
	
	@Test(description = "TC_RG_001: Register Valid Account")
	public void verifySuccessfullRegistration() {
		Assert.assertTrue(rp.verifyHomePage(), "!?Home Page is Not Loaded Properly?!");
		rp.navigateToRegistrationPage("My Account", "Register");
		Assert.assertTrue(rp.verifyRegistrationPage(), "!?Registration Page is Not Loaded Properly?!");
		rp.fillRegisterDetails(fake.name().firstName(), fake.name().lastName(), fake.internet().emailAddress(), fake.internet().password());
		rp.agreePrivacyPolicy();
		rp.clickContinueBtn();
		Assert.assertEquals(rp.verifyAccountCreatedSuccessfullMsg(), "Your Account Has Been Created!", "!?You Account is not registered?!");
	}
}
