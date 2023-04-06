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

    public static void createProject() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter project name: ");
        String projectName = input.nextLine();
        Project project = new Project(projectName);
        System.out.println(projectName + " has been created." + " with ID: " + Project.getProjectID());
        projects.add(project);

    }

    // Get overview of all projects
    public static void getProjects() {
        for (Project project : projects) {
            System.out.println("Project ID: " + Project.getProjectID() + ", Project Name: " + project.getProjectName());
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
