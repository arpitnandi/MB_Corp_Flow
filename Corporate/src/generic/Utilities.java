package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities 
{
	WebDriver Driver ;
	
	public WebDriver launchBrowser( String Browser )
	{
		if( Browser.equalsIgnoreCase("chrome") )
		{
			System.setProperty( "webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe" );
			Driver = new ChromeDriver();
		}
		return Driver;
	}
	
	public void launchApp( String URL ) 
	{
		this.Driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
		this.Driver.get(URL);
		this.Driver.manage().window().maximize();
	}
	
	
	public void waitForElementToBeVisible( WebElement Element , int time ) 
	{
		new WebDriverWait( this.Driver , time ).until( ExpectedConditions.visibilityOf( Element ));
	}
	
	public void waitForElementToBeClikable( WebElement Element , int time ) 
	{
		new WebDriverWait( this.Driver , time ).until( ExpectedConditions.elementToBeClickable( Element ));
	}
	
	public void waitForElementToBeSelected( WebElement Element , int time ) 
	{
		new WebDriverWait( this.Driver , time ).until( ExpectedConditions.elementToBeSelected( Element ));
	}
	
	@SuppressWarnings("unused")
	private void popup( String Do ) 
	{
		if( Do.equalsIgnoreCase("accept") )
			this.Driver.switchTo().alert().accept();
		if( Do.contains("message") || Do.contains("text") )
			this.Driver.switchTo().alert().getText();
		else
			this.Driver.switchTo().alert().dismiss();
	}
	
}
