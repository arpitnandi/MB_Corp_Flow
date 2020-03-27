package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import corporate_pages.*;
import generic.*;


public class Sopra_Banking extends Base_Class
{
	//Test Variables
	String URL = "http://portal.medibuddy.in" ;
	String UN = "Test05@sbs" ;
	String PW = "Medi@123" ;
	String Beneficiary = "testself" ;
	String Package = "HealthCheck_SopraSteria Male" ;
	String Sponsored_Status = "(Sponsored)" ;
	String Price_Payable = "0" ;
	String State = "Karnataka" ;
	String City = "Bengaluru" ;
	String Provider = "The Sagar Clinic" ;
	String Area = "Indiranagar" ;
	String Date = "30" ;
	String Time = "09:30" ;
	String actual ;
	String expected ;
	
	
	//Page Objects
	Constants Const ;
	Corp_User_Landing Landing ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;
	Infiniti_Common_Elements Infiniti ;
	
	
	// Test Scenario - "Testing the AHC Sponsored Booking flow for 'Sopra Baning' user'
	@Test
	public void Testing_the_AHC_Sponsored_Booking_flow_for_Sopra_Baning_user() throws InterruptedException
	{
		//Initializing Page Objects 
		Const = new Constants( driver );
		Landing = new Corp_User_Landing( driver ) ;
		Home = new Corp_Customised_Home( driver ) ;
		Booking = new Corp_Wellness_Booking( driver ) ;
		Infiniti = new Infiniti_Common_Elements( driver ) ;
		
		
		// Step - 1 "User launches the application and login"
		super.launchApp( URL );
		Landing.portalLogin( UN, PW );
		
		
		// Step - 2 "Proceed for AHC Booking flow"
		Home.Close_Btn.click();
		Home.Wellness_Tile.click();
		Const.mouseMoveToElement( Home.Heart_Beat_Symbol );
		Home.HC_Book_Now_Btn.click();
		
		
		// Step - 3 "Select Beneficiary, Package"
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Const.selectElementAmong( Booking.Beneficiary_Tiles, Beneficiary );
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		for( int i = 0 ; i < Booking.AHC_Names.size() ; i++ )
		{
			if( Booking.AHC_Names.get( i ).getText().equalsIgnoreCase( Package ))
			{
				Booking.AHC_Choes_Btns.get( i ).click();
				break;
			}
		}
		
		
		// Step - 4 "Verify selected package 'Price' and 'Sponsored status' then proceed"
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		actual = Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 11 , 12 );
		expected = Price_Payable ;
		Assert.assertEquals(actual, expected);
		
		actual = Booking.Selected_Package_Tile_Price_with_Status.getText().substring( 13 );
		expected = Sponsored_Status ;
		Assert.assertEquals(actual, expected);
		
		
		// Step - 5 "Select required State, City and Provider clinic for Appointment"
		Thread.sleep( 1000 );
		Const.selectFromDropdown( Booking.Select_State, State );
		Thread.sleep( 1000 );
		Const.selectFromDropdown( Booking.Select_City, City );
		Thread.sleep( 1000 );
		Const.selectFromDropdown( Booking.Select_Provider, Provider + " , " + Area);
		Thread.sleep( 100 );
		Booking.Next_Btn.click();
		
		
		// Step - 6 "Verify the selected State, City and Provider then proceed"
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		String[] Selected = Booking.Selected_Provider_State_City_inside_Tile.getText().split( " , " );
		String[] Location = Selected[1].split( " | ") ;
		String[] Area_State = Location[0].split( "\n" ) ;
		
		actual = Selected[0] + " , " + Area_State[0] ;
		expected = Provider + " , " + Area ;
		Assert.assertEquals(actual, expected);
		
		actual =  Area_State[1] + " | " + Location[2] ;
		expected = State + " | " + City ;
		Assert.assertEquals(actual, expected);
		
		
		// Step - 7 "Select required First and Second preferred Dates and Time slots for Appointment"
		Thread.sleep( 1000 );
		Booking.Preferred_First_Date_Time.click();
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Dates, Date );
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Time_Slots, Time);

		Thread.sleep( 500 );
		Booking.Preferred_Second_Date_Time.click();
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Dates, "" + Integer.parseInt( Date ) + 1 );
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Time_Slots, Time);
		

		// Step - 8 "Add to cart and proceed to pay"
		Const.scrollDown( 2 );
		Booking.Add_Appointment_Btn.click();
		Thread.sleep( 1000 );
		actual = Booking.Invoice_Detail.get( Booking.Invoice_Detail.size() - 1 ).getText().trim() ;
		expected = Price_Payable ;
		Assert.assertEquals( actual, expected );
		Booking.Proceed_to_booking_Btn.click();
		Thread.sleep( 1000 );
		

		// Step - 9 "Book Appointment for the above selected details"
		Booking.Continue_with_booking_Btn.click();
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Thread.sleep( 3000 );
		actual = Booking.readAlertMessage() ;
		expected = "Appointment request created successfully" ;
		Assert.assertTrue( actual.contains( expected ) );
		Booking.Dismiss_Alert_Btn.click();
		
		
		// Step - 10 "Verify the booked appointment inside Track Orders"
		driver.get( "https://portal.medibuddy.in/mbinfiniti.aspx" );
		Infiniti.User_Avatar.click();
		Infiniti.Track_Order.click();
		Infiniti.Order_Details.get( 0 ).click();
		
		actual = Infiniti.Order_Name.get( 0 ).getText() ;
		expected = Package ;
		Assert.assertEquals(actual, expected);
		
		actual = Infiniti.Order_Name.get( 1 ).getText() ;
		expected = Provider ;
		Assert.assertEquals(actual, expected);
		
		
		// Step - 11 "User Sign out"
		Infiniti.User_Avatar.click();
		Infiniti.Sig_out.click();
		Assert.assertTrue( Infiniti.Log_in.isDisplayed() );
	}
}
