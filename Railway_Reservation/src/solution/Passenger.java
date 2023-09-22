package solution;

public class Passenger {
	static int id =1;//static variable to give id for every new passenger
	String name;
	int age;
	String Boarding;
	String Reserving;
	String berthpref;
	int passengerId; // id of passenger created automatically
	String alloted;//alloted type (L,U,M,RAC,WL)
	int number; //seat numbers
	public Passenger(String name, int age,String Boarding, String Reserving , String berthpref)
	{
		this.name = name;
		this.age = age;
		this.Boarding = Boarding;
		this.Reserving = Reserving;
		this.berthpref = berthpref;
		this.passengerId = id++;
		alloted = "";
		number = -1;
	}
	

}
