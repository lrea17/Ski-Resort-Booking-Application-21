package ski.reservation.application.model;

import java.util.ArrayList;
import java.util.Random;

public class Guest {
    private static ArrayList<Integer> listOfGuestIds = new ArrayList<>(); // tracks id of next guest account created
    private int id;                        // account id
    private String name;                   // guest name
    private int age;                       // guest age
    private String passType;               // type of pass a guest requires
    private Pass currentPass;              // current pass on guest profile
    Random randomNumber = new Random();    // randomID generator variable
    ArrayList<Pass> listOfExpiredPasses;   // list of passes guest has used


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
        listOfExpiredPasses = new ArrayList<>();
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

    public void setCurrentPass(Pass p) {
        currentPass = p;
    }

    // MODIFIES: this passType
    // EFFECTS: sets the passType that guest requires depending on guest age
    public void setPassType() {
        String assignedPassType = null;
        String child = "child";
        String youth = "youth";
        String adult = "adult";
        String senior = "senior";
        if (age >= 0 && age <= 5) {
            assignedPassType = child;
        } else if (age > 5 && age <= 18) {
            assignedPassType = youth;
        } else if (age > 18 && age < 65) {
            assignedPassType = adult;
        } else if (age >= 65) {
            assignedPassType = senior;
        }
        passType = assignedPassType;
    }

    // MODIFIES: this
    // EFFECTS: creates a new pass that is assigned to this guest
    public void newCurrentPass() {
        currentPass = new Pass(this.getAge());
    }

    // EFFECTS: creates a random number for the accountId variable
    public int randomIdGenerator() {
        int accountId = randomNumber.nextInt();
        while (listOfGuestIds.contains(accountId) || accountId < 0 || accountId > 99999) {
            accountId = randomNumber.nextInt();
        }
        listOfGuestIds.add(accountId);
        return accountId;
    }

    //MODIFIES: this
    //EFFECTS: checks if guest has pass and if the currentSkiDay matches the passes dayValid,
    //         if both are true makes reservation, returns "reservation made" sets pass to be
    //         expired & adds pass to expired passes list.  If no pass, returns "no pass", if
    //         validDay does not match, returns validDay does not match
    public void makeReservation() {
        if (this.currentPass == null) {
            newCurrentPass();
        }
        listOfExpiredPasses.add(currentPass);
        currentPass.setExpiredPass();
        currentPass = null;
    }


    //MODIFIES: this
    //EFFECTS: cancels a guests reservation by setting hasReservation to false
    public void cancelReservation() {
        Pass p;
        p = listOfExpiredPasses.get(listOfExpiredPasses.size() - 1);
        p.revalidatePass();
        listOfExpiredPasses.remove(p);
        currentPass = p;
    }


}


