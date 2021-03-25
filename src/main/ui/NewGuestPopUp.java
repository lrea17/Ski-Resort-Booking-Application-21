package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private CreateGuestAction action = new CreateGuestAction();
    private JButton createGuestButton = new JButton(action);
    private JButton mainMenuButton = new JButton("Main Menu");


    //TODO THIS IS AT ITS MAX CHECKSTYLE LINES!!

    // EFFECTS: constructor for the new guest pop up window
    public NewGuestPopUp(SkiAppGUI editor) {
        // initializes graphics
        this.editor = editor;
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
                if (name == null || name.equals("")) {
                    success.setText("Invalid Input for Name Field!");
                } else if (age == null || age.equals("")) {
                    success.setText("Invalid Input for Age Field!");
                } else {
                    action.putValue("Guest Name", name);
                    action.putValue("Age", age);
                    addGuestInfoToEditor();
                    userNameText.setText("");
                    ageText.setText("");
                }
            }
        });
    }


    // MODIFIES: this
    // EFFECTS: creates main menu button and adds button to button pane
    public void createMainMenuButton() {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNewGuest.setVisible(false);
                editor.setVisible(true);
            }
        });
    }

    public void addGuestInfoToEditor() {
        JPanel guestInfoPane = new JPanel(new GridLayout(0, 1));
        //int newWidth = WIDTH + 200;
        //int newHeight = HEIGHT + 200;
        //mainPanel.setMinimumSize(new Dimension(newWidth,newHeight));
        JLabel title = new JLabel("Last Guest Created:");
        Font font = new Font("Arial", Font.BOLD,12);
        title.setFont(font);
        JLabel guestName = new JLabel("Name: " + getUserNameTextInput());
        JLabel guestAge = new JLabel("Age: " + getAgeTextInput());
        guestInfoPane.add(title);
        guestInfoPane.add(guestName);
        guestInfoPane.add(guestAge);
        editor.add(guestInfoPane, BorderLayout.EAST);
        //JLabel accountID = new JLabel("Account ID: " ) - add guest info her thats created when we create the guest
    }
}
