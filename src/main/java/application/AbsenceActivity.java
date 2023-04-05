package application;

import java.time.LocalDate;

public class AbsenceActivity extends Activity {

    public AbsenceActivity(String name, double expectedWorkingHours, LocalDate startTime, LocalDate endTime) {
        super(name, expectedWorkingHours, startTime, endTime);
    }
}