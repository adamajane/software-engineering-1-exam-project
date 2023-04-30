package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Project {
    private ProjectType projectType;
    private static int projectCounter = 0;
    private static int projectID;
    private String projectName;
    private Employee projectLeader;
    private List<Employee> employees;
    private static List<Activity> activities;

    public Project(String projectName, ProjectType projectType) {
        this.projectName = projectName;
        this.projectID = generateProjectNumber();
        this.employees = new ArrayList<>();
        this.activities = new ArrayList<>();
        this.projectType = projectType;
    }

    private int generateProjectNumber() {
        projectCounter++;
        int year = LocalDate.now().getYear() % 100;
        return Integer.parseInt(String.format("%d%03d", year, projectCounter));
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void assignProjectLeader(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }

    public List<Activity> getProjectActivities() {
        return activities;

    }

    public Activity findActivityByName(String activityName) {
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    public static int getProjectID() {
        return projectID;
    }

    public String makeTimeConsumptionReport() {
        StringBuilder report = new StringBuilder();
        double totalTime = 0;
        double totalExpectedWorkingHours = 0;

        report.append("Time Consumption Report for Project: ").append(projectID).append(" - ").append(projectName).append("\n");
        report.append("-------------------------------------------------\n");

        for (Activity activity : activities) {
            double activityTime = 0;

            report.append("Activity: ").append(activity.getActivityName()).append("\n");
            report.append("Budgeted Hours: ").append(activity.getBudgetedHours()).append(" hours\n");
            report.append("Start Year: ").append(activity.getStartYear()).append("\n");
            report.append("Start Week: ").append(activity.getStartWeek()).append("\n");
            report.append("End Year: ").append(activity.getEndYear()).append("\n");
            report.append("End Week: ").append(activity.getEndWeek()).append("\n");
            report.append("Employee Time Spent: ");

            // Iterate over time registrations for each activity
            for (TimeRegistration timeRegistration : activity.getTimeRegistrations()) {
                double hours = timeRegistration.getHours();
                activityTime += hours;
                Employee employee = timeRegistration.getEmployee();

                // Append employee details and hours to the report
                report.append("[Employee ID: ").append(employee.getEmployeeID()).append(", Time: ").append(hours).append(" hours] ");
            }

            totalTime += activityTime;
            totalExpectedWorkingHours += activity.getExpectedWorkingHours(); // Add expected working hours of the activity

            report.append("\nTotal Time Spent on Activity: ").append(activityTime).append(" hours\n");
            report.append("-------------------------------------------------\n");
        }

        report.append("Total Time Spent on Project: ").append(totalTime).append(" hours\n");
        double remainingWorkingHours = totalExpectedWorkingHours - totalTime;
        report.append("Expected Remaining Working Hours on Project: ").append(remainingWorkingHours).append(" hours\n");
        return report.toString();
    }


    public static void getTimeConsumptionReport(Scanner scanner) {

        System.out.print("Enter the project ID: ");
        int projectID = scanner.nextInt();
        scanner.nextLine(); // Consume newline from previous input

        Project project = Project.findProjectByID(projectID);

        if (project != null) {
            String report = project.makeTimeConsumptionReport();
            System.out.println(report);
        } else {
            System.out.println("Project not found.");
        }
    }

    public static Project findProjectByID(int projectID) {
        ArrayList<Project> projects = ProjectLeader.getProjects();
        for (Project project : projects) {
            if (project.getProjectID() == projectID) {
                return project;
            }
        }
        return null;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public static List<Activity> getActivities() {
        return activities;
    }

    public void clearActivities() {
        activities.clear();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

}