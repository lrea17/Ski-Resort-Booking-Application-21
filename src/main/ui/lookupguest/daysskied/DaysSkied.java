package ui.lookupguest.daysskied;

import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DaysSkied extends ApplicationButtons implements ActionListener {
    private SkiAppGUI editor;

    public DaysSkied(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Used Passes");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


