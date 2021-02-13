package ui;

import ski.reservation.application.model.Guest;

import java.util.Scanner;

//TODO build out my UI

// Ski Reservation application
public class SkiReservationApp {
    private Guest name;
    private Guest age;
    private Scanner input;

    //EFFECTS: runs the ski reservation application
    public SkiReservationApp() {
        runSkiReservationApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input and acts on it
    private void runSkiReservationApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("n")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n Hello! Welcome to Wintery Mountain. Please select from the options below");
        System.out.println("\tn -> create new guest");
        System.out.println("\tr -> make a new reservation for existing guest");
        System.out.println("\tc -> cancel an existing reservation");
        System.out.println("\td -> delete a guest account");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            createNewGuest();
        }

    }

    private void doNewGuest() {
        Guest newGuest = createNewGuest();
        System.out.println("Please enter the guests name:");
        String guestName = input.next();
        System.out.println("Please enter the guests age");
        int guestAge = input.nextInt();

    }

    private void doNewReservationExistingGuest() {

    }

    private void doCancelReservation() {

    }

    private void doDeleteGuestAccount() {

    }

    // MODIFIES: this
    // EFFECTS: conducts the create new guest command
    private Guest createNewGuest() {
        return null;
    }


}
