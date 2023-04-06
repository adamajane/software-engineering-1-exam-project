package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private static int projectCounter = 0;
    private int projectNumber;
    private String name;
    private Employee projectManager;
    private List<Employee> employees;
    private List<Activity> activities;

    public Project(String name) {
        this.name = name;
        this.projectNumber = generateProjectNumber();
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

    public void assignProjectManager(Employee projectManager) {
        this.projectManager = projectManager;
    }

    public Activity findActivityByName(String activityName) {
        for (Activity activity : activities) {
            if (activity.getActivityName().equals(activityName)) {
                return activity;
            }
        }
        return null;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public String getTimeConsumptionReport() {
        StringBuilder report = new StringBuilder();
        double totalTime = 0;
        double totalExpectedWorkingHours = 0;

        report.append("Time Consumption Report for Project: ").append(projectNumber).append(" - ").append(name).append("\n");
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


}