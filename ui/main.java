package ui;

import model.ListOfStarConstellation;
import model.Rating;
import model.StarConstellation;
import model.Star;

import java.io.IOException;

import static model.UIRating.userRating;
import static model.UserInteration.userInteract;

public class main {

    public static void main(String[] args) throws IOException {
        new main().runApp();
    }

    private void runApp() throws IOException {
        Star alphaAndro = new Star("Alpha Andromedae", 2.06);

        Star altair = new Star("Altair", .76);

        Star phact = new Star("Phact", 2.645);

        ListOfStarConstellation allstarcons = new ListOfStarConstellation("All Stars" );

        StarConstellation andromeda = new StarConstellation("Andromeda", 97, StarConstellation.Location.NORTH, "The Chained Lady", alphaAndro);

        StarConstellation aquila = new StarConstellation("Aquila", 16.73, StarConstellation.Location.NORTH, "The Eagle", altair);

        StarConstellation columba = new StarConstellation("Columba", 261, StarConstellation.Location.SOUTH, "The Dove", phact);

        //andromeda.printName();
        //allstarcons.getNorth();
        //allstarcons.restore();
        //allstarcons.getSouth();
        //allstarcons.printList();
        //allstarcons.position(0).printName();
        userInteract();
    }
}



