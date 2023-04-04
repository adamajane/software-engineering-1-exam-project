package application;

public class Developer extends Employee {

    public Developer(String employeeId) {
        super(employeeId);

    }

    // Hvad gør vores Edit metode i use case diagram????

    // Hvad skal denne metode?
    public void requestColleagueHelp() {
    }

    @Override
    public String getRole() {
        return "Developer";
    }
}