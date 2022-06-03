package attaque;

import interfaces.IAttaque;
import interfaces.IEchange;
import interfaces.IPokemon;
import interfaces.IStrategy;

import java.util.Random;

public class Strategy implements IStrategy {
    private IPokemon[] ranch;
    private Random rand = new Random();

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
            return attaquant.getCapacitesApprises()[this.rand.nextInt(4)];
        } else {
            IEchange echange = new Echange(attaquant);
            echange.setPokemon(this.choisitCombattantContre(defenseur));
            return echange; // Change de Pokemon
        }
    }
}
