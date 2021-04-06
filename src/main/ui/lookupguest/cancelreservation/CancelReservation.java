package ui.lookupguest.cancelreservation;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookupGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// cancel a reservation for the guest that has been looked up
public class CancelReservation extends ApplicationButtons {
    private Guest guest;

    //EFFECTS: creates cancel reservation button on the lookup guest pop up. When clicked
    //         it removes the most recently used pass from the guests list of used passes,
    //         this is effectively canceling a "reservation"
    public CancelReservation(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new CancelResClickHandler());
    }

    // EFFECTS: creates a cancel reservation button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Cancel Existing Reservation");
        button = customizeButton(button);
        addToParent(parent);
    }

    // EFFECTS: click handler for cancel reservation button
    private class CancelResClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            doCancelReservation();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets this guests to the input guest
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    // MODIFIES: LookUpGuestPopUp
    // EFFECTS: cancels and existing reservation for a guest
    private void doCancelReservation() {
        if (guest.getListOfExpiredPasses().size() == 0) {
            LookupGuestPopUp.setSuccessMessage(guest.getName() + " does not have any previous reservations!");
            LookupGuest.playSound(LookupGuest.getErrorSound());
        } else {
            guest.cancelReservation();
            LookupGuestPopUp.setSuccessMessage("Cancellation successful for " + guest.getName());
        }
    }
}
