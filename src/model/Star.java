package model;

public class Star {
    private String name;
    private double brightness; //in apparent magnitude

    public Star (String name, double brightness) {
        this.name = name;
        this.brightness = brightness;
    }

    // getters
    public String getStarName() {return name; }
    public double getBrightness() {return brightness; }

}
