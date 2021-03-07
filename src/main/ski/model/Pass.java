package ski.model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

public class Pass implements Writable {
    // keeps track of all pass num created
    private static final ArrayList<Integer> listOfPassNumUsed = new ArrayList<>();
    private final int passNum;                 // the unique pass number
    private String passType;                   // type of the pass: child, youth, adult, senior
    private boolean expiredPass;               // T if pass expired
    Random randomNumber = new Random();        // random pass number creator
    public static final String child = "child";
    public static final String youth = "youth";
    public static final String adult = "adult";
    public static final String senior = "senior";
    public static final String invalid = "invalid pass type";

    // REQUIRES: age > 0
    // EFFECTS: passNum is an unique random positive integer not assigned
    //          to any other account; passType depends on age of guest
    //          assigned to it, expiredPass set to false
    public Pass(int age) {
        this.passNum = randomPassNumGenerator();
        expiredPass = false;
        this.passType = setPassType(age);
    }

    public Pass(int passNum, String passType, boolean expired) {
        this.passNum = passNum;
        this.passType = passType;
        expiredPass = expired;
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
    // EFFECTS: sets the passType depending on the input age of the pass
    public String setPassType(int age) {
        String assignedPassType;
        if (age >= 0 && age <= 5) {
            assignedPassType = child;
        } else if (age > 5 && age <= 18) {
            assignedPassType = youth;
        } else if (age > 18 && age < 65) {
            assignedPassType = adult;
        } else if (age >= 65) {
            assignedPassType = senior;
        } else {
            assignedPassType = invalid;
        }
        return assignedPassType;
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
        while (!(validPassNumber(passId))) {
            passId = randomNumber.nextInt();
        }
        listOfPassNumUsed.add(passId);
        return passId;
    }

    // EFFECTS: returns true if number is valid, false if otherwise
    public boolean validPassNumber(int randomNumber) {
        boolean validAccountId = true;
        if (listOfPassNumUsed.contains(randomNumber)) {
            validAccountId = false;
        } else if (randomNumber < 0) {
            validAccountId = false;
        } else if (randomNumber > 99999) {
            validAccountId = false;
        }
        return validAccountId;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", passNum);
        json.put("passType", passType);
        json.put("expired", expiredPass);
        return json;
    }

}
