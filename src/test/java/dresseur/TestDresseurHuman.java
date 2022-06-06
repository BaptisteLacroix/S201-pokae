package dresseur;

import attaque.Capacite;
import attaque.Echange;
import org.junit.Assert;
import org.junit.Test;

/**
 * Une classe qui est utilisée pour tester la classe DresseurHuman.
 */
public class TestDresseurHuman {
    /**
     * Créer un nouvel objet DresseurHuman et l'affecter à la variable human.
     */
    private final DresseurHuman human = new DresseurHuman("IA1");

    /**
     * Testez que la fonction getRanch() renvoie un tableau non nul de longueur 6.
     */
    @Test
    public void testGetRanch() {
        Assert.assertNotNull(human.getRanch());
        Assert.assertEquals(6, human.getRanch().length);
    }

    /**
     * Il teste que le niveau de l'humain est de 6
     */
    @Test
    public void testNiveau() {
        Assert.assertEquals(6, human.getNiveau());
    }

    /**
     * Il teste que la méthode choisitAttaque() renvoie un objet de type Capacité ou Echange
     */
    @Test
    public void testChoisitAttaque() {
        Assert.assertSame(human.choisitAttaque(human.getRanch()[0], human.getRanch()[1]).getClass(), Capacite.class);
        Assert.assertSame(human.choisitAttaque(human.getRanch()[0], human.getRanch()[1]).getClass(), Echange.class);
    }
}
