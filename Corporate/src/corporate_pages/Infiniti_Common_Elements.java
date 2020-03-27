package corporate_pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Infiniti_Common_Elements 
{
	WebDriver driver ;
	
	public Infiniti_Common_Elements( WebDriver driver ) 
	{
		PageFactory.initElements( driver, this );
	}
	
	
	//Elements
	@FindBy( xpath = "//*[@*='user']" )
	public WebElement MB_Logo ;
	
	@FindBy( xpath = "//*[contains(text(),'Log in')]" )
	public WebElement Log_in ;
	
	@FindBy( xpath = "//*[@*='user']" )
	public WebElement User_Avatar ;
	
	@FindBy( xpath = "//*[contains(text(),'Track order')]" )
	public WebElement Track_Order ;
	
	@FindBy( xpath = "//*[contains(text(),'Order Details')]" )
	public List<WebElement> Order_Details ;
	
	@FindBy( xpath = "//*[@class='panel-body']/div/div[1]/div/p" )
	public List<WebElement> Order_Name ;
	
	@FindBy( xpath = "//*[contains(text(),'Sign out')]" )
	public WebElement Sig_out ;
}
