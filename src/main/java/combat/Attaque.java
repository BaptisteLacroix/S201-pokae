/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IAttaque.java
 */
package main.java.combat;

import main.interfaces.IAttaque;
import main.interfaces.IPokemon;

/**
 * @author Leo Donati
 * Une attaque est une action du Pokemon durant une bataille.
 * Il y a deux types d'attaques :
 * - les capacités (interface ICapacity)
 * - les échanges (interface IEchange)
 */
public class Attaque implements IAttaque {
	//renvoie le nombre de points de vie qu'il faut enlever au receveur

	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		throw new UnsupportedOperationException();
	}

	//fait diminuer de 1 le nombre restant de fois où l'attaque peut être utilisée
	public void utilise() {
		throw new UnsupportedOperationException();
	}
}
