package ui.lookupguest.makereservation;

import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MakeReservation extends ApplicationButtons implements ActionListener {
    private SkiAppGUI editor;

    public MakeReservation(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    @Override
    protected void addListener() {

    }

    @Override
    protected void createButton(JComponent parent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
