package ski.reservation.application.model;

import java.util.ArrayList;
import java.util.Random;

public class Pass {
    //private static int nextPassNum = 0;
    private static ArrayList<Integer> listOfPassNumsUsed = new ArrayList<>(); // keeps track of all pass nums created
    private int passNum;                 // the unique pass number
    private String passType;             // type of the pass: child, youth, adult, senior
    private boolean expiredPass;         // T if pass expired
    Random randomNumber = new Random();  // random pass number creator
    private String assignedPassType;


    //EFFECTS: creates a single pass; passNum is a positive
    //         integer not assigned to any other account;
    //         passType depends on the age of the person it
    //         will be assigned to; dayValid needs to be set
    //         with a string;  expiredPass starts at false
    public Pass(int age) {
        this.passNum = randomPassNumGenerator();
        expiredPass = false;
        String child = "child";
        String youth = "youth";
        String adult = "adult";
        String senior = "senior";
        if (age >= 0 && age <= 5) {
            assignedPassType = child;
        }
        if (age > 5 && age <= 18) {
            assignedPassType = youth;
        }
        if (age > 18 && age < 65) {
            assignedPassType = adult;
        }
        if (age >= 65) {
            assignedPassType = senior;
        }
        this.passType = assignedPassType;

    }

    // getters
    public String getPassType() {
        return passType;
    }

    public int getPassNum() {
        return passNum;
    }

    public boolean isPassExpired() {
        return expiredPass;
    }

    public static ArrayList<Integer> getListOfPassNumsUsed() {
        return listOfPassNumsUsed;
    }

    //MODIFIES: this
    //EFFECTS: sets pass to expired
    public void setExpiredPass() {
        this.expiredPass = true;
    }

    //MODIFIES: this
    //EFFECTS: un-expires pass (in case guest cancels day)
    public void revalidatePass() {
        this.expiredPass = false;
    }

    public String passTypeAssigner() {
        return "boop";
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: creates a unique pass number for each pass created, checks listOfPassNumCreated to ensure
    //         a no number is used twice
    public int randomPassNumGenerator() {
        int passId = randomNumber.nextInt();
        while (listOfPassNumsUsed.contains(passId) || passId < 0) {
            passId = randomNumber.nextInt();
        }
        listOfPassNumsUsed.add(passId);
        return passId;
    }
}
