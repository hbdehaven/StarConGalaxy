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

import static ui.UserUI.fieldFrame;

public class RatingUI {
    private static ArrayList<Rating> ratings = new ArrayList<>();
    private static Scanner userInput;
    private static RatingDatabase ratingDatabase = new RatingDatabase();
    private static int fontSize = 18;

    public static void selectRatingGUI(User user) throws IOException {
        Scanner scanner1 = new Scanner(new BufferedReader(new FileReader(new File("ratings.txt"))));
        load(scanner1);

        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
        ListOfGalaxy log = new ListOfGalaxy("LOG");

        Object[] options = {"Star Constellations", "Galaxies", "Back"};

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
            selectStarConstellationsGUI(user, losc);
        else if (n == 1)
            selectGalaxiesGUI(user, log);
        else if (n == 2)
            StellarObjectUI.displayGUIOptions(user);
    }

    private static void selectStarConstellationsGUI(User user, ListOfStarConstellation losc){
        JPanel buttonPanel = whichSCGUI(user, losc);

        selectGUIOnePanel("Star Constellations", buttonPanel, user);
    }

    private static void selectGalaxiesGUI(User user, ListOfGalaxy log){
        JPanel buttonPanel = whichGGUI(user, log);

        selectGUIOnePanel("Galaxies", buttonPanel, user);
    }

    private static JButton getBackButton(JFrame frame, User user) {
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, fontSize));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    selectRatingGUI(user);
                } catch (IOException e1) {
                    System.out.println("Caught IOEx from selectRatingGUI");
                }
            }
        });
        return back;
    }

    public static void selectGUIOnePanel(String name, JPanel buttonPanel, User user){
        JFrame frame = new JFrame(name);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.CENTER);

        JButton back = getBackButton(frame, user);

        panel.add(back, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(fieldFrame);
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
    }

    private static void checkValidity(User user, Rating rating) {
        String answer = "";
        userInput = new Scanner(System.in);
        ArrayList<Rating> allRatings = new ArrayList<>();

        while (true) {
            try {
                rating.isValid();
                allRatings.add(rating);
                callingSave(allRatings);
                ratingDatabase.addUserListRating(user, rating);
                StellarObjectUI.displayGUIOptions(user);
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
