package combat;

import dresseur.Dresseur;
import pokemon.Capacites;
import pokemon.Espece;
import pokemon.Pokemon;
import readingFile.ReadingFile;

import javax.management.BadAttributeValueExpException;
import java.util.*;

public class CombatIA extends Combat {
    private Random rand = new Random();

    public CombatIA(Dresseur d1, Dresseur d2) throws BadAttributeValueExpException {
        super(d1, d2);
        this.combatStart();
    }

    @Override
    public void combatStart() throws BadAttributeValueExpException {
        while (this.getEnd()) {
            //System.out.println(this.nbrTours + " " + this.end);
            if (this.getNbrTours() == 1) {
                this.setP1(this.choixPokemon(this.getD1()));
                this.setP2(this.choixPokemon(this.getD2()));
            }
            this.tourd1(this.getD1(), this.getP1());
            this.tourd2(this.getD2(), this.getP2());
            if (this.getP1().statsSpecifiques.getVitesse() > this.getP2().statsSpecifiques.getVitesse()) {
                this.degats(this.getD1(), this.getP1(), this.getP2(), this.getCapacitep2());
                this.degats(this.getD2(), this.getP2(), this.getP1(), this.getCapacitep1());
            } else {
                this.degats(this.getD2(), this.getP2(), this.getP1(), this.getCapacitep1());
                this.degats(this.getD1(), this.getP1(), this.getP2(), this.getCapacitep2());
            }
            this.setNbrTours(+1);
        }
    }

    @Override
    public Pokemon choixPokemon(Dresseur d) {
        Pokemon choixPokemon = d.ranch.get(this.rand.nextInt(d.ranch.size()-1));
        System.out.println(d.nom + " choose : " + choixPokemon.nom);
        return choixPokemon;
    }

    public Pokemon changePokemon(Dresseur d, Pokemon pA) {
        Pokemon p = this.choixPokemon(d);
        while (p.equals(pA))
            p = this.choixPokemon(d);
        return p;
    }

    @Override
    public void tourd1(Dresseur d, Pokemon p) {
        int choix = this.rand.nextInt(2) + 1;  // Read user input
        if (choix == 1) {
            System.out.println(d.nom + " choose an action to Fight !");
            this.setCapacitep1(this.choixAttaque(p));
        } else {
            System.out.println(d.nom + " choose to Change !");
            this.setP1(this.changePokemon(d, p));
        }
    }

    @Override
    public void tourd2(Dresseur d, Pokemon p) {
        int choix = this.rand.nextInt(2) + 1;  // Read user input
        if (choix == 1) {
            System.out.println(d.nom + " choose an action to Fight !");
            this.setCapacitep2(this.choixAttaque(p));
        } else {
            System.out.println(d.nom + " choose to Change !");
            this.setP2(this.changePokemon(d, p));
        }
    }

    @Override
    public Capacites choixAttaque(Pokemon p) {
        Capacites cap = p.capacites.get(this.rand.nextInt(p.capacites.size()-1) + 1);
        System.out.println(" Capacite choisie : " + cap);
        return cap;
    }
}
