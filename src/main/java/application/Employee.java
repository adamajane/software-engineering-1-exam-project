package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public abstract class Employee {
    private String employeeID;
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Activity> activities;
    private Map<LocalDate, Map<Activity, Double>> timeRegistrations;

    public Employee(String employeeID) {
        this.employeeID = employeeID;
        this.timeRegistrations = new HashMap<>();
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

        employees.add(employee);
    }

    public static void printEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet.");
            return;
        }
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getEmployeeID() + ", Role: " + employee.getRole());
        }
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public abstract String getRole();

    public Activity createActivity(String activityName, int budgetedHours, int startYear, int startWeek, int endYear, int endWeek) {
        return new Activity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void changeRegisteredData(Activity activity, int time) {

    }


    public void registerTime(Activity activity, double hours) {
        LocalDate currentDate = LocalDate.now();
        timeRegistrations.putIfAbsent(currentDate, new HashMap<>());
        Map<Activity, Double> dailyRegistrations = timeRegistrations.get(currentDate);
        dailyRegistrations.put(activity, dailyRegistrations.getOrDefault(activity, 0.0) + hours);

        // Create a TimeRegistration object and add it to the activity.
        TimeRegistration timeRegistration = new TimeRegistration(this, activity, currentDate, hours);
        activity.addTimeRegistration(timeRegistration);
    }

    public double getRegisteredHours(LocalDate date) {
        if (timeRegistrations.containsKey(date)) {
            return timeRegistrations.get(date).values().stream().mapToDouble(Double::doubleValue).sum();
        }
        return 0.0;
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

