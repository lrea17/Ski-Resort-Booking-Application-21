package model;

import java.util.ArrayList;

public class Guest {
    private static int nextAccountId = 1;  // tracks id of next account created
    private int id;             // account id
    private String name;        // guest name
    private int age;            // guest age
    private boolean hasAPass = false;    // has a pass or not
    private int numDaysSkied = 0;      // # of days skied this year
    private String passType = "none";
    ArrayList<Guest> listOfGuests = new ArrayList<Guest>();

    //REQUIRES: guestName has a non-zero length,
    //EFFECTS: name of guest is set to guestName; account id is a
    //         positive integer not assigned to any other account;
    //         age is set to guestAge; hasAPass is set to hasPass (starts false);
    //         numDaysSkied set to daysSkied (starts at 0);
    public Guest(String guestName, int guestAge) {
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

    public Boolean checkForPass() {
        return hasAPass;
    }

    public int getAge() {
        return age;
    }

    public int getNumDaysSkied() {
        return numDaysSkied;
    }

    public String getPassType() {
        return passType;
    }

    public ArrayList<Guest> getListOfGuests() {
        return listOfGuests;
    }

    public String bookSkiDay() {
        return "none";
    }

      /*if (checkForPass()) {
        return "Your ski day is booked";
    } else {
        return "Please add a pass to your guest profile";
    }
    */

    public void addPassToProfile() {
     /*   if (this.getAge() <= 5) {
            passType = "child";
        } if-else (this.getAge() > 5 && this.getAge() <= 18) {
            passType = "youth";
        } if else (this.getAge() > 18 && this.getAge() < 65) {
            passType = "adult";
        } else {
            passType = "senior";
        }
        this.hasAPass = true;
        numDaysSkied++;*/
    }

}


