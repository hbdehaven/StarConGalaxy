package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUI {
    private static List<User> users = new ArrayList<>();
    public static Frame fieldFrame;
    private static int labelFontSize = 20;

    // MODIFIES: this
    // EFFECTS: adds user to field users
    public static void addUser(User user){
        users.add(user);
    }

    private static void readyFrame() throws IOException {
        SaveLoadUsers.load("users.txt");

        File file = new File("C:\\Users\\Heather DeHaven\\Pictures\\Saved Pictures\\astronomyhubble.jpg");
        BufferedImage image = ImageIO.read(file);

        JLabel pictureLabel = new JLabel((new ImageIcon(image)));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setSize( 1200, 1000 );
        frame.setVisible( true );
        frame.setContentPane(pictureLabel);
        fieldFrame = frame;

        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    public static void loggingInGUI() throws IOException {
        readyFrame();

        logInOrCreate();
    }

    private static void logInOrCreate() throws IOException {
        Object[] options = {"Logging In", "Creating Account"};

        JLabel telescopeLabel = new JLabel("Welcome to Telescope");
        telescopeLabel.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,18)));

        JOptionPane login = new JOptionPane();
        int n = login.showOptionDialog(fieldFrame,
                telescopeLabel,
                "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0) {
            loggingIn();
            login.setVisible(false);
        }
        else if (n == 1) {
            creatingUser();
            login.setVisible(false);}
    }


    //EFFECTS: start logging in interaction; calls exploreApp if findingUser is successful
    private static void loggingIn() throws IOException {
        JOptionPane loggingIn = new JOptionPane();
        JLabel label = new JLabel("Enter your Username");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        String userName = loggingIn.showInputDialog(fieldFrame, label, "");

        if ((userName != null) && (userName.length()>0)) {
            User user = findingUser(userName);
            if (user == null)
                return;
            StellarObjectUI.displayGUIOptions(user);
        }
        else logInOrCreate();
    }

    //EFFECTS: returns found user; if user doesnt exist sends person back to beginning
    private static User findingUser(String username) throws IOException {
        User user = new User(username);
        if (users.contains(user)){
            return users.get(users.indexOf(user));
        }
        else {
            Object[] options = {"Okay"};
            JOptionPane noUser = new JOptionPane();
            JLabel label = new JLabel("Username Does Not Exist");
            label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

            int n = noUser.showOptionDialog(null,
                    label,
                    "Astronomy Exploration",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (n == 0)
                logInOrCreate();
                noUser.setVisible(false);
            return null;}
    }

    private static void creatingUser() throws IOException {
        JOptionPane creatingUser = new JOptionPane();

        JLabel label = new JLabel("What would you like your username to be?");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        String name = creatingUser.showInputDialog(fieldFrame,label);

        if ((name != null) && (name.length()>0)) {
            User user = new User(name);
            if (alreadyExists(user)){
                creatingUser();
                return;
            }
            SaveLoadUsers.save("users.txt");

            Object[] options ={"Explore!"};
            JLabel exploreLabel = new JLabel("Now that you are logged in, you can explore the app.");
            label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

            int n = creatingUser.showOptionDialog(fieldFrame, exploreLabel
                    , "Astronomy Exploration",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (n == 0)
                StellarObjectUI.displayGUIOptions(user);
        }
        else logInOrCreate();
    }

    //MODIFIES: this
    //EFFECTS: checks if users already contains user, if not, add user
    private static boolean alreadyExists(User user) throws IOException {
        if (users.contains(user)){
            Object[] options = {"Okay"};
            JOptionPane noUser = new JOptionPane();
            JLabel alreadyExists = new JLabel("Username Already Exists. Try Another.");
            alreadyExists.setFont(new Font("Arial", Font.BOLD, labelFontSize));
            int n = noUser.showOptionDialog(fieldFrame,
                    alreadyExists,
                    "Astronomy Exploration",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
        return true;
        }
        else addUser(user);
        return false;
    }

    //getter
    public static List<User> getUsers() {
        return users;
    }

    public static void pickList(User user){
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,18)));

        JLabel lists = new JLabel("Which list would you like to add to?");
        lists.setFont(new Font("Arial", Font.BOLD, 20));

        Object[] options = {"Have Seen", "Want to See"};
        JOptionPane list = new JOptionPane();
        int n = list.showOptionDialog(fieldFrame, lists,
                "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        addingToUserListGUI(user, n);
    }

    private static void addingToUserListGUI(User user, int answer){
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,18)));

        JLabel stellarObject = new JLabel("Would you like to add a Galaxy or Star Constellation?");
        stellarObject.setFont(new Font("Arial", Font.BOLD, 20));

        Object[] options = {"Galaxy", "Star Constellation"};
        JOptionPane list = new JOptionPane();
        int n = list.showOptionDialog(fieldFrame,
                stellarObject,
                "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0){
            addingGalaxyListGUI(user, answer);
        }
        else addingStarConListGUI(user, answer);
    }

    private static void addingGalaxyListGUI(User user, int answer){
        ListOfGalaxy log = new ListOfGalaxy("LOG");
        JPanel buttonPanel = whichGGUI(user, log, answer);

        selectGUIPanel("Which Galaxy?", buttonPanel, user);
    }

    private static void addingStarConListGUI(User user, int answer){
        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
        JPanel buttonPanel = whichSCGUI(user, losc, answer);

        selectGUIPanel("Which Star Constellation?", buttonPanel, user);
    }

    private static JPanel whichGGUI(User user, ListOfGalaxy log, int answer){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7, 7, 7, 7));

        JLabel label = new JLabel("Galaxies:");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPane.add(label);

        for (Galaxy g: log) {
            JButton button = new JButton(g.getName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, labelFontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (answer == 0){
                        user.addStellarObjectHaveSeen(g);
                        getOkay();
                }
                    else {
                        user.addStellarObjectWantToSee(g);
                        getOkay();
                    }
                }
            });
        }
        buttonPanel.add(buttonPane);

        return buttonPanel;
    }

    private static JPanel whichSCGUI(User user, ListOfStarConstellation losc, int answer){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7, 7, 7, 7));

        JLabel label = new JLabel("Star Constellations:");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPane.add(label);

        for (StarConstellation sc : losc) {
            JButton button = new JButton(sc.getName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, labelFontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (answer == 0){
                        user.addStellarObjectHaveSeen(sc);
                        getOkay();
                    }
                    else {
                        user.addStellarObjectWantToSee(sc);
                        getOkay();
                    }
                }
            });
        }
        buttonPanel.add(buttonPane);

        return buttonPanel;
    }

    private static void selectGUIPanel(String name, JPanel buttonPanel, User user){
        JFrame frame = new JFrame(name);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);

        JButton back = getBackButton(frame, user);

        panel.add(back, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(fieldFrame);
        frame.setVisible(true);
    }

    private static JButton getBackButton(JFrame frame, User user) {
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, labelFontSize));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveLoadUsers.save("users.txt");
                } catch (IOException e1) {
                    // do nothing
                }
                frame.dispose();
                StellarObjectUI.displayGUIOptions(user);
            }
        });
        return back;
    }

    private static void getOkay(){
        JOptionPane okay = new JOptionPane();

        JLabel set = new JLabel("All Set!");
        set.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        okay.showMessageDialog(fieldFrame, set);
    }



}
