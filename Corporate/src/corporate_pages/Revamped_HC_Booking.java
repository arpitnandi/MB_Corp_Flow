package corporate_pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Revamped_HC_Booking 
{
	WebDriver driver ;
	
	public Revamped_HC_Booking( WebDriver driver )
	{
		PageFactory.initElements( driver, this );
	}
	
	
	//Elements
	@FindBy( xpath = "//*[@*='glider-track']//li/div" )
	public List<WebElement> Available_Dates ;
	
	@FindBy( xpath = "//*[@*='content']//b" )
	public List<WebElement> Available_Provider_Names ;
	
	@FindBy( xpath = "//*[@*='content']//b/following-sibling::*/*[1]" )
	public List<WebElement> Available_Provider_Location ;
	
	@FindBy( xpath = "//*[@*='content']//a" )
	public List<WebElement> Select_slot_Btn ;
	
	@FindBy( xpath = "//*[contains(@*,'optionsRadios_')]/.." )
	public List<WebElement> Time_Slot_Radio_Btn ;
	
	@FindBy( xpath = "//*[text()='Confirm slot']")
	public WebElement Confirm_slot_Btn ; 
	
	@FindBy( xpath = "//*[@*='select-provider']//div")
	public WebElement Select_a_provider_and_timeslot_Area ;
	
	@FindBy( xpath = "//*[@*='provider-selected']/div/div[1]")
	public WebElement Selected_Provider ;
	
	@FindBy( xpath = "//*[@*='provider-selected']/div/div[2]/span")
	public WebElement Selected_TimeSlot ;
	
	@FindBy( xpath = "//*[text()='Continue']/..")
	public WebElement Continue_Btn ;
	
	
	//Page Actions
	public boolean selectDate( String Date ) 
	{
		boolean flag = false ;
		for( WebElement D : this.Available_Dates )
		{
			if( D.getText().trim().contains( Date ) )
			{
				D.click();
				flag = true ;
				break;
			}
		}
		return flag ;
	}
	
	public boolean selectTimeSlot( String TimeSlot ) 
	{
		boolean flag = false ;
		for( WebElement T : this.Time_Slot_Radio_Btn )
		{
			if( T.getText().trim().equalsIgnoreCase( TimeSlot ))
			{
				T.click();
				Assert.assertTrue( this.Confirm_slot_Btn.isDisplayed() );
				this.Confirm_slot_Btn.click();
				break;
			}
		}
		return flag ;
	}
}
