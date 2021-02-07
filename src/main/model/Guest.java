package model;


public class Guest extends Passsenger{
    private static int nextAccountId = 1;  // tracks id of next account created
    private int id;             // account id
    private String name;        // guest name
    private int age;            // guest age
    private boolean hasAPass = false;    // has a pass or not
    private int numDaysSkied = 0;      // # of days skied this year
    private String passType = "none";

    //REQUIRES: guestName has a non-zero length,
    //EFFECTS: name of guest is set to guestName; account id is a
    //         positive integer not assigned to any other account;
    //         age is set to guestAge; hasAPass is set to hasPass (starts false);
    //         numDaysSkied set to daysSkied (starts at 0);
    public Guest(String guestName, int guestAge, boolean hasPass, int daysSkied) {
        //Guest guest = new Guest();
        this.id = nextAccountId++;
        this.name = guestName;
        this.age = guestAge;
        this.hasAPass = hasPass;
        this.numDaysSkied = daysSkied;

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

    public void buyPass() {
    if (getAge() <= 5) {




    }

    }

    public String bookSkiDay() {
        if(checkForPass()) {
            return "Your ski day is booked";
        } else {

        }

        }

    }

}
