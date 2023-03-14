package application;

import java.time.LocalDate;

public class Availability {
    private LocalDate date;
    private int availableHours;

    public Availability(LocalDate date, int availableHours) {
        this.date = date;
        this.availableHours = availableHours;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAvailableHours() {
        return availableHours;
    }
}
