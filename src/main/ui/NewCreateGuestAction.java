package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewCreateGuestAction extends AbstractAction {
    private String nameString;
    private String ageString;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkValueNameString().equals("no name")) {
            // this is a test so i know its doing what its suppose to
            System.out.println("name field invalid");
        } else if (checkValueAgeString().equals("no age")) {
            // this is a test so i know its doing what its suppose to
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
