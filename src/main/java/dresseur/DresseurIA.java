package dresseur;

import attaque.Strategy;
import interfaces.*;
import pokedex.Pokedex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author Lacroix Baptiste and Vidal Théo
 */
public class DresseurIA implements IDresseur {
    private final String nom;
    private final IPokemon[] ranch;
    private int niveau = 0;
    private final IStrategy strategy;

    public DresseurIA(String nom) {
        this.nom = nom;
        this.writeLogs("création du dresseur.");
        Pokedex pokedex = new Pokedex();
        this.writeLogs("création du ranch.");
        this.ranch = pokedex.engendreRanch();
        this.writeLogs("génération du ranch terminé.");
        this.strategy = new Strategy(this.ranch);
        this.setNiveau();
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
    public void setNiveau() {
        this.niveau = 0;
        for (IPokemon p : ranch) {
            this.niveau += p.getNiveau();
        }
    }

    /**
     * C'est une méthode qui renvoie le niveau du formateur.
     */
    @Override
    public int getNiveau() {
        this.niveau = 0;
        for (IPokemon p : ranch) {
            this.niveau += p.getNiveau();
        }
        return this.niveau;
    }//Niveau du dresseur

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
        this.setNiveau();
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
        return this.strategy.choisitCombattant();
    }

    /**
     * Cette méthode est utilisée pour choisir le pokémon qui combattra le pokémon adverse.
     *
     * @param pok pokemon de l'adversaire
     * @return Pokemon qui combattra
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        return this.strategy.choisitCombattantContre(pok);
    }

    /**
     * Cette méthode permet de choisir l'attaque qui sera utilisée contre le pokémon défenseur.
     *
     * @param attaquant pokemon de l'attaquant
     * @param defenseur pokemon du defenseur
     * @return Une Instance de Attaque
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        return this.strategy.choisitAttaque(attaquant, defenseur);
    }

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
