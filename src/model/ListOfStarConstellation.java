package model;

import model.exceptions.InvalidStringInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.StellarObject.locationNorth;
import static model.StellarObject.locationSouth;

// inspired by IntegerSetIntersect
public class ListOfStarConstellation{
    private String name;
    private ArrayList<StarConstellation> listofstarcons;

    public ListOfStarConstellation(String name){
        this.name = name;

        this.restore();
    }

    // inspired by https://www.baeldung.com/java-concurrentmodificationexception
    //     MODIFIES: this
    //     EFFECTS: orders listofstarcons from north hemisphere
    private void sortNorth(){
        List<StellarObject> toRemove = new ArrayList<>();
        for (StarConstellation sc: listofstarcons){
            if (locationSouth(sc.getLocation())){
                toRemove.add(sc);
            }
        }
        listofstarcons.removeAll(toRemove);
    }

    // MODIFIES: this
    // EFFECTS: orders listofstarcons from the south hemisphere
    private void sortSouth() {
        List<StellarObject> toRemove = new ArrayList<>();
        for (StarConstellation sc : listofstarcons) {
            if (locationNorth(sc.getLocation())) {
                toRemove.add(sc);
            }
        }
        listofstarcons.removeAll(toRemove);
    }

    // EFFECTS: prints out every star con in the list
    public void printList() {
        for (StarConstellation sc: listofstarcons) {
            System.out.println(sc.getName());
        }
    }

    // EFFECTS: prints out every starconstellation in the list
    public void printListSymbols() {
        for (StarConstellation sc : listofstarcons) {
            System.out.println(sc.getName() + ": " + sc.getSymbolism());
        }
    }

    // EFFECTS: prints out every starconstellation in the list
    public void printListStars() {
        for (StarConstellation sc : listofstarcons) {
            System.out.println(sc.getName() + ": " + sc.getStar().getStarName());
        }
    }

    // EFFECTS: prints out list of starcons that are visible from North
    public void getNorth(){
        sortNorth();
        printList();
    }

    // EFFECTS: prints out list of starcons that are visible from North
    public void getSouth (){
        sortSouth();
        printList();
    }

    // MODIFIES: this
    // EFFECTS: restores listofstarcons to the entire list of starcons
    public void restore(){
        Star alphaAndro = new Star("Alpha Andromedae", 2.06, 97);

        Star altair = new Star("Altair", .76, 16.73);

        Star phact = new Star("Phact", 2.645, 261);

        Star gammaDraconis = new Star("Gamme Draconis", 2.23, 154);

        Star regulus = new Star("Regulus", 8.13, 79.3);

        Star rigel = new Star("Regulus", .13, 860);

        Star persei = new Star("Persei", 1.81, 510);

        StarConstellation andromeda = new StarConstellation("Andromeda", StellarObject.Location.NORTH,
                "The Chained Lady", alphaAndro);

        StarConstellation aquila = new StarConstellation("Aquila", StellarObject.Location.BOTH,
                "The Eagle", altair);

        StarConstellation columba = new StarConstellation("Columba", StellarObject.Location.SOUTH,
                "The Dove", phact);

        StarConstellation draco = new StarConstellation("Draco",  StellarObject.Location.NORTH,
                "The Dragon" ,gammaDraconis);

        StarConstellation leo = new StarConstellation("Leo", StellarObject.Location.BOTH,
                "The Lion", regulus);

        StarConstellation orion = new StarConstellation("Orion", StellarObject.Location.BOTH,
                "The Hunter", rigel);

        StarConstellation perseus = new StarConstellation("Perseus", StellarObject.Location.NORTH,
                "Perseus", persei);

        ArrayList<StarConstellation> restored = new ArrayList<>();
        restored.add(andromeda);
        restored.add(aquila);
        restored.add(columba);
        restored.add(draco);
        restored.add(leo);
        restored.add(orion);
        restored.add(perseus);
        listofstarcons = restored;
    }

    // MODIFIES: this
    // EFFECTS: removes sc to a ListofStellarObject
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


    // MODIFIES: this
    // EFFECTS: adds sc to a ListOfStarConstellations
    public void add(StarConstellation sc){
        listofstarcons.add(sc);
    }


//// ask for help with greatest distance one
//    public void greatestDis(ListOfStarConstellation starcons){
//
//    }

    public void sort(String ans) throws InvalidStringInput {
        if (ans.equals("northern")){
            System.out.println("Here you are");
            getNorth();
        }
        if (ans.equals("southern")){
            System.out.println("Here you are");
            getSouth();
        }
        else if (!(ans.equals("northern") || ans.equals("southern"))){
            throw new InvalidStringInput("Invalid input." +
                    " Please input either Northern or Southern");
        }
    }

}
