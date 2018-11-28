package ui;

import model.*;
import model.ListOfStarConstellation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static ui.UserUI.fieldFrame;

public class StellarObjectUI{
    private static int fontSize=20;

    public static void displayGUIOptions(User user){
        Object[] options = {"Star Constellations!", "Galaxies!",
                "Ratings!", "User!", "NASA: Picture of the Day!"};

        JOptionPane explore = new JOptionPane();

        JLabel exploreAppLabel = new JLabel("Which would you like to explore?");
        exploreAppLabel.setFont(new Font("Arial", Font.BOLD, fontSize));

        int n = explore.showOptionDialog(fieldFrame,
                exploreAppLabel,
                "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 0) {
            selectStarConstellationsGUI(user);
        }
        else if (n == 1)
            selectGalaxiesGUI(user);
        else if (n == 2)
            try {
                RatingUI.selectRatingGUI(user);
            } catch (IOException e) {
                System.out.println("IOException Caught.");
            }
        else if (n == 3)
//            try {
//                UserUI.addingToLists(user);
//            } catch (IOException e) {
//                System.out.println("IOException Caught.");
//            }
            UserUI.pickList(user);
        else if (n == 4)
            try {
                NASAPictureOfTheDay nasaPictureOfTheDay = new NASAPictureOfTheDay();
                nasaPictureOfTheDay.PictureOfTheDay(user);
            } catch (IOException e) {
                System.out.println("IOException Caught.");
        }
    }

    private static void selectStarConstellationsGUI(User user){
        ListOfStarConstellation losc = new ListOfStarConstellation("Used");

        JPanel buttonPanel = listToButtonsLOSC(losc);

        selectGUI("Star Constellations", buttonPanel, user);
    }

    private static void selectGalaxiesGUI(User user){
        ListOfGalaxy log = new ListOfGalaxy("USED");

        JPanel buttonPanel = listToButtonsLOG(log);

        selectGUI("Galaxies", buttonPanel, user);
    }

    private static void selectNorthGalaxiesGUI(User user){
        ListOfGalaxy north = new ListOfGalaxy("North");
        north.sortNorth();

        JPanel buttonPanel = listToButtonsLOG(north);

        selectGUI("Galaxies", buttonPanel, user);
    }

    private static void selectSouthGalaxiesGUI(User user){
        ListOfGalaxy south = new ListOfGalaxy("North");
        south.sortSouth();

        JPanel buttonPanel = listToButtonsLOG(south);

        selectGUI("Galaxies", buttonPanel, user);
    }

    private static void selectNorthStarConstellationsGUI(User user){
        ListOfStarConstellation north = new ListOfStarConstellation("North");
        north.sortNorth();

        JPanel buttonPanel = listToButtonsLOSC(north);

        selectGUI("Star Constellations", buttonPanel, user);
    }

    private static void selectSouthStarConstellationsGUI(User user){
        ListOfStarConstellation south = new ListOfStarConstellation("South");
        south.sortSouth();

        JPanel buttonPanel = listToButtonsLOSC(south);

        selectGUI("Star Constellations", buttonPanel, user);
    }

    private static void selectGUI(String name, JPanel buttonPanel, User user){
        JFrame frame = new JFrame(name);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(buttonPanel, BorderLayout.WEST);

        JPanel buttonAttributesPanel = new JPanel(new GridBagLayout());
        buttonAttributesPanel.setBorder(new EmptyBorder(7,7,7,7));

        if (name.equals("Star Constellations")) {
            JPanel buttonAttributes = getButtonAttributesLOSC(frame, user);
            buttonAttributesPanel.add(buttonAttributes);
        }
        else if (name.equals("Galaxies")){
            JPanel buttonAttributes = getButtonAttributesLOG(frame, user);
            buttonAttributesPanel.add(buttonAttributes);
        }

        JButton back = getBackButton(frame, user);

        panel.add(back, BorderLayout.SOUTH);

        panel.add(buttonAttributesPanel, BorderLayout.EAST);

        frame.add(panel);
       // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(UserUI.fieldFrame);
        frame.setVisible(true);
    }

    private static JButton getBackButton(JFrame frame, User user) {
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, fontSize));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                displayGUIOptions(user);
            }
        });
        return back;
    }

    private static JPanel getButtonAttributesLOSC(JFrame frame, User user) {
        JPanel buttonAttributes = new JPanel(new GridLayout(0,1,5,5));

        JLabel label = new JLabel("Attributes:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        buttonAttributes.add(label);

        JButton north = new JButton("Northern Sky");
        north.setFont(new Font("Arial", Font.BOLD, fontSize));
        north.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNorthStarConstellationsGUI(user);
                frame.dispose();
            }
        });

        JButton south = new JButton("Southern Sky");
        south.setFont(new Font("Arial", Font.BOLD, fontSize));
        south.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSouthStarConstellationsGUI(user);
                frame.dispose();
            }
        });

        JButton symbol = new JButton("Symbolism");
        symbol.setFont(new Font("Arial", Font.BOLD, fontSize));
        symbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionSymbolism(user);
                frame.dispose();
            }
        });

        JButton stars = new JButton("Brightest Stars");
        stars.setFont(new Font("Arial", Font.BOLD, fontSize));
        stars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionStar(user);
                frame.dispose();
            }
        });

        buttonAttributes.add(north);
        buttonAttributes.add(south);
        buttonAttributes.add(symbol);
        buttonAttributes.add(stars);
        return buttonAttributes;
    }

    private static JPanel getButtonAttributesLOG(JFrame frame, User user) {
        JPanel buttonAttributes = new JPanel(new GridLayout(0,1,5,5));

        JLabel label = new JLabel("Attributes:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        buttonAttributes.add(label);

        JButton north = new JButton("Northern Sky");
        north.setFont(new Font("Arial", Font.BOLD, fontSize));
        north.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNorthGalaxiesGUI(user);
                frame.dispose();
            }
        });

        JButton south = new JButton("Southern Sky");
        south.setFont(new Font("Arial", Font.BOLD, fontSize));
        south.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectSouthGalaxiesGUI(user);
                frame.dispose();
            }
        });

        JButton type = new JButton("Type");
        type.setFont(new Font("Arial", Font.BOLD, fontSize));
        type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionType(user);
                frame.dispose();
            }
        });

        buttonAttributes.add(north);
        buttonAttributes.add(south);
        buttonAttributes.add(type);
        return buttonAttributes;
    }

    private static void actionType(User user){
        JOptionPane actionType = new JOptionPane("Type of Galaxies");

        Object[] options = {"Elliptical", "Spiral", "Irregular"};

        JLabel exploreTypeLabel = new JLabel("Which galactic type would you like to explore?");
        exploreTypeLabel.setFont(new Font("Arial", Font.BOLD, fontSize));

        int n = actionType.showOptionDialog(fieldFrame,
                exploreTypeLabel,
                "Astronomy Exploration",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        selectTypeOfGalaxies(n, user);

    }

    private static void actionSymbolism(User user){
        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
        JPanel actionSymbol = listToSymbolButtonsLOSC(losc);

        symbolismStarPanel("Star Constellations", actionSymbol, user);
    }

    private static void actionStar(User user){
        ListOfStarConstellation losc = new ListOfStarConstellation("LOSC");
        JPanel actionSymbol = listToStarButtonsLOSC(losc);

        symbolismStarPanel("Star Constellations", actionSymbol, user);
    }

    private static void symbolismStarPanel(String name, JPanel actionSymbol, User user){
        JFrame frame = new JFrame(name);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(actionSymbol, BorderLayout.CENTER);

        JButton back = getBackButton(frame, user);

        panel.add(back, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(fieldFrame);
        frame.setVisible(true);
    }

    private static void selectTypeOfGalaxies(int n, User user){
        ListOfGalaxy type = new ListOfGalaxy("Type");
        if (n == 0){
            type.sortType(Galaxy.Type.ELLIPTICAL);
            JPanel buttonPanel = listToButtonsLOG(type);
            selectGUI("Galaxies", buttonPanel, user);
        }
        else if (n == 1){
            type.sortType(Galaxy.Type.SPIRAL);
            JPanel buttonPanel = listToButtonsLOG(type);
            selectGUI("Galaxies", buttonPanel, user);
        }
        else if (n ==2) {
            type.sortType(Galaxy.Type.IRREGULAR);
            JPanel buttonPanel = listToButtonsLOG(type);
            selectGUI("Galaxies", buttonPanel, user);
        }
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


    private static JPanel listToSymbolButtonsLOSC(ListOfStarConstellation losc){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7,7,7,7));

        JLabel label = new JLabel("Symbols of the Star Constellations:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0,1,5,5));
        buttonPane.add(label);

        for (StarConstellation sc: losc) {
            JButton button = new JButton(sc.getSymbolism());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"Okay"};
                    JOptionPane info = new JOptionPane();
                    JLabel infoName = new JLabel(String.valueOf(sc.infoNameForSymbolismAndStarAction()));
                    infoName.setFont(new Font("Arial", Font.BOLD, fontSize));

                    int n = info.showOptionDialog(fieldFrame,
                            infoName,
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

    private static JPanel listToStarButtonsLOSC(ListOfStarConstellation losc){
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(new EmptyBorder(7,7,7,7));

        JLabel label = new JLabel("Brightest Stars of the Star Constellations:");
        label.setFont(new Font("Arial", Font.BOLD, fontSize));

        JPanel buttonPane = new JPanel(new GridLayout(0,1,5,5));
        buttonPane.add(label);

        for (StarConstellation sc: losc) {
            JButton button = new JButton(sc.getStar().getStarName());
            buttonPane.add(button);
            button.setFont(new Font("Arial", Font.BOLD, fontSize));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"Okay"};
                    JOptionPane info = new JOptionPane();
                    JLabel infoName = new JLabel(String.valueOf(sc.infoNameForSymbolismAndStarAction()));
                    infoName.setFont(new Font("Arial", Font.BOLD, fontSize));

                    int n = info.showOptionDialog(fieldFrame,
                            infoName,
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

    public static StarConstellation retrieveStarCon(String i, ListOfStarConstellation losc) {
        int position = Integer.parseInt(i);
        return losc.position(position - 1);
    }

    public static Galaxy retrieveGalaxy(String i, ListOfGalaxy log) {
        int position = Integer.parseInt(i);
        return log.position(position - 1);
    }

}
