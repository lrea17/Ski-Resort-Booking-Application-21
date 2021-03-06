package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import ski.model.Accounts;
import ski.model.Guest;
import ski.model.Pass;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Citation: code for this class has been obtained from JSonSerializationDemo
 * URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
 */

// Represents a reader that reads Guest from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Accounts from file and returns it;
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

    // EFFECTS: parses Accounts from JSON object and returns it
    private Accounts parseAccounts(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Accounts acc = new Accounts(name);
        addGuests(acc, jsonObject);
        return acc;
    }

    // MODIFIES: acc
    // EFFECTS: parses Guests from JSON object and adds them to Accounts
    private void addGuests(Accounts acc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfGuests");
        for (Object json : jsonArray) {
            JSONObject nextGuest = (JSONObject) json;
            addGuest(acc, nextGuest);
        }

    }

    // MODIFIES: acc
    // EFFECTS: parses guest from JSON object and adds it to Accounts
    private void addGuest(Accounts acc, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        int id = jsonObject.getInt("id");
        JSONArray jsonArray = jsonObject.getJSONArray("listOfExpiredPasses");
        Guest g = new Guest(name, age, id);
        addPasses(g, jsonArray);
        acc.addGuest(g);
    }


    // MODIFIES: g
    // EFFECTS: parses Passes from JSON object and adds them to Guest
    private void addPasses(Guest g, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextPass = (JSONObject) json;
            addPass(g, nextPass);
        }
    }

    // MODIFIES: g
    // EFFECTS: parses pass from JSON object and adds it to Guest
    private void addPass(Guest g, JSONObject jsonObject) {
        String passType = jsonObject.getString("passType");
        int passNum = jsonObject.getInt("passNum");
        boolean expired = jsonObject.getBoolean("passExpired");
        Pass p = new Pass(passNum, passType, expired);
        g.loadExpiredPasses(p);
    }

}

