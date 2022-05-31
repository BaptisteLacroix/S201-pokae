package dresseur;

import attaque.Capacite;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokedex.Pokedex;

import java.util.Random;

public class Strategy implements IStrategy {
    private String nom;
    private IPokemon[] ranch;
    private Random rand = new Random();

    public Strategy(String nom) {
        this.nom = nom;
        Pokedex pokedex = new Pokedex();
        this.ranch = pokedex.getRanch();
    }

    public String getNom() {
        return this.nom;
    } //Nom de l'IA

    @Override
    public IPokemon choisitCombattant() {
        return this.ranch[this.rand.nextInt(this.ranch.length)];
    }

    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        return this.ranch[this.rand.nextInt(this.ranch.length)];
    }

    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        int choixAttaque = rand.nextInt(2 + 1) + 1;  // Read user input
        if (choixAttaque == 1) {
            return attaquant.getCapacitesApprises()[this.rand.nextInt(4)];
        } else {
            throw new UnsupportedOperationException(); // Change de Pokemon
        }
    }
}
