package ski.model;

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
        Guest g = new Guest("Lindsay", 28);
        addGuest(g);
        assertTrue(getListOfGuests().contains(g));
    }

    @Test
    public void testGetName(){
        Accounts accountTest = new Accounts("Lindsay");
        assertEquals("Lindsay", accountTest.getName());
    }

    @Test
    public void testRemoveGuest(){
        Guest g = new Guest("Lindsay", 28);
        addGuest(g);
        removeGuest(g);
        assertFalse(getListOfGuests().contains(g));
    }

    @Test
    public void testMultipleGuestsInListRemove(){
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
    }

    @Test
    public void testLookupGuestByID(){
        Guest g = new Guest("Lindsay", 28);
        addGuest(g);
        assertEquals(g, lookupGuest(g.getID()));
    }

    @Test
    public void testLookupGuestIDDoesNotExist(){
        Guest g = new Guest("Lindsay", 28);
        addGuest(g);
        assertNull(lookupGuest(1234));
    }
}
