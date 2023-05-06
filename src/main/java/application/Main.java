package application;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Scanner;

//import static application.Employee.employees;
import static application.Employee.findActivityByName;

public class Main {

    public static void main(String[] args) {
        startMenu();
    }

    private static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose login option:");
            System.out.println("1) Admin login");
            System.out.println("2) Project Leader login");
            System.out.println("3) Developer login");
            System.out.println("0) Exit application");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    projectLeaderLogin();
                    break;
                case 3:
                    developerLogin();
                    break;
                case 0:
                    System.out.println("Exiting the Time Management App...");
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }

    private static void adminLogin() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("(Admin login)");
            System.out.println("Choose option:");
            System.out.println("1) Projects");
            System.out.println("2) Activities");
            System.out.println("3) Employees");
            System.out.println("0) Back to login menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminProjectMenu();
                    break;
                case 2:
                    adminActivityMenu();
                    break;
                case 3:
                    adminEmployeeMenu();
                    break;
                case 0:
                    startMenu();
                    return;
            }
        }
    }

    private static void adminProjectMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Create project");
            System.out.println("2) Assign project leader to project");
            System.out.println("3) Edit project");
            System.out.println("4) Project overview");
            System.out.println("0) Back to admin menu");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
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
                case 2:
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
                case 3:
                    adminEditProjectSubMenu();
                    break;
                case 4:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before trying to get a project overview.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before trying to get a project overview.");
                        break;
                    }

                    System.out.print("Enter the project ID: ");
                    int projectIDReport = scanner.nextInt();
                    scanner.nextLine();

                    Project.getTimeConsumptionReport(projectIDReport);
                    break;
                case 0:
                    adminLogin();
                    return;
            }
        }
    }

    private static void adminActivityMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Create activity");
            System.out.println("2) Add employee to activity");
            System.out.println("3) Edit activity");
            System.out.println("4) Activity overview");
            System.out.println("0) Back admin menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before creating an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before creating an activity.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectID;
                    try {
                        projectID = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }
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
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    String employeeID = scanner.nextLine().toUpperCase();

                    if (Employee.findEmployeeByID(employeeID) == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    System.out.println("Enter the activity name:");
                    String activityNameAdd = scanner.nextLine();

                    Employee.addEmployeeToActivity(employeeID, activityNameAdd);
                    Employee.showActivitiesAssignedToEmployee(employeeID);
                    break;
                case 3:
                    adminEditActivitySubMenu();
                    break;
                case 4:
                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before viewing activity overview.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectIdOverview;
                    try {
                        projectIdOverview = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }
                    Project projectOverview = Project.findProjectByID(projectIdOverview);

                    if (projectOverview != null) {
                        System.out.println("Activity Overview:");
                        System.out.println("Project ID: " + projectIdOverview);
                        System.out.println("Project Name: " + projectOverview.getProjectName());
                        System.out.println("Activities:");

                        if (projectOverview.getActivities().isEmpty()) {
                            System.out.println("No activities found for this project.");
                        } else {
                            for (Activity activity : projectOverview.getActivities()) {
                                System.out.println("  Activity Name: " + activity.getActivityName());
                                System.out.println("  Budgeted Hours: " + activity.getBudgetedHours());
                                System.out.println("  Start Year: " + activity.getStartYear());
                                System.out.println("  Start Week: " + activity.getStartWeek());
                                System.out.println("  End Year: " + activity.getEndYear());
                                System.out.println("  End Week: " + activity.getEndWeek());
                                System.out.println();
                            }
                        }
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 0:
                    adminLogin();
                    return;
            }
        }
    }

    private static void adminEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Create employee");
            System.out.println("2) Employee availability");
            System.out.println("3) Employee activities");
            System.out.println("4) Edit employee data");
            System.out.println("0) Back to admin menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    String employeeIDCreate;
                    String isLeader;

                    while (true) {
                        System.out.println("Enter employee ID: ");
                        employeeIDCreate = scanner.nextLine().toUpperCase();

                        System.out.println("Is this employee a project leader? (Yes/No): ");
                        isLeader = scanner.nextLine();

                        if (Employee.isValidEmployeeID(employeeIDCreate)) {
                            break;
                        }
                    }

                    Employee.addEmployee(employeeIDCreate, isLeader);
                    break;
                case 2:
                    //System.out.println("Employee availability");
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add an employee before checking availability.");
                        break;
                    }

                    System.out.print("Enter Employee ID: ");
                    String employeeId = scanner.next().toUpperCase();
                    scanner.nextLine();

                    Employee employee = Employee.findEmployeeByID(employeeId);

                    if (employee == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    int currentYear = LocalDate.now().getYear();
                    int currentWeek = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

                    if (employee.isAvailableAndNotOverloaded(currentYear, currentWeek)) {
                        System.out.println(employee.getEmployeeID() + " is available.");
                    } else {
                        System.out.println(employee.getEmployeeID() + " is not available.");
                    }
                    break;
                case 3:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    String employeeIDShow = scanner.nextLine().toUpperCase();

                    Employee.showActivitiesAssignedToEmployee(employeeIDShow);
                    break;
                case 4:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before editing their data.");
                        break;
                    }

                    System.out.print("Enter current Employee ID: ");
                    String currentEmployeeID = scanner.nextLine().toUpperCase();

                    Employee employeeToUpdate = Employee.findEmployeeByID(currentEmployeeID);

                    if (employeeToUpdate == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    System.out.print("Enter new Employee ID: ");
                    String newEmployeeID = scanner.nextLine().toUpperCase();

                    if (Employee.findEmployeeByID(newEmployeeID) != null) {
                        System.out.println("The new Employee ID is already in use. Please choose a different ID.");
                        break;
                    }

                    employeeToUpdate.setEmployeeID(newEmployeeID);
                    System.out.println("Employee ID updated successfully.");
                case 0:
                    adminLogin();
                    return;
            }
        }
    }

    private static void projectLeaderLogin() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("(Project leader login)");
            System.out.println("Choose option:");
            System.out.println("1) Projects");
            System.out.println("2) Activities");
            System.out.println("3) Employees");
            System.out.println("4) Time Registration");
            System.out.println("0) Back login menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    pLProjectMenu();
                    break;
                case 2:
                    pLActivityMenu();
                    break;
                case 3:
                    pLEmployeeMenu();
                    break;
                case 4:
                    pLTimeRegistrationMenu();
                    break;
                case 0:
                    startMenu();
                    return;
            }
        }
    }

    private static void pLProjectMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Create project");
            System.out.println("2) Assign project leader");
            System.out.println("3) Edit project");
            System.out.println("4) Project overview");
            System.out.println("0) Back to project leader menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
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
                case 2:
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
                case 3:
                    plEditProjectSubMenu();
                    break;
                case 4:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before trying to get a project overview.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before trying to get a project overview.");
                        break;
                    }

                    System.out.print("Enter the project ID: ");
                    int projectIDReport = scanner.nextInt();
                    scanner.nextLine();

                    Project.getTimeConsumptionReport(projectIDReport);
                    break;
                case 0:
                    projectLeaderLogin();
                    return;
            }
        }
    }

    private static void pLActivityMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Create activity");
            System.out.println("2) Add employee to activity");
            System.out.println("3) Edit activity");
            System.out.println("4) Activity overview");
            System.out.println("0) Back to project leader menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before creating an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before creating an activity.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectID;
                    try {
                        projectID = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }
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
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    String employeeID = scanner.nextLine().toUpperCase();

                    if (Employee.findEmployeeByID(employeeID) == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    System.out.println("Enter the activity name:");
                    String activityNameAdd = scanner.nextLine();

                    Employee.addEmployeeToActivity(employeeID, activityNameAdd);
                    Employee.showActivitiesAssignedToEmployee(employeeID);
                    break;
                case 3:
                    pLEditActivitySubMenu();
                    break;
                case 4:
                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before viewing activity overview.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectIdOverview;
                    try {
                        projectIdOverview = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }
                    Project projectOverview = Project.findProjectByID(projectIdOverview);

                    if (projectOverview != null) {
                        System.out.println("Activity Overview:");
                        System.out.println("Project ID: " + projectIdOverview);
                        System.out.println("Project Name: " + projectOverview.getProjectName());
                        System.out.println("Activities:");

                        if (projectOverview.getActivities().isEmpty()) {
                            System.out.println("No activities found for this project.");
                        } else {
                            for (Activity activity : projectOverview.getActivities()) {
                                System.out.println("  Activity Name: " + activity.getActivityName());
                                System.out.println("  Budgeted Hours: " + activity.getBudgetedHours());
                                System.out.println("  Start Year: " + activity.getStartYear());
                                System.out.println("  Start Week: " + activity.getStartWeek());
                                System.out.println("  End Year: " + activity.getEndYear());
                                System.out.println("  End Week: " + activity.getEndWeek());
                                System.out.println();
                            }
                        }
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 0:
                    projectLeaderLogin();
                    return;
            }
        }
    }

    private static void pLEmployeeMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Employee availability");
            System.out.println("2) Employee activities");
            System.out.println("3) Edit employee data");
            System.out.println("0) Back to project leader menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //System.out.println("Employee availability");
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add an employee before checking availability.");
                        break;
                    }

                    System.out.print("Enter Employee ID: ");
                    String employeeId = scanner.next().toUpperCase();
                    scanner.nextLine();

                    Employee employee = Employee.findEmployeeByID(employeeId);

                    if (employee == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    int currentYear = LocalDate.now().getYear();
                    int currentWeek = LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

                    if (employee.isAvailableAndNotOverloaded(currentYear, currentWeek)) {
                        System.out.println(employee.getEmployeeID() + " is available.");
                    } else {
                        System.out.println(employee.getEmployeeID() + " is not available.");
                    }
                    break;
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    String employeeIDShow = scanner.nextLine().toUpperCase();

                    Employee.showActivitiesAssignedToEmployee(employeeIDShow);
                    break;
                case 3:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before editing their data.");
                        break;
                    }

                    System.out.print("Enter current Employee ID: ");
                    String currentEmployeeID = scanner.nextLine().toUpperCase();

                    Employee employeeToUpdate = Employee.findEmployeeByID(currentEmployeeID);

                    if (employeeToUpdate == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    System.out.print("Enter new Employee ID: ");
                    String newEmployeeID = scanner.nextLine().toUpperCase();

                    if (Employee.findEmployeeByID(newEmployeeID) != null) {
                        System.out.println("The new Employee ID is already in use. Please choose a different ID.");
                        break;
                    }

                    employeeToUpdate.setEmployeeID(newEmployeeID);
                    System.out.println("Employee ID updated successfully.");
                    break;
                case 0:
                    projectLeaderLogin();
                    return;
            }
        }
    }

    private static void pLTimeRegistrationMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Register work hours");
            System.out.println("2) Check registered work hours");
            System.out.println("0) Back project leader menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add an employee before registering time.");
                        break;
                    }

                    System.out.print("Enter Employee ID: ");
                    String employeeIDRegister = scanner.next().toUpperCase();
                    scanner.nextLine();

                    System.out.print("Enter Activity Name: ");
                    String activityNameRegister = scanner.next();

                    System.out.print("Enter Hours: ");
                    double hours = scanner.nextDouble();

                    TimeRegistration.registerTimeForEmployee(employeeIDRegister, activityNameRegister, hours);
                    break;
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.print("Enter the project ID: ");
                    int projectIDReport = scanner.nextInt();
                    scanner.nextLine();

                    Project.getTimeConsumptionReport(projectIDReport);
                    break;
                case 0:
                    projectLeaderLogin();
                    return;
            }
        }
    }

    private static void developerLogin() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("(Developer login)");
            System.out.println("Choose option:");
            System.out.println("1) Activities");
            System.out.println("2) Time Registration");
            System.out.println("0) Back to login menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    devActivityMenu();
                    break;
                case 2:
                    devTimeRegistrationMenu();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void devActivityMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Create activity");
            System.out.println("2) Join activity");
            System.out.println("3) Edit activity");
            System.out.println("4) Activity overview");
            System.out.println("5) Request colleague help");
            System.out.println("0) Back to developer menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before creating an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before creating an activity.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectID;
                    try {
                        projectID = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }
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
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    String employeeID = scanner.nextLine().toUpperCase();

                    if (Employee.findEmployeeByID(employeeID) == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    System.out.println("Enter the activity name:");
                    String activityNameAdd = scanner.nextLine();

                    Employee.addEmployeeToActivity(employeeID, activityNameAdd);
                    Employee.showActivitiesAssignedToEmployee(employeeID);
                    break;
                case 3:
                    devEditActivitySubMenu();
                    break;
                case 4:
                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before viewing activity overview.");
                        break;
                    }

                    System.out.println("Enter the project ID:");
                    int projectIdOverview;
                    try {
                        projectIdOverview = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }
                    Project projectOverview = Project.findProjectByID(projectIdOverview);

                    if (projectOverview != null) {
                        System.out.println("Activity Overview:");
                        System.out.println("Project ID: " + projectIdOverview);
                        System.out.println("Project Name: " + projectOverview.getProjectName());
                        System.out.println("Activities:");

                        if (projectOverview.getActivities().isEmpty()) {
                            System.out.println("No activities found for this project.");
                        } else {
                            for (Activity activity : projectOverview.getActivities()) {
                                System.out.println("  Activity Name: " + activity.getActivityName());
                                System.out.println("  Budgeted Hours: " + activity.getBudgetedHours());
                                System.out.println("  Start Year: " + activity.getStartYear());
                                System.out.println("  Start Week: " + activity.getStartWeek());
                                System.out.println("  End Year: " + activity.getEndYear());
                                System.out.println("  End Week: " + activity.getEndWeek());
                                System.out.println();
                            }
                        }
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 5:
                    //System.out.println("Request help");
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before requesting help.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before requesting help from a colleague.");
                        break;
                    }

                    System.out.println("Enter the employee ID:");
                    employeeID = scanner.nextLine().toUpperCase();

                    if (Employee.findEmployeeByID(employeeID) == null) {
                        System.out.println("Employee not found.");
                        break;
                    }

                    System.out.println("Enter the activity name:");
                    activityNameAdd = scanner.nextLine();

                    Employee.addEmployeeToActivity(employeeID, activityNameAdd);
                    Employee.showActivitiesAssignedToEmployee(employeeID);
                    break;
                case 0:
                    developerLogin();
                    return;
            }
        }
    }

    private static void devTimeRegistrationMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Register work hours");
            System.out.println("2) Check registered work hours");
            System.out.println("0) Back to developer menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add an employee before registering time.");
                        break;
                    }

                    System.out.print("Enter Employee ID: ");
                    String employeeIDRegister = scanner.next().toUpperCase();
                    scanner.nextLine();

                    System.out.print("Enter Activity Name: ");
                    String activityNameRegister = scanner.next();

                    System.out.print("Enter Hours: ");
                    double hours = scanner.nextDouble();

                    TimeRegistration.registerTimeForEmployee(employeeIDRegister, activityNameRegister, hours);
                    break;
                case 2:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
                        break;
                    }

                    if (ProjectLeader.getProjects().isEmpty()) {
                        System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
                        break;
                    }

                    System.out.print("Enter the project ID: ");
                    int projectIDReport = scanner.nextInt();
                    scanner.nextLine();

                    Project.getTimeConsumptionReport(projectIDReport);
                    break;
                case 0:
                    developerLogin();
                    return;
            }
        }
    }

    private static void adminEditProjectSubMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Edit project name");
            System.out.println("2) Edit project type");
            System.out.println("0) Back to project menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the project ID: ");
                    int projectID1;
                    try {
                        projectID1 = scanner.nextInt();
                        scanner.nextLine();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid project ID. Please try again.");
                        scanner.next();
                        break;
                    }

                    Project project = Project.findProjectByID(projectID1);
                    if (project == null) {
                        System.out.println("Project not found.");
                        break;
                    }

                    // Prompt the user to enter the new name for the project
                    System.out.print("Enter the new name for the project: ");
                    String newProjectName = scanner.nextLine();

                    // Update the project name using the updateProjectName() method in the ProjectLeader class
                    ProjectLeader.updateProjectName(projectID1, newProjectName);
                    break;
                case 2:
                    // Prompt the user to enter the project ID
                    System.out.print("Enter the project ID: ");
                    int projectID;
                    try {
                        projectID = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid project ID. Please try again.");
                        scanner.next(); // Consume the invalid input
                        break;
                    }

                    // Check if the project ID corresponds to an existing project
                    Project project2 = Project.findProjectByID(projectID);
                    if (project2 == null) {
                        System.out.println("Project not found.");
                        break;
                    }

                    // Prompt the user to enter the new type for the project
                    System.out.print("Enter the new type for the project: ");
                    String newProjectTypeStr = scanner.nextLine();

                    try {
                        ProjectType newProjectType = ProjectType.valueOf(newProjectTypeStr);

                        // Check if the new project type is the same as the current project type
                        if (project2.getProjectType() == newProjectType) {
                            System.out.println("The new project type is the same as the current project type. No changes made.");
                        } else {
                            // Update the project type using the updateProjectType() method in the Project class
                            project2.setProjectType(newProjectType);
                            System.out.println("Project type updated successfully.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid project type. Please try again.");
                    }
                    break;
                case 0:
                    adminProjectMenu();
                    return;
            }
        }
    }

    private static void plEditProjectSubMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Edit project name");
            System.out.println("2) Edit project type");
            System.out.println("0) Back to project menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the project ID: ");
                    int projectID1;
                    try {
                        projectID1 = scanner.nextInt();
                        scanner.nextLine();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid project ID. Please try again.");
                        scanner.next();
                        break;
                    }

                    Project project = Project.findProjectByID(projectID1);
                    if (project == null) {
                        System.out.println("Project not found.");
                        break;
                    }

                    // Prompt the user to enter the new name for the project
                    System.out.print("Enter the new name for the project: ");
                    String newProjectName = scanner.nextLine();

                    // Update the project name using the updateProjectName() method in the ProjectLeader class
                    ProjectLeader.updateProjectName(projectID1, newProjectName);
                    break;
                case 2:
                    // Prompt the user to enter the project ID
                    System.out.print("Enter the project ID: ");
                    int projectID;
                    try {
                        projectID = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid project ID. Please try again.");
                        scanner.next(); // Consume the invalid input
                        break;
                    }

                    // Check if the project ID corresponds to an existing project
                    Project project2 = Project.findProjectByID(projectID);
                    if (project2 == null) {
                        System.out.println("Project not found.");
                        break;
                    }

                    // Prompt the user to enter the new type for the project
                    System.out.print("Enter the new type for the project: ");
                    String newProjectTypeStr = scanner.nextLine();

                    try {
                        ProjectType newProjectType = ProjectType.valueOf(newProjectTypeStr);

                        // Check if the new project type is the same as the current project type
                        if (project2.getProjectType() == newProjectType) {
                            System.out.println("The new project type is the same as the current project type. No changes made.");
                        } else {
                            // Update the project type using the updateProjectType() method in the Project class
                            project2.setProjectType(newProjectType);
                            System.out.println("Project type updated successfully.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid project type. Please try again.");
                    }
                    break;
                case 0:
                    pLProjectMenu();
                    return;
            }
        }
    }

    private static void adminEditActivitySubMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Edit activity name");
            System.out.println("2) Edit activity budgeted hours");
            System.out.println("3) Edit activity start year");
            System.out.println("4) Edit activity end year");
            System.out.println("5) Edit activity start week");
            System.out.println("6) Edit activity end week");
            System.out.println("0) Back to activity menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Prompt the user to enter the activity name
                    System.out.print("Enter the current activity name: ");
                    String currentActivityName = scanner.nextLine();

                    // Find the activity by its name
                    Activity activity1 = null;
                    for (Project project : ProjectLeader.getProjects()) {
                        activity1 = project.findActivityByName(currentActivityName);
                        if (activity1 != null) {
                            break;
                        }
                    }

                    if (activity1 == null) {
                        System.out.println("Activity not found.");
                        break;
                    }

                    // Prompt the user to enter a new name for the activity
                    System.out.print("Enter the new name for the activity: ");
                    String newActivityName = scanner.nextLine();

                    // Update the activity name
                    activity1.setActivityName(newActivityName);
                    System.out.println("Activity name updated successfully.");
                    break;
                case 2:
                    // Prompt the user to enter the activity name
                    System.out.print("Enter the activity name: ");
                    String activityName = scanner.nextLine();

                    // Find the activity by its name
                    Activity activity2 = null;
                    for (Project project : ProjectLeader.getProjects()) {
                        activity2 = project.findActivityByName(activityName);
                        if (activity2 != null) {
                            break;
                        }
                    }

                    if (activity2 == null) {
                        System.out.println("Activity not found.");
                        break;
                    }

                    // Prompt the user to enter new budgeted hours for the activity
                    System.out.print("Enter new budgeted hours for the activity: ");
                    int newBudgetedHours = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Update the activity's budgeted hours
                    activity2.setBudgetedHours(newBudgetedHours);
                    System.out.println("Activity budgeted hours updated successfully.");
                    break;
                case 3:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeStartYear = scanner.nextLine();

                    Activity activity3 = findActivityByName(activityNameToChangeStartYear);
                    if (activity3 != null) {
                        System.out.println("Enter new start year for the activity:");
                        int newStartYear = scanner.nextInt();
                        scanner.nextLine();

                        activity3.setStartYear(newStartYear);
                        System.out.println("Activity start year updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeEndYear = scanner.nextLine();

                    Activity activity4 = findActivityByName(activityNameToChangeEndYear);
                    if (activity4 != null) {
                        System.out.println("Enter new end year for the activity:");
                        int newEndYear = scanner.nextInt();
                        scanner.nextLine();

                        activity4.setEndYear(newEndYear);
                        System.out.println("Activity end year updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeStartWeek = scanner.nextLine();

                    Activity activity5 = findActivityByName(activityNameToChangeStartWeek);
                    if (activity5 != null) {
                        System.out.println("Enter new start week for the activity:");
                        int newStartWeek = scanner.nextInt();
                        scanner.nextLine();

                        activity5.setStartWeek(newStartWeek);
                        System.out.println("Activity start week updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeEndWeek = scanner.nextLine();

                    Activity activity6 = findActivityByName(activityNameToChangeEndWeek);
                    if (activity6 != null) {
                        System.out.println("Enter new end week for the activity:");
                        int newEndWeek = scanner.nextInt();
                        scanner.nextLine();

                        activity6.setEndWeek(newEndWeek);
                        System.out.println("Activity end week updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 0:
                    adminActivityMenu();
                    return;

            }
        }
    }

    private static void pLEditActivitySubMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Edit activity name");
            System.out.println("2) Edit activity budgeted hours");
            System.out.println("3) Edit activity start year");
            System.out.println("4) Edit activity end year");
            System.out.println("5) Edit activity start week");
            System.out.println("6) Edit activity end week");
            System.out.println("0) Back to activity menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Prompt the user to enter the activity name
                    System.out.print("Enter the current activity name: ");
                    String currentActivityName = scanner.nextLine();

                    // Find the activity by its name
                    Activity activity1 = null;
                    for (Project project : ProjectLeader.getProjects()) {
                        activity1 = project.findActivityByName(currentActivityName);
                        if (activity1 != null) {
                            break;
                        }
                    }

                    if (activity1 == null) {
                        System.out.println("Activity not found.");
                        break;
                    }

                    // Prompt the user to enter a new name for the activity
                    System.out.print("Enter the new name for the activity: ");
                    String newActivityName = scanner.nextLine();

                    // Update the activity name
                    activity1.setActivityName(newActivityName);
                    System.out.println("Activity name updated successfully.");
                    break;
                case 2:
                    // Prompt the user to enter the activity name
                    System.out.print("Enter the activity name: ");
                    String activityName = scanner.nextLine();

                    // Find the activity by its name
                    Activity activity2 = null;
                    for (Project project : ProjectLeader.getProjects()) {
                        activity2 = project.findActivityByName(activityName);
                        if (activity2 != null) {
                            break;
                        }
                    }

                    if (activity2 == null) {
                        System.out.println("Activity not found.");
                        break;
                    }

                    // Prompt the user to enter new budgeted hours for the activity
                    System.out.print("Enter new budgeted hours for the activity: ");
                    int newBudgetedHours = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Update the activity's budgeted hours
                    activity2.setBudgetedHours(newBudgetedHours);
                    System.out.println("Activity budgeted hours updated successfully.");
                    break;
                case 3:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeStartYear = scanner.nextLine();

                    Activity activity3 = findActivityByName(activityNameToChangeStartYear);
                    if (activity3 != null) {
                        System.out.println("Enter new start year for the activity:");
                        int newStartYear = scanner.nextInt();
                        scanner.nextLine();

                        activity3.setStartYear(newStartYear);
                        System.out.println("Activity start year updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeEndYear = scanner.nextLine();

                    Activity activity4 = findActivityByName(activityNameToChangeEndYear);
                    if (activity4 != null) {
                        System.out.println("Enter new end year for the activity:");
                        int newEndYear = scanner.nextInt();
                        scanner.nextLine();

                        activity4.setEndYear(newEndYear);
                        System.out.println("Activity end year updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeStartWeek = scanner.nextLine();

                    Activity activity5 = findActivityByName(activityNameToChangeStartWeek);
                    if (activity5 != null) {
                        System.out.println("Enter new start week for the activity:");
                        int newStartWeek = scanner.nextInt();
                        scanner.nextLine();

                        activity5.setStartWeek(newStartWeek);
                        System.out.println("Activity start week updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeEndWeek = scanner.nextLine();

                    Activity activity6 = findActivityByName(activityNameToChangeEndWeek);
                    if (activity6 != null) {
                        System.out.println("Enter new end week for the activity:");
                        int newEndWeek = scanner.nextInt();
                        scanner.nextLine();

                        activity6.setEndWeek(newEndWeek);
                        System.out.println("Activity end week updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 0:
                    pLActivityMenu();
                    return;

            }
        }
    }

    private static void devEditActivitySubMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("Choose option:");
            System.out.println("1) Edit activity name");
            System.out.println("2) Edit activity budgeted hours");
            System.out.println("3) Edit activity start year");
            System.out.println("4) Edit activity end year");
            System.out.println("5) Edit activity start week");
            System.out.println("6) Edit activity end week");
            System.out.println("0) Back to activity menu");
            System.out.println("Enter choice:");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Prompt the user to enter the activity name
                    System.out.print("Enter the current activity name: ");
                    String currentActivityName = scanner.nextLine();

                    // Find the activity by its name
                    Activity activity1 = null;
                    for (Project project : ProjectLeader.getProjects()) {
                        activity1 = project.findActivityByName(currentActivityName);
                        if (activity1 != null) {
                            break;
                        }
                    }

                    if (activity1 == null) {
                        System.out.println("Activity not found.");
                        break;
                    }

                    // Prompt the user to enter a new name for the activity
                    System.out.print("Enter the new name for the activity: ");
                    String newActivityName = scanner.nextLine();

                    // Update the activity name
                    activity1.setActivityName(newActivityName);
                    System.out.println("Activity name updated successfully.");
                    break;
                case 2:
                    // Prompt the user to enter the activity name
                    System.out.print("Enter the activity name: ");
                    String activityName = scanner.nextLine();

                    // Find the activity by its name
                    Activity activity2 = null;
                    for (Project project : ProjectLeader.getProjects()) {
                        activity2 = project.findActivityByName(activityName);
                        if (activity2 != null) {
                            break;
                        }
                    }

                    if (activity2 == null) {
                        System.out.println("Activity not found.");
                        break;
                    }

                    // Prompt the user to enter new budgeted hours for the activity
                    System.out.print("Enter new budgeted hours for the activity: ");
                    int newBudgetedHours = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Update the activity's budgeted hours
                    activity2.setBudgetedHours(newBudgetedHours);
                    System.out.println("Activity budgeted hours updated successfully.");
                    break;
                case 3:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeStartYear = scanner.nextLine();

                    Activity activity3 = findActivityByName(activityNameToChangeStartYear);
                    if (activity3 != null) {
                        System.out.println("Enter new start year for the activity:");
                        int newStartYear = scanner.nextInt();
                        scanner.nextLine();

                        activity3.setStartYear(newStartYear);
                        System.out.println("Activity start year updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 4:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeEndYear = scanner.nextLine();

                    Activity activity4 = findActivityByName(activityNameToChangeEndYear);
                    if (activity4 != null) {
                        System.out.println("Enter new end year for the activity:");
                        int newEndYear = scanner.nextInt();
                        scanner.nextLine();

                        activity4.setEndYear(newEndYear);
                        System.out.println("Activity end year updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeStartWeek = scanner.nextLine();

                    Activity activity5 = findActivityByName(activityNameToChangeStartWeek);
                    if (activity5 != null) {
                        System.out.println("Enter new start week for the activity:");
                        int newStartWeek = scanner.nextInt();
                        scanner.nextLine();

                        activity5.setStartWeek(newStartWeek);
                        System.out.println("Activity start week updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter the activity name:");
                    String activityNameToChangeEndWeek = scanner.nextLine();

                    Activity activity6 = findActivityByName(activityNameToChangeEndWeek);
                    if (activity6 != null) {
                        System.out.println("Enter new end week for the activity:");
                        int newEndWeek = scanner.nextInt();
                        scanner.nextLine();

                        activity6.setEndWeek(newEndWeek);
                        System.out.println("Activity end week updated successfully.");
                    } else {
                        System.out.println("Activity not found.");
                    }
                    break;
                case 0:
                    devActivityMenu();
                    return;

            }
        }
    }
}