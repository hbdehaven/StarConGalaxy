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
    private static List<User> users = new ArrayList<>();
    private static Scanner userInput;
    public static boolean userLogInBoolean;
    public static boolean whileCreateUser;

    // MODIFIES: this
    // EFFECTS: adds user to field users
    private static void addUser(User user){
        users.add(user);
    }

    //MODIFIES: this
    //EFFECTS: loads users; starts initial user interaction
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

        StellarObjectUI.exploreApp(findingUser(userName));
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
            save("users.txt");
            System.out.println("Now that you are logged in, you can explore the app.");
            StellarObjectUI.exploreApp(user);
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

    private static void load(String filename) throws IOException {
        List<String> usersFromFile = Files.readAllLines(Paths.get(filename));
        for (String userLog : usersFromFile){
            ArrayList<String> partsOfLine = splitOnRegex(userLog, ",");
            String userName = partsOfLine.get(0);
            User user = new User(userName);
            try {
                List<StellarObject> haveSeen = parseListsOfStellarObjects(partsOfLine.get(1));
                user.setHaveSeen(haveSeen);
            }
            catch (IndexOutOfBoundsException e){
                // do nothing
            }
            try {
                List<StellarObject> wantToSee = parseListsOfStellarObjects(partsOfLine.get(2));
                user.setWantToSee(wantToSee);
            }
            catch (IndexOutOfBoundsException e){
                // do nothing
            }
            users.add(user);
        }
    }

    private static List<StellarObject> parseListsOfStellarObjects(String stellarObjects) {
        List<StellarObject> list = new ArrayList<>();
        ArrayList<String> parts = splitOnRegex(stellarObjects, "~");
        for(String s : parts) {
            ArrayList<String> stellarObjProperties = splitOnRegex(s, "-");
            if (stellarObjProperties.size() == (3)){
                galaxyInstantiation(stellarObjProperties,list);
            }
            else if (stellarObjProperties.size() == 4) {
                starConInstantiation(stellarObjProperties,list);
            }
        }
        return list;
    }

    private static void galaxyInstantiation(ArrayList<String> stellarObjProperties, List<StellarObject> list){
        Galaxy.Type enumTypeProperty = stringToEnum(stellarObjProperties.get(1));
        StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(2));

        Galaxy galaxy = new Galaxy(stellarObjProperties.get(0),enumTypeProperty, enumLocationProperty);
        list.add(galaxy);
    }

    private static void starConInstantiation(ArrayList<String> stellarObjProperties, List<StellarObject> list){
        StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(1));

        String starName = stellarObjProperties.get(3);
        Double brightness = Double.parseDouble(stellarObjProperties.get(4));
        Double distance = Double.parseDouble(stellarObjProperties.get(5));

        list.add(new StarConstellation(stellarObjProperties.get(0),enumLocationProperty,
                stellarObjProperties.get(2), (new Star(starName, brightness, distance))));
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

    private static void save(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for (User u: users){
            StringBuilder sb = new StringBuilder();
            sb.append(u.getName() + ",");
            saveTrackerLists(sb, u.getHaveSeen());
            sb.append(",");
            saveTrackerLists(sb, u.getWantToSee());
            writer.println(sb.toString());
        }
        writer.close();
    }

    private static void saveTrackerLists(StringBuilder sb, List<StellarObject> stellarObjects){
        for (StellarObject so: stellarObjects){
            if (so instanceof Galaxy){
                String galaxy = (so.getName() + "-" + ((Galaxy) so).getType() + "-" + so.getLocation() +  "~");
                sb.append(galaxy);
            }
            else if (so instanceof StarConstellation) {
                String starCon = (so.getName() + "-" + so.getLocation() + "-" + ((StarConstellation) so).getSymbolism()
                        + "-" + so.getLocation() + "~");
                sb.append(starCon);
            }
        }
    }

    public static void addingToLists(User user) throws IOException {
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to add to the list of Stellar Objects you have seen " +
                "or the list of Stellar Objects you want to see?");
        System.out.println("Remember, you can only add to have seen if it has been added to want to see!");
        answer = userInput.nextLine();

        addingToUsersFieldLists(user, answer);
    }

    private static void addingToUsersFieldLists(User user, String answer) throws IOException {
        String input = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to add a Galaxy or Star Constellation?");
        input = userInput.nextLine();

        if (input.equals("galaxy")) {
            ListOfGalaxy log = new ListOfGalaxy("Used");
            log.printList();

            System.out.println("Which Galaxy? Indicate by inputted the location in the list.");

            input = userInput.next();
            Galaxy g = StellarObjectUI.retrieveGalaxy(input, log);

            if (answer.equals("have seen")) {
                user.addStellarObjectHaveSeen(g);

                System.out.println("All set!");
                more(user);
            }
            if (answer.equals("want to see")) {
                user.addStellarObjectWantToSee(g);
                System.out.println("All set!");
                more(user);
            }
        }
        else if (input.equals("star constellation")) {
                ListOfStarConstellation losc = new ListOfStarConstellation("Used");
                losc.printList();

                System.out.println("Which Star Constellation? Indicate by inputted the location in the list.");

                input = userInput.next();
                StarConstellation sc = StellarObjectUI.retrieveStarCon(input, losc);

                if (answer.equals("have seen")){
                    user.addStellarObjectHaveSeen(sc);
                    System.out.println("All set!");
                    more(user);
                }
                if (answer.equals("want to see")) {
                    user.addStellarObjectWantToSee(sc);
                    System.out.println("All set!");
                    more(user);
                }
        }
    }

    private static void more(User user) throws IOException {
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to add more?");
        answer = userInput.next();
        if (answer.equals("yes")){
            addingToLists(user);
        }
        else {
            save("users.txt");
            StellarObjectUI.exploreApp(user);}
    }

}
