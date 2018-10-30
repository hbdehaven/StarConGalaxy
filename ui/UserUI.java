package ui;

import model.*;
import model.exceptions.NoUserFound;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserUI {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner userInput;
    public static boolean userLogInBoolean;
    public static boolean whileCreateUser;

    public static void addUser(User user){
        users.add(user);
    }

//    //ask what user name they have and find and pull it up
//    // then call explore app with that user passed in as parameter
//    public static void containsUser(String username){
//        User user = new User(username);
//        if (users.contains(user)){
//            System.out.println("Username already exists. Please create another");
//        }
//        if (!users.contains(user)){
//            users.add(user);
//            System.out.println("Great! Your user has successfully been created.");
//        }
//    }

    public static void userLogIn() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        userLogInBoolean = true;

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
            // TODO
            // ask what user name they have and find and pull it up
            // then call explore app with that user passed in as parameter
            System.out.println("Great!");
        }
    }

    private static void loggingIn() throws IOException {
        userInput = new Scanner(System.in);
        String userName = "";

        UserUI.load("users.txt");

        System.out.println("What is your username?");
        userName = userInput.nextLine();

        StellarObjectUI.exploreApp(UserUI.findingUser(userName));
    }

    private static User findingUser(String username) throws IOException {
        User user = new User(username);
        if (users.contains(user)){
            return user;
        }
        else {return null;}
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
            UserUI.addUser(user);
            UserUI.save("users.txt");
            System.out.println("Now that you are logged in, you can explore the app.");
            StellarObjectUI.exploreApp(user);
        }
    }

    private static void load(String filename) throws IOException {
        List<String> usersFromFile = Files.readAllLines(Paths.get(filename));
        for (String userLog : usersFromFile){
            ArrayList<String> partsOfLine = splitOnRegex(userLog, ",");
            String userName = partsOfLine.get(0);
            List<StellarObject> haveSeen = parseLists(partsOfLine.get(1));
            List<StellarObject> wantToSee = parseLists(partsOfLine.get(2));
            User user = new User(userName);
            user.setHaveSeen(haveSeen);
            user.setWantToSee(wantToSee);
            users.add(user);
        }
    }

    private static List<StellarObject> parseLists(String stellarObjects) {
        List<StellarObject> l = new ArrayList<>();
        ArrayList<String> parts = splitOnRegex(stellarObjects, "|");
        for(String s : parts) {
            ArrayList<String> stellarObjProperties = splitOnRegex(s, "-");
            if (stellarObjProperties.size() == (3)){
                Galaxy.Type enumTypeProperty = stringToEnum(stellarObjProperties.get(1));
                StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(2));
                l.add(new Galaxy(stellarObjProperties.get(0),enumTypeProperty, enumLocationProperty));
            }
            else if (stellarObjProperties.size() == 4) {
                StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(1));

                String starName = stellarObjProperties.get(3);
                Double brightness = Double.parseDouble(stellarObjProperties.get(4));
                Double distance = Double.parseDouble(stellarObjProperties.get(5));

                l.add(new StarConstellation(stellarObjProperties.get(0),enumLocationProperty,
                        stellarObjProperties.get(2), (new Star(starName, brightness, distance))));
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

    public static ArrayList<String> splitOnRegex(String line, String regex){
        String[] splits = line.split(regex);
        return new ArrayList<>(Arrays.asList(splits));
    }

    public static void save(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for (User u: users){
            writer.println(u.getName() + "," + u.getHaveSeen() + "," + u.getWantToSee());
        }
    }






}
