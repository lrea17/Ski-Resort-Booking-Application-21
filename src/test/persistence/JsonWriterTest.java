package persistence;

import org.junit.jupiter.api.Test;
import ski.model.Accounts;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

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
    void testWriterEmptyWorkroom() {
        try {
            Accounts acc = new Accounts("Snowy Mountain");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccounts.json");
            writer.open();
            writer.write(acc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccounts.json");
            acc = reader.read();
            assertEquals("Snowy Mountain", acc.getName());
            assertEquals(0, acc.);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
