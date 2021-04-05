package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public abstract class ApplicationButtons {

    protected JButton button;
    protected SkiAppGUI editor;
    private static String  click = "/Users/lindsayrea/IdeaProjects/project_d5y0z/data/sounds/regular-click.wav";
    private static String error = "/Users/lindsayrea/IdeaProjects/project_d5y0z/data/sounds/error-button.wav";

    //EFFECTS: creates an abstract class for any buttons in the GUI.  This requires
    //         all buttons to have the same features
    public ApplicationButtons(SkiAppGUI editor, JComponent parent) {
        this.editor = editor;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    // EFFECTS: adds a listener for this button
    protected abstract void addListener();

    // EFFECTS: creates button and activates it
    protected abstract void createButton(JComponent parent);

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this action
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);

        return button;
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }


    // MODIFIES: this
    // EFFECTS: enables buttons to play sound when clicked
    public static void playSound(String soundName) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // EFFECTS: gets click sound
    public static String getClickSound() {
        return click;
    }

    // EFFECTS: gets error sound
    public static String getErrorSound() {
        return error;
    }


}
