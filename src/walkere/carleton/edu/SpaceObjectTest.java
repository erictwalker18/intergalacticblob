package walkere.carleton.edu;

import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class SpaceObjectTest {
    private SpaceObject nonMovingObject; //Mr. Blobwall, position (0,0), velocity (0,0), size (5,100)
    private SpaceObject strangeObjecct; //Mr. Wallblob, position (300,1000), velocity (-3, 15), size (0,0)

    @Before
    public void setUp() {
        nonMovingObject = new Blob();
        nonMovingObject.setName("Mr. Blobwall");
        nonMovingObject.setPosition(0,0);
        nonMovingObject.setVelocity(0,0);
        nonMovingObject.setSize(5,15);

        strangeObjecct = new SpaceJunk();
        strangeObjecct.setName("Mr. Wallblob");
        strangeObjecct.setPosition(300, 100);
        strangeObjecct.setVelocity(0,0);
        strangeObjecct.setSize(5,15);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Mr. Blobwall", nonMovingObject.getName());
        assertEquals("Mr. Wallblob", strangeObjecct.getName());
    }

    @Test
    public void testSetName() throws Exception {
        nonMovingObject.setName("Mr. Blobwall the second");
        assertEquals("Mr. Blobwall the second", nonMovingObject.getName());
        strangeObjecct.setName("Mr. Wallblob the second");
        assertEquals("Mr. Wallblob the second", strangeObjecct.getName());

        //reset the objects back to where they were
        nonMovingObject.setName("Mr. Blobwall");
        strangeObjecct.setName("Mr. Wallblob");
    }

    @Test
    public void testGetPosition() throws Exception {
        assertEquals(new javafx.geometry.Point2D(0,0) , nonMovingObject.getPosition() );
        assertEquals(new javafx.geometry.Point2D(300,1000) , strangeObjecct.getPosition() );
    }

    @Test
    public void testSetPosition() throws Exception {
        nonMovingObject.setPosition(40,40);
        assertEquals(new javafx.geometry.Point2D(40,40) , nonMovingObject.getPosition() );
        strangeObjecct.setPosition(30,0);
        assertEquals(new javafx.geometry.Point2D(30,0) , strangeObjecct.getPosition() );

        //reset the objects back to where they were
        nonMovingObject.setPosition(0,0);
        strangeObjecct.setPosition(300,1000);
    }

    @Test
    public void testGetVelocity() throws Exception {
        assertEquals(new javafx.geometry.Point2D(0,0) , nonMovingObject.getVelocity() );
        assertEquals(new javafx.geometry.Point2D(-3,15) , strangeObjecct.getVelocity() );
    }

    @Test
    public void testSetVelocity() throws Exception {
        nonMovingObject.setVelocity(40, 40);
        assertEquals(new javafx.geometry.Point2D(40,40) , nonMovingObject.getVelocity() );
        strangeObjecct.setVelocity(0, 0);
        assertEquals(new javafx.geometry.Point2D(0,0) , strangeObjecct.getVelocity() );

        //reset the objects back to their initial speeds
        nonMovingObject.setVelocity(0, 0);
        strangeObjecct.setVelocity(-3, 15);
    }

    @Test
    public void testGetSize() throws Exception {
        assertEquals(new javafx.geometry.Point2D(5,100) , nonMovingObject.getSize() );
        assertEquals(new javafx.geometry.Point2D(0,0) , strangeObjecct.getSize() );
    }

    @Test
    public void testSetSize() throws Exception {
        nonMovingObject.setSize(40, 40);
        assertEquals(new javafx.geometry.Point2D(40,40) , nonMovingObject.getSize() );
        strangeObjecct.setSize(100, 500);
        assertEquals(new javafx.geometry.Point2D(0,0) , strangeObjecct.getSize() );

        //reset the objects back to their initial speeds
        nonMovingObject.setSize(0, 0);
        strangeObjecct.setSize(-3, 15);
    }

    @Test
    public void testStep() throws Exception {
        nonMovingObject.step();
        strangeObjecct.step();
        assertEquals(new javafx.geometry.Point2D(0,0), nonMovingObject.getPosition());
        assertEquals(new javafx.geometry.Point2D(297,985), strangeObjecct.getPosition());

    }
}