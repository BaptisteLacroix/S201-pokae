/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IPokedex.java
 */
package main.java.pokedex;

import main.java.pokemon.IEspece;
import main.java.pokemon.IPokemon;
import main.java.statsPokemon.ICapacite;
import main.java.statsPokemon.IType;

/**
 * @author Leo Donati
 *
 */
public class IPokedex {
	public IPokemon[] engendreRanch();
	public IEspece getInfo(String nomEspece);
	public Double getEfficacite(IType attaque, IType defense);
	public ICapacite getCapacite(String nom);
	public ICapacite getCapacite(int n);
}
