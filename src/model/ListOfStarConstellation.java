package model;

import model.exceptions.InvalidStringInput;

import java.util.ArrayList;
import java.util.List;

import static model.StellarObject.locationNorth;
import static model.StellarObject.locationSouth;

// inspired by IntegerSetIntersect
public class ListOfStarConstellation{
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
    public void restore (){
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
