package model;

import java.io.IOException;
import java.util.ArrayList;

public interface Saveable {

    void save(String fileName) throws IOException;
}

