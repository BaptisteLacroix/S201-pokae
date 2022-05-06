package pokemon;

import java.io.Serializable;

public class Capacites implements Serializable {
    public String nom;
    public String type;
    public String categorie;
    public int puissance;
    public double precision;
    public int PP;

    public Capacites(String nom, String type, String categorie, int puissance, double precision, int PP) {
        this.nom = nom;
        this.type = type;
        this.categorie = categorie;
        this.puissance = puissance;
        this.precision = precision;
        this.PP = PP;
    }

    @Override
    public String toString() {
        return "Capacites{" +
                "nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", categorie='" + categorie + '\'' +
                ", puissance=" + puissance +
                ", precision=" + precision +
                ", PP=" + PP +
                '}';
    }
}
