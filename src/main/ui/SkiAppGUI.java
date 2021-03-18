package ui;

import javax.swing.*;
import java.awt.*;

/*
 * Represents the main window in which the space invaders
 * game is played
 */

public class SkiAppGUI extends JFrame {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;

    public SkiAppGUI() {
        super("Ski Application");
        initializeGraphics();

    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        //createTools();
        //addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
