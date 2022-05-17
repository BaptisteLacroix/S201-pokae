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
 * @author Leo Donati
 *
 */
public class Type implements IType {
	private String nom;

	public Type(String nom) {
		this.nom = nom;
	}

	@Override
	public String getNom() {
		return this.nom;
	}
}
