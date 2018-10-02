package model;

import model.Star;
import model.Rating;

import java.util.ArrayList;
import java.util.List;

public class StarConstellation {
    public enum Location {
        NORTH, SOUTH
    }

    private String name; //most well known name of constellation; could be a number, but keep as String
    private double distance;  //average distance in lys from earth
    private Location location;
    private String symbolism; //small fact or what it represents
    private Star star; // brightest star; Star class with fields name and brightness
    private List<Rating> rating = new ArrayList<>();

    public StarConstellation(String name, double distance, Location location, String symbolism, Star star) {
        this.name = name;
        this.distance = distance;
        this.location = location;
        this.symbolism = symbolism;
        this.star = star;
    }

    // EFFECTS: prints name of starconstellation
    public void printName() {
        System.out.println("The name is " + name);
    }


    //getters
    // EFFECTS: get name of StarConstellation
    public String getName() {return name;}
    // EFFECTS: get distance of StarConstellation
    public double getDistance() {return distance;}
    // EFFECTS: get location of StarConstellation
    public Location getLocation() {return location;}
    // EFFECTS: get symbolism of StarConstellation
    public String getSymbolism() {return symbolism;}
    // EFFECTS: get the Star of the StarConstellation
    public Star getStar() {return star;}


}
