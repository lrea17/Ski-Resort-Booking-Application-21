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
    private JPanel panel1 = new JPanel();
    //creates the labels
    private JLabel name = new JLabel(guestName);
    private JLabel age = new JLabel(guestAge);
    //Create the text fields and set them up.
    private JTextField userNameText = new JTextField(20);
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
        creatingNewGuest.add(panel1);

        panel1.setLayout(new BorderLayout());

        creatingNewGuest.setTitle("Create New Guest");
        //creatingNewGuest.pack();

        //Lay out the labels in a panel1.
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        labelPane.add(name);
        labelPane.add(age);

        //TODO used formatted text field demo to do this - boxes aren't ideal size and may need to
        // indicate what kind of inputs ie string for name and int for age
        //Layout the text fields in a panel1.
        JPanel fieldPane = new JPanel(new GridLayout(0, 1));
        fieldPane.add(userNameText);
        fieldPane.add(ageText);

        //create buttons
        CreateGuestAction action = new CreateGuestAction();
        //TODO TRYING TO READ THE TEXT PUT INTO THE FIELD - NEED TO FIGURE OUT HOW TO GET THE TEXT AND PRINT IT OUT
        // https://stackoverflow.com/questions/5752307/how-to-retrieve-value-from-jtextfield-in-java-swing
        // maybe just add action listener to the createGuestButton?
        action.putValue("Guest Name", getUserNameTextInput());
        JButton createGuestButton = new JButton(action);
        createGuestButton.setText("Create Guest");
        JButton mainMenuButton = new JButton("Main Menu");

        //Layout the buttons on buttonPanel
        JPanel buttonPanel = new JPanel(new GridLayout(0,1));
        buttonPanel.add(createGuestButton);
        buttonPanel.add(mainMenuButton);



        //button.setBounds(30, 80, 50, 25);

        //Put the panels in this panel1, labels on left,
        //text fields on right.
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel1.add(labelPane, BorderLayout.CENTER);
        panel1.add(fieldPane, BorderLayout.LINE_END);
        panel1.add(buttonPanel,BorderLayout.SOUTH);

        //TODO maybe look at getting rid of this button and just have it so you hit enter and it does what you want
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
