package ski.reservation.application.model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Accounts implements Writable {
    private String name;
    private static ArrayList<Guest> listOfGuests = new ArrayList<>();

    // EFFECTS: creates a list of guests list for ski application
    public Accounts(String name) {
        this.name = name;
    }

    // getter
    public static ArrayList<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public String getName() {
        return name;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("guests", thingiesToJson());
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
