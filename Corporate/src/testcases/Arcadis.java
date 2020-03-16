package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import corporate_pages.ArcadisHome;
import corporate_pages.Corporate_class;
import corporate_pages.Login;
import corporate_pages.MultipleEntity;

public class Arcadis extends Corporate_class {
	WebDriver driver;
	
	  @BeforeClass
		public void browser() {
		  System.setProperty("webdriver.chrome.driver", "C:\\chrome extension\\chromedriver.exe");
		  driver=new ChromeDriver();
		  driver.get("https://portal.medibuddy.in/Home.aspx");
		  driver.manage().window().maximize();
	  }
	  
     @Test
     public void arcadisLogin() throws InterruptedException {
		 loginpage = new Login(driver);	    
		 loginpage.login("test1@arcadis","01-01-1992");
		 arcadisHome = new ArcadisHome(driver);
		 arcadisHome.closePopUp();
		 Thread.sleep(2000);
		 arcadisHome.mouseHoverBook();
		 
		 multiplentity = new MultipleEntity(driver);
		 Thread.sleep(3000);
		 multiplentity.selfTestArcadis();
		 Thread.sleep(3000);
		 multiplentity.chooseSelectArcadis();
		 Thread.sleep(40000);
		 	 
		 				
		 // moving on will come back to you later
//		 Thread.sleep(2000);
//		 arcadisHome.bookAppointment();		 
		 
      }
}
