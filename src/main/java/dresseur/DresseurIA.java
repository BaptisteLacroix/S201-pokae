package dresseur;

import attaque.Strategy;
import interfaces.*;
import pokedex.Pokedex;


public class DresseurIA implements IDresseur {
    private String nom;
    private IPokemon[] ranch;
    private int niveau = 0;
    private IStrategy strategy;

    public DresseurIA(String nom) {
        this.nom = nom;
        Pokedex pokedex = new Pokedex();
        this.ranch = pokedex.getRanch();
        this.strategy = new Strategy(this.ranch);
        this.setNiveau();
    }

    @Override
    public String getNom() {
        return this.nom;
    }//Nom du dresseur

    public IPokemon[] getRanch() {
        return this.ranch;
    }

    public void setNiveau() {
        this.niveau = 0;
        for (IPokemon p : ranch) {
            this.niveau += p.getNiveau();
        }
    }

    @Override
    public int getNiveau() {
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
        this.setNiveau();
        for (IPokemon p : this.ranch) {
            p.soigne();
        }
    } //Redonne à tous les pokemon du Ranch leur PV max

    @Override
    public IPokemon choisitCombattant() {
        return this.strategy.choisitCombattant();
    }

    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        return this.strategy.choisitCombattantContre(pok);
    }

    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        return this.strategy.choisitAttaque(attaquant, defenseur);
    }
}
