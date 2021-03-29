package ui.lookupguest;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.cancelreservation.CancelReservation;
import ui.lookupguest.makereservation.MakeReservation;
import ui.lookupguest.usedpasses.UsedPasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ski.model.Accounts.lookupGuest;

//creates a dialog window with buttons after lookup guest is clicked on editor
public class LookUpGuestPopUp extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    // dimensions for jDialog screen location
    private final int xaxis = 450;
    private final int yaxis = 250;
    private SkiAppGUI editor;
    private JDialog lookupGuest = new JDialog();
    // panels
    private JPanel mainPanel;
    private JPanel successPane;
    private JPanel labelPane;
    private JPanel textPane;
    private JPanel buttonPane;
    //creates the labels
    private static JLabel success;
    private JLabel id = new JLabel(guestID);
    private static String guestID = "Guest ID: ";
    //Create the text fields and set them up.
    private final JTextField idText = new JTextField(20);
    // creates buttons
    private List<ApplicationButtons> buttons;
    private JButton lookupButton = new JButton();
    private JButton mainMenuButton = new JButton("Main Menu");

    public LookUpGuestPopUp(SkiAppGUI editor) {
        this.editor = editor;
        initializeGraphics();

        // lay out success message on  successPane
        successPane = new JPanel(new GridLayout(0, 1));
        success = new JLabel("", SwingConstants.CENTER);
        success.setBounds(10, 10, 300, 25);
        successPane.add(success);

        //Lay out the field labels in a labelPane.
        labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(id);

        //Layout the text fields in a mainPanel.
        textPane = new JPanel(new GridLayout(0, 1));
        textPane.add(idText);

        //create buttons
        buttonPane = new JPanel(new GridLayout(0, 1));
        lookupButton.setText("Lookup Guest");
        lookupButtonActionListener();
        buttonPane.add(lookupButton);
        mainMenuButtonActionListener();
        buttonPane.add(mainMenuButton);

        //Put the panels in this mainPanel, labels on left, text fields on right, buttons on bottom
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(successPane, BorderLayout.NORTH);
        mainPanel.add(labelPane, BorderLayout.CENTER);
        mainPanel.add(textPane, BorderLayout.LINE_END);
        mainPanel.add(buttonPane, BorderLayout.SOUTH);

        setAllPanesColor(editor.getColor());
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

    // EFFECTS: returns the text input into the ageText field
    public String getIdTextInput() {
        String idInput;
        if (idText.getText().isEmpty()) {
            idInput = null;
        } else {
            idInput = idText.getText();
        }
        return idInput;
    }

    //MODIFIES: this
    //EFFECTS: action listener for lookup guest button, checks that the input is valid,
    //         checks guest exists in accounts, if successful opens guest accounts page,
    //         else plays error sound and displays message
    private void lookupButtonActionListener() {
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LookupGuest.playSound(LookupGuest.getClickSound());
                String id = getIdTextInput();
                try {
                    Guest currentGuest = lookupGuest(parseInt(id));
                    if (currentGuest == null) {
                        success.setText("This guest does not exist in our system...");
                    } else {
                        guestLookupSuccessfulPanel(currentGuest);
                        idText.setText("");
                    }
                } catch (NumberFormatException exception) {
                    success.setText("Invalid input for guest ID!");
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: takes the guest looked up and makes their information available for the
    //          buttons on the guest account page
    public void sendGuest(Guest guest, ApplicationButtons button) {
        if (button instanceof MakeReservation) {
            ((MakeReservation) button).setGuest(guest);
        } else if (button instanceof CancelReservation) {
            ((CancelReservation) button).setGuest(guest);
        } else if (button instanceof UsedPasses) {
            ((UsedPasses) button).setGuest(guest);
        }
    }

    //MODIFIES: this
    //EFFECTS: makes mainPanel hidden and removes all original lookupGuest panes from the mainPanel
    public void removeAllPanes() {
        mainPanel.setVisible(false);
        mainPanel.remove(labelPane);
        mainPanel.remove(textPane);
        mainPanel.remove(buttonPane);
    }

    //MODIFIES: this
    //EFFECTS: makes mainPanel visible with new buttons for after a guest has been successfully
    //         looked up by id
    public void guestLookupSuccessfulPanel(Guest guest) {
        removeAllPanes();

        mainPanel.setVisible(true);
        JPanel buttonArea = new JPanel();
        buttonArea.setBackground(editor.getColor());
        buttons = new ArrayList<>();

        success.setText(guest.getName() + "'s Account");

        buttonArea.setLayout(new GridLayout(0, 1));
        add(buttonArea, BorderLayout.CENTER);

        ApplicationButtons makeRes = new MakeReservation(this.editor, buttonArea);
        sendGuest(guest, makeRes);
        buttons.add(makeRes);

        ApplicationButtons cancelRes = new CancelReservation(this.editor, buttonArea);
        sendGuest(guest, cancelRes);
        buttons.add(cancelRes);

        ApplicationButtons usedPasses = new UsedPasses(this.editor, buttonArea);
        sendGuest(guest, usedPasses);
        buttons.add(usedPasses);

        buttonArea.add(mainMenuButton);

        lookupGuest.setTitle("Guest Options");
        mainPanel.setBackground(editor.getColor());
        mainPanel.add(buttonArea);
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics for the look up guest dialog window
    private void initializeGraphics() {
        mainPanel = new JPanel();
        lookupGuest.setMinimumSize(new Dimension(WIDTH, (HEIGHT)));
        lookupGuest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        lookupGuest.setVisible(true);
        lookupGuest.add(mainPanel);
        lookupGuest.setLocation(xaxis, yaxis);
        mainPanel.setLayout(new BorderLayout());
        lookupGuest.setTitle("Lookup Guest");
    }

    // MODIFIES:this
    // EFFECTS: sets the success message that appears in main{anel
    public static void setSuccessMessage(String message) {
        success.setText(message);
    }


    // MODIFIES: this, SkiAppGUI
    // EFFECTS: creates click handler for main menu button and adds button to button pane,
    //          sets main editor visible and closes lookupGuest dialog box
    public void mainMenuButtonActionListener() {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupGuest.setVisible(false);
                editor.setSidePanel(editor.createPhotoPanel());
                editor.setVisible(true);
                LookupGuest.playSound(LookupGuest.getClickSound());
            }
        });
    }

}
