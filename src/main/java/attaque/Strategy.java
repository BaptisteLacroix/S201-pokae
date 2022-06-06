/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date Strategy.java
 */
package attaque;

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
}
