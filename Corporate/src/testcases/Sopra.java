package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.Corporate_class;
import corporate_pages.Login;

public class Sopra extends Corporate_class {
	WebDriver driver;

@BeforeClass
	public void browser() {
	  System.setProperty("webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("https://portal.medibuddy.in/Home.aspx");
	  driver.manage().window().maximize();
}

@Test
public void sopraLogin() throws InterruptedException {
	 loginpage = new Login(driver);	    
	 loginpage.login("test101@ssi","Medi@123");
  }
}
