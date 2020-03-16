package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.Constants;

public class HealthCheck {
	
public WebDriver driver;
    @FindBy(xpath="//a[text()='Health Check']")
    WebElement healthcheck;

    @FindBy(xpath="//a[contains(text(),'Book now')]")
    WebElement booknow;
    
    @FindBy(xpath="/html/body/div[1]/div/div/div/div[1]/div[1]/img")
	WebElement city;
    
    @FindBy(xpath="//div[@class='action bold mdbTxt text-right']//span[contains(text(),'View Package')]")
	WebElement viewpkg;
    
    @FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement bookapoint;
    
    @FindBy(xpath="//div[@id='42245aaaaaaaaaaaaaaaaaaaaaaaaaaa']//a[@class='btn btn-primary pull-right clearfix btn-xs'][contains(text(),'Select slot')]")
	WebElement selectslot;
    
    @FindBy(xpath="//label[contains(text(),'08:00 AM')]")
	WebElement timings;
    
    @FindBy(xpath="//a[contains(text(),'Confirm slot')]")
	WebElement confirmTime;
    
    @FindBy(xpath="//span[contains(text(),'Continue')]")
	WebElement cont;
	
	public HealthCheck(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}	
	
	public void health() {
		healthcheck.click();
	}	
	
	public void book() {
		booknow.click();
	}
	
	
	public void WaitExplicitly()		{
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(city));
		
	}
	
	public void citySelect() {
		city.click();
	}
	
	
	public void viewPackage() {
		viewpkg.click();
	}	
	
	
	public void bookAppointment() {
		bookapoint.click();
	}
	
	
	public void slotSelect() {
		selectslot.click();
	}
		
	public void selectTime() {
		timings.click();
	}
		
	public void slotConfirm() {
		confirmTime.click();
	}	
	
	public void continueNext() {
		cont.click();
	}
}
