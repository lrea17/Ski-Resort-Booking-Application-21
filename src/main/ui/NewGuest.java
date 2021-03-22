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
    private JDialog creatingNewGuest = new JDialog();
    private JPanel mainPanel = new JPanel();
    //creates the labels
    private JLabel name = new JLabel(guestName);
    private JLabel age = new JLabel(guestAge);
    //Create the text fields and set them up.
    private final JTextField userNameText = new JTextField(20);
    private JTextField ageText = new JTextField(20);


    public NewGuest(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);

    }

    public JTextField getUserNameText() {
        return userNameText;
    }

    public String getUserNameTextInput() {
        String input = userNameText.getText();
        return String.valueOf(input);
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

        //Lay out the labels in a mainPanel.
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(name);
        labelPane.add(age);

        //TODO used formatted text field demo to do this - boxes aren't ideal size and may need to
        // indicate what kind of inputs ie string for name and int for age

        //Layout the text fields in a mainPanel.
        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(userNameText);
        fieldPane.add(ageText);

        //Create buttons
        CreateGuestAction action = new CreateGuestAction();
        JButton createGuestButton = new JButton(action);
        createGuestButton.setText("Create Guest");
        createGuestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String test = new String(userNameText.getText());
                action.putValue("Guest Name", test);
            }
        });
        JButton mainMenuButton = new JButton("Main Menu");

        //Layout the buttons on buttonPane
        JPanel buttonPane = new JPanel(new GridLayout(0,1));
        buttonPane.add(createGuestButton);
        buttonPane.add(mainMenuButton);

        //Put the panels in this mainPanel, labels on left, text fields on right, buttons on bottom
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(labelPane, BorderLayout.CENTER);
        mainPanel.add(fieldPane, BorderLayout.LINE_END);
        mainPanel.add(buttonPane,BorderLayout.SOUTH);

    }

    private void testDialogBox() {
        String userName = JOptionPane.showInputDialog("Guest Name", "Age");
        System.out.println(userName);
        //String age = JOptionPane.showInputDialog("Age");
   /*     CreateGuestAction action = new CreateGuestAction();
        action.putValue("Guest Name", userName);
        JButton createGuestButton = new JButton(action);
        createGuestButton.setText("Create Guest");*/

    }

    private class NewGuestClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createNewGuest();
            //testDialogBox();

        }
    }
}
