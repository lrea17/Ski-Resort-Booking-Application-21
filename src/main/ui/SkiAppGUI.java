package ui;

import persistence.JsonReader;
import persistence.JsonWriter;
import ski.model.Accounts;
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
    private Accounts accounts;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JLayeredPane infoPanel2 = new JLayeredPane();
    private JPanel infoPanel = new JPanel();
    private JLabel photoPanel;
    private final int xaxis = 450;
    private final int yaxis = 250;
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private Color color = new Color(178, 217, 255);


    private List<ApplicationButtons> buttons;

    public SkiAppGUI() {
        super("Ski Mountain Booking System");
        accounts = new Accounts("Snowy Mountain");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initializeFields();
        initializeGraphics();

    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public String getJsonStore() {
        return JSON_STORE;
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new GridLayout(0, 2)); //TODO changed this to grid layout - mebe insert mountain photo
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocation(xaxis,yaxis);
        createButtons();
        createPhoto();
        //setUpInfoPanel();
        //TODO 1
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //guests = new JLabel("Number of guests: 0"); - don't think i need this
        this.pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the mountain photo and adds to main frame
    public void createPhoto() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new
                    File("/Users/lindsayrea/IdeaProjects/project_d5y0z/data/images/gondola.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        photoPanel = new JLabel(new ImageIcon(myPicture));

        infoPanel.add(photoPanel);
        add(infoPanel, BorderLayout.EAST);

        //photoPanel.setBounds(new Rectangle(new Point(0,0), photoPanel.getPreferredSize()));
        //photoPanel.setVisible(true);
        //photoPanel.setComponentOrientation();
    }

    //TODO 2
    public void setUpInfoPanel() {
        infoPanel2.add(photoPanel, JLayeredPane.PALETTE_LAYER);
        //infoPanel2.setBounds(0,0, photoPanel.getWidth(), photoPanel.getHeight());
        this.add(infoPanel2, BorderLayout.EAST);
        infoPanel2.setVisible(true);
    }

    public void setSidePanel(JPanel panel) {
        infoPanel.removeAll();

        infoPanel.add(panel);
        infoPanel.setVisible(true);

        //TODO 3
        //panel.setAlignmentX(0.5f);
        //panel.setAlignmentY(0.5f);
        //infoPanel2.add(panel, JLayeredPane.POPUP_LAYER);
        //infoPanel2.moveToBack(photoPanel);
    }

    public void removePhotoPanel() {
        remove(photoPanel);
    }

    public void photoPanelVisible() {
        add(photoPanel);
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

    //EFFECTS: creates a pop up window to verify an action is complete/incomplete
    public void statusOfSaveJOptionPane(String messageText, String title) {
        ImageIcon icon = null;
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new
                    File("/Users/lindsayrea/IdeaProjects/project_d5y0z/data/images/snowyMountainLogo2.0.jpg"));
            icon = new ImageIcon(myPicture);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(icon);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label);
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(new JLabel(messageText));
        textPanel.setBackground(color);
        JPanel panel2 = new JPanel(new GridLayout(0,2));
        panel2.add(textPanel, BorderLayout.WEST);
        panel2.add(panel, BorderLayout.EAST);
        JOptionPane.showMessageDialog(null, panel2, title, JOptionPane.DEFAULT_OPTION);
    }


}
