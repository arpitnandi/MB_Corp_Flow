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

public class Analog extends Corporate_class {
	WebDriver driver;
	
	static Logger Log = Logger.getLogger(Analog.class.getName());

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
	public void analog() throws InterruptedException {
		 loginpage = new Login(driver);	    
		 loginpage.login("Test01@analog","01011990");
		 Reporter.log("Login Passed");
		 multiHealth = new MultipleHealth(driver);
		 multiHealth.pop();
		 Thread.sleep(2000);
		 multiHealth.hoverHealth();
		 Reporter.log("Health Check clickable | HealthCheck Page");
		 mbPage = new MbPage(driver);
		 Thread.sleep(15000);
		 mbPage.WaitExplicitly();
		 mbPage.citySelect();
		 Reporter.log("City Selection Passed");
		 Thread.sleep(2000);
		 mbPage.viewPackage();
		 Reporter.log("Sponsor Package Selected");
		 Thread.sleep(7000);
		 mbPage.bookAppointment();
		 Reporter.log("Book Appointmnet Passed");
		 Thread.sleep(10000);
	/*	 mbPage.slotSelectanalog(); //Need to check again
		 Thread.sleep(2000);
		 mbPage.selectTime();
		 Thread.sleep(2000);
		 mbPage.slotConfirm();
		 Thread.sleep(4000);
		 mbPage.continueNext();	 */

  
  }
	
}
