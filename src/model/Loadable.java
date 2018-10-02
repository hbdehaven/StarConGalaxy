package model;

import java.io.IOException;
import java.util.Scanner;

public interface Loadable {

    void load(Scanner scanner) throws IOException;
}
