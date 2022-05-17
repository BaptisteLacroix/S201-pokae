/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package main.java.combat;

import main.interfaces.IEchange;
import main.interfaces.IPokemon;

/**
 * @author Leo Donati
 * C'est un autre type d'attaque
 * Correspond à l'échange du Pokemon du combat avec un autre Pokemon du ranch
 */
public class Echange extends Attaque implements IEchange {
	public void setPokemon(IPokemon pok) //choisit le Pokemon remplaçant
	{
		throw new UnsupportedOperationException();
	}

	public IPokemon echangeCombattant()  //active le remplacement (et renvoie l'ancien pokemon)
	{
		throw new UnsupportedOperationException();
	}
}
