package ui;

import model.*;
import model.exceptions.InvalidStringInput;
import model.ListOfStarConstellation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import static ui.UserUI.fieldFrame;

public class StellarObjectUI{
    private static Scanner userInput;
    private static int fontSize=18;

//    public static void exploreApp(User user)  {
//        boolean continueWhile = true;
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        while (continueWhile){
//            displayOptions();
//            answer = userInput.next();
//            answer = answer.toLowerCase();
//
//            if (answer.equals("s")){
//                selectStarConstellations(user);
//            }
//
//            else if (answer.equals("g")){
//                selectGalaxies(user);
//            }
//            else if (answer.equals("r")){
//                try {
//                    RatingUI.selectRating(user);
//                } catch (IOException e) {
//                    System.out.println("IOException Caught.");
//                }
//            }
//            else if (answer.equals("u")){
//                try {
//                    UserUI.addingToLists(user);
//                } catch (IOException e) {
//                    System.out.println("IOException Caught.");
//                }
//            }
//            else if (answer.equals("nasa")){
//                try {
//                    NASAPictureOfTheDay nasaPictureOfTheDay = new NASAPictureOfTheDay();
//                    nasaPictureOfTheDay.addObserver(user);
//                    nasaPictureOfTheDay.PictureOfTheDay();
//                } catch (IOException e) {
//                    System.out.println("IOException Caught.");                }
//            }
//            else if (answer.equals("q")){
//                continueWhile = false;
//                UserUI.whileCreateUser = false;
//                UserUI.userLogInBoolean = false;
//            }
//            else System.out.println("Invalid Selection");
//        }
//        System.out.println("Thank you!");
//    }
//
//    // inspired by AccountNotRobust
//    private static void displayOptions() {
//        System.out.println("\nWhat would you like to explore? Select from:");
//        System.out.println("\ts -> star constellations!");
//        System.out.println("\tg -> galaxies!");
//        System.out.println("\tr -> upload a rating of your favourite stellar objects!");
//        System.out.println("\tu -> add to your user's seen lists and want to see lists!");
//        System.out.println("\tnasa -> see NASA's astronomy picture of the day!");
//        System.out.println("\tq -> quit");
//    }

    public static void displayGUIOptions(User user){
        Object[] options = {"Star Constellations!", "Galaxies!",
                "Ratings!", "User!", "NASA: Picture of the Day!"};

        JLabel exploreAppLabel = new JLabel("Which would you like to explore?");
        exploreAppLabel.setFont(new Font("Arial", Font.BOLD, fontSize));

        int n = JOptionPane.showOptionDialog(fieldFrame,
                exploreAppLabel,
                "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0)
            selectStarConstellationsGUI();
        else if (n == 1)
            selectGalaxiesGUI();
        else if (n == 2)
            try {
                RatingUI.selectRatingGUI(user);
            } catch (IOException e) {
                System.out.println("IOException Caught.");
            }
        else if (n == 3)
            try {
                UserUI.addingToLists(user);
            } catch (IOException e) {
                System.out.println("IOException Caught.");
            }
        else if (n == 4)
            try {
                NASAPictureOfTheDay nasaPictureOfTheDay = new NASAPictureOfTheDay();
                nasaPictureOfTheDay.addObserver(user);
                nasaPictureOfTheDay.PictureOfTheDay();
            } catch (IOException e) {
                System.out.println("IOException Caught.");
        }
    }

    private static void selectStarConstellationsGUI(){
        ListOfStarConstellation losc = new ListOfStarConstellation("Used");

        JPanel buttonPanel = listToButtonsLOSC(losc);

        selectGUI("Star Constellations", buttonPanel);
    }

    private static void selectGalaxiesGUI(){
        ListOfGalaxy log = new ListOfGalaxy("USED");

        JPanel buttonPanel = listToButtonsLOG(log);

        selectGUI("Galaxies", buttonPanel);
    }

    private static void selectGUI(String name, JPanel buttonPanel){
        JFrame frame = new JFrame(name);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.WEST);

        JPanel buttonAttributesPanel = new JPanel(new GridBagLayout());
        buttonAttributesPanel.setBorder(new EmptyBorder(7,7,7,7));

        if (name.equals("Star Constellations")) {
            JPanel buttonAttributes = getButtonAttributesLOSC();
            buttonAttributesPanel.add(buttonAttributes);
        }
        else if (name.equals("Galaxies")){
            JPanel buttonAttributes = getButtonAttributesLOG();
            buttonAttributesPanel.add(buttonAttributes);
        }

        panel.add(buttonAttributesPanel, BorderLayout.EAST);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(UserUI.fieldFrame);
        frame.setVisible(true);
    }

    private static JPanel getButtonAttributesLOSC() {
        JPanel buttonAttributes = new JPanel(new GridLayout(0,1,5,5));

        JLabel label = new JLabel("Attributes:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        buttonAttributes.add(label);

        JButton north = new JButton("Northern Sky");
        north.setFont(new Font("Arial", Font.BOLD, fontSize));
        JButton south = new JButton("Southern Sky");
        south.setFont(new Font("Arial", Font.BOLD, fontSize));
        JButton symbol = new JButton("Symbolism");
        symbol.setFont(new Font("Arial", Font.BOLD, fontSize));
        JButton stars = new JButton("Brightest Stars");
        stars.setFont(new Font("Arial", Font.BOLD, fontSize));

        buttonAttributes.add(north);
        buttonAttributes.add(south);
        buttonAttributes.add(symbol);
        buttonAttributes.add(stars);
        return buttonAttributes;
    }

    private static JPanel getButtonAttributesLOG() {
        JPanel buttonAttributes = new JPanel(new GridLayout(0,1,5,5));

        JLabel label = new JLabel("Attributes:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        buttonAttributes.add(label);

        JButton north = new JButton("Northern Sky");
        north.setFont(new Font("Arial", Font.BOLD, fontSize));
        JButton south = new JButton("Southern Sky");
        south.setFont(new Font("Arial", Font.BOLD, fontSize));
        JButton type = new JButton("Type");
        type.setFont(new Font("Arial", Font.BOLD, fontSize));

        buttonAttributes.add(north);
        buttonAttributes.add(south);
        buttonAttributes.add(type);
        return buttonAttributes;
    }


    private static JPanel listToButtonsLOSC(ListOfStarConstellation losc){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7,7,7,7));

        JLabel label = new JLabel("Star Constellations:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0,1,5,5));
        buttonPane.add(label);

        for (StarConstellation sc: losc) {
            JButton button = new JButton(sc.getName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"Okay"};
                    JOptionPane info = new JOptionPane();
                    JLabel allInfo = new JLabel(String.valueOf(sc.allInformation()));
                    allInfo.setFont(new Font("Arial", Font.BOLD, fontSize));


                    int n = info.showOptionDialog(fieldFrame,
                            allInfo,
                            "Astronomy Exploration",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0)
                        listToButtonsLOSC(losc);
                }
            });
        }
        buttonPanel.add(buttonPane);

        return buttonPanel;
    }

    private static JPanel listToButtonsLOG(ListOfGalaxy log){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7,7,7,7));

        JLabel label = new JLabel("Galaxies:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0,1,5,5));
        buttonPane.add(label);

        for (Galaxy g: log){
            JButton button = new JButton(g.getName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"Okay"};
                    JOptionPane info = new JOptionPane();
                    JLabel allInfo = new JLabel(String.valueOf(g.allInformation()));
                    allInfo.setFont(new Font("Arial", Font.BOLD, fontSize));

                    int n = info.showOptionDialog(fieldFrame,
                            allInfo,
                            "Astronomy Exploration",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (n == 0)
                        listToButtonsLOG(log);
                }
            });
        }

        buttonPanel.add(buttonPane);

        return buttonPanel;
    }

    public static String nameOfStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1).getName();
    }

    public static StarConstellation retrieveStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1);
    }

    private static void whichAttribute(User user, ListOfStarConstellation losc) {
        String answer = "";
        userInput = new Scanner(System.in);

        while (true){
            System.out.println("Great!");
            System.out.println("Would you like to explore by location, symbolism, or brightest Star?");

            answer = userInput.next();
            answer = answer.toLowerCase();

            exploreBy(user, answer, losc);
        }
    }

    private static void exploreBy(User user, String answer, ListOfStarConstellation losc) {
        userInput = new Scanner(System.in);

        losc.restore();

        if (answer.equals("location")){
            locationAttribute(user, losc);
        }
        else if (answer.equals("symbolism")){
            System.out.println("Here are the symbols of all our Star Constellations.");
            losc.printListSymbols();
            System.out.println(" ");
        }
        else if (answer.equals("brightest star") || answer.equals("star")){
            System.out.println("Here are the brightest stars of all our Star Constellations.");
            losc.printListStars();
            System.out.println(" ");
        }
    }

    private static void locationAttribute(User user, ListOfStarConstellation losc){
        String answer = "";
        userInput = new Scanner(System.in);
        System.out.println("Which part of the sky? Northern or Southern?");
        try {
            answer = userInput.next();
            answer = answer.toLowerCase();
            losc.sort(answer);
        } catch (InvalidStringInput invalidStringInput) {
            System.out.println("Invalid input, try again.");
        }
        anotherAttribute(user, losc);
        System.out.println(" ");
    }

    private static void anotherAttribute(User user, ListOfStarConstellation losc) {
        String answer = "";
        userInput = new Scanner(System.in);

        System.out.println("Would you like to explore another attribute? ");

        answer = userInput.next();

        if (answer.equals("yes")){
            whichAttribute(user, losc);
        }
        else if (answer.equals("no")){
//            selectStarConstellations(user);
            System.out.println(" ");
        }
    }

//    private static void selectGalaxies(User user) {
//        ListOfGalaxy log = new ListOfGalaxy("Used");
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("Let's explore Galaxies");
//            log.printList();
//            System.out.println("Would you like to explore individually or by attribute?");
//            System.out.println("To return to main display, input return.");
//
//            answer = userInput.next();
//            answer = answer.toLowerCase();
//
//            if (answer.equals("individually")){
//                whichGalaxy(user, log);
//            }
//            else if (answer.equals("by attribute") || answer.equals( "attribute")){
//                whichGalacticAttribute(log);
//            }
//            else if (answer.equals("return")){
//                displayGUIOptions(user);
//            }
//        }
//    }
//
//    private static void whichGalaxy(User user, ListOfGalaxy log) {
//        String position = "";
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        System.out.println("Great!");
//        System.out.println("Which Galaxy? Indicate by inputted the location in the list.");
//
//        position = userInput.next();
//
//        System.out.println("Here is all associated data with " + nameOfGalaxy(position, log));
//        retrieveGalaxy(position, log).allInformation();
//        System.out.println("                             ");
//        System.out.println("Would you like to explore another? ");
//
//        answer = userInput.next();
//
//        if (answer.equals("yes")){
//            whichGalaxy(user, log);
//        }
//        else if (answer.equals("no")){
//            selectGalaxies(user);
//            System.out.println(" ");
//        }
//    }

    public static String nameOfGalaxy(String i, ListOfGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1).getName();
    }

    public static Galaxy retrieveGalaxy(String i, ListOfGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1);
    }

//    private static void whichGalacticAttribute(ListOfGalaxy log) {
//        String answer = "";
//        userInput = new Scanner(System.in);
//
//        while (true){
//            System.out.println("Great!");
//            System.out.println("Would you like to explore by location or type?");
//
//            answer = userInput.next();
//            answer = answer.toLowerCase();
//
//            galacticExploreBy(answer, log);
//        }
//    }

//    private static void galacticExploreBy(String answer, ListOfGalaxy log) {
//        userInput = new Scanner(System.in);
//
//        if (answer.equals("location")) {
//            System.out.println("Which part of the sky? Northern or Southern?");
//
//            try {
//                answer = userInput.next();
//                answer = answer.toLowerCase();
//                log.sort(answer);
//            } catch (InvalidStringInput invalidStringInput) {
//                System.out.println("Invalid input, try again.");
//            }
//            System.out.println(" ");
//
//        } else if (answer.equals("type")) {
//            System.out.println("Here are the type of all our Galaxies.");
//            log.printListType();
//            System.out.println(" ");
//        }
//
//    }

}
