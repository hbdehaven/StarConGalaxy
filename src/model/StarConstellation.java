package model;

import model.Star;
import model.Rating;

public class StarConstellation {
    private String name; //most well known name of constellation; could be a number, but keep as String
    private double distance;  //average distance in lys from earth
    private boolean location;  //true means northern hemisphere, false means southern
    private String symbolism; //small fact or what it represents
    private Star star; // brightest star; Star class with fields name and brightness
    private Rating rating; // most recent rating of a star

    public StarConstellation(String name, double distance, boolean location, String symbolism, Star star, Rating rating) {
        this.name = name;
        this.distance = distance;
        this.location = location;
        this.symbolism = symbolism;
        this.star = star;
        this.rating = rating;
    }

    public void printName() {
        System.out.println("The name is " + name);
    }

    //getters
    public String getName() {return name;}
    public double getDistance() {return distance;}
    public boolean isLocation() {return location;}
    public String getSymbolism() {return symbolism;}
    public Star getStar() {return star;}
    public Rating getRating() {return rating;}


}
