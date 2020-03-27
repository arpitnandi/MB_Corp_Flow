package testcases;


import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import corporate_pages.*;
import generic.*;


public class NOS extends Base_Class
{
	//Test Variables
	String URL = "https:\\portal.medibuddy.in";
	String UN="11234@NOS";
	String PW="22-07-1991";
	String EmpID = "Test3";
	String Beneficiary;
	String Package;
	String Payment_Status;
	String Price_Payable;
	String Price_Actual;
	boolean Sponsored;
	boolean Booked;
	String State = "Karnataka";
	String City = "Bengaluru";
	String Provider = "Bangalore Diagnostic & Multi Speciality Centre , Rajajinagar";
	String Date = "27";
	String Time = "09:30";
	String Mobile = "9090909090";
	String Email = "test@BOB.co";
	List<WebElement> Options ;
	String actual;
	String expected;
	
	
	//Page Objects
	Constants Const ;
	Corp_User_Landing Landing ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;

	
	// Test Scenario - "Test Revamped AHC Sponsored Booking flow for NOS User"
	@Test
	public void Test_Revamped_AHC_Sponsored_Booking_flow_for_NOS_User() throws InterruptedException, AWTException
	{	
		//Setting Test Variables
		String Beneficiary = "Test_c" ;
		String Package = "NOS Sponsored Annual Health Checkup for Officer & Non-Officer" ;
		Payment_Status = "(Sponsored)" ;
		String Price_Payable = "0" ;
		String Price_Actual = "1250" ;
		Sponsored = true ;
		Booked = true ;
		
		
		//Initiating POM objects
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver ) ;
		Home = new Corp_Customised_Home( driver ) ;
		Booking = new Corp_Wellness_Booking( driver ) ;
				
				
		// Step - 1 "User Logs in to Corporate Portal and chose 'Employee' access"		
		super.launchApp( URL );
		
		Landing.portalLogin( UN , PW );
		
		Thread.sleep( 1000 );
	
		Landing.selectEmpRole( "Employee" );
		
		
		// Step - 2 "Proceeding to Book AHC flow"
		Const.waitForElementToBeVisible( Home.OUTPATIENT_SERVICES_Modal, 3 );
		Home.Close_Modal.click();
		
		Home.Wellness_Menu_Option.click();
		
		
		// Step - 3 "Selecting required Appointment details"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		Thread.sleep( 1000 );
		for( WebElement B : Booking.Beneficiary_Tiles )
		{
			if( B.getText().equalsIgnoreCase( Beneficiary ) )
			{
				B.click();
				break;
			}
		}
		
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		Thread.sleep( 1000 );
		
		for( int i = 0 ; i < Booking.AHC_Names.size() ; i++ )
		{
			if( Booking.AHC_Names.get( i ).getText().equalsIgnoreCase( Package ) )
			{
				Booking.AHC_Choes_Btns.get( i ).click();
				break;
			}
		}
		
		
		// Step - 4 "Filling required Medical Center details"
		Const.waitForElementToBeDisapear( Booking.loader  , 15 );
		Thread.sleep( 3000 );
		
		Booking.Select_State.click();
		Thread.sleep( 1000 );
		Const.selectFromDropdown( Booking.Select_State , State );
		
		Thread.sleep( 1000 );
		
		Booking.Select_City.click();
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_City , City );
		
		Thread.sleep( 1000 );
		
		Booking.Select_Provider.click();
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_Provider , Provider );
		
		Thread.sleep( 500 );
		Booking.Next_Btn.click();
		
		
		// Step - 5 "Verify the displaying 'Package Name','Sponsored' status and 'Price' for selected Beneficiary and Provider"
		actual = Booking.Selected_Package_Tile_Name.getText() ;
		expected = Package ;
		
		Assert.assertEquals( actual, expected );
		
		if( Sponsored && ! Booked )
		{
			actual = Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 13 ) ;
			expected = Payment_Status ;

			Assert.assertEquals( actual, expected );

			actual = Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 11, 12 ) ;
			expected = Price_Payable ;

			Assert.assertEquals( actual, expected );
		}

		
		// Step - 6 "Filling required First and Second preferred dates for Appointment"
		Thread.sleep( 2000 );
		
		Booking.Preferred_First_Date_Time.click();
		Thread.sleep( 1000 );
		Const.selectElementAmong( Booking.Dates , Date );
		try {
			Const.waitForElementToBeVisible( Booking.No_thanks_Btn , 3 );
			if( Booking.No_thanks_Btn.isDisplayed() )
				Booking.No_thanks_Btn.click();
		}
		catch( Exception E )
		{} 
		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Time_Slots , Time );
			
		Booking.Preferred_Second_Date_Time.click();
		Thread.sleep( 1000 );
		try {
			Const.waitForElementToBeVisible( Booking.No_thanks_Btn , 3 );
			if( Booking.No_thanks_Btn.isDisplayed() )
				Booking.No_thanks_Btn.click();
		}
		catch( Exception E )
		{}
		Const.selectElementAmong( Booking.Dates , "" + ( Integer.parseInt( Date ) + 3 ) );
		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Time_Slots , Time );
		
		
		// Step - 7 "Filling required Patient Contact details"
		Booking.Mobile_Number.clear();
		Booking.Mobile_Number.sendKeys( Mobile );
		
		Booking.Email_Id.clear();
		Booking.Email_Id.sendKeys( Email );
		
		
		// Step - 8 "Book Appointment for the selected details"
		Booking.Book_Appointment_Btn.click();

		Const.waitForElementToBeDisapear( Booking.loader, 15 );
		Thread.sleep( 5000 );
		
		if( Sponsored && ! Booked )
		{
			Booked = true ;
			Const.dismissAlerts();
		}
		else
		{
			driver.switchTo().frame( 0 );
			Booking.Close_Razorpay.click();
			driver.switchTo().defaultContent();
		}
			
		
		// Step - 9 "Verify that 'Actual price' is displaying for the booked sponsored AHC"
		for( WebElement B : Booking.Beneficiary_Tiles )
		{
			if( B.getText().equalsIgnoreCase( Beneficiary ) )
			{
				B.click();
				break;
			}
		}
		
		for( int i = 0 ; i < Booking.AHC_Names.size() ; i++ )
		{
			if( Booking.AHC_Names.get( i ).getText().equalsIgnoreCase( Package ) )
			{
				actual = Booking.AHC_Prices.get( i ).getText() ;

				expected = Price_Actual ;

				Assert.assertEquals( actual, expected );
				
				Booking.AHC_Choes_Btns.get( i ).click();
			}
		}
		
		
		// Step - 10 "User Logs out"
		Booking.Sign_Out.click();
	}
	
	
	// Test Scenario - "Test Revamped AHC Paid Booking flow for NOS User"
	@Test
	public void Test_Revamped_AHC_Paid_Booking_flow_for_NOS_User() throws InterruptedException, AWTException
	{	
		//Setting Test Variables
		String Beneficiary = "Test_c" ;
		String Package = "NOS Sponsored Annual Health Checkup for Officer & Non-Officer" ;
		String Price_Actual = "1250" ;
		Sponsored = true ;
		Booked = true ;
				
				
		//Initiating POM objects
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver ) ;
		Home = new Corp_Customised_Home( driver ) ;
		Booking = new Corp_Wellness_Booking( driver ) ;

		
		// Step - 1 "User Logs in to Corporate Portal and chose 'Employee' access"
		super.launchApp( URL );
		
		Landing.portalLogin( UN, PW );
		
		Thread.sleep( 1000 );
		Landing.selectEmpRole( "Employee" );

		
		// Step - 2 "Proceeding to Book AHC flow"
		Const.waitForElementToBeVisible( Home.OUTPATIENT_SERVICES_Modal, 3 );
		Home.Close_Modal.click();
		
		Home.Wellness_Menu_Option.click();

		
		// Step - 3 "Selecting required Appointment details"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );

		Thread.sleep( 1000 );
		for( WebElement B : Booking.Beneficiary_Tiles )
		{
			if( B.getText().equalsIgnoreCase( Beneficiary ) )
			{
				B.click();
				break;
			}
		}
		
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		Thread.sleep( 1000 );
		
		for( int i = 0 ; i < Booking.AHC_Names.size() ; i++ )
		{
			if( Booking.AHC_Names.get( i ).getText().equalsIgnoreCase( Package ) )
			{
				Booking.AHC_Choes_Btns.get( i ).click();
				break;
			}
		}

		
		// Step - 4 "Filling required Medical Center details"
		Const.waitForElementToBeDisapear( Booking.loader, 15 );
		Thread.sleep( 3000 );
		
		Booking.Select_State.click();
		Thread.sleep( 1000 );
		Const.selectFromDropdown( Booking.Select_State, State );

		Thread.sleep( 1000 );
		Booking.Select_City.click();
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_City, City );

		Thread.sleep( 1000 );
		Booking.Select_Provider.click();
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_Provider, Provider );
		
		Thread.sleep( 500 );
		Booking.Next_Btn.click();

		
		// Step - 5 "Verify the displaying 'Package Name' and 'Price' for selected Beneficiary and Provider"
		actual = Booking.Selected_Package_Tile_Name.getText() ;
		expected = Package ;
		
		Assert.assertEquals( actual, expected );
		
		if( Booked )
		{
			actual = Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 11 ) ;
			expected = Price_Actual ;

			Assert.assertEquals( actual, expected );
		}
		
		
		// Step - 6 "Filling required First and Second preferred dates for Appointment"
		Thread.sleep( 2000 );
		Const.waitForElementToBeClikable( Booking.Preferred_First_Date_Time, 3 );
		Booking.Preferred_First_Date_Time.click();
		
		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Dates, Date );

		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Time_Slots , Time );
		
		Thread.sleep( 2000 );
		Booking.Preferred_Second_Date_Time.click();
		
		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Dates, ""  + ( Integer.parseInt( Date ) + 3 ) );

		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Time_Slots , Time );

		
		// Step - 7 "Filling required Patient Contact details"
		Booking.Mobile_Number.clear();
		Booking.Mobile_Number.sendKeys( Mobile );
		
		Booking.Email_Id.clear();
		Booking.Email_Id.sendKeys( Email );
		
		
		// Step - 8 "Book Appointment for the selected details"
		if( Sponsored && Booked )
		{
			Booking.Book_Appointment_Btn.click();
			
			Const.waitForElementToBeDisapear( Booking.loader, 15 );
			Thread.sleep( 5000 );
			
			driver.switchTo().frame( 0 );
			
			Booking.Close_Razorpay.click();
			
			driver.switchTo().defaultContent();
		}

		
		// Step - 9 "User Logs out"
		Booking.Sign_Out.click();
		
		Const.waitForElementToBeClikable( Landing.Signin_Btn, 5 );
	}
	
}
