package model;

import model.exceptions.InvalidStringInput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static model.StellarObject.locationNorth;
import static model.StellarObject.locationSouth;

public class ListOfGalaxy implements Iterable<Galaxy>{
    private String name;
    private ArrayList<Galaxy> listofgalaxies;

    public ListOfGalaxy(String name){
        this.name = name;

        this.restoreLOG();
    }

    //EFFECTS: prints out every galaxy in the list
    public void printList(){
        for (Galaxy g: listofgalaxies){
            System.out.println(g.getName());
        }
    }

    //EFFECTS: prints out every galaxy in the list
    public void printListType(){
        for (Galaxy g: listofgalaxies){
            System.out.println(g.getName() + ": " + g.getType());
        }
    }

    // inspired by https://www.baeldung.com/java-concurrentmodificationexception
    //     MODIFIES: this
    //     EFFECTS: orders listofstarcons from north hemisphere
    public void sortNorth(){
        List<StellarObject> toRemove = new ArrayList<>();
        for (Galaxy g: listofgalaxies){
            if (locationSouth(g.getLocation())){
                toRemove.add(g);
            }
        }
        listofgalaxies.removeAll(toRemove);
    }

    // MODIFIES: this
    // EFFECTS: orders listofstarcons from the south hemisphere
    public void sortSouth(){
        List<StellarObject> toRemove = new ArrayList<>();
        for (Galaxy g: listofgalaxies){
            if (locationNorth(g.getLocation())){
                toRemove.add(g);
            }
        }
        listofgalaxies.removeAll(toRemove);
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

    public String nameOfGalaxy(String i) {
        int position = Integer.parseInt(i);
        return position(position - 1).getName();
    }

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

    public void restoreLOG(){
        Galaxy milkyWay = new Galaxy("Milky Way", Galaxy.Type.SPIRAL, StellarObject.Location.BOTH);
        Galaxy largeMC = new Galaxy("Large Magellanic Cloud", Galaxy.Type.SPIRAL, StellarObject.Location.SOUTH);
        Galaxy smallMC = new Galaxy("Small Magellanic Cloud", Galaxy.Type.IRREGULAR, StellarObject.Location.SOUTH);
        Galaxy andromeda = new Galaxy("Andromeda Galaxy", Galaxy.Type.SPIRAL, StellarObject.Location.NORTH);
        Galaxy ngc4696 = new Galaxy("NGC 4696", Galaxy.Type.ELLIPTICAL, StellarObject.Location.SOUTH);
        Galaxy triangulum = new Galaxy("Triangulum Galaxy", Galaxy.Type.SPIRAL, StellarObject.Location.NORTH);

        ArrayList<Galaxy> restored = new ArrayList<>();
        restored.add(milkyWay);
        restored.add(largeMC);
        restored.add(smallMC);
        restored.add(andromeda);
        restored.add(ngc4696);
        restored.add(triangulum);

        listofgalaxies = restored;
    }

    //EFFECTS: returns that StarConstellation at pos x
    public Galaxy position(int x){
        return listofgalaxies.get(x);
    }

    // EFFECTS: returns size of ListOfStarCONstellation
    public int size(){
        return listofgalaxies.size();
    }

    @Override
    public Iterator<Galaxy> iterator() {
        return listofgalaxies.iterator();
    }
}
