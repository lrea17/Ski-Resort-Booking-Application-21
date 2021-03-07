package persistence;

import ski.model.Guest;
import ski.model.Pass;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGuest(String name, int age, Guest guest) {
        assertEquals(name, guest.getName());
        assertEquals(age, guest.getAge());
    }

    protected void checkPass(String passType, int passNum, Pass pass) {
        assertEquals(passType, pass.getPassType());
        assertEquals(passNum, pass.getPassNum());
    }
}
