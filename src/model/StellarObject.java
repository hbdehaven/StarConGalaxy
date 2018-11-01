package model;

import java.util.ArrayList;
import java.util.List;

public abstract class StellarObject {
    public enum Location {  // north, south parts of the sky, 90-0 degrees declination: north; 0-(-90) degree declination: south
        NORTH, SOUTH, BOTH
    }
    protected String name;
    protected Location location;
    protected List<User> haveSeenofUsers;
    protected List<User> wantToSeeofUsers;

    public StellarObject(String name, Location location) {
        this.name = name;
        this.location = location;
        haveSeenofUsers = new ArrayList<>();
        wantToSeeofUsers = new ArrayList<>();
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
        if (!wantToSeeofUsers.contains(u)){
            wantToSeeofUsers.add(u);
            u.addStellarObjectWantToSee(this);
        }
    }

    // taking User from want to see, removing, and putting in seen
    public void addUserSeen(User u){
        if (wantToSeeofUsers.contains(u) && !haveSeenofUsers.contains(u)){
            wantToSeeofUsers.remove(u);
            haveSeenofUsers.add(u);
            u.addStellarObjectHaveSeen(this);
        }
    }

    // EFFECTS: print have seen list
    public void printHaveSeenList() {
        System.out.println("Have Seen: ");
        for (User u: haveSeenofUsers){
            System.out.println(u.getName());
        }
        System.out.println("/////////");
    }

    // EFFECTS: print want to see list
    public void printWantToSeeList() {
        System.out.println("Would Like to See: ");
        for (User u: wantToSeeofUsers){
            System.out.println(u.getName());
        }
        System.out.println("/////////");
    }

    public abstract void allInformation();


    //EFFECTS: get name of StellarObject
    public String getName() {return name;}
    //EFFECTS: get location of StellarObject
    public Location getLocation() {return location;}
    public List<User> getHaveSeenofUsers() {
        return haveSeenofUsers;
    }
    public List<User> getWantToSeeofUsers() {
        return wantToSeeofUsers;
    }

    public void setHaveSeenofUsers(List<User> haveSeenofUsers) {
        this.haveSeenofUsers = haveSeenofUsers;
    }

    public void setWantToSeeofUsers(List<User> wantToSeeofUsers) {
        this.wantToSeeofUsers = wantToSeeofUsers;
    }
}
