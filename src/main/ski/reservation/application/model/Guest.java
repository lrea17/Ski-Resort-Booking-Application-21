package ski.reservation.application.model;

import java.util.ArrayList;
import java.util.Random;

public class Guest {
    private static final ArrayList<Integer> listOfGuestIds = new ArrayList<>(); // tracks account ids already created
    private final int id;                  // account id
    private final String name;             // guest name
    private int age;                 // guest age
    private String passType;               // type of pass a guest requires
    private Pass currentPass;              // pass available on a guest account to "reserve" a ski day with
    Random randomNumber = new Random();    // randomID generator variable
    final ArrayList<Pass> listOfExpiredPasses = new ArrayList<>(); // list of passes guest has used

    // REQUIRES: guestName has a non-zero length, age is a non-zero length between 0 - 150
    // EFFECTS: name of guest is set to guestName; guest id is a unique random
    //          positive integer not assigned to any other account; age is set
    //          to guestAge; passType is dependent on age of guest; instantiates a new list
    //          of expired passes to track guest ski history
    public Guest(String guestName, int guestAge) {
        this.id = randomIdGenerator();
        this.name = guestName;
        this.age = guestAge;
        this.setPassType();
    }

    // getters
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassType() {
        return passType;
    }

    public Pass getCurrentPass() {
        return currentPass;
    }

    public ArrayList<Pass> getListOfExpiredPasses() {
        return listOfExpiredPasses;
    }

    public static ArrayList<Integer> getListOfGuestIds() {
        return listOfGuestIds;
    }

    public int getNumberOfDaysSkied() {
        return listOfExpiredPasses.size();
    }

    public void setCurrentPass(Pass p) {
        currentPass = p;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // MODIFIES: this passType
    // EFFECTS: sets the passType that guest requires depending on guest age
    public void setPassType() {
        String assignedPassType = null;
        if (age >= 0 && age <= 5) {
            assignedPassType = Pass.child;
        } else if (age > 5 && age <= 18) {
            assignedPassType = Pass.youth;
        } else if (age > 18 && age < 65) {
            assignedPassType = Pass.adult;
        } else if (age >= 65) {
            assignedPassType = Pass.senior;
        }
        passType = assignedPassType;
    }

    // MODIFIES: this
    // EFFECTS: creates a new pass that is assigned to this guest
    public void newCurrentPass() {
        currentPass = new Pass(this.getAge());
    }

    // EFFECTS: creates random number for the accountId variable, checks
    //          listOfGuestIds to ensure no reduplication
    public int randomIdGenerator() {
        int accountId = randomNumber.nextInt();
        while (!(validNumber(accountId))) {
            accountId = randomNumber.nextInt();
        }
        listOfGuestIds.add(accountId);
        return accountId;
    }

    // EFFECTS: returns true if number is valid, false if otherwise
    public boolean validNumber(int randomNumber) {
        boolean validAccountId = true;
        if (listOfGuestIds.contains(randomNumber)) {
            validAccountId = false;
        } else if (randomNumber < 0) {
            validAccountId = false;
        } else if (randomNumber > 99999) {
            validAccountId = false;
        }
        return validAccountId;
    }


    //MODIFIES: this
    //EFFECTS: checks to see if the guest has a current pass, if not creates a new pass
    //         effectively "booking a reservation", adds that pass to the guests list
    //         of expired passes, and sets that pass to expired and changes current pass
    //         to null
    public void makeReservation() {
        if (this.currentPass == null) {
            newCurrentPass();
        }
        listOfExpiredPasses.add(currentPass);
        currentPass.setExpiredPass();
        currentPass = null;
    }

    // MODIFIES: this and last pass in list of expired passes
    // EFFECTS: cancels a guests reservation by revalidating and removing the last pass in
    //          the guests list of expired passes, and sets that specific pass to be the
    //          guests current pass
    public void cancelReservation() {
        Pass p = listOfExpiredPasses.get(listOfExpiredPasses.size() - 1);
        p.revalidatePass();
        listOfExpiredPasses.remove(p);
        currentPass = p;
    }
}


