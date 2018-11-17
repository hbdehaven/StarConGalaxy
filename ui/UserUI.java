package ui;

import model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUI {
    private static List<User> users = new ArrayList<>();
    private static Scanner userInput;
    public static boolean userLogInBoolean;
    public static boolean whileCreateUser;

    // MODIFIES: this
    // EFFECTS: adds user to field users
    public static void addUser(User user){
        users.add(user);
    }

    //MODIFIES: this
    //EFFECTS: loads users; starts initial user interaction
    public static void userLogIn() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        userLogInBoolean = true;
        SaveLoadUsers.load("users.txt");

        while (userLogInBoolean){
            System.out.println("Hi! Need to create user or logging in again?");
            answer = scanner.nextLine();
            firstQuestionUser(answer);
        }
    }

    //MODIFIES: this
    //EFFECTS: helper method leading to creating or logging in
    private static void firstQuestionUser(String answer) throws IOException {
        if(answer.equals("create user")){
            createUser();
        }
        else if (answer.equals("logging in again") | answer.equals("logging in")){
            loggingIn();
            System.out.println("Great!");
        }
    }

    //EFFECTS: start logging in interaction; calls exploreApp if findingUser is successful
    private static void loggingIn() throws IOException {
        userInput = new Scanner(System.in);
        String userName = "";

        System.out.println("What is your username?");
        userName = userInput.nextLine();

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
            userLogIn();
            return null;}
    }

    //MODIFIES: this
    //EFFECTS: creates new user and adds to field users through alreadyExists
    //         once successful, send to exploreApp
    private static void createUser() throws IOException {
        userInput = new Scanner(System.in);
        String name = "";
        whileCreateUser = true;

        while (whileCreateUser) {
            System.out.println("Welcome!");
            System.out.println("What would you like your username to be?");
            name = userInput.nextLine();
            User user = new User(name);
            alreadyExists(user);
            SaveLoadUsers.save("users.txt");
            System.out.println("Now that you are logged in, you can explore the app.");
            StellarObjectUI.displayGUIOptions(user);
        }
    }

    //MODIFIES: this
    //EFFECTS: checks if users already contains user, if not, add user
    private static void alreadyExists(User user) throws IOException {
        if (users.contains(user)){
            System.out.println("That username already exists. Try another.");
            createUser();
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
