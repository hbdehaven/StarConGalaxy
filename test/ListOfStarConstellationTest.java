package test;

import model.ListOfStarConstellation;
import model.StarConstellation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ListOfStarConstellationTest {
    // inspired by TPD-lecture-starters

    private ListOfStarConstellation test;
    private StarConstellation starcon1;
    private StarConstellation starcon2;

    @BeforeEach
    public void setUp(){
        test = new ListOfStarConstellation("test");
        starcon1 = new StarConstellation("Starcon1", StarConstellation.Location.NORTH,"",null);
        starcon2 = new StarConstellation("Starcon2",StarConstellation.Location.SOUTH,"",null);
    }

    @Test
    public void testsortNorthWithNorth(){
        test.add(starcon1);
        test.sortNorth();
        assertTrue(test.contains(starcon1));
    }

    @Test
    public void testsortNorthWithoutNorth(){
        test.add(starcon2);
        test.sortNorth();
        assertFalse(test.contains(starcon2));
    }

    @Test
    public void testsortNorthWithNorthandWithout(){
        test.add(starcon1);
        test.sortNorth();
        assertTrue(test.contains(starcon1));
        assertFalse(test.contains(starcon2));
    }

    @Test
    public void testsortSouthWithOutSouth(){
        test.add(starcon1);
        test.sortSouth();
        assertFalse(test.contains(starcon1));
    }

    @Test
    public void testsortSouthWithSouth(){
        test.add(starcon2);
        test.sortSouth();
        assertTrue(test.contains(starcon2));
    }

    @Test
    public void testsortSouthWithSouthandWithout(){
        test.add(starcon1);
        test.add(starcon2);
        test.sortSouth();
        assertFalse(test.contains(starcon1));
        assertTrue(test.contains(starcon2));
    }

}
