package persistence;

import org.json.JSONArray;
import org.json.JSONObject;
import ski.reservation.application.model.Accounts;
import ski.reservation.application.model.Guest;
import ski.reservation.application.model.Pass;

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
    public System read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSystem(jsonObject);
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
    private Guest parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("1"); // not sure if this is right but added
        Guest guest = new Guest(name,age);
        addThingies(guest, jsonObject);
        return guest;
    }

    // MODIFIES: guest
    // EFFECTS: parses Passes from JSON object and adds them to Guest
    private void addThingies(Guest guest, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Passes");
        for (Object json : jsonArray) {
            JSONObject nextPass = (JSONObject) json;
            addThingy(guest, nextPass); // should this be next pass or next action?
        }
    }

    // MODIFIES: guest
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(Guest guest, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("1"); // not sure if this is right but added
        Accounts accounts = Accounts.valueOf(jsonObject.getString("accounts"));
        Pass pass = new Pass(age);
        guest.addThingy(pass);
    }
}
