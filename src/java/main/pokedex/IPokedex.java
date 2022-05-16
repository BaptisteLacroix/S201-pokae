/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IPokedex.java
 */
package interfaces.java.main.pokedex;

import interfaces.java.main.pokemon.IEspece;
import interfaces.java.main.pokemon.IPokemon;
import interfaces.java.main.statsPokemon.ICapacite;
import interfaces.java.main.statsPokemon.IType;

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
