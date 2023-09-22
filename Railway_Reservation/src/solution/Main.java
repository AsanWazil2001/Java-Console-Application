package solution;
import java.util.*;

public class Main {
	
	//function for booking ticket
	public static void bookTicket(Passenger p)
	{
		TicketBooker booker = new TicketBooker();
		
		if(TicketBooker.availableWL == 0)
		{
			System.out.println("No Tickets Available");
			return;
		}
		
		//check if preferred berth is available
		if( (p.berthpref.equals("L") && TicketBooker.availableLowerBerth > 0) ||
			(p.berthpref.equals("M") && TicketBooker.availableMiddleBerth > 0) ||	
			(p.berthpref.equals("U") && TicketBooker.availableUpperBerth > 0) )
		{
			System.out.println("Preferred Berth Available");
			
			if(p.berthpref.equals("L"))
			{
				System.out.println("Lower Berth is Given");
				//call booking function in the TicketBooker class
				booker.bookTicket(p,(TicketBooker.lowerBerthPosition.get(0)),"L");
				//remove the booked position from available positions and also decrease available seats of that
                // particular type
				TicketBooker.lowerBerthPosition.remove(0);
				TicketBooker.availableLowerBerth--;
			}
			
			else if(p.berthpref.equals("M"))
			{
				System.out.println("Middle Berth is Given");
				//call booking function in the TicketBooker class
				booker.bookTicket(p,(TicketBooker.middleBerthPosition.get(0)),"M");
				//remove the booked position from available positions and also decrease available seats of that
                // particular type
				TicketBooker.middleBerthPosition.remove(0);
				TicketBooker.availableMiddleBerth--;
			}
			
			else if(p.berthpref.equals("U"))
			{
				System.out.println("Upper Berth is Given");
				//call booking function in the TicketBooker class
				booker.bookTicket(p,(TicketBooker.upperBerthPosition.get(0)),"U");
				//remove the booked position from available positions and also decrease available seats of that
                // particular type
				TicketBooker.upperBerthPosition.remove(0);
				TicketBooker.availableUpperBerth--;
			}
		}
		
		//preference not available -> book the available berth
		else if(TicketBooker.availableLowerBerth > 0)
		{
			System.out.println("Lower Berth is Given");
			
			booker.bookTicket(p,(TicketBooker.lowerBerthPosition.get(0)),"L");
			//remove the booked position from available positions and also decrease available seats of that
            // particular type
			TicketBooker.lowerBerthPosition.remove(0);
			TicketBooker.availableLowerBerth--;
		}
		else if(TicketBooker.availableMiddleBerth > 0)
		{
			System.out.println("Middle Berth is Given");
			//call booking function in the TicketBooker class
			booker.bookTicket(p,(TicketBooker.middleBerthPosition.get(0)),"M");
			//remove the booked position from available positions and also decrease available seats of that
            // particular type
			TicketBooker.middleBerthPosition.remove(0);
			TicketBooker.availableMiddleBerth--;
		}
		
		else if(TicketBooker.availableUpperBerth > 0)
		{
			System.out.println("Upper Berth is Given");
			//call booking function in the TicketBooker class
			booker.bookTicket(p,(TicketBooker.upperBerthPosition.get(0)),"U");
			//remove the booked position from available positions and also decrease available seats of that
            // particular type
			TicketBooker.upperBerthPosition.remove(0);
			TicketBooker.availableUpperBerth--;
		}
	
		// if no berth available go to RAC
		else if(TicketBooker.availableRACTicket > 0)
		{
			System.out.println("RAC Available");
			booker.addToRAC(p,(TicketBooker.racPosition.get(0)),"RAC");
		}
		
		// if no RAC available go to WL
		else if(TicketBooker.availableWL > 0)
		{
			System.out.println("Booked in Waiting List");
			booker.addToWL(p,(TicketBooker.WLPosition.get(0)),"WL");
		}
	}
	
	public static void cancelTicket(int id)
	{
		TicketBooker booker = new TicketBooker();
		
		//check if passenger id is valid
		if(!booker.passengers.containsKey(id))
		{
			System.out.println("Invalid Passenger");
		}
		else {
			booker.cancelTicket(id);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner o = new Scanner(System.in);
		boolean start = true;
		while(start)
		{
			System.out.println("1. Book Ticket \n 2. Cancel Ticket \n 3. Available Ticket \n 4. Booked Ticket \n 5, Exit");
			int ch = o.nextInt();
			switch(ch)
			{
			//booking
			case 1:
				System.out.println("Enter Passenger Name");
				String name = o.next();
				System.out.println("Enter the Age");
				int age = o.nextInt();
				System.out.println("Enter the Boarding From");
				String Boarding = o.next();
				System.out.println("Enter the Reserved Upto");
				String Reserving = o.next();
				System.out.println("Enter Birth Preference (L,M or U)");
				String berthpref = o.next();
				//create passenger object 
				Passenger p = new Passenger(name,age,Boarding,Reserving,berthpref);
				//booking
				bookTicket(p);
				break;
				
			//canceling
			case 2:
				//get passenger id to cancel
				System.out.println("Enter the Passenger Id to Cancel");
				int id = o.nextInt();
				cancelTicket(id);
				break;
				
			//Available tickets
			case 3:
				TicketBooker booker = new TicketBooker();
				booker.displayAvailable();
				break;
				
			//booked tickets
			case 4:
				TicketBooker bookers = new TicketBooker();
				bookers.displayPassengers();
				break;
				
			//Exit
			case 5:
				start = false;
				break;
				
			default:
				break;
			}
		}

	}

}
