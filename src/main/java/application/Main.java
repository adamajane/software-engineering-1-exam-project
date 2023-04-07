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
            System.out.println("5. Create Activity");
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
                    Employee.addEmployeeToActivity(scanner);
                    break;
                case 4:
                    ProjectLeader.assignProjectManager(scanner);
                    break;
                case 5:
                    Employee.createActivity(scanner);
                    break;
                case 6:
                    TimeRegistration.registerTimeForEmployee(scanner);
                    break;
                case 7:
                    Project.getTimeConsumptionReport(scanner);
                    break;
                case 8:
                    Employee.printEmployees();
                    break;
                case 9:
                    Employee.checkDailyHoursRegistration(scanner);
                    break;
                case 10:
                    Employee.updateActivityName(scanner);
                    break;
                case 11:
                    Employee.updateTimeRegistration(scanner);
                    break;
                case 12:
                    ProjectLeader.updateProjectName(scanner);
                    break;
                case 13:
                    Employee.updateActivityDate(scanner);
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

}