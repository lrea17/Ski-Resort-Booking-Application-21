package ski.reservation.application.model;

import java.util.ArrayList;
import java.util.Random;

public class Pass {
    // keeps track of all pass num created
    private static final ArrayList<Integer> listOfPassNumUsed = new ArrayList<>();
    private final int passNum;                 // the unique pass number
    private final String passType;             // type of the pass: child, youth, adult, senior
    private boolean expiredPass;               // T if pass expired
    Random randomNumber = new Random();        // random pass number creator
    private String assignedPassType;
    public static final String child = "child";
    public static final String youth = "youth";
    public static final String adult = "adult";
    public static final String senior = "senior";

    // REQUIRES: age > 0
    // EFFECTS: passNum is an unique random positive integer not assigned
    //          to any other account; passType depends on age of guest
    //          assigned to it, expiredPass set to false
    public Pass(int age) {
        this.passNum = randomPassNumGenerator();
        expiredPass = false;
        if (age >= 0 && age <= 5) {
            assignedPassType = child;
        } else if (age > 5 && age <= 18) {
            assignedPassType = youth;
        } else if (age > 18 && age < 65) {
            assignedPassType = adult;
        } else if (age >= 65) {
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

    public static ArrayList<Integer> getListOfPassNumUsed() {
        return listOfPassNumUsed;
    }

    public void setExpiredPass() {
        this.expiredPass = true;
    }

    // MODIFIES: this
    // EFFECTS: un-expires pass
    public void revalidatePass() {
        this.expiredPass = false;
    }

    // MODIFIES: this
    // EFFECTS: creates unique pass number for pass, checks
    //          listOfPassNumCreated to ensure no reduplication
    public int randomPassNumGenerator() {
        int passId = randomNumber.nextInt();
        while (listOfPassNumUsed.contains(passId) || passId < 0) {
            passId = randomNumber.nextInt();
        }
        listOfPassNumUsed.add(passId);
        return passId;
    }
}
