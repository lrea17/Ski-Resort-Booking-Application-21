package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {
    private Guest guestTest;
    private final Guest guestTestChildUnderFive = new Guest("Beth", 4);
    private final Guest guestTestChildFive = new Guest("George", 5);
    private final Guest guestTestYouth = new Guest("Anna", 6);
    private final Guest guestTestAlmostAdult = new Guest("Blair", 18);
    private final Guest guestTestAdult = new Guest("Rupert", 19);
    private final Guest guestTestAlmostSenior = new Guest("Lindsay", 64);
    private final Guest guestTestSenior = new Guest("Ken", 65);


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
        assertEquals(0, guestTest.getNumberOfDaysSkied());
    }

    @Test
    void testSetAge(){
        Guest newGuest = new Guest("Bob", 22);
        newGuest.setAge(4);
        assertEquals(4, newGuest.getAge());
        newGuest.setPassType();
        assertEquals("child", newGuest.setPassType());
        newGuest.setAge(5);
        assertEquals("child", newGuest.setPassType());
        newGuest.setAge(18);
        assertEquals("youth", newGuest.setPassType());
        assertEquals("youth", newGuest.getPassType());
        newGuest.setAge(19);
        assertEquals("adult" ,newGuest.setPassType());
        assertEquals("adult", newGuest.getPassType());
        newGuest.setAge(64);
        assertEquals("adult", newGuest.setPassType());
        assertEquals("adult", newGuest.getPassType());
        newGuest.setAge(65);
        assertEquals(65, newGuest.getAge());
        assertEquals("senior", newGuest.setPassType());
        assertEquals("senior", newGuest.getPassType());
    }

    @Test
    void testDiffPassTypes() {
        assertEquals("child", guestTestChildUnderFive.getPassType());
        assertEquals("child", guestTestChildFive.getPassType());
        assertEquals("youth", guestTestYouth.getPassType());
        assertEquals("youth", guestTestAlmostAdult.getPassType());
        assertEquals("adult", guestTestAdult.getPassType());
        assertEquals("adult", guestTestAlmostSenior.getPassType());
        assertEquals("senior", guestTestSenior.getPassType());
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
        assertEquals(3, guestTest.getNumberOfDaysSkied());
    }

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
    void testValidAccountNumberInList(){
        assertFalse(guestTest.validNumber(guestTest.getID()));
    }

    @Test
    void testValidAccountNumberLessThanZero(){
        assertFalse(guestTest.validNumber(-2));
    }

    @Test
    void testValidAccountNumberTooBig(){
        assertFalse(guestTest.validNumber(100000));
        assertTrue(guestTest.validNumber(99999));
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


