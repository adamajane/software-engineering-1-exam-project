package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static application.Employee.findActivityByName;

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
            System.out.println("10. Update Activity Name");
            System.out.println("11. Update Project Name");
            System.out.println("12. Update Activity Dates");

            System.out.println("0. Exit");
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
                    ProjectLeader.assignProjectLeader(scanner);
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
                    System.out.print("Enter the current activity name: ");
                    String currName = scanner.nextLine();
                    System.out.print("Enter the new activity name: ");
                    String newName = scanner.nextLine();
                    Employee.updateActivityName(currName, newName);
                    break;
                case 11:
                    System.out.print("Enter the project ID: ");
                    int projID = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new project name: ");
                    String newProjName = scanner.nextLine();
                    ProjectLeader.updateProjectName(projID, newProjName);
                    break;
                case 12:
                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before updating an activity.");
                        break;
                    }

                    if (Project.getActivities().isEmpty()) {
                        System.out.println("No activities have been added yet. Please add activities before updating an activity.");
                        break;
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
                        scanner.nextLine();
                        Employee.updateActivityDate(newStartYear, newStartWeek, newEndYear, newEndWeek, activityName);
                        System.out.println("Activity dates updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
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