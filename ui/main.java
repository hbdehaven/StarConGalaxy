package ui;

import model.Galaxy;
import model.StellarObject;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws IOException {
        new main().runApp();
    }

    private void runApp() throws IOException {
        UserUI.load("users.txt");
        Galaxy milkyWay = new Galaxy("Milky Way", Galaxy.Type.SPIRAL, StellarObject.Location.BOTH);
        UserUI.users.get(0).addStellarObjectWantToSee(milkyWay);
        UserUI.save("users.txt");
        UserUI.userLogIn();
    }
}



