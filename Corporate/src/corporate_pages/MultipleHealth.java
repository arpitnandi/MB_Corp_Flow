package corporate_pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultipleHealth {
	
	public WebDriver driver;
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	WebElement popup;
	
	//for navigating direct view pkg
	@FindBy(xpath="//a[@id='btnPopupClick']")
	WebElement bookPop;
	
	@FindBy(id="btnPopupClick")
	WebElement bookPopid;
	
	@FindBy(xpath="//div[@class='col-sm-6 bg1']//a")
	WebElement healthHover;
	
	@FindBy(xpath="//*[@id=\"wellnessoption\"]/div[1]/a")
	WebElement healthHovermonsanto;
	
	
	public MultipleHealth(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void pop() throws InterruptedException {
		popup.click();
		Thread.sleep(4000);
	}
	
	public void popBook() throws InterruptedException {
		bookPop.click();
		Thread.sleep(2000);
	}
	
	public void popBookId() throws InterruptedException {
		bookPopid.click();
		Thread.sleep(2000);
	}
	
	public void windowHandle() {
		String parentHandle = driver.getWindowHandle(); // get the current window handle		
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
	}
	
	public MultipleEntity hoverHealth() throws InterruptedException {
		healthHover.click();
		Thread.sleep(4000);
		return new MultipleEntity(driver);
	}
	
	public MultipleEntity hoverHealthMonsanto() throws InterruptedException {
		healthHovermonsanto.click();
		Thread.sleep(4000);
		return new MultipleEntity(driver);
	}
	

}
