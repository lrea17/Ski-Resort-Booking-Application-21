package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private Guest guestTest = new Guest("Lindsay",28);;




    //void setup() {
        //private guestTest =


    @Test
    void testConstructor() {
        assertEquals("Lindsay", guestTest.getName());
        assertEquals(28, guestTest.getAge());
        assertEquals("No current pass", guestTest.getPassType());  // may need to tweak this one
        assertEquals(1, guestTest.getID());
        assertNull(guestTest.getCurrentPass());
    }

    @Test
    void makeReservationWithCurrentPass() {
        Pass passTester = new Pass(guestTest.getAge());
        guestTest.setCurrentPass(passTester);
        assertEquals("You already have a reservation.", guestTest.makeReservation());
        assertNull(guestTest.getCurrentPass());
        assertTrue(passTester.isPassExpired());
        assertTrue(guestTest.listOfExpiredPasses.contains(passTester));
    }

    // this may have issues because of the p variable - trying to use the p
    // in the guest class of this method
    @Test
    void makeReservationNoCurrentPass(){
        //Pass p = new Pass(guestTest.getAge());
        assertEquals("Your reservation has been made.", guestTest.makeReservation());
        assertNull(guestTest.getCurrentPass());
        //assertTrue(p.isPassExpired());
        //assertTrue(guestTest.listOfExpiredPasses.contains(p));
        assertEquals(1, guestTest.listOfExpiredPasses.size());

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


