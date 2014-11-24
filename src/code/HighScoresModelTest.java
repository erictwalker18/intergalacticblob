package code;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HighScoresModelTest {
    private HighScoresModel hsm;

    @Before
    public void setUp() {
        hsm = new HighScoresModel();
    }

    @Test
    public void testGetHighScores() throws Exception {
        hsm.addHighScore(50, "Test name", "random Date");
        hsm.addHighScore(-500, "Test name 2", "5-5-10000");
    }
}