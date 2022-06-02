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

    @Override
    public IPokemon choisitCombattant() {
        IPokemon pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        while (pokemon.estEvanoui()) {
            pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        }
        return pokemon;
    }

    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        IPokemon pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        while (pokemon.estEvanoui()) {
            pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        }
        return pokemon;
    }

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
