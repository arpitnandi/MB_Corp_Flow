package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import corporate_pages.* ;
import generic.* ;


public class ABG extends Base_Class
{
	//Test Variables
	String URL = "https://portal.medibuddy.in" ;
	String UN1 = "advi@adityabirlacapital.com" ;
	String PW1 = "Mahs@123" ;
	String Beneficiary = "testself" ;
	String Package = "Segment 3: >46 years Female" ;
	String Price_Payable = "0" ;
	String State = "Karnataka" ;
	String City = "Bengaluru" ;
	String Provider = "Apollo Clinic , HSR Layout" ;
	String Date = "30" ;
	String Time = "09:00" ;
	
	String UN2 = "siri@adityabirlacapital.com" ;
	String PW2 = "Mahs@123" ;
	
	String actual ;
	String expected ;
	
	
	//Page Objects
	Constants Const ;
	Corp_User_Landing Landing ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;
	Infiniti_Common_Elements Infiniti ;
	
	
	//Test Scenario - "Testing AHC Sponsored Booking flow for 'Aditya Birla Group' users"
	@Test
	public void Testing_AHC_Sponsored_Booking_flow_for_Aditya_Birla_Group_users() throws InterruptedException
	{
		//Initializing Page Objects
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver ) ;
		Home = new Corp_Customised_Home( driver ) ;
		Booking = new Corp_Wellness_Booking( driver ) ;
		Infiniti = new Infiniti_Common_Elements( driver ) ;
		
		
		// Step - 1 "User launches the application and login"
		super.launchApp( URL );
		Landing.portalLogin( UN1, PW1 );
		
		
		// Step - 2 "Proceed to AHC booking"
		Home.Close_Btn.click();
		Const.mouseMoveToElement( Home.Heart_Beat_Symbol );
		Home.HC_Book_Now_Btn.click();
		
		
		// Step - 3 "Select Beneficiary and AHC Package"
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Thread.sleep( 100 );
		Const.selectFromDropdown( Booking.Select_Beneficiary, Beneficiary );
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Thread.sleep( 100 );
		Const.selectFromDropdown( Booking.Select_Package, Package );
		
		
		// Step - 4 "Select State, City and Provider"
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Thread.sleep( 100 );
		Const.selectFromDropdown( Booking.Select_State, State );
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_City, City );
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_Provider, Provider );
		
		
		// Step - 5 "Select First and Second Preferred appointment dates"
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Thread.sleep( 500 );
		Booking.Preferred_First_Date_Time.click();
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Dates, Date );
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Time_Slots, Time );
		
		Thread.sleep( 500 );
		Booking.Preferred_Second_Date_Time.click();
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Dates, "" + ( Integer.parseInt( Date ) + 1 ) );
		Thread.sleep( 100 );
		Const.selectElementAmong( Booking.Time_Slots, Time );
		
		
		// Step - 6 "Add to cart and proceed to pay"
		Const.scrollDown( 2 );
		Booking.Add_Appointment_Btn.click();
		Thread.sleep( 1000 );
		actual = Booking.Invoice_Detail.get( Booking.Invoice_Detail.size() - 1 ).getText().trim() ;
		expected = Price_Payable ;
		Assert.assertEquals( actual, expected );
		Booking.Proceed_to_booking_Btn.click();
		Thread.sleep( 1000 );
		

		// Step - 7 "Book Appointment for the above selected details"
		Booking.Continue_with_booking_Btn.click();
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Thread.sleep( 3000 );
		actual = Booking.readAlertMessage() ;
		expected = "Appointment request created successfully" ;
		Assert.assertTrue( actual.contains( expected ) );
		Booking.Dismiss_Alert_Btn.click();
		
		
		// Step - 8 "Verify the booked appointment inside Track Orders"
		driver.get( "https://portal.medibuddy.in/mbinfiniti.aspx" );
		Infiniti.User_Avatar.click();
		Infiniti.Track_Order.click();
		Infiniti.Order_Details.get( 0 ).click();
		
		Thread.sleep( 1000 );
		actual = Infiniti.Order_Name.get( 0 ).getText() ;
		expected = Package ;
		Assert.assertEquals(actual, expected);
		
		actual = Infiniti.Order_Name.get( 1 ).getText() ;
		expected = Provider ;
		Assert.assertEquals(actual, expected);
		
		
		// Step - 9 "User Log out"
		Booking.Sign_Out.click();
		Assert.assertTrue( Landing.Signin_Btn.isDisplayed() );
	}
}
