package application;

import java.time.LocalDate;

public class TimeRegistration {
    private Employee employee;
    private Activity activity;
    private LocalDate date;
    private double hours;

    public TimeRegistration(Employee employee, Activity activity, LocalDate date, int hours) {
        this.employee = employee;
        this.activity = activity;
        this.date = date;
        this.hours = hours;
    }

    public void updateHours(double newHours) {
        this.hours = newHours;
    }

    public void updateDate(LocalDate newDate) {
        this.date = newDate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getHours() {
        return this.hours;
    }
}