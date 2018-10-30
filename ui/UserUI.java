package ui;

import model.*;

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

    public static void addUser(User user){
        users.add(user);
    }

    public static void containsUser(String username){
        User user = new User(username);
        if (users.contains(user)){
            System.out.println("Username already exists. Please create another");
        }
    }


    public static void load(String filename) throws IOException {
        List<String> usersFromFile = Files.readAllLines(Paths.get(filename));
        for (String userLog : usersFromFile){
            ArrayList<String> partsOfLine = splitOnRegex(userLog, ","); // [hbdehaven,[],[]]
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
