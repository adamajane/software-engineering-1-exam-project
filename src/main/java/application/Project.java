package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Project {
    private static int projectCounter = 0;
    private int projectNumber;
    private String name;
    private Employee projectLeader;
    private List<Employee> employees;
    private List<Activity> activities;

    public Project(String name) {
        this.name = name;
        this.projectNumber = this.generateProjectNumber();
        this.employees = new ArrayList();
        this.activities = new ArrayList();
    }

    private int generateProjectNumber() {
        ++projectCounter;
        int year = LocalDate.now().getYear() % 100;
        return Integer.parseInt(String.format("%d%03d", year, projectCounter));
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void assignProjectManager(Employee projectLeader) {
        this.projectLeader = projectLeader;
    }

    public Activity findActivityByName(String activityName) {
        Iterator var2 = this.activities.iterator();

        Activity activity;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            activity = (Activity) var2.next();
        } while (!activity.getActivityName().equals(activityName));

        return activity;
    }

    public int getProjectNumber() {
        return this.projectNumber;
    }
}