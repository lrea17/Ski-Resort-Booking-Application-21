package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the main window in which the space invaders
 * game is played
 */

public class SkiAppGUI extends JFrame {

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
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        //createTools();
        //addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


}
