/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ICapacite.java
 */
package main.java.statsPokemon;


import main.interfaces.ICapacite;
import main.interfaces.ICategorie;
import main.interfaces.IType;
import main.java.combat.Attaque;

/**
 * @author Leo Donati
 * Une capacité est un type d'attaque qu ele pokemon peut utilser
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
     * > Cette fonction renvoie le nom du Pokemon
     *
     * @return Le nom de la personne.
     */
    @Override
    public String getNom() {
        return this.nom;
    }


    /**
     * Cette fonction renvoie la précision du Pokemon
     *
     * @return La précision du classificateur.
     */
    @Override
    public double getPrecision() {
        return this.precision;
    }


    /**
     * > Cette fonction retourne la valeur de l'attribut puissance
     *
     * @return La puissance du moteur.
     */
    @Override
    public int getPuissance() {
        return this.puissance;
    }

    /**
     * > Cette fonction renvoie le PP du coup
     *
     * @return Le PP du déménagement.
     */
    @Override
    public int getPP() {
        return this.PP;
    }


    /**
     * Réinitialise le PP du mouvement à sa valeur de base.
     */
    @Override
    public void resetPP() {
        this.PP = this.PP_base;
    }


    /**
     * > Cette fonction renvoie la catégorie de l'objet courant Pokemon
     *
     * @return La catégorie du produit.
     */
    @Override
    public ICategorie getCategorie() {
        return this.categorie;
    }


    /**
     * Renvoie le type du Pokemon
     *
     * @return Le type de la variable.
     */
    @Override
    public IType getType() {
        return this.type;
    }

}
