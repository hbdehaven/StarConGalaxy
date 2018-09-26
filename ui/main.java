package ui;

import model.ListOfStarConstellation;
import model.Rating;
import model.StarConstellation;
import model.Star;

import java.util.ArrayList;

import static model.Rating.userRating;
import static model.User.userInterest;

public class main {

    public static void main(String[] args) {
        new main().runApp();

    }

    private void runApp(){
        Star alphaAndro = new Star("Alpha Andromedae", 2.06);

        Star altair = new Star("Altair", .76);

        Star phact = new Star("Phact", 2.645);

        Rating andromeda5 = new Rating(5, "Heather", 924);

        Rating aquila3 = new Rating(3, "Jake", 924);

        Rating columba2 = new Rating(2 , "Carole", 924);

        ArrayList<StarConstellation> starcons = new ArrayList<>();

        StarConstellation andromeda = new StarConstellation("Andromeda", 97, true, "The Chained Lady", alphaAndro, andromeda5);

        StarConstellation aquila = new StarConstellation("Aquila", 16.73, true, "The Eagle", altair, aquila3);

        StarConstellation columba = new StarConstellation("Columba", 261, false, "The Dove", phact, columba2);

        starcons.add(andromeda);
        starcons.add(aquila);
        starcons.add(columba);
        andromeda.printName();
        starcons = ListOfStarConstellation.sortNorth(starcons);
        //printList();
        //userInterest();
        userRating();
    }
}



