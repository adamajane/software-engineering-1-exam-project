package application;

import java.util.ArrayList;
import java.util.List;

public class Project {
    public int ID;
    public static int nextID = 1;
    private Employee projectManager;
    private int startYear;
    private int startWeek;
    private int endYear;
    private int endWeek;
    private List<Activity> activities;

    public Project(Employee projectManager, int startYear, int startWeek, int endYear, int endWeek) {
        ID = (this.nextID++) + 23000;
        this.projectManager = projectManager;
        this.startYear = startYear;
        this.startWeek = startWeek;
        this.endYear = endYear;
        this.endWeek = endWeek;
        this.activities = new ArrayList<>();
    }

    public Project(int startYear, int startWeek, int endYear, int endWeek) {
        ID = (this.nextID++) + 23000;
        this.startYear = startYear;
        this.startWeek = startWeek;
        this.endYear = endYear;
        this.endWeek = endWeek;
        this.activities = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public double getTotalBudgetedHours() {
        double totalBudgetedHours = 0.0;
        for (Activity activity : activities) {
            totalBudgetedHours += activity.getBudgetedHours();
        }
        return totalBudgetedHours;
    }

    public double getTotalActualHours() {
        double totalActualHours = 0.0;
        for (Activity activity : activities) {
            totalActualHours += activity.getTotalActualHours();
        }
        return totalActualHours;
    }

    public double getRemainingBudgetedHours() {
        return getTotalBudgetedHours() - getTotalActualHours();
    }
}
