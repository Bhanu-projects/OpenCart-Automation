package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HeaderSection extends BasePage{

	public HeaderSection(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='col text-end']//ul[@class='list-inline']//span")
	List<WebElement> headerSection;
	
	public void section(String str) {
		for(WebElement ele : headerSection) {
			if(ele.getText().contains(str)) {
				scrollClick(ele);
				break;
			}
		}
	}
	
	
}
