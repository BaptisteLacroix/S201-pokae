package pokemon;

import java.io.Serializable;

public class Statistiques implements Serializable {
    protected int force;
    protected int defense;
    protected int vitesse;
    protected int special;
    protected double pv;

    public Statistiques(int force, int defense, int vitesse, int special, double pv) {
        this.force = force;
        this.defense = defense;
        this.vitesse = vitesse;
        this.special = special;
        this.pv = pv;
    }

    public int getForce() {
        return force;
    }

    public int getDefense() {
        return defense;
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getSpecial() {
        return special;
    }

    public double getPv() {
        return pv;
    }

    @Override
    public String toString() {
        return "Statistiques{" +
                "force=" + force +
                ", defense=" + defense +
                ", vitesse=" + vitesse +
                ", special=" + special +
                ", pv=" + pv +
                '}';
    }
}
