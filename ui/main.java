package ui;

import model.ListOfStarConstellation;
import model.Rating;
import model.StarConstellation;
import model.Star;

import java.io.IOException;
import java.util.ArrayList;

import static com.sun.deploy.util.SessionState.save;
import static model.Rating.userRating;
import static model.User.userInterest;

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

        Rating andromeda5 = new Rating("Rating of Andromeda", 5, "Heather", 924);

        Rating aquila3 = new Rating("Rating of Aquila",3, "Jake", 924);

        Rating columba2 = new Rating("Rating of Columba",2 , "Carole", 924);

        //andromeda.printName();
        //allstarcons.getNorth(allstarcons);
        //allstarcons.restore(allstarcons);
        //allstarcons.printList();
        //allstarcons.position(0).printName();
        //userInterest();
        userRating();
    }
}



