package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private static int projectCounter = 0;
    private static int projectID;
    private String projectName;
    private Employee projectLeader;
    private List<Employee> employees;
    private List<Activity> activities;

    public Project(String projectName) {
        this.projectName = projectName;
        this.projectID = generateProjectNumber();
        this.employees = new ArrayList<>();
        this.activities = new ArrayList<>();
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

    public String getTimeConsumptionReport() {
        StringBuilder report = new StringBuilder();
        double totalTime = 0;
        double totalExpectedWorkingHours = 0;

        report.append("Time Consumption Report for Project: ").append(projectID).append(" - ").append(projectName).append("\n");
        report.append("-------------------------------------------------\n");

        for (Activity activity : activities) {
            double activityTime = 0;
            for (TimeRegistration timeRegistration : activity.getTimeRegistrations()) {
                activityTime += timeRegistration.getHours();
            }
            totalTime += activityTime;
            totalExpectedWorkingHours += activity.getExpectedWorkingHours(); // Add expected working hours of the activity

            report.append("Activity: ").append(activity.getActivityName()).append("\n");
            report.append("Time Spent: ").append(activityTime).append(" hours\n");
            report.append("-------------------------------------------------\n");
        }

        report.append("Total Time Spent on Project: ").append(totalTime).append(" hours\n");
        double remainingWorkingHours = totalExpectedWorkingHours - totalTime;
        report.append("Expected Remaining Working Hours on Project: ").append(remainingWorkingHours).append(" hours\n");
        return report.toString();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getProjectLeader() {
        return projectLeader;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public String getProjectName() {
        return projectName;
    }

}