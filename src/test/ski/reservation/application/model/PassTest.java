package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassTest {

    private Pass passTest;
    private final Guest guestTestChildUnderFive = new Guest("Beth", 4);
    private final Guest guestTestChildFive = new Guest("George", 5);
    private final Guest guestTestYouth = new Guest("Anna", 6);
    private final Guest guestTestAlmostAdult = new Guest("Blair", 18);
    private final Guest guestTestAdult = new Guest("Rupert", 19);
    private final Guest guestTestAlmostSenior = new Guest("Lindsay", 64);
    private final Guest guestTestSenior = new Guest("Ken", 65);

    @BeforeEach
    void setup() {
        passTest = new Pass(guestTestAdult.getAge());
    }

    @Test
    void testConstructor() {
        assertEquals("adult", passTest.getPassType());
        assertFalse(passTest.isPassExpired());
    }

   /* @Test
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
    }*/

    @Test
    void testPassType(){
        Pass a = new Pass(4);
        Pass b = new Pass(5);
        Pass c = new Pass(6);
        Pass d = new Pass(18);
        Pass e = new Pass(19);
        Pass f = new Pass(64);
        Pass g = new Pass(65);
        assertEquals("child", a.getPassType());
        assertEquals("child", b.getPassType());
        assertEquals("youth", c.getPassType());
        assertEquals("youth", d.getPassType());
        assertEquals("adult", e.getPassType());
        assertEquals("adult", f.getPassType());
        assertEquals("senior", g.getPassType());
    }

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


    @Test
    void testIndividualPassTypeInList(){
        guestTestYouth.makeReservation();
        guestTestYouth.makeReservation();
        assertEquals("youth", guestTestYouth.getListOfExpiredPasses().get(0).getPassType());
        assertEquals("youth", guestTestYouth.getListOfExpiredPasses().get(1).getPassType());
    }


}
