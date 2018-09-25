package model;

import java.util.ArrayList;

public class ListOfStarConstellation {
    private String name;

    ArrayList<StarConstellation> listofstarcons;

    public ListOfStarConstellation(String name){
        this.name = name;
        listofstarcons = new ArrayList<StarConstellation>();
    }

    // MODIFIES: this
    // EFFECTS: adds sc to a ListOfStarConstellations
    public void placeIn (StarConstellation sc){
        listofstarcons.add(sc);
    }

    public boolean contains(StarConstellation sc){
        return listofstarcons.contains(sc);
    }
    // MODIFIES: this
    // EFFECTS: orders all starconstellations seen from the north hemisphere
    public static ArrayList<StarConstellation> sortNorth (ArrayList<StarConstellation> starcons) {
        ArrayList<StarConstellation> north = new ArrayList<>();
        for (StarConstellation sc : starcons) {
            if (sc.isLocation()) {
                north.add(sc);
            }
        }
        return north;
    }

    // EFFECTS: prints out every starconstellation in the list
    public void printList(ArrayList<StarConstellation> starcons) {
        for (StarConstellation sc : starcons) {
            System.out.println(sc.getName());
        }
    }
}
