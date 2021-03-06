package model;

import java.util.Objects;

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

    protected String readableLocation(){
        if (location == StellarObject.Location.NORTH){
            return "the Northern Sky";
        }
        else if (location == StellarObject.Location.SOUTH){
            return "the Southern Sky";
        }
        else
        return "both the Northern and Southern Skys";
    }

    protected static boolean locationNorth(StellarObject.Location location){
        return (location == StellarObject.Location.NORTH);
    }

    protected static boolean locationSouth(StellarObject.Location location){
        return (location == StellarObject.Location.SOUTH);
    }

    public abstract StringBuilder allInformation();

    //EFFECTS: get name of StellarObject
    public String getName() {return name;}
    //EFFECTS: get location of StellarObject
    public Location getLocation() {return location;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StellarObject)) return false;
        StellarObject that = (StellarObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
