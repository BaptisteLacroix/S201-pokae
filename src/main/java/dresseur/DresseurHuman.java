package dresseur;

import attaque.Echange;
import interfaces.*;
import pokedex.Pokedex;

import java.util.Arrays;
import java.util.Scanner;


/**
 * @author Lacroix Baptiste
 */

public class DresseurHuman implements IDresseur {
    private String nom;
    private IPokemon[] ranch;
    private int niveau = 0;

    public DresseurHuman(String nom) {
        this.nom = nom;
        Pokedex pokedex = new Pokedex();
        this.ranch = pokedex.getRanch();
        this.getNiveau();
    }

    @Override
    public String getNom() {
        return this.nom;
    }//Nom du dresseur

    public IPokemon[] getRanch() {
        return this.ranch;
    }

    @Override
    public int getNiveau() {
        this.niveau = 0;
        for (IPokemon p : ranch) {
            this.niveau += p.getNiveau();
        }
        return this.niveau;
    }//Niveau du dresseur

    @Override
    public IPokemon getPokemon(int i) {
        return ranch[i];
    }//Récupère le i-eme Pokemon du Ranch

    @Override
    public void enseigne(IPokemon pok, ICapacite[] caps) {
        ICapacite[] capSet = pok.getEspece().getCapSet();
        int cmp = 0;
        for (int i = 0; i < 4; i++) {
            if (capSet[i].getNom().equals(caps[i].getNom())) {
                cmp++;
            }
        }
        if (cmp == 4) {
            pok.apprendCapacites(caps);
        }
    }//Donne au pokemon pok les capacites caps

    @Override
    public void soigneRanch() {
        for (IPokemon p : this.ranch) {
            p.soigne();
        }
    } //Redonne à tous les pokemon du Ranch leur PV max

    @Override
    public IPokemon choisitCombattant() {
        IPokemon pok = null;
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        for (IPokemon pokemon : this.ranch)
            System.out.printf("%-32s", "nom : " + pokemon.getNom() + " | stats : " + pokemon.getStat() +  " | PourcentagePV : " + pokemon.getPourcentagePV() + "\n");
        System.out.println(this.nom + " Give the name of the Pokemon that will fight : ");
        String choixPokemon = input.nextLine();  // Read user input
        for (IPokemon p : this.ranch) {
            if (p.getNom().equals(choixPokemon) && !p.estEvanoui())
                pok = p;
        }
        return pok;
    } //Choisit le premier Pokemon pour combattre

    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        IPokemon pokemon = null;
        System.out.println(pok.toString());
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println(this.nom + " choose a pokemon who is gonna fight against " + pok.getNom() + " : ");
        System.out.println("Your ranch : " + Arrays.toString(this.ranch));
        String choixPokemon = input.next();  // Read user input
        for (IPokemon p : ranch) {
            if (p.getNom().equals(choixPokemon) && !p.estEvanoui())
                pokemon = p;
        }
        return pokemon;
    } //Choisit le Pokemon pour combattre contre pok

    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        ICapacite attaque = null;
        System.out.println(defenseur.toString());
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println(this.nom + " what attack do you want to use (capacity 1) or (change 2) : ");
        int choixAttaque = input.nextInt();  // Read user input
        if (choixAttaque == 1) {
            System.out.println(attaquant.getNom() + " knows : " + Arrays.toString(attaquant.getCapacitesApprises()));
            input = new Scanner(System.in);  // Create a Scanner object
            System.out.println(this.nom + " give the name of the capacity you want to use : ");
            String choixCap = input.nextLine();  // Read user input
            for (ICapacite p : attaquant.getCapacitesApprises()) {
                if (p.getNom().equals(choixCap))
                    attaque = p;
            }
            return attaque;
        } else {
            IEchange echange = new Echange(attaquant);
            echange.setPokemon(this.choisitCombattantContre(defenseur));
            return echange; // Change de Pokemon
        }
    } //Choisit l'attaque à utiliser contre le pokemon defenseur
}
