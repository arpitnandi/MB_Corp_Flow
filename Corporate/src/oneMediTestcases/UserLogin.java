package oneMediTestcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import oneMedi_Pages.CommonClass;
import oneMedi_Pages.OnemediLogin;

public class UserLogin extends CommonClass {
	WebDriver driver;
	@BeforeClass
	 public void browser() {
		  System.setProperty("webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe");
		  driver=new ChromeDriver();
		  driver.get("https://one.medibuddy.in");
		  driver.manage().window().maximize();
		  }
	
  @Test
  public void testLogin() {
	  onemedilogin = new OnemediLogin(driver);
	  onemedilogin.signin();
	  onemedilogin.login("MEDIBUDDY\\j.srinivasan","Medi@12345");
  }
}