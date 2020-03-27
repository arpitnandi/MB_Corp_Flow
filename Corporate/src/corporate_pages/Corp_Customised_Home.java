package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Corp_Customised_Home 
{
	WebDriver driver ;
	
	public Corp_Customised_Home( WebDriver driver )
	{
		PageFactory.initElements( driver, this );
		this.driver = driver ;
	}
	
	
	//Elements
	@FindBy( xpath = "//*[text()='Home']" )
	public WebElement Home ;
	
	@FindBy( xpath = "//*[text()='Your health buddy on-the-go!']" )
	public WebElement Your_health_buddy_on_the_go_Modal ;
	
	@FindBy( xpath = "//*[text()='sponsored Health check ']" )
	public WebElement sponsored_Health_check_Modal ;
	
	@FindBy( xpath = "//button[@data-dismiss='modal']" )
	public WebElement Close_Modal ;
	
	@FindBy( xpath = "//button[text()='Close']" )
	public WebElement Close_Btn ;
	
	@FindBy( xpath = "//a[text()='Book Now']" )
	public WebElement sponsored_Health_check_Book_Now_Btn ;
	
	@FindBy( xpath = "//*[text()='OUTPATIENT SERVICES?']" )
	public WebElement OUTPATIENT_SERVICES_Modal ;
	
	@FindBy( id = "nav-toggle" )
	public WebElement Menu ;
	
	@FindBy( xpath = "//*[text()='Wellness']" )
	public WebElement Wellness_Menu_Option ;

	@FindBy( xpath = "//*[@*='dLabel2' and contains(text(),'Wellness')]" )
	public WebElement Wellness_Dropdown ;
	
	@FindBy( id = "A1" )
	public WebElement Wellness_N_Pharmacy_Dropdown ;
	
	@FindBy( xpath = "//*[text()='Wellness']/../.." )
	public WebElement Wellness_Tile ;
	
	@FindBy( id = "btnHealthCheck" )
	public WebElement Health_Check_Btn ;
	
	@FindBy( xpath = "//*[text()='Annual Health Check']" )
	public WebElement Anual_Health_Check_Option ;
	
	@FindBy( xpath = "//li/*[contains(text(),'Health Check Appointment')]" )
	public WebElement Health_Check_Appointment_Option ;
	
	@FindBy( xpath = "//*[text()='Book Appointment']" )
	public WebElement Book_Appointment_Option ;
	
	@FindBy( xpath = "//*[text()='Health Check']/.." )
	public WebElement Heart_Beat_Symbol ;
	
	@FindBy( xpath = "//*[text()='Health Check']/following-sibling::*/*[text()='Book now']" )
	public WebElement HC_Book_Now_Btn ;
	
}
