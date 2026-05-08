package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class RegistrationPage extends BasePage{

	public RegistrationPage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(css = "div[id='content'] h3")
	WebElement homePage;
	
	@FindBy(tagName = "h1")
	WebElement registrationPage;
	
	@FindBy(id = "input-firstname")
	WebElement inputFirstName;
	
	@FindBy(id = "input-lastname")
	WebElement inputLastName;
	
	@FindBy(id = "input-email")
	WebElement inputEmail;
	
	@FindBy(id = "input-password")
	WebElement inputPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement inputPrivacyPolicy;
	
	@FindBy(css = ".btn.btn-primary")
	WebElement inputContinueBtn;
	
	@FindBy(css = "div[id='content'] h1")
	WebElement successfullMsg;
	
	@FindBy(xpath = "//a[normalize-space()='Edit Account']")
	WebElement editAccount;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement errorMsg;
	
	@FindBy(id = "error-firstname")
	WebElement errorFirstName;
	
	@FindBy(id = "error-lastname")
	WebElement errorLastName;
	
	@FindBy(id = "error-email")
	WebElement errorEmail;
	
	@FindBy(id = "error-password")
	WebElement errorPassword;
	
	public boolean verifyHomePage() {
		return isDisplayed(homePage);
	}
	
	public boolean verifyRegistrationPage() {
		return isDisplayed(registrationPage);
	}

	public void fillRegisterDetails(String firstName, String lastName, String email, String pwd) {
		scrollInput(inputFirstName, firstName);
		scrollInput(inputLastName, lastName);
		scrollInput(inputEmail, email);
		scrollInput(inputPassword, pwd);
	}
	
	public void agreePrivacyPolicy() {
		scrollClick(inputPrivacyPolicy);
	}
	
	public void clickContinueBtn() {
		scrollClick(inputContinueBtn);
	}
	
	public String verifyAccountCreatedSuccessfullMsg() {
		waitOnly(editAccount);
		return successfullMsg.getText();
	}
	
	public String getAlertMsg() {
		waitOnly(errorMsg);
		return errorMsg.getText();
	}
	
	public String getErrorFirstName() {
		scrollOnly(errorFirstName);
		return errorFirstName.getText();
	}
	
	public String getErrorLastName() {
		scrollOnly(errorLastName);
		return errorLastName.getText();
	}
	
	public String getErrorEmail() {
		scrollOnly(errorEmail);
		return errorEmail.getText();
	}
	
	public String getErrorPwd() {
		scrollOnly(errorPassword);
		return errorPassword.getText();
	}
}
