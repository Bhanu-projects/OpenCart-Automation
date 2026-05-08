package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "div[id='content'] h3")
	WebElement homePage;
	
	@FindBy(xpath = "//h2[normalize-space()='Returning Customer']")
	WebElement loginPage;
	
	@FindBy(id = "input-email")
	WebElement inputEmail;
	
	@FindBy(id = "input-password")
	WebElement inputPwd;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginBtn;
	
	@FindBy(tagName = "h1")
	WebElement successfullLogin;
	
	@FindBy(tagName = "h1")
	WebElement successfullLogout;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement errorMsg;
	
	public boolean verifyHomePage() {
		return isDisplayed(homePage);
	}
	
	public boolean verifyLoginPage() {
		return isDisplayed(loginPage);
	}
	
	public void login(String email, String pwd) {
		scrollInput(inputEmail, email);
		scrollInput(inputPwd, pwd);
		scrollClick(loginBtn);
	}
	
	public String verifySuccessfullLogin() {
		return successfullLogin.getText();
	}
	
	public String getAlertMsg() {
		waitOnly(errorMsg);
		return errorMsg.getText();
	}
	
	public String verifySuccessfullLogout() {
		return successfullLogout.getText();
	}

}
