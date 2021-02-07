package ui;

import model.Guest;

public class Main {
    public static void main(String[] args) {

        Guest guest1 = new Guest("Lindsay", 28);
        System.out.println(guest1.checkForPass());

    }
}
