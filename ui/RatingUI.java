package ui;

import model.*;
import model.exceptions.InvalidRatingValue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static ui.StellarObjectUI.nameOfGalaxy;
import static ui.StellarObjectUI.nameOfStarCon;
import static ui.UserUI.fieldFrame;

public class RatingUI {
    private static ArrayList<Rating> ratings = new ArrayList<>();
    private static Scanner userInput;
    private static boolean whileLoop;
    private static RatingDatabase ratingDatabase = new RatingDatabase();
    private static int fontSize = 18;

    public static void selectRatingGUI(User user) throws IOException {
        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
        load(scanner1);

        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
        ListOfGalaxy log = new ListOfGalaxy("LOG");

        Object[] options = {"Star Constellations", "Galaxies"};

        JLabel label = new JLabel("Which Stellar Object would you like to Rate?");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JOptionPane rating = new JOptionPane();
        int n = rating.showOptionDialog(fieldFrame,
                label,
                "Ratings",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0)
            selectGalaxiesGUI(user, log);
        else if (n == 1)
            selectStarConstellationsGUI(user, losc);
    }

//    public static void selectRating(User user) throws IOException {
//        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
//        load(scanner1);
//
//        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
//        ListOfGalaxy log = new ListOfGalaxy("LOG");
//        whileLoop = true;
//
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        while (whileLoop) {
//            System.out.println("For which Stellar Object would you like to upload a Rating?");
//            System.out.println("Galaxies or Star Constellations?");
//
//            answer = userInput.next();
//            answer = answer.toLowerCase();
//
//            if(answer.equals("galaxies")){
//                whichGalaxy(user, log);
//            }
//            // not working for "star constellations"
//            else if (answer.equals("starconstellations")|answer.equals("star constellations")){
//                whichStarConstellation(user, losc);
//            }
//        }
//    }

//    private static void whichGalaxy(User user, ListOfGalaxy log) {
//        String position = "";
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        log.printList();
//        System.out.println("For which Galaxy would you like to upload a Rating?");
//        System.out.println("Please input the position of the Galaxy in the list.");
//
//        position = userInput.next();
//
//        String nameofGalaxy = nameOfGalaxy(position, log);
//        System.out.println("You would like to rate " + nameofGalaxy + "?");
//
//        answer = userInput.next();
//
//        if (answer.equals("yes")) {
//            createRatingGUI(user, nameofGalaxy);
//        } else if (answer.equals("no")) {
//            whichGalaxy(user, log);
//        }
//    }

//    private static void whichStarConstellation(User user, ListOfStarConstellation losc) {
//        String position = "";
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        losc.printList();
//        System.out.println("For which Star Constellation would you like to upload a Rating?");
//        System.out.println("Please input the position of the Star Constellation in the list.");
//
//        position = userInput.next();
//
//        String nameOfStarCon = nameOfStarCon(position, losc);
//        System.out.println("You would like to rate " + nameOfStarCon + "?");
//
//        answer = userInput.next();
//
//        if (answer.equals("yes")) {
//            createRatingGUI(user, nameOfStarCon);
//        } else if (answer.equals("no")) {
//            whichStarConstellation(user, losc);
//        }
//    }

    private static void selectStarConstellationsGUI(User user, ListOfStarConstellation losc){
        JPanel buttonPanel = whichSCGUI(user, losc);

        selectGUI("Star Constellations", buttonPanel);
    }

    private static void selectGalaxiesGUI(User user, ListOfGalaxy log){
        JPanel buttonPanel = whichGGUI(user, log);

        selectGUI("Galaxies", buttonPanel);
    }

    private static void selectGUI(String name, JPanel buttonPanel){
        JFrame frame = new JFrame(name);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(UserUI.fieldFrame);
        frame.setVisible(true);
    }

    private static JPanel whichSCGUI(User user, ListOfStarConstellation losc){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7, 7, 7, 7));

        JLabel label = new JLabel("Star Constellations:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPane.add(label);

        for (StarConstellation sc : losc) {
            JButton button = new JButton(sc.getName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createRatingGUI(user, sc.getName());
                }
            });
        }
        buttonPanel.add(buttonPane);

        return buttonPanel;
    }

    private static JPanel whichGGUI(User user, ListOfGalaxy log){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7, 7, 7, 7));

        JLabel label = new JLabel("Galaxies:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0, 1, 5, 5));
        buttonPane.add(label);

        for (Galaxy g: log) {
            JButton button = new JButton(g.getName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createRatingGUI(user, g.getName());
                }
            });
        }
        buttonPanel.add(buttonPane);

        return buttonPanel;
    }


    private static void createRatingGUI(User user, String ratingName){
        JOptionPane creatingRating = new JOptionPane();

        JLabel dateLabel = new JLabel("What's the date in the form of MMDD?");
        dateLabel.setFont(new Font("Arial", Font.BOLD, fontSize));

        String answer = creatingRating.showInputDialog(fieldFrame,dateLabel);
        int date = Integer.parseInt(answer);

        JLabel rateLabel = new JLabel("How many stars out of 5 would you like to rate " + ratingName + "?");
        rateLabel.setFont(new Font("Arial", Font.BOLD, fontSize));

        String rateAnswer = creatingRating.showInputDialog(fieldFrame,rateLabel);
        int num = Integer.parseInt(rateAnswer);

        Rating rating = new Rating(ratingName, num, date);

        checkValidity(user, rating);

        uploadAnotherQuestion(user);
    }

//    private static void createRating(User user, String ratingName){
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        System.out.println("What's the date in the form of MMDD?");
//        answer = userInput.next();
//        int date = Integer.parseInt(answer);
//
//        System.out.println("How many stars out of 5 would you like to rate " + ratingName + "?");
//        answer = userInput.next();
//        int num = Integer.parseInt(answer);
//
//        Rating rating = new Rating(ratingName, num, date);
//
//        checkValidity(user, rating);
//
//        uploadAnotherQuestion(user);
//    }

    private static void checkValidity(User user, Rating rating){
        String answer = "";
        userInput = new Scanner(System.in);
        ArrayList<Rating> allRatings = new ArrayList<>();

        while (true) {
            try {
                rating.isValid();
                allRatings.add(rating);
                callingSave(allRatings);
                ratingDatabase.addUserListRating(user, rating);
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
                    selectRatingGUI(user);
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

    private static void averageRatingStellarObject(String SOname) throws IOException {
        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
        load(scanner1);
        int sumOfRatings = 0;
        int amountOfRatings = 0;
        for(Rating r: ratings){
            if (r.getNameOfR().equals(SOname)){
                sumOfRatings += r.getRating();
                amountOfRatings++;
            }
        }
        try {
            int average = sumOfRatings / amountOfRatings;
            System.out.println(average);
        }
        catch (ArithmeticException ae){
            System.out.println("No ratings uploaded for that Stellar Object");
        }
    }


}
