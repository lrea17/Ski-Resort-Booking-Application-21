package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private Guest guestTest;

    @BeforeEach
    void setup() {
        guestTest = new Guest("Lindsay",28);
    }

    @Test
    void testConstructor() {
        assertEquals("Lindsay", guestTest.getName());
        assertEquals(28, guestTest.getAge());
        assertFalse(guestTest.hasAPass());
        assertTrue(guestTest.getListOfGuests().contains(guestTest));
        //assertEquals("none", guestTest.getPassType());
        assertEquals(1, guestTest.getID());
        assertFalse(guestTest.hasReservation());
    }

}