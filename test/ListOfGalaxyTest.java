package test;

import model.Galaxy;
import model.ListOfGalaxy;
import model.StellarObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListOfGalaxyTest {
    private ListOfGalaxy test;
    private Galaxy g1;
    private Galaxy g2;

    @BeforeEach
    public void setUp(){
        test = new ListOfGalaxy("test");
        g1 = new Galaxy("first", Galaxy.Type.IRREGULAR, StellarObject.Location.NORTH);
        g2 = new Galaxy("second", Galaxy.Type.SPIRAL, StellarObject.Location.SOUTH);
    }

    @Test
    public void testSortNorth(){
        test.add(g1);
        test.add(g2);
        test.sortNorth();
        assertTrue(test.contains(g1));
        assertFalse(test.contains(g2));
    }

    @Test
    public void testSortSouth(){
        test.add(g1);
        test.add(g2);
        test.sortSouth();
        assertFalse(test.contains(g1));
        assertTrue(test.contains(g2));
    }

    @Test
    public void testSortTypeIrregular(){
        test.add(g1);
        test.add(g2);
        test.sortType(Galaxy.Type.IRREGULAR);
        assertTrue(test.contains(g1));
        assertFalse(test.contains(g2));
    }

    @Test
    public void testSortTypeSpiral(){
        test.add(g1);
        test.add(g2);
        test.sortType(Galaxy.Type.SPIRAL);
        assertFalse(test.contains(g1));
        assertTrue(test.contains(g2));
    }
}
