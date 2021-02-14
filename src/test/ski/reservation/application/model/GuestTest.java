package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private Guest guestTest;


    @BeforeEach
    void setup() {
        guestTest = new Guest("Lindsay", 28);
    }


    @Test
    void testConstructor() {
        assertEquals("Lindsay", guestTest.getName());
        assertEquals(28, guestTest.getAge());
        assertEquals("adult", guestTest.getPassType());
        assertNull(guestTest.getCurrentPass());
    }

    @Test
    public void testingOrderOfExpiredPass() {
        Pass p1 = new Pass(guestTest.getAge());
        Pass p2 = new Pass(guestTest.getAge());
        Pass p3 = new Pass(guestTest.getAge());
        guestTest.listOfExpiredPasses.add(p1);
        guestTest.listOfExpiredPasses.add(p2);
        guestTest.listOfExpiredPasses.add(p3);
        assertEquals(p3, guestTest.listOfExpiredPasses.get(guestTest.listOfExpiredPasses.size() - 1));
    }

    // maybe try to have a test that makes sure these passes were added to the list of expired passes
    // the exact pass..
    // this test could be redundant
    @Test
    void testListOfExpiredPasses() {
        guestTest.makeReservation();
        guestTest.makeReservation();
        guestTest.makeReservation();
        assertEquals(3, guestTest.getListOfExpiredPasses().size());

    }

    @Test
    void testRandomIdGenerator() {
        int i = guestTest.randomIdGenerator();
        int j = guestTest.randomIdGenerator();
        assertNotEquals(i, j);
        assertTrue(i >= 0);
        assertTrue(j >= 0);
        assertTrue(i < 99999);
        assertTrue(j < 99999);
        assertTrue(Guest.getListOfGuestIds().contains(guestTest.getID()));
        assertTrue(Guest.getListOfGuestIds().contains(i));
        assertTrue(Guest.getListOfGuestIds().contains(j));
    }


    @Test
    void makeReservationWithCurrentPass() {
        Pass passTester = new Pass(guestTest.getAge());
        guestTest.setCurrentPass(passTester);
        guestTest.makeReservation();
        assertNull(guestTest.getCurrentPass());
        assertTrue(passTester.isPassExpired());
        assertTrue(guestTest.listOfExpiredPasses.contains(passTester));
    }

    // this may have issues because of the p variable - trying to use the p
    // in the guest class of this method
    @Test
    void makeReservationNoCurrentPass() {
        assertNull(guestTest.getCurrentPass());
        guestTest.makeReservation();
        assertEquals(1, guestTest.listOfExpiredPasses.size());

    }

    @Test
    void testCancelReservation() {
        Pass passTester2 = new Pass(guestTest.getAge());
        guestTest.setCurrentPass(passTester2);
        assertEquals(passTester2, guestTest.getCurrentPass());
        guestTest.makeReservation();
        assertEquals(1, guestTest.listOfExpiredPasses.size());
        guestTest.cancelReservation();
        assertEquals(passTester2, guestTest.getCurrentPass());
        assertFalse(passTester2.isPassExpired());
        assertFalse(guestTest.listOfExpiredPasses.contains(passTester2));
    }

}


