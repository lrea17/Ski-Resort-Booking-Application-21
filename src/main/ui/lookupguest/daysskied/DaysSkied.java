package ui.lookupguest.daysskied;

import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// view days skied/passes for the guest that has been looked up
public class DaysSkied extends ApplicationButtons {
    private SkiAppGUI editor;

    public DaysSkied(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new DaysSkiedClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Used Passes");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class DaysSkiedClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
        }
    }
}


