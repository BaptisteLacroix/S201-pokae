package pokemon;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Pokemon implements Serializable {
    // Attributs visibles
    public int id = 0;
    public String nom;
    public int niveau;
    public int pointXP = 0;
    public List<Capacites> capacites;
    public StatistiquesMutables statsSpecifiques;

    // Attributs non visibles
    private StatistiquesMutables effortValues;
    private Statistiques determinantValues;

    public Pokemon(String nom, int niveau, List<Capacites> capacites, int force, int defense, int vitesse, int special, double pv) {
        this.id += 1;
        this.nom = nom;
        this.niveau = niveau;

        this.capacites = new ArrayList<>(capacites);
        this.statsSpecifiques = new StatistiquesMutables(force, defense, vitesse, special, pv);
        this.effortValues = new StatistiquesMutables(0, 0, 0, 0, 0);
        this.determinantValues = new Statistiques(ThreadLocalRandom.current().nextInt(0, 16),
                ThreadLocalRandom.current().nextInt(0, 16),
                ThreadLocalRandom.current().nextInt(0, 16),
                ThreadLocalRandom.current().nextInt(0, 16),
                ThreadLocalRandom.current().nextInt(0, 16));
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", niveau=" + niveau +
                ", pointXP=" + pointXP +
                ", capacites=" + capacites +
                ", statsSpecifiques=" + statsSpecifiques +
                ", effortValues=" + effortValues +
                ", determinantValues=" + determinantValues +
                '}';
    }
}
