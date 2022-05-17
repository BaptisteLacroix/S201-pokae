/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * ICategory.java
 */
package main.java.statsPokemon;

import main.interfaces.ICategorie;

/**
 * @author Leo Donati
 * Il s'agit de la catégorie d'une capacité :
 *  - soit Physique
 *  - soit Special
 */
public class Categorie implements ICategorie {

	@Override
	public boolean isSpecial() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getNom() {
		throw new UnsupportedOperationException();
	}
}
