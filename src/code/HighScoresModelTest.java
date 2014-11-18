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

        assertEquals("50\tTest name\trandom Date", hsm.getHighScores(1));
        assertEquals("50\tTest name\trandom Date\n-500\tTest name 2\t5-5-10000", hsm.getHighScores(2));
    }
}