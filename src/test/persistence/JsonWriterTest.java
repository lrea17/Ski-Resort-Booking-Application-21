package persistence;

import org.junit.jupiter.api.Test;
import ski.model.Accounts;
import ski.model.Guest;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Accounts acc = new Accounts("Snowy Mountain");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccounts() {
        try {
            Accounts acc = new Accounts("Snowy Mountain");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccounts.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccounts.json");
            acc = reader.read();
            assertEquals("Snowy Mountain", acc.getName());
            assertEquals(0,acc.numGuests());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccounts() {
        try {
            Accounts acc = new Accounts("Snowy Mountain");
            acc.addGuest(new Guest("A", 28));
            acc.addGuest(new Guest("B", 65));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccounts.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccounts.json");
            acc = reader.read();
            assertEquals("Snowy Mountain", acc.getName());
            List<Guest> listOfGuests = acc.getListOfGuests();
            assertEquals(2, listOfGuests.size());
            checkGuest("A", 28, listOfGuests.get(0));
            checkGuest("B", 65, listOfGuests.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
