package testcases;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.HealthCheck;
import corporate_pages.Login;
import generic.Constants;

public class Hccb extends Corporate_class {
	WebDriver driver;
	
	static Logger Log = Logger.getLogger(Hccb.class.getName());
	
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
  public void hccb() throws InterruptedException {
	  loginpage = new Login(driver);
	  constant = new Constants(driver);
	  healthcheck = new HealthCheck(driver);
	  
//	  loginpage.user("test101@hccb","Mahs@123");
//	  loginpage.pswd("Mahs@123");
	  loginpage.login("test101@hccb","Mahs@123");
	  Reporter.log("Login Passed");
	  
	  constant.mousemove();
	  
//	  constant.WaitImplicitly();
	  Thread.sleep(2000);	  
	  healthcheck.health();
	  healthcheck.book();
	  Reporter.log("HealthCheck Clickable | HealthCheck Page");
	  Thread.sleep(15000);
	  healthcheck.WaitExplicitly();
//	  constant.WaitExplicitly();
	  healthcheck.citySelect();
	  Reporter.log("City Selection Passed");
	  Thread.sleep(2000);
	  healthcheck.viewPackage();
	  Reporter.log("Sponsor Package Selectable");
	  Thread.sleep(3000);
	  healthcheck.bookAppointment();
	  Reporter.log("Book Appointment Done");
	  Thread.sleep(2000);
	  healthcheck.slotSelect();
	  Thread.sleep(2000);
	  healthcheck.selectTime();
	  healthcheck.slotConfirm();
	  Reporter.log("Timings and Slot Confirmed");
	  Thread.sleep(4000);	  
	  healthcheck.continueNext();
	  Reporter.log("Continued to Last Page");
	  
  }
}
