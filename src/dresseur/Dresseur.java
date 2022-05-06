package dresseur;

import pokedex.Pokedex;
import pokemon.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dresseur implements Serializable {

    public String nom;
    public int niveau;
    public List<Pokemon> ranch = new ArrayList<>();
    public Pokedex pokedex = new Pokedex();

    public Dresseur(String nom) {
        this.nom = nom;
        for (int i = 0; i < 6; i ++)
            ranch.add(this.pokedex.choosePokemon());
    }

    @Override
    public String toString() {
        return "Dresseur{" +
                "nom='" + nom + '\'' +
                ", niveau=" + niveau +
                ", ranch=" + ranch +
                '}';
    }
}
