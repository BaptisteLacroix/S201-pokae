package pokemon;

import interfaces.*;
import junit.framework.TestCase;
import org.junit.Assert;
import combat.Capacite;
import org.junit.Test;
import statsPokemon.Categorie;
import statsPokemon.Stat;
import statsPokemon.Type;

import java.util.Arrays;

public class PokemonTest extends TestCase {

    @Test
    public void testGetStat() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // Stat
        Assert.assertEquals(15, pokemon.getStat().getPV());
        Assert.assertEquals(5, pokemon.getStat().getForce());
        Assert.assertEquals(5, pokemon.getStat().getDefense());
        Assert.assertEquals(6, pokemon.getStat().getSpecial());
        Assert.assertEquals(5, pokemon.getStat().getVitesse());
    }

    @Test
    public void testGetExperience() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // Experience
        Assert.assertTrue(Math.abs(64 - pokemon.getExperience()) <= 0.00000001);
    }

    @Test
    public void testGetNiveau() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // Niveau
        Assert.assertEquals(4, pokemon.getNiveau());
    }

    @Test
    public void testGetId() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // id
        Assert.assertEquals(1, pokemon.getId());
    }

    @Test
    public void testGetNom() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // Nom
        Assert.assertEquals("Bulbizarre", pokemon.getNom());
        Assert.assertNotNull("Bulbizarre", pokemon.getNom());
        Assert.assertNotEquals("Bulbizae", pokemon.getNom());
    }

    @Test
    public void testGetPourcentagePV() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // Pourcentage
        Assert.assertTrue(Math.abs(100.0 - pokemon.getPourcentagePV()) <= 0.00000001);
    }

    @Test
    public void testGetEspece() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        // Espece
        Assert.assertEquals(espece, pokemon.getEspece());
    }

    @Test
    public void testApprendCapacites() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        ICapacite[] capacite = new Capacite[4];
        capacite[0] = new Capacite("Écras'Face", 1.00, 40,
                35, Categorie.Physique, Type.Normal);

        capacite[1] = new Capacite("Poing Karaté", 1.00, 50,
                25, Categorie.Physique, Type.Normal);

        capacite[2] = new Capacite("Poing Feu", 1.00, 75,
                15, Categorie.Physique, Type.Feu);

        capacite[3] = new Capacite("Guillotine", 0.30, -1,
                5, Categorie.Physique, Type.Normal);

        pokemon.apprendCapacites(capacite);

        for (ICapacite cap : pokemon.getCapacitesApprises()) {
            Assert.assertNotNull(cap);
        }
        Assert.assertEquals(capacite[0], pokemon.getCapacitesApprises()[0]);
        Assert.assertEquals(capacite[1], pokemon.getCapacitesApprises()[1]);
        Assert.assertEquals(capacite[2], pokemon.getCapacitesApprises()[2]);
        Assert.assertEquals(capacite[3], pokemon.getCapacitesApprises()[3]);
        Assert.assertEquals(4, pokemon.getCapacitesApprises().length);
    }

    @Test
    public void testRemplaceCapacite() throws Exception {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        ICapacite[] capacite = new Capacite[4];
        capacite[0] = new Capacite("Écras'Face", 1.00, 40,
                35, Categorie.Physique, Type.Normal);

        capacite[1] = new Capacite("Poing Karaté", 1.00, 50,
                25, Categorie.Physique, Type.Normal);

        capacite[2] = new Capacite("Poing Feu", 1.00, 75,
                15, Categorie.Physique, Type.Feu);

        capacite[3] = new Capacite("Guillotine", 0.30, -1,
                5, Categorie.Physique, Type.Normal);

        pokemon.apprendCapacites(capacite);
        pokemon.remplaceCapacite(1, capacite[0]);

        Assert.assertEquals(capacite[1], pokemon.getCapacitesApprises()[1]);
        Assert.assertEquals(capacite[0], pokemon.getCapacitesApprises()[1]);
    }

    @Test
    public void testEstEvanoui() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        Assert.assertFalse(pokemon.estEvanoui());
    }

    @Test
    public void testAChangeNiveau() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        Assert.assertTrue(pokemon.aChangeNiveau());
    }

    @Test
    public void testPeutMuter() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        Assert.assertTrue(pokemon.peutMuter());
    }

    @Test
    public void testSoigne() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(stats, "Bulbizarre", 1, 64, evstats, type);
        IPokemon pokemon = new Pokemon(1, "Bulbizarre", 1, 64, 100.0, espece);

        System.out.println("Stat Pokemon : " + pokemon.getStat());
        pokemon.getStat().setPV(10);
        System.out.println("[Le Pokémon se fait soigner]");
        pokemon.soigne();
        System.out.println("Affichage des PV suite à sa guérison: ");
        Assert.assertEquals(15, pokemon.getStat().getPV());
    }
}