package testcases;


import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import corporate_pages.*;
import generic.*;


public class Mercedes_Benz extends Base_Class
{
	//Test Variables
	String UN = "Mercedes20@Mercedes" ;
	String PW = "Medi@123" ;
	String Self = "Self_20" ;
	String Package_1 = "MBRDI Executive Health Check (Additional Test Plus Female Test) - Female" ;
	String Package_2 = "MBRDI Executive Health Checks (With Additional Test) - Male/Female" ;
	String Package_3 = "MBRDI Executive Health Check" ;
	String[] Packages = { Package_1 , Package_2 , Package_3 } ; 
	String State = "Karnataka" ;
	String City = "Bengaluru" ;
	String Provider = "Apollo Spectra Hospitals , Koramangala" ;
	String Price_Payable_1 = "5929.5" ;
	String Price_Payable_2 = "5180.2" ;
	String Price_Payable_3 = "3422" ;
	String[] Prices = { Price_Payable_1 , Price_Payable_2 , Price_Payable_3 } ;
	String Total_Price_Payable = "14531.7" ;
	String Date = "28" ;
	String Time = "09:00" ;
	String actual ;
	String expected ;
	
	
	//Page objects
	Constants Const ;
	Corp_User_Landing Landing ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;
	
	
	// Test scenario - 1 "Testing AHC Paid Booking flow from 'portal.medibuddy' for 'Mercedese Benz' user" 
	@Test
	public void Testing_AHC_Paid_Booking_flow_for_Mercedese_Benz_user() throws InterruptedException
	{
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver );
		Home = new Corp_Customised_Home( driver );
		Booking = new Corp_Wellness_Booking( driver );
		
		
		// Step - 1 "Launch app and login" 
		super.launchApp( "https:\\portal.medibuddy.in" );
		
		Landing.portalLogin( UN, PW );
		
		
		// Step - 2 "Proceed to book AHC"
		Home.Close_Modal.click();
		
		Home.Wellness_Dropdown.click();
		
		Home.Anual_Health_Check_Option.click();
		
		//Select Beneficiary as 'Self'
		Const.waitForElementToBeDisapear( Booking.loader, 5 );
		Const.selectFromDropdown( Booking.Select_Beneficiary, Self );
		
		
		// Step - 3 "Verify all package prices for 'Self' beneficiary, fill required Appointment details and add to cart" 
		Select S = new Select( Booking.Select_Package ) ;
		for( int i = 1 ; i < S.getOptions().size() ; i++ )
		{
			Thread.sleep( 1000 );
			Const.selectFromDropdown( Booking.Select_Package, Packages[ i-1 ] );
			Const.waitForElementToBeDisapear( Booking.loader, 5 );
			
			Thread.sleep( 5000 );
			Const.selectFromDropdown( Booking.Select_State, State );

			Thread.sleep( 500 );
			Const.selectFromDropdown( Booking.Select_City, City );

			Thread.sleep( 500 );
			Const.selectFromDropdown( Booking.Select_Provider, Provider );
			Const.waitForElementToBeDisapear( Booking.loader, 5 );

			Thread.sleep( 500 );
			Const.scrollDown( 1 );
			
			if( S.getFirstSelectedOption().getText().trim().equals( Packages[ i-1 ] ) )
				Booking.Selected_Package_Payable_Price.getText().trim().equals( Prices[ i-1 ] );
			
			Booking.Preferred_First_Date_Time.click();
			Thread.sleep( 500 );
			Booking.selectDate( Date );
			Thread.sleep( 100 );
			Booking.selectTime( Time );
			
			Thread.sleep( 500 );
			Booking.Preferred_Second_Date_Time.click();
			Thread.sleep( 500 );
			Booking.selectDate( "" + ( Integer.parseInt( Date ) + 2 ) );
			Thread.sleep( 100 );
			Booking.selectTime( Time );
			
			Const.scrollDown( 2 );
			Thread.sleep( 1000 );
			
			Booking.Add_Appointment_Btn.click();
			Thread.sleep( 500 );

			if( i < S.getOptions().size() - 1 )
			{
				Const.scrollUp( 4 );
				Thread.sleep( 1000 );

				Const.selectFromDropdown( Booking.Select_Beneficiary, Self );
				Const.waitForElementToBeDisapear( Booking.loader, 5 );
			}
		}
		
		
		// Step -  4 "Verify the Cart then proceed"
		List<WebElement> Cart_Details = Booking.Cart_Details ;
		
		for( int i = 0 ; i < Cart_Details.size() / 5 ; i++ )
		{
			Assert.assertEquals( Cart_Details.get( 0 + ( i * 5 ) ).getText().trim(), Self );
			Assert.assertEquals( Cart_Details.get( 1 + ( i * 5 ) ).getText().trim(), Packages[ i ] );
			Assert.assertEquals( Cart_Details.get( 2 + ( i * 5 ) ).getText().trim(), Provider );
			Assert.assertEquals( Cart_Details.get( 3 + ( i * 5 ) ).getText().substring( 3, 5 ).trim(), Date );
			Assert.assertEquals( Cart_Details.get( 3 + ( i * 5 ) ).getText().substring( 11 ).trim(), Time );
		}

		Booking.Proceed_to_booking_Btn.click();
		
		
		// Step -  5 "Verify the Invoice then proceed"
		List<WebElement> Invoice_Details = Booking.Invoice_Detail ;
		
		for( int i = 0 ; i < ( Invoice_Details.size() - 2 ) / 3 ; i++ )
		{
			Assert.assertEquals( Invoice_Details.get( i * 3 + 1 ).getText().trim(), Packages[ i ] );
			Assert.assertEquals( Invoice_Details.get( i * 3 + 2 ).getText().trim(), Prices[ i ] );
		}
		
		Assert.assertEquals( Invoice_Details.get( 10 ).getText().trim(), Total_Price_Payable );

		Booking.Continue_with_booking_Btn.click();
		
		
		// Step - 6 "Proceed to pay for selected AHC package cart"	
		Const.waitForElementToBeVisible( Booking.Razorpay_Background , 15 );
		
		driver.switchTo().frame( 0 );
		Thread.sleep(1000 );
		
		actual = Booking.Razorpay_Amount.getText().substring( 2 ).trim() ;
		expected = Total_Price_Payable + "0" ;
		
		Assert.assertEquals( actual, expected.substring( 0, 2 ) + "," + expected.substring( 2 ) );
		
		Booking.Close_Razorpay.click();
		
		driver.switchTo().defaultContent();
		
		
		// Step - 7 "Signing out from the user"
		Booking.Sign_Out.click();
		Assert.assertTrue( Landing.Signin_Btn.isDisplayed() );
	}
}
