package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// store rating and change a rating // each constellation has a list of ratings that has a date and time and a user (String name)

public class Rating implements Loadable, Saveable {
    private String name; // what star constellation the rating is for; in the form of "Rating for " + starcon name
    private int rating; //rating is out of 1 to 5 stars
    private String user; // name of user who inputted rating
    private double date; // month and day of updated rating (inputted as MMDD)

    public Rating(String name, int rating, String user, int date){
        this.name = name;
        this.rating = rating;
        this.user = user;
        this.date = date;
    }

    // EFFECTS: asks questions and saves data based on user's input
    public static void userRating() {
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
                System.out.println("Which star constellation would you like to rate? Please pick a number from 1 to 3");
                pos = scanner.nextLine();
                System.out.println("You'd like to rate " + forrating.position(Integer.parseInt(pos) - 1).getName() + "?");
                answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    System.out.println("How many stars out of 5 would you like to rate " +
                            forrating.position(Integer.parseInt(pos) - 1).getName() + "?");
                    answer = scanner.nextLine();
                    Rating starconnamerating = new Rating(forrating.position(Integer.parseInt(pos) - 1).getName(),
                            0, "", 0);
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
                    if (answer.equals("yes")) {
                        System.out.println("Great! Thanks again for your input.");
                        //////////////////////////////////
                        allratings.add(starconnamerating);
                        for(Rating r : allratings) {
                            try {
                                r.save("ratings.txt");
                            } catch (IOException e) {
                            }
                        }
                        // SAVE THIS INPUT
                        /////////////////////////////////
                        System.out.println("Would you like to rate another?");
                        answer = scanner.nextLine();
                        if (answer.equals("yes")) {
                            userRating();
                        } else if (answer.equals("no")) {
                            break;
                        }
                    } else if (answer.equals("no")) {
                        System.out.println("Let's try again.");
                        userRating();
                    } else {
                        break;
                    }
                    //makes this rating that star constellation's rating
                    //also adds to the star constellations list of rating
                }
            }
            else if (answer.equals("no")){
                System.out.println("Okay!");
                break;
            }
        }
    }

    // Scanner scanner = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))))
    // rating.load(scanner);

    @Override
    public void load(Scanner scanner) throws IOException {
        String value = scanner.nextLine();
        String[] values = value.split(",");
        this.name = values[0];
        this.rating = Integer.parseInt(values[1]);
        this.user = values[2];
        this.date = Double.parseDouble(values[3]);
    }

    // inspired by FileReaderWriter
    @Override
    public void save (String fileName) throws IOException {
        Files.write(Paths.get(fileName), Arrays.asList(name+","+rating+","+user+","+date), StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    // getters
    //EFFECTS: get name of Rating
    public String getNameOfR() {return name;}
    // EFFECTS: get rating of Rating
    public int getRating() {return rating;}
    // EFFECTS: get user of Rating
    public String getUser() {return user;}
    // EFFECTS get date of Rating
    public double getDate() {return date;}

}
