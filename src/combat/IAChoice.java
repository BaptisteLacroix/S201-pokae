package combat;

import dresseur.Dresseur;
import pokemon.Pokemon;

public class IAChoice {
    private Pokemon p;

    public Pokemon choosePokemon(Dresseur d) {
        this.p = d.ranch.get(0);
        for (Pokemon p : d.ranch) {
            // if ()
            System.out.println("ee");
        }
        return this.p;
    }
}
