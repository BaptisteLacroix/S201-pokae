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
 * @author Lacroix Baptiste
 * Une capacité est un type d'attaque que le pokemon peut utilser
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
     * Cette fonction renvoie le nom de la Capacité
     *
     * @return Le nom de la capacité.
     */
    @Override
    public String getNom() {
        return this.nom;
    }


    /**
     * Cette fonction renvoie la précision de la capacité
     *
     * @return La précision de la capacité.
     */
    @Override
    public double getPrecision() {
        return this.precision;
    }


    /**
     * Cette fonction retourne la valeur de l'attribut puissance
     *
     * @return La puissance de la capacité.
     */
    @Override
    public int getPuissance() {
        return this.puissance;
    }

    /**
     * Cette fonction renvoie le PP restant de la capacité.
     *
     * @return Le PP de la capacité.
     */
    @Override
    public int getPP() {
        return this.PP;
    }


    /**
     * Réinitialise le PP de la capacité à sa valeur de base.
     */
    @Override
    public void resetPP() {
        this.PP = this.PP_base;
    }


    /**
     * Cette fonction renvoie la catégorie de la capacité
     *
     * @return La catégorie du produit.
     */
    @Override
    public ICategorie getCategorie() {
        return this.categorie;
    }


    /**
     * Renvoie le type de la Capacité
     *
     * @return Le type de la Capacité.
     */
    @Override
    public IType getType() {
        return this.type;
    }

    /**
     * Il renvoie une chaîne qui contient le nom de la classe, le nom de l'objet et les valeurs de tous les attributs de
     * l'objet
     *
     * @return Le nom, la précision, la puissance, le PP de base, le PP, la catégorie et le type de mouvement.
     */
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
