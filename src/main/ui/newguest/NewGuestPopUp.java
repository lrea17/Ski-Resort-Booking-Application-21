package ui.newguest;

import ski.model.Guest;
import ski.model.Pass;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// creates dialog window when new guest button is clicked on editor
public class NewGuestPopUp extends JPanel {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 200;
    private final int xaxis = 450;
    private final int yaxis = 250;
    private int ageString;
    private SkiAppGUI editor;
    private JDialog creatingNewGuest = new JDialog();
    //panels
    private JPanel mainPanel;
    private JPanel guestInfoPane = new JPanel(new GridLayout(0, 1));
    private JPanel successPane = new JPanel(new GridLayout(0, 1));
    private JPanel labelPane = new JPanel(new GridLayout(0, 1));
    private JPanel buttonPane = new JPanel(new GridLayout(0, 1));
    private JPanel textPane = new JPanel(new GridLayout(0, 1));
    //creates the labels
    private JLabel success;
    private JLabel name = new JLabel(guestName);
    private JLabel age = new JLabel(guestAge);
    //Strings for the labels on pop up
    private static String guestName = "Name: ";
    private static String guestAge = "Age: ";
    // labels for editor panel
    private JLabel newGuestName = new JLabel("Name: ");
    private JLabel accountID = new JLabel("Account ID: ");
    private JLabel newGuestAge = new JLabel("Age: ");
    private JLabel pass = new JLabel("Pass Type: ");
    private JLabel usedPasses = new JLabel("Used passes: ");
    //creates text fields
    private final JTextField userNameText = new JTextField(20);
    private JTextField ageText = new JTextField(20);
    // creates buttons
    private JButton createGuestButton = new JButton();
    private JButton mainMenuButton = new JButton("Main Menu");

    public NewGuestPopUp(SkiAppGUI editor) {
        // initializes graphics
        this.editor = editor;
        initializeGraphics();

        // lay out success message on  successPane
        success = new JLabel("", SwingConstants.CENTER);
        success.setBounds(10, 10, 300, 25);
        success.setForeground(Color.RED);
        successPane.add(success);

        //Lay out the field labels in a labelPane.
        labelPane.add(name);
        labelPane.add(age);

        //Layout the text fields in a mainPanel.
        textPane.add(userNameText);
        textPane.add(ageText);

        //create buttons
        createGuestButton.setText("Create Guest");
        createGuestButtonActionListener();
        buttonPane.add(createGuestButton);
        mainMenuButtonActionListener();
        buttonPane.add(mainMenuButton);

        //Put the panels in this mainPanel, labels on left, text fields on right, buttons on bottom
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(successPane, BorderLayout.NORTH);
        mainPanel.add(labelPane, BorderLayout.CENTER);
        mainPanel.add(textPane, BorderLayout.LINE_END);
        mainPanel.add(buttonPane, BorderLayout.SOUTH);

        setAllPanesColor(editor.color);
    }

    // EFFECTS: returns the text input into the userNameText field
    public String getUserNameTextInput() {
        String nameInput;
        if (userNameText.getText().isEmpty()) {
            nameInput = null;
        } else {
            nameInput = userNameText.getText();
        }
        return nameInput;
    }


    // EFFECTS: returns the text input into the ageText field
    public String getAgeTextInput() {
        String ageInput;
        if (ageText.getText().isEmpty()) {
            ageInput = null;
        } else {
            ageInput = ageText.getText();
        }
        return ageInput;
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics for the creatingNewGuest dialog box
    private void initializeGraphics() {
        mainPanel = new JPanel();
        creatingNewGuest.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        creatingNewGuest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        creatingNewGuest.setLocation(xaxis, yaxis);
        creatingNewGuest.setVisible(true);
        creatingNewGuest.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        creatingNewGuest.setTitle("Create New Guest");
    }

    // MODIFIES: this
    // EFFECTS: sets the background color of all the panels to match
    public void setAllPanesColor(Color color) {
        mainPanel.setBackground(color);
        buttonPane.setBackground(color);
        textPane.setBackground(color);
        labelPane.setBackground(color);
        successPane.setBackground(color);
    }

    //MODIFIES: this
    //EFFECTS: action listener for create guest button, checks that text field inputs are
    //         valid, if so creates guest and resets fields, else gives error message and
    //         plays error sound
    private void createGuestButtonActionListener() {
        createGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGuest.playSound(NewGuest.getClickSound());
                String name = getUserNameTextInput();
                String age = getAgeTextInput();
                if (name == null || name.equals("")) {
                    success.setText("Invalid Input for Name Field!");
                    NewGuest.playSound(NewGuest.getErrorSound());
                } else if (age == null || age.equals("")) {
                    success.setText("Invalid Input for Age Field!");
                    NewGuest.playSound(NewGuest.getErrorSound());
                } else if (checkAgeInputIsInteger() < 0 || checkAgeInputIsInteger() > 150) {
                    success.setText("Invalid Input for Age Field!");
                    NewGuest.playSound(NewGuest.getErrorSound());
                } else {
                    doNewGuest();
                    resetInputs();
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: resets text inputs and success message on dialog box
    private void resetInputs() {
        success.setForeground(Color.BLACK);
        success.setText("Account created for: " + getUserNameTextInput());
        userNameText.setText("");
        ageText.setText("");
    }

    // MODIFIES: this
    // EFFECTS: creates new guests, adds pass to guest account
    //          (a pass being added is the same as having a reservation),
    private void doNewGuest() {
        String name = getUserNameTextInput();
        int age = Integer.parseInt(getAgeTextInput());
        Guest newGuest = new Guest(name, age);
        newGuest.makeReservation();
        int id = newGuest.getID();
        String passType = newGuest.getPassType();
        ArrayList<Pass> usedPasses = newGuest.getListOfExpiredPasses();
        editor.getAccounts().addGuest(newGuest);
        updateLabelsForMostRecentGuest(name, id, age, passType, usedPasses);
        showUpdatedGuestInfo();
    }


    // MODIFIES: this, SkiAppGUI
    // EFFECTS: creates click handler for main menu button and adds button to button pane,
    //          sets main editor visible with new guest info panel and closes new guest dialog box
    public void mainMenuButtonActionListener() {

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNewGuest.setVisible(false);
                editor.setVisible(true);
                NewGuest.playSound(NewGuest.getClickSound());
                guestInfoPane.setVisible(true);
            }
        });
    }


    // EFFECTS: checks to make sure that the age input is an integer
    public int checkAgeInputIsInteger() {
        try {
            ageString = Integer.parseInt(getAgeTextInput());
        } catch (NumberFormatException e) {
            ageString = -1;
        }
        return ageString;
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that displays the account info of the last guest added,
    //          adds this panel to the editor on main menu
    public void showUpdatedGuestInfo() {
        JLabel title = new JLabel("Last Guest Created:");
        Font font = new Font("Arial", Font.BOLD, 12);
        title.setFont(font);
        guestInfoPane.setBackground(Color.WHITE);
        guestInfoPane.add(title);
        guestInfoPane.add(newGuestName);
        guestInfoPane.add(accountID);
        guestInfoPane.add(newGuestAge);
        guestInfoPane.add(pass);
        guestInfoPane.add(usedPasses);
        editor.setSidePanel(guestInfoPane);

    }

    // MODIFIES: this
    // EFFECTS: creates a panel that can display the account info of the last guest added
    public void createRecentGuestInfoPane() {
        JLabel title = new JLabel("Last Guest Created:");
        Font font = new Font("Arial", Font.BOLD, 12);
        title.setFont(font);
        guestInfoPane.add(title);
        guestInfoPane.add(newGuestName);
        guestInfoPane.add(accountID);
        guestInfoPane.add(newGuestAge);
        guestInfoPane.add(pass);
        guestInfoPane.add(usedPasses);
        guestInfoPane.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: adds the updated information from the most recent guest added to the labels
    //          on the guest info pane
    public void updateLabelsForMostRecentGuest(String name, int accountId, int age,
                                               String passType, ArrayList<Pass> expiredPasses) {
        newGuestName.setText("Name: " + name);
        accountID.setText("Account ID: " + accountId);
        newGuestAge.setText("Age: " + age);
        pass.setText("Pass Type: " + passType);
        usedPasses.setText("Used passes: " + expiredPasses);
    }


}
