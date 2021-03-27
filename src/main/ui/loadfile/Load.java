package ui.loadfile;

import ui.ApplicationButtons;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Load extends ApplicationButtons {
    public Load(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new LoadClickHandler());

    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load Work from File");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class LoadClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Load.playSound(getClickSound());
        }
    }
}
