package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the main window in which the space invaders
 * game is played
 */

public class SkiAppGUI extends JFrame implements ActionListener {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    private List<ApplicationButtons> buttons;

    public SkiAppGUI() {
        super("Ski Application");
        initializeGraphics();

    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new FlowLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createButtons();
        //addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new JLabel("Welcome to Snowy Mountain Resort"));
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        //activeTool = null;
        //currentDrawing = null;
        buttons = new ArrayList<ApplicationButtons>();
    }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instantiates all buttons
    private void createButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.CENTER);

        ApplicationButtons newGuest = new NewGuest(this, buttonArea);
        buttons.add(newGuest);

        ApplicationButtons cancelRes = new CancelReservation(this, buttonArea);
        buttons.add(cancelRes);

        ApplicationButtons saveWork = new Save(this, buttonArea);
        buttons.add(saveWork);

        ApplicationButtons loadWork = new Load(this, buttonArea);
        buttons.add(loadWork);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
