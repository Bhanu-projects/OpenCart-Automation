package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import net.datafaker.Faker;
import pages.MyAccountPage;
import pages.RegistrationPage;

public class RegistrationTest extends BaseTest{
	
	RegistrationPage rp;
	Faker fake;
	String firstName;
	String lastName;
	String email;
	String pwd;
	SoftAssert soft;
	MyAccountPage map;
	
	
	@BeforeMethod
	public void initializeClass() {
		rp = new RegistrationPage(driver);
		fake = new Faker();
		soft = new SoftAssert();
		map = new MyAccountPage(driver);
	}
	
	@Test(description = "TC_RG_001: Register Valid Account")
	public void verifySuccessfullRegistration() {
		Assert.assertTrue(rp.verifyHomePage(), "!?Home Page is Not Loaded Properly?!");
		map.navigateTo("My Account", "Register");
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
		map.navigateTo("My Account", "Logout");
		map.navigateTo("My Account", "Register");
		Assert.assertTrue(rp.verifyRegistrationPage(), "!?Registration Page is Not Loaded Properly?!");
		rp.fillRegisterDetails(firstName, lastName, email, pwd);
		rp.agreePrivacyPolicy();
		rp.clickContinueBtn();
		Assert.assertTrue(rp.getAlertMsg().contains("Warning: E-Mail Address is already registered!"), "Didn't get the Warning message.Either You registered with new email or warning message must be wrong");
	}
	
	@Test(description = "TC_RG_003: Register Empty Submission")
	public void verifyEmptySubmission() {
		Assert.assertTrue(rp.verifyHomePage(), "!?Home Page is Not Loaded Properly?!");
		map.navigateTo("My Account", "Register");
		Assert.assertTrue(rp.verifyRegistrationPage(), "!?Registration Page is Not Loaded Properly?!");
		rp.clickContinueBtn();
		soft.assertEquals(rp.getAlertMsg(), "Warning: You must agree to the Privacy Policy!");
		soft.assertEquals(rp.getErrorFirstName(), "First Name must be between 1 and 32 characters!", "!?Please Enter First Name, it should not be Empty");
		soft.assertEquals(rp.getErrorLastName(), "Last Name must be between 1 and 32 characters!", "!?Please Enter Last Name, it should not be Empty");
		soft.assertEquals(rp.getErrorEmail(), "E-Mail Address does not appear to be valid!", "!?Please Enter Email, it should not be Empty");
		soft.assertEquals(rp.getErrorPwd(), "Password must be between 6 and 40 characters!", "!?Please Enter Password, it should not be Empty");
		soft.assertAll();
	}
}
