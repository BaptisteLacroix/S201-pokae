/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * ICategory.java
 */
package main.java.statsPokemon;

import main.interfaces.ICategorie;

import java.util.Objects;

/**
 * @author Lacroix Baptiste
 * Il s'agit de la catégorie d'une capacité :
 *  - soit Physique
 *  - soit Special
 */
public enum Categorie implements ICategorie {
	Physique("Physique"),
	Special("Special");
	private String nom;

	/**
	 *
	 * @param nom
	 */
	Categorie(String nom) {
		this.nom = nom;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public boolean isSpecial() {
		return Objects.equals(this.nom, Physique.nom);
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String getNom() {
		return this.nom;
	}
}
