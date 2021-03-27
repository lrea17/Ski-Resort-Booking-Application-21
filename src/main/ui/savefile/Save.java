package ui.savefile;

import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Save extends ApplicationButtons {
    public Save(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Work to File");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class SaveClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Save.playSound(getClickSound());
        }
    }
}
