package code;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SpaceObjectTest {
    private SpaceObject nonMovingObject; //Mr. Blobwall, position (0,0), velocity (0,0), size (5,100)
    private SpaceObject strangeObject; //Mr. Wallblob, position (300,1000), velocity (-3, 15), size (0,0)

    @Before
    public void setUp() {
        nonMovingObject = new Blob();
        nonMovingObject.setName("Mr. Blobwall");
        nonMovingObject.setPosition(0,0);
        nonMovingObject.setVelocity(0,0);
        nonMovingObject.setSize(5,15);

        strangeObject = new SpaceJunk();
        strangeObject.setName("Mr. Wallblob");
        strangeObject.setPosition(300, 100);
        strangeObject.setVelocity(0,0);
        strangeObject.setSize(5,15);
    }

    @Test
    public void testGetSize() throws Exception {
        //Not trivial because we're using imageView, which can cause unexpected issues
        assertEquals(new javafx.geometry.Point2D(5,100) , nonMovingObject.getSize() );
        assertEquals(new javafx.geometry.Point2D(0,0) , strangeObject.getSize() );
    }

    @Test
    public void testSetSize() throws Exception {
        //Not trivial because we're using imageView, which can cause unexpected issues
        nonMovingObject.setSize(40, 40);
        assertEquals(new javafx.geometry.Point2D(40,40) , nonMovingObject.getSize() );
        strangeObject.setSize(100, 500);
        assertEquals(new javafx.geometry.Point2D(0,0) , strangeObject.getSize() );

        //reset the objects back to their initial speeds
        nonMovingObject.setSize(0, 0);
        strangeObject.setSize(-3, 15);
    }

    @Test
    public void testStep() throws Exception {
        nonMovingObject.step();
        strangeObject.step();
        assertEquals(new javafx.geometry.Point2D(0,0), nonMovingObject.getPosition());
        assertEquals(new javafx.geometry.Point2D(297,985), strangeObject.getPosition());

    }
}