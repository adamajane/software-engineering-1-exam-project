package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

// Author: Adam Ajane (contributors: Oliver Brandt + Alexander Vaaben)
public abstract class Employee {

    private boolean available;
    private String employeeID;
    private static ArrayList<Employee> employees = new ArrayList<Employee>();
    private static ArrayList<Activity> activities;
    private Map<LocalDate, Map<Activity, Double>> timeRegistrations;

    public Employee(String employeeID) {
        this.employeeID = employeeID;
        this.timeRegistrations = new HashMap<>();
        activities = new ArrayList<Activity>();
        this.available = true;
    }

    // Creates and adds an employee to the employees list (i.e., the system)
    public static void addEmployee(String employeeID, String isLeader) {
        Employee employee;

        if (isLeader.equalsIgnoreCase("Yes")) {
            employee = new ProjectLeader(employeeID);
        } else {
            employee = new Developer(employeeID);
        }

        System.out.println("Created new employee. Employee ID: " + employee.getEmployeeID());

        employees.add(employee);
    }

    // Checks if the employee ID is valid
    public static boolean isValidEmployeeID(String employeeID) {
        if (findEmployeeByID(employeeID) != null) {
            System.out.println("Employee with ID " + employeeID + " already exists. Try again.");
            return false;
        } else if (employeeID.length() > 4) {
            System.out.println("Employee ID must be no longer than 4 characters long. Try again.");
            return false;
        } else if (!employeeID.matches("[A-Z]+")) {
            System.out.println("Employee ID must only contain letters. Try again.");
            return false;
        } else {
            return true;
        }
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    // Finds an employee by their ID in the employees list
    public static Employee findEmployeeByID(String employeeId) {
        ArrayList<Employee> employees = Employee.getEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    // Adds an employee to an activity
    public static void addEmployeeToActivity(String employeeId, String activityName) {
        Employee employee = Employee.findEmployeeByID(employeeId);
        Activity activity = null;

        for (Project projects : ProjectLeader.getProjects()) {
            activity = projects.findActivityByName(activityName);
            if (activity != null) {
                break;
            }
        }

        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }

        if (!employee.isAvailable()) {
            System.out.println("Cannot add employee to the activity as they are currently unavailable.");
            return;
        }

        if (employee.getActivitiesInWeek(activity.getStartYear(), activity.getStartWeek()) < 20) {
            activity.assignEmployee(employee);
            System.out.println("Employee " + employeeId + " has been assigned to the activity " + activityName);
        } else {
            System.out.println("Employee " + employeeId + " cannot be assigned to more than 20 activities in a week.");
        }
    }


    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String newEmployeeID) {
        this.employeeID = newEmployeeID;
    }

    // An abstract method that is implemented by the child classes
    public abstract String getRole();

    // Creates an activity using the Activity constructor
    public static Activity createActivity(String activityName, int budgetedHours, int startYear, int startWeek, int endYear, int endWeek) {
        return new Activity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
    }

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    // Checks the daily hours registered for an employee
    public static void checkDailyHoursRegistration(String employeeID) {

        Employee employee = Employee.findEmployeeByID(employeeID);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        double dailyHours = employee.getRegisteredHours(currentDate);
        System.out.println("Total Registered Hours for Today: " + dailyHours);
    }

    // Registers time for an employee
    public boolean registerTime(Activity activity, double hours) {
        // If the employee is unavailable, they cannot register time for any activity
        if (!isAvailable()) {
            System.out.println("Error: You cannot register time for an activity while you are unavailable.");
            return false;
        }

        // If you are not assigned to the activity already, you cannot register time for it
        if (!activity.getAssignedEmployees().contains(this)) {
            System.out.println("Error: You cannot register time for an activity you are not assigned to.");
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        timeRegistrations.putIfAbsent(currentDate, new HashMap<>());
        Map<Activity, Double> dailyRegistrations = timeRegistrations.get(currentDate);
        dailyRegistrations.put(activity, dailyRegistrations.getOrDefault(activity, 0.0) + hours);

        // Create a TimeRegistration object and add it to the activity.
        TimeRegistration timeRegistration = new TimeRegistration(this, activity, currentDate, hours);
        activity.addTimeRegistration(timeRegistration);

        return true;
    }


    public double getRegisteredHours(LocalDate date) {
        if (timeRegistrations.containsKey(date)) {
            return timeRegistrations.get(date).values().stream().mapToDouble(Double::doubleValue).sum();
        }
        return 0.0;
    }

    public int getActivitiesInWeek(int year, int week) {
        int count = 0;
        for (Activity activity : activities) {
            if (activity.getStartYear() == year && activity.getStartWeek() == week) {
                count++;
            }
        }
        return count;
    }

    // Updates the name of an existing activity
    public static void updateActivityName(String currName, String newName) {

        // Can't update activity name if there are no activities
        if (ProjectLeader.getProjects().isEmpty()) {
            System.out.println("No activities have been added yet. Please add activities before updating an activity.");
            return;
        }

        String currentActivityName = currName;
        Activity activity = findActivityByName(currentActivityName);

        if (activity != null) {
            String newActivityName = newName;
            activity.setActivityName(newActivityName);
            System.out.println("Activity name updated successfully.");
        } else {
            System.out.println("Activity not found.");
        }
    }

    // Updates the dates of an existing activity
    public static void updateActivityDate(String activityName, int newStartYear, int newStartWeek, int newEndYear, int newEndWeek) {

        Activity activity = findActivityByName(activityName);
        if (activity != null) {
            activity.setStartYear(newStartYear);
            activity.setStartWeek(newStartWeek);
            activity.setEndYear(newEndYear);
            activity.setEndWeek(newEndWeek);
        } else {
            System.out.println("Activity not found.");
        }
    }

    // Finds an activity by name
    public static Activity findActivityByName(String activityName) {
        for (Project project : ProjectLeader.getProjects()) {
            Activity activity = project.findActivityByName(activityName);
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }


    public void markAsAvailable() {
        setAvailable(true);
    }

    public void markAsUnavailable() {
        setAvailable(false);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> assignedActivities = new ArrayList<>();
        for (Project project : ProjectLeader.getProjects()) {
            for (Activity activity : project.getActivities()) {
                if (activity.getAssignedEmployees().contains(this)) {
                    assignedActivities.add(activity);
                }
            }
        }
        return assignedActivities;
    }

    // Show all activities assigned to an employee
    public static void showActivitiesAssignedToEmployee(String employeeID) {
        Employee employee = Employee.findEmployeeByID(employeeID);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Activities assigned to employee " + employeeID + ":");
        for (Activity activity : employee.getActivities()) {
            System.out.println(activity.getActivityName());
        }
    }

    public boolean isAvailableAndNotOverloaded(int year, int week) {
        if (!isAvailable()) {
            return false;
        }

        return getActivitiesInWeek(year, week) < 20;
    }

}