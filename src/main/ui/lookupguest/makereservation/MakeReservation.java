package ui.lookupguest.makereservation;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookUpGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// makes a reservation for the guest that has been looked up
public class MakeReservation extends ApplicationButtons {
    private SkiAppGUI editor;
    private Guest guest;

    //EFFECTS: creates make reservation button on the lookup guest pop up. When clicked
    //         it makes a "reservation" for the guest by adding a pass to their list of
    //         used passes
    public MakeReservation(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new MakeResClickHandler());

    }

    // EFFECTS: creates a make reservation button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Make Reservation");
        button = customizeButton(button);
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: sets this guests to the input guest
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    // MODIFIES: LookUpGuestPopUp
    // EFFECTS: click handler for cancel reservation button
    private class MakeResClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            guest.makeReservation();
            LookUpGuestPopUp.setSuccessMessage("Reservation booked for " + guest.getName());

        }
    }
}
