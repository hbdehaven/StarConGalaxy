package model;

public class Star {
    private String name;
    private double brightness; //in apparent magnitude

    public Star (String name, double brightness) {
        this.name = name;
        this.brightness = brightness;
    }

    // getters
    // EFFECTS: get name of Star
    public String getStarName() {return name; }
    // EFFECTS: get brightness of Star
    public double getBrightness() {return brightness; }

}
