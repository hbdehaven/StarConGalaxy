package model;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static model.UIRating.userRating;

public class UserInteration {

    // EFFECTS: asks certain questions based on user input
    public static void userInteract() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        ListOfStarConstellation starcons = new ListOfStarConstellation("All");
        ListofGalaxy galaxies = new ListofGalaxy("All");
        starcons.restore(starcons);
        while (true) {
            System.out.println("What are you more interested in? Galaxies or Star Constellations?");
            answer = scanner.nextLine();
            if (answer.equals("star constellations")) {
                System.out.println("Here are our Star Constellations:");
                starcons.printList();
                System.out.println("What would you like to explore? Location or symbolism?");
                answer = scanner.nextLine();
                if (answer.equals("location")) {
                    System.out.println("Great!");
                    System.out.println("Are you interested in constellations that you can see from " +
                            "the Northern or Southern Hemisphere?");
                    answer = scanner.nextLine();
                    if (answer.equals("northern")) {
                        System.out.println("Here you are");
                        starcons.getNorth(starcons);
                    } else if (answer.equals("southern")) {
                        System.out.println("Here you are");
                        starcons.restore(starcons);
                        starcons.getSouth(starcons);
                    }
                    System.out.println("Great! Would you like to explore anything else?");
                    answer = scanner.nextLine();
                    if (answer.equals("no")){
                        break;
                    }
                    if (answer.equals("yes")){
                        userInteract();
                    }
                } else if (answer.equals("symbolism")) {
                    System.out.println("Great!");
                    System.out.println("Here is a list of the Star Constellations and their associated symbols.");
                    starcons.printListSymbols();
                    System.out.println("Great! Would you like to explore anything else?");
                    answer = scanner.nextLine();
                }
            } else if (answer.equals("galaxies")) {
                answerGalaxies();
            }
            userRating();
        }

    }

    public static void answerGalaxies() {
        ListofGalaxy galaxies = new ListofGalaxy("All");
        System.out.println("Here are our Galaxies:");
        galaxies.printList();
        System.out.println("What would you like to explore? Location or type?");
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        answer = scanner.nextLine();
        if (answer.equals("location")){
            locationGalaxy();
        }
    }

    public static void locationGalaxy(){
        System.out.println("Great!");
        System.out.println("More to come!");
//        System.out.println("Are you interested in galaxies that you can see from " +
//                "the Northern or Southern Hemisphere?");
//        String answer = "";
//        Scanner scanner = new Scanner(System.in);
//        answer = scanner.nextLine();
//        if (answer.equals("northern")){
//            northernGalaxy();
//        }
    }

    public static void northernGalaxy(){
        ListofGalaxy galaxies = new ListofGalaxy("All");
        System.out.println("Here you are");
        //galaxies.getNorth(galaxies);
    }

}