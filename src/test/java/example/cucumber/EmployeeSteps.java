package example.cucumber;

import application.Developer;
import application.Employee;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class EmployeeSteps {

    @Test // This has to be changed to a cucumber step
    public void testEmployeeConstructorWithValidID() {
        Employee employee = new Developer("0001");
        assertEquals("0001", employee.getEmployeeID());
    }

}
