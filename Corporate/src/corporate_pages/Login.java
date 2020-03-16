package corporate_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	
	public WebDriver driver;
	
	public Login(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@id='username']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath="//button[@id='signinBtn']")
	WebElement signin;
	
	public void user(String name) {
		username.sendKeys(name);
	}	
	
	public void pswd(String pwd) {
		password.sendKeys(pwd);
	}
		
//	public WebElement login() {
//		return signin;
//	}
	
	public MultipleHealth login(String uname,String psw)
	{
		user(uname);
		pswd(psw);
		signin.click();
		return new MultipleHealth(driver);
	}
	
	public SchenHealth loginSch(String uname,String psw)
	{
		user(uname);
		pswd(psw);
		signin.click();
		return new SchenHealth(driver);
	}
	
	

}
