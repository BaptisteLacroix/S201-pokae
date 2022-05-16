/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEspece.java
 */
package main.java.pokemon;

import main.java.statsPokemon.ICapacite;
import main.java.statsPokemon.IStat;
import main.java.statsPokemon.IType;

/**
 * @author Leo Donati
 *
 */
public class IEspece {
	public IStat getBaseStat();
	public String getNom();
	public int getNiveauDepart();
	public int getBaseExp();
	public IStat getGainsStat();
	public ICapacite[] getCapSet();
	public IEspece getEvolution(int niveau);  //renvoie null si aucune evolution possible
	public IType[] getTypes();
}
