package ui.savefile;

import persistence.JsonWriter;
import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// enables user to save to file
public class Save extends ApplicationButtons {
    private JComponent parent;

    public Save(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.parent = parent;
    }


    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new SaveClickHandler());
    }


    // EFFECTS: creates save button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save Work to File");
        button = customizeButton(button);
        addToParent(parent);
    }

    // EFFECTS: click handler for save button
    private class SaveClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Save.playSound(getClickSound());
            editor.setSidePanel(editor.createPhotoPanel());
            saveChangesToFile();
        }
    }

    // EFFECTS: saves the accounts to file
    private void saveChangesToFile() {
        JsonWriter writer = editor.getJsonWriter();
        String jsonStore = editor.getJsonStore();
        try {
            writer.open();
            writer.write(editor.getAccounts());
            writer.close();
            System.out.println("Saved " + editor.getAccounts().getName() + " to " + jsonStore);
            editor.statusOfSaveJOptionPane("Accounts saved successfully!", "Success Saving Message");
        } catch (FileNotFoundException e) {
            playSound(getErrorSound());
            editor.statusOfSaveJOptionPane("Unable to write to file: " + jsonStore, "Error Saving Message");
            JOptionPane.showMessageDialog(parent, "Unable to write to file: " + jsonStore);
        }
    }





}
