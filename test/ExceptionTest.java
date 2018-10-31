package test;

import model.ListOfStarConstellation;
import model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionTest  {
    private ListOfStarConstellation listOfStarConstellation;
    private Rating testValidRating;
    private Rating testInValidRating;

    @BeforeEach
    public void setup(){
        listOfStarConstellation = new ListOfStarConstellation("Test");
        testValidRating = new Rating("Valid", 3, 0);
        testInValidRating  = new Rating("Valid", 7, 0);
    }

    @Test
    public void testValidInputNorthern(){
        try{
            listOfStarConstellation.sort("northern");
        }
        catch (Exception invalidStringInput) {
            fail("Unexpected exception caught");
        }
    }

    @Test
    public void testValidInputSouthern(){
        try{
            listOfStarConstellation.sort("southern");
        }
        catch (Exception invalidStringInput) {
            fail("Unexpected exception caught");
        }
    }

    @Test
    public void testInvalidInputNorth(){
        try{
            listOfStarConstellation.sort("north");
            fail("Did not throw exception");
        }
        catch (Exception invalidStringInput){
            //nothing
        }
    }

    @Test
    public void testInvalidInputSouth(){
        try{
            listOfStarConstellation.sort("south");
            fail("Did not throw exception");
        }
        catch (Exception invalidStringInput){
            //nothing
        }
    }
    @Test
    public void testValidRating(){
        try{
            testValidRating.isValid();
        }
        catch (Exception invalidRatingValue) {
            fail("Unexpected exception caught");
        }
    }

    @Test
    public void testInValidRating(){
        try{
            testInValidRating.isValid();
            fail("Did not throw exception");
        }
        catch (Exception invalidRatingValue) {
            // nothing
        }
    }
}
