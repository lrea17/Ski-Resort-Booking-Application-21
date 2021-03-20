package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGuest extends ApplicationButtons {
    private int guestsNum = 0;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    //Strings for the labels
    private static String name = "Name: ";
    private static String rateString = "Age: ";



    public NewGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new NewGuestClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Create New Guest");
        button = customizeButton(button);
        // button.addActionListener(this); - maybe delete here got from youtube vid
        addToParent(parent);
    }

    private void createNewGuest() {
        JFrame creatingNewGuest = new JFrame();
        JPanel panel = new JPanel();

        creatingNewGuest.setMinimumSize(new Dimension(350, 200));
        creatingNewGuest.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        creatingNewGuest.setVisible(true);
        creatingNewGuest.add(panel);

        panel.setLayout(new FlowLayout());

        creatingNewGuest.setTitle("Create New Guest");
        creatingNewGuest.pack();

        JLabel name = new JLabel("Name");
        name.setBounds(10,20,80,15);
        panel.add(name);

        JTextField userNameText = new JTextField(20);
        userNameText.setBounds(100, 20, 165, 25);
        panel.add(userNameText);

        JLabel age = new JLabel("Age");
        age.setBounds(10, 50,80,15);
        panel.add(age);

        JTextField ageText = new JTextField(20);
        userNameText.setBounds(100, 50, 165, 25);
        panel.add(ageText);




    }

    private class NewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createNewGuest();

        }
    }
}
