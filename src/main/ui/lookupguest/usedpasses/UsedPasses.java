package ui.lookupguest.usedpasses;

import ski.model.Guest;
import ski.model.Pass;
import ui.ApplicationButtons;
import ui.SkiAppGUI;
import ui.lookupguest.LookUpGuestPopUp;
import ui.lookupguest.LookupGuest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// view used passes for the guest that has been looked up
public class UsedPasses extends ApplicationButtons {
    private SkiAppGUI editor;
    private Guest guest;
    private JPanel usedPassesPane = new JPanel(new GridLayout(0, 1));
    private JDialog usedPassesDialog = new JDialog();
    private JScrollPane scrollPane = new JScrollPane();
    private JList listOfExpiredPass;
    private Color lightBlue = new Color(203, 247, 235);

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
            usedPassesPane.removeAll();
            getUsedPasses();
            //showUsedPasses();
        }
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void createUsedPassesDialogBox() {
        usedPassesDialog.setMinimumSize(new Dimension(200, 200));
        usedPassesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        usedPassesDialog.setVisible(true);
        usedPassesDialog.setLayout(new BorderLayout());
        usedPassesDialog.setTitle(guest.getName() + "'s Used Passes: ");
    }

    public void showUsedPasses() {
        createUsedPassesDialogBox();
        setScrollPaneSetUp();
        usedPassesPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVisible(true);
        usedPassesPane.setVisible(true);
        usedPassesDialog.add(usedPassesPane);
    }

    public void newLineListItems() {
        listOfExpiredPass = new JList(guest.getListOfExpiredPasses().toArray());
    }

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


    private void getUsedPasses() {
        if (guest.getListOfExpiredPasses().isEmpty()) {
            LookupGuest.playSound(LookupGuest.getErrorSound());
            LookUpGuestPopUp.setSuccessMessage(guest.getName() + " has no used passes!");
        } else {
            showUsedPasses();
        }
    }


}


