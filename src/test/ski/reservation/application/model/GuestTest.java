package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private Guest guestTest;
    private Pass p = new Pass(guestTest.getAge());  //TODO not sure if this is right


    @BeforeEach
    void setup() {
        guestTest = new Guest("Lindsay",28);
    }

    @Test
    void testConstructor() {
        assertEquals("Lindsay", guestTest.getName());
        assertEquals(28, guestTest.getAge());
        assertEquals("No current pass", guestTest.getPassType());  // may need to tweak this one
        assertEquals(1, guestTest.getID());
        assertEquals(null, guestTest.getCurrentPass());
    }

    @Test
    void makeReservationWithCurrentPass() {
        Pass passTester = new Pass(guestTest.getAge());
        guestTest.setCurrentPass(passTester);
        guestTest.makeReservation();
        assertEquals(null, guestTest.getCurrentPass());
        assertTrue(passTester.isPassExpired());
        assertTrue(guestTest.listOfExpiredPasses.contains(passTester);
        assertEquals("You already have a reservation.", guestTest.makeReservation());

    }

    @Test
    void testCancelReservation(){
        Pass passTester2 = new Pass(guestTest.getAge());
        guestTest.setCurrentPass(passTester2);
        guestTest.makeReservation();
        guestTest.cancelReservation(passTester2);
        assertEquals(passTester2, guestTest.getCurrentPass());
        assertFalse(passTester2.isPassExpired());
        assertFalse(guestTest.listOfExpiredPasses.contains(passTester2));
    }

    }


