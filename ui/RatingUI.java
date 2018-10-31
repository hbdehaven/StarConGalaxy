package ui;

import model.*;
import model.exceptions.InvalidRatingValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static ui.StellarObjectUI.nameOfGalaxy;
import static ui.StellarObjectUI.nameOfStarCon;

public class RatingUI {
    public static ArrayList<Rating> ratings = new ArrayList<>();
    private static Scanner userInput;
    private static boolean whileLoop;

    public static void selectRating(User user) throws IOException {
        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
        load(scanner1);

        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
        ListOfGalaxy log = new ListOfGalaxy("LOG");
        whileLoop = true;

        String answer = "";
        userInput = new Scanner(System.in);

        while (whileLoop) {
            System.out.println("For which Stellar Object would you like to upload a Rating?");
            System.out.println("Galaxies or Star Constellations?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            if(answer.equals("galaxies")){
                whichGalaxy(user, log);
            }
            else if (answer.equals("starconstellations")){
                whichStarConstellation(user, losc);
            }
        }
    }

    private static void whichGalaxy(User user, ListOfGalaxy log){
        String position = "";
        String answer = "";
        userInput = new Scanner(System.in);

        log.printList();
        System.out.println("For which Galaxy would you like to upload a Rating?");
        System.out.println("Please input the position of the Galaxy in the list.");

        position = userInput.next();

        String nameofGalaxy = nameOfGalaxy(position, log);
        System.out.println("You would like to rate " + nameofGalaxy + "?");

        answer = userInput.next();

        if(answer.equals("yes")){
            createRating(user, nameofGalaxy);
        }
        else if (answer.equals("no")){
            whichGalaxy(user, log);
        }
    }

    private static void whichStarConstellation(User user, ListOfStarConstellation losc) {
        String position = "";
        String answer = "";
        userInput = new Scanner(System.in);

        losc.printList();
        System.out.println("For which Star Constellation would you like to upload a Rating?");
        System.out.println("Please input the position of the Star Constellation in the list.");

        position = userInput.next();

        String nameOfStarCon = nameOfStarCon(position, losc);
        System.out.println("You would like to rate " + nameOfStarCon + "?");

        answer = userInput.next();

        if(answer.equals("yes")){
            createRating(user, nameOfStarCon);
        }
        else if (answer.equals("no")){
            whichStarConstellation(user, losc);
        }
    }

    private static void createRating(User user, String ratingName){
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("What's the date in the form of MMDD?");
        answer = userInput.next();
        int date = Integer.parseInt(answer);

        System.out.println("How many stars out of 5 would you like to rate " + ratingName + "?");
        answer = userInput.next();
        int num = Integer.parseInt(answer);

        Rating rating = new Rating(ratingName, num, date);

        checkValidity(user, rating);

        uploadAnotherQuestion(user);
    }

    private static void checkValidity(User user, Rating rating){
        String answer = "";
        userInput = new Scanner(System.in);
        ArrayList<Rating> allratings = new ArrayList<>();

        while (true) {
            try {
                rating.isValid();
                allratings.add(rating);
                callingSave(allratings);
                RatingDatabase.addUserListRating(user, rating);
                break;
            } catch (InvalidRatingValue invalidRatingValue) {
                System.out.println("Invalid. Rating value must be between 1 and 5.");
                System.out.println("Please input another Rating value between 1 and 5.");

                answer = userInput.next();

                int num = Integer.parseInt(answer);
                rating.setValue(num);
            }
        }
    }

    private static void uploadAnotherQuestion(User user)  {
        String answer = "";
        userInput = new Scanner(System.in);

        while (true) {
            System.out.println("Great! It has been successfully updated.");
            System.out.println("Would you like to rate another?");

            answer = userInput.next();

            if (answer.equals("yes")) {
                try {
                    selectRating(user);
                } catch (IOException e) {
                    System.out.println("IOException caught.");
                }
            } else if (answer.equals("no")) {
                whileLoop = false;
                break;
            }
        }
    }

    private static void callingSave(ArrayList<Rating> allratings) {
        for (Rating r : allratings) {
            try {
                r.save("ratings.txt");
            } catch (IOException e) {
                System.out.println("Caught IOException");
            }
        }
    }

    private static void load(Scanner scanner) throws IOException {
        while (scanner.hasNextLine()) {
            Rating rate = new Rating("R", 0, 0000);
            rate.load(scanner);
            ratings.add(rate);
        }
    }

    // TODO: average rating based on string parameter of name of stellar object method based on ratings or allratings?
    // now add this to user interaction





}
