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
        //System.out.println(this.getValue("Guest Name"));
        //System.out.println(this.getValue("Age"));
        //System.out.println("this test works");
        if (checkValueNameString() == "no name") {
            System.out.println("name field invalid");
        } else if (checkValueAgeString() == "no age") {
            System.out.println("age value invalid");
        } else {
            //TODO here is where I put what i want it to do on the back end !!!
            System.out.println("everything worked right");
            System.out.println(checkValueNameString());
            System.out.println(checkValueAgeString());
        }
    }

    public String checkValueNameString() {
        if (this.getValue("Guest Name") == null) {
            nameString = "no name";
        } else {
            nameString = this.getValue("Guest Name").toString();
        }
        return nameString;
    }

    public String checkValueAgeString() {
        if (this.getValue("Age") == null) {
            ageString = "no age";
        } else {
            ageString = this.getValue("Age").toString();
        }
        return ageString;
    }

}
