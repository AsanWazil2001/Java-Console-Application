/*
The are 6 points(A,B,C,D,E,F) 15 KM apart 60 min travel between each, n taxis all taxis at A starting
100 rs for first 5 KM and then 10 for each of the further KMs, rate from pickup to drop only
pickup time example : 9 hrs, 15 hrs

When a customer books a Taxi, a free taxi at that point is allocated
-If no free taxi is available at that point, a free taxi at the nearest point is allocated.
-If two taxi’s are free at the same point, one with lower earning is allocated
-If no taxi is free at that time, booking is rejected


Input 1:
Customer ID: 1
Pickup Point: A
Drop Point: B
Pickup Time: 9

Output 1:
Taxi can be allotted.
Taxi-1 is allotted

*/


package taxi_book;
import java.util.*;

public class Main {
	
	public static void bookTaxi(int customerId, char pickup, char drop, int pickupTime, List<TaxiBook> freeTaxi)
	{
		// to find nearest dummy value initialize
		int min = 9999;
		
		//distance between pickup and drop
		int dis_between_point = 0;
		
		//this trip earning
		int earning = 0;
		
		//when taxi will be free next
		int nextFreeTime = 0;
		
		//where taxi is after trip is over
		char nextStop = 'Z';
		
		//booked taxi
		TaxiBook bookedTaxi = null;
		
		//all details of current trip as string
		String tripDetail = "";
		
		for(TaxiBook t : freeTaxi)
		{
			int dis_bt_CustomerAndTaxi = Math.abs((t.currentStop - '0') - (pickup - '0')) * 15;
			
			if(dis_bt_CustomerAndTaxi < min)
			{
				bookedTaxi = t;
				
				//distance between pickup and drop = (drop - pickup) * 15KM
				dis_between_point = Math.abs((drop - '0') - (pickup - '0'))*15;
				
				//trip earning = 100 + (distanceBetweenpickUpandDrop-5) * 10
				earning = (dis_between_point - 5)*10 +100;
				
				//drop time calculation
				int dropTime = pickupTime+dis_between_point/15;
				
				//when taxi will be free next
				nextFreeTime = dropTime;
				
				//taxi will be at drop point after trip
				nextStop = drop;
				
				// creating trip detail
				tripDetail = customerId+"		"+customerId+"		"+pickup+"		"+drop+"		"+pickupTime+"		"+dropTime+"		"+earning;
				min = dis_bt_CustomerAndTaxi;
			}
		}
				
		//setting corresponding details to allotted taxi
		bookedTaxi.setDetails(true,nextStop,nextFreeTime,bookedTaxi.totalEarnings+earning,tripDetail);
		//BOOKED SUCCESSFULLY
		System.out.println("Taxi No. : "+ bookedTaxi.id +" booked Successfully");
				
	}
	public static List<TaxiBook> createTaxi(int n)
	{
		List <TaxiBook> taxi = new ArrayList<TaxiBook>();
		//create taxi
				
		for(int i=1;i<=n;i++)
		{
			TaxiBook t = new TaxiBook();
			taxi.add(t);
		}
		return taxi;
	}
			
	public static List<TaxiBook> getFreeTaxi(List<TaxiBook> taxi, int pickupTime, char pickup)
	{
		List<TaxiBook> freeTaxi = new ArrayList<TaxiBook>();
		for(TaxiBook t : taxi)
		{
			if(t.freeTime <= pickupTime && (Math.abs((t.currentStop- '0') - (pickup - '0')) <= (pickupTime - t.freeTime)))
			{
				freeTaxi.add(t);
			}
		}
		return freeTaxi;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List <TaxiBook> taxi = createTaxi(4);
		
		Scanner o = new Scanner(System.in);
		int id = 1;
		while(true)
		{
			System.out.println("1. Book Taxi");
			System.out.println("2. Print Taxi Details");
			int ch = o.nextInt();
			
			switch(ch)
			{
			case 1:
				//get details from customers
				int customerId = id;
				System.out.println("Enter the Pickup Point");
				char pickup = o.next().charAt(0);
				System.out.println("Enter the Drop Point");
				char drop = o.next().charAt(0);
				System.out.println("Enter PickUp Time");
				int pickupTime = o.nextInt();
				
				//check if pickup and drop points are valid
				if(pickup < 'A' || pickup > 'F' || drop < 'A' || drop >'F')
				{
					 System.out.println("Enter the Valid pickup and drop points [ A, B, C, D, E, F]. Exitting...");
			          return;
				}
				// get all free taxis that can reach customer on or before pickup time
				List <TaxiBook> freeTaxi = getFreeTaxi(taxi,pickupTime,pickup);
				
				//no free taxi means we cannot allot, exit!
				if(freeTaxi.size() == 0)
				{
					System.out.println("No Taxi is available right now. Exitting...");
					return;
				}
				
				//sort taxis based on earnings 
				Collections.sort(freeTaxi,(a,b)->a.totalEarnings  - b.totalEarnings);
				//3,4,2 -> 2,3,4
				
				//get free Taxi nearest to us
				bookTaxi(id,pickup,drop,pickupTime,freeTaxi);
				
				id++;
				break;
				
			case 2:
				//two functions to print details
				for(TaxiBook t : taxi)
				{
					t.displayTaxiDetails();
				}
				for(TaxiBook t : taxi)
				{
					t.displayDetails();
				}
				break;
				
			default:
				return;	
			}
		}

	}

}
