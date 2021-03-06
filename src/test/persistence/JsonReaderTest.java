package persistence;

import org.junit.jupiter.api.Test;
import ski.model.Accounts;
import ski.model.Guest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Accounts acc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccounts() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccounts.json");
        try {
            Accounts acc = reader.read();
            assertEquals("Snowy Mountain", acc.getName());
            assertEquals(0, acc.numGuests());
        } catch (IOException e) {
            System.out.println(e);
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccounts() {

        JsonReader reader = new JsonReader("./data/testReaderGeneralAccounts.json");
        try {
            Accounts acc = reader.read();
            assertEquals("Snowy Mountain", acc.getName());
            List<Guest> listOfGuests = acc.getListOfGuests();
            assertEquals(2, listOfGuests.size());
            checkGuest("A", 28, listOfGuests.get(0));
            checkGuest("B", 65, listOfGuests.get(1));
        } catch (IOException e) {
            System.out.println(e);
            fail("Couldn't read from file");
        }
    }

}
