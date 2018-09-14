package ui;

import model.StarConstellation;
import model.Star;

import java.util.ArrayList;

public class main {

    public static void main (String[] args){
        ArrayList<StarConstellation> starcons = new ArrayList<>();
        StarConstellation andromeda = new StarConstellation("Andromeda");
        andromeda.distance = 97;
        andromeda.symbolism = "The Chained Lady";
        //andromeda.star = alphaAndromedae;
        StarConstellation aquila = new StarConstellation("Aquila");
        aquila.distance = 16.73;
        aquila.symbolism = "The Eagle";
        //aquila.star = altair
        StarConstellation columba = new StarConstellation("Columba");
        columba.distance = 261;
        columba.location = false;
        columba.symbolism = "The Dove";
        //columba.star = phact
        starcons.add(andromeda);
        starcons.add(aquila);
        starcons.add(columba);
        sentence();
    }

    public static void sentence() {
        starsYeah();
        sayAnd();
        conStell();
    }

    private static void starsYeah() {
        System.out.println("stars");
    }

    private static void conStell() {
        System.out.println("constellations");
    }

    private static void sayAnd() {
        System.out.println("and");
    }

//    public void sortNorth(List<StarConstellation> scon) {
//        //ArrayList<StarConstellation> north = new ArrayList<>();
//        for (StarConstellation sc: scon) {
//            if (sc.location = true) {
//                north.add(scon);
//                }
//        return north;
//    }
    //passing a parameter, loop, and local variable 


}