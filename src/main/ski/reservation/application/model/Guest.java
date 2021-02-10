package ski.reservation.application.model;

import java.util.ArrayList;

public class Guest extends Pass {
    private static int nextAccountId = 1;  // tracks id of next account created
    private int id;             // account id
    private String name;        // guest name
    private int age;            // guest age
    private boolean pass = false;    // has a pass or not
    private String passType = "none"; //TODO DO I NEED THIS?
    private boolean reservation = false;
    ArrayList<Guest> listOfGuests = new ArrayList<Guest>();

    //REQUIRES: guestName has a non-zero length,
    //EFFECTS: name of guest is set to guestName; account id is a
    //         positive integer not assigned to any other account;
    //         age is set to guestAge; pass is set to hasAPass (starts false);
    //         reservation is set to hasReservation (starts false); passType is set to "none"
    //         //TODO WHAT HAPPENS WITH ME EXTENDING PASS INTO GUEST? DOES THAT COUNT AS ADDING X TO Y
    public Guest(String guestName, int guestAge) {
        super("none");
        this.id = nextAccountId++;
        this.name = guestName;
        this.age = guestAge;
        listOfGuests.add(this);
    }

    // getters
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean hasAPass() {
        return pass;
    }

    public int getAge() {
        return age;
    }

    public boolean hasReservation() {
        return reservation;
    }

    public ArrayList<Guest> getListOfGuests() {
        return listOfGuests;
    }

    //MODIFIES: this
    //EFFECTS: adds the correct passType to the guest profile depending on age of guest
    public void addPassToProfile() {
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
        ;*/
    }

    //MODIFIES: this
    //EFFECTS: confirms a guest has a day reserved by returning true, false if otherwise
    public String confirmReservation() {
        return "none";
    }
    //TODO add in this code after tests
      /*if (hasAPass() && hasReservation()) {
        return "Your ski day is booked";
    } else {
        return "Please add a pass to your guest profile";
    }
    */

    //MODIFIES: this
    //EFFECTS: cancels a guests reservation by setting hasReservation to false
    public void cancelReservation() {

    }



}


