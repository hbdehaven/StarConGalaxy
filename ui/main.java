package ui;

import model.*;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        new main().runApp();
    }

    private void runApp() throws IOException {
//        User heather = new User("Heather");
//        heather.addStellarObjectWantToSee(andromeda);
//        heather.addStellarObjectWantToSee(aquila);
//        heather.addStellarObjectWantToSee(columba);
//
//        heather.addStellarObjectHaveSeen(andromeda);
//
//        heather.printHaveSeenList();
//
//        heather.printWantToSeeList();
//
//        andromeda.printHaveSeenList();
//        andromeda.printWantToSeeList();
//
//        aquila.printWantToSeeList();

        StellarObjectUI.userLogIn();
    }

}



