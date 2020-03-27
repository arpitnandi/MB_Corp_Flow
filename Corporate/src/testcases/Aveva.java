package testcases;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import corporate_pages.*;
import generic.*;

public class Aveva extends Base_Class
{
	//Test Variables
	String Corp = "Aveva" ;
	String UN1="TestMale40@avevainfo" ;
	String PW1="Medi@123" ;
	String UN2="TestFemale40@avevainfo" ;
	String PW2="Test@123" ;
	String Self = "Self" ;
	String Package = "AVEVA Annual Health Checkup" ;
	String Sponsored_Status = "(Sponsored)" ;
	String Price_Payable = "0" ;
	String Price_Actual = "1416" ;
	String Sucess_Message_Half1 = "Appointment request created successfully with order ID" ;
	String Sucess_Message_Half2 = ". You will receive a confirmation letter to the registered email Id, on confirmation from the medical centre." ;
	String Order_ID ;
	String State = "Karnataka" ;
	String City = "Bengaluru" ;
	String Provider = "Aarthi Scans & Labs , Jayanagar" ;
	String Date = "30" ;
	String Time = "08:30 AM" ;
	String actual ;
	String expected ;
		
		
	//Page Objects
	Constants Const ;
	Corp_User_Landing Landing ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;
		
		
	//Test Case : "Test Revamped HC Booking flow for 'Aveva',by booking a Sponsored AHC"
	@Test
	public void Test_Revamped_HC_Booking_flow_for_Aveva_by_booking_a_Sponsored_AHC() throws InterruptedException, AWTException
	{
		//Initiating Page objects
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver );
		Home = new Corp_Customised_Home( driver );
		Booking = new Corp_Wellness_Booking( driver );
			
			
		// Step - 1 "Launch app and login with "Aveva" test user credentials"
		super.launchApp("https:\\portal.medibuddy.in");
		Landing.portalLogin( UN1 , PW1 );
			
			
		// Step - 2 "Proceeding to book AHC flow"
		Home.Close_Btn.click();
		
		Home.Wellness_Tile.click();
		
		Const.mouseMoveToElement( Home.Heart_Beat_Symbol );
		
		Home.HC_Book_Now_Btn.click();
		
		
		// Step - 3 "Select 'Self' as Beneficiary and then select required AHC"
		Const.waitForElementToBeDisapear( Booking.loader, 15 );
		
		for( WebElement B : Booking.Beneficiary_Tiles )
		{
			if( B.getText().equalsIgnoreCase( Self ))
			{
				B.click();
				break;
			}
		}
		
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		
		for( int i = 0 ; i < Booking.AHC_Names.size() ;  )
		{
			if( Booking.AHC_Names.get( i ).getText().equalsIgnoreCase( Package ))
			{
				Booking.AHC_Choes_Btns.get( i ).click();
				break;
			}
		}
		
		
		// Step - 4 "Verify the price payable for the selected sponsored HC package"
		Const.waitForElementToBeDisapear( Booking.loader , 3 );
		
		Assert.assertEquals( Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 11, 12 ) , Price_Payable );
		Assert.assertEquals( Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 13 ) , Sponsored_Status );
		
		
		// Step - 5 "Select 'State', 'City' and 'Provider Clinic' for Appointment"
		Const.waitForElementToBeDisapear( Booking.loader , 2 );
		
		Select S = new Select( Booking.Select_State );
		S.selectByVisibleText( State );

		Const.waitForElementToBeDisapear( Booking.loader , 2 );
		
		S = new Select( Booking.Select_City );
		S.selectByVisibleText( City );

		Const.waitForElementToBeDisapear( Booking.loader , 2 );
		
		S = new Select( Booking.Select_Provider );
		S.selectByVisibleText( Provider );

		Const.waitForElementToBeDisapear( Booking.loader , 2 );
		
		Booking.Next_Btn.click();
		
		
		// Step - 6 "Select 'First' and 'Second' preferred dates for Appointment"
		Thread.sleep( 500 );
		Booking.Preferred_First_Date_Time.click();
		Thread.sleep( 500 );
		Booking.selectDate( Date );
		Thread.sleep( 500 );
		Booking.selectTime( Time );
		
		Thread.sleep( 500 );
		Booking.Preferred_Second_Date_Time.click();
		Thread.sleep( 500 );
		Booking.selectDate( "" + Integer.parseInt( Date ) + 2 );
		Thread.sleep( 500 );
		Booking.selectTime( Time );
		
		Booking.Book_Appointment_Btn.click();
		Thread.sleep( 2000 );
		Assert.assertTrue(  Booking.readAlertMessage().contains( Sucess_Message_Half1 ) );
		String[] MSG = Booking.readAlertMessage().split( " " ) ;
		Order_ID = MSG[ 7 ] ;
		Assert.assertTrue(  Booking.readAlertMessage().contains( Sucess_Message_Half2 ) );
		Thread.sleep( 2000 );
		
		
		// Step - 7 "Verify the real price of the Booked AHC after sponsored booking done"
		Booking.Dismiss_Alert_Btn.click();
		
		Const.scrollUp( 4 );
		
		for( WebElement B : Booking.Beneficiary_Tiles )
		{
			if( B.getText().equalsIgnoreCase( Self ))
				B.click();
		}
		
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		
		for( int i = 0 ; i < Booking.AHC_Names.size() ;  )
		{
			if( Booking.AHC_Names.get( i ).getText().equalsIgnoreCase( Package ))
			{
				Booking.AHC_Choes_Btns.get( i ).click();
				break;
			}
		}
		
		
		// Step - 8 "User logs out"
		Booking.Sign_Out.click();
		Assert.assertTrue( Landing.Signin_Btn.isDisplayed() );
	}
}
