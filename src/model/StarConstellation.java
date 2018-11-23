package model;

public class StarConstellation extends StellarObject{
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
    public StringBuilder allInformation(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName()+ "! ");
        sb.append("Seen from " + readableLocation() + "! ");
        sb.append("Known symbolism: " + getSymbolism() + "! ");
        sb.append("Brightest Star: " + getStar().getStarName() + "! ");
        return sb;
    }

    public String infoNameForSymbolismAndStarAction(){
        return "Name: " + getName()+ "! ";
    }

}
