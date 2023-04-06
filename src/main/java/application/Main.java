package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Project> projects = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Time Management App");
            System.out.println("1. Add Employee");
            System.out.println("2. Create Project");
            System.out.println("3. Add Employee to Project");
            System.out.println("4. Assign Project Manager");
            System.out.println("5. Create Activity for Project");
            System.out.println("6. Register Time");
            System.out.println("7. Get Time Consumption Report");
            System.out.println("8. Show All Employees");
            System.out.println("9. Check Daily Hours Registration");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-9): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Employee.addEmployee();
                    break;
                case 2:
                    createProject(scanner);
                    break;
                case 3:
                    addEmployeeToProject(scanner);
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

    private static void createProject(Scanner scanner) {
        System.out.print("Enter Project Name: ");
        String projectName = scanner.next();
        Project project = new Project(projectName);
        projects.add(project);
        System.out.println("Project created with ID: " + project.getProjectID() + " and name: " + projectName);
    }

    private static void addEmployeeToProject(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.next().toUpperCase();

        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter Project Number: ");
        int projectNumber = scanner.nextInt();

        Project project = findProjectByID(projectNumber);
        if (project == null) {
            System.out.println("Project not found.");
            return;
        }

        project.addEmployee(employee);
        System.out.println("Employee added to the project successfully.");
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
        System.out.print("Enter Project Number: ");
        int projectNumber = scanner.nextInt();

        Project project = findProjectByID(projectNumber);
        if (project == null) {
            System.out.println("Project not found.");
            return;
        }
        System.out.print("Enter Activity Name: ");
        String activityName = scanner.next();

        System.out.print("Enter Budgeted Hours: ");
        int budgetedHours = scanner.nextInt();

        System.out.print("Enter Start Year: ");
        int startYear = scanner.nextInt();

        System.out.print("Enter Start Week: ");
        int startWeek = scanner.nextInt();

        System.out.print("Enter End Year: ");
        int endYear = scanner.nextInt();

        System.out.print("Enter End Week: ");
        int endWeek = scanner.nextInt();

        Activity activity = new Activity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
        project.getActivities().add(activity);
        System.out.println("Activity created successfully.");
    }

    private static void registerTimeForEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String employeeId = scanner.next().toUpperCase();

        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter Activity Name: ");
        String activityName = scanner.next();

        Activity activity = null;
        for (Project project : projects) {
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
        for (Project project : projects) {
            if (project.getProjectID() == projectID) {
                return project;
            }
        }
        return null;
    }
}