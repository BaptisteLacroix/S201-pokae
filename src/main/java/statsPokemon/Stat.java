/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IStat.java
 */
package main.java.statsPokemon;

/**
 * @author Leo Donati
 *
 */
public class Stat implements main.interfaces.IStat {
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

    @Override
    public void setPV(int pv) {
        this.pv = pv;
    }

    @Override
    public int getPV() {
        return pv;
    }

    @Override
    public int getForce() {
        return force;
    }

    @Override
    public void setForce(int force) {
        this.force = force;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getSpecial() {
        return special;
    }

    @Override
    public void setSpecial(int special) {
        this.special = special;
    }

    @Override
    public int getVitesse() {
        return vitesse;
    }

    @Override
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
}
