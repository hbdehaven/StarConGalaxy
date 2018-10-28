package model;

public class Star {
    private String name;
    private double brightness; //in apparent magnitude
    private double distance;

    public Star (String name, double brightness, double distance) {
        this.name = name;
        this.brightness = brightness;
        this.distance = distance;
    }

    // getters
    // EFFECTS: get name of Star
    public String getStarName() {return name; }
    // EFFECTS: get brightness of Star
    public double getBrightness() {return brightness; }
    public double getDistance(){return distance;}

}
