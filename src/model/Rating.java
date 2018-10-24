package model;

import model.exceptions.InvalidRatingValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

// store rating and change a rating // each constellation has a list of ratings that has a date and time and a user (String name)

public class Rating implements Loadable, Saveable {
    private String name; // what star constellation the rating is for; in the form of "Rating for " + starcon name
    private int rating; //rating is out of 1 to 5 stars
    private String user; // name of user who inputted rating
    private double date; // month and day of updated rating (inputted as MMDD)

    public Rating(String name, int rating, String user, int date) {
        this.name = name;
        this.rating = rating;
        this.user = user;
        this.date = date;
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
    public void save(String fileName) throws IOException {
        Files.write(Paths.get(fileName), Arrays.asList(name + "," + rating + "," + user + "," + date),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public boolean isValid() throws InvalidRatingValue {
        if (rating > 0 && rating < 6){
            return true;
        }
        else {
            throw new InvalidRatingValue("Invalid. Must be less than 5.");
        }
    }



    // getters
    //EFFECTS: get name of Rating
    public String getNameOfR() {
        return name;
    }

    // EFFECTS: get rating of Rating
    public int getRating() {
        return rating;
    }

    // EFFECTS: get user of Rating
    public String getUser() {
        return user;
    }

    // EFFECTS get date of Rating
    public double getDate() {
        return date;
    }

    //setters
    public void setValue(int val){
        this.rating = val;
    }

}
