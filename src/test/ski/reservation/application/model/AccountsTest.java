package ski.reservation.application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ski.reservation.application.model.Accounts.*;
import static ski.reservation.application.model.Accounts.addGuest;

public class AccountsTest {

    @BeforeEach
    public void setUp(){
        new Accounts();
    }

    @Test
    public void testListEmpty(){
        assertEquals(0, getListOfGuests().size());
    }

    @Test
    public void testAddGuest(){
        Guest g = new Guest("Lindsay", 28);
        addGuest(g);
        assertTrue(getListOfGuests().contains(g));
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
        assertEquals(2, getListOfGuests().size());
        assertEquals(0, getListOfGuests().indexOf(a));
        assertEquals(1, getListOfGuests().indexOf(c));
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
