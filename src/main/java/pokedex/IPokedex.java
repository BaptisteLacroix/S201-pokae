/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IPokedex.java
 */
package main.java.pokedex;

import main.java.pokemon.IEspece;
import main.java.pokemon.IPokemon;
import main.java.statsPokemon.ICapacite;
import main.java.statsPokemon.IType;

/**
 * @author Leo Donati
 */
public class IPokedex {
    private IPokemon[] ranch;

    public IPokemon[] engendreRanch() {
        return null;
    }           //Renvoie un tableau de 6 Pokemon au hasard

    public IEspece getInfo(String nomEspece) {
        return null;
    }           //Renvoie une instance de l'espèce de Pokemon dont on fournit le nom

    public Double getEfficacite(IType attaque, IType defense) {
        return null;
    }           //Calcule l'efficacité d'un type d'attaque sur un type de cible

    public ICapacite getCapacite(String nomCapacite) {
        return null;
}               //Renvoie une instance de la capacité appelée nomCapacite

    public ICapacite getCapacite(int nunCapacite) {
        return null;
    }
}
