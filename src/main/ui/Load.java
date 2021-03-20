package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Load extends ApplicationButtons implements ActionListener {
    public Load(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Previous Work from File");
        button = customizeButton(button);
        addToParent(parent);
    }
}
