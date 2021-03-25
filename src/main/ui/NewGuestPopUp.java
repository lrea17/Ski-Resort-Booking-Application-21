package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGuestPopUp extends JPanel {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 200;
    private JDialog creatingNewGuest = new JDialog();
    private JPanel mainPanel;
    //creates the labels
    private JLabel success;
    private JLabel name = new JLabel(guestName);
    private JLabel age = new JLabel(guestAge);
    //Strings for the labels
    private static String guestName = "Name: ";
    private static String guestAge = "Age: ";
    //Create the text fields and set them up.
    private final JTextField userNameText = new JTextField(20);
    private JTextField ageText = new JTextField(20);
    // Buttons
    private CreateGuestAction action = new CreateGuestAction();
    private JButton createGuestButton = new JButton(action);
    private JButton mainMenuButton = new JButton("Main Menu");

    // EFFECTS: constructor for the new guest pop up window
    public NewGuestPopUp() {
        // initializes graphics
        initializeGraphicsCreateNewGuest();

        // lay out success message on  successPane
        JPanel successPane = new JPanel(new GridLayout(0, 1));
        success = new JLabel("", SwingConstants.CENTER);
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
        JPanel buttonPane = new JPanel(new GridLayout(0, 1));
        createGuestButton.setText("Create Guest");
        createGuestButtonActionListener();
        buttonPane.add(createGuestButton);
        createMainMenuButton();
        buttonPane.add(mainMenuButton);

        //Put the panels in this mainPanel, labels on left, text fields on right, buttons on bottom
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(successPane, BorderLayout.NORTH);
        mainPanel.add(labelPane, BorderLayout.CENTER);
        mainPanel.add(textPane, BorderLayout.LINE_END);
        mainPanel.add(buttonPane, BorderLayout.SOUTH);
    }

    // EFFECTS: returns the text input into the userNameText field
    public String getUserNameTextInput() {
        String nameInput = null;
        if (userNameText.getText().isEmpty()) {
            // do nothing - nameInput stays null
        } else {
            nameInput = userNameText.getText();
        }
        return nameInput;
    }


    // EFFECTS: returns the text input into the ageText field
    public String getAgeTextInput() {
        String ageInput = null;
        if (ageText.getText().isEmpty()) {
            // do nothing - ageInput stays null
        } else {
            ageInput = ageText.getText();
        }
        return ageInput;
    }

    public void setSuccessMessage(String s) {
        success = new JLabel(s, SwingConstants.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics for the pop up window
    private void initializeGraphicsCreateNewGuest() {
        mainPanel = new JPanel();
        creatingNewGuest.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        creatingNewGuest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        creatingNewGuest.setVisible(true);
        creatingNewGuest.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        creatingNewGuest.setTitle("Create New Guest");
    }


    //TODO trying to find a way so that the action listener only works if it meet the criteria of having
    // text filled into both boxes
    // TODO try to move this action performed into create guest action
    //MODIFIES: this
    //EFFECTS: creates the buttons for the create new guest dialog box
    private void createGuestButtonActionListener() {
        createGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = getUserNameTextInput();
                String age = getAgeTextInput();
                if (name == null || name == "") {
                    success.setText("Invalid Input for Name Field!");
                } else if (age == null || age == "") {
                    success.setText("Invalid Input for Age Field!");
                } else {
                    action.putValue("Guest Name", name);
                    action.putValue("Age", age);
                    userNameText.setText("");
                    ageText.setText("");
                }
            }
        });
    }


/*    //MODIFIES: this
    //EFFECTS: creates the buttons for the create new guest dialog box
    private void createCreateGuestButton() {
        createGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = new String(getUserNameTextInput());
                String age = new String(getAgeTextInput());
                if (name != null && age != null) {
                    action.putValue("Guest Name", name);
                    action.putValue("Age", age);
                    userNameText.setText("");
                    ageText.setText("");
                } else if (name.equals("")) {
                    success.setText("Invalid Input for Name Field!");
                } else if (age.equals("")) {
                    success.setText("Invalid Input for Age Field!");
                }
            }
        });
    }*/

    // MODIFIES: this
    // EFFECTS: creates main menu button and adds button to button pane
    public void createMainMenuButton() {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNewGuest.setVisible(false);
            }
        });
    }
}
