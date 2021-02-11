package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PassTest {

    private Pass passTest;
    private Guest guestTestChild = new Guest("Rachel", 4);
    private Guest guestTestChildUnderFive = new Guest("Beth", 4);
    private Guest guestTestChildFive = new Guest("George", 5);
    private Guest guestTestYouth = new Guest("Anna", 6);
    private Guest guestTestAlmostAdult = new Guest("Blair", 18);
    private Guest guestTestAdult = new Guest("Rupert", 19);
    private Guest guestTestAlmostSenior = new Guest("Lindsay", 64);
    private Guest guestTestSenior = new Guest("Ken", 65);

    //TODO not sure I want a before each here, so I can test the different ages
   @BeforeEach
    void setup() {
       passTest = new Pass(guestTestAdult.getAge());
   }

    @Test
    void testConstructor() {
        assertEquals(0, passTest.getPassNum());
        assertEquals("adult", passTest.getPassType()); // should return lindsays age?
        assertFalse(passTest.isPassExpired());
    }

    //TODO need to test all the passTypes for the different ages
    //TODO need to test the setters/getters in Pass

}
