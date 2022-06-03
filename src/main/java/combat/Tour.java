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
        this.damageRecuPok1 = this.pokemon1.getStat().getPV();
        this.damageRecuPok2 = this.pokemon2.getStat().getPV();
    }

    @Override
    public void commence() {
        if (attaque1.getClass() == Echange.class && attaque2.getClass() != Echange.class) {
            this.pokemon1.subitAttaqueDe(pokemon2, attaque2);
            this.damageRecuPok1 -= this.pokemon1.getStat().getPV();
            this.echange1 = true;
            System.out.println(this);
        } else if (attaque1.getClass() != Echange.class && attaque2.getClass() == Echange.class) {
            this.pokemon2.subitAttaqueDe(pokemon1, attaque1);
            this.damageRecuPok2 -= this.pokemon2.getStat().getPV();
            this.echange2 = true;
            System.out.println(this);
        } else if (attaque1.getClass() == Echange.class) {
            this.echange1 = true;
            this.echange2 = true;
            System.out.println(this);
        } else {
            // Si vitesses P1 > P2 alors P1 commence sinon P2
            if (this.pokemon1.getStat().getVitesse() > this.pokemon2.getStat().getVitesse()) {
                this.pokemon2.subitAttaqueDe(pokemon1, attaque1);
                this.pokemon1.subitAttaqueDe(pokemon2, attaque2);
            } else {
                this.pokemon1.subitAttaqueDe(pokemon2, attaque2);
                this.pokemon2.subitAttaqueDe(pokemon1, attaque1);
            }
            this.damageRecuPok1 -= this.pokemon1.getStat().getPV();
            this.damageRecuPok2 -= this.pokemon2.getStat().getPV();
            System.out.println(this);
        }
    }        //Lance un tour de combat

    @Override
    public String toString() {
        if (echange1 && !echange2) {
            return this.echange1check();
        } else if (!echange1 && echange2) {
            return this.echange2check();
        } else if (echange1 && echange2) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + "Le Dresseur 2 a changé son Pokémon par : " + this.pokemon2.getNom() + "\n"
                    + "Aucun n'a recu de dammage."
                    + "\nIl reste " + this.pokemon1.getStat().getPV() + "pv a " + this.pokemon1.getNom()
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
        } else if (this.pokemon1.getStat().getVitesse() > this.pokemon2.getStat().getVitesse()) {
            return this.printWhoIsKo1();
        } else {
            return this.printWhoIsKo2();
        }
    }

    private String echange1check() {
        if (this.pokemon1.estEvanoui()) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\n" + this.pokemon1.getNom() + " est ko !"
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
        } else {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\nIl reste " + this.pokemon1.getStat().getPV() + "pv a " + this.pokemon1.getNom()
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
        }
    }

    private String echange2check() {
        if (this.pokemon2.estEvanoui()) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\n" + this.pokemon2.getNom() + " est ko !"
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
        } else {
            return "Le Dresseur 2 a changé son Pokémon par : " + this.pokemon2.getNom() + "\n"
                    + this.pokemon2.getNom() + " a subit " + this.damageRecuPok2 + " de damage par " + this.pokemon1.getNom()
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom()
                    + "\nIl reste " + this.pokemon1.getStat().getPV() + "pv a " + this.pokemon1.getNom();
        }
    }

    private String printWhoIsKo1() {
        if (this.pokemon1.estEvanoui() && !this.pokemon2.estEvanoui()) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\n" + this.pokemon1.getNom() + " est ko !"
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
        } else if (this.pokemon2.estEvanoui() && !this.pokemon1.estEvanoui()) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\n" + this.pokemon2.getNom() + " est ko !"
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
        } else if (this.pokemon1.estEvanoui() && this.pokemon2.estEvanoui()) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\n" + this.pokemon1.getNom() + " est ko !"
                    + "\n" + this.pokemon2.getNom() + " est ko !";
        } else
            return this.pokemon1.getNom() + " attaque le premier en infligeant " + this.damageRecuPok2 + "\n"
                    + this.pokemon2.getNom() + " attaque le deuxième en infligeant " + this.damageRecuPok1
                    + "\nIl reste " + this.pokemon1.getStat().getPV() + "pv a " + this.pokemon1.getNom()
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom();
    }

    private String printWhoIsKo2() {
        if (this.pokemon1.estEvanoui() && !this.pokemon2.estEvanoui()) {
            return this.pokemon2.getNom() + " attaque le premier en infligeant " + this.damageRecuPok1 + "\n"
                    + this.pokemon1.getNom() + " attaque le deuxième en infligeant " + this.damageRecuPok2
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom()
                    + "\n" + this.pokemon1.getNom() + " est ko !";
        } else if (this.pokemon2.estEvanoui() && !this.pokemon1.estEvanoui()) {
            return this.pokemon2.getNom() + " attaque le premier en infligeant " + this.damageRecuPok1 + "\n"
                    + this.pokemon1.getNom() + " attaque le deuxième en infligeant " + this.damageRecuPok2
                    + "\n" + this.pokemon2.getNom() + " est ko !"
                    + "\nIl reste " + this.pokemon1.getStat().getPV() + "pv a " + this.pokemon1.getNom();
        } else if (this.pokemon1.estEvanoui() && this.pokemon2.estEvanoui()) {
            return "Le Dresseur 1 a changé son Pokémon par : " + this.pokemon1.getNom() + "\n"
                    + this.pokemon1.getNom() + " a subit " + this.damageRecuPok1 + " de damage par " + this.pokemon2.getNom()
                    + "\n" + this.pokemon1.getNom() + " est ko !"
                    + "\n" + this.pokemon2.getNom() + " est ko !";
        } else
            return this.pokemon2.getNom() + " attaque le premier en infligeant " + this.damageRecuPok1 + "\n"
                    + this.pokemon1.getNom() + " attaque le deuxième en infligeant " + this.damageRecuPok2
                    + "\nIl reste " + this.pokemon2.getStat().getPV() + "pv a " + this.pokemon2.getNom()
                    + "\nIl reste " + this.pokemon1.getStat().getPV() + "pv a " + this.pokemon1.getNom();
    }
}
