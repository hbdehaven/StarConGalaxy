package model;

import java.util.Scanner;
import model.StarConstellation;

public class Rating {
    private int rating; //rating is out of 1 to 5 stars
    private String user; // name of user who inputted rating
    private double date; // month and day of updated rating (inputted as MMDD)

    public Rating(int rating, String user, int date){
        this.rating = rating;
        this.user = user;
        this.date = date;
    }

    public static void userRating(){
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        while (true){
            System.out.println("Would you like to rate "); //getName());
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                System.out.println("How many stars out of 5 would you like to rate __?");
                answer = scanner.nextLine();
                Rating starconnamerating = new Rating(0,"",0);
                int num = Integer.parseInt(answer);
                starconnamerating.rating = num;
                System.out.println("What's your name?");
                answer = scanner.nextLine();
                starconnamerating.user = answer;
                System.out.println("What's the date in the form of MMDD?");
                answer = scanner.nextLine();
                int date = Integer.parseInt(answer);
                starconnamerating.date = date;
                //makes this rating that star constellation's rating
                //also adds to the star constellations list of rating
            }
            if (answer.equals("no")){
                System.out.println("Okay!");
                scanner.nextLine();
            }
            else if (answer.equals("quit")){
                break;
            }
        }
    }

    // getters
    // EFFECTS: get rating of Rating
    public int getRating() {return rating; }
    // EFFECTS: get user of Rating
    public String getUser() {return user;}
    // EFFECTS get date of Rating
    public double getDate() {return date;}
}
