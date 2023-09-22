package solution;
import java.util.*;
public class TicketBooker {
	
	//63 berths(upper ,lower , middle)  + ( 18 RAC passengers) 
    //10 waiting list tickets ->21 L, 21 M, 21U , 18RAC, 10WL
	static int availableLowerBerth = 1;
	static int availableMiddleBerth = 1;
	static int availableUpperBerth = 1;
	static int availableRACTicket = 1;
	static int availableWL = 1;
	
	static Queue <Integer> wlList = new LinkedList<>(); //queue of WL passengers
	static Queue <Integer> racList= new LinkedList<>(); //queue of RAC passengers
	static List <Integer> bookedTicketList = new ArrayList<>(); //list of bookedticket passengers
	
	static List <Integer> lowerBerthPosition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
	static List <Integer> middleBerthPosition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
	static List <Integer> upperBerthPosition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7));
	static List <Integer> racPosition = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
	static List <Integer> WLPosition = new ArrayList<>(Arrays.asList(1,2,3));
	
	static Map<Integer , Passenger> passengers = new HashMap<>(); //map of passenger ids to passengers
	
	//book Ticket
	
	public void bookTicket(Passenger p, int berthInfo, String allotedBerth)
	{
		//assign the seat number and type of berth(L,U,M)
		p.number = berthInfo;
		p.alloted = allotedBerth;
		// add passenger to the map
		passengers.put(p.passengerId,p);
		//add passenger id to the list of booked tickets
		bookedTicketList.add(p.passengerId);
		System.out.println("----------------------Booked Successfully");
				
	}
	
	public void addToRAC(Passenger p, int racInfo, String allotedRAC)
	{
		//assign seat number and type(RAC)
		p.number = racInfo;
		p.alloted = allotedRAC;
		// add passenger to the map
		passengers.put(p.passengerId, p);
		//add passenger id to the queue of RAC tickets
		racList.add(p.passengerId);
		//decrease available RAC tickets by 1    
		availableRACTicket--;
		//remove the position that was alloted to the passenger
		racPosition.remove(0);
		
		System.out.println("----------------------Added to RAC Successfully");
	}
	
	public void addToWL(Passenger p, int WLInfo, String allotedWL)
	{
		//assign seat number and type(WL)
		p.number = WLInfo;
		p.alloted = allotedWL;
		// add passenger to the map
		passengers.put(p.passengerId, p);
		//add passenger id to the queue of WL tickets
		wlList.add(p.passengerId);
		//decrease available WL tickets by 1    
		availableWL--;
		//remove the position that was alloted to the passenger
		WLPosition.remove(0);
		
		System.out.println("----------------------Added to Waiting List Successfully");
		
	}
	
	//cancel Ticket
	public void cancelTicket(int passengerId)
	{
		//remove the passenger from the map
		Passenger p = passengers.get(passengerId);
		passengers.remove(Integer.valueOf(passengerId));
		
		//remove the booked ticket from the list
		bookedTicketList.remove(Integer.valueOf(passengerId));
		
		//take the booked position which is now free
		int positionBooked = p.number;
		
		System.out.println("-----------------cancelled Successfully");
		
		//add the free position to the correspoding type of list (either L,M,U)
		if(p.alloted.equals("L"))
		{
			availableLowerBerth++;
			lowerBerthPosition.add(positionBooked);
		}
		else if(p.alloted.equals("M"))
		{
			availableMiddleBerth++;
			middleBerthPosition.add(positionBooked);
		}
		else if(p.alloted.equals("U"))
		{
			availableUpperBerth++;
			upperBerthPosition.add(positionBooked);
		}
		
		//check if any RAC is there
		if(racList.size()>0)
		{
			//take the passenger from WL and add them to RAC , increase the free space in waiting list and 
            //increase available WL and decrease available RAC by 1
			Passenger passengerFromRAC = passengers.get(racList.poll());
			int positionRAC = passengerFromRAC.number;
			racPosition.add(positionRAC);
			racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
			availableRACTicket++;
			
			//check if any WL is there
			if(wlList.size() > 0)
			{
				//take the passenger from WL and add them to RAC , increase the free space in waiting list and 
                //increase available WL and decrease available RAC by 1
				Passenger passengerFromWL = passengers.get(wlList.poll()); //
				int positionWL = passengerFromWL.number;
				WLPosition.add(positionWL);
				wlList.remove(Integer.valueOf(passengerFromWL.passengerId));
				
				passengerFromWL.number = racPosition.get(0);
				passengerFromWL.alloted = "RAC";
				racPosition.remove(0);
				racList.add(passengerFromWL.passengerId);
				
				availableRACTicket--;
				availableWL++;
			}
			Main.bookTicket(passengerFromRAC);
			
		}
	}
	
	//print all available seats
	public void displayAvailable()
	{
		System.out.println("Avaliable Lower Berths: " +availableLowerBerth);
		System.out.println("Avaliable Middle Berths: " +availableMiddleBerth);
		System.out.println("Avaliable Upper Berths: " +availableUpperBerth);
		System.out.println("Avaliable RAC : " +availableRACTicket);
		System.out.println("Avaliable Waiting List : " +availableWL);
		System.out.println("---------------------------------------------");
	}
	
	 //print all occupied passengers from all types including WL
	public void displayPassengers()
	{
		if(passengers.size() == 0)
		{
			System.out.println("No Details of Passengers");
			return;
		}
		else {
			for(Passenger p:passengers.values())
			{
				System.out.println("Passenger ID : "+p.passengerId );
				System.out.println("Name : "+p.name );
				System.out.println("Age : "+p.age);
				System.out.println("Current Status : "+p.alloted );
				System.out.println("-------------------------------------------");
			}
		}
	}
	
}
