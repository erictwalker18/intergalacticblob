package walkere.carleton.edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlobTest {

    @Test
    public void testIsHit() throws Exception {
        Blob blob = new Blob();
        SpaceJunk sj = new SpaceJunk();
        assertEquals(true, blob.isHit(sj));

        blob.setPosition(600, 600);
        assertEquals(false, blob.isHit(sj));
    }
}