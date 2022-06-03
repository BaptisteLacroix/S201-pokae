package attaque;

import dresseur.DresseurIA;
import org.junit.Assert;
import org.junit.Test;

public class TestEchange {
    private final DresseurIA IA = new DresseurIA("IA1");
    private Echange echange = new Echange(IA.getPokemon(1));

    @Test
    public void testEchangePokemon() {
        echange.setPokemon(IA.getPokemon(0));
        Assert.assertNotEquals(IA.getPokemon(1), echange.echangeCombattant());
        Assert.assertEquals(IA.getPokemon(0), echange.echangeCombattant());
    }

    @Test
    public void testCalculeDommage() {
        Assert.assertEquals(0, echange.calculeDommage(IA.getPokemon(1), IA.getPokemon(0)));
    }
}
