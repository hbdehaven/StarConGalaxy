package ui;

import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserUI {
    public static List<User> users = new ArrayList<>();
    private static Scanner userInput;
    public static boolean userLogInBoolean;
    public static boolean whileCreateUser;

    public static void addUser(User user){
        users.add(user);
    }

    public static void userLogIn() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        userLogInBoolean = true;
        load("users.txt");

        while (userLogInBoolean){
            System.out.println("Hi! Need to create user or logging in again?");
            answer = scanner.nextLine();
            firstQuestionUser(answer);
        }
    }

    private static void firstQuestionUser(String answer) throws IOException {
        if(answer.equals("create user")){
            createUser();
        }
        else if (answer.equals("logging in again") | answer.equals("logging in")){
            loggingIn();
            System.out.println("Great!");
        }
    }

    private static void loggingIn() throws IOException {
        userInput = new Scanner(System.in);
        String userName = "";

        System.out.println("What is your username?");
        userName = userInput.nextLine();

        StellarObjectUI.exploreApp(findingUser(userName));
    }

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
            addUser(user);
            save("users.txt");
            System.out.println("Now that you are logged in, you can explore the app.");
            StellarObjectUI.exploreApp(user);
        }
    }

    private static void alreadyExists(User user) throws IOException {
        if (users.contains(user)){
            System.out.println("That username already exists. Try another.");
            createUser();
        }
    }

    public static void load(String filename) throws IOException {
        List<String> usersFromFile = Files.readAllLines(Paths.get(filename));
        for (String userLog : usersFromFile){
            ArrayList<String> partsOfLine = splitOnRegex(userLog, ",");
            String userName = partsOfLine.get(0);
            List<StellarObject> haveSeen = parseListsOfStellarObjects(partsOfLine.get(1));
            List<StellarObject> wantToSee = parseListsOfStellarObjects(partsOfLine.get(2));
            User user = new User(userName);
            user.setHaveSeen(haveSeen);
            user.setWantToSee(wantToSee);
            users.add(user);
        }
    }

    // EFFECTS: creates the users in lists of haveSeenofUsers and wantToSeeofUsers of Stellar Objects
    private static List<User> instantiateUsers(String line){
        ArrayList<String> parts = splitOnRegex(line, "-");
        List<User> listOfUser = new ArrayList<>();
        for (String userLog : parts){
            ArrayList<String> partsOfLine = splitOnRegex(userLog, "-");
            String userName = partsOfLine.get(0);
            List<StellarObject> haveSeen = parseListsOfStellarObjects(partsOfLine.get(1));
            List<StellarObject> wantToSee = parseListsOfStellarObjects(partsOfLine.get(2));
            User user = new User(userName);
            user.setHaveSeen(haveSeen);
            user.setWantToSee(wantToSee);
            listOfUser.add(user);
        }
        return listOfUser;
    }

    private static List<StellarObject> parseListsOfStellarObjects(String stellarObjects) {
        List<StellarObject> l = new ArrayList<>();
        ArrayList<String> parts = splitOnRegex(stellarObjects, "~");
        for(String s : parts) {
            ArrayList<String> stellarObjProperties = splitOnRegex(s, "-");
            if (stellarObjProperties.size() == (3)){
                Galaxy.Type enumTypeProperty = stringToEnum(stellarObjProperties.get(1));
                StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(2));

                // realized i didn't parse for haveSeenofUsers and wantToSeeofUsers

//                List<User> haveSeenGalaxyofUsers = instantiateUsers(stellarObjProperties.get(3));
//                List<User> wantToSeeGalaxyofUsers = instantiateUsers(stellarObjProperties.get(4));

                Galaxy galaxy = new Galaxy(stellarObjProperties.get(0),enumTypeProperty, enumLocationProperty);
                l.add(galaxy);
//                galaxy.setHaveSeenofUsers(haveSeenGalaxyofUsers);
//                galaxy.setWantToSeeofUsers(wantToSeeGalaxyofUsers);
//                set this galaxy's haveseen and wanttosee lists of users
                // TODO: parse for haveSeenofUsers and wantToSeeofUsers
            }
            else if (stellarObjProperties.size() == 4) {
                StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(1));

                String starName = stellarObjProperties.get(3);
                Double brightness = Double.parseDouble(stellarObjProperties.get(4));
                Double distance = Double.parseDouble(stellarObjProperties.get(5));

                l.add(new StarConstellation(stellarObjProperties.get(0),enumLocationProperty,
                        stellarObjProperties.get(2), (new Star(starName, brightness, distance))));
                // TODO: parse for haveSeenofUsers and wantToSeeofUsers
            }
        }
        return l;
    }

    private static StellarObject.Location stringToEnumLocation(String property){
        if (property.equals("NORTH")){
            return StellarObject.Location.NORTH;
        }
        else if (property.equals("SOUTH")){
            return StellarObject.Location.SOUTH;
        }
        else return StellarObject.Location.BOTH;
    }

    private static Galaxy.Type stringToEnum(String property){
        if (property.equals("Elliptical")){
            return Galaxy.Type.ELLIPTICAL;
        }
        else if (property.equals("Irregular")){
            return Galaxy.Type.IRREGULAR;
        }
        else return Galaxy.Type.SPIRAL;
    }

    private static ArrayList<String> splitOnRegex(String line, String regex){
        String[] splits = line.split(regex);
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static void save(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for (User u: users){
//            writer.println(u.getName() + "," + retrieveTrackingLists(writer,u.getHaveSeenofUsers())
//                    + "," + retrieveTrackingLists(writer, u.getWantToSeeofUsers()));
            // TODO: how do i save them the corresponding symbols in load???
            // not saving how i want it to lol
        }
        writer.close();
    }

    private static void retrieveTrackingLists(PrintWriter writer, List<StellarObject> stellarObjects){
        for (StellarObject so: stellarObjects){
            if (so instanceof Galaxy){
                writer.println(so.getName() + "-" + ((Galaxy) so).getType() + "-" + so.getLocation() +
                        so.getHaveSeenofUsers() + "-" + so.getWantToSeeofUsers() +  "~");
            }
            if (so instanceof StarConstellation){
                writer.println(so.getName() + "-" + so.getLocation() + "-" + ((StarConstellation) so).getSymbolism()
                + "-" + so.getLocation() + so.getHaveSeenofUsers() + "-" + so.getWantToSeeofUsers() + "~");
            }
        }
        writer.close();
    }

    // TODO: user interaction for adding so into wantToSeeofUsers and haveSeenofUsers;
    // also adding a feature to see all users who have seen that so and want to see it; will be in StellarObjectUI




}
