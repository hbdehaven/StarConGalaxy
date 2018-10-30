package ui;

import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws IOException {
        new main().runApp();
    }

    private void runApp() throws IOException {
         UserUI.userLogIn();
    }
}



