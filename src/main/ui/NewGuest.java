package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGuest extends ApplicationButtons {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 200;
    //Strings for the labels
    private static String guestName = "Name: ";
    private static String guestAge = "Age: ";



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

        creatingNewGuest.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        creatingNewGuest.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        creatingNewGuest.setVisible(true);
        creatingNewGuest.add(panel);

        panel.setLayout(new BorderLayout());

        creatingNewGuest.setTitle("Create New Guest");
        //creatingNewGuest.pack();

        //creates the labels
        JLabel name = new JLabel(guestName);
        JLabel age = new JLabel(guestAge);

        //Create the text fields and set them up.
        JTextField userNameText = new JTextField(20);
        JTextField ageText = new JTextField(20);

        //Lay out the labels in a panel.
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        labelPane.add(name);
        labelPane.add(age);

        //TODO used formatted text field demo to do this - boxes aren't ideal size and may need to
        // indicate what kind of inputs ie string for name and int for age
        //Layout the text fields in a panel.
        JPanel fieldPane = new JPanel(new GridLayout(0,1));
        fieldPane.add(userNameText);
        fieldPane.add(ageText);

        JButton doneButton = new JButton("Done");
        //button.setBounds(30, 80, 50, 25);

        //Put the panels in this panel, labels on left,
        //text fields on right.
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(labelPane, BorderLayout.CENTER);
        panel.add(fieldPane, BorderLayout.LINE_END);
        panel.add(doneButton, BorderLayout.AFTER_LAST_LINE);

        //TODO maybe look at getting rid of this button and just have it so you hit enter and it does what you want
    }

    private class NewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createNewGuest();

        }
    }
}
