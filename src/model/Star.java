package model;

public class Star {
    private String name;
    private double brightness; //in apparent magnitude

    public Star (String name) {
        this.name = name;
        this.brightness = 0;
        Star alphaAndro = new Star ("Alpha Andromedae");
        alphaAndro.brightness = 2.06;
        Star altair = new Star ("Altair");
        altair.brightness = .76;
        Star phact = new Star ("Phact");
        phact.brightness = 2.645;
    }

    // source: control-and-data-flow-lecture-starters Customer class
    public String returnName() {return name; }
    public double returnBrightness() {return brightness; }

}
