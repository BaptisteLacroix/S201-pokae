/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IType.java
 */
package statsPokemon;

import interfaces.IType;

/**
 * @author Lacroix baptiste and Vidal Théo
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
	 * Constructeur de l'enum Type.
 	 */
	Type(String nom) {
		this.nom = nom;
	}


	/**
	 * Cette fonction renvoie le nom du type
	 *
	 * @return Le nom du type.
	 */
	@Override
	public String getNom() {
		return this.nom;
	}
}
