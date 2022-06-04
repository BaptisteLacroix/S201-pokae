package dresseur;

import attaque.Capacite;
import attaque.Echange;
import org.junit.Assert;
import org.junit.Test;

public class TestDresseurHuman {
    private final DresseurHuman human = new DresseurHuman("IA1");

    @Test
    public void testGetRanch() {
        Assert.assertNotNull(human.getRanch());
        Assert.assertEquals(6, human.getRanch().length);
    }

    @Test
    public void testNiveau() {
        Assert.assertEquals(6, human.getNiveau());
    }

    @Test
    public void testChoisitAttaque() {
        Assert.assertSame(human.choisitAttaque(human.getRanch()[0], human.getRanch()[1]).getClass(), Capacite.class);
        Assert.assertSame(human.choisitAttaque(human.getRanch()[0], human.getRanch()[1]).getClass(), Echange.class);
    }
}
