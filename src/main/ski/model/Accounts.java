package ski.model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Accounts implements Writable {
    private String name;
    private static List<Guest> listOfGuests;

    // EFFECTS: constructs an account with a name and empty list of guests
    public Accounts(String name) {
        this.name = name;
        listOfGuests = new ArrayList<>();
    }

    // getter
    public static List<Guest> getListOfGuests() {
        return listOfGuests;
                //Collections.unmodifiableList(listOfGuests);
    }

    public String getName() {
        return name;
    }

    public int numGuests() {
        return listOfGuests.size();
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
        json.put("listOfGuests", guestsToJson());
        return json;
    }

    // EFFECTS: returns guests in this Account as a JSON array
    private JSONArray guestsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Guest g : listOfGuests) {
            jsonArray.put(g.toJson());
        }
        return jsonArray;
    }
}
