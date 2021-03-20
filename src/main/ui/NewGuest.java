package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGuest extends ApplicationButtons implements ActionListener  {

    public NewGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Create New Guest");
        button = customizeButton(button);
        addToParent(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
