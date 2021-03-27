package ui.newguest;

import ski.model.Guest;
import ski.model.Pass;
import ui.SkiAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewGuestPopUp extends JPanel {
    private static final int WIDTH = 350;
    private static final int HEIGHT = 200;
    private SkiAppGUI editor;
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
    //TODO maybe i dont need the action class
    private NewGuestAction action = new NewGuestAction();
    private JButton createGuestButton = new JButton();
    private JButton mainMenuButton = new JButton("Main Menu");


    //TODO THIS IS AT ITS MAX CHECKSTYLE LINES!!

    // EFFECTS: constructor for the new guest pop up window
    public NewGuestPopUp(SkiAppGUI editor) {
        // initializes graphics
        this.editor = editor;
        initializeGraphics();

        // lay out success message on  successPane
        JPanel successPane = new JPanel(new GridLayout(0, 1));
        success = new JLabel("", SwingConstants.CENTER);
        success.setBounds(10, 10, 300, 25);
        successPane.add(success);

        //Lay out the field labels in a labelPane.
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(name);
        labelPane.add(age);

        //Layout the text fields in a mainPanel.
        JPanel textPane = new JPanel(new GridLayout(0, 1));
        textPane.add(userNameText);
        textPane.add(ageText);

        //create buttons
        JPanel buttonPane = new JPanel(new GridLayout(0, 1));
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
    // EFFECTS: initializes the graphics for the pop up window
    private void initializeGraphics() {
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
                NewGuest.playSound(NewGuest.getClickSound());
                String name = getUserNameTextInput();
                String age = getAgeTextInput();
                if (name == null || name.equals("")) {
                    success.setText("Invalid Input for Name Field!");
                    NewGuest.playSound(NewGuest.getErrorSound());
                } else if (age == null || age.equals("")) {
                    success.setText("Invalid Input for Age Field!");
                    NewGuest.playSound(NewGuest.getErrorSound());
                } else if (Integer.parseInt(age) < 0 || Integer.parseInt(age) > 150) {
                    success.setText("Invalid Input for Age Field!");
                } else {
                    doNewGuest();
                    System.out.println(getUserNameTextInput());
                    System.out.println(getAgeTextInput());
                    //action.putValue("Guest Name", name);
                    //action.putValue("Age", age);
                    //addGuestInfoToEditor(); - made new method!
                    userNameText.setText("");
                    ageText.setText("");
                }
            }
        });
    }

    // REQUIRES: guestName has a non-zero length and age is a
    //           non-zero length between 0 - 15
    // MODIFIES: this
    // EFFECTS: conducts a creation of a new guests & books them a reservation
    private void doNewGuest() {
        String name = getUserNameTextInput();
        int age = Integer.parseInt(getAgeTextInput());
        Guest newGuest = new Guest(name, age);
        newGuest.makeReservation();
        int id = newGuest.getID();
        String passType = newGuest.getPassType();
        ArrayList<Pass> usedPasses = newGuest.getListOfExpiredPasses();
        System.out.println(usedPasses);
        editor.getAccounts().addGuest(newGuest);
        addNewGuestInfoToEditor(name, id, age, passType, usedPasses);


    }



    // MODIFIES: this
    // EFFECTS: creates main menu button and adds button to button pane
    public void mainMenuButtonActionListener() {

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNewGuest.setVisible(false);
                editor.setVisible(true);
                NewGuest.playSound(NewGuest.getClickSound());
            }
        });
    }

    //TODO doing a test with this
    public void addNewGuestInfoToEditor(String name, int accountId, int age,
                                        String passType, ArrayList<Pass> expiredPasses) {
        editor.removePhotoPanel();
        JPanel guestInfoPane = new JPanel(new GridLayout(0, 1));
        //int newWidth = WIDTH + 200;
        //int newHeight = HEIGHT + 200;
        //mainPanel.setMinimumSize(new Dimension(newWidth,newHeight));
        JLabel title = new JLabel("Last Guest Created:");
        Font font = new Font("Arial", Font.BOLD, 12);
        title.setFont(font);
        JLabel guestName = new JLabel("Name: " + name);
        JLabel accountID = new JLabel("Account ID: " + accountId);
        JLabel guestAge = new JLabel("Age: " + age);
        JLabel pass = new JLabel("Pass Type: " + passType);
        JLabel usedPasses = new JLabel("Used passes: " + expiredPasses);
        guestInfoPane.add(title);
        guestInfoPane.add(guestName);
        guestInfoPane.add(accountID);
        guestInfoPane.add(guestAge);
        guestInfoPane.add(pass);
        guestInfoPane.add(usedPasses);
        editor.add(guestInfoPane, BorderLayout.EAST);
    }


}
