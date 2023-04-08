package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectLeader extends Employee {

    // List of all projects
    private static ArrayList<Project> projects = new ArrayList<Project>();

    public ProjectLeader(String employeeId) {
        super(employeeId);
    }

    @Override
    public String getRole() {
        return "Project Leader";
    }

    public static void createProject(Scanner scanner) {
        if (Employee.getEmployees().isEmpty()) {
            System.out.println("No employees have been added yet. Please add employees before creating a project.");
            return;
        }
        System.out.print("Enter Project Name: ");
        String projectName = scanner.nextLine();

        System.out.print("Choose Project Type (INTERNAL/CUSTOMER): ");
        String projectTypeInput = scanner.next().toUpperCase();
        while (!projectTypeInput.equals("INTERNAL") && !projectTypeInput.equals("CUSTOMER")) {
            System.out.print("Invalid input. Choose Project Type (INTERNAL/CUSTOMER): ");
            projectTypeInput = scanner.next().toUpperCase();
        }
        ProjectType projectType = ProjectType.valueOf(projectTypeInput);

        Project project = new Project(projectName, projectType);
        projects.add(project);
        System.out.println("Project created with ID: " + Project.getProjectID() + ", name: " + projectName + ", and type: " + projectType);
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

    public static void assignProjectLeader(Scanner scanner) {

        if (Employee.getEmployees().isEmpty()) {
            System.out.println("No employees have been added yet. Please add employees before assigning them to an activity.");
            return;
        }

        if (ProjectLeader.getProjects().isEmpty()) {
            System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
            return;
        }

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

    public static void updateProjectName(Scanner scanner) {

        if (ProjectLeader.getProjects().isEmpty()) {
            System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
            return;
        }

        System.out.print("Enter the project ID: ");
        int projectID = scanner.nextInt();
        scanner.nextLine(); // Consume newline from previous input
        Project project = Project.findProjectByID(projectID);

        if (project != null) {
            System.out.print("Enter the new project name: ");
            String newProjectName = scanner.nextLine();
            project.setProjectName(newProjectName);
            System.out.println("Project name updated successfully.");
        } else {
            System.out.println("Project not found.");
        }
    }


}