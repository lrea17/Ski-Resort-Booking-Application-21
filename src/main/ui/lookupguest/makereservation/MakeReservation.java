package ui.lookupguest.makereservation;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookUpGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ski.model.Accounts.lookupGuest;
import static ui.lookupguest.LookUpGuestPopUp.*;

// make a reservation for the guest that has been looked up
public class MakeReservation extends ApplicationButtons {
    private SkiAppGUI editor;
    private Guest guest;

    public MakeReservation(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new MakeResClickHandler());

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Make Reservation");
        button = customizeButton(button);
        addToParent(parent);
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    private class MakeResClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            guest.makeReservation();
            LookUpGuestPopUp.setSuccessMessage("Reservation booked for " + guest.getName());

        }
    }

    /*// MODIFIES: this
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
    }*/
}
