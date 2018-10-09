package model;

public abstract class StellarObject {
    public enum Location {
        NORTH, SOUTH
    }

    protected String name;
    protected Location location;

    public StellarObject(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    // EFFECTS: prints name of stellar object
    public void printName() {
        System.out.println("The name is " + name);
    }

    public abstract void locationStatement(StellarObject so);


    //EFFECTS: get name of StellarObject
    public String getName() {return name;}
    //EFFECTS: get location of StellarObject
    public Location getLocation() {return location;}
}
