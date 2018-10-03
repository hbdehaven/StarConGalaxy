package test;

import model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    public Rating rate;

    @BeforeEach
    public void setup(){
        rate = new Rating("Rating", 0 , "", 0000);
    }

    @Test
    public void save() throws IOException {
        File testsave = new File("testsave1.txt");
        Rating r1 = new Rating("r1", 1, "H", 1002);
        r1.save("testsave1.txt");

        Scanner sc = new Scanner(testsave);
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            System.out.println(line);
            assertEquals(line, "r1,1,H,1002.0");
        }

    }
    @Test
    public void load() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("testload.txt")));
        Scanner scanner = new Scanner(br);
        rate.load(scanner);
        while (scanner.hasNextLine()) {
            String line1;
            while((line1=br.readLine())!=null) {
                assertEquals(line1, "Andromeda,5,Heather,1002");
            }
            String line2 = scanner.nextLine();
            assertEquals(line2, "Aquila,3,Jake,1202");
            String line3 = scanner.nextLine();
            assertEquals(line3, "Columba,2,Carole,0126");
        }
    }
}
