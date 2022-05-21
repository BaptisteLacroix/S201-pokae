package statsPokemon;

import combat.Capacite;
import interfaces.*;
import org.junit.Assert;
import org.junit.Test;
import pokemon.Espece;
import pokemon.Pokemon;

public class CapaciteTest {

    @Test
    public void testCalculeDommage() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);
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

        // System.out.println(pokemon.getCapacitesApprises()[0].calculeDommage(pokemon, pokemon));
        Assert.assertNotNull(pokemon.getCapacitesApprises()[0].calculeDommage(pokemon, pokemon));
    }

    @Test
    public void testUtilise() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);
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

        pokemon.getCapacitesApprises()[0].utilise();
        pokemon.getCapacitesApprises()[0].utilise();
        pokemon.getCapacitesApprises()[0].utilise();
        Assert.assertEquals(32, pokemon.getCapacitesApprises()[0].getPP());
    }

    @Test
    public void testGetPP() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);
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
        Assert.assertEquals(35, pokemon.getCapacitesApprises()[0].getPP());
    }

    @Test
    public void testResetPP() {
        IType[] type = new IType[2];
        type[0] = Type.Plante;
        type[1] = Type.Poison;
        IStat stats = new Stat(45, 49, 49, 65, 45);
        IStat evstats = new Stat(0, 0, 0, 1, 0);
        IEspece espece = new Espece(1, stats, "Bulbizarre", 1, 64, evstats, type);
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

        pokemon.getCapacitesApprises()[0].utilise();
        pokemon.getCapacitesApprises()[0].utilise();
        pokemon.getCapacitesApprises()[0].utilise();
        Assert.assertEquals(32, pokemon.getCapacitesApprises()[0].getPP());
        pokemon.getCapacitesApprises()[0].resetPP();
        Assert.assertEquals(35, pokemon.getCapacitesApprises()[0].getPP());
    }
}