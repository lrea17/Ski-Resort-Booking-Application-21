package ski.model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Accounts implements Writable {
    private String name;
    private List<Guest> listOfGuests;

    // EFFECTS: creates a list of guests list for ski application
    public Accounts(String name) {
        this.name = name;
        listOfGuests = new ArrayList<>();
    }

    // getter
    public List<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public String getName() {
        return name;
    }

    public int numGuests() {
        return getListOfGuests().size();
    }

    // MODIFIES: this
    // EFFECTS: adds a guest to the list of guests
    public static void addGuest(Guest g) {
        listOfGuests.add(g);
    }

    // MODIFIES: this
    // EFFECTS: removes a guest from the list of guests
    public static void removeGuest(Guest g) {
        listOfGuests.remove(g);
    }

    // EFFECTS: looks up a guest by their account id number
    public static Guest lookupGuest(int id) {
        int i;
        for (i = 0; i < listOfGuests.size(); i++) {
            if (id == listOfGuests.get(i).getID()) {
                return listOfGuests.get(i);
            }
        }
        return null;
    }

    //EFFECTS: See interface Writable
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("listOfGuests", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Guest g : listOfGuests) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }
}
