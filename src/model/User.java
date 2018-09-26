package model;

import java.util.Scanner;

public class User {

    // EFFECTS: asks certain questions based on user input
    public static void userInterest() {
        Scanner scanner = new Scanner(System.in);
        String answer = "";
        while (true) {
            System.out.println("Do you like star constellations? yes or no?");
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                System.out.println("Awesome!");
                System.out.println("Would you like to sign up for daily emails?");
                answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    System.out.println("Great! More to come!");
                }
                scanner.nextLine();
            }

            if (answer.equals("no")) {
                System.out.println("Then get out.");
                scanner.nextLine();
            } else if (answer.equals("quit")) {
                break;
            }

        }

    }

}

