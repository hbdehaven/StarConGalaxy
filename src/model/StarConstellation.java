package model;

import model.Star;
import model.Rating;

import java.util.ArrayList;
import java.util.List;

public class StarConstellation extends StellarObject{
    private double distance;  //average distance in lys from earth
    private String symbolism; //small fact or what it represents
    private Star star; // brightest star; Star class with fields name and brightness

    public StarConstellation(String name, double distance, Location location, String symbolism, Star star) {
        super(name, location);
        this.distance = distance;
        this.symbolism = symbolism;
        this.star = star;
    }

    //getters
    // EFFECTS: get distance of StarConstellation
    public double getDistance() {return distance;}
    // EFFECTS: get location of StarConstellation
    public String getSymbolism() {return symbolism;}
    // EFFECTS: get the Star of the StarConstellation
    public Star getStar() {return star;}

    @Override
    public void locationStatement(StellarObject so) {
        System.out.println("The location of this star constellation is " + getLocation());
    }
}
