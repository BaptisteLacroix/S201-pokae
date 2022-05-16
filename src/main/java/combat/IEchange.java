/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package main.java.combat;

import main.java.combat.IAttaque;
import main.java.pokemon.IPokemon;

/**
 * @author Leo Donati
 *
 */
public class IEchange extends IAttaque {
	public void setPokemon(IPokemon pok);
	public IPokemon echangeCombattant(); 
}
