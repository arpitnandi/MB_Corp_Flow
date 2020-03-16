package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.HealthCheck;
import corporate_pages.Login;
import corporate_pages.MultipleHealth;
import corporate_pages.WiproWellness;
import generic.Constants;

public class Wipro extends Corporate_class {
	WebDriver driver;
	
	  @BeforeClass
		public void browser() {
		  System.setProperty("webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe");
		  driver=new ChromeDriver();
		  driver.get("https://portal.medibuddy.in/Home.aspx");
		  driver.manage().window().maximize();
	  }
	   
		
	  @Test
	  public void loginpg() throws InterruptedException {
		  loginpage = new Login(driver);
		  		  
		  loginpage.login("TestF@wipro","Medi@123");
		  
		  wWellness = new WiproWellness(driver);
		  
		  wWellness.wellnessDdwn();
	//	  wWellness.annualHealth();
		  multiHealth = wWellness.annualHealth();
	//	  multiHealth.hoverHealth();
		  multiplentity = multiHealth.hoverHealth();
		  Thread.sleep(2000);
		  multiplentity.selfTestWipro();
		  multiplentity.chooseSelectWipro();
		  Thread.sleep(2000);
		  
		  	
	  }
}
