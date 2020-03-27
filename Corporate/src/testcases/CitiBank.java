package testcases;

import java.util.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import corporate_pages.*;
import generic.*;

public class CitiBank extends Base_Class
{
	//Test Variables
	String UN="gaurav.lillaney@medibuddy.in" ;
	String PW="Test1234" ;
	String Beneficiary = "dolly" ;
	List<String> Packages = new ArrayList<String>() ;
	List<String> Prices = new ArrayList<String>() ;
	float Total_Payable ;
	String State = "Maharashtra" ;
	String City = "Mumbai" ;
	String Provider = "Apollo Spectra Hospitals , Chembur" ;
	String Date = "30" ;
	String Time = "08:30 AM" ;
	String actual = "" ;
	String expected = "" ;
		
		
	//Page Objects
	Constants Const ;
	Corp_User_Landing Landing ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;
		
		
	//Test Case : "Test AHC Booking flow for 'CitiBank' user"
	@Test
	public void Test_AHC_Booking_flow_for_CitiBank_user() throws InterruptedException
	{
		//Initiating Page objects
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver );
		Home = new Corp_Customised_Home( driver );
		Booking = new Corp_Wellness_Booking( driver );
			
			
		// Step - 1 "Launch app and login with "Monsanto" test user credentials"
		super.launchApp("https:\\portal.medibuddy.in");
		Landing.portalLogin( UN , PW );
			
			
		// Step - 2 "Proceeding to book AHC flow"
		Home.Health_Check_Btn.click();
		Thread.sleep( 500 );
		
		
		
		// Step - 3 "Select the required beneficiary"
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		Const.selectFromDropdown( Booking.Select_Beneficiary, Beneficiary );
		
		
		// Step - 4 "Select the required location"
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		Const.selectFromDropdown( Booking.Select_Location, City + ", " + State );
		
		
		// Step - 5 "Select the required package"
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		Thread.sleep( 1000 );
		Booking.Select_Package.click();
		Thread.sleep( 100 );
		Const.scrollDown( 2 ); 
		for( WebElement P : Booking.Package_Checkboxes )
		{
			if( ! P.getText().isEmpty() )
			{
				String[] Package_Price = P.getText().split( " - Rs." ) ;
				Packages.add( Package_Price[0] );
				Prices.add( Package_Price[1] );
				Total_Payable += Float.parseFloat( Package_Price[1] );
				P.click();
				Const.waitForElementToBeDisapear( Booking.loader , 5 );
			}
		}
		Assert.assertEquals( Float.parseFloat( Booking.Selected_Package_Payable_Price.getText() ), Total_Payable );
		
		
		// Step - 6 "Select the available 'State' and 'City'"
		Const.selectFromDropdown( Booking.Select_State, State );
		Thread.sleep( 500 );
		Const.selectFromDropdown( Booking.Select_City, City);
		Thread.sleep( 500 );
		
		
		// Step - 7 "Select the required provider clinic and verify the selected one"
		Const.selectFromDropdown( Booking.Select_Provider, Provider );
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		String[] actual_Address = Booking.Selected_Provider_Address.getText().split( ", " ) ;
		Assert.assertEquals( actual_Address[0] + " , " + actual_Address[actual_Address.length-1], Provider );
		Const.scrollDown( 2 );
		Thread.sleep( 500 );
		
		
		// Step - 7 "Select required preferred date for appointment"
		Booking.Preferred_First_Date_Time.click();
		Thread.sleep( 500 );
		Const.selectElementAmong( Booking.Dates, Date );
		Thread.sleep( 500 );
		Booking.Select_Time_Slot.click();
		Select S = new Select( Booking.Select_Time_Slot );
		for( WebElement T : S.getOptions() )
		{
			System.out.println(T.getText());
			if( T.getText().contains( Time.substring( 1 ) ) )
			{
				T.click();
				break;
			}
		}
		Booking.Add_Appointment_Btn.click();
		Const.scrollDown( 1 );
		
		
		// Step - 8 "Click on 'Add appointment to cart' and verify the cart details"
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		if( Booking.verifyCart( Packages, Provider, Date, Time ) )
			Booking.Proceed_to_booking_Btn.click();
		
		
		// Step - 9 "Click on 'Proceed to booking' and verify the invoice details"
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		if( Booking.verifyInvoice( Packages, Prices, Total_Payable, Provider, Date, Time ) )
			Booking.Continue_with_booking_Btn.click();
		
		
		// Step - 10 "Click on 'Continue with booking' and verify the Razorpay price"
		Const.waitForElementToBeDisapear( Booking.loader , 5 );
		Thread.sleep( 5000 );
		driver.switchTo().frame( 0 );
		actual = Booking.Razorpay_Amount.getText() ;
		expected = Booking.Invoice_Detail.get( Booking.Invoice_Detail.size() - 1 ).getText().trim() ;
		Assert.assertEquals( actual, expected.substring( 0, 2 ) + expected.substring( 2 ) );
		Booking.Close_Razorpay.click();
		
		
		// Step - 11 "User Sign out"
		Thread.sleep( 500 );
		Booking.Sign_Out.click();
	}
}
