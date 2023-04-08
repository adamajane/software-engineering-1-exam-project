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

    public static Employee findEmployeeById(String employeeId) {
        ArrayList<Employee> employees = Employee.getEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public static void addEmployeeToActivity(Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
            return;
        }

        if (ProjectLeader.getProjects().isEmpty()) {
            System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
            return;
        }

        System.out.println("Enter the employee ID:");
        String employeeId = scanner.nextLine();
        Employee employee = Employee.findEmployeeById(employeeId);

        System.out.println("Enter the activity name:");
        String activityName = scanner.nextLine();
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

    public abstract String getRole();

    public static void createActivity(Scanner scanner) {

        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet. Please add employees before creating an activity.");
            return;
        }

        if (ProjectLeader.getProjects().isEmpty()) {
            System.out.println("No projects have been added yet. Please add projects before creating an activity.");
            return;
        }

        System.out.println("Enter the project ID:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = Project.findProjectByID(projectId);

        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        System.out.println("Enter the activity name:");
        String activityName = scanner.nextLine();
        System.out.println("Enter the budgeted hours:");
        int budgetedHours = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the start year:");
        int startYear = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the start week:");
        int startWeek = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the end year:");
        int endYear = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the end week:");
        int endWeek = Integer.parseInt(scanner.nextLine());

        Activity activity = new Activity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
        project.getActivities().add(activity); // Add the activity to the project's activity list
        System.out.println("Activity created.");
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void changeRegisteredData(Activity activity, int time) {

    }

    public static void checkDailyHoursRegistration(Scanner scanner) {

        if (employees.isEmpty()) {
            System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
            return;
        }

        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.next().toUpperCase();
        scanner.nextLine(); // Consume newline from previous input

        Employee employee = Employee.findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        double dailyHours = employee.getRegisteredHours(currentDate);
        System.out.println("Total Registered Hours for Today: " + dailyHours);
    }

    public void registerTime(Activity activity, double hours) {
        // if you are not assigned to the activity already, you cannot register time for it
        if (!activity.getAssignedEmployees().contains(this)) {
            System.out.println("Error: You cannot register time for an activity you are not assigned to.");
            return;
        }

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

    public int getActivitiesInWeek(int year, int week) {
        int count = 0;
        for (Activity activity : activities) {
            if (activity.getStartYear() == year && activity.getStartWeek() == week) {
                count++;
            }
        }
        return count;
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


    public static void updateProjectName(Scanner scanner) {
        // Implementation here
    }

    public static void updateActivityName(Scanner scanner) {

        // Can't update activity name if there are no activities
        if (Project.getActivities() == null) {
            System.out.println("No activities have been added yet. Please add activities before updating an activity.");
            return;
        }

        System.out.print("Enter the current activity name: ");
        String currentActivityName = scanner.nextLine();
        Activity activity = findActivityByName(currentActivityName);

        if (activity != null) {
            System.out.print("Enter the new activity name: ");
            String newActivityName = scanner.nextLine();
            activity.setActivityName(newActivityName);
            System.out.println("Activity name updated successfully.");
        } else {
            System.out.println("Activity not found.");
        }
    }


    public static void updateActivityDate(Scanner scanner) {

        if (Project.getActivities() == null) {
            System.out.println("No activities have been added yet. Please add activities before updating an activity.");
            return;
        }

        System.out.print("Enter the activity name: ");
        String activityName = scanner.nextLine();
        Activity activity = findActivityByName(activityName);

        if (activity != null) {
            System.out.print("Enter the new start year: ");
            int newStartYear = scanner.nextInt();

            System.out.print("Enter the new start week: ");
            int newStartWeek = scanner.nextInt();

            System.out.print("Enter the new end year: ");
            int newEndYear = scanner.nextInt();

            System.out.print("Enter the new end week: ");
            int newEndWeek = scanner.nextInt();

            activity.setStartYear(newStartYear);
            activity.setStartWeek(newStartWeek);
            activity.setEndYear(newEndYear);
            activity.setEndWeek(newEndWeek);
            System.out.println("Activity dates updated successfully.");
        } else {
            System.out.println("Activity not found.");
        }
    }

    public static Activity findActivityByName(String activityName) {
        for (Project project : ProjectLeader.getProjects()) {
            Activity activity = project.findActivityByName(activityName);
            if (activity != null) {
                return activity;
            }
        }
        return null;
    }


}

