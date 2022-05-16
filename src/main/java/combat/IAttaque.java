/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IAttaque.java
 */
package main.java.combat;

import main.java.pokemon.IPokemon;

/**
 * @author Leo Donati
 *
 */
public class IAttaque {
	int calculeDommage(IPokemon lanceur, IPokemon receveur);
	void utilise();
}
