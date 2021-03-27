package ui.savefile;

import persistence.JsonWriter;
import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

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
            saveChangesToFile();
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveChangesToFile() {
        JsonWriter writer = editor.getJsonWriter();
        String jsonStore = editor.getJsonStore();
        try {
            writer.open();
            writer.write(editor.getAccounts());
            writer.close();
            System.out.println("Saved " + editor.getAccounts().getName() + " to " + jsonStore);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

}
