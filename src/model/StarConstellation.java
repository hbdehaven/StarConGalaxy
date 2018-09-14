package model;

import model.Star;

public class StarConstellation {
    public String name;  //most well known name of constellation; could be a number, but keep as String
    public double distance;  //average distance in lys from earth
    public boolean location;  //true means northern hemisphere, false means southern
    public String symbolism; //small fact or what it represents
    public Star star; // brightest star; Star class with fields name and brightness

    public StarConstellation(String name) {
        location = true;
        this.name = name;
        this.distance = 0;
        this.symbolism = "";
        //getName();
        //star?
        //StarConstellation A = new StarConstellation("A");
        //andromeda.distance = 100;
        //andromeda.star = star1;
    }

    public void getName() {
        System.out.println("The name is " + name);
    }

    //public String returnstarconName() {return name};


}
