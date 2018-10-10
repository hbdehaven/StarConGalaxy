package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListofGalaxy{
    private String name;
    private ArrayList<Galaxy> listofgalaxies;

    public ListofGalaxy(String name){
        this.name = name;

        Galaxy milkyWay = new Galaxy("Milky Way", 1, StellarObject.Location.BOTH);
        Galaxy largeMC = new Galaxy("Large Magellanic Cloud", 1, StellarObject.Location.SOUTH);
        Galaxy smallMC = new Galaxy("Small Magellanic Cloud", 2, StellarObject.Location.SOUTH);

        listofgalaxies = new ArrayList<>();
        listofgalaxies.add(milkyWay);
        listofgalaxies.add(largeMC);
        listofgalaxies.add(smallMC);
    }

    //EFFECTS: prints out every galaxy in the list
    public void printList(){
        for (Galaxy g: listofgalaxies){
            System.out.println(g.getName());
        }
    }

    //EFFECTS: returns that StarConstellation at pos x
    public Galaxy position(int x){
        return listofgalaxies.get(x);
    }

    // EFFECTS: returns size of ListOfStarCONstellation
    public int size(){
        return listofgalaxies.size();
    }
}
