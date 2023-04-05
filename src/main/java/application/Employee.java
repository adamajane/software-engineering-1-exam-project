package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public abstract class Employee {
    private String employeeID;
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Activity> activities;

    public Employee(String employeeID) {
        try {
            if (employeeID.length() > 4) {
                throw new IllegalArgumentException("Employee ID must be 4 characters long");
            }
            this.employeeID = employeeID;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        activities = new ArrayList<Activity>();
    }

    public static void addEmployee() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee ID: ");
        String employeeID = scanner.nextLine().toUpperCase();

        System.out.println("Is this employee a project leader? (Yes/No): ");
        String isLeader = scanner.nextLine();

        Employee employee;

        if (isLeader.equalsIgnoreCase("Yes")) {
            employee = new ProjectLeader(employeeID);
        } else {
            employee = new Developer(employeeID);
        }

        System.out.println("Employee ID: " + employee.getEmployeeID());

        if (employees == null) {
            employees = new ArrayList<Employee>();
        }
        employees.add(employee);
    }

    public static void getEmployees() {
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getEmployeeID() + ", Role: " + employee.getRole());
        }
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public abstract String getRole();

    public void registerTime(Activity activity, int time) {
        TimeRegistration timeRegistration = new TimeRegistration(this, activity, LocalDate.now(), time);
        activity.addTimeEntry(timeRegistration);
    }

    public void addActivity(Activity activity) {
    }

    //public ArrayList<Activity> getActivities() {
    //    return new ArrayList<Activity>(activities.values());

    //}
    public void changeRegisteredData(Activity activity, int time) {

    }


    public void registerFutureActivity(Activity activity, LocalDate starDate, LocalDate endDate) {
    }

    public void joinVacationActivity() {

    }

    public void joinSickActivity() {

    }

    public void registerWorkHours() {
    }

    public void checkRegisteredWorkHours() {
    }

    public void editWorkHours() {
    }


}

