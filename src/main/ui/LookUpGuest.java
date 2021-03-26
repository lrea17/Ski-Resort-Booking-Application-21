package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookUpGuest extends ApplicationButtons {
    private SkiAppGUI editor;

    public LookUpGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
        this.editor = editor;
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LookUpNewGuestClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {

    }

    private class LookUpNewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
