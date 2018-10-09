package model;

import java.util.ArrayList;

public abstract class ListofStellarObject {
    protected String name;
    protected ArrayList<StellarObject> listofso;

    public ListofStellarObject(String name){
        this.name = name;
        listofso = new ArrayList<>();
    }

    // EFFECTS: prints out every stellar object in the list
    public void printList() {
        for (StellarObject so: listofso) {
            System.out.println(so.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds sc to a ListOfStarConstellations
    public void add(StellarObject so){
        listofso.add(so);
    }

    // MODIFIES: this
    // EFFECTS: removes sc to a ListofStellarObject
    public void remove (StellarObject so){
        listofso.remove(so);
    }

    // EFFECTS: checks if listofstarcons contains sc
    public boolean contains(StellarObject so){
        return listofso.contains(so);
    }

    // EFFECTS: returns size of ListOfStarCONstellation
    public int size(){
        return listofso.size();
    }

    //EFFECTS: returns that StarConstellation at pos x
    public StellarObject position(int x){
        return listofso.get(x);
    }

    //getters
    //EFFECTS: retrieve name of ListOfStarConstellation
    public String getLOSOname() {
        return name;
    }
    public ArrayList<StellarObject> getListofso() {
        return listofso;
    }

    public boolean locationNorth(StellarObject.Location location){
        return (location == StellarObject.Location.NORTH);
    }

    public  boolean locationSouth(StellarObject.Location location){
        return (location == StellarObject.Location.SOUTH);
    }

//    // MODIFIES: this
//    // EFFECTS: orders listofstarcons from north hemisphere
//    public ListofStellarObject sortNorth(ListofStellarObject loso){
//        for (StellarObject so: loso.listofso){
//            if (locationSouth(so.getLocation())){
//                loso.remove(so);
//                return loso;
//            }
//
//        }
//        return loso;
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: orders listofstarcons from the south hemisphere
//    public ListofStellarObject sortSouth(ListofStellarObject loso){
//        for (StellarObject so: loso.listofso){
//            if (locationNorth(so.getLocation())){
//                loso.remove(so);
//                return loso;
//            }
//
//        }
//        return loso;
//    }


}