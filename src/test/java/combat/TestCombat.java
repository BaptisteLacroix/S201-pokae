package combat;

import attaque.Echange;
import dresseur.DresseurIA;
import org.junit.Assert;
import org.junit.Test;

public class TestCombat {
    private DresseurIA IA1 = new DresseurIA("IA1");
    private DresseurIA IA2 = new DresseurIA("IA2");
    private Echange echange1 = new Echange(IA1.getPokemon(1));
    private Echange echange2 = new Echange(IA2.getPokemon(1));
    private Combat combat = new Combat(IA1, IA2);



    @Test
    public void testNouveauTour() {
        Assert.assertEquals(combat.nouveauTour(IA1.getPokemon(0), echange1, IA2.getPokemon(1), echange2).getClass(), Tour.class);
    } //Crée un tour du combat

    @Test
    public void testGetDresseur1() {
        Assert.assertEquals(combat.getDresseur1(), IA1);
    }

    @Test
    public void testGetDresseur2() {
        Assert.assertEquals(combat.getDresseur2(), IA2);
    }
}
