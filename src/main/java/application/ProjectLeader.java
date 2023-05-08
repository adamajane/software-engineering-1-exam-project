package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectLeader extends Employee {

    private static ArrayList<Project> projects = new ArrayList<Project>();

    public ProjectLeader(String employeeID) {
        super(employeeID);
    }

    // Overrides the getRole() method in Employee
    @Override
    public String getRole() {
        return "Project Leader";
    }

    // Creates and adds a project to the projects list
    public static void createProject(String projectName, String projectTypeInput) {

        ProjectType projectType = ProjectType.valueOf(projectTypeInput);

        Project project = new Project(projectName, projectType);
        projects.add(project);
        System.out.println("Project created with ID: " + project.getProjectID() + ", name: " + projectName + ", and type: " + projectType);
    }

    public static ArrayList<Project> getProjects() {
        return projects;
    }

    // Assigns a project leader to an exisiting project
    public static void assignProjectLeader(int projectID, String employeeID) {
        Project project = Project.findProjectByID(projectID);

        if (project != null) {
            Employee employee = Employee.findEmployeeByID(employeeID);

            if (employee.getRole().equals("Project Leader")) {
                project.setProjectLeader(employee);
                System.out.println("Project leader assigned successfully.");
            } else {
                System.out.println("The employee is not a project leader. Try again.");
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    // Updaets the name of an exisiting project
    public static void updateProjectName(int currProjID, String newProjName) {

        if (ProjectLeader.getProjects().isEmpty()) {
            System.out.println("No projects have been added yet. Please add projects before assigning employees to an activity.");
            return;
        }


        int projectID = currProjID;
        Project project = Project.findProjectByID(projectID);

        if (project != null) {

            String newProjectName = newProjName;
            project.setProjectName(newProjectName);
            System.out.println("Project name updated successfully.");
        } else {
            System.out.println("Project not found.");
        }
    }
}
