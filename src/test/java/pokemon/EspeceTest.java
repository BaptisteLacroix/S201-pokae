package pokemon;

import interfaces.IEspece;
import interfaces.IStat;
import interfaces.IType;
import org.junit.Assert;
import org.junit.Test;
import statsPokemon.Stat;
import statsPokemon.Type;

public class EspeceTest {

    @Test
    public void testGetBaseStat() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // BaseStat
        Assert.assertEquals(45, espece.getBaseStat().getPV());
        Assert.assertEquals(49, espece.getBaseStat().getForce());
        Assert.assertEquals(49, espece.getBaseStat().getDefense());
        Assert.assertEquals(65, espece.getBaseStat().getSpecial());
        Assert.assertEquals(45, espece.getBaseStat().getVitesse());
    }

    @Test
    public void testGetNom() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // Nom
        Assert.assertEquals("Bulbizarre", espece.getNom());
        Assert.assertNotNull("Bulbizarre", espece.getNom());
        Assert.assertNotEquals("Bulbizae", espece.getNom());
    }

    @Test
    public void testGetNiveauDepart() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // Niveau Départ
        Assert.assertEquals(1, espece.getNiveauDepart());
    }

    @Test
    public void testGetBaseExp() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // BaseExp
        Assert.assertEquals(64, espece.getBaseExp());
    }

    @Test
    public void testGetGainsStat() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // GainsStat
        Assert.assertEquals(0, espece.getGainsStat().getPV());
        Assert.assertEquals(0, espece.getGainsStat().getForce());
        Assert.assertEquals(0, espece.getGainsStat().getDefense());
        Assert.assertEquals(1, espece.getGainsStat().getSpecial());
        Assert.assertEquals(0, espece.getGainsStat().getVitesse());
    }

    @Test
    public void testGetTypes() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // Type
        Assert.assertEquals(Type.Plante, espece.getTypes()[0]);
        Assert.assertNotEquals(Type.Eau, espece.getTypes()[0]);
        Assert.assertEquals(Type.Poison, espece.getTypes()[1]);
        Assert.assertNotNull(espece.getTypes()[0]);
    }

    @Test
    public void testGetEvolution() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);

        // Evolution
        Assert.assertNotNull(espece.getEvolution(1));
    }
}