package solution;

public class Passenger {
	static int id = 28734;
	String name;
	int age;
	String Boarding;
	String Reserved;
	String pref;
	int passengerId;
	String alloted;
	int number;
	
	public Passenger(String name,int age, String Boarding, String Reserved, String pref){
		this.name = name;
		this.age = age;
		this.Boarding = Boarding;
		this.Reserved = Reserved;
		this.pref = pref;
		this.passengerId = id++;
		alloted = "";
		number = -1;
	}

}
