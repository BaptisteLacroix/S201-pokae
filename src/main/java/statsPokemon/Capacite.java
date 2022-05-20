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
    private int PP_base;
    private int PP;
    private ICategorie categorie;
    private IType type;

    public Capacite(String nom, double precision, int puissance, int PP, ICategorie categorie, IType type) {
        this.nom = nom;
        this.precision = precision;
        this.puissance = puissance;
        this.PP_base = PP;
        this.PP = PP;
        this.categorie = categorie;
        this.type = type;
    }

    /**
     *
     * @return
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     *
     * @return
     */
    @Override
    public double getPrecision() {
        return this.precision;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPuissance() {
        return this.puissance;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPP() {
        return this.PP;
    }

    /**
     *
     */
    @Override
    public void resetPP() {
        this.PP = this.PP_base;
    }

    /**
     *
     * @return
     */
    @Override
    public ICategorie getCategorie() {
        return this.categorie;
    }

    /**
     * 
     * @return
     */
    @Override
    public IType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Capacite{" +
                "nom='" + nom + '\'' +
                ", precision=" + precision +
                ", puissance=" + puissance +
                ", PP_base=" + PP_base +
                ", PP=" + PP +
                ", categorie=" + categorie +
                ", type=" + type +
                '}';
    }
}
