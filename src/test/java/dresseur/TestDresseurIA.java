package dresseur;

import org.junit.Assert;
import org.junit.Test;

/**
 * Une classe qui est utilisée pour tester la classe DresseurIA.
 */
public class TestDresseurIA {
    /**
     * Créer un nouvel objet DresseurIA et l'affecter à la variable IA1.
     */
    private final DresseurIA IA1 = new DresseurIA("IA1");

    /**
     * Cette fonction teste la fonction getRanch() de la classe DresseurIA
     */
    @Test
    public void testGetRanch() {
        DresseurIA IA1 = new DresseurIA("IA1");
        Assert.assertNotNull(IA1.getRanch());
        Assert.assertEquals(6, IA1.getRanch().length);
    }

    /**
     * Il teste que la méthode getNiveau() de l'objet IA1 renvoie 6
     */
    @Test
    public void testNiveau() {
        Assert.assertEquals(6, IA1.getNiveau());
    }
}