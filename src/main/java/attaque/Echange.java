/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package attaque;

import interfaces.IEchange;
import interfaces.IPokemon;

/**
 * @author Lacroix baptiste and Vidal Théo
 * C'est un autre type d'attaque
 * Correspond à l'échange du Pokemon du combat avec un autre Pokemon du ranch
 */
public class Echange implements IEchange {
	private IPokemon newPokemon;

	@Override
	public void setPokemon(IPokemon pok) //choisit le Pokemon remplaçant
	{
		this.newPokemon = pok;
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
