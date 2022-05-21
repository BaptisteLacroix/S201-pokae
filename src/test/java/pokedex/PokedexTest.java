package pokedex;

import interfaces.IPokemon;
import interfaces.IType;
import org.junit.Assert;
import org.junit.Test;
import statsPokemon.Type;

public class PokedexTest {

    private Pokedex pokedex = new Pokedex();
    @Test
    public void engendreRanch() {
        IPokemon[] p = pokedex.getRanch();
        for (IPokemon pok : p) {
            Assert.assertNotNull(pok);
        }
        Assert.assertEquals(6, p.length);
    }

    @Test
    public void getInfo() {
        Assert.assertNotNull(pokedex.getInfo("Bulbizarre"));
        Assert.assertNull(pokedex.getInfo("Bulbizare"));
    }

    @Test
    public void getEfficacite() {
        IType typeA = Type.Insecte;
        IType typeD = Type.Sol;
        Assert.assertNotNull(pokedex.getEfficacite(typeA, typeD));
        Assert.assertTrue(Math.abs(0.5 - pokedex.getEfficacite(typeA, typeD)) <= 0.00000001);
    }

    @Test
    public void getCapacite() {
        Assert.assertEquals(pokedex.getCapacite("Écras'Face").getNom(), "Écras'Face");
        Assert.assertEquals(pokedex.getCapacite(1).getNom(), "Écras'Face");
    }
}