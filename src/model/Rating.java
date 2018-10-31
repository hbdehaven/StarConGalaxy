package model;

import model.exceptions.InvalidRatingValue;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Rating implements Loadable, Saveable {
    private String name; // what star constellation the rating is for; in the form of "Rating for " + starcon name
    private int rating; //rating is out of 1 to 5 stars
    private double date; // month and day of updated rating (inputted as MMDD)

    public Rating(String name, int rating, int date) {
        this.name = name;
        this.rating = rating;
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
        this.date = Double.parseDouble(values[2]);
    }

    // inspired by FileReaderWriter
    @Override
    public void save(String fileName) throws IOException {
        Files.write(Paths.get(fileName), Arrays.asList(name + "," + rating + "," + date),
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating rating1 = (Rating) o;
        return rating == rating1.rating &&
                Double.compare(rating1.date, date) == 0 &&
                Objects.equals(name, rating1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rating, date);
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

    // EFFECTS get date of Rating
    public double getDate() {
        return date;
    }

    //setters
    public void setValue(int val){
        this.rating = val;
    }

}
