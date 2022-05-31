/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ITour.java
 */
package combat;

import interfaces.ICombat;
import interfaces.IDresseur;
import interfaces.IPokemon;

/**
 * @author Leo Donati
 */
public class Tour {
    private final IDresseur dresseur1;
    private IPokemon pokemon1;
    private final IDresseur dresseur2;
    private IPokemon pokemon2;
    private int ko1 = 0;
    private int ko2 = 0;

    public Tour(IDresseur dresseur1, IDresseur dresseur2) {
        this.dresseur1 = dresseur1;
        this.dresseur2 = dresseur2;
    }

    public void commence() {
        this.pokemon1 = this.dresseur1.choisitCombattant();
        this.pokemon2 = this.dresseur2.choisitCombattant();
        while (this.ko1 != 6 && this.ko2 != 6) {
            ICombat combat = new Combat(this.dresseur1, this.pokemon1, this.dresseur2, this.pokemon2);
            combat.commence();
            if (this.pokemon1.estEvanoui()) {
                this.ko1 ++;
                this.pokemon1 = this.dresseur1.choisitCombattant();
            }
            if (this.pokemon2.estEvanoui()) {
                this.ko2 ++;
                this.pokemon2 = this.dresseur2.choisitCombattant();
            }
        }
    }        //Lance un tour de combat
}
