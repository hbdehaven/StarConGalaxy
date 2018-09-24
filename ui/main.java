package ui;

import model.Rating;
import model.StarConstellation;
import model.Star;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
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
        sentence();
        andromeda.printName();
        starcons = sortNorth(starcons);
        printList(starcons);
        userInteraction();
    }

    // EFFECTS: prints out a sentence
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

    // MODIFIES: this
    // EFFECTS: orders all starconstellations seen from the north hemisphere
    private static ArrayList sortNorth(ArrayList<StarConstellation> starcons) {
        ArrayList<StarConstellation> north = new ArrayList<>();
        for (StarConstellation sc : starcons) {
            if (sc.isLocation()) {
                north.add(sc);
            }
        }
        return north;
    }

    // EFFECTS: prints out every starconstellation in the list
    private static void printList(ArrayList<StarConstellation> starcons) {
        for (StarConstellation sc : starcons) {
            System.out.println(sc.getName());
        }
    }

    // inspired by LittleLoggingCalculator

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: asks certain questions based on user input
    private static void userInteraction() {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        while (true) {
            System.out.println("Do you like star constellations? yes or no?");
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                System.out.println("Awesome!");
                System.out.println("Would you like to sign up for daily emails?");
                answer = scanner.nextLine();
//                System.out.println(answer);
                if (answer.equals("yes")) {
                    System.out.println("Great! More to come!");
                }
                scanner.nextLine();
            }

            if (answer.equals("no")) {
                System.out.println("Then get out.");
                scanner.nextLine();
            }

            else if (answer.equals("quit")) {
                break;
            }

        }

    }

    // store rating and change a rating // each constellation has a list of ratings that has a date and time and a user (String name)

}
