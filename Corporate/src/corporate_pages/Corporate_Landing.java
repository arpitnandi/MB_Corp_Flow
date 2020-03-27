package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic.*;

public class Corporate_Landing
{
	WebDriver Driver ;
	
	Utilities Utils = new Utilities();
	
	public Corporate_Landing( WebDriver driver ) 
	{
		PageFactory.initElements( driver, this);
		this.Driver = driver ;
	}
	
	//Elements
	@FindBy( id = "username" )
	public WebElement UserName_Field ;
	
	@FindBy( id = "password" )
	public WebElement Password_Field ;
	
	@FindBy( id = "signinBtn" )
	public WebElement Signin_Btn ;
	
	//Page Actions
	public void portalLogin( String UN, String PW )
	{
		this.UserName_Field.sendKeys( UN );
		this.Password_Field.sendKeys( PW );
		this.Signin_Btn.click();
	}
	
	
}
