package oneMedi_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import corporate_pages.MultipleHealth;

public class OnemediLogin {

	
	public WebDriver driver;
	
	public OnemediLogin(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@id='idp_SignInButton']")
	WebElement signIn;
	
	@FindBy(xpath="//input[@id='userNameInput']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='passwordInput']")
	WebElement password;
	
	@FindBy(xpath="//span[@id='submitButton']")
	WebElement submit;
	
	public void signin() {
		signIn.click();
	}	
		
	public void userName(String name) {
		username.sendKeys(name);
	}
	
	public void pswd(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void login(String uname,String psw)
	{
		userName(uname);
		pswd(psw);
		submit.click();		
	}
}
