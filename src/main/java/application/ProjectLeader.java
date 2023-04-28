package application;

import java.util.ArrayList;
import java.util.Scanner;

public class ProjectLeader extends Employee {

    private static ArrayList<Project> projects = new ArrayList<Project>();

    public ProjectLeader(String employeeId) {
        super(employeeId);
    }

    @Override
    public String getRole() {
        return "Project Leader";
    }

    public static void createProject(String projectName, String projectTypeInput) {

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
            System.out.print("Enter the Employee ID of the new project leader: ");
            String employeeId = scanner.nextLine().toUpperCase();
            Employee employee = Employee.findEmployeeById(employeeId);

            if (employee instanceof ProjectLeader) {
                project.assignProjectLeader(employee);
                System.out.println("Project manager assigned successfully.");
            } else {
                System.out.println("The employee is not a project leader. Try again.");
            }
        } else {
            System.out.println("Project not found.");
        }
    }

    public void assignEmployees() {

    }

    public void checkEmployeeAvailability() {

    }

    public void checkEmployeeActivities() {

    }

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
