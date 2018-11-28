package ui;

import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaveLoadUsers {

    public static void load(String filename) throws IOException {
        List<String> usersFromFile = Files.readAllLines(Paths.get(filename));
        for (String userLog : usersFromFile) {
            ArrayList<String> partsOfLine = splitOnRegex(userLog, ",");
            String userName = partsOfLine.get(0);
            User user = new User(userName);
            try {
                List<StellarObject> haveSeen = parseListsOfStellarObjects(partsOfLine.get(1));
                user.setHaveSeen(haveSeen);
            } catch (IndexOutOfBoundsException e) {
                // do nothing
            }
            try {
                List<StellarObject> wantToSee = parseListsOfStellarObjects(partsOfLine.get(2));
                user.setWantToSee(wantToSee);
            } catch (IndexOutOfBoundsException e) {
                // do nothing
            }
            UserUI.addUser(user);
        }
    }

    private static List<StellarObject> parseListsOfStellarObjects(String stellarObjects) {
        List<StellarObject> list = new ArrayList<StellarObject>();
        ArrayList<String> parts = splitOnRegex(stellarObjects, "~");
        for (String s : parts) {
            ArrayList<String> stellarObjProperties = splitOnRegex(s, "-");
            if (stellarObjProperties.size() == (3)) {
                galaxyInstantiation(stellarObjProperties, list);
            } else starConInstantiation(stellarObjProperties, list);
        }
        return list;
    }

    private static void galaxyInstantiation(ArrayList<String> stellarObjProperties, List<StellarObject> list) {
        Galaxy.Type enumTypeProperty = stringToEnum(stellarObjProperties.get(1));
        StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(2));

        Galaxy galaxy = new Galaxy(stellarObjProperties.get(0), enumTypeProperty, enumLocationProperty);
        list.add(galaxy);
    }

    private static void starConInstantiation(ArrayList<String> stellarObjProperties, List<StellarObject> list) {
        StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(1));

        String starName = stellarObjProperties.get(3);
        Double brightness = Double.parseDouble(stellarObjProperties.get(4));
        Double distance = Double.parseDouble(stellarObjProperties.get(5));

        list.add(new StarConstellation(stellarObjProperties.get(0), enumLocationProperty,
                stellarObjProperties.get(2), (new Star(starName, brightness, distance))));
    }

    private static StellarObject.Location stringToEnumLocation(String property) {
        if (property.equals("NORTH")) {
            return StellarObject.Location.NORTH;
        } else if (property.equals("SOUTH")) {
            return StellarObject.Location.SOUTH;
        } else return StellarObject.Location.BOTH;
    }

    private static Galaxy.Type stringToEnum(String property) {
        if (property.equals("Elliptical")) {
            return Galaxy.Type.ELLIPTICAL;
        } else if (property.equals("Irregular")) {
            return Galaxy.Type.IRREGULAR;
        } else return Galaxy.Type.SPIRAL;
    }

    private static ArrayList<String> splitOnRegex(String line, String regex) {
        String[] splits = line.split(regex);
        return new ArrayList<String>(Arrays.asList(splits));
    }

    public static void save(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        for (User u : UserUI.getUsers()) {
            StringBuilder sb = new StringBuilder();
            sb.append(u.getName() + ",");
            saveTrackerLists(sb, u.getHaveSeen());
            sb.append(",");
            saveTrackerLists(sb, u.getWantToSee());
            writer.println(sb.toString());
        }
        writer.close();
    }

    private static void saveTrackerLists(StringBuilder sb, List<StellarObject> stellarObjects) {
        for (StellarObject so : stellarObjects) {
            if (so instanceof Galaxy) {
                String galaxy = (so.getName() + "-" + ((Galaxy) so).getType() + "-" + so.getLocation() + "~");
                sb.append(galaxy);
            } else if (so instanceof StarConstellation) {
                String starCon = (so.getName() + "-" + so.getLocation() + "-" + ((StarConstellation) so).getSymbolism()
                        + "-" + ((StarConstellation) so).getStar().getStarName() + "-" +
                        ((StarConstellation) so).getStar().getBrightness()+ "-" +
                        ((StarConstellation) so).getStar().getDistance() + "~");
                sb.append(starCon);
            }
        }
    }
}