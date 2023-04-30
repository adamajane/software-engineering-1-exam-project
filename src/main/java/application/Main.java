package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

//import static application.Employee.employees;
import static application.Employee.findActivityByName;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Time Management App");
            System.out.println("1. Add Employee");
            System.out.println("2. Create Project");
            System.out.println("3. Create Activity");
            System.out.println("4. Assign Project Leader");
            System.out.println("5. Add Employee to Activity");
            System.out.println("6. Register Time");
            System.out.println("7. Get Time Consumption Report");
            System.out.println("8. Show All Employees");
            System.out.println("9. Check Daily Hours Registration");
            System.out.println("10. Update Activity Name");
            System.out.println("11. Update Project Name");
            System.out.println("12. Update Activity Dates");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline from previous input

            switch (choice) {
                case 1:
                    Employee.addEmployee();
                    break;
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before creating a project.");
                        break;
                    }

                    String projectName;

                    while (true) {
                        System.out.print("Enter Project Name: ");
                        projectName = scanner.nextLine();
                        if (projectName.isEmpty()) {
                            System.out.println("Project name cannot be empty.");
                            return;
                        } else {
                            System.out.print("Choose Project Type (INTERNAL/CUSTOMER): ");
                            String projectTypeInput = scanner.next().toUpperCase();
                            while (!projectTypeInput.equals("INTERNAL") && !projectTypeInput.equals("CUSTOMER")) {
                                System.out.print("Invalid input. Choose Project Type (INTERNAL/CUSTOMER): ");
                                projectTypeInput = scanner.next().toUpperCase();
                            }
                            ProjectLeader.createProject(projectName, projectTypeInput);
                        }
                        break;
                    }
                    break;
                case 3:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before creating an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before creating an activity.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectID = Integer.parseInt(scanner.nextLine());
                    Project project = Project.findProjectByID(projectID);

                    if (project != null) {
                        System.out.println("Enter the activity name:");
                        String activityNameCreate = scanner.nextLine();
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

                        Activity activity = new Activity(activityNameCreate, budgetedHours, startYear, startWeek, endYear, endWeek);
                        project.getActivities().add(activity);
                        System.out.println("Activity created.");
                        System.out.println();

                        // Prints all activities in project
                        System.out.println("Activities in project:");
                        for (Activity a : project.getActivities()) {
                            System.out.println(a.getActivityName());
                        }
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 4:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }


                    System.out.print("Enter the project ID: ");
                    int projectIDAssign = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter the Employee ID of the new project leader: ");
                    String employeeIDAssign = scanner.nextLine().toUpperCase();

                    ProjectLeader.assignProjectLeader(projectIDAssign, employeeIDAssign);
                    break;
                case 5:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    String employeeID = scanner.nextLine();

                    System.out.println("Enter the activity name:");
                    String activityNameAdd = scanner.nextLine();

                    Employee.addEmployeeToActivity(employeeID, activityNameAdd);
                    break;
                case 6: // TODO: Check this out. Jeg fik en InputMismatchException fejl her (Adam)
                    TimeRegistration.registerTimeForEmployee(scanner);
                    break;
                case 7:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        return;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        return;
                    }

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
                        Employee.updateActivityDate(activityName, newStartYear, newStartWeek, newEndYear, newEndWeek);
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