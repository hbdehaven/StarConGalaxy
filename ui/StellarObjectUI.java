package ui;

import model.*;
import model.exceptions.InvalidStringInput;
import model.ListOfStarConstellation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StellarObjectUI {
    private static Scanner userInput;
    private static boolean whileCreateUser;
    private static boolean userLogInBoolean;

    public static void userLogIn() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        userLogInBoolean = true;

        while (userLogInBoolean){
            System.out.println("Hi! Need to create user or logging in again?");
            answer = scanner.nextLine();
            firstQuestionUser(answer);
        }
    }

    private static void firstQuestionUser(String answer) throws IOException {
        if(answer.equals("create user")){
            createUser();
        }
        else if (answer.equals("logging in again")){
            UserUI.load("users.txt");
            UserUI.containsUser(answer);
            // TODO
            // ask what user name they have and find and pull it up
            // then call explore app with that user passed in as parameter
            System.out.println("Great!");
        }
    }

    private static void createUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String name = "";
        whileCreateUser = true;

        while (whileCreateUser) {
            System.out.println("Welcome!");
            System.out.println("What would you like your username to be?");
            name = scanner.nextLine();
            User user = new User(name);
            UserUI.addUser(user);
            UserUI.save("users.txt");
            System.out.println("Now that you are logged in, you can explore the app.");
            exploreApp(user);
        }
    }

    private static void exploreApp(User user)  {
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
                try {
                    RatingUI.selectRating(user);
                } catch (IOException e) {
                    System.out.println("IOException Caught.");
                }
            }
            else if (answer.equals("u")){
                System.out.println("u");
            }
            else if (answer.equals("q")){
                continueWhile = false;
                whileCreateUser = false;
                userLogInBoolean = false;
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
            System.out.println("To return to main display, input return.");

            answer = userInput.next();
            answer = answer.toLowerCase();

            if (answer.equals("individually")){
                whichStarConstellation(user, losc);
            }
            else if (answer.equals("by attribute") || answer.equals( "attribute")){
                whichAttribute(user, losc);
            }
            else if (answer.equals("return")){
                exploreApp(user);
            }
        }
    }

    private static void whichStarConstellation(User user, ListOfStarConstellation losc) {
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
            System.out.println(" ");
        }
    }

    public static String nameOfStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1).getName();
    }

    public static StarConstellation retrieveStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1);
    }

    private static void whichAttribute(User user, ListOfStarConstellation losc) {
        String answer = "";
        userInput = new Scanner(System.in);

        while (true){
            System.out.println("Great!");
            System.out.println("Would you like to explore by location, symbolism, or brightest Star?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            exploreBy(user, answer, losc);
        }
    }

    private static void exploreBy(User user, String answer, ListOfStarConstellation losc) {
        userInput = new Scanner(System.in);

        losc.restore();

        if (answer.equals("location")){
            System.out.println("Which part of the sky? Northern or Southern?");

            try {
                answer = userInput.next();
                answer = answer.toLowerCase();
                losc.sort(answer);
            } catch (InvalidStringInput invalidStringInput) {
                System.out.println("Invalid input, try again.");
            }
            anotherAttribute(user, losc);
            System.out.println(" ");


        }
        else if (answer.equals("symbolism")){
            System.out.println("Here are the symbols of all our Star Constellations.");
            losc.printListSymbols();
            System.out.println(" ");
        }
        else if (answer.equals("brightest star") || answer.equals("star")){
            System.out.println("Here are the brightest stars of all our Star Constellations.");
            losc.printListStars();
            System.out.println(" ");
        }
    }

    private static void anotherAttribute(User user, ListOfStarConstellation losc) {
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to explore another attribute? ");

        answer = userInput.next();

        if (answer.equals("yes")){
            whichAttribute(user, losc);
        }
        else if (answer.equals("no")){
            selectStarConstellations(user);
            System.out.println(" ");
        }
    }

    private static void selectGalaxies(User user) {
        ListOfGalaxy log = new ListOfGalaxy("Used");
        String answer = "";
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("Let's explore Galaxies");
            log.printList();
            System.out.println("Would you like to explore individually or by attribute?");
            System.out.println("To return to main display, input return.");

            answer = userInput.next();
            answer = answer.toLowerCase();

            if (answer.equals("individually")){
                whichGalaxy(user, log);
            }
            else if (answer.equals("by attribute") || answer.equals( "attribute")){
                whichGalacticAttribute(user, log);
            }
            else if (answer.equals("return")){
                exploreApp(user);
            }
        }
    }

    private static void whichGalaxy(User user, ListOfGalaxy log) {
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
            System.out.println(" ");

        }
    }

    public static String nameOfGalaxy(String i, ListOfGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1).getName();
    }

    public static Galaxy retrieveGalaxy(String i, ListOfGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1);
    }

    private static void whichGalacticAttribute(User user, ListOfGalaxy log) {
        String answer = "";
        userInput = new Scanner(System.in);

        while (true){
            System.out.println("Great!");
            System.out.println("Would you like to explore by location or type?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            galacticExploreBy(answer, log);
        }
    }

    private static void galacticExploreBy(String answer, ListOfGalaxy log) {
        userInput = new Scanner(System.in);

        if (answer.equals("location")) {
            System.out.println("Which part of the sky? Northern or Southern?");

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
            log.printListType();
            System.out.println(" ");
        }

    }

}
