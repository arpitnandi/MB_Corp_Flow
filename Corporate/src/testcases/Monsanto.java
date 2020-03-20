package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.Login;
import corporate_pages.MbPage;
import corporate_pages.MultipleHealth;

public class Monsanto extends Corporate_class {
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
	public void monsanto() throws InterruptedException {
		 loginpage = new Login(driver);	    
		 loginpage.login("19831983@Monsanto","Medi@123");  
		 multiHealth = new MultipleHealth(driver);
		 multiHealth.pop();
		 multiHealth.hoverHealthMonsanto();
		 Thread.sleep(20000);
		 mbPage = new MbPage(driver);
		 mbPage.citySelect();
		 Thread.sleep(5000);
		 mbPage.viewPackage();
		 Thread.sleep(3000);
		 mbPage.bookAppointment();
		 Thread.sleep(3000);
		 mbPage.slotSelect();
		 mbPage.selectTime();
		 Thread.sleep(2000);
		 mbPage.slotConfirm();
		 Thread.sleep(6000);
		 mbPage.continueNext();
  }
}
