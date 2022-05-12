/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IPokedex.java
 */
package interfaces.pokedex;

import interfaces.pokemon.IEspece;
import interfaces.pokemon.IPokemon;
import interfaces.statsPokemon.ICapacite;
import interfaces.statsPokemon.IType;

/**
 * @author Leo Donati
 *
 */
public interface IPokedex {
	public IPokemon[] engendreRanch();
	public IEspece getInfo(String nomEspece);
	public Double getEfficacite(IType attaque, IType defense);
	public ICapacite getCapacite(String nom);
	public ICapacite getCapacite(int n);
}
