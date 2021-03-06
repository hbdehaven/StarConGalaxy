package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static ui.StellarObjectUI.displayGUIOptions;
import static ui.UserUI.fieldFrame;

public class NASAPictureOfTheDay{
    // inspired by P10 API given example

    private static String photoURL;
    private static int fontSize=20;

    public NASAPictureOfTheDay(){
        super();
    }
    public static void PictureOfTheDay(User user) throws IOException {

        BufferedReader br = null;

        try {
            String theURL = "https://api.nasa.gov/planetary/apod?api_key=wUxYiEoOzHKrc9X1CNekGRPlLGRK1IxXBHXIBFPT"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());

            }

            ArrayList list = splitOnRegex(sb.toString(), "\"");
            String thePhotoURL = (String) list.get(list.size()-2);
            photoURL = thePhotoURL;
            pictureGUI(thePhotoURL, user);
        } finally {

            if (br != null) {
                br.close();
            }
        }

    }

    private static ArrayList<String> splitOnRegex(String line, String regex) {
        String[] splits = line.split(regex);
        return new ArrayList<String>(Arrays.asList(splits));
    }
    
    private static void pictureGUI(String url, User user){
        JFrame frame = new JFrame("NASA Picture of the Day");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel labelTitle = new JLabel();
        labelTitle.setText("Click!");
        labelTitle.setFont(new Font("Arial", Font.BOLD, fontSize));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("NASA Picture of the Day");
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWebPage(photoURL);
            }
        });

        JButton back = getBackButton(frame, user);

        panel.add(labelTitle);
        panel.add(button);
        panel.add(back);

        frame.add(panel);

        frame.setSize(450,175);
        frame.setLocationRelativeTo(fieldFrame);
        frame.setVisible(true);
    }

    // inspired by https://stackoverflow.com/questions/10967451/open-a-link-in-browser-with-java-button
    private static void openWebPage(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            System.out.println("Could not open webpage");
        }
    }

    private static JButton getBackButton(JFrame frame, User user) {
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, fontSize));
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                displayGUIOptions(user);
            }
        });
        return back;
    }
}
