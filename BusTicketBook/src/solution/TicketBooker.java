package solution;

import java.util.*;
public class TicketBooker {
	static Scanner o = new Scanner(System.in);
	static int availableSleeper = 2;
	static int availableSemi_Sleeper = 2;
	static int totalTicket = 10;
	
	static List <Integer> semiSleeperList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
	static List <Integer> SleeperList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
	static List <Integer> bookedTicketList = new ArrayList<>();
	
	static Map <Integer,Passenger> passengers = new HashMap<>();
	
	public void bookTicket(Passenger p, int seatno , String pref)
	{
		p.number = seatno;
		p.alloted = pref;
		passengers.put(p.passengerId, p);
		bookedTicketList.add(p.passengerId);
		System.out.println("-----------------Successfully Booked.\nYour Passenger ID is :"+p.passengerId+"\n---------------------------");
	}
	public void cancelTicket(int passengerId) {
		Passenger p = passengers.get(passengerId);
		passengers.remove(Integer.valueOf(passengerId));
		bookedTicketList.remove(Integer.valueOf(passengerId));
		int availableSeat = p.number;
		System.out.println("---------------Cancelled Successfully");
		
		if(p.alloted.equals("Sleeper"))
		{
			availableSleeper++;
			SleeperList.add(availableSeat);
		}
		else if(p.alloted.equals("Semi-Sleeper"))
		{
			availableSemi_Sleeper++;
			semiSleeperList.add(availableSeat);
		}
	}
	
	public void availableTickets()
	{
		System.out.println("Avaliable Sleeper Berths: " +availableSleeper);
		System.out.println("Avaliable Semi-Sleeper Berths: " +availableSemi_Sleeper);
		
		System.out.println("---------------------------------------------");
	}
	public void displayTickets()
	{
		if(passengers.size() == 0)
		{
			System.out.println("No Passengers Details Available");
		}
		else {
			System.out.println("Enter Your Passenger ID");
			int i = o.nextInt();
			Passenger p =  passengers.get(i);
				System.out.println("Passenger ID : "+p.passengerId );
				System.out.println("Name : "+p.name );
				System.out.println("Age : "+p.age);
				System.out.println("Journey Details  :  "+p.Boarding+" to "+p.Reserved);
				System.out.println("Booked Status : "+p.alloted );
				System.out.println("-------------------------------------------");
		}
	}

}
