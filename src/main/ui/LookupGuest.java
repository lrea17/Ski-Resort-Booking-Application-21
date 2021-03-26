package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookupGuest extends ApplicationButtons {
    private SkiAppGUI editor;

    public LookupGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LookupNewGuestClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Lookup Guest");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class LookupNewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
