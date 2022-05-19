/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IStat.java
 */
package main.java.statsPokemon;

/**
 * @author Lacroix Baptiste
 *
 */
public class Stat implements main.interfaces.IStat {
    private int pv;
    private int force;
    private int defense;
    private int special;
    private int vitesse;

    /**
     *
     * @param pv
     * @param force
     * @param defense
     * @param special
     * @param vitesse
     */
    public Stat(int pv, int force, int defense, int special, int vitesse) {
        this.pv = pv;
        this.force = force;
        this.defense = defense;
        this.special = special;
        this.vitesse = vitesse;
    }

    /**
     *
     * @param pv
     */
    @Override
    public void setPV(int pv) {
        this.pv = pv;
    }

    /**
     *
     * @return
     */
    @Override
    public int getPV() {
        return pv;
    }

    /**
     *
     * @return
     */
    @Override
    public int getForce() {
        return force;
    }

    /**
     *
     * @param force
     */
    @Override
    public void setForce(int force) {
        this.force = force;
    }

    /**
     *
     * @return
     */
    @Override
    public int getDefense() {
        return defense;
    }

    /**
     *
     * @param defense
     */
    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     *
     * @return
     */
    @Override
    public int getSpecial() {
        return special;
    }

    /**
     *
     * @param special
     */
    @Override
    public void setSpecial(int special) {
        this.special = special;
    }

    /**
     *
     * @return
     */
    @Override
    public int getVitesse() {
        return vitesse;
    }

    /**
     * Permet de définir changer la vitesse du Pokemon
     * @param vitesse : Integer
     */
    @Override
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}
