package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// will extend application buttons
public class CancelReservation extends ApplicationButtons implements ActionListener {
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
    public void actionPerformed(ActionEvent e) {

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
        }
    }
}
