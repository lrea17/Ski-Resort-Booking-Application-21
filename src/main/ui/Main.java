package ui;

import ski.reservation.application.model.Guest;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //TODO below was just for a test
        //TODO need to call my ski reservation app once built

        Guest guest1 = new Guest("Lindsay", 28);
        System.out.println(guest1.hasAPass());

        // want a list of all the guests
        ArrayList<Guest> listOfGuests = new ArrayList<Guest>();


    }
}
