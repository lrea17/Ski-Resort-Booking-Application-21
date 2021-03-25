package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateGuestAction extends AbstractAction {
    private String nameString;
    private String ageString;


    //TODO how do i make this so that it only "does something" if the guest name field
    // and age field were filled in correctly? - i don't want it doing this action EVERY time the button is
    // is clicked - it should be an "only if" its clicked && meets criteria

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getValue("Guest Name"));
        System.out.println(this.getValue("Age"));
        System.out.println("this test works");
    }

}
