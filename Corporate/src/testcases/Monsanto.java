package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import corporate_pages.Corporate_Landing;
import generic.*;

public class Monsanto 
{
	WebDriver Driver ;
	
	Utilities Utils = new Utilities();
	Corporate_Landing Landing ;
	
	String UN="19831983@Monsanto";
	String PW="Medi@123";
	
	@Test
	public void loginMosanto()
	{
		this.Driver = Utils.launchBrowser("Chrome");
		Utils.launchApp("https:\\portal.medibuddy.in");
		
		Landing = new Corporate_Landing( this.Driver );
		
		Landing.portalLogin( UN , PW );
	}
}
