package ui.lookupguest.usedpasses;

import ski.model.Guest;
import ski.model.Pass;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookupGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// view used passes/previous reservations for the guest that has been looked up
public class UsedPasses extends ApplicationButtons {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private Guest guest;
    private JPanel usedPassesPane = new JPanel(new GridLayout(0, 1));
    private JDialog usedPassesDialog = new JDialog();
    private JScrollPane scrollPane = new JScrollPane();
    private JList listOfExpiredPass;
    private final int xaxis = 450;
    private final int yaxis = 250;

    //EFFECTS: creates used passes button on the lookup guest pop up. When clicked
    //         it opens a pop up displaying a list of the guests used passes
    public UsedPasses(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    // MODIFIES: this
    // EFFECTS:  associate button with new ClickHandler
    @Override
    protected void addListener() {
        button.addActionListener(new UsedPassesClickHandler());
    }

    // EFFECTS: creates a view used passes button and activates it
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Used Passes");
        button = customizeButton(button);
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS: sets this guests to the input guest
    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    // MODIFIES: LookUpGuestPopUp
    // EFFECTS: click handler for cancel reservation button
    private class UsedPassesClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            usedPassesPane.removeAll();
            getUsedPasses();
        }
    }


    // EFFECTS: creates the graphics for the used passes dialog box
    public void createUsedPassesDialogBox() {
        usedPassesDialog.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        usedPassesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        usedPassesDialog.setLocation(xaxis, yaxis);
        usedPassesDialog.setVisible(true);
        usedPassesDialog.setLayout(new BorderLayout());
        usedPassesDialog.setTitle(guest.getName() + "'s Used Passes: ");
    }

    // MODIFIES: this
    // EFFECTS: creates dialog box with scroll pane of guests used passes
    public void showUsedPasses() {
        createUsedPassesDialogBox();
        setScrollPaneSetUp();
        usedPassesPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVisible(true);
        usedPassesPane.setVisible(true);
        usedPassesDialog.add(usedPassesPane);
    }

    // MODIFIES: this
    // EFFECTS: sets list of expiredPass to be a Jlist
    public void newLineListItems() {
        listOfExpiredPass = new JList(guest.getListOfExpiredPasses().toArray());
    }


    // EFFECTS: creates scroll pane of used passes and adds it to used Passes pane
    public void setScrollPaneSetUp() {
        newLineListItems();

        listOfExpiredPass.setModel(new AbstractListModel() {

            private static final long serialVersionUID = 1L;

            ArrayList<Pass> strings = guest.getListOfExpiredPasses();

            public int getSize() {
                return strings.size();
            }

            public Object getElementAt(int i) {
                return (i + 1) + ": " + strings.get(i).toString();
            }
        });

        usedPassesPane.add(listOfExpiredPass);
        scrollPane.setViewportView(listOfExpiredPass);
    }


    // MODIFIES: this, LookUpGuestPopUp
    // EFFECTS: checks to see if the guest has any used passes, if empty it updates the success message
    //          on the LookUpGuestPopUp and plays failure sound, else opens dialog scroll box of passes
    private void getUsedPasses() {
        if (guest.getListOfExpiredPasses().isEmpty()) {
            LookupGuest.playSound(LookupGuest.getErrorSound());
            LookupGuestPopUp.setSuccessMessage(guest.getName() + " has no used passes!");
        } else {
            showUsedPasses();
        }
    }


}


