package model;

import java.util.ArrayList;

// inspired by IntegerSetIntersect
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

    // EFFECTS: checks if listofstarcons contains sc
    public boolean contains(StarConstellation sc){
        return listofstarcons.contains(sc);
    }

    public int size(){
        return listofstarcons.size();
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

    // MODIFIES: this
    // EFFECTS: orders all starconstellations seen from the north hemisphere
    public static ArrayList<StarConstellation> sortSouth (ArrayList<StarConstellation> starcons) {
        ArrayList<StarConstellation> south = new ArrayList<>();
        for (StarConstellation sc : starcons) {
            if (!sc.isLocation()) {
                south.add(sc);
            }
        }
        return south;
    }

// ask for help with greatest distance one

    // EFFECTS: prints out every starconstellation in the list
    public void printList(ArrayList<StarConstellation> starcons) {
        for (StarConstellation sc : starcons) {
            System.out.println(sc.getName());
        }
    }
}
