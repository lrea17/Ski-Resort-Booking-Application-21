package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateGuestAction extends AbstractAction {
    private String nameString;
    private String ageString;
    private NewGuestPopUp newGuestPopUp;


    //TODO how do i make this so that it only "does something" if the guest name field
    // and age field were filled in correctly? - i don't want it doing this action EVERY time the button is
    // is clicked - it should be an "only if" its clicked && meets criteria

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(this.getValue("Guest Name"));
        //System.out.println(this.getValue("Age"));
        //System.out.println("this test works");
        if (checkValueNameString() == "") {
            newGuestPopUp.setSuccessMessage("Invalid Input for Name Field!");
            System.out.println("name field invalid");
        } else if (checkValueAgeString() == "") {
            newGuestPopUp.setSuccessMessage("Invalid Input for Age Field!");
            System.out.println("age value invalid");
        } else {
            System.out.println("everything worked right");
            System.out.println(checkValueNameString());
            System.out.println(checkValueAgeString());
        }
    }

    public String checkValueNameString() {
        if (this.getValue("Guest Name") == null) {
            nameString = "...";
        } else {
            nameString = this.getValue("Guest Name").toString();
        }
        return nameString;
    }

    public String checkValueAgeString() {
        if (this.getValue("Age") == null) {
            ageString = "...";
        } else {
            ageString = this.getValue("Age").toString();
        }
        return ageString;
    }

}
