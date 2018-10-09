package model;

public class Galaxy extends StellarObject {
    private int type; // 0 for elliptical, 1 for spiral, 2 for irregular

    public Galaxy (String name, int type, Location location){
        super(name, location);
        this.type = type;
    }

    //EFFECTS: returns Galaxy type in String form
    public String typeGalaxy(Galaxy g){
        if (g.type == 0){
            return "Elliptical";
        }
        else if (g.type == 1){
            return "Spiral";
        }
        else return "Irregular";
    }

    //getters
    //EFFECTS: get type of Galaxy
    public int getType(){return type;}

    @Override
    public void locationStatement(StellarObject so) {
        System.out.println("The location of this galaxy is "+ getLocation());
    }
}
