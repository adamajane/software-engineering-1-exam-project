import java.time.LocalDate;

public class TimeEntry {
    private Employee employee;
    private Activity activity;
    private LocalDate date;
    private int hours;

    public TimeEntry(Employee employee, Activity activity, LocalDate date, int hours) {
        this.employee = employee;
        this.activity = activity;
        this.date = date;
        this.hours = hours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Activity getActivity() {
        return activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        // TODO Auto-generated method stub
        return hours;
    }
}