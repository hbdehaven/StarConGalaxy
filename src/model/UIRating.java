package model;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UIRating {
    // had to make fields of rating public to do this

    // EFFECTS: asks questions and saves data based on user's input
    public static void userRating() throws IOException {
        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
        Rating rate = new Rating("", 0, "", 0000);
        rate.load(scanner1);
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
                    Rating starconnamerating = new Rating(nameOfStarCon(pos), 0, "", 0);
                    int num = Integer.parseInt(answer);
                    starconnamerating.rating = num;
                    System.out.println("What's your name?");
                    String user = scanner.nextLine();
                    starconnamerating.user = user;
                    System.out.println("What's the date in the form of MMDD?");
                    answer = scanner.nextLine();
                    int date = Integer.parseInt(answer);
                    starconnamerating.date = date;
                    System.out.println("Is this correct? Rating of " + num + " by " + user + " on " + date + "?");
                    answer = scanner.nextLine();
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

    private static void wouldLikeToRateStar(String pos){
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many stars out of 5 would you like to rate " + nameOfStarCon(pos) + "?");
        answer = scanner.nextLine();
        Rating starconnamerating = new Rating(nameOfStarCon(pos), 0, "", 0);
        int num = Integer.parseInt(answer);
        starconnamerating.rating = num;
        System.out.println("What's your name?");
        String user = scanner.nextLine();
        starconnamerating.user = user;
        System.out.println("What's the date in the form of MMDD?");
        answer = scanner.nextLine();
        int date = Integer.parseInt(answer);
        starconnamerating.date = date;
        System.out.println("Is this correct? Rating of " + num + " by " + user + " on " + date + "?");
        answer = scanner.nextLine();
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

}


