package pokemon;

import java.io.Serializable;

public class StatistiquesMutables extends Statistiques implements Serializable {

    public StatistiquesMutables(int force, int defense, int vitesse, int special, double pv) {
        super(force, defense, vitesse, special, pv);
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    @Override
    public String toString() {
        return "StatistiquesMutables{" +
                "force=" + force +
                ", defense=" + defense +
                ", vitesse=" + vitesse +
                ", special=" + special +
                ", pv=" + pv +
                '}';
    }
}
