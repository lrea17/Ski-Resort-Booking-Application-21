package ski.reservation.application.model;

import java.util.ArrayList;

public class Guest {
    private static int nextAccountId = 1;  // tracks id of next account created
    private int id;             // account id
    private String name;        // guest name
    private int age;            // guest age
    private boolean pass = false;    // has a pass or not
    private boolean reservation = false;
    ArrayList<Pass> listOfPasses;
    //TODO put in code for Guest after we do tests

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
        return "none";
    }

    public Boolean hasAPass() {
        return pass;
    }

    public boolean hasReservation() {
        return reservation;
    }

    //MODIFIES: this
    //EFFECTS: adds the correct passType to the guest profile depending on age of guest
    public void addPassToProfile(Pass passType) {
        //list of passes, add a pass to the list of passes here
        // also need to set pass to tru
        listOfPasses.add(passType);


        // TODO make this run
     /*   if (this.getAge() <= 5) {
            passType = "child";
        } if-else (this.getAge() > 5 && this.getAge() <= 18) {
            passType = "youth";
        } if else (this.getAge() > 18 && this.getAge() < 65) {
            passType = "adult";
        } else {
            passType = "senior";
        }
        this.pass = true;
        numOfPasses += numPassesWanted;

        ;*/
    }

    //MODIFIES: this
    //EFFECTS: confirms a guest has a day reserved by returning true, false if otherwise
    public void makeReservation(String currentSkiDay) {
        this.reservation = true;
    }
    //TODO add in this code after tests
      /*if (hasAPass() && hasReservation()) {
        return "Your ski day is booked";
    } else {
        return "Please add a pass to your guest profile";
    }

    started coding this
    if (guestTest.hasAPass()) {
            if (p.getDayValid() == currentValidDay) {
                guestTest.makeReservation();
                p.setExpiredPass();
            }
        }
    */

    //MODIFIES: this
    //EFFECTS: cancels a guests reservation by setting hasReservation to false
    public void cancelReservation() {
        this.reservation = false;
    }



}


