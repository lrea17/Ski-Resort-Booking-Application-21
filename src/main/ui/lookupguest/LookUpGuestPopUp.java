package ui.lookupguest;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.CancelReservation;
import ui.SkiAppGUI;
import ui.loadfile.Load;
import ui.newguest.NewGuest;
import ui.savefile.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JLabel success;
    private JLabel id = new JLabel(guestID);
    //Strings for the labels
    private static String guestID = "Guest ID: ";
    //Create the text fields and set them up.
    private final JTextField idText = new JTextField(20);
    // Buttons
    private LookupGuestAction action = new LookupGuestAction();
    private JButton lookupButton = new JButton(action);
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
                String id = getIdTextInput();
                try {
                    // TODO here we try to look up our guest - i need to connect this to the back end no?
                    Guest currentGuest = lookupGuest(parseInt(id));
                    if (currentGuest == null) {
                        System.out.println("This guest does not exist in our system...");
                    } else {
                        //TODO here is where i need an if else case that check to see
                        // that this exists in our list of guests!!
                        action.putValue("Guest ID", id);
                        idText.setText("");
                    }
                } catch (NumberFormatException exception) {
                    success.setText("Invalid input for guest ID!");
                }
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: removes all original panes from the mainPanel
    public void removeAllPanes() {
        mainPanel.remove(successPane);
        mainPanel.remove(labelPane);
        mainPanel.remove(textPane);
        mainPanel.remove(buttonPane);
    }

    public void guestLookupSuccessfulPanel() {
        removeAllPanes();
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        add(buttonArea, BorderLayout.CENTER);

        ApplicationButtons makeRes = new MakeReservation(this, buttonArea);
        buttons.add(makeRes);

        ApplicationButtons cancelRes = new CancelReservation(this, buttonArea);
        buttons.add(cancelRes);

        ApplicationButtons daysSkied = new LookupGuest(this, buttonArea);
        buttons.add(daysSkied);

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
