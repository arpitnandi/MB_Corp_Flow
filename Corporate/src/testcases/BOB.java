package testcases;


import java.awt.AWTException;
import java.util.*;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import corporate_pages.*;
import generic.*;


public class BOB extends Base_Class
{
	//Session Variables
	List<String> Tabs ;
	boolean flag ;
	
	//Test Variables
	String URL = "https:\\portal.medibuddy.in" ;
	String UN="BOBTest01" ;
	String PW="BOBTest01." ;
	String Beneficiary ;
	String Package;
	String Payment_Status;
	String Price_Payable;
	String Price_Actual = "3389.8" ;
	boolean Sponsored;
	boolean Booked;
	String State = "Karnataka" ;
	String City = "Bengaluru" ;
	String Provider = "Apollo Hospitals , Jayanagar" ;
	String Date = "28" ;
	String Time = "09:30" ;
	String Mobile = "9090909090" ;
	String Email = "test@BOB.co" ;
	List<WebElement> Options ;
	String actual;
	String expected;
			
			
	//Page Object
	Corp_User_Landing Landing ;
	Constants Const ;
	Corp_Customised_Home Home ;
	Corp_Wellness_Booking Booking ;
			

	// Test Scenario - "Test1 Revamped HC Booking flow by book a Sponsored package for BOB User"
	@Test
	public void Test1_Revamped_HC_Booking_flow_by_book_a_Sponsored_package_for_BOB_User() throws InterruptedException, AWTException
	{	
		//Setting test values
		Beneficiary = "testself";
		Package = "BOB Annual Health Check for Male below 30";
		Payment_Status = "Sponsored Package";
		Price_Payable = "0";
		Sponsored = true ;
		Booked = false ;
		
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver );
		Home = new Corp_Customised_Home( driver );
		Booking = new Corp_Wellness_Booking( driver );

		
		// Step - 1 "User Logs in to Corporate portal"		
		super.launchApp( URL );
		
		Landing.portalLogin( UN , PW );
		
		
		// Step - 2 "Proceeding to AHC Booking flow"
		Const.waitForElementToBeVisible( Home.sponsored_Health_check_Modal , 3 );
				
		Home.sponsored_Health_check_Book_Now_Btn.click();
		
		this.Tabs = new ArrayList<String>( driver.getWindowHandles() ); 
		
		driver.switchTo().window( Tabs.get(0).toString() );
		driver.close();
		
		driver.switchTo().window( Tabs.get(1).toString() );

		
		// Step - 3 "Filling required Appointment details"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		Assert.assertTrue( Booking.BOB_Logo.isDisplayed() );
		
		if( Const.selectFromDropdown( Booking.Select_Beneficiary, Beneficiary ) )
		{
			Const.waitForElementToBeDisapear( Booking.loader , 15 );
			
			Const.selectFromDropdown( Booking.Select_Package, Package );
		}


		// Step - 4 "Filling required Medical Center details"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		Const.selectFromDropdown( Booking.Select_State, State );
		
		Const.selectFromDropdown( Booking.Select_City, City );
		
		Const.selectFromDropdown( Booking.Select_Provider, Provider );

		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		
		// Step - 5 "Verify the displaying 'Sponsored' status and presence of 'View details' button for selected Beneficiary and Provider"
		if( Sponsored && ! Booked )
		{
			Const.waitForElementToBeDisapear( Booking.loader , 15 );
			
			actual = Booking.Selected_Package_Payment_Status.getText() ;
			
			expected =  Payment_Status;
			
			Assert.assertEquals( actual , expected );
		}
		Assert.assertTrue( Booking.View_details_Btn.isDisplayed() );
		
		
		// Step - 6 "Filling required First and Second preferred dates for Appointment"
		Booking.Preferred_First_Date_Time.click();

		Thread.sleep( 100 );
		
		Const.selectElementAmong( Booking.Dates , Date );
		
		Const.selectElementAmong( Booking.Time_Slots , Time );
		
		try {
			if( Booking.No_thanks_Btn.isDisplayed())
				Booking.No_thanks_Btn.click();
		}
		catch( Exception E )
		{}
		
		Booking.Preferred_Second_Date_Time.click();

		Thread.sleep( 100 );
		
		Const.selectElementAmong( Booking.Dates , "" + ( Integer.parseInt( Date ) + 3 ) );
		
		Const.selectElementAmong( Booking.Time_Slots , Time );

		try {
			if( Booking.No_thanks_Btn.isDisplayed())
				Booking.No_thanks_Btn.click();
		}
		catch( Exception E )
		{}
		
		
		// Step - 7 "Filling required Contact details"
		Booking.Mobile_Number.clear();
		Booking.Mobile_Number.sendKeys( Mobile );
		
		Booking.Email_Id.clear();
		Booking.Email_Id.sendKeys( Email );

		
		// Step - 8 "Adding Appointment and Verifying the Selected Appointment details"
		Booking.Add_Appointment_Btn.click();
		
		Assert.assertEquals( Booking.Cart_Details.get( 0 ).getText(), Beneficiary );
		Assert.assertEquals( Booking.Cart_Details.get( 1 ).getText(), Package );
		Assert.assertEquals( Booking.Cart_Details.get( 2 ).getText(), Provider );
		Assert.assertEquals( Booking.Cart_Details.get( 3 ).getText().substring( 3, 5 ), "" + Date );
		
		// Step - 9 "Verify the displaying invoice and Book Appointment if invoice details are correct"
		Booking.Proceed_to_booking_Btn.click();
		
		actual = Booking.Invoice_Detail.get( 1 ).getText();
		expected = Package;
		
		Assert.assertEquals( actual, expected );
		
		if( actual.equals( expected ) )
		{
			actual = Booking.Invoice_Detail.get( 2 ).getText();
			expected = Price_Payable ;
		
			Assert.assertEquals( actual, expected );
			
			if( actual.equals( expected ) )
			{
				actual = Booking.Invoice_Detail.get( 4 ).getText();
				
				if( actual.equals( expected ) && ! Booked )
				{
					System.out.println( "' " + Booking.Continue_with_booking_Btn.getText() + "' is clicked for : " + Package );
					Booked = true ;
					Booking.Cancel_booking_Btn.click();
					//Booking.Continue_with_booking_Btn.click();
					//Booking.dismissAlertPopup();
				}
				else
					Booking.Cancel_booking_Btn.click();
			}
		}
		if( Sponsored && Booked )
		{
			Const.waitForElementToBeDisapear( Booking.loader , 15 );
			Thread.sleep( 2000 );
			Const.dismissAlerts();
		}
		
		
		// Step - 10 "Verify that 'Actual price' is displaying for the booked sponsored AHC"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		if( Const.selectFromDropdown( Booking.Select_Beneficiary, Beneficiary ) )
		{
			Const.waitForElementToBeDisapear( Booking.loader , 15 );
			
			Const.selectFromDropdown( Booking.Select_Package, Package );
		}

		Const.selectFromDropdown( Booking.Select_State, State );
		
		Const.selectFromDropdown( Booking.Select_City, City );
		
		Const.selectFromDropdown( Booking.Select_Provider, Provider );
		
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
				
		actual = Booking.Selected_Package_Payable_Price.getText() ;
		
		if( Booked )
			expected = Price_Actual ;		
		else
			expected = Price_Payable ;

		Assert.assertEquals( actual, expected );
		
		
		// Step - 11 "User Logs out"
		if( Booked )
		{
			Booking.Sign_Out.click();
			
			Const.waitForElementToBeVisible( Landing.Signin_Btn , 3 );
		}
	}
	
	
	// Test Scenario - "Test2 Revamped HC Booking flow by book a Paid package for BOB User"
	@Test
	public void Test2_Revamped_HC_Booking_flow_by_book_a_Paid_package_for_BOB_User() throws InterruptedException, AWTException
	{	
		//Setting test values
		Beneficiary = "testsmom";
		Package = "BOB Annual Health Checkup for Female above 30";
		Payment_Status = "Price (Rs.) : ";
		Price_Payable = "4000";
		Booked = false ;
		
		Const = new Constants( driver ) ;
		Landing = new Corp_User_Landing( driver );
		Home = new Corp_Customised_Home( driver );
		Booking = new Corp_Wellness_Booking( driver );

		
		// Step - 1 "User Logs in to Corporate portal"		
		super.launchApp( URL );
		
		Landing.portalLogin( UN , PW );
			
			
		// Step - 2 "Proceeding to AHC Booking flow"
		Const.waitForElementToBeVisible( Home.sponsored_Health_check_Modal , 3 );
					
		Home.sponsored_Health_check_Book_Now_Btn.click();
			
		this.Tabs = new ArrayList<String>( driver.getWindowHandles() ); 
			
		driver.switchTo().window( Tabs.get(0).toString() );
		driver.close();
			
		driver.switchTo().window( Tabs.get(1).toString() );

			
		// Step - 3 "Filling required Appointment details"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		Assert.assertTrue( Booking.BOB_Logo.isDisplayed() );
		
		if( Const.selectFromDropdown( Booking.Select_Beneficiary, Beneficiary ) )
		{
			Const.waitForElementToBeDisapear( Booking.loader , 15 );
			
			Const.selectFromDropdown( Booking.Select_Package, Package );
		}

			
		// Step - 4 "Filling required Medical Center details"
		Const.selectFromDropdown( Booking.Select_State, State );
			
		Const.selectFromDropdown( Booking.Select_City, City );
			
		Const.selectFromDropdown( Booking.Select_Provider, Provider );

		Const.waitForElementToBeDisapear( Booking.loader , 15 );
			
			
		// Step - 5 "Verify the displaying 'Package Price' and presence of 'View details' button for selected Beneficiary and Provider"
		Const.waitForElementToBeDisapear( Booking.loader , 15 );
		
		actual = Booking.Selected_Package_Payable_Price.getText() ;
		
		expected = Price_Payable;
			
		Assert.assertEquals( actual , expected );
			
		Assert.assertTrue( Booking.View_details_Btn.isDisplayed() );
			
		
		// Step -6 "Filling required First and Second preferred for Appointment"
		Booking.Preferred_First_Date_Time.click();

		Thread.sleep( 100 );
			
		Const.selectElementAmong( Booking.Dates , Date );
			
		Const.selectElementAmong( Booking.Time_Slots , Time );
			
		Booking.Preferred_Second_Date_Time.click();

		try {
			if( Booking.No_thanks_Btn.isDisplayed())
				Booking.No_thanks_Btn.click();
		}
		catch( Exception E )
		{}
		
		Thread.sleep( 100 );
			
		Const.selectElementAmong( Booking.Dates , "" + ( Integer.parseInt( Date ) + 3 ) );
			
		Const.selectElementAmong( Booking.Time_Slots , Time );

		try {
			if( Booking.No_thanks_Btn.isDisplayed())
				Booking.No_thanks_Btn.click();
		}
		catch( Exception E )
		{}
		
		
		// Step - 7 "Filling required Patient Contact details"
		Booking.Mobile_Number.clear();
		Booking.Mobile_Number.sendKeys( Mobile );
			
		Booking.Email_Id.clear();
		Booking.Email_Id.sendKeys( Email );

			
		// Step - 8 "Adding Appointment and Verifying the Selected Appointment details"
		Booking.Add_Appointment_Btn.click();
			
		Assert.assertEquals( Booking.Cart_Details.get( 0 ).getText(), Beneficiary );
		Assert.assertEquals( Booking.Cart_Details.get( 1 ).getText(), Package );
		Assert.assertEquals( Booking.Cart_Details.get( 2 ).getText(), Provider );
		Assert.assertEquals( Booking.Cart_Details.get( 3 ).getText().substring( 3, 5 ), "" + Date );
			
		// Step - 9 "Verify the displaying invoice then proceed to book the selected Appointment"
		Booking.Proceed_to_booking_Btn.click();
		
		actual = Booking.Invoice_Detail.get( 1 ).getText();
		expected = Package;
		
		Assert.assertEquals( actual, expected );
		
		if( actual.equals( expected ) )
		{
			actual = Booking.Invoice_Detail.get( 2 ).getText();
			expected = Price_Payable ;
		
			Assert.assertEquals( actual, expected );
			
			if( actual.equals( expected ) )
			{
				actual = Booking.Invoice_Detail.get( 4 ).getText();
				
				if( actual.equals( expected ) )
				{
					Booking.Continue_with_booking_Btn.click();
					Booked = true ;
				}
				else
					Booking.Cancel_booking_Btn.click();
			}
		}
			
		// Step - 10 "Verify the Razorpay price if invoice is correct then proceed to pay
		if( Booked )
		{
			Const.waitForElementToBeDisapear( Booking.loader , 15 );
			Thread.sleep( 5000 );
			
			driver.switchTo().frame( 0 );
			
			actual = Booking.Razorpay_Amount.getText();
			expected = Price_Payable;
			
			Assert.assertEquals( actual, expected );
			
			if( actual.equals( expected ))
			{
				System.out.println( "AHC is being booked by ID : " + Booking.Payment_Request_Id.getText() );
				Booking.Close_Razorpay.click();
			}
		}
		
		
		// Step - 11 "User Logs out"
		Booking.Sign_Out.click();
		Const.waitForElementToBeVisible( Landing.Signin_Btn , 3 );
	}
}
