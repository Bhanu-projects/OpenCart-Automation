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
	String firstName;
	String lastName;
	String email;
	String pwd;
	
	
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
		firstName = fake.name().firstName();
		lastName = fake.name().lastName();
		email = fake.internet().emailAddress();
		pwd = fake.internet().password();
		rp.fillRegisterDetails(firstName, lastName, email, pwd);
		rp.agreePrivacyPolicy();
		rp.clickContinueBtn();
		Assert.assertEquals(rp.verifyAccountCreatedSuccessfullMsg(), "Your Account Has Been Created!", "!?You Account is not registered?!");
	}
	
	@Test(description = "TC_RG_002: Register Duplicate Email")
	public void verifyDuplicateEmail() {
		verifySuccessfullRegistration();
		rp.navigateToRegistrationPage("My Account", "Logout");
		rp.navigateToRegistrationPage("My Account", "Register");
		Assert.assertTrue(rp.verifyRegistrationPage(), "!?Registration Page is Not Loaded Properly?!");
		rp.fillRegisterDetails(firstName, lastName, email, pwd);
		rp.agreePrivacyPolicy();
		rp.clickContinueBtn();
		Assert.assertTrue(rp.getAlertMsg().contains("Warning: E-Mail Address is already registered!"), "Didn't get the Warning message.Either You registered with new email or warning message must be wrong");
	}
}
