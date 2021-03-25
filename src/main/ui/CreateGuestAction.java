package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreateGuestAction extends AbstractAction {


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.getValue("Guest Name"));
        System.out.println(this.getValue("Age"));
        System.out.println("this test works");
    }

}
