package ui.lookupguest.usedpasses;

import ski.model.Guest;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookUpGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// view used passes for the guest that has been looked up
public class UsedPasses extends ApplicationButtons {
    private SkiAppGUI editor;
    private Guest guest;
    private JPanel usedPassesPane = new JPanel(new GridLayout(0, 1));
    private JLabel usedPasses = new JLabel("Used passes: ");

    public UsedPasses(SkiAppGUI editor, JComponent parent) {
        super(editor, parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new UsedPassesClickHandler());
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Used Passes");
        button = customizeButton(button);
        addToParent(parent);
    }

    private class UsedPassesClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            LookupGuest.playSound(LookupGuest.getClickSound());
            getUsedPasses();
            showUsedPasses();
        }
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    //TODO add name of guest in
    public void showUsedPasses() {
        JDialog usedPassesPopUp = new JDialog();
        usedPassesPopUp.setMinimumSize(new Dimension(200,200));
        usedPassesPopUp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        usedPassesPopUp.setVisible(true);
        usedPassesPopUp.setLayout(new BorderLayout());
        usedPassesPopUp.setTitle(guest.getName() + "'s Used Passes: ");

        //TODO I dont think i need this it makes double title int he box
        //JLabel title = new JLabel(guest.getName() + "'s Used Passes: ");
        //Font font = new Font("Arial", Font.BOLD, 12);
        //title.setFont(font);
        //usedPassesPane.add(title);

        usedPassesPane.setBackground(Color.WHITE);

        usedPassesPane.add(usedPasses);
        usedPasses.setText("Used passes: " + guest.getListOfExpiredPasses());
        usedPassesPane.setVisible(true);

        usedPassesPopUp.add(usedPassesPane);
        //editor.setSidePanel(usedPassesPane);
    }

    private void getUsedPasses() {
        if (guest.getListOfExpiredPasses() == null) {
            LookUpGuestPopUp.setSuccessMessage(guest.getName() + " has no used passes");
            LookupGuest.playSound(getErrorSound());
        } else {
            System.out.println(guest.getListOfExpiredPasses());
        }

    }

}


