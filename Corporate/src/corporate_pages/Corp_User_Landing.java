package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Corp_User_Landing
{
	WebDriver Driver ;
	
	public Corp_User_Landing( WebDriver driver ) 
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
	
	@FindBy( xpath = "//*[text()='Employee']" )
	public WebElement Employee_Radio_Btn ;
	
	@FindBy( xpath = "//*[text()='Confirm']" )
	public WebElement Confirm_Btn ;
	
	
	//Page Actions
	public void portalLogin( String UN, String PW )
	{
		this.UserName_Field.sendKeys( UN );
		this.Password_Field.sendKeys( PW );
		this.Signin_Btn.click();
	}
	
	public void selectEmpRole( String Role )
	{
		if( Role.equalsIgnoreCase( "Employee" ) )
		{
			this.Employee_Radio_Btn.click();
			this.Confirm_Btn.click();
		}
	}
	
}
