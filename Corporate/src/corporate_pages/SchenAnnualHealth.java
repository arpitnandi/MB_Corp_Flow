package corporate_pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SchenAnnualHealth {
public WebDriver driver;
	
	public SchenAnnualHealth(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(xpath="//select[@id='ddlApptFor']")
	WebElement selfTestdrp;
	
	@FindBy(xpath="//select[@id='ddlState']")
	WebElement state;
	
	@FindBy(xpath="//select[@id='ddlCity']")
	WebElement city;
	
	@FindBy(xpath="//select[@id='ddlDiagnostic']")
	WebElement diagnostic;
	
	@FindBy(xpath="//input[@id='txtApptDate1']")
	WebElement date;
	
	public void appointment() throws InterruptedException {
	//	selfTestdrp.click();
		Thread.sleep(2000);
		Select select = new Select(selfTestdrp);
		select.selectByVisibleText("testself");
	}		
	
	public void selectState() {
	 Select select = new Select(state);
	 select.selectByVisibleText("Karnataka");
	}
			
	public void selectCity() {
		Select select = new Select(city);
		select.selectByVisibleText("Bengaluru");
	}	
	
	public void selectDiagnostic() {
		Select select = new Select(diagnostic);
		select.selectByVisibleText("Aarthi Scans & Labs , Indiranagar");
	}	
	
	public void selectDate() throws InterruptedException {
		date.click();
		Thread.sleep(2000);
		date.sendKeys("03232020");
//		List<WebElement> columns=date.findElements(By.tagName("//body/div[2]/div[1]/div[2]"));
//
//		for (WebElement cell: columns){
//		   //Select 13th Date 
//		   if (cell.getText().equals("23")){
//		      cell.findElement(By.linkText("23")).click();
//		      break;
//		 }
//	}
	}
}