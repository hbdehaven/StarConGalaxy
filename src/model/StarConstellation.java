package model;

import model.Star;

public class StarConstellation {
    private boolean location;  //true means northern hemisphere, false means southern
    private String name;  //most well known name of constellation; could be a number, but keep as String
    private int distance;  //average distance in lys from earth
    private String symbolism; //small fact or what it represents
    private Star star; // brightest star; Star class with fields name and brightness

    public StarConstellation(String name) {
        location = true;
        this.name = name;
        distance = 0;
        this.symbolism = symbolism;
    }

}
