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
        System.out.println("Project created with ID: " + project.getProjectID() + " and name: " + projectName);
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

    public void assignProjectLeader() {

    }

    public void assignEmployees() {

    }

    public void checkEmployeeAvailability() {

    }

    public void checkEmployeeActivities() {

    }


}
