package ui;

import model.StarConstellation;
import model.Star;

import java.util.ArrayList;

public class main {

    public static void main (String[] args){
        Star alphaAndro = new Star ("Alpha Andromedae", 2.06);

        Star altair = new Star ("Altair", .76);

        Star phact = new Star ("Phact", 2.645);

        ArrayList<StarConstellation> starcons = new ArrayList<>();

        StarConstellation andromeda = new StarConstellation("Andromeda",97,true, "The Chained Lady", alphaAndro);

        StarConstellation aquila = new StarConstellation("Aquila", 16.73, true, "The Eagle", altair);

        StarConstellation columba = new StarConstellation("Columba", 261,false, "The Dove", phact);

        starcons.add(andromeda);
        starcons.add(aquila);
        starcons.add(columba);
        sentence();
        andromeda.printName();
        starcons = sortNorth(starcons);
        printNorth(starcons);
    }

    private static void sentence() {
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

    public static ArrayList sortNorth (ArrayList<StarConstellation> starcons) {
        ArrayList<StarConstellation> north = new ArrayList<>();
        for (StarConstellation sc: starcons) {
            if (sc.isLocation()) {
                north.add(sc);
            }
        }
        return north;
    }

    private static void printNorth (ArrayList<StarConstellation> starcons) {
        for (StarConstellation sc: starcons) {
            System.out.println(sc.getName());
        }
    }
    //passing a parameter, loop, return something, and local variable


}