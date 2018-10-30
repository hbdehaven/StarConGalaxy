package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class User{
    private String name;
    private List<StellarObject> haveSeen;
    private List<StellarObject> wantToSee;

    public User(String name){
        this.name = name;
        haveSeen = new ArrayList<>();
        wantToSee = new ArrayList<>();
    }

    public void addStellarObjectWantToSee(StellarObject so){
        if (!wantToSee.contains(so)){
            wantToSee.add(so);
            so.addUserWantToSee(this);
        }
    }

    // remove SO from wanttoSee, add to have seen;
    // subsequently called addUserSeen
    public void addStellarObjectHaveSeen(StellarObject so){
        if (wantToSee.contains(so) &&!haveSeen.contains(so)){
            wantToSee.remove(so);
            haveSeen.add(so);
            so.addUserSeen(this);
        }
    }

    // EFFECTS: print have seen list
    public void printHaveSeenList() {
        System.out.println("Have Seen: ");
        for (StellarObject so: haveSeen){
            System.out.println(so.getName());
        }
        System.out.println("/////////");
    }

    // EFFECTS: print want to see list
    public void printWantToSeeList() {
        System.out.println("Would Like to See: ");
        for (StellarObject so: wantToSee){
            System.out.println(so.getName());
        }
        System.out.println("/////////");
    }

    // getters
    public String getName(){
        return name;
    }

    public List<StellarObject> getHaveSeen() {
        return haveSeen;
    }

    public List<StellarObject> getWantToSee() {
        return wantToSee;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setHaveSeen(List<StellarObject> haveSeen) {
        this.haveSeen = haveSeen;
    }

    public void setWantToSee(List<StellarObject> wantToSee) {
        this.wantToSee = wantToSee;
    }


//    public void load(Scanner scanner) throws IOException {
//        List<String> users = Files.readAllLines(Paths.get("users.txt"));
//        for (String userLog : users){
//            ArrayList<String> partsOfLine = splitOnRegex(userLog, ","); // [hbdehaven,[],[]]
//            String userName = partsOfLine.get(0);
//            List<StellarObject> haveSeen = parseLists(partsOfLine.get(1));
//            List<StellarObject> wantToSee = parseLists(partsOfLine.get(2));
//            User user = new User(userName);
//            user.haveSeen = haveSeen;
//            user.wantToSee = wantToSee;
//
//            // add user to list of users
//        }
//    }
//
//    private List<StellarObject> parseLists(String stellarObjects) {
//        List<StellarObject> l = new ArrayList<>();
//        ArrayList<String> parts = splitOnRegex(stellarObjects, "|");
//        for(String s : parts) {
//            ArrayList<String> stellarObjProperties = splitOnRegex(s, "-");
//            if (stellarObjProperties.size() == (3)){
//                Galaxy.Type enumTypeProperty = stringToEnum(stellarObjProperties.get(1));
//                StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(2));
//                l.add(new Galaxy(stellarObjProperties.get(0),enumTypeProperty, enumLocationProperty));
//            }
//            else if (stellarObjProperties.size() == 4) {
//                StellarObject.Location enumLocationProperty = stringToEnumLocation(stellarObjProperties.get(1));
//
//                String starName = stellarObjProperties.get(3);
//                Double brightness = Double.parseDouble(stellarObjProperties.get(4));
//                Double distance = Double.parseDouble(stellarObjProperties.get(5));
//
//                l.add(new StarConstellation(stellarObjProperties.get(0),enumLocationProperty,
//                        stellarObjProperties.get(2), (new Star(starName, brightness, distance))));
//            }
//        }
//        return l;
//    }
//
//    private StellarObject.Location stringToEnumLocation(String property){
//        if (property.equals("NORTH")){
//            return StellarObject.Location.NORTH;
//        }
//        else if (property.equals("SOUTH")){
//            return StellarObject.Location.SOUTH;
//        }
//        else return StellarObject.Location.BOTH;
//    }
//
//    private Galaxy.Type stringToEnum(String property){
//        if (property.equals("Elliptical")){
//            return Galaxy.Type.ELLIPTICAL;
//        }
//        else if (property.equals("Irregular")){
//            return Galaxy.Type.IRREGULAR;
//        }
//        else return Galaxy.Type.SPIRAL;
//    }
//
//    public ArrayList<String> splitOnRegex(String line, String regex){
//        String[] splits = line.split(regex);
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    @Override
//    public void save(String fileName) throws IOException {
//        Files.write(Paths.get(fileName), Arrays.asList(name + "," + haveSeen + "," + wantToSee ),
//                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
