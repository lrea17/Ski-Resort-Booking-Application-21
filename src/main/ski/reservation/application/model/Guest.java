package ski.reservation.application.model;

import java.util.ArrayList;
import java.util.Random;

public class Guest {
    private static ArrayList<Integer> listOfGuestIds = new ArrayList<>(); // tracks id of next guest account created
    private int id;                        // account id
    private String name;                   // guest name
    private int age;                       // guest age
    private Pass currentPass;              // current pass on guest profile
    Random randomNumber = new Random();    // randomID generator variable
    ArrayList<Pass> listOfExpiredPasses;   // list of passes guest has used



    //REQUIRES: guestName has a non-zero length and is letters, age is a non-zero length between (0 - infinity)
    //EFFECTS: name of guest is set to guestName; guest id is a unique random
    //         positive integer not assigned to any other account; age is set
    //         to guestAge;
    public Guest(String guestName, int guestAge) {
        this.id = randomIdGenerator();
        this.name = guestName;
        this.age = guestAge;
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
        if (this.getCurrentPass() != null) {
            return currentPass.getPassType();
        } else {
            return "No current pass";
        }
    }

    public Pass getCurrentPass() {
        return currentPass;
    }

    public void newCurrentPass() {
        currentPass = new Pass(this.getAge());
    }

    public void setCurrentPass(Pass p) {
        currentPass = p;
    }

    public ArrayList<Pass> getListOfExpiredPasses() {
        return listOfExpiredPasses;
    }

    public static ArrayList<Integer> getListOfGuestIds() {
        return listOfGuestIds;
    }


    public int randomIdGenerator() {
        int accountId = randomNumber.nextInt();
        while (listOfGuestIds.contains(accountId) || accountId < 0 || accountId > 99999) {
            accountId = randomNumber.nextInt();
        }
        listOfGuestIds.add(accountId);
        return accountId;
    }

    //MODIFIES: this & Pass if there is a pass on guest profile
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


