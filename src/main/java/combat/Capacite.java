/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date ICapacite.java
 */
package combat;


import interfaces.ICapacite;
import interfaces.ICategorie;
import interfaces.IPokemon;
import interfaces.IType;
import pokedex.Pokedex;

import java.util.Random;

/**
 * @author Lacroix Baptiste
 * Une capacité est un type d'attaque que le pokemon peut utilser
 */
public class Capacite implements ICapacite {
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
     * Il calcule les dégâts d'une attaque
     *
     * @param lanceur Le pokémon qui utilise le mouvement
     * @param receveur Le pokémon qui reçoit l'attaque
     * @return Les dégâts que l'attaque fera.
     */
    @Override
    public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
        Random rand = new Random();
        if (this.precision < 0 + 1 * rand.nextDouble()) {
            return 0;
        }
        this.utilise();
        double eff = new Pokedex().getEfficacite(this.type, receveur.getEspece().getTypes()[0]);
        double eff2 = new Pokedex().getEfficacite(this.type, receveur.getEspece().getTypes()[1]);

        if (this.type == lanceur.getEspece().getTypes()[0] || this.type == lanceur.getEspece().getTypes()[1]) {
            double pparenthese = lanceur.getNiveau() * 0.4 + 2;
            double numerateur = pparenthese * lanceur.getStat().getForce() * this.puissance;
            double denominateur = receveur.getStat().getDefense() * 50;
            double fraction = numerateur / denominateur + 2;
            double CM = (eff * eff2) * 1.5 * (0.85 + (1 - 0.85) * rand.nextDouble());
            return (int) Math.round(fraction * CM);
        }

        double pparenthese = lanceur.getNiveau() * 0.4 + 2;
        double numerateur = pparenthese * lanceur.getStat().getForce() * this.puissance;
        double denominateur = receveur.getStat().getDefense() * 50;
        double fraction = numerateur / denominateur + 2;
        double CM = (eff * eff2) * (0.85 + (1 - 0.85) * rand.nextDouble());
        return (int) Math.round(fraction * CM);
    }

    /**
     * Cette fonction diminue le PP de 1.
     */
    @Override
    public void utilise() {
        this.PP --;
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