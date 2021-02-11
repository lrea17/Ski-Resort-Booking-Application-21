package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        assertFalse(guestTest.hasAPass());
        assertEquals("none", guestTest.getPassType());  // may need to tweak this one
        assertEquals(1, guestTest.getID());
        assertFalse(guestTest.hasReservation());
    }

    @Test
    void testAddPassToProfile(){
        guestTest.addPassToProfile(p);
        assertTrue(guestTest.listOfPasses.contains(p));
        assertTrue(guestTest.hasAPass());
    }

    // want to make a successful reservation - pass should be on profile and day matches
    // should now have a reservation && should make the pass p expired
    @Test
    void testMakeReservationCorrectDay() {
        String currentValidDay = "Monday";
        guestTest.addPassToProfile(p);
        p.setDayValid(currentValidDay);
        guestTest.makeReservation("Monday");
        assertTrue(guestTest.hasReservation());
        assertTrue(p.isPassExpired());
    }

    // guest has pass but valid day does not match
    @Test
    void testMakeReservationIncorrectDay(){
        String currentValidDay = "Tuesday";
        guestTest.addPassToProfile(p);
        p.setDayValid(currentValidDay);
        guestTest.makeReservation("Monday");
        assertFalse(guestTest.hasReservation());
        assertFalse(p.isPassExpired());
    }

    // testing trying to make a reservation with no pass
    @Test
    void testMakeReservationNoPass(){
        guestTest.makeReservation("Monday");
        assertFalse(guestTest.hasReservation());
    }

    @Test
    void testCancelReservation(){
        guestTest.cancelReservation();
        assertFalse(guestTest.hasReservation());
    }

    }


