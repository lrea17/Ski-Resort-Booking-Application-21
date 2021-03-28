package ui.savefile;

import persistence.JsonWriter;
import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Save extends ApplicationButtons {
    private JComponent parent;

    public Save(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.parent = parent;
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
            editor.statusOfSaveJOptionPane("Accounts saved successfully!", "Success Saving Message");
        } catch (FileNotFoundException e) {
            playSound(getErrorSound());
            editor.statusOfSaveJOptionPane("Unable to write to file: " + jsonStore, "Error Saving Message");
            JOptionPane.showMessageDialog(parent, "Unable to write to file: " + jsonStore);
        }
    }





}
