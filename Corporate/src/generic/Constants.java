package generic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constants 
{
	public WebDriver driver;
	public Actions Action ;
	public JavascriptExecutor Execut ;
	public Robot Robo;
	
	
	public Constants(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public void dismiss() {
		driver.switchTo().alert().dismiss();
	}
	
	public void accept() {
		driver.switchTo().alert().accept();
	}
	
	public void mousemove() {
		Actions builder = new Actions(driver);
		Action action = builder.doubleClick().build();
		action.perform();
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div/div/div[1]/div[1]/img")
	WebElement cityPopup;

	//ExplicitWait
	public void WaitExplicitly()		{
			WebDriverWait wait=new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(cityPopup));
			
		}

	public void WaitImplicitly() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	//SelectByIndex
	public void SelectDDLByIndex(WebElement ddl,int index )	{
			Select sel=new Select(ddl);
			sel.selectByIndex(index);
		}
		
		//SelectByValue
	public void SelectDDLByValue(WebElement ddl,String value ){
			Select sel=new Select(ddl);
			sel.selectByValue(value);
		}
	
	public void selectText(WebElement ddl,String value) {
		Select sel = new Select(ddl);
		sel.selectByVisibleText(value);
	}

	public void waitForElementToBeVisible( WebElement Element , int time ) 
	{
		new WebDriverWait( this.driver , time ).until( ExpectedConditions.visibilityOf( Element ));
	}
	
	public void waitForElementToBeClikable( WebElement Element , int time ) 
	{
		new WebDriverWait( this.driver , time ).until( ExpectedConditions.elementToBeClickable( Element ));
	}
	
	public void waitForElementToBeSelected( WebElement Element , int time ) 
	{
		new WebDriverWait( this.driver , time ).until( ExpectedConditions.elementToBeSelected( Element ));
	}
	
	@SuppressWarnings("unused")
	private void popup( String Do ) 
	{
		if( Do.equalsIgnoreCase("accept") )
			this.driver.switchTo().alert().accept();
		if( Do.contains("message") || Do.contains("text") )
			this.driver.switchTo().alert().getText();
		else
			this.driver.switchTo().alert().dismiss();
	}
	
	public boolean elemenIsPresent( WebElement Element )
	{
		boolean flag = false;
		try {
			if( Element.isDisplayed() )
				flag = true ;
		}
		catch( Exception E )
		{
			if( E.getClass().getSimpleName().equals("NoSuchElementException") )
				flag = false;
		}
		return flag;
	}
	
	public boolean elementNotPresent( WebElement Element )
	{
		boolean flag = false;
		try {
			if( Element.isDisplayed() )
				flag = false ;
		}
		catch( Exception E )
		{
			if( E.getClass().getSimpleName().equals("NoSuchElementException") )
				flag = true ;
		}
		return flag;
	}
	
	public boolean waitForElementToBeDisapear( WebElement Element , int time ) 
	{
		boolean flag = false;
		try {
			new WebDriverWait( driver , time ).until( ExpectedConditions.visibilityOf( Element ) );
			new WebDriverWait( driver , time ).until( ExpectedConditions.invisibilityOf( Element ) );
		}
		catch( Exception E )
		{}
		return flag;
	}
	
	public void mouseMoveToElement( WebElement Element ) 
	{
		Action = new Actions( driver );
		Action.moveToElement( Element ).build().perform();
	}

	public void mouseClick() 
	{
		Action = new Actions( driver );
		Action.click().build().perform();
	}
	
	public void scrollUp( int repeat )
	{
		Execut = (JavascriptExecutor) driver;
		for( int i = 0 ; i < repeat ; i++ )
			Execut.executeScript( "window.scrollBy(0,-100)" );
	}

	public void scrollDown( int repeat )
	{
		Execut = (JavascriptExecutor) driver;
		for( int i = 0 ; i < repeat ; i++ )
			Execut.executeScript( "window.scrollBy(0,100)" );
	}

	public void scrollLeft( int repeat )
	{
		Execut = (JavascriptExecutor) driver;
		for( int i = 0 ; i < repeat ; i++ )
			Execut.executeScript( "window.scrollBy(-100,0)" );
	}

	public void scrollRight( int repeat )
	{
		Execut = (JavascriptExecutor) driver;
		for( int i = 0 ; i < repeat ; i++ )
			Execut.executeScript( "window.scrollBy(100,0)" );
	}

	public void scrollToElement( WebElement Element )
	{
		Execut = (JavascriptExecutor) driver;
		Execut.executeScript( "arguments[0].scrollIntoView()" , Element );
	}

	public void clickOnElement( WebElement Element )
	{
		Execut = (JavascriptExecutor) driver;
		Execut.executeScript( "arguments[0].click(true)" , Element );
	}
	
	public boolean selectFromDropdown( WebElement Dropdown, String Option )
	{
		boolean flag = false ;
		Select S = new Select( Dropdown );
		List<WebElement> Options;
		
		for( long i = 0 ; i < 1000000000 ; i++ )
		{
			S = new Select( Dropdown );
			Options = S.getOptions();
			if( Options.size() > 1 )
			{
				if( ! S.getFirstSelectedOption().getText().equals( Option ) )
					S.selectByVisibleText( Option );
				flag = true ;
				break;
			}
		}
		
		return flag ;
	}
	
	public void selectElementAmong( List<WebElement> ElementGroup, String Value ) 
	{
		for( WebElement D : ElementGroup )
		{
			if( D.getText().equals( Value ) )
			{	
				D.click();
				break;
			}
		}
	}
	
	public void dismissAlerts() throws AWTException 
	{
		Robo = new Robot() ;
		Robo.mouseMove( 500, 500 );
		Robo.mousePress( InputEvent.BUTTON1_DOWN_MASK );
		Robo.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
	}
	
}
