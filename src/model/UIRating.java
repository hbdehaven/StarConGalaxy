package model;

import model.exceptions.InvalidRatingValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UIRating {
    public static ArrayList<Rating> ratings = new ArrayList<>();

    // EFFECTS: asks questions and saves data based on user's input
    public static void userRating() throws IOException {
        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
        load(scanner1);
        ArrayList<Rating> allratings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        String pos = "";
        ListOfStarConstellation forrating = new ListOfStarConstellation("For rating");
        while (true) {
            System.out.println("Would you like to rate a star constellation?");
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                forrating.printList();
                System.out.println("Which star constellation would you like to rate? Please pick a number from 1 to "
                        + sizeList() + ".");
                pos = scanner.nextLine();
                System.out.println("You'd like to rate " + nameOfStarCon(pos) + "?");
                answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    //wouldLikeToRateStar(pos);
                    System.out.println("How many stars out of 5 would you like to rate " + nameOfStarCon(pos) + "?");
                    answer = scanner.nextLine();
                    int num = Integer.parseInt(answer);

                    System.out.println("What's your name?");
                    String user = scanner.nextLine();
                    System.out.println("What's the date in the form of MMDD?");
                    answer = scanner.nextLine();
                    int date = Integer.parseInt(answer);
                    System.out.println("Is this correct? Rating of " + num + " by " + user + " on " + date + "?");
                    answer = scanner.nextLine();
                    Rating starconnamerating = new Rating(nameOfStarCon(pos), num, user, date);

                    while (true) {
                        try {
                            starconnamerating.isValid();
                            allratings.add(starconnamerating);
                            callingSave(allratings);
                            break;
                        } catch (InvalidRatingValue invalidRatingValue) {
                            System.out.println("Invalid. Rating value must be between 1 and 5.");
                            System.out.println("Please input another Rating value between 1 and 5.");
                            answer = scanner.nextLine();
                            int num2 = Integer.parseInt(answer);
                            starconnamerating.setValue(num2);
                        }
                    }
                    //isItCorrect(answer);
                    if (answer.equals("yes")) {
                        System.out.println("Great! Thanks again for your input.");
                        allratings.add(starconnamerating);
                        callingSave(allratings);
                        System.out.println("Would you like to rate another?");
                        answer = scanner.nextLine();
                        if (answer.equals("yes")) {
                            userRating();
                        } else if (answer.equals("no")) {
                            break;
                        }
                    } else tryAgain(answer);
                } else {
                    break;
                }
            } else if (answer.equals("no")) {
                System.out.println("Okay!");
                break;
            }
        }
    }

    private static void tryAgain(String answer) throws IOException {
        if (answer.equals("no")) {
            System.out.println("Let's try again.");
            userRating();
        }
    }

    private static String nameOfStarCon(String i) {
        ListOfStarConstellation forRating = new ListOfStarConstellation("For rating");
        return forRating.position(Integer.parseInt(i) - 1).getName();
    }

    private static int sizeList() {
        ListOfStarConstellation forRating = new ListOfStarConstellation("For rating");
        return forRating.size();
    }

//    public static void wouldLikeToRateAnother() throws IOException {
//        String answer = "";
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Would you like to rate another?");
//        answer = scanner.nextLine();
//        if (answer.equals("yes")) {
//            userRating();
//        } else if (answer.equals("no")) {
//            break;
//        }
//    }

    public static void callingSave(ArrayList<Rating> allratings) {
        for (Rating r : allratings) {
            try {
                r.save("ratings.txt");
            } catch (IOException e) {
            }
        }
    }

    public static void load(Scanner scanner) throws IOException {
        while (scanner.hasNextLine()) {
            Rating rate = new Rating("R", 0, "U", 0000);
            rate.load(scanner);
            ratings.add(rate);
        }
    }

    //add average rating
}


