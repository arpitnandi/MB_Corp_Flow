package testcases;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.Login;
import corporate_pages.MbPage;
import corporate_pages.MultipleHealth;

public class CbnI extends Corporate_class {
	WebDriver driver;
	
	static Logger Log = Logger.getLogger(CbnI.class.getName());

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
	public void cbni() throws InterruptedException {
		 loginpage = new Login(driver);	    
		 loginpage.login("CB01@CBI","03-12-1989");  
		 Reporter.log("Login Passed");
		 multiHealth = new MultipleHealth(driver);
		 multiHealth.pop();
		 multiHealth.hoverHealth();
		 Reporter.log("HealthCheck Clickable |  HealthCheck Page");
		 Thread.sleep(20000);
		 mbPage = new MbPage(driver);
		 mbPage.cityCbnI();
		 Reporter.log("City Selection Passed");
		 Thread.sleep(5000);
		 mbPage.viewPackage();
		 Reporter.log("Sponsor Package selected");
		 Thread.sleep(2000);
		 mbPage.bookAppointment();
		 Reporter.log("Book Appointment Done");
		 Thread.sleep(3000);
		 mbPage.slotSelectCbnI();
		 mbPage.selectTime();		 
		 Thread.sleep(2000);
		 mbPage.slotConfirm();
		 Reporter.log("Timings and Slot confirmed");
		 Thread.sleep(6000);
		 mbPage.continueNext();
		 Reporter.log("Continued to last Page");	
	}		
}
