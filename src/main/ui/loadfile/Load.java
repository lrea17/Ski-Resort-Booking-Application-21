package ui.loadfile;

import persistence.JsonReader;
import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Load extends ApplicationButtons {
    private JComponent parent;
    private final int xaxis = 450;
    private final int yaxis = 250;

    public Load(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.parent = parent;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LoadClickHandler());

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Work from File");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class LoadClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Load.playSound(getClickSound());
            loadAccountsFromFile();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
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
