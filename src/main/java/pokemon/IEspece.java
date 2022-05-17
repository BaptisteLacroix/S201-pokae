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
	private IStat baseStat;
	private String nom;
	private int niveauDepart;
	private int baseExp;
	private IStat gainsStat;
	private ICapacite[] capSet;
	private IStat evolution;
	private IType[] types;


	public IStat getBaseStat() {
		return baseStat;
	}

	public String getNom() {
		return nom;
	}

	public int getNiveauDepart() {
		return niveauDepart;
	}

	public int getBaseExp() {
		return baseExp;
	}

	public IStat getGainsStat() {
		return gainsStat;
	}

	public ICapacite[] getCapSet() {
		return capSet;
	} //ensemble des capacités disponibles pour cette espèce

	public IStat getEvolution() {
		return evolution;
	}

	public IType[] getTypes() {
		return types;
	} //une espece de pokemon peut avoir un ou deux types


	public IEspece getEvolution(int niveau) {
		return null;
	}  //renvoie null si aucune evolution possible

}
