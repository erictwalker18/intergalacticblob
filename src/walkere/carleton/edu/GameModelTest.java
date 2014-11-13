package walkere.carleton.edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameModelTest {

    @Test
    public void testStep() throws Exception {
        GameModel gm = new GameModel();
        gm.step();
        assertEquals(10,gm.getScore());
    }

    @Test
    public void testAddSpaceJunk() throws Exception {
        GameModel gm = new GameModel();
    }

    @Test
    public void testGetScore() throws Exception {
        GameModel gm = new GameModel();
    }

    @Test
    public void testGetSpaceObjects() {
        GameModel gm = new GameModel();
    }
}