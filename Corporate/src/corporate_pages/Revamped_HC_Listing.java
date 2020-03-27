package corporate_pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class Revamped_HC_Listing 
{
	WebDriver driver ;
	
	public Revamped_HC_Listing( WebDriver driver ) 
	{
		PageFactory.initElements( driver, this );
	}
	
	//Elements
	@FindBy( xpath = "//*[@*='form-loading']/span" )
	public WebElement Please_wait_Processing_Loader ;
	
	@FindBy( id = "select-city" )
	public WebElement Select_City ;
	@FindBy( xpath = "//*[text()='Choose your location']" )
	public WebElement Select_City_Modal ;
	
	@FindBy( xpath = "//*[@*='top-cities-container']/*/div" )
	public List<WebElement> Top_Cities ;
	
	@FindBy( xpath = "//*[contains(@*,'packageDetail.isSponsored')]" )
	public List<WebElement> Free_Banner ;
	
	@FindBy( xpath = "//*[@*='content']//div[@*='mdbTxt bold text-center']" )
	public List<WebElement> Sponsored_Beneficiary_Names ;
	
	@FindBy( xpath = "//div[@*='mdbTxt bold text-center']/../../following-sibling::*/button//*[@*='m-l-5 loader-inner']" )
	public List<WebElement> View_Package_inner_loader ;
	
	@FindBy( xpath = "//div[@*='mdbTxt bold text-center']/../../following-sibling::*/button" )
	public List<WebElement> Sponsored_View_Package_Btn ;
	
	
	//Page Actions
	public boolean selectAmongTopCity( String City )
	{
		boolean flag = false ;
		
		for( WebElement C : this.Top_Cities )
		{
			if( C.equals( C ) )
			{
				C.click();
				flag = true ;
				break;
			}
		}
		
		return flag ;
	}
}
