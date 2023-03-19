package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    public int ID;
    public static int nextID = 1;
    private Employee projectManager;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Activity> activities;

    // med projectmanager i constructor
    public Project(Employee projectManager, LocalDate startDate, LocalDate endDate) {
        ID = (this.nextID++) + 23000;
        this.projectManager = projectManager;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activities = new ArrayList<>();
    }

    // uden projectmanager constructor, så man kan lave project, hvor der ikke er udvalgt projectmanager endnu
    public Project(LocalDate startDate, LocalDate endDate) {
        ID = (this.nextID++) + 23000;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activities = new ArrayList<>();
    }


    public int getID() {
        return ID;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
