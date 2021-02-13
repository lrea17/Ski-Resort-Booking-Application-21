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
        //assertEquals(1, passTest.getPassNum());
        assertEquals("adult", passTest.getPassType());
        assertFalse(passTest.isPassExpired());
    }

    //double check checkstyle method lengths
    @Test
    void testDiffPassTypes() {
        guestTestChildUnderFive.newCurrentPass();
        assertEquals("child", guestTestChildUnderFive.getCurrentPass().getPassType());
        guestTestChildFive.newCurrentPass();
        assertEquals("child", guestTestChildFive.getPassType());
        guestTestYouth.newCurrentPass();
        assertEquals("youth", guestTestYouth.getPassType());
        guestTestAlmostAdult.newCurrentPass();
        assertEquals("youth", guestTestAlmostAdult.getPassType());
        guestTestAdult.newCurrentPass();
        assertEquals("adult", guestTestAdult.getPassType());
        guestTestAlmostSenior.newCurrentPass();
        assertEquals("adult", guestTestAlmostSenior.getPassType());
        guestTestSenior.newCurrentPass();
        assertEquals("senior", guestTestSenior.getPassType());

    }

    //TODO need to test the setters/getters in Pass
    // get pass num, get pass type already tested

    @Test
    void testSetExpiredPass(){
        passTest.setExpiredPass();
        assertTrue(passTest.isPassExpired());
    }

    @Test
    void testRevalidatePass(){
        passTest.revalidatePass();
        assertFalse(passTest.isPassExpired());
    }

    @Test
    void testRandomPassNumGenerator(){
        int i = passTest.randomPassNumGenerator();
        int j = passTest.randomPassNumGenerator();
        assertNotEquals(i, j);
        assertTrue(i >= 0);
        assertTrue(j >= 0);
        assertTrue(Pass.getListOfPassNumUsed().contains(passTest.getPassNum()));
        assertTrue(Pass.getListOfPassNumUsed().contains(i));
        assertTrue(Pass.getListOfPassNumUsed().contains(j));
    }


}
