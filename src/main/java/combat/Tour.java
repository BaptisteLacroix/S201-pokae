/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ITour.java
 */
package combat;

import attaque.Echange;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.ITour;

/**
 * @author Lacroix Baptiste
 */
public class Tour implements ITour {
    private final IPokemon pokemon1;
    private final IAttaque attaque1;
    private final IPokemon pokemon2;
    private final IAttaque attaque2;
    private boolean echange1 = false;
    private boolean echange2 = false;
    private int damageRecuPok1;
    private int damageRecuPok2;

    public Tour(IPokemon pokemon1, IAttaque attaque1, IPokemon pokemon2, IAttaque attaque2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.attaque1 = attaque1;
        this.attaque2 = attaque2;
    }

    public void commence() {
        if (attaque1.getClass() == Echange.class && attaque2.getClass() != Echange.class) {
            this.damageRecuPok1 = attaque2.calculeDommage(this.pokemon2, this.pokemon1);
            this.pokemon1.getStat().setPV(this.pokemon1.getStat().getPV() - this.damageRecuPok1);
            this.echange1 = true;
        } else if (attaque1.getClass() != Echange.class && attaque2.getClass() == Echange.class) {
            this.damageRecuPok2 = attaque1.calculeDommage(this.pokemon1, this.pokemon2);
            this.pokemon2.getStat().setPV(this.pokemon2.getStat().getPV() - this.damageRecuPok2);
            this.echange2 = true;
        } else if (attaque1.getClass() != Echange.class && attaque2.getClass() != Echange.class) {
            // Si vitesses P1 > P2 alors P1 commence sinon P2
            if (this.pokemon1.getStat().getVitesse() > this.pokemon2.getStat().getVitesse()) {
                this.damageRecuPok1 = attaque2.calculeDommage(this.pokemon2, this.pokemon1);
                this.damageRecuPok2 = attaque1.calculeDommage(this.pokemon1, this.pokemon2);
                this.pokemon2.getStat().setPV(this.pokemon2.getStat().getPV() - this.damageRecuPok1);
                this.pokemon1.getStat().setPV(this.pokemon1.getStat().getPV() - this.damageRecuPok2);
            } else {
                this.pokemon1.getStat().setPV(this.pokemon1.getStat().getPV() - this.damageRecuPok2);
                this.pokemon2.getStat().setPV(this.pokemon2.getStat().getPV() - this.damageRecuPok1);
            }
        }
    }        //Lance un tour de combat

    public String toString() {
        if (echange1 && !echange2) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1 + "\n"
                    + this.pokemon1 + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2;
        } else if (!echange1 && echange2) {
            return "Le Dresseur 2 a changé son Pokémon par : " + this.pokemon2 + "\n"
                    + this.pokemon2 + " a subit " + this.damageRecuPok2 + " de damage par " + this.pokemon1;
        } else if (echange1 && echange2) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1 + "\n"
                    + "Le Dresseur 2 a changé son Pokémon par : " + this.pokemon2 + "\n"
                    + "Aucun n'a recu de dammage.";
        } else if (this.pokemon1.getStat().getVitesse() > this.pokemon2.getStat().getVitesse()) {
            return this.pokemon1 + " attaque le premier en infligeant " + this.damageRecuPok2 + "\n"
                    + this.pokemon2 + " attaque le deuxième en infligeant " + this.damageRecuPok1;
        } else {
            return this.pokemon2 + " attaque le premier en infligeant " + this.damageRecuPok1 + "\n"
                    + this.pokemon1 + " attaque le premier en infligeant " + this.damageRecuPok2;
        }
    }
}
