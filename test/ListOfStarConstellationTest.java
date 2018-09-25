package test;

import model.ListOfStarConstellation;
import model.StarConstellation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class ListOfStarConstellationTest {
    // inspired by TPD-lecture-starters

    ListOfStarConstellation listofstarcons;

    @BeforeEach
    public void setUp(){
//        StarConstellation starcon1 = new StarConstellation("",0,true,"",null, null);
//        StarConstellation starcon2 = new StarConstellation("",0,false,"",null, null);
        listofstarcons = new ListOfStarConstellation("Star Cons");
//        listofstarcons.placeIn(starcon1);
//        listofstarcons.placeIn(starcon2);
    }

    @Test
    public void testsortNorthWithNorth(){
        StarConstellation starcon1 = new StarConstellation("",0,true,"",null, null);
        listofstarcons.placeIn(starcon1);
        assertTrue(listofstarcons.contains(starcon1));
    }

    @Test
    public void testsortNorthWithoutNorth(){
        StarConstellation starcon2 = new StarConstellation("",0,false,"",null, null);
        assertFalse(listofstarcons.contains(starcon2));
    }

    @Test
    public void testsortNorthWithNorthandWithout(){
        StarConstellation starcon1 = new StarConstellation("",0,true,"",null, null);
        StarConstellation starcon2 = new StarConstellation("",0,false,"",null, null);
        listofstarcons.placeIn(starcon1);
        assertTrue(listofstarcons.contains(starcon1));
        assertFalse(listofstarcons.contains(starcon2));
    }

}
