package ui;

import ski.reservation.application.model.Guest;

import java.util.Scanner;

import static ski.reservation.application.model.Accounts.addGuest;
import static ski.reservation.application.model.Accounts.lookupGuest;

//TODO build out my UI

// Ski Reservation application
public class SkiReservationApp {
    //private static ArrayList<Guest> listOfGuests = new ArrayList<>();
    private Guest name;
    private Guest age;
    private Scanner input;

    //EFFECTS: runs the ski reservation application
    public SkiReservationApp() {
        runSkiReservationApp();
        //listOfGuests = Guest.getListOfGuests();
    }

    //MODIFIES: this
    //EFFECTS: processes user input and acts on it
    private void runSkiReservationApp() {
        boolean keepGoing = true;
        String command = null;

        //init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Reminder: tell the guest to have a good ski day - Goodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n Hello! Welcome to Wintery Mountain. Please select from the options below");
        System.out.println("\tn -> create new guest");
        System.out.println("\tr -> make a new reservation for existing guest");
        System.out.println("\tc -> cancel an existing reservation");
        System.out.println("\td -> delete a guest account");
        System.out.println("\tq - > quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            doNewGuest();
        } else if (command.equals("r")) {
            doNewReservationExistingGuest();
        } else if (command.equals("c")) {
            doCancelReservation();
        } else if (command.equals("d")) {
            doDeleteGuestAccount();
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // MODIFIES: this
    // EFFECTS: creates a new guests & books a reservation for them
    private void doNewGuest() {
        System.out.println("Please enter the guests name:");
        String guestName = input.next();
        System.out.println("Please enter the guests age");
        int guestAge = input.nextInt();
        Guest newGuest = new Guest(guestName, guestAge);
        addGuest(newGuest);
        newGuest.makeReservation();
    }

    private void doNewReservationExistingGuest() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        if (lookupGuest(guestId) == null) {
            System.out.println("This guest does not exist in our system...");
        } else {
            lookupGuest(guestId).makeReservation();
        }
    }

    private void doCancelReservation() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        if (lookupGuest(guestId) == null) {
            System.out.println("This guest does not exist in our system...");
        } else {
            lookupGuest(guestId).cancelReservation(lookupGuest(guestId).getCurrentPass());
        }
    }

    private void doDeleteGuestAccount() {

    }

    private void returnToMainMenu() {

    }

    // MODIFIES: this
    // EFFECTS: conducts the create new guest command
    private Guest createNewGuest() {

        return null;
    }




}
