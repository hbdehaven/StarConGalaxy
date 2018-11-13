package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class NASAPictureOfTheDay {
    // inspired by P10 API given example

    public static void NASAPictureOfTheDay() throws IOException {

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
            ArrayList list = splitOnRegex(sb.toString(), ",");
            String urlLabeledString = (String) list.get(13);
            ArrayList nextList = splitOnRegex(urlLabeledString, "\"");
            String thePhotoURL = (String) nextList.get(3);
            System.out.println(thePhotoURL);

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

}
