package corporate_pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Revamped_HC_Description 
{
	WebDriver driver ;
	
	public Revamped_HC_Description( WebDriver driver ) 
	{
		PageFactory.initElements( driver, this );
	}
	
	@FindBy( xpath = "//div[@*='logo gender']/*" )
	public WebElement Beneficiary_Gender_Logo ;
	
	@FindBy( className = "heading" )
	public WebElement Beneficiary_Name ;
	
	@FindBy( xpath = "//*[@*='heading']/following-sibling::*[@*='gray-color']" )
	public WebElement Package_Name ;
	
	@FindBy( xpath = "//*[text()='Book Appointment']" )
	public WebElement Book_Appointment_Btn ;
}
