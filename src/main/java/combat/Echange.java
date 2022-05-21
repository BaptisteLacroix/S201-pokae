/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package combat;

import interfaces.IEchange;
import interfaces.IPokemon;

/**
 * @author Leo Donati
 * C'est un autre type d'attaque
 * Correspond à l'échange du Pokemon du combat avec un autre Pokemon du ranch
 */
public class Echange implements IEchange {

	@Override
	public void setPokemon(IPokemon pok) //choisit le Pokemon remplaçant
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public IPokemon echangeCombattant()  //active le remplacement (et renvoie l'ancien pokemon)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		return 0;
	}

	@Override
	public void utilise() {}
}
