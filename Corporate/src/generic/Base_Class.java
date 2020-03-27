package generic;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;


public class Base_Class 
{
	public static WebDriver driver;
	public static Actions Action;
	public static JavascriptExecutor Execut;
	public static String Browser = "chrome";
	
	
	//Reusable methods
	public void launchBrowser( String Browser )
	{
		if( Browser.equalsIgnoreCase("chrome") )
		{
			System.setProperty( "webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe" );
			driver = new ChromeDriver();
		}
		if( Browser.equalsIgnoreCase("firefox") )
		{
			System.setProperty( "webdriver.gecko.driver", "C:\\chrome extension\\geckodriver.exe" );
			driver = new FirefoxDriver();
		}
		if( Browser.equalsIgnoreCase("IE") )
		{
			System.setProperty( "webdriver.ie.driver", "C:\\chrome extension\\IEDriverServer.exe" );
			driver = new InternetExplorerDriver();
		}
		if( Browser.equalsIgnoreCase("Eadge") )
		{
			System.setProperty( "webdriver.edge.driver", "C:\\chrome extension\\msedgedriver.exe" );
			driver = new EdgeDriver();
		}
		Execut = (JavascriptExecutor) driver ;
		Action = new Actions( driver );
	}
	
	public void launchApp( String URL ) 
	{
		driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
		driver.manage().window().maximize();
		driver.get(URL);
	}
	

	@BeforeTest
	public void startTest()
	{
		this.launchBrowser( Browser );
	}
	
	@AfterTest
	public void endTest() throws InterruptedException
	{
		Thread.sleep( 5000 );
		driver.close();
	}
	
}
