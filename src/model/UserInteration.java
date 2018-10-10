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
        String pos = "";
        ListOfStarConstellation starcons = new ListOfStarConstellation("All");
        ListofGalaxy galaxies = new ListofGalaxy("All");
        starcons.restore(starcons);
        while (true) {
            System.out.println("What are you more interested in? Galaxies or Star Constellations?");
            answer = scanner.nextLine();
            if (answer.equals("star constellations")) {
                System.out.println("Here are our Star Constellations:");
                starcons.printList();
                System.out.println("Would you like to explore a specific Star Constellations?");
                answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    System.out.println("Which star constellation would you like to see? Please pick a number from 1 to "
                            + sizeList() + ".");
                    pos = scanner.nextLine();
                    System.out.println("Here is " + nameOfStarCon(pos));
                    StarConstellation interact = starcons.position(Integer.parseInt(pos) - 1);
                    interact.locationStatement(interact);
                    System.out.println("Great! Would you like to explore anything else?");
                    answer = scanner.nextLine();
                    if (answer.equals("no")) {
                        break;
                    }
                    if (answer.equals("yes")) {
                        userInteract();
                    }
                } else if (answer.equals("no")) {
                    noSpecific();
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
                        if (answer.equals("no")) {
                            break;
                        }
                        if (answer.equals("yes")) {
                            userInteract();
                        }
                    } else if (answer.equals("symbolism")) {
                        System.out.println("Great!");
                        System.out.println("Here is a list of the Star Constellations and their associated symbols.");
                        starcons.printListSymbols();
                        System.out.println("Great! Would you like to explore anything else?");
                        answer = scanner.nextLine();
                        if (answer.equals("no")) {
                            break;
                        }
                        if (answer.equals("yes")) {
                            userInteract();
                        }
                    }
                }

            } else if (answer.equals("galaxies")) {
                answerGalaxies();

            }
            System.out.println("Great! Would you like to explore anything else?");
            answer = scanner.nextLine();
            if (answer.equals("no")) {
                System.out.println("Would you like to upload a Rating?");
                answer = scanner.nextLine();
                if (answer.equals("no")){
                    break;
                }
                if (answer.equals("yes")){
                    userRating();
                }
            }
            if (answer.equals("yes")) {
                userInteract();
            }
        }
    }


    public static void answerGalaxies() {
        ListofGalaxy galaxies = new ListofGalaxy("All");
        String answer = "";
        String pos = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Here are our Galaxies:");
        galaxies.printList();
        System.out.println("Would you like to explore a specific Galaxy?");
        answer = scanner.nextLine();
        if (answer.equals("yes")) {
            System.out.println("Which galaxy would you like to see? Please pick a number from 1 to "
                    + sizeGList() + ".");
            pos = scanner.nextLine();
            System.out.println("Here is " + nameOfGalaxy(pos));
            Galaxy interact = galaxies.position(Integer.parseInt(pos) - 1);
            interact.locationStatement(interact);
        } else if (answer.equals("no")) {
            System.out.println("What would you like to explore? Location or type?");
            answer = scanner.nextLine();
            if (answer.equals("location")) {
                locationGalaxy();
            }
            if (answer.equals("type")) {
                System.out.println("Awesome!");
                System.out.println("More to come!");
//            System.out.println("Which type? ...");
            }
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
//        if (answer.equals("southern")){
//            southernGalaxy();
//        }
    }

    public static void northernGalaxy(){
        ListofGalaxy galaxies = new ListofGalaxy("All");
        System.out.println("Here you are");
        //galaxies.getNorth(galaxies);
    }

    public static void southernGalaxy(){
        ListofGalaxy galaxies = new ListofGalaxy("All");
        System.out.println("Here you are");
        //galaxies.getNorth(galaxies);
    }

    private static String nameOfStarCon(String i) {
        ListOfStarConstellation forInteraction = new ListOfStarConstellation("For interaction");
        return forInteraction.position(Integer.parseInt(i) - 1).getName();
    }

    private static String nameOfGalaxy(String i) {
        ListofGalaxy forInteraction = new ListofGalaxy("For interaction");
        return forInteraction.position(Integer.parseInt(i) - 1).getName();
    }

    private static int sizeList() {
        ListOfStarConstellation forInteraction = new ListOfStarConstellation("For interaction");
        return forInteraction.size();
    }

    private static int sizeGList() {
        ListofGalaxy forInteraction = new ListofGalaxy("For interaction");
        return forInteraction.size();
    }

    private static void noSpecific(){
        System.out.println("Okay!");
        System.out.println("Here is a list of our Star Constellations");
    }

}