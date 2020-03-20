package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.Login;
import corporate_pages.SchenAnnualHealth;
import corporate_pages.SchenHealth;
import generic.Constants;

public class Schneider extends Corporate_class{
	WebDriver driver;
	
	  @BeforeClass
		public void browser() {
		  System.setProperty("webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe");
		  driver=new ChromeDriver();
		  driver.get("https://portal.medibuddy.in/Home.aspx");
		  driver.manage().window().maximize();
	  }
	  
	  @AfterClass
		public void quit() {
			driver.quit();
		}
	   
		
	  @Test
	  public void schneider() throws InterruptedException {
		 loginpage = new Login(driver);	    
		  
		 loginpage.login("test101@Schneider","28-02-1967");
		 
		 schenHealth = new SchenHealth(driver);	 
		 	  
		 Thread.sleep(3000);
		 schenHealth.healthClick();
		 
//		 constant = new Constants(driver);
		 Thread.sleep(2000);
//		 constant.accept();
		 schenAnnualHealth = new SchenAnnualHealth(driver);
		 
		 schenAnnualHealth=schenHealth.selectOk();
		 schenAnnualHealth.appointment();
		 Thread.sleep(40000);
		 schenAnnualHealth.selectState();
		 Thread.sleep(2000);
		 schenAnnualHealth.selectCity();
		 Thread.sleep(2000);
		 schenAnnualHealth.selectDiagnostic();
		 Thread.sleep(2000);
		 schenAnnualHealth.selectDate();
		 
		 //datepicker remaining 
		 
		 
 
  }
}
