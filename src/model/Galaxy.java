package model;

public class Galaxy extends StellarObject {
    public enum Type {
        ELLIPTICAL, SPIRAL, IRREGULAR
    }
    private Type type;

    public Galaxy (String name, Type type, Location location){
        super(name, location);
        this.type = type;
    }

    //getters
    //EFFECTS: get type of Galaxy
    public Type getType(){return type;}

    @Override
    public StringBuilder allInformation(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName() + "! ");
        sb.append("Galaxy Type: " + getType() + "! ");
        sb.append("Seen from " + readableLocation() + "! ");
        return sb;
    }


}
