package walkere.carleton.edu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameModelTest {

    @Test
    public void testStep() throws Exception {
        GameModel gm = new GameModel();
        gm.step();
        assertEquals(10, gm.getScore());
        assertEquals(3, gm.getAvatar().getPosition().getX(), 0);
        gm.addSpaceJunk(gm.getAvatar().getPosition().getX(), gm.getAvatar().getPosition().getY());
        gm.step();
        assertEquals(true, gm.isOver());
    }
}