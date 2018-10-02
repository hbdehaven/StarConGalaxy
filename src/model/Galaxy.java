package model;

public class Galaxy {
    public enum Location {
        NORTH, SOUTH
    }

    private String name;
    private int type; // 0 for elliptical, 1 for spiral, 2 for irregular
    private Location location;

    public Galaxy (String name, int type, Location location){
        this.name = name;
        this.type = type;
        this.location = location;
    }

    //EFFECTS: returns Galaxy type in String form
    public String typeGalaxy(Galaxy g){
        if (g.type == 0){
            return "Elliptical";
        }
        else if (g.type == 1){
            return "Spiral";
        }
        else return "Irregular";
    }

    //getters
    //EFFECTS: get name of Galaxy
    public String getGalaxyName() {return name;}
    //EFFECTS: get type of Galaxy
    public int getType(){return type;}
    //EFFECTS: get location of Galaxy
    public Location getLocation() {return location;}
}
