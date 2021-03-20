package ui;

import javax.swing.*;

public abstract class ApplicationButtons {

    protected JButton button;
    protected SkiAppGUI editor;
    private boolean active; //TODO not sure if i need active

    public ApplicationButtons(SkiAppGUI editor, JComponent parent) {
        this.editor = editor;
        createButton(parent);
        addToParent(parent);
        //active = false;
        //addListener();
    }

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

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);




}
