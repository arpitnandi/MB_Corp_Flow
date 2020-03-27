package corporate_pages;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class Corp_Wellness_Booking 
{
	WebDriver driver ;
	
	public Corp_Wellness_Booking( WebDriver driver ) 
	{
		PageFactory.initElements( driver, this );
		this.driver = driver ;
	}
	
	
	//Elements
	@FindBy( className = "corplogo" )
	public WebElement BOB_Logo ;
	
	@FindBy( id = "empId" )
	public WebElement Emp_Id_Field ;
	
	@FindBy( xpath = "//*[text()='Search']" )
	public WebElement Seach_Btn ;
	
	@FindBy( className = "loader-load" )
	public WebElement loader ;
	
	@FindBy( id = "ddlApptFor" )
	public WebElement Select_Beneficiary ;
	
	@FindBy( xpath = "//*[@*='nameOfBenef']" )
	public List<WebElement> Beneficiary_Tiles ;
	
	@FindBy( xpath = "//*[@*='tblPackage']/tbody/tr/td[1]" )
	public List<WebElement> AHC_Names ;

	@FindBy( xpath = "//*[@*='tblPackage']/tbody/tr/td[2]" )
	public List<WebElement> AHC_Prices ;

	@FindBy( xpath = "//*[@*='tblPackage']/tbody/tr/td[3]" )
	public List<WebElement> AHC_View_Btns ;
	
	@FindBy( xpath = "//*[@*='tblPackage']/tbody/tr/td[4]" )
	public List<WebElement> AHC_Choes_Btns ;
	
	@FindBy( id = "ddlPackage" )
	public WebElement Select_Package ;
	
	@FindBy( xpath = "//*[@*='checkboxes']/*" )
	public List<WebElement> Package_Checkboxes ;
	
	@FindBy( id = "ddlState" )
	public WebElement Select_State ;
	
	@FindBy( id = "ddlCity" )
	public WebElement Select_City ;
	
	@FindBy( id = "ddlLocation" )
	public WebElement Select_Location ;
	
	@FindBy( id = "ddlDiagnostic" )
	public WebElement Select_Provider ;
	
	@FindBy( id = "dcAddress" )
	public WebElement Selected_Provider_Address ;
	
	@FindBy( id = "btnNext" )
	public WebElement Next_Btn ;
	
	@FindBy( id = "pkgPrice" )
	public WebElement Selected_Package_Payment_Status ;

	@FindBy( xpath = "//*[@*='pkgPrice']/*" )
	public WebElement Selected_Package_Payable_Price ;
	
	@FindBy( xpath = "//*[@*='divpackageDetails']//h4" )
	public WebElement Selected_Package_Tile_Name ;
	
	@FindBy( xpath = "//*[@*='divpackageDetails']//span" )
	public WebElement Selected_Package_Tile_Price_with_Status ;
	
	@FindBy( id = "divDCLocationDetails" )
	public WebElement Selected_Provider_State_City_inside_Tile ;
	
	@FindBy( xpath = "//*[text()='View details']" )
	public WebElement View_details_Btn ;
	
	@FindBy( id = "txtApptDate1" )
	public WebElement Preferred_First_Date_Time ;

	@FindBy( id = "txtApptDate2" )
	public WebElement Preferred_Second_Date_Time ;
	
	@FindBy( className = "xdsoft_calendar" )
	public WebElement Calender ;
	
	@FindBy( xpath = "//*[@class='xdsoft_calendar']/table/tbody/tr/td/div" )
	public List<WebElement> Dates ;

	@FindBy( xpath = "//*[@class='xdsoft_time_box xdsoft_scroller_box']/div/div" )
	public List<WebElement> Time_Slots ;
	
	@FindBy( id = "ddlTimeslot" )
	public WebElement Select_Time_Slot ;
	
	@FindBy( id = "txtMobileNum" )
	public WebElement Mobile_Number ;

	@FindBy( id = "txtEmail" )
	public WebElement Email_Id ;
	
	@FindBy( id = "btnBookAppointment" )
	public WebElement Add_Appointment_Btn ;

	@FindBy( id = "btnBookAppointment" )
	public WebElement Book_Appointment_Btn ;
	
	@FindBy( xpath = "//*[text()='No thanks!']" )
	public WebElement No_thanks_Btn ;
	
	@FindBy( id = "AlertPopup" )
	public WebElement Alert_Popup ;
	
	@FindBy( id = "result" )
	public WebElement Alert_Msg ;
	
	@FindBy( xpath = "//*[text()='Okay!']" )
	public WebElement Dismiss_Alert_Btn ;
	
	@FindBy( id = "divAHCmessage" )
	public WebElement Note ;
	
	@FindBy( xpath = "//*[@*='tblSelectedAppointment']/tbody/tr/td" )
	public List<WebElement> Cart_Details ;
	
	@FindBy( xpath = "//*[text()='Proceed to booking']" )
	public WebElement Proceed_to_booking_Btn ;
	
	@FindBy( xpath = "//*[@*='tblInvoice']/tbody/tr/td" )
	public List<WebElement> Invoice_Detail ;
	
	@FindBy( xpath = "//*[text()='Continue with booking']" )
	public WebElement Continue_with_booking_Btn ;
	
	@FindBy( xpath = "//*[text()='Cancel booking']" )
	public WebElement Cancel_booking_Btn ;
	
	@FindBy( className = "razorpay-backdrop" )
	public WebElement Razorpay_Background ;
	
	@FindBy( className = "razorpay-checkout-frame" )
	public WebElement Razorpay_Modal ;
	
	@FindBy( id = "merchant-desc" )
	public WebElement Payment_Request_Id ;
	
	@FindBy( xpath = "//*[@*='amount']/*[@*='original-amount']" )
	public WebElement Razorpay_Amount ;
	
	@FindBy( id = "modal-close" )
	public WebElement Close_Razorpay ;

	@FindBy( id = "alogout" )
	public WebElement Sign_Out ;
	
	
	//Page Actions
	public String readAlertMessage() 
	{
		new WebDriverWait( driver, 15  ).until( ExpectedConditions.visibilityOf( this.Alert_Msg ));
		return this.Alert_Msg.getText();
	}
	
	public void dismissAlertPopup() throws AWTException 
	{
		Robot r = new Robot() ;
		r.mouseMove( 750, 500);
		r.mousePress( InputEvent.BUTTON1_DOWN_MASK );
		r.mouseRelease( InputEvent.BUTTON1_DOWN_MASK );
	}
	
	public boolean selectDate( String Date )
	{
		boolean flag = false ;
		for( WebElement D : this.Dates )
		{
			if( D.getText().trim().equals( Date ))
			{
				D.click();
				flag = true ;
				break;
			}
		}
		return flag ;
	}
	
	public boolean selectTime( String Time )
	{
		boolean flag = false ;
		for( WebElement T : this.Time_Slots )
		{
			if( T.getText().trim().equals( Time ))
			{
				T.click();
				flag = true ;
				break;
			}
		}
		return flag ;
	}
	
	public boolean verifyCart( List<String> Packages, String Provider, String Date, String Time )
	{
		boolean flag = false ;
		for( int i = 0 ; i < this.Cart_Details.size() / 5 ; i++ )
		{
			Assert.assertEquals( this.Cart_Details.get( i * 5 + 1 ).getText().trim(), Packages.get( i ) );
			Assert.assertEquals( this.Cart_Details.get( i * 5 + 2 ).getText().trim(), Provider );
			Assert.assertEquals( this.Cart_Details.get( i * 5 + 3 ).getText().trim().substring( 3, 5 ), Date );
			Assert.assertEquals( this.Cart_Details.get( i * 5 + 3 ).getText().trim().substring( 11, 13 ), Time.substring( 0, 2 ) );
			flag = true ;
		}
		return flag ;
	}
	
	public boolean verifyInvoice( List<String> Packages, List<String> Prices, float Total_Payable, String Provider, String Date, String Time )
	{
		boolean flag = false ;
		for( int i = 0 ; i < ( this.Invoice_Detail.size() - 2 ) / 3 ; i++ )
		{
			Assert.assertEquals( this.Invoice_Detail.get( i * 3 + 1 ).getText().trim(), Packages.get( i ) );
			Assert.assertTrue( Float.parseFloat( Prices.get( i ) ) - Float.parseFloat( this.Invoice_Detail.get( i * 3 + 2 ).getText().trim() ) < 1 );
			flag = true ;
		}
		flag = ( Total_Payable - Float.parseFloat( this.Invoice_Detail.get( this.Invoice_Detail.size() - 1 ).getText().trim() ) < 1 );
		return flag ;
	}
}
