package persistence;

import ski.model.Guest;
import ski.model.Pass;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Citation: code for this class has been obtained from JSonSerializationDemo
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

public class JsonTest {
    protected void checkGuest(String name, int age, Guest guest) {
        assertEquals(name, guest.getName());
        assertEquals(age, guest.getAge());
    }

    protected void checkPass(String passType, int passNum, boolean passExpired, Pass pass) {
        assertEquals(passType, pass.getPassType());
        assertEquals(passNum, pass.getPassNum());
        assertEquals(passExpired, pass.isPassExpired());
    }
}
