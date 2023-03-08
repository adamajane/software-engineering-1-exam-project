import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private int ID;
    private List<Activity> activities;
    private List<Availability> availability;



    public Employee(String name, int ID) {
        this.name = name;
        this.ID = ID;
        activities = new ArrayList<>();
        availability = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public List<Availability> getAvailability() {
        return availability;
    }

    public void addAvailability(Availability availability) {
        this.availability.add(availability);
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        this.activities.remove(activity);
    }
}
