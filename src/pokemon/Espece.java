package pokemon;

import readingFile.ReadingFile;

import java.io.Serializable;
import java.util.*;

public class Espece implements Serializable {
    // Attributs visibles
    public String nom;
    public List<String> types = new ArrayList<>(2);
    public Statistiques statsDeBase;
    public int niveau;
    public List<Capacites> capacitesDispo;
    public List<String> possibleEvo;

    // Attributs non visibles
    private int xpbase;
    private Map<String, Integer> gainsDeStats = new HashMap<>();

    public Espece(int idPokemon, String nom, String type1, String type2, int force, int defense, int vitesse, int special, double pv, int niveau) {
        this.nom = nom;
        this.types.add(type1);
        this.types.add(type2);
        this.statsDeBase = new Statistiques(force, defense, vitesse, special, pv);
        this.niveau = niveau;

        ReadingFile r = new ReadingFile();
        List<String> pEvo = r.nextPokemon(idPokemon);
        if (pEvo.isEmpty()) {
            this.possibleEvo = new ArrayList<>();
            this.possibleEvo.add(null);
            this.possibleEvo.add(null);
            this.possibleEvo.add(null);
        } else {
            this.possibleEvo = new ArrayList<>();
            this.possibleEvo.add(pEvo.get(0));
            this.possibleEvo.add(pEvo.get(1));
            this.possibleEvo.add(pEvo.get(2));
        }
    }

    public Espece(int idPokemon, String nom, String type1, int force, int defense, int vitesse, int special, int pv, int niveau) {
        this(idPokemon, nom, type1, null, force, defense, vitesse, special, pv, niveau);
    }

    public int getXpbase() {
        return xpbase;
    }

    public void setXpbase(int xpbase) {
        this.xpbase = xpbase;
    }

    public Map<String, Integer> getGainsDeStats() {
        return gainsDeStats;
    }

    public void setGainsDeStats(Map<String, Integer> gainsDeStats) {
        this.gainsDeStats = gainsDeStats;
    }

    public List<String> getPossibleEvo() {
        return possibleEvo;
    }

    public void setPossibleEvo(List<String> possibleEvo) {
        this.possibleEvo = possibleEvo;
    }

    @Override
    public String toString() {
        return "Espece{" +
                "nom='" + nom + '\'' +
                ", types=" + types +
                ", statsDeBase=" + statsDeBase +
                ", niveau=" + niveau +
                ", capacitesDispo=" + capacitesDispo +
                ", possibleEvo=" + possibleEvo +
                ", xpbase=" + xpbase +
                ", gainsDeStats=" + gainsDeStats +
                '}';
    }
}
