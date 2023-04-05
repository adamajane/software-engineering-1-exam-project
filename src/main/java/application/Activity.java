package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private double expectedWorkingHours;
    private LocalDate startTime;
    private LocalDate endTime;
    private List<TimeRegistration> timeRegistrations;

    public Activity(String name, double expectedWorkingHours, LocalDate startTime, LocalDate endTime) {
        this.name = name;
        this.expectedWorkingHours = expectedWorkingHours;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeRegistrations = new ArrayList();
    }

    public void updateExpectedWorkingHours(double newExpectedWorkingHours) {
        this.expectedWorkingHours = newExpectedWorkingHours;
    }

    public void updateStartTime(LocalDate newStartTime) {
        this.startTime = newStartTime;
    }

    public void updateEndTime(LocalDate newEndTime) {
        this.endTime = newEndTime;
    }

    public void addTimeRegistration(TimeRegistration timeRegistration) {
        this.timeRegistrations.add(timeRegistration);
    }

    public String getName() {
        return this.name;
    }

    public List<TimeRegistration> getTimeRegistrations() {
        return this.timeRegistrations;
    }
}