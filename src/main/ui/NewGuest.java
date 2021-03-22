package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGuest extends ApplicationButtons {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 200;
    private JDialog creatingNewGuest = new JDialog();
    private JPanel mainPanel = new JPanel();
    private JPanel buttonPane = new JPanel(new GridLayout(0, 1));
    //creates the labels
    private JLabel success = new JLabel("", SwingConstants.CENTER);
    private JLabel name = new JLabel(guestName);
    private JLabel age = new JLabel(guestAge);
    //Strings for the labels
    private static String guestName = "Name: ";
    private static String guestAge = "Age: ";
    //Create the text fields and set them up.
    private final JTextField userNameText = new JTextField(20);
    private JTextField ageText = new JTextField(20);


    public NewGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);

    }


    public String getUserNameTextInput() {
        String nameInput = userNameText.getText();
        return nameInput;
    }

    public String getAgeTextInput() {
        String ageInput = ageText.getText();
        return ageInput;
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
        creatingNewGuest.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        creatingNewGuest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        creatingNewGuest.setVisible(true);
        creatingNewGuest.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        creatingNewGuest.setTitle("Create New Guest");
        //creatingNewGuest.pack();

        // lay out success message on  successPane
        JPanel successPane = new JPanel(new GridLayout(0, 1));
        success.setBounds(10, 10, 300, 25);
        successPane.add(success);

        //Lay out the field labels in a labelPane.
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(name);
        labelPane.add(age);

        //TODO used formatted text field demo to do this - boxes aren't ideal size and may need to
        // indicate what kind of inputs ie string for name and int for age

        //Layout the text fields in a mainPanel.
        JPanel textPane = new JPanel(new GridLayout(0, 1));
        textPane.add(userNameText);
        textPane.add(ageText);

        //create buttons
        createCreateGuestButton();
        createMainMenuButton();


        //Put the panels in this mainPanel, labels on left, text fields on right, buttons on bottom
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(successPane, BorderLayout.NORTH);
        mainPanel.add(labelPane, BorderLayout.CENTER);
        mainPanel.add(textPane, BorderLayout.LINE_END);
        mainPanel.add(buttonPane, BorderLayout.SOUTH);

    }

    private class NewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createNewGuest();

        }
    }

    //MODIFIES: this
    //EFFECTS: creates the buttons for the create new guest dialog box
    private void createCreateGuestButton() {
        CreateGuestAction action = new CreateGuestAction();
        JButton createGuestButton = new JButton(action);
        //Layout the buttons on buttonPane
        buttonPane.add(createGuestButton);
        createGuestButton.setText("Create Guest");
        createGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = new String(getUserNameTextInput());
                String age = new String(getAgeTextInput());
                if (name.equals("")) {
                    success.setText("Invalid Input for Name Field!");
                } else if (age.equals("")) {
                    success.setText("Invalid Input for Age Field!");
                } else {
                    action.putValue("Guest Name", name);
                    action.putValue("Age", age);
                }
            }
        });
    }

    public void createMainMenuButton() {
        JButton mainMenuButton = new JButton("Main Menu");
        buttonPane.add(mainMenuButton);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNewGuest.dispose();
            }
        });
    }

}
