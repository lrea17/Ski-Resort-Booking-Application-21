package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGuest extends ApplicationButtons {
    private SkiAppGUI editor;

    public NewGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new NewGuestClickHandler());
    }

    // EFFECTS: creates button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Create New Guest");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class NewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editor.setVisible(false);
            new NewGuestPopUp(editor);
        }
    }


}
