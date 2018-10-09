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

    ListOfStarConstellation listofstarcons;

    @BeforeEach
    public void setUp(){
//        StarConstellation starcon1 = new StarConstellation("",0,true,"",null, null);
//        StarConstellation starcon2 = new StarConstellation("",0,false,"",null, null);
        listofstarcons = new ListOfStarConstellation("Star Cons");
//        listofstarcons.add(starcon1);
//        listofstarcons.add(starcon2);
    }

    @Test
    public void testsortNorthWithNorth(){
        StarConstellation starcon1 = new StarConstellation("",0, StarConstellation.Location.NORTH,"",null);
        listofstarcons.add(starcon1);
        assertTrue(listofstarcons.contains(starcon1));
    }

    @Test
    public void testsortNorthWithoutNorth(){
        StarConstellation starcon2 = new StarConstellation("",0, StarConstellation.Location.SOUTH,"",null);
        assertFalse(listofstarcons.contains(starcon2));
    }

    @Test
    public void testsortNorthWithNorthandWithout(){
        StarConstellation starcon1 = new StarConstellation("",0, StarConstellation.Location.NORTH,"",null);
        StarConstellation starcon2 = new StarConstellation("",0, StarConstellation.Location.SOUTH,"",null);
        listofstarcons.add(starcon1);
        assertTrue(listofstarcons.contains(starcon1));
        assertFalse(listofstarcons.contains(starcon2));
    }

    @Test
    public void testsortSouthWithOutSouth(){
        StarConstellation starcon1 = new StarConstellation("",0, StarConstellation.Location.NORTH,"",null);
        if (starcon1.getLocation() == StarConstellation.Location.SOUTH){
            listofstarcons.add(starcon1);
        }
        assertFalse(listofstarcons.contains(starcon1));
    }

    @Test
    public void testsortSouthWithSouth(){
        StarConstellation starcon2 = new StarConstellation("",0, StarConstellation.Location.SOUTH,"",null);
        if (starcon2.getLocation() == StarConstellation.Location.SOUTH){
            listofstarcons.add(starcon2);
        }
        assertTrue(listofstarcons.contains(starcon2));
    }

    @Test
    public void testsortSouthWithSouthandWithout(){
        StarConstellation starcon1 = new StarConstellation("",0, StarConstellation.Location.NORTH,"",null);
        StarConstellation starcon2 = new StarConstellation("",0, StarConstellation.Location.SOUTH,"",null);
        if (starcon1.getLocation() == StarConstellation.Location.NORTH) {}
        else if (starcon2.getLocation() == StarConstellation.Location.SOUTH) {listofstarcons.add(starcon2);}
        assertFalse(listofstarcons.contains(starcon1));
        assertTrue(listofstarcons.contains(starcon2));
    }

}
