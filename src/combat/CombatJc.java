package combat;

import dresseur.Dresseur;
import pokemon.Capacites;
import pokemon.Pokemon;

import javax.management.BadAttributeValueExpException;
import java.util.Scanner;

public class CombatJc extends Combat {

    public CombatJc(Dresseur d1, Dresseur d2) throws BadAttributeValueExpException {
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
    public Pokemon choixPokemon(Dresseur d) throws BadAttributeValueExpException {
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println(d.nom + " Give the name of the Pokemon that will fight : ");
        String choixPokemon = input.nextLine();  // Read user input
        for (Pokemon p : d.ranch) {
            if (p.nom.equals(choixPokemon))
                return p;
        }
        throw new BadAttributeValueExpException("Item not found!");
    }

    @Override
    public void tourd1(Dresseur d, Pokemon p) throws BadAttributeValueExpException {
        System.out.println(p.statsSpecifiques);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println(d.nom + " choose an action : Fight (1) or Change (2)");
        int choix = Integer.parseInt(input.nextLine());  // Read user input
        if (choix == 1) {
            this.setCapacitep1(this.choixAttaque(p));
        } else {
            this.choixPokemon(d);
        }
    }

    @Override
    public void tourd2(Dresseur d, Pokemon p) throws BadAttributeValueExpException {
        System.out.println("\n" + p.statsSpecifiques);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println(d.nom + " choose an action : Fight (1) or Change (2)");
        int choix = Integer.parseInt(input.nextLine());  // Read user input
        if (choix == 1) {
            this.setCapacitep2(this.choixAttaque(p));
        } else {
            this.choixPokemon(d);
        }
    }

    @Override
    public Capacites choixAttaque(Pokemon p) throws BadAttributeValueExpException {
        System.out.println("\n" + p.capacites);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter the name of the capacity you want : ");
        String nameCap = input.nextLine();  // Read user input
        for (Capacites cap : p.capacites) {
            if (cap.nom.equals(nameCap))
                return cap;
        }
        throw new BadAttributeValueExpException("Item not found!");
    }
}
