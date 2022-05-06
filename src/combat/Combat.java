package combat;

import dresseur.Dresseur;
import pokemon.Capacites;
import pokemon.Espece;
import pokemon.Pokemon;
import readingFile.ReadingFile;

import javax.management.BadAttributeValueExpException;
import java.util.*;

public abstract class Combat {
    private Random rand = new Random();
    private int nbrTours = 1;
    private Boolean end = true;
    private final Dresseur d1;
    private final Dresseur d2;
    private Pokemon p1;
    private Pokemon p2;
    private Capacites capacitep1;
    private Capacites capacitep2;

    public Combat(Dresseur d1, Dresseur d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    abstract void combatStart() throws BadAttributeValueExpException;

    public abstract Pokemon choixPokemon(Dresseur d) throws BadAttributeValueExpException;

    public abstract void tourd1(Dresseur d, Pokemon p) throws BadAttributeValueExpException;

    public abstract void tourd2(Dresseur d, Pokemon p) throws BadAttributeValueExpException;

    public abstract Capacites choixAttaque(Pokemon p) throws BadAttributeValueExpException;

    public void degats(Dresseur d2, Pokemon p1, Pokemon p2, Capacites c1) {
        if (c1.precision < 0 + 1 * this.rand.nextDouble())
            System.out.println("Attack failed !");
        else {
            double d = this.degatsInf(d2, p1, p2, c1);
            System.out.println(p1.nom + " attack with " + c1.nom + " and inflicts " + d + " damage\n");
            p2.statsSpecifiques.setPv(p2.statsSpecifiques.getPv() - d);
            if (p2.statsSpecifiques.getPv() < 0) {
                System.out.println(p2.nom + " is ko !");
                this.end = false;
            }
        }
    }

    public double degatsInf(Dresseur d, Pokemon p, Pokemon p2, Capacites c) {
        Espece e = d.pokedex.getInfo().get(p);
        double ef = this.efficacite(c, e);
        double pparenthese = p.niveau * 0.4 + 2;
        double numerateur = pparenthese * p.statsSpecifiques.getForce() * c.puissance;
        double denominateur = p2.statsSpecifiques.getDefense() * 50;
        double fraction = numerateur / denominateur + 2;
        if (c.type.equals(e.types.get(0)) || c.type.equals(e.types.get(1))) {
            double CM = ef * 1.5 * (0.85 + (1 - 0.85) * this.rand.nextDouble());
            return fraction * CM;
        }
        double CM = ef * (0.85 + (1 - 0.85) * this.rand.nextDouble());
        return fraction * CM;
    }

    public double efficacite(Capacites c, Espece e) {
        ReadingFile r = new ReadingFile();
        int cmp1 = 0;
        int cmp2 = 0;
        if (e.types.get(0) != null && e.types.get(1) != null) {
            int cmp3 = 0;
            List<String> choixType = r.chooseEfficacite(0);
            List<String> strList = strToList(choixType);
            for (int i = 0; i < strList.size() - 1; i++) {
                if (strList.get(i).equals(c.type))
                    cmp1 = i;
                else if (strList.get(i).equals(e.types.get(0)))
                    cmp2 = i;
                else if (strList.get(i).equals(e.types.get(1)))
                    cmp3 = i;
                else {
                    cmp1++;
                    cmp2++;
                    cmp3++;
                }
            }
            List<String> eff = r.chooseEfficacite(cmp1);
            List<String> Listeff = strToList(eff);
            double eff2 = Double.parseDouble(Listeff.get(cmp2));
            double eff3 = Double.parseDouble(Listeff.get(cmp3));
            return Math.max(eff2, eff3);
        } else {
            List<String> choixType = r.chooseEfficacite(0);
            List<String> strList = strToList(choixType);
            for (int i = 0; i < strList.size() - 1; i++) {
                if (strList.get(i).equals(c.type))
                    cmp1 = i;
                else if (strList.get(i).equals(e.types.get(0)))
                    cmp2 = i;
                else {
                    cmp1++;
                    cmp2++;
                }
            }
            List<String> eff = r.chooseEfficacite(cmp1);
            List<String> Listeff = strToList(eff);
            return Double.parseDouble(Listeff.get(cmp2));
        }
    }

    public List<String> strToList(List<String> choixType) {
        choixType = Collections.singletonList(choixType.get(0).replace("[", ""));
        choixType = Collections.singletonList(choixType.get(0).replace("]", ""));
        String[] strSplit = choixType.get(0).split(",");
        return new ArrayList<>(Arrays.asList(strSplit));
    }

    public int getNbrTours() {
        return nbrTours;
    }

    public void setNbrTours(int nbrTours) {
        this.nbrTours = nbrTours;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Dresseur getD1() {
        return d1;
    }

    public Dresseur getD2() {
        return d2;
    }

    public Pokemon getP1() {
        return p1;
    }

    public void setP1(Pokemon p1) {
        this.p1 = p1;
    }

    public Pokemon getP2() {
        return p2;
    }

    public void setP2(Pokemon p2) {
        this.p2 = p2;
    }

    public Capacites getCapacitep1() {
        return capacitep1;
    }

    public void setCapacitep1(Capacites capacitep1) {
        this.capacitep1 = capacitep1;
    }

    public Capacites getCapacitep2() {
        return capacitep2;
    }

    public void setCapacitep2(Capacites capacitep2) {
        this.capacitep2 = capacitep2;
    }
}
