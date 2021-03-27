package ui.lookupguest.makereservation;

import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// make a reservation for the guest that has been looked up
public class MakeReservation extends ApplicationButtons {
    private SkiAppGUI editor;

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

    private class MakeResClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
        }
    }
}
