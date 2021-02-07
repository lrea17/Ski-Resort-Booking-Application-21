package model;


public class Passsenger {
    private String passType;
    //private int numDays;
    private double passPrice;
    private int childPrice = 0;
    private int youthPrice = 50;
    private int studentPrice = 60;
    private int adultPrice = 75;
    private int seniorPrice = 0;

    public Passsenger(String type) {
        passType = type;
        if (passType == "child") {
            passPrice = childPrice;
        }
        if (passType == "youth") {
            passPrice = youthPrice;
        }
        if (passType == "student") {
            passPrice = studentPrice;
        }
        if (passType == "adult") {
            passPrice = adultPrice;
        } else {
            passPrice = seniorPrice;
        }
    }

}
