package ui;

import ski.reservation.application.model.Guest;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //TODO below was just for a test
        //TODO need to call my ski reservation app once built
        //TODO add guest to list of guests in UI
        // want a list of all the guests
        //ArrayList<Guest> listOfGuests = new ArrayList<Guest>();

        Guest guest1 = new Guest("Lindsay", 28);
        System.out.println(guest1.getID());
        Guest guest2 = new Guest("bob", 2);
        System.out.println(guest2.getID());







    }
}
