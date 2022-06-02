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
	private IPokemon pokemon;

	public Echange(IPokemon pokemon) {
		this.pokemon = pokemon;
	}

	@Override
	public void setPokemon(IPokemon pok) //choisit le Pokemon remplaçant
	{
		this.pokemon = pok;
	}

	@Override
	public IPokemon echangeCombattant()  //active le remplacement (et renvoie l'ancien pokemon)
	{
		return this.pokemon;
	}

	@Override
	public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
		return 0;
	}

	@Override
	public void utilise() {}
}
