package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class ApplicationButtons {

    protected JButton button;
    protected SkiAppGUI editor;

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
    // EFFECTS:  customizes the button used for this tool
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

}
