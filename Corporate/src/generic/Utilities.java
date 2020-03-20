package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
	
}
