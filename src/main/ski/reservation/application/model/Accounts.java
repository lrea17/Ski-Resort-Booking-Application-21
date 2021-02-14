package ski.reservation.application.model;

import java.util.ArrayList;

public class Accounts {
    private static ArrayList<Guest> listOfGuests;

    // EFFECTS: instantiates master list of guests list for ski application
    public Accounts() {
        listOfGuests = new ArrayList<>();
    }

    public static ArrayList<Guest> getListOfGuests() {
        return listOfGuests;
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

}
