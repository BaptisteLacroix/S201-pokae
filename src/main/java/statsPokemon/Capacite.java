/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 * @date
 * ICapacite.java
 */
package main.java.statsPokemon;


import main.interfaces.ICapacite;
import main.interfaces.ICategorie;
import main.interfaces.IType;
import main.java.combat.Attaque;

/**
 * @author Leo Donati
 *	Une capacité est un type d'attaque qu ele pokemon peut utilser
 */
public class Capacite extends Attaque implements ICapacite {

    @Override
    public String getNom() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getPrecision() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPuissance() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPP() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetPP() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ICategorie getCategorie() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IType getType() {
        throw new UnsupportedOperationException();
    }
}
