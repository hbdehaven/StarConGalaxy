package ui;

import model.StarConstellation;
import model.Star;

import java.util.ArrayList;

public class main {
    public static void main (String[] args) {
        starsYeah();
        sayAnd();
        conStell();
        StarConstellation starcon1 = new StarConstellation("a");
    }

    private static void starsYeah() {
        System.out.println("STARS!");
        Star star = new Star("star");
    }

    private static void conStell() {
        System.out.println("CONSTELLATIONS!");
    }

    private static void sayAnd(){
        System.out.println(" and ");
    }

}
