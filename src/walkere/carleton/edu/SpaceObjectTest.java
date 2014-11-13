package walkere.carleton.edu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceObjectTest {
    private SpaceObject nonMovingObject; //Mr. Blobwall, position (0,0), velocity (0,0), size (5,100)
    private SpaceObject strangeObjecct; //Mr. Wallblob, position (300,1000), velocity (-3, 15), size (0,0)

    @Test
    public void testGetName() throws Exception {
        assertEquals("Mr. Blobwall", nonMovingObject.getName());
        assertEquals("Mr. Wallblob", strangeObjecct.getName());
    }

    @Test
    public void testSetName() throws Exception {
    }

    @Test
    public void testGetPosition() throws Exception {

    }

    @Test
    public void testSetPosition() throws Exception {

    }

    @Test
    public void testGetVelocity() throws Exception {

    }

    @Test
    public void testSetVelocity() throws Exception {

    }

    @Test
    public void testGetSize() throws Exception {

    }

    @Test
    public void testSetSize() throws Exception {

    }

    @Test
    public void testStep() throws Exception {

    }
}