package ui;

import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserUI {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner userInput;

    public static void callingSave(User user) {
        try {
            user.save("users.txt");
        } catch (IOException e) {
            System.out.println("Caught IOException");
        }

    }

    public static void load(Scanner scanner) throws IOException {
        while (scanner.hasNextLine()) {
            User user = new User("USER");
            user.load(scanner);
            users.add(user);
        }
    }
}
