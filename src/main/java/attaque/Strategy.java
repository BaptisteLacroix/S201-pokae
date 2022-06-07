/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date Strategy.java
 */
package attaque;

import dresseur.DresseurIA;
import interfaces.*;

import java.util.Random;

/**
 * Une stratégie est utilisée par les dresseurs non humains (IA) pour prendre les décisions
 * Un DresseurIA possède une référence sur une IStrategy à qui il délègue la prise de décision
 * Un dresseur humain n'utilise pas IStrategy
 * Chaque méthode de IStrategy correspond à la méthode homonyme de IDresseur
 *
 * @author Lacroix Baptiste and Vidal Théo
 */
public class Strategy implements IStrategy {
    /**
     * ranch contenant les pokémons du dresseur IA
     */
    private final IPokemon[] ranch;
    /**
     * Objet Random perméttant de générer un nombre aléatoire.
     */
    private final Random rand = new Random();

    /**
     * Constructeur de la Strategy
     *
     * @param ranch ranch contenant les Pokémons du dresseur IA.
     */
    public Strategy(IPokemon[] ranch) {
        this.ranch = ranch;
    }

    /**
     * La fonction `choisitCombattant()` renvoie un pokémon aléatoire du ranch qui ne s'est pas évanoui et qui combattra
     *
     * @return Un pokémon qui ne s'est pas évanoui qui combattra.
     */
    @Override
    public IPokemon choisitCombattant() {
        IPokemon pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        while (pokemon.estEvanoui()) {
            int random = this.rand.nextInt(this.ranch.length);
            pokemon = this.ranch[random];
        }
        return pokemon;
    }

    /**
     * La fonction choisit un pokémon aléatoire du ranch qui ne s'est pas évanoui et qui combattra
     *
     * @param pok le pokémon que l'adversaire utilise
     * @return Un pokémon qui ne s'est pas évanoui qui combattra.
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        IPokemon pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        while (pokemon.estEvanoui()) {
            pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        }
        return pokemon;
    }

    /**
     * La fonction choisit une attaque aléatoire dans la liste des attaques de l'attaquant ou choisit changer de
     * Pokémon parmis la liste des Pokémon de l'entraîneur
     *
     * @param attaquant Le Pokémon qui attaque
     * @param defenseur Le Pokémon que l'IA attaque
     * @return L'attaque que l'ordinateur va utiliser.
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        int choixAttaque = rand.nextInt(2 + 1) + 1;  // Read user input
        if (choixAttaque == 1) {
            ICapacite random = attaquant.getCapacitesApprises()[this.rand.nextInt(4)];
            while (random == null) {
                random = attaquant.getCapacitesApprises()[this.rand.nextInt(4)];
            }
            return random;
        } else {
            IEchange echange = new Echange(attaquant);
            echange.setPokemon(this.choisitCombattantContre(defenseur));
            return echange; // Change de Pokemon
        }
    }

    public static void main(String[] args) {
        DresseurIA dresseurIA1 = new DresseurIA("IA1");
        DresseurIA dresseurIA2 = new DresseurIA("IA2");
        choixIA(dresseurIA1);
        choixIA(dresseurIA2);
        for (int i = 0; i < 5; i ++)
            System.out.println(Strategy.F(i, dresseurIA1.getPokemon(0), dresseurIA2, dresseurIA2.getPokemon(0)));
    }

    private static int F(int X, IPokemon pokemonA, IDresseur dresseurD, IPokemon pokemonD) {
        if (X == 1) return 1;
        else if (X == 0) return 0;
        else {
            int max = 0;
            ICapacite[] listeCoupsPossiblesCapacitesAttaquant = pokemonA.getCapacitesApprises();
            ICapacite[] listeCoupsPossiblesCapacitesDefenseur = pokemonD.getCapacitesApprises();
            IPokemon[] ranchD = new IPokemon[6];
            for (int i = 0; i < 6; i ++)
                ranchD[i] = dresseurD.getPokemon(i);
            for (ICapacite c_1 : listeCoupsPossiblesCapacitesAttaquant) {
                int min = 1;
                for (ICapacite c_2 : listeCoupsPossiblesCapacitesDefenseur) {
                    int val = c_1.calculeDommage(pokemonA, pokemonD) + c_2.calculeDommage(pokemonA, pokemonD);
                    System.out.println("val : " + val);
                    System.out.println("Math.min(min, val) : " + Math.min(min, val));
                    min = Math.min(min, val);
                    System.out.println("min : " + min);
                    System.out.println("max = Math.max(min, max) : " + Math.max(min, max));
                    max = Math.max(min, max);
                    System.out.println("max : " + max + "\n");
                }
            }
            return max;
        }
    }

    private static void choixIA(DresseurIA dresseur) {
        Random rand = new Random();
        for (IPokemon pokemon : dresseur.getRanch()) {
            ICapacite[] capacites = pokemon.getEspece().getCapSet();
            ICapacite[] capacitesApp = new ICapacite[4];
            for (int i = 0; i < 4; i++) {
                Capacite random = (Capacite) capacites[rand.nextInt(capacites.length)];
                while (pokemon.getNiveau() < random.getNiveau())
                    random = (Capacite) capacites[rand.nextInt(capacites.length)];
                System.out.println(dresseur.getNom() + " choose a new capacity to learn for " + pokemon.getNom() + " : " + random.getNom());
                capacitesApp[i] = random;
            }
            System.out.println();
            pokemon.apprendCapacites(capacitesApp);
            // affichage(pokemon);
        }
    }
}
