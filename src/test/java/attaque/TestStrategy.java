package attaque;

import dresseur.DresseurIA;
import org.junit.Assert;
import org.junit.Test;
import pokemon.Pokemon;

public class TestStrategy {
    private DresseurIA IA = new DresseurIA("IA1");

    @Test
    public void testChoisitCombattant() {
        Assert.assertEquals(IA.choisitCombattant().getClass(), Pokemon.class);
    }

    @Test
    public void testChoisitCombattantContre() {
        Assert.assertEquals(IA.choisitCombattantContre(IA.getPokemon(1)).getClass(), Pokemon.class);
    }

    @Test
    public void testChoisitAttaque() {
        Assert.assertSame(IA.choisitAttaque(IA.getRanch()[0], IA.getRanch()[1]).getClass(), Capacite.class);
        Assert.assertSame(IA.choisitAttaque(IA.getRanch()[0], IA.getRanch()[1]).getClass(), Echange.class);
    }
}
