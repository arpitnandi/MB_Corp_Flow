package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.Login;
import corporate_pages.MultipleEntity;
import corporate_pages.MultipleHealth;
import corporate_pages.SopraHome;

public class Sopra extends Corporate_class {
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
public void sopra() throws InterruptedException {
	 loginpage = new Login(driver);	    
	 loginpage.login("test101@ssi","Medi@123");
	 
	 sopraHome = new SopraHome(driver);
	 sopraHome.closePopUp();
	 Thread.sleep(2000);
	 sopraHome.selectWellness();
	 Thread.sleep(2000);
	 multiHealth = new MultipleHealth(driver);
	// multiplentity = new MultipleEntity(driver);
	 multiplentity=multiHealth.hoverHealth();
	 Thread.sleep(5000);
	 multiplentity.selfTestSopra();
	 Thread.sleep(5000);
	 multiplentity.chooseSelectSopra();
	 //last page 
  }
}
