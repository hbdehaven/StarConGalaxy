package ui;

import model.*;
import model.exceptions.InvalidStringInput;

import java.util.Scanner;

public class UserUI {
    private static Scanner userInput;
    private static boolean whileCreateUser;

    public static void userLogIn() {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        while (true){
            System.out.println("Hi! Need to create user or logging in again?");
            answer = scanner.nextLine();
            firstQuestionUser(answer);
        }
    }

    private static void firstQuestionUser(String answer){
        if(answer.equals("create user")){
            createUser();
        }
        else if (answer.equals("logging in again")){
            // ask what user name they have and find and pull it up
            // then call explore app with that user passed in as parameter
            System.out.println("Great!");
        }
    }

    public static void createUser(){
        Scanner scanner = new Scanner(System.in);
        String name = "";
        whileCreateUser = true;

        while (whileCreateUser) {
            System.out.println("Welcome!");
            System.out.println("What would you like your username to be?");
            name = scanner.nextLine();
            User user = new User(name);
            System.out.println("Now that you are logged in, you can explore the app.");
            exploreApp(user);
        }
    }

    public static void exploreApp(User user){
        boolean continueWhile = true;
        String answer = "";
        userInput = new Scanner(System.in);

        while (continueWhile){
            displayOptions();
            answer = userInput.next();
            answer = answer.toLowerCase();

            if (answer.equals("s")){
                selectStarConstellations(user);
            }

            else if (answer.equals("g")){
                selectGalaxies(user);
            }

            else if (answer.equals("r")){
                System.out.println("r");
            }
            else if (answer.equals("u")){
                System.out.println("u");
            }
            else if (answer.equals("q")){
                continueWhile = false;
                whileCreateUser = false;
            }
            else System.out.println("Invalid Selection");
        }
        System.out.println("Thank you!");
    }

    // inspired by AccountNotRobust
    private static void displayOptions() {
        System.out.println("\nWhat would you like to explore? Select from:");
        System.out.println("\ts -> star constellations!");
        System.out.println("\tg -> galaxies!");
        System.out.println("\tr -> upload a rating of your favourite stellar objects!");
        System.out.println("\tu -> add to your user's seen lists and want to see lists!");
        System.out.println("\tq -> quit");
    }

    private static void selectStarConstellations(User user) {
        ListOfStarConstellation losc = new ListOfStarConstellation("Used");
        String answer = "";
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("Let's explore Star Constellations");
            losc.printList();
            System.out.println("Would you like to explore individually or by attribute?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            if (answer.equals("individually")){
                whichStarConstellation(user, losc);
            }
            else if (answer.equals("by attribute") || answer.equals( "attribute")){
                whichAttribute(user, losc);
            }
            else if (answer.equals("quit")){
                exploreApp(user);
            }
        }
    }

    private static void whichStarConstellation(User user, ListOfStarConstellation losc){
        String position = "";
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Great!");
        System.out.println("Which Star Constellation? Indicate by inputted the location in the list.");

        position = userInput.next();

        System.out.println("Here is all associated data with " + nameOfStarCon(position, losc));
        retrieveStarCon(position, losc).allInformation();
        System.out.println("                             ");
        System.out.println("Would you like to explore another? ");

        answer = userInput.next();

        if (answer.equals("yes")){
            whichStarConstellation(user, losc);
        }
        else if (answer.equals("no")){
            selectStarConstellations(user);
        }
    }

    private static String nameOfStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1).getName();
    }

    private static StarConstellation retrieveStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1);
    }

    private static void whichAttribute(User user, ListOfStarConstellation losc) {
        String answer = "";
        userInput = new Scanner(System.in);

        while (true){
            System.out.println("Great!");
            System.out.println("Would you like to explore by location, symbolism, distance, or brightest Star?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            exploreBy(answer, losc);

        }
    }

    private static void exploreBy(String answer, ListOfStarConstellation losc) {
        userInput = new Scanner(System.in);

        if (answer.equals("location")){
            System.out.println("Which hemisphere? Northern or Southern?");

            try {
                answer = userInput.next();
                answer = answer.toLowerCase();
                losc.sort(answer);
            } catch (InvalidStringInput invalidStringInput) {
                System.out.println("Invalid input, try again.");
            }
            System.out.println(" ");

        }
        else if (answer.equals("symbolism")){
            System.out.println("Here are the symbols of all our Star Constellations.");
            losc.printListSymbols();
            System.out.println(" ");
        }
        else if (answer.equals("distance")){
            System.out.println("to come");
            System.out.println(" ");
        }
        else if (answer.equals("brightest star") || answer.equals("star")){
            System.out.println("Here are the brightest stars of all our Star Constellations.");
            losc.printListStars();
            System.out.println(" ");
        }
    }

    private static void selectGalaxies(User user) {
        ListofGalaxy log = new ListofGalaxy("Used");
        String answer = "";
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("Let's explore Galaxies");
            log.printList();
            System.out.println("Would you like to explore individually or by attribute?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            if (answer.equals("individually")){
                whichGalaxy(user, log);
            }
            else if (answer.equals("by attribute") || answer.equals( "attribute")){
                whichGalaticAttribute(user, log);
            }
            else if (answer.equals("quit")){
                exploreApp(user);
            }
        }
    }

    private static void whichGalaxy(User user, ListofGalaxy log){
        String position = "";
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Great!");
        System.out.println("Which Galaxy? Indicate by inputted the location in the list.");

        position = userInput.next();

        System.out.println("Here is all associated data with " + nameOfGalaxy(position, log));
        retrieveGalaxy(position, log).allInformation();
        System.out.println("                             ");
        System.out.println("Would you like to explore another? ");

        answer = userInput.next();

        if (answer.equals("yes")){
            whichGalaxy(user, log);
        }
        else if (answer.equals("no")){
            selectGalaxies(user);
        }
    }

    private static String nameOfGalaxy(String i, ListofGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1).getName();
    }

    private static Galaxy retrieveGalaxy(String i, ListofGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1);
    }

    private static void whichGalaticAttribute(User user, ListofGalaxy log) {
        String answer = "";
        userInput = new Scanner(System.in);

        while (true){
            System.out.println("Great!");
            System.out.println("Would you like to explore by location or type");

            answer = userInput.next();
            answer = answer.toLowerCase();

            galacticExploreBy(answer, log);
        }
    }

    private static void galacticExploreBy(String answer, ListofGalaxy log) {
        userInput = new Scanner(System.in);

        if (answer.equals("location")) {
            System.out.println("Which hemisphere? Northern or Southern?");

            try {
                answer = userInput.next();
                answer = answer.toLowerCase();
                log.sort(answer);
            } catch (InvalidStringInput invalidStringInput) {
                System.out.println("Invalid input, try again.");
            }
            System.out.println(" ");

        } else if (answer.equals("type")) {
            System.out.println("Here are the type of all our Galaxies.");
            log.printList();
            System.out.println(" ");
        }

    }

}
