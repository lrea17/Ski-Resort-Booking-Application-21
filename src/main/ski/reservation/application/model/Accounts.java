package ski.reservation.application.model;

import java.util.ArrayList;

public class Accounts {
    private static ArrayList<Guest> listOfGuests;

    public Accounts() {
        listOfGuests = new ArrayList<>();
    }

    public static ArrayList<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public static void addGuest(Guest g) {
        listOfGuests.add(g);
    }

    public static void removeGuest(Guest g) {
        listOfGuests.remove(g);
    }

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
