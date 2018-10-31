package model;

import java.util.ArrayList;
import java.util.List;

public abstract class StellarObject {
    public enum Location {  // north, south parts of the sky, 90-0 degrees declination: north; 0-(-90) degree declination: south
        NORTH, SOUTH, BOTH
    }
    protected String name;
    protected Location location;
    protected List<User> haveSeen;
    protected List<User> wantToSee;

    public StellarObject(String name, Location location) {
        this.name = name;
        this.location = location;
        haveSeen = new ArrayList<>();
        wantToSee = new ArrayList<>();
    }

    public String readableLocation(){
        if (location == StellarObject.Location.NORTH){
            return "the Northern Sky";
        }
        else if (location == StellarObject.Location.SOUTH){
            return "the Southern Sky";
        }
        else
        return "both the Northern and Southern Skys";
    }

    public static boolean locationNorth(StellarObject.Location location){
        return (location == StellarObject.Location.NORTH);
    }

    public static boolean locationSouth(StellarObject.Location location){
        return (location == StellarObject.Location.SOUTH);
    }

    // adding user to want to see
    public void addUserWantToSee(User u){
        if (!wantToSee.contains(u)){
            wantToSee.add(u);
            u.addStellarObjectWantToSee(this);
        }
    }

    // taking User from want to see, removing, and putting in seen
    public void addUserSeen(User u){
        if (wantToSee.contains(u) && !haveSeen.contains(u)){
            wantToSee.remove(u);
            haveSeen.add(u);
            u.addStellarObjectHaveSeen(this);
        }
    }

    // EFFECTS: print have seen list
    public void printHaveSeenList() {
        System.out.println("Have Seen: ");
        for (User u: haveSeen){
            System.out.println(u.getName());
        }
        System.out.println("/////////");
    }

    // EFFECTS: print want to see list
    public void printWantToSeeList() {
        System.out.println("Would Like to See: ");
        for (User u: wantToSee){
            System.out.println(u.getName());
        }
        System.out.println("/////////");
    }

    public abstract void allInformation();


    //EFFECTS: get name of StellarObject
    public String getName() {return name;}
    //EFFECTS: get location of StellarObject
    public Location getLocation() {return location;}

    public void setHaveSeen(List<User> haveSeen) {
        this.haveSeen = haveSeen;
    }

    public void setWantToSee(List<User> wantToSee) {
        this.wantToSee = wantToSee;
    }
}
