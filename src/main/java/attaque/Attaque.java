/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IAttaque.java
 */
package attaque;

import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IPokemon;
import pokedex.Pokedex;
import statsPokemon.Type;

import java.util.Random;

/**
 * @author Lacroix Baptiste and Vidal Théo
 * Une attaque est une action du Pokemon durant une bataille.
 * Il y a deux types d'attaques :
 * - les capacités (interface ICapacity)
 * - les échanges (interface IEchange)
 */
public class Attaque implements IAttaque {

    private ICapacite capacite;
    private Random rand = new Random();

    public Attaque(ICapacite capacite) {
        this.capacite = capacite;
    }

    /**
     * Il calcule les dégâts d'une attaque
     *
     * @param lanceur  Le pokémon qui utilise le mouvement
     * @param receveur Le pokémon qui reçoit l'attaque
     * @return Le nombre de points de vie qu'il faut enlever au receveur.
     */
    @Override
    public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
        if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
            this.utilise();
            return 0;
        }

        int damage = this.caseAttack(lanceur, receveur);

        if (damage == -1) {
            this.utilise();
            double eff = new Pokedex().getEfficacite(this.capacite.getType(), receveur.getEspece().getTypes()[0]);
            double eff2 = new Pokedex().getEfficacite(this.capacite.getType(), receveur.getEspece().getTypes()[1]);
            double pparenthese = lanceur.getNiveau() * 0.4 + 2;
            double numerateur = pparenthese * lanceur.getStat().getForce() * this.capacite.getPuissance();
            double denominateur = receveur.getStat().getDefense() * 50;
            double fraction = numerateur / denominateur + 2;

            if (this.capacite.getType() == lanceur.getEspece().getTypes()[0] || this.capacite.getType() == lanceur.getEspece().getTypes()[1]) {
                double CM = (eff * eff2) * 1.5 * (0.85 + (1 - 0.85) * rand.nextDouble());
                return (int) Math.round(fraction * CM);
            }

            double CM = (eff * eff2) * (0.85 + (1 - 0.85) * rand.nextDouble());
            return (int) Math.round(fraction * CM);
        } else return damage;
    }

    /**
     * Il calcule les dommages causés par un mouvement
     *
     * @param lanceur le pokémon qui utilise l'attaque
     * @param receveur le pokémon attaqué
     * @return Les dégâts que l'attaque fera.
     */
    private int caseAttack(IPokemon lanceur, IPokemon receveur) {
        switch (this.capacite.getPuissance()) {
            case -1:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                return receveur.getStat().getPV();
            case -2:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                if (receveur.getEspece().getTypes()[0] == Type.Spectre || receveur.getEspece().getTypes()[1] == Type.Spectre) {
                    this.utilise();
                    return 0;
                }
                this.utilise();
                return 20;
            case -4:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                this.utilise();
                return lanceur.getNiveau();
            case -5:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                return 40;
            case -6:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                if (receveur.getEspece().getTypes()[0] == Type.Normal || receveur.getEspece().getTypes()[1] == Type.Normal) {
                    this.utilise();
                    return 0;
                }
                this.utilise();
                return lanceur.getNiveau();
            case -8:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                this.utilise();
                return lanceur.getNiveau() * ((rand.nextInt(6 - 1 + 1) + 1) + 5) / 10;
            case -9:
                if (this.capacite.getPrecision() < 0 + 1 * rand.nextDouble()) {
                    this.utilise();
                    return 0;
                }
                this.utilise();
                if (receveur.getStat().getPV() == 1)
                    return 1;
                return receveur.getStat().getPV() / 2;
            default:
                return -1;
        }
    }

    /**
     * fait diminuer de 1 le nombre restant de fois où l'attaque peut être utilisée
     */
    @Override
    public void utilise() {
        this.capacite.utilise();
    }
}
