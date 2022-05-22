/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IStat.java
 */
package statsPokemon;

import interfaces.IStat;

/**
 * @author Lacroix Baptiste
 *
 */
public class Stat implements IStat {
    private int pv;
    private int force;
    private int defense;
    private int special;
    private int vitesse;

    public Stat(int pv, int force, int defense, int special, int vitesse) {
        this.pv = pv;
        this.force = force;
        this.defense = defense;
        this.special = special;
        this.vitesse = vitesse;
    }


    /**
     * Cette fonction définit le pv de l'objet sur le pv passé en paramètre.
     *
     * @param pv La santé actuelle du Pokemon.
     */
    @Override
    public void setPV(int pv) {
        this.pv = pv;
    }



    /**
     * Cette fonction renvoie la valeur de la variable privée pv.
     *
     * @return La variable pv est renvoyée.
     */
    @Override
    public int getPV() {
        return this.pv;
    }


    /**
     * Cette fonction renvoie la force du Pokemon.
     *
     * @return La force du Pokemon.
     */
    @Override
    public int getForce() {
        return this.force;
    }


    /**
     * Cette fonction définit la force de l'objet sur la valeur du paramètre force.
     *
     * @param force La force du Pokemon.
     */
    @Override
    public void setForce(int force) {
        this.force = force;
    }


    /**
     * Cette fonction renvoie la défense du Pokemon
     *
     * @return La défense du Pokemon.
     */
    @Override
    public int getDefense() {
        return defense;
    }


    /**
     * Cette fonction fixe la défense du Pokemon à la valeur du paramètre défense.
     *
     * @param defense La quantité de défense dont dispose le Pokemon.
     */
    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }


    /**
     * Cette fonction renvoie la valeur de la variable spéciale
     *
     * @return La variable spéciale est renvoyée.
     */
    @Override
    public int getSpecial() {
        return special;
    }


    /**
     * Cette fonction définit la variable spéciale sur la valeur du paramètre spécial.
     *
     * @param special La valeur spéciale du Pokemon.
     */
    @Override
    public void setSpecial(int special) {
        this.special = special;
    }


    /**
     * Cette fonction renvoie la vitesse d'attaque du Pokemon
     *
     * @return La vitesse d'attaque du Pokemon.
     */
    @Override
    public int getVitesse() {
        return vitesse;
    }


    /**
     * Cette fonction fixe la vitesse d'attaque du Pokemon à la valeur du paramètre vitesse.
     *
     * @param vitesse la vitesse d'attaque du Pokemon
     */
    @Override
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "pv=" + pv +
                ", force=" + force +
                ", defense=" + defense +
                ", special=" + special +
                ", vitesse=" + vitesse +
                '}';
    }
}