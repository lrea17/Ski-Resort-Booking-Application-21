package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassTest {

    private Pass passTest;
    private Guest guestTestChildUnderFive = new Guest("Beth", 4);
    private Guest guestTestChildFive = new Guest("George", 5);
    private Guest guestTestYouth = new Guest("Anna", 6);
    private Guest guestTestAlmostAdult = new Guest("Blair", 18);
    private Guest guestTestAdult = new Guest("Rupert", 19);
    private Guest guestTestAlmostSenior = new Guest("Lindsay", 64);
    private Guest guestTestSenior = new Guest("Ken", 65);

    @BeforeEach
    void setup() {
        passTest = new Pass(guestTestAdult.getAge());
    }

    @Test
    void testConstructor() {
        assertEquals(1, passTest.getPassNum());
        assertEquals("adult", passTest.getPassType()); // should return lindsays age?
        assertFalse(passTest.isPassExpired());
    }

    //double check checkstyle method lengths
    @Test
    void testDiffPassTypes() {
        Pass passTest1 = new Pass(guestTestChildUnderFive.getAge());
        assertEquals("child", guestTestChildUnderFive.getPassType());
        Pass passTest2 = new Pass(guestTestChildFive.getAge());
        assertEquals("child", guestTestChildFive.getPassType());
        Pass passTest3 = new Pass(guestTestYouth.getAge());
        assertEquals("youth", guestTestYouth.getPassType());
        Pass passTest4 = new Pass(guestTestAlmostAdult.getAge());
        assertEquals("youth", guestTestAlmostAdult.getPassType());
        Pass passTest5 = new Pass(guestTestAdult.getAge());
        assertEquals("adult", guestTestAdult.getPassType());
        Pass passTest6 = new Pass(guestTestAlmostSenior.getAge());
        assertEquals("adult", guestTestAlmostSenior.getPassType());
        Pass passTest7 = new Pass(guestTestSenior.getAge());
        assertEquals("senior", guestTestSenior.getPassType());
        assertEquals(2, passTest1.getPassNum());
        assertEquals(3, passTest2.getPassNum());
        assertEquals(4, passTest3.getPassNum());
        assertEquals(5, passTest4.getPassNum());
        assertEquals(6, passTest5.getPassNum());
        assertEquals(7, passTest6.getPassNum());
        assertEquals(8, passTest7.getPassNum());
    }

    //TODO need to test the setters/getters in Pass
    // get pass num, get pass type already tested

    @Test
    void testSetExpiredPass(){
        passTest.setExpiredPass();
        assertTrue(passTest.isPassExpired());
    }

    @Test
    void testSetDayValid(){
        passTest.setDayValid("Wednesday");
        assertEquals("Wednesday", passTest.getDayValid());
    }







}
