package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='col text-end']//ul[@class='list-inline']//span")
	List<WebElement> headerSection;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right show']//a")
	List<WebElement> subSection;
	
	public void navigateTo(String sectionName, String subSectionName) {
		for(WebElement ele : headerSection) {
			if(ele.getText().equals(sectionName)) {
				scrollClick(ele);
				break;
			}
		}
		
		for(WebElement ele : subSection) {
			if(ele.getText().equals(subSectionName)) {
				scrollClick(ele);
				break;
			}
		}
	}

}
