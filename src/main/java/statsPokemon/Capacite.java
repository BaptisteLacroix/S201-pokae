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
    private String nom;
    private double precision;
    private int puissance;
    private int PP;
    private ICategorie categorie;
    private IType type;

    /**
     *
     * @return
     */
    @Override
    public String getNom() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Override
    public double getPrecision() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Override
    public int getPuissance() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Override
    public int getPP() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     */
    @Override
    public void resetPP() {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @return
     */
    @Override
    public ICategorie getCategorie() {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @return
     */
    @Override
    public IType getType() {
        throw new UnsupportedOperationException();
    }
}
