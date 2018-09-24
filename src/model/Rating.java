package model;

public class Rating {
    private int rating; //rating is out of 1 to 5 stars
    private String user; // name of user who inputted rating
    private double date; // month and day of updated rating (inputted as MMDD)

    public Rating(int rating, String user, int date){
        this.rating = rating;
        this.user = user;
        this.date = date;
    }

    // getters
    public int getRating() {return rating; }
    public String getUser() {return user;}
    public double getDate() {return date;}
}
