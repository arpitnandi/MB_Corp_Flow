package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WiproWellness {
public WebDriver driver;
	
	public WiproWellness(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//span[contains(text(),'Wellness Services')]")
	WebElement wellness;
	
	@FindBy(xpath="//a[contains(text(),'Annual Health Checkup - Cashless')]")
	WebElement annualSelect;
	
	public void wellnessDdwn() {
		wellness.click();
	}
		
	public MultipleHealth annualHealth() {
		annualSelect.click();
		return new MultipleHealth(driver);
	}

}
