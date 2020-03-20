package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SopraHome {
	public WebDriver driver;
	
	public SopraHome(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='well']")
	WebElement wellness;
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	WebElement close;
	
	public void closePopUp() {
		close.click();
	}
	
	public void selectWellness() {
		wellness.click();
	}
	

}
