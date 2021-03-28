package ui.lookupguest.usedpasses;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookUpGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// view used passes for the guest that has been looked up
public class UsedPasses extends ApplicationButtons {
    private SkiAppGUI editor;
    private Guest guest;

    public UsedPasses(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new UsedPassesClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Used Passes");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class UsedPassesClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            getUsedPasses();
        }
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    private void getUsedPasses() {
        if (guest.getListOfExpiredPasses() == null) {
            LookUpGuestPopUp.setSuccessMessage(guest.getName() + " has no used passes");
            LookupGuest.playSound(getErrorSound());
        } else {
            System.out.println(guest.getListOfExpiredPasses());
        }

    }

}


