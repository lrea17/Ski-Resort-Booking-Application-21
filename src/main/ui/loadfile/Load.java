package ui.loadfile;

import persistence.JsonReader;
import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// enables user to load from file
public class Load extends ApplicationButtons {
    private JComponent parent;

    public Load(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.parent = parent;
    }

    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new LoadClickHandler());

    }

    // EFFECTS: creates a load button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Work from File");
        button = customizeButton(button);
        addToParent(parent);
    }

    // EFFECTS: click handler for load button
    private class LoadClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Load.playSound(getClickSound());
            editor.setSidePanel(editor.createPhotoPanel());
            editor.setVisible(true);
            loadAccountsFromFile();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from file
    private void loadAccountsFromFile() {
        JsonReader reader = editor.getJsonReader();
        try {
            editor.setAccounts(reader.read());
            editor.statusOfSaveJOptionPane("Accounts loaded successfully!",
                    "Success Loading Message");
        } catch (IOException e) {
            editor.statusOfSaveJOptionPane("Unable to read from file: " + editor.getJsonStore(),
                    "Error Loading Message");
            playSound(getErrorSound());
        }
    }
}
