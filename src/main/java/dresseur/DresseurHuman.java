/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date DresseurHuman.java
 */
package dresseur;

import attaque.Echange;
import interfaces.*;
import pokedex.Pokedex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;


/**
 * Classe qui gère la création d'un dresseur humain. Implémente IDresseur.
 *
 * @author Lacroix Baptiste and Vidal Théo
 */
public class DresseurHuman implements IDresseur {
    /**
     * Nom du Dresseur Humain
     */
    private final String nom;
    /**
     * Contient les Pokémons du dresseur Humain
     */
    private final IPokemon[] ranch;

    /**
     * Constructeur du Dresseur Humain
     *
     * @param nom Nom du dresseur Humain
     */
    public DresseurHuman(String nom) {
        this.nom = nom;
        this.writeLogs("création du dresseur.");
        Pokedex pokedex = new Pokedex();
        this.writeLogs("création du ranch.");
        this.ranch = pokedex.engendreRanch();
        this.writeLogs("génération du ranch terminé.");
    }

    /**
     * Une méthode qui renvoie le nom du formateur.
     */
    @Override
    public String getNom() {
        return this.nom;
    }//Nom du dresseur

    /**
     * Cette fonction renvoie le tableau ranch.
     *
     * @return Le tableau du ranch.
     */
    public IPokemon[] getRanch() {
        return this.ranch;
    }

    /**
     * C'est une méthode qui renvoie le niveau du formateur.
     */
    public int getNiveau() {
        int niveau = 0;
        for (IPokemon p : ranch) {
            niveau += p.getNiveau();
        }
        return niveau;
    } //Niveau du dresseur

    /**
     * C'est une méthode qui retourne le ième pokémon du ranch.
     */
    @Override
    public IPokemon getPokemon(int i) {
        return ranch[i];
    }//Récupère le i-eme Pokemon du Ranch


    /**
     * Donne au pokemon pok les capacites caps
     *
     * @param pok  pokemonn a qui on va enseigner les capacités
     * @param caps tableau de capacités a enseigner
     */
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

    /**
     * C'est une méthode qui soigne tous les pokémons du ranch.
     */
    @Override
    public void soigneRanch() {
        for (IPokemon p : this.ranch) {
            p.soigne();
        }
    } //Redonne à tous les pokemon du Ranch leur PV max


    /**
     * Cette méthode est utilisée pour choisir le premier pokémon qui combattra.
     *
     * @return Pokemon qui combattra
     */
    @Override
    public IPokemon choisitCombattant() {
        IPokemon pok = null;
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        for (IPokemon pokemon : this.ranch)
            System.out.printf("%-32s", "nom : " + pokemon.getNom() + " | stats : " + pokemon.getStat() + " | PourcentagePV : " + pokemon.getPourcentagePV() + "\n");
        System.out.println(this.nom + " Give the name of the Pokemon that will fight : ");
        String choixPokemon = input.nextLine();  // Read user input
        for (IPokemon p : this.ranch) {
            if (p.getNom().equals(choixPokemon) && !p.estEvanoui())
                pok = p;
        }
        return pok;
    } //Choisit le premier Pokemon pour combattre

    /**
     * Cette méthode est utilisée pour choisir le pokémon qui combattra le pokémon adverse.
     *
     * @param pok pokemon de l'adversaire
     * @return Pokemon qui combattra
     */
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


    /**
     * Cette méthode permet de choisir l'attaque qui sera utilisée contre le pokémon défenseur.
     *
     * @param attaquant pokemon de l'attaquant
     * @param defenseur pokemon du defenseur
     * @return Une Instance de Attaque
     */
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

    /**
     * Il écrit la date et le texte dans un fichier appelé log.txt
     *
     * @param texte le texte à écrire dans le fichier journal
     */
    private void writeLogs(String texte) {
        Date date = new Date();
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true));
            writer.println(date + " : " + texte);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}