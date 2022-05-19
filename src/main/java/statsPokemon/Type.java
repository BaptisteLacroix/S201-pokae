/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IType.java
 */
package main.java.statsPokemon;

import main.interfaces.IType;

/**
 * @author Lacroix Baptiste
 *
 */
public enum Type implements IType {
	Plante("Plante"),
	Feu("Feu"),
	Insecte("Insecte"),
	Normal("Normal"),
	Electrik("Electrik"),
	Sol("Sol"),
	Poison("Poison"),
	Eau("Eau"),
	Combat("Combat"),
	Psy("Psy"),
	Roche("Roche"),
	Spectre("Spectre"),
	Glace("Glace"),
	Dragon("Dragon"),
	Vol("Vol");
	private String nom;

	/**
	 *
	 * @param nom
	 */
	Type(String nom) {
		this.nom = nom;
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
