package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import ski.model.Accounts;
import ski.model.Guest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 This class has been copied from JSonSerializationDemo
 Repo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// Represents a reader that reads Guest from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Guest from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Accounts read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccounts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Guest from JSON object and returns it
    private Accounts parseAccounts(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Accounts acc = new Accounts(name);
        addGuests(acc, jsonObject);
        return acc;
    }

    // MODIFIES: acc
    // EFFECTS: parses Passes from JSON object and adds them to Accounts
    private void addGuests(Accounts acc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Passes");
        for (Object json : jsonArray) {
            JSONObject nextGuest = (JSONObject) json;
            addGuest(acc, nextGuest);
        }
    }

    // took out //Accounts accounts = Accounts.valueOf(jsonObject.getString("accounts"));

    // MODIFIES: acc
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addGuest(Accounts acc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");  // not sure if this is right to get age
        Guest g = new Guest(name, age);
        acc.addGuest(g);
    }

}
