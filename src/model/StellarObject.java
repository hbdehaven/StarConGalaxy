package model;

import java.util.ArrayList;
import java.util.List;

public abstract class StellarObject {
    public enum Location {  // north, south parts of the sky, 90-0 degrees declination: north; 0-(-90) degree declination: south
        NORTH, SOUTH, BOTH
    }
    protected String name;
    protected Location location;

    public StellarObject(String name, Location location) {
        this.name = name;
        this.location = location;
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


    public abstract void allInformation();


    //EFFECTS: get name of StellarObject
    public String getName() {return name;}
    //EFFECTS: get location of StellarObject
    public Location getLocation() {return location;}

}
