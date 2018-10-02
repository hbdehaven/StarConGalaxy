package model;

import java.util.ArrayList;

// inspired by IntegerSetIntersect
public class ListOfStarConstellation {
    private String name;
    private ArrayList<StarConstellation> listofstarcons;

    public ListOfStarConstellation(String name){
        this.name = name;

        Star alphaAndro = new Star("Alpha Andromedae", 2.06);

        Star altair = new Star("Altair", .76);

        Star phact = new Star("Phact", 2.645);

        StarConstellation andromeda = new StarConstellation("Andromeda", 97, StarConstellation.Location.NORTH, "The Chained Lady", alphaAndro);

        StarConstellation aquila = new StarConstellation("Aquila", 16.73, StarConstellation.Location.NORTH, "The Eagle", altair);

        StarConstellation columba = new StarConstellation("Columba", 261, StarConstellation.Location.SOUTH, "The Dove", phact);

        listofstarcons = new ArrayList<>();
        listofstarcons.add(andromeda);
        listofstarcons.add(aquila);
        listofstarcons.add(columba);
    }

    // MODIFIES: this
    // EFFECTS: orders listofstarcons from north hemisphere
    public ListOfStarConstellation sortNorth(ListOfStarConstellation losc){
        for (StarConstellation sc: losc.listofstarcons){
            if (locationSouth(sc.getLocation())){
                losc.remove(sc);
                return losc;
            }
        }
        return losc;
    }

    // MODIFIES: this
    // EFFECTS: orders listofstarcons from the south hemisphere
    public ListOfStarConstellation sortSouth (ListOfStarConstellation losc) {
        for (StarConstellation sc : losc.listofstarcons) {
            if (locationNorth(sc.getLocation())) {
                losc.remove(sc);
                return losc;
            }
        }
        return losc;
    }

    // EFFECTS: prints out every starconstellation in the list
    public void printList() {
        for (StarConstellation sc : listofstarcons) {
            System.out.println(sc.getName());
        }
    }

    // EFFECTS: prints out list of starcons that are visible from North
    public void getNorth(ListOfStarConstellation list){
        sortNorth(list);
        printList();
    }

    // MODIFIES: this
    // EFFECTS: restores listofstarcons to the entire list of starcons
    public void restore (ListOfStarConstellation losc){
        Star alphaAndro = new Star("Alpha Andromedae", 2.06);

        Star altair = new Star("Altair", .76);

        Star phact = new Star("Phact", 2.645);

        StarConstellation andromeda = new StarConstellation("Andromeda", 97, StarConstellation.Location.NORTH, "The Chained Lady", alphaAndro);

        StarConstellation aquila = new StarConstellation("Aquila", 16.73, StarConstellation.Location.NORTH, "The Eagle", altair);

        StarConstellation columba = new StarConstellation("Columba", 261, StarConstellation.Location.SOUTH, "The Dove", phact);
        ArrayList<StarConstellation> restored = new ArrayList<>();
        restored.add(andromeda);
        restored.add(aquila);
        restored.add(columba);
        losc.listofstarcons = restored;
    }

    //getters
    //EFFECTS: retrieve name of ListOfStarConstellation
    public String getLOSCname() {
        return name;
    }
    public ArrayList<StarConstellation> getListofstarcons() {
        return listofstarcons;
    }

    public boolean locationNorth(StarConstellation.Location location){
        return (location == StarConstellation.Location.NORTH);
    }

    public boolean locationSouth(StarConstellation.Location location){
        return (location == StarConstellation.Location.SOUTH);
    }


//// ask for help with greatest distance one
//    public void greatestDis(ListOfStarConstellation starcons){
//
//    }

    // MODIFIES: this
    // EFFECTS: adds sc to a ListOfStarConstellations
    public void placeIn (StarConstellation sc){
        listofstarcons.add(sc);
    }

    // MODIFIES: this
    // EFFECTS: removes sc to a ListofStarConstellations
    public void remove (StarConstellation sc){
        listofstarcons.remove(sc);
    }

    // EFFECTS: checks if listofstarcons contains sc
    public boolean contains(StarConstellation sc){
        return listofstarcons.contains(sc);
    }

    // EFFECTS: returns size of ListOfStarCONstellation
    public int size(){
        return listofstarcons.size();
    }

    //EFFECTS: returns that StarConstellation at pos x
    public StarConstellation position(int x){
        return listofstarcons.get(x);
    }
}
