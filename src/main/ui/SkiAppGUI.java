package ui;

import ui.loadfile.Load;
import ui.lookupguest.LookupGuest;
import ui.newguest.NewGuest;
import ui.savefile.Save;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the main window in which the space invaders
 * game is played
 */

public class SkiAppGUI extends JFrame {
    private static final String JSON_STORE = "./data/accounts.json";


    private JLabel guests;
    private JLabel photoPanel;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    private List<ApplicationButtons> buttons;

    public SkiAppGUI() {
        super("Ski Application");
        initializeFields();
        initializeGraphics();

    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new GridLayout(0, 2)); //TODO changed this to grid layout - mebe insert mountain photo
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createButtons();
        createPhoto();
        //addNewDrawing();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //guests = new JLabel("Number of guests: 0"); - don't think i need this
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the mountain photo and adds to main frame
    private void createPhoto() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new
                    File("/Users/lindsayrea/IdeaProjects/project_d5y0z/moountain2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        photoPanel = new JLabel(new ImageIcon(myPicture));
        add(photoPanel, BorderLayout.EAST);
    }

    public void removePhotoPanel() {
        remove(photoPanel);
    }

    // MODIFIES: this
    // EFFECTS:  instantiates buttons with ArrayList
    private void initializeFields() {
        buttons = new ArrayList();
    }

    // MODIFIES: this
    // EFFECTS: a helper method which declares and instantiates all buttons
    private void createButtons() {
        JPanel buttonArea = new JPanel();
        //TODO set colour scheme
        //buttonArea.setBackground(Color.GREEN);
        buttonArea.setLayout(new GridLayout(0, 1));
        buttonArea.setSize(new Dimension(2, 10));
        add(buttonArea, BorderLayout.CENTER);

        ApplicationButtons newGuest = new NewGuest(this, buttonArea);
        buttons.add(newGuest);

        ApplicationButtons lookUpGuest = new LookupGuest(this, buttonArea);
        buttons.add(lookUpGuest);

        ApplicationButtons saveWork = new Save(this, buttonArea);
        buttons.add(saveWork);

        ApplicationButtons loadWork = new Load(this, buttonArea);
        buttons.add(loadWork);
    }


}
