/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * ICapacite.java
 */
package interfaces.java.main.statsPokemon;

import interfaces.java.main.combat.IAttaque;

/**
 * @author Leo Donati
 *
 */
public class ICapacite extends IAttaque {
	String getNom();
	double getPrecision();
	int getPuissance();
	int getPP();
	void resetPP();
	ICategorie getCategorie();
	IType getType();
}
