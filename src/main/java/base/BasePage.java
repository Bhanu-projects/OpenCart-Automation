package base;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions act;
	protected JavascriptExecutor js;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.act = new Actions(driver);
		this.js = (JavascriptExecutor)driver;
		
		PageFactory.initElements(driver, this);
	}
	
	public void waitOnly(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitClick(WebElement element) {
		waitOnly(element);
		element.click();
	}
	
	public void scrollOnly(WebElement element) {
		act.scrollToElement(element).perform();
	}
	
	public void scrollClick(WebElement element) {
		waitOnly(element);
		scrollOnly(element);
		element.click();
	}
	
	public boolean isDisplayed(WebElement element) {
		waitOnly(element);
		scrollOnly(element);
		return element.isDisplayed();
	}
	
	public void scrollInput(WebElement element, String str) {
		scrollOnly(element);
		element.clear();
		element.sendKeys(str);
	}
	
	public void scrollUsingJs(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		wait.until(driver -> {
		    Double before = (Double) ((JavascriptExecutor) driver).executeScript("return arguments[0].getBoundingClientRect().top;", element);
		    try { Thread.sleep(300); } catch (Exception e) {}
		    Double after = (Double) ((JavascriptExecutor) driver).executeScript("return arguments[0].getBoundingClientRect().top;", element);
		    return Math.abs(before-after)<1;
		});
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void scrollClickUsingJs(WebElement element) {
		scrollUsingJs(element);
		element.click();
	}
	
	public void scrollInputUsingJs(WebElement element, String str) {
		scrollUsingJs(element);
		element.sendKeys(str);
	}
	
	public void scrollCenterUsingJs(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		wait.until(driver -> {
		    Double before = (Double) ((JavascriptExecutor) driver).executeScript("return arguments[0].getBoundingClientRect().top;", element);
		    try { Thread.sleep(300); } catch (Exception e) {}
		    Double after = (Double) ((JavascriptExecutor) driver).executeScript("return arguments[0].getBoundingClientRect().top;", element);
		    return Math.abs(before-after)<1;
		});
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void scrollCenterClickUsingJs(WebElement element) {
		scrollCenterUsingJs(element);
		element.click();
	}
	
	public void forceClickUsingJs(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	} 
}
