/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package interfaces.java.main.combat;

import interfaces.java.main.combat.IAttaque;
import interfaces.java.main.pokemon.IPokemon;

/**
 * @author Leo Donati
 *
 */
public class IEchange extends IAttaque {
	public void setPokemon(IPokemon pok);
	public IPokemon echangeCombattant(); 
}
