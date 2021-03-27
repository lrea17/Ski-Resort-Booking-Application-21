package ui.lookupguest.cancelreservation;

import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// cancel a reservation for the guest that has been looked up
public class CancelReservation extends ApplicationButtons {
    private SkiAppGUI editor;

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
        }
    }
}
