package ski.reservation.application.model;

public class Pass {
    private static int nextPassNum = 0;   // keeps track of pass number
    private int passNum;
    private String passType;
    private String dayValid;
    private boolean expiredPass;
    //TODO put in code for Pass after we do tests


    //EFFECTS: creates a single pass; passNum is a positive
    //         integer not assigned to any other account;
    //         passType depends on the age of the person it
    //         will be assigned to; dayValid needs to be set
    //         with a string;  expiredPass starts at false
    public Pass(int age) {
        String child = "child";
        String youth = "youth";
        String adult = "adult";
        String senior = "senior";
        if (age <= 5) {
            passType = child;
        }
        if (age <= 18) {
            passType = youth;
        }
        if (age < 65) {
            passType = adult;
        } else {
            passType = senior;
        }
        this.passNum = nextPassNum++;
        expiredPass = false;
    }

    // getters
    public String getPassType() {
        return passType;
    }

    public int getPassNum() {
        return passNum;
    }

    public String getDayValid() {
        return dayValid;
    }

    //MODIFIES: this
    //EFFECTS: sets pass to expired
    public void setExpiredPass() {
        this.expiredPass = true;
    }

    //MODIFIES: this
    //EFFECTS: sets the valid day of the pass
    public void setDayValid(String dayValid) {
        this.dayValid = dayValid;
    }

    //EFFECTS: If the dayValid matches the day of the week, returns false sets expired pass to false.
    //         If dayValid does not match the day of the week, returns true and sets expired pass to true
    public boolean isPassExpired() {
        return expiredPass;
    }
}
