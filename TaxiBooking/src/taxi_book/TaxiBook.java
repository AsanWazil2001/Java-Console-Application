package taxi_book;
import java.util.*;
public class TaxiBook {
	static int taxiCount = 0; // taxi number
	int id;
	boolean booked; //taxi booked or not
	char currentStop;  //where taxi is now
	int freeTime; // when taxi becomes free
	int totalEarnings; // total earnings of taxi
	List <String> trips; // all details of all trips by this taxi
    
	public TaxiBook() {
		booked = false;
		currentStop = 'A'; //start point A
		freeTime = 5; //example 5 AM
		totalEarnings = 0;
		trips = new ArrayList<String>();
		taxiCount = taxiCount+1; // every time new taxi is created a new id will be assigned
		id = taxiCount;
	}
	
	public void setDetails(boolean booked, char currentStop, int freeTime, int totalEarnings, String tripDetail)
	{
		this.booked = booked;
		this.currentStop = currentStop;
		this.freeTime = freeTime;
		this.totalEarnings = totalEarnings;
		this.trips.add(tripDetail);
	}
	
	public void displayDetails()
	{
		System.out.println();
		System.out.println("Taxi No. = "+this.id +"	Total Earnings = "+this.totalEarnings);
		System.out.println("Taxi ID		Booking ID 	Customer ID 	From	  	To          	PickUpTime      DropTime	Amount");
		for(String trip : trips)
		{
			System.out.println(id+"		"+trip);
		}
		System.out.println("---------------------------------------------------------------------------------------------------------");
	}
	
	public void displayTaxiDetails() {
		System.out.println("Taxi No. = "+this.id+"	Total Earnings = "+this.totalEarnings+"	Current Stop = "+this.currentStop+"	Free Time = "+this.freeTime);
	}

}
