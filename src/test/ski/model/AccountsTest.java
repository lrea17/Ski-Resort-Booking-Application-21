package ski.model;

import exceptions.AgeOutOfBounds;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ski.model.Accounts.*;
import static ski.model.Accounts.addGuest;

public class AccountsTest {

    @BeforeEach
    public void setUp(){
        Accounts accountTest = new Accounts("Lindsay");
    }

    @Test
    public void testAddGuest(){
        try {
            Guest g = null;
            g = new Guest("Lindsay", 28);
            addGuest(g);
            assertTrue(getListOfGuests().contains(g));
        } catch (AgeOutOfBounds ageOutOfBounds) {
            fail("Not expecting age out of bounds exception here");
        }

    }

    @Test
    public void testGetName(){
        Accounts accountTest = new Accounts("Lindsay");
        assertEquals("Lindsay", accountTest.getName());
    }

    @Test
    public void testRemoveGuest(){
        try {
            Guest g = null;
            g = new Guest("Lindsay", 28);
            addGuest(g);
            removeGuest(g);
            assertFalse(getListOfGuests().contains(g));
        } catch (AgeOutOfBounds ageOutOfBounds) {
            fail("Not expecting age out of bounds exception here");
        }

    }

    @Test
    public void testNumGuests() {
        Accounts account1 = new Accounts("Lindsay");
        try {
            Guest a = new Guest("A", 10);
            Guest b = new Guest("B", 19);
            Guest c = new Guest("C", 65);
            assertEquals(0, account1.numGuests());
            addGuest(a);
            assertEquals(1, account1.numGuests());
            addGuest(b);
            assertEquals(2, account1.numGuests());
            addGuest(c);
            assertEquals(3, account1.numGuests());
        } catch (AgeOutOfBounds e) {
            fail("Not expecting age out of bounds exception here");
        }

    }

    @Test
    public void testMultipleGuestsInListRemove(){
        try {
            Guest a = new Guest("Molly", 18);
            Guest b = new Guest("Bob", 4);
            Guest c = new Guest("Jim", 66);
            addGuest(a);
            addGuest(b);
            addGuest(c);
            removeGuest(b);
            assertFalse(getListOfGuests().contains(b));
            assertTrue(getListOfGuests().contains(a));
            assertTrue(getListOfGuests().contains(c));
        } catch (AgeOutOfBounds e) {
            fail("Not expecting age out of bounds exception here");
        }
    }

    @Test
    public void testLookupGuestByID(){
        try {
            Guest g = new Guest("Lindsay", 28);
            addGuest(g);
            assertEquals(g, lookupGuest(g.getID()));
        } catch (AgeOutOfBounds e) {
            fail("Not expecting age out of bounds exception here");
        }
    }

    @Test
    public void testLookupGuestIDDoesNotExist(){
        try {
            Guest g = new Guest("Lindsay", 28);
            addGuest(g);
            assertNull(lookupGuest(1234));
        } catch (AgeOutOfBounds e) {
            fail("Not expecting age out of bounds exception here");
        }
    }
}
