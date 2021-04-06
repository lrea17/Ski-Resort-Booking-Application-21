package ui.lookupguest;

import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// enables user to lookup guest by their account ID
public class LookupGuest extends ApplicationButtons {
    private SkiAppGUI editor;

    //EFFECTS: creates the button for lookup guest attached to the main menu
    public LookupGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new LookupNewGuestClickHandler());
    }

    // EFFECTS: creates button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Lookup Guest");
        button = customizeButton(button);
        addToParent(parent);
    }

    // MODIFIES: this, SkiAppGUI
    // EFFECTS: click handler for Create New Guest button, hides editor,
    //          opens pop up for lookup guest
    private class LookupNewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LookupGuestPopUp(editor);
            playSound(getClickSound());
            editor.setVisible(false);
        }
    }
}
