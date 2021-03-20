package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Save extends ApplicationButtons implements ActionListener {
    public Save(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Work to File");
        button = customizeButton(button);
        addToParent(parent);
    }
}
