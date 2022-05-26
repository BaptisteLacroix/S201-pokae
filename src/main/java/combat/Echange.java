/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * IEchange.java
 */
package combat;

import interfaces.ICapacite;
import interfaces.IEchange;
import interfaces.IPokemon;

/**
 * @author Lacroix baptiste and Vidal Théo
 * C'est un autre type d'attaque
 * Correspond à l'échange du Pokemon du combat avec un autre Pokemon du ranch
 */
public class Echange extends Attaque implements IEchange {
	private IPokemon pokemon;

	public Echange(IPokemon pokemon, ICapacite capacite) {
		super(capacite);
		this.pokemon = pokemon;
	}

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
}
