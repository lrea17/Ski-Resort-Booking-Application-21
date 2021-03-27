package ui.loadfile;

import persistence.JsonReader;
import ski.model.Accounts;
import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Load extends ApplicationButtons {
    public Load(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
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
            System.out.println("Loaded " + editor.getAccounts().getName() + " from " + editor.getJsonStore());
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + editor.getJsonStore());
        }
    }
}
