package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MbPage {
	public WebDriver driver;

    public MbPage(WebDriver driver)
    {
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}	
//    @FindBy(xpath="//a[text()='Health Check']")
//    WebElement healthcheck;
//
//    @FindBy(xpath="//a[contains(text(),'Book now')]")
//    WebElement booknow;
    
    @FindBy(xpath="/html/body/div[1]/div/div/div/div[1]/div[1]/img")
	WebElement city;
    
    @FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
    WebElement cityRealimage;
    
    @FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]")
    WebElement cityCbn;
    
    @FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/img[1]")
    WebElement citytransafe;
    
    @FindBy(xpath="//div[@class='action bold mdbTxt text-right']//span[contains(text(),'View Package')]")
	WebElement viewpkg;
    
    @FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/hc-card[1]/div[1]/div[2]/button[1]/span[1]")
    WebElement viewpkgtransafe;
    
    @FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement bookapoint;
    
    @FindBy(xpath="//div[@id='42245aaaaaaaaaaaaaaaaaaaaaaaaaaa']//a[@class='btn btn-primary pull-right clearfix btn-xs'][contains(text(),'Select slot')]")
	WebElement selectslot;
    
    @FindBy(xpath="//div[@class='row']//div[3]//div[1]//div[2]//span[1]//a[1]")
    WebElement selectSlotanalog;
    
    @FindBy(xpath="//div[@id='60708aaaaaaaaaaaaaaaaaaaaaaaaaaa']//a[@class='btn btn-primary pull-right clearfix btn-xs'][contains(text(),'Select slot')]")
    WebElement selectSlotrealImage;
    
    @FindBy(xpath="//a[@class='btn btn-primary pull-right clearfix btn-xs']")
    WebElement selectSlotCbn;
    
    @FindBy(xpath="//a[@class='btn btn-primary pull-right clearfix btn-xs']")
    WebElement selectSlotinvest;
    
    @FindBy(xpath="//*[@id=\"196932aaaaaaaaaaaaaaaaaaaaaaaaaa\"]/div[1]/div[2]/span/a")
    WebElement selectSlotngsl;
    
    @FindBy(xpath="//div[@id='60659aaaaaaaaaaaaaaaaaaaaaaaaaaa']//a[@class='btn btn-primary pull-right clearfix btn-xs'][contains(text(),'Select slot')]")
    WebElement selectSlottransafe;
    
    @FindBy(xpath="//div[@id='179736aaaaaaaaaaaaaaaaaaaaaaaaaa']//a[@class='btn btn-primary pull-right clearfix btn-xs'][contains(text(),'Select slot')]")
    WebElement selectSlotlnt;
    
    @FindBy(xpath="//a[@class='btn btn-primary pull-right clearfix btn-xs']")
    WebElement selectSlotsbi;
    
    @FindBy(xpath="//label[contains(text(),'08:00 AM')]")
	WebElement timings;
    
    @FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/section[1]/div[1]/div[3]/div[1]/div[1]/provider-selection[1]/div[1]/div[4]/div[2]/div[3]/div[1]/medi-book-slot[1]/div[1]/div[2]/ul[1]/li[2]/label[1]")
    WebElement timingstransafe;
    
    
    @FindBy(xpath="//a[contains(text(),'Confirm slot')]")
	WebElement confirmTime;
    
    @FindBy(xpath="//span[contains(text(),'Continue')]")
	WebElement cont;
    

	
	
//	public void health() {
//		healthcheck.click();
//	}	
//	
//	public void book() {
//		booknow.click();
//	}
//	
	
	public void WaitExplicitly()		{
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(city));
		
	}
	
	public void citySelect() {
		city.click();
	}
	
	public void cityRealImage() {
		cityRealimage.click();
	}
	
	public void cityCbnI() {
		cityCbn.click();
	}
	
	public void cityTransafe() {
		citytransafe.click();
	}
	
	
	public void viewPackage() {
		viewpkg.click();
	}	
	
	public void viewPackageTransafe() {
		viewpkgtransafe.click();
	}
	
	
	public void bookAppointment() {
		bookapoint.click();
	}
	
	
	public void slotSelect() {
		selectslot.click();
	}
	
	public void slotSelectanalog() {
		selectslot.click();
	}
	
	public void slotSelectRealImage() {
		selectSlotrealImage.click();
	}
	
	public void slotSelectCbnI() {
		selectSlotCbn.click();
	}
	
	public void slotSelectInvestCorp() {
		selectSlotinvest.click();
	}
	
	public void slotSelectNgsl() {
		selectSlotngsl.click();
	}
	
	public void slotSelectTransafe() {
		selectSlottransafe.click();
	}
	
	public void slotSelectSbi() {
		selectSlotsbi.click();
	}
	
	public void slotSelectLnt() {
		selectSlotlnt.click();
	}
		
	public void selectTime() {
		timings.click();
	}
	
	public void selectTimeTransafe() {
		timingstransafe.click();
	}
		
	public void slotConfirm() {
		confirmTime.click();
	}	
	
	public void continueNext() {
		cont.click();
	}

	
}
