package persistence;

import ski.model.Guest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGuest(String name, int age, Guest guest) {
        assertEquals(name, guest.getName());
        assertEquals(age, guest.getAge());
    }
}
