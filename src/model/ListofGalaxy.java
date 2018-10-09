package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListofGalaxy extends ListofStellarObject{
    private ArrayList<Galaxy> listofgalaxies;

    public ListofGalaxy(String name){
        super(name);

        Galaxy milkyWay = new Galaxy("Milky Way", 1, StellarObject.Location.BOTH);
        Galaxy largeMC = new Galaxy("Large Magellanic Cloud", 1, StellarObject.Location.SOUTH);
        Galaxy smallMC = new Galaxy("Small Magellanic Cloud", 2, StellarObject.Location.SOUTH);

        listofgalaxies = new ArrayList<>();
        listofgalaxies.add(milkyWay);
        listofgalaxies.add(largeMC);
        listofgalaxies.add(smallMC);
    }

}
