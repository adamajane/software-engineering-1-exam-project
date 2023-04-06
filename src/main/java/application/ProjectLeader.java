package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectLeader extends Employee {

    // list of projects
    private static ArrayList<Project> projects = new ArrayList<Project>();

    public ProjectLeader(String employeeId) {
        super(employeeId);
    }

    @Override
    public String getRole() {
        return "Project Leader";
    }

    public static void createProject(Scanner scanner) {
        System.out.print("Enter Project Name: ");
        String projectName = scanner.next();
        Project project = new Project(projectName);
        projects.add(project);
        System.out.println("Project created with ID: " + Project.getProjectID() + " and name: " + projectName);
    }

    // Get overview of all projects
    public static void printProjects() {
        for (Project project : projects) {
            System.out.println("Project ID: " + Project.getProjectID() + ", Project Name: " + project.getProjectName());
        }
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    public static void assignProjectManager(Scanner scanner) {
        System.out.print("Enter the project ID: ");
        int projectID = scanner.nextInt();
        scanner.nextLine();
        Project project = Project.findProjectByID(projectID);

        if (project != null) {
            System.out.print("Enter the Employee ID of the new project manager: ");
            String employeeId = scanner.nextLine().toUpperCase();
            Employee employee = Employee.findEmployeeById(employeeId);

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

    public void assignProjectLeader() {

    }

    public void assignEmployees() {

    }

    public void checkEmployeeAvailability() {

    }

    public void checkEmployeeActivities() {

    }


}
