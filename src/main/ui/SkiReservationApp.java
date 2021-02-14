package ui;

import ski.reservation.application.model.Accounts;
import ski.reservation.application.model.Guest;

import java.util.Scanner;

import static ski.reservation.application.model.Accounts.*;


// Ski Reservation application
public class SkiReservationApp {
    private Accounts accounts;
    private Scanner input;

    //EFFECTS: runs the ski reservation application
    public SkiReservationApp() {
        runSkiReservationApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runSkiReservationApp() {
        boolean keepGoing = true;
        String command = null;

        init();

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
    // EFFECTS: initializes accounts (list of guests)
    private void init() {
        input = new Scanner(System.in);
        accounts = new Accounts();
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

    // REQUIRES: guestName has a non-zero length and age is a
    //           non-zero length between 0 - 15
    // MODIFIES: this
    // EFFECTS: conducts a creation of a new guests & books them a reservation
    private void doNewGuest() {
        System.out.println("Please enter the guests name:");
        String guestName = input.next();
        System.out.println("Please enter the guests age");
        int guestAge = input.nextInt();
        if (guestAge > 0 && guestAge < 150) {
            Guest newGuest = new Guest(guestName, guestAge);
            addGuest(newGuest);
            System.out.println("New account created for: " + guestName);
            System.out.println("account ID: " + newGuest.getID());
            System.out.println("age: " + guestAge);
            System.out.println("pass type: " + newGuest.getPassType());
            newGuest.makeReservation();
            System.out.println("\nA reservation has been made for " + guestName + ". They may hit the slopes!");
        } else {
            System.out.println("Invalid input!");
        }
    }

    // MODIFIES: this
    // EFFECTS: books a reservation for an existing guest
    private void doNewReservationExistingGuest() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        if (lookupGuest(guestId) == null || !(getListOfGuests().contains(guestId))) {
            System.out.println("This guest does not exist in our system...");
        } else {
            lookupGuest(guestId).makeReservation();
            System.out.println("\nA reservation has been made for "
                    + lookupGuest(guestId).getName() + ". They may hit the slopes!");
        }
    }

    // MODIFIES: this
    // EFFECTS: cancels and existing reservation for a guest
    private void doCancelReservation() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        if (lookupGuest(guestId) == null || !(getListOfGuests().contains(guestId))) {
            System.out.println("This guest does not exist in our system...");
        } else if (lookupGuest(guestId).getListOfExpiredPasses().size() == 0) {
            System.out.println("This guest does not have any previous reservations.");
        } else {
            lookupGuest(guestId).cancelReservation();
            System.out.println("Cancellation successful for " + lookupGuest(guestId).getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes the account of an existing guests
    private void doDeleteGuestAccount() {
        String yesNo = null;
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        if (lookupGuest(guestId) == null) {
            System.out.println("This guest does not exist in our system...");
        } else {
            System.out.println("Are you sure you would like to proceed? (y / n)");
            System.out.println("These changes are permanent and cannot be undone");
            yesNo = input.next();
            if (yesNo.equals("y")) {
                removeGuest(lookupGuest(guestId));
                System.out.println("\nThe account requested has been deleted permanently");
            } else {
                System.out.println("Action cancelled, returning to main menu.");
            }
        }
    }
}
