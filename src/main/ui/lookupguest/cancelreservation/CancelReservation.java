package ui.lookupguest.cancelreservation;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookUpGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ski.model.Accounts.lookupGuest;

// cancel a reservation for the guest that has been looked up
public class CancelReservation extends ApplicationButtons {
    private SkiAppGUI editor;
    private Guest guest;

    public CancelReservation(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new CancelResClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Cancel Existing Reservation");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class CancelResClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            doCancelReservation();
        }
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    // MODIFIES: this
    // EFFECTS: cancels and existing reservation for a guest
    private void doCancelReservation() {
        if (guest.getListOfExpiredPasses().size() == 0) {
            LookUpGuestPopUp.setSuccessMessage(guest.getName() + " does not have any previous reservations.");
        } else {
            guest.cancelReservation();
            LookUpGuestPopUp.setSuccessMessage("Cancellation successful for " + guest.getName());
        }
    }


}
