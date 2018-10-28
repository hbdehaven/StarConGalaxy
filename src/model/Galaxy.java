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

    //EFFECTS: returns Galaxy type in String form
    public String typeGalaxy(Galaxy g){
        if (g.type == Type.ELLIPTICAL){
            return "Elliptical";
        }
        else if (g.type == Type.SPIRAL){
            return "Spiral";
        }
        else return "Irregular";
    }

    //getters
    //EFFECTS: get type of Galaxy
    public Type getType(){return type;}

    @Override
    public void allInformation(){
        System.out.println("Name: " + getName());
        System.out.println("Galaxy Type: " + getType());
        System.out.println("Seen from " + readableLocation());
    }

    public void typeStatement(Galaxy g){
        System.out.println("The type of this galaxy is " + typeGalaxy(g));
    }


}
