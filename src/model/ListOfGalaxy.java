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

    public void sortType(Galaxy.Type type){
        List<Galaxy> toRemove = new ArrayList<>();
        for (Galaxy g: listofgalaxies){
            if (!(g.getType().equals(type)))
                toRemove.add(g);
        }
        listofgalaxies.removeAll(toRemove);
    }

    public String nameOfGalaxy(String i) {
        int position = Integer.parseInt(i);
        return position(position - 1).getName();
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

    //EFFECTS: returns that Galaxy at pos x
    public Galaxy position(int x){
        return listofgalaxies.get(x);
    }

    // EFFECTS: returns size of ListOfStarCONstellation
    public int size(){
        return listofgalaxies.size();
    }

    // EFFECTS: adds galaxy to list; for testing purposes
    public void add(Galaxy g){
        listofgalaxies.add(g);
    }

    // EFFECTS: returns true if contains g, for testing purposes
    public boolean contains(Galaxy g){
        return listofgalaxies.contains(g);
    }

    @Override
    public Iterator<Galaxy> iterator() {
        return listofgalaxies.iterator();
    }
}
