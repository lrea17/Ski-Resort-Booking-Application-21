package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import ski.model.Accounts;
import ski.model.Guest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static ski.model.Accounts.*;


// Ski Reservation application
public class SkiReservationApp {
    private static final String JSON_STORE = "./data/accounts.json";
    private Scanner input;
    private Accounts accounts;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the ski reservation application
    public SkiReservationApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        accounts = new Accounts("Snowy Mountain");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runSkiReservationApp();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runSkiReservationApp() {
        boolean keepGoing = true;
        String command;

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

        System.out.println("\nReminder: tell the guest to have a good ski day - Goodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n Hello! Welcome to Snowy Mountain. Please select from the options below");
        System.out.println("\tn -> create new guest");
        System.out.println("\ts -> lookup days skied for existing guest");
        System.out.println("\tr -> make a new reservation for existing guest");
        System.out.println("\tc -> cancel an existing reservation");
        System.out.println("\td -> delete a guest account");
        System.out.println("\tf -> save changes to accounts to file");
        System.out.println("\tl -> load accounts from file");
        System.out.println("\tq - > quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts (list of guests)
    private void init() {
        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            doNewGuest();
        } else if (command.equals("s")) {
            doDaysSkied();
        } else if (command.equals("r")) {
            doNewReservationExistingGuest();
        } else if (command.equals("c")) {
            doCancelReservation();
        } else if (command.equals("d")) {
            doDeleteGuestAccount();
        } else if (command.equals("l")) {
            loadAccountsFromFile();
        } else if (command.equals("f")) {
            saveChangesToFile();
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
            accounts.addGuest(newGuest);  //TODO added accounts to front of this so same as json
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
    // EFFECTS: looks up the guest and provides the number of days skied
    private void doDaysSkied() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        Guest currentGuest = lookupGuest(guestId);
        if (currentGuest == null) {
            System.out.println("This guest does not exist in our system...");
        } else {
            currentGuest.getNumberOfDaysSkied();
            System.out.println(currentGuest.getName() + " has skied: " + currentGuest.getNumberOfDaysSkied()
                    + " day(s) this season.");
        }
    }

    // MODIFIES: this
    // EFFECTS: books a reservation for an existing guest
    private void doNewReservationExistingGuest() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        Guest currentGuest = lookupGuest(guestId);
        if (currentGuest == null) {
            System.out.println("This guest does not exist in our system...");
        } else {
            currentGuest.makeReservation();
            System.out.println("\nA reservation has been made for "
                    + currentGuest.getName() + ". They may hit the slopes!");
        }
    }

    // MODIFIES: this
    // EFFECTS: cancels and existing reservation for a guest
    private void doCancelReservation() {
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        Guest currentGuest = lookupGuest(guestId);
        if (currentGuest == null) {
            System.out.println("This guest does not exist in our system...");
        } else if (currentGuest.getListOfExpiredPasses().size() == 0) {
            System.out.println("This guest does not have any previous reservations.");
        } else {
            currentGuest.cancelReservation();
            System.out.println("Cancellation successful for " + currentGuest.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes the account of an existing guests
    private void doDeleteGuestAccount() {
        String yesNo;
        System.out.println("Please enter the guests account ID:");
        int guestId = input.nextInt();
        if (lookupGuest(guestId) == null) {
            System.out.println("This guest does not exist in our system...");
        } else {
            System.out.println("Are you sure you would like to proceed? (y / n)");
            System.out.println("These changes are permanent and cannot be undone");
            yesNo = input.next();
            if (yesNo.equals("y")) {
                accounts.removeGuest(lookupGuest(guestId)); //TODO added this in
                loadAccountsFromFile(); // not sure if this is right either
                System.out.println("\nThe account requested has been deleted permanently");
            } else {
                System.out.println("Action cancelled, returning to main menu.");
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveChangesToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(accounts);
            jsonWriter.close();
            System.out.println("Saved " + accounts.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadAccountsFromFile() {
        try {
            accounts = jsonReader.read();
            System.out.println("Loaded " + accounts.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
