package solution;
import java.util.*;
public class Main {
	static Scanner o;
	public static void bookTicket(Passenger p)
	{
		TicketBooker booker = new TicketBooker();
		if(TicketBooker.totalTicket == 0)
		{
			System.out.println("NO MORE BOOKING");
			return;
		}
		if( (p.pref.equals("Sleeper") && TicketBooker.availableSleeper > 0) || (p.pref.equals("Semi-Sleeper") && TicketBooker.availableSemi_Sleeper > 0))
		{
			System.out.println("Selected Prefernce is available");
			if(p.pref.equals("Sleeper"))
			{
				System.out.println("Sleeper Berth is Given");
				booker.bookTicket(p,(TicketBooker.SleeperList.get(0)),"Sleeper");
				TicketBooker.SleeperList.remove(0);
				TicketBooker.availableSleeper--;
			}
			else if(p.pref.equals("Semi-Sleeper"))
			{
				System.out.println("Semi-Sleeper Berth is Given");
				booker.bookTicket(p,(TicketBooker.semiSleeperList.get(0)),"Semi-Sleeper");
				TicketBooker.semiSleeperList.remove(0);
				TicketBooker.availableSemi_Sleeper--;
			}
		}
		else {
			System.out.println("Prefferd Berth is not Available \n Want to continue to Book the available seats or to Exit Y/N");
			char c = o.next().charAt(0);
			if(c=='Y')
			{
				if(TicketBooker.availableSleeper > 0)
				{
					System.out.println("Sleeper Berth is Given");
					booker.bookTicket(p,(TicketBooker.SleeperList.get(0)),"Sleeper");
					TicketBooker.SleeperList.remove(0);
					TicketBooker.availableSleeper--;
				}
				else if(TicketBooker.availableSemi_Sleeper > 0)
				{
					System.out.println("Semi-Sleeper Berth is Given");
					booker.bookTicket(p,(TicketBooker.semiSleeperList.get(0)),"Semi-Sleeper");
					TicketBooker.semiSleeperList.remove(0);
					TicketBooker.availableSemi_Sleeper--;
				}
			}
			else {
				return;
			}
			
		}	
	}
	public static void cancelTicket(int id)
	{
		TicketBooker booker = new TicketBooker();
		if(!booker.passengers.containsKey(id))
		{
			System.out.println("Invalid Passenger ID");
		}
		else {
			booker.cancelTicket(id);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		o = new Scanner(System.in);
		boolean start = true;
		while(start)
		{
			System.out.println("1. Book Ticket \n2. Cancel Ticket \n3. Available Seats \n4. Print Tickets \n5. Exit");
			int ch = o.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Enter the Name");
				String name = o.next();
				System.out.println("Enter the Age");
				int age = o.nextInt();
				System.out.println("Enter the Boarding At");
				String Boarding = o.next();
				System.out.println("Enter the Reserved upto");
				String Reserved = o.next();
				System.out.println("Enter the Preference[Sleeper, Semi-Sleeper");
				String pref = o.next();
				
				Passenger p = new Passenger(name,age,Boarding,Reserved,pref);
				bookTicket(p);
				break;
				
			case 2:
				System.out.println("Enter the Ticket ID");
				int id = o.nextInt();
				cancelTicket(id);
				break;
			
			case 3:
				TicketBooker Booker = new TicketBooker();
				Booker.availableTickets();
				break;
			
			case 4:
				TicketBooker Bookers = new TicketBooker();
				Bookers.displayTickets();
				break;
				
			case 5:
				start = false;
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + ch);
			}
		}

	}

}

