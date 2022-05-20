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


	Categorie(String nom) {
		this.nom = nom;
	}


	/**
	 * > Si le nom de l'objet en cours est égal au nom de l'objet passé en paramètre, alors renvoie vrai, sinon renvoie faux
	 *
	 * @return Le nom de l'objet.
	 */
	@Override
	public boolean isSpecial() {
		return Objects.equals(this.nom, Physique.nom);
	}


	/**
	 * > Cette fonction renvoie le nom de la Catégorie
	 *
	 * @return Le nom de la personne.
	 */
	@Override
	public String getNom() {
		return this.nom;
	}
}
