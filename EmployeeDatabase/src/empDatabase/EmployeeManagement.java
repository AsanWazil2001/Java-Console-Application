package empDatabase;
import java.util.*;
import java.util.Scanner;

class Employee {
    int id;
    String name;
    float salary;
    long contact_no;
    String email_id;

    public Employee(int id, String name, float salary, long contact_no, String email_id) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.contact_no = contact_no;
        this.email_id = email_id;
    }

    public String toString() {
        return "\nEmployee Details :" + "\nID: " + this.id + "\nName: " + this.name + "\nSalary: " +
                this.salary + "\nContact No: " + this.contact_no + "\nEmail-id: " + this.email_id;
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        int id;
        String name;
        float salary;
        long contact_no;
        String email_id;

        Scanner sc = new Scanner(System.in);
        Map<Integer, Employee> employeeMap = new HashMap<>();

        do {
            System.out.println("\n*********Welcome to the Employee Management System**********\n");
            System.out.println("1). Add Employee to the Database\n" +
                    "2). Search for Employee\n" +
                    "3). Edit Employee details\n" +
                    "4). Delete Employee Details\n" +
                    "5). Display all Employees working in this company\n" +
                    "6). EXIT\n");
            System.out.println("Enter your choice : ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nEnter the following details to ADD list:\n");
                    System.out.println("Enter ID :");
                    id = sc.nextInt();
                    System.out.println("Enter Name :");
                    name = sc.next();
                    System.out.println("Enter Salary :");
                    salary = sc.nextFloat();
                    System.out.println("Enter Contact No :");
                    contact_no = sc.nextLong();
                    System.out.println("Enter Email-ID :");
                    email_id = sc.next();
                    employeeMap.put(id, new Employee(id, name, salary, contact_no, email_id));
                    display(employeeMap);
                    break;

                case 2:
                    System.out.println("Enter the Employee ID to search :");
                    id = sc.nextInt();
                    if (employeeMap.containsKey(id)) {
                        Employee foundEmployee = employeeMap.get(id);
                        System.out.println(foundEmployee + "\n");
                    } else {
                        System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
                    }
                    break;


                case 3:
                    System.out.println("\nEnter the Employee ID to EDIT the details");
                    id = sc.nextInt();
                    if (employeeMap.containsKey(id)) {
                        Employee employeeToUpdate = employeeMap.get(id);

                        // Display the current details of the employee
                        System.out.println("Current Employee Details:");
                        System.out.println(employeeToUpdate);

                        // Allow editing and update employee data in the map
                        System.out.println("\nEdit employee details...");

                        // Update attributes as needed
                        System.out.println("Enter new Employee Name:");
                        String newName = sc.next();
                        employeeToUpdate.name = newName;

                        System.out.println("Enter new Employee Salary:");
                        float newSalary = sc.nextFloat();
                        employeeToUpdate.salary = newSalary;

                        System.out.println("Enter new Employee Contact No:");
                        long newContactNo = sc.nextLong();
                        employeeToUpdate.contact_no = newContactNo;

                        System.out.println("Enter new Employee Email-ID:");
                        String newEmail = sc.next();
                        employeeToUpdate.email_id = newEmail;

                        System.out.println("Employee details updated successfully:");
                        System.out.println(employeeToUpdate);
                    } else {
                        System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
                    }
                    break;


                case 4:
                    System.out.println("\nEnter Employee ID to DELETE from the Database :");
                    id = sc.nextInt();
                    Employee removedEmployee = employeeMap.remove(id);
                    if (removedEmployee != null) {
                        display(employeeMap);
                    } else {
                        System.out.println("\nEmployee Details are not available, Please enter a valid ID!!");
                    }
                    break;

                case 5:
                    display(employeeMap);
                    break;

                case 6:
                    System.out.println("\nYou have chosen EXIT !! Closing the tool.");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nEnter a correct choice from the List :");
                    break;
            }
        } while (true);
    }

    static void display(Map<Integer, Employee> employeeMap) {
        System.out.println("\n--------------Employee List---------------\n");
        System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s", "ID", "Name", "Salary", "Contact-No", "Email-Id"));
        for (Employee e : employeeMap.values()) {
            System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s", e.id, e.name, e.salary, e.contact_no, e.email_id));
        }
    }
}
