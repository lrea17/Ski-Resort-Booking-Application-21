package persistence;

import org.json.JSONObject;
import ski.model.Accounts;
import ski.model.Guest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/**
 This class has been copied from JSonSerializationDemo
 Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// Represents a writer that writes JSON representation of Guests to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // takes accounts  ->
    // MODIFIES: this
    // EFFECTS: writes JSON representation of system to file
    public void write(Accounts acc) {
        JSONObject json = acc.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
