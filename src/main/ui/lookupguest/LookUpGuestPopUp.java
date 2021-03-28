package ui.lookupguest;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.cancelreservation.CancelReservation;
import ui.lookupguest.daysskied.DaysSkied;
import ui.lookupguest.makereservation.MakeReservation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static ski.model.Accounts.lookupGuest;

public class LookUpGuestPopUp extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
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
    //Strings for the labels
    private static String guestID = "Guest ID: ";
    //Create the text fields and set them up.
    private final JTextField idText = new JTextField(20);
    // Buttons
    private List<ApplicationButtons> buttons;
    //private LookupGuestAction action = new LookupGuestAction();
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
                        //action.putValue("Guest ID", id); - not sure i need this
                        idText.setText("");
                    }
                } catch (NumberFormatException exception) {
                    success.setText("Invalid input for guest ID!");
                }
            }
        });
    }

    public void sendGuest(Guest guest, ApplicationButtons button) {
        if (button instanceof MakeReservation) {
            ((MakeReservation) button).setGuest(guest);
        } else if (button instanceof CancelReservation) {
            ((CancelReservation) button).setGuest(guest);
        }
    }

    //MODIFIES: this
    //EFFECTS: makes mainpanel hidden and removes all original lookupGuest panes from the mainPanel
    public void removeAllPanes() {
        mainPanel.setVisible(false);
        //mainPanel.remove(successPane);
        mainPanel.remove(labelPane);
        mainPanel.remove(textPane);
        mainPanel.remove(buttonPane);
    }

    //MODIFIES: this
    //EFFECTS: makes mainPanel visible with new buttons for after a guest has been looked up by id
    public void guestLookupSuccessfulPanel(Guest guest) {
        removeAllPanes();
        mainPanel.setVisible(true);
        JPanel buttonArea = new JPanel();
        buttons = new ArrayList<>();

        success.setText(guest.getName() + "'s Account");

        buttonArea.setLayout(new GridLayout(0, 1));
        add(buttonArea, BorderLayout.CENTER);

        ApplicationButtons makeRes = new MakeReservation(this.editor, buttonArea);
        sendGuest(guest, (MakeReservation) makeRes);
        buttons.add(makeRes);

        ApplicationButtons cancelRes = new CancelReservation(this.editor, buttonArea);
        sendGuest(guest, (CancelReservation) cancelRes);
        buttons.add(cancelRes);

        ApplicationButtons daysSkied = new DaysSkied(this.editor, buttonArea);
        buttons.add(daysSkied);

        buttonArea.add(mainMenuButton);

        lookupGuest.setTitle("Guest Options");
        mainPanel.add(buttonArea);
    }

    // MODIFIES: this
    // EFFECTS: initializes the graphics for the pop up window
    private void initializeGraphics() {
        mainPanel = new JPanel();
        lookupGuest.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        lookupGuest.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        lookupGuest.setVisible(true);
        lookupGuest.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        lookupGuest.setTitle("Lookup Guest");
    }

    public static void setSuccessMessage(String message) {
        success.setText(message);
    }


    // MODIFIES: this
    // EFFECTS: creates main menu button and adds button to button pane
    public void mainMenuButtonActionListener() {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lookupGuest.setVisible(false);
                editor.setVisible(true);
                LookupGuest.playSound(LookupGuest.getClickSound());
            }
        });
    }

}
