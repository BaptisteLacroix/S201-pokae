/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package interfaces.combat;

import interfaces.combat.IAttaque;
import interfaces.pokemon.IPokemon;

/**
 * @author Leo Donati
 *
 */
public class IEchange extends IAttaque {
	public void setPokemon(IPokemon pok);
	public IPokemon echangeCombattant(); 
}
