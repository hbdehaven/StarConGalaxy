package model;

public class StarConstellation extends StellarObject{
    private double distance;  //average distance in lys from earth
    private String symbolism; //small fact or what it represents
    private Star star; // brightest star; Star class with fields name and brightness

    public StarConstellation(String name, Location location, String symbolism, Star star) {
        super(name, location);
        this.symbolism = symbolism;
        this.star = star;
    }

    //getters
    // EFFECTS: get location of StarConstellation
    public String getSymbolism() {return symbolism;}
    // EFFECTS: get the Star of the StarConstellation
    public Star getStar() {return star;}

    @Override
    public void allInformation(){
        System.out.println("Name: " + getName());
        System.out.println("Seen from " + readableLocation());
        System.out.println("Known symbolism: " + getSymbolism());
        System.out.println("Brightest Star: " + getStar().getStarName());
    }
}
