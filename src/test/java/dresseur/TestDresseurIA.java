package dresseur;

import org.junit.Assert;
import org.junit.Test;

public class TestDresseurIA {
    private DresseurIA IA1 = new DresseurIA("IA1");

    @Test
    public void testGetRanch() {
        DresseurIA IA1 = new DresseurIA("IA1");
        Assert.assertNotNull(IA1.getRanch());
        Assert.assertEquals(6, IA1.getRanch().length);
    }

    @Test
    public void testNiveau() {
        Assert.assertEquals(6, IA1.getNiveau());
    }
}
