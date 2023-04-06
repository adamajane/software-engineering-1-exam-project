package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Time Management App");
            System.out.println("1. Add Employee");
            System.out.println("2. Create Project");
            System.out.println("3. Add Employee to Activity");
            System.out.println("4. Assign Project Manager");
            System.out.println("5. Create Activity for Project");
            System.out.println("6. Register Time");
            System.out.println("7. Get Time Consumption Report");
            System.out.println("8. Show All Employees");
            System.out.println("9. Check Daily Hours Registration");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-9): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline from previous input

            switch (choice) {
                case 1:
                    Employee.addEmployee();
                    break;
                case 2:
                    ProjectLeader.createProject(scanner);
                    break;
                case 3:
                    addEmployeeToActivity(scanner);
                    break;
                case 4:
                    assignProjectManager(scanner);
                    break;
                case 5:
                    createActivityForProject(scanner);
                    break;
                case 6:
                    registerTimeForEmployee(scanner);
                    break;
                case 7:
                    getTimeConsumptionReport(scanner);
                    break;
                case 8:
                    printAllEmployees();
                    break;
                case 9:
                    checkDailyHoursRegistration(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the Time Management App...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }



    private static void addEmployeeToActivity(Scanner scanner) {
        System.out.println("Enter the employee ID:");
        String employeeId = scanner.nextLine();
        Employee employee = findEmployeeById(employeeId);

        System.out.println("Enter the activity name:");
        String activityName = scanner.nextLine();
        Activity activity = null;
        for (Project p : ProjectLeader.getProjects()) {
            activity = p.findActivityByName(activityName);
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





    private static void assignProjectManager(Scanner scanner) {
        System.out.print("Enter the project ID: ");
        int projectID = scanner.nextInt();
        scanner.nextLine();
        Project project = findProjectByID(projectID);

        if (project != null) {
            System.out.print("Enter the Employee ID of the new project manager: ");
            String employeeId = scanner.nextLine().toUpperCase();
            Employee employee = findEmployeeById(employeeId);

            if (employee instanceof ProjectLeader) {
                project.assignProjectLeader(employee);
                System.out.println("Project manager assigned successfully.");
            } else {
                System.out.println("The employee is not a Project Leader. Operation failed.");
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    private static void createActivityForProject(Scanner scanner) {
        System.out.println("Enter the project ID:");
        int projectId = Integer.parseInt(scanner.nextLine());
        Project project = findProjectByID(projectId);

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


    private static void registerTimeForEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.next().toUpperCase();
        scanner.nextLine(); // Consume newline from previous input

        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter Activity Name: ");
        String activityName = scanner.next();

        Activity activity = null;
        for (Project project : ProjectLeader.getProjects()) {
            activity = project.findActivityByName(activityName);
            if (activity != null) {
                break;
            }
        }

        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }

        System.out.print("Enter Hours: ");
        double hours = scanner.nextDouble();

        employee.registerTime(activity, hours);
        System.out.println("Time registered successfully.");
    }

    private static void getTimeConsumptionReport(Scanner scanner) {
        System.out.print("Enter the project ID: ");
        int projectID = scanner.nextInt();
        scanner.nextLine(); // Consume newline from previous input

        scanner.nextLine();
        Project project = findProjectByID(projectID);

        if (project != null) {
            String report = project.getTimeConsumptionReport();
            System.out.println(report);
        } else {
            System.out.println("Project not found.");
        }
    }

    private static void checkDailyHoursRegistration(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.next().toUpperCase();
        scanner.nextLine(); // Consume newline from previous input

        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        double dailyHours = employee.getRegisteredHours(currentDate);
        System.out.println("Total Registered Hours for Today: " + dailyHours);
    }

    private static void printAllEmployees() {
        ArrayList<Employee> employees = Employee.getEmployees();
        for (Employee employee : employees) {
            System.out.println("Employee ID: " + employee.getEmployeeID() + ", Role: " + employee.getRole());
        }
    }


    private static Employee findEmployeeById(String employeeId) {
        ArrayList<Employee> employees = Employee.getEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeID().equalsIgnoreCase(employeeId)) {
                return employee;
            }
        }
        return null;
    }


    private static Project findProjectByID(int projectID) {
        for (Project project : ProjectLeader.getProjects()) {
            if (project.getProjectID() == projectID) {
                return project;
            }
        }
        return null;
    }
}