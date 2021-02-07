package model;

public class Pass {
    private String passType;
    //private int numDays;
    private double passPrice;
    private int childPrice = 0;
    private int youthPrice = 50;
    private int adultPrice = 75;
    private int seniorPrice = 0;

    public Pass(String type) {
        passType = type;
        if (passType == "child") {
            passPrice = childPrice;
        }
        if (passType == "youth") {
            passPrice = youthPrice;
        }
        if (passType == "adult") {
            passPrice = adultPrice;
        } else {
            passPrice = seniorPrice;
        }
    }

}
