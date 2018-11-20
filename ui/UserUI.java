package ui;

import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUI {
    private static List<User> users = new ArrayList<>();
    private static Scanner userInput;
//    public static boolean userLogInBoolean;
//    public static boolean whileCreateUser;
    public static Frame fieldFrame;
    private static int buttonFontSize = 16;
    private static int labelFontSize = 18;

    // MODIFIES: this
    // EFFECTS: adds user to field users
    public static void addUser(User user){
        users.add(user);
    }

    public static void loggingInGUI() throws IOException {
    SaveLoadUsers.load("users.txt");

    File file = new File("C:\\Users\\Heather DeHaven\\Pictures\\Saved Pictures\\astronomyhubble.jpg");
    BufferedImage image = ImageIO.read(file);

    JLabel pictureLabel = new JLabel((new ImageIcon(image)));

    JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        frame.setSize( 1050, 900 );
        frame.setVisible( true );
        frame.setContentPane(pictureLabel);
        fieldFrame = frame;

    // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

    Object[] options = {"Logging In", "Creating Account"};

    JLabel telescopeLabel = new JLabel("Welcome to Telescope");
    telescopeLabel.setFont(new Font("Arial", Font.BOLD, labelFontSize));

    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,buttonFontSize)));

        JOptionPane login = new JOptionPane();
    int n = login.showOptionDialog(frame,
            telescopeLabel,
            "Astronomy Exploration",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        if (n == 0)
            loggingIn();
        else if (n == 1)
            creatingUser();
}

    //EFFECTS: start logging in interaction; calls exploreApp if findingUser is successful
    private static void loggingIn() throws IOException {
        JOptionPane loggingIn = new JOptionPane();
        JLabel label = new JLabel("Enter your username");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));
        String userName = loggingIn.showInputDialog(null, label);

        StellarObjectUI.displayGUIOptions(findingUser(userName));
    }

    //EFFECTS: returns found user; if user doesnt exist sends person back to beginning
    private static User findingUser(String username) throws IOException {
        User user = new User(username);
        if (users.contains(user)){
            return users.get(users.indexOf(user));
        }
        else {
            System.out.println("Username does not exist. Please try again or create a user.");
            //userLogIn();
            return null;}
    }

    private static void creatingUser() throws IOException {
        JOptionPane creatingUser = new JOptionPane();

        JLabel label = new JLabel("What would you like your username to be?");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        String name = creatingUser.showInputDialog(null,label);

        User user = new User(name);
        alreadyExists(user);
        SaveLoadUsers.save("users.txt");

        Object[] options ={"Explore!"};
        JLabel exploreLabel = new JLabel("Now that you are logged in, you can explore the app.");
        label.setFont(new Font("Arial", Font.BOLD, labelFontSize));

        int n = creatingUser.showOptionDialog(null, exploreLabel
        , "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (n == 0)
            StellarObjectUI.displayGUIOptions(user);
    }

    //MODIFIES: this
    //EFFECTS: checks if users already contains user, if not, add user
    private static void alreadyExists(User user) throws IOException {
        if (users.contains(user)){
            System.out.println("That username already exists. Try another.");
            creatingUser();
        }
        else addUser(user);
    }

    //MODIFIES: this
    //EFFECTS: asks if user wants to add to haveSeen or wantToSee
    public static void addingToLists(User user) throws IOException {
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to add to the list of Stellar Objects you have seen " +
                "or the list of Stellar Objects you want to see?");
        System.out.println("Remember, you can only add to have seen if it has been added to want to see!");
        answer = userInput.nextLine();

        addingToUsersFieldLists(user, answer);
    }

    //MODIFIES: this
    //EFFECTS: asks if user would like to add galaxy or starcon
    private static void addingToUsersFieldLists(User user, String answer) throws IOException {
        String input = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to add a Galaxy or Star Constellation?");
        input = userInput.nextLine();

        if (input.equals("galaxy")) {
            addingGalaxyToList(user, answer);
        }
        else if (input.equals("star constellation")) {
            addingStarConToList(user, answer);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds selected galaxy to selected list (answer)
    private static void addingGalaxyToList(User user, String answer) throws IOException {
        String input = "";
        userInput = new Scanner(System.in);
        ListOfGalaxy log = new ListOfGalaxy("Used");

        log.printList();
        System.out.println("Which Galaxy? Indicate by inputted the location in the list.");

        input = userInput.next();
        Galaxy g = StellarObjectUI.retrieveGalaxy(input, log);

        addingToTrackerList(answer, user, g);
    }

    //MODIFIES: this
    //EFFECTS: adds selected starCon to selected list (answer)
    private static void addingStarConToList(User user, String answer) throws IOException {
        String input = "";
        userInput = new Scanner(System.in);
        ListOfStarConstellation losc = new ListOfStarConstellation("Used");
        losc.printList();

        System.out.println("Which Star Constellation? Indicate by inputted the location in the list.");

        input = userInput.next();
        StarConstellation sc = StellarObjectUI.retrieveStarCon(input, losc);

        addingToTrackerList(answer, user, sc);
    }

    //MODIFIES: this
    //EFFECTS: adds selected stellarObject to selected list (answer)
    private static void addingToTrackerList(String answer, User user, StellarObject sc) throws IOException {
        if (answer.equals("have seen")){
            user.addStellarObjectHaveSeen(sc);
            System.out.println("All set!");
            more(user);
        }
        else if (answer.equals("want to see")) {
            user.addStellarObjectWantToSee(sc);
            System.out.println("All set!");
            more(user);
        }
    }

    //MODIFIES: this, users.txt
    //EFFECTS: restarts if user wants to add more; saves ones they have added if not
    private static void more(User user) throws IOException {
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to add more?");
        answer = userInput.next();
        if (answer.equals("yes")){
            addingToLists(user);
        }
        else {
            SaveLoadUsers.save("users.txt");
            StellarObjectUI.displayGUIOptions(user);}
    }

    //getter
    public static List<User> getUsers() {
        return users;
    }
}
