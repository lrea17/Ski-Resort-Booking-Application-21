package ski.reservation.application.model;

import java.util.ArrayList;

public class Guest {
    private static int nextAccountId = 1;  // tracks id of next guest account created
    private int id;                        // account id
    private String name;                   // guest name
    private int age;                       // guest age
    private Pass currentPass;              // current pass on guest profile
    ArrayList<Pass> listOfExpiredPasses;   // list of expired/used passed

    // add current pass
    // string of old invalid passes
    // list of expired passes -> when you add a new pass it adds one to the the list of expired passes

    //REQUIRES: guestName has a non-zero length, age is a non-zero length
    //EFFECTS: name of guest is set to guestName; account id is a
    //         positive integer not assigned to any other account;
    //         age is set to guestAge; pass is set to hasAPass (starts false);
    //         reservation is set to hasReservation (starts false); passType is set to "none"
    public Guest(String guestName, int guestAge) {
        this.id = nextAccountId++;
        this.name = guestName;
        this.age = guestAge;
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
    } //TODO tweaked not sure if correct

    public Pass getCurrentPass() {
        return currentPass;
    }

    public void newCurrentPass() {
        Pass p = new Pass(this.getAge());
        currentPass = p;

    }

    public void setCurrentPass(Pass p) {
        currentPass = p;
    }

    //MODIFIES: this & Pass if there is a pass on guest profile
    //EFFECTS: checks if guest has pass and if the currentSkiDay matches the passes dayValid,
    //         if both are true makes reservation, returns "reservation made" sets pass to be
    //         expired & adds pass to expired passes list.  If no pass, returns "no pass", if
    //         validDay does not match, returns validDay does not match
    public String makeReservation() {
        if (this.currentPass != null) {
            // this.reservation = true;
            listOfExpiredPasses.add(currentPass);
            currentPass.setExpiredPass();
            currentPass = null;
            return "You already have a reservation.";
        } else {
            newCurrentPass();
            listOfExpiredPasses.add(currentPass);
            currentPass.setExpiredPass();
            currentPass = null;
        }
        return "Your reservation has been made.";
    }


    //MODIFIES: this
    //EFFECTS: cancels a guests reservation by setting hasReservation to false
    public void cancelReservation(Pass p) {
        p.revalidatePass();
        listOfExpiredPasses.remove(p);
        currentPass = p;
    }


}


