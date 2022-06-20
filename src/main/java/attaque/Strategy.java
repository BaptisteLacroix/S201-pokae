/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date Strategy.java
 */
package attaque;

import interfaces.*;
import pokemon.Pokemon;
import useLogger.MyLoggerConfiguration;

import java.util.*;
import java.util.logging.Level;


/**
 * Une stratégie est utilisée par les dresseurs non humains (IA) pour prendre les décisions
 * Un DresseurIA possède une référence sur une IStrategy à qui il délègue la prise de décision
 * Un dresseur humain n'utilise pas IStrategy
 * Chaque méthode de IStrategy correspond à la méthode homonyme de IDresseur
 *
 * @author Lacroix Baptiste and Vidal Théo
 */
public class Strategy implements IStrategy {
    private final IDresseur dresseur;
    /**
     * ranch contenant les pokémons du dresseur IA
     */
    private final IPokemon[] ranch;
    /**
     * Objet Random perméttant de générer un nombre aléatoire.
     */
    private final Random rand = new Random();
    private CoupsPossibles coupsPossiblesAttaquant = new CoupsPossibles();
    private CoupsPossibles coupsPossiblesDefenseur = new CoupsPossibles();

    /**
     * Constructeur de la Strategy
     *
     * @param ranch ranch contenant les Pokémons du dresseur IA.
     */
    public Strategy(IDresseur dresseur, IPokemon[] ranch) {
        this.dresseur = dresseur;
        this.ranch = ranch;
    }

    /**
     * La fonction `choisitCombattant()` renvoie un pokémon aléatoire du ranch qui ne s'est pas évanoui et qui combattra
     *
     * @return Un pokémon qui ne s'est pas évanoui qui combattra.
     */
    @Override
    public IPokemon choisitCombattant() {
        IPokemon pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        while (pokemon.estEvanoui()) {
            int random = this.rand.nextInt(this.ranch.length);
            pokemon = this.ranch[random];
        }
        return pokemon;
    }

    /**
     * La fonction choisit un pokémon aléatoire du ranch qui ne s'est pas évanoui et qui combattra
     *
     * @param pok le pokémon que l'adversaire utilise
     * @return Un pokémon qui ne s'est pas évanoui qui combattra.
     */
    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        IPokemon pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        while (pokemon.estEvanoui()) {
            pokemon = this.ranch[this.rand.nextInt(this.ranch.length)];
        }
        Pokemon pok2 = (Pokemon) pok;
        pok2.addCount();
        return pokemon;
    }

    /**
     * La fonction choisit une attaque aléatoire dans la liste des attaques de l'attaquant ou choisit changer de
     * Pokémon parmis la liste des Pokémon de l'entraîneur
     *
     * @param attaquant Le Pokémon qui attaque
     * @param defenseur Le Pokémon que l'IA attaque
     * @return L'attaque que l'ordinateur va utiliser.
     */
    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IDresseur dresseurDefenseur, IPokemon defenseur) {
        return MiniMax(new EtatDuJeu(this.dresseur, dresseurDefenseur),
                new Coup(0, null),
                attaquant,
                defenseur, 6,
                Double.NEGATIVE_INFINITY,
                Double.POSITIVE_INFINITY,
                true).getAttaque();
    }

    public Coup MiniMax(EtatDuJeu etatDuJeu, Coup position, IPokemon attaquant, IPokemon defenseur, int profondeur, double alpha, double beta, boolean maximizingPlayer) {
        if (etatDuJeu.pvDresseurAttaquant() == 0)
            return new Coup(0, null);
        if (etatDuJeu.pvDresseurDefenseur() == 0)
            return new Coup(1, null);
        if (profondeur == 0) {
            return position;
        }
        this.addCoupsPossiblesAttaquant(etatDuJeu, attaquant);
        this.addCoupsPossiblesDefenseur(etatDuJeu, defenseur);

        if (maximizingPlayer) {
            Coup coupAttaquant = null;
            Coup coupMax = new Coup(position.getProbabilite(), position.getAttaque());
            for (Coup coupA : this.coupsPossiblesAttaquant.getCoupsPossibles()) {
                Coup eval = MiniMax(etatDuJeu, coupA, attaquant, defenseur, profondeur - 1, alpha, beta, false);
                eval.setProbabilite((etatDuJeu.pvDresseurAttaquant() / (double) (etatDuJeu.pvDresseurAttaquant() + etatDuJeu.pvDresseurDefenseur())));

                if (coupMax.getProbabilite() >= eval.getProbabilite()) {
                    coupAttaquant = coupMax;
                } else {
                    coupAttaquant = new Coup(eval.getProbabilite(), coupA.getAttaque());
                    coupMax = coupAttaquant;
                }
                alpha = Math.max(alpha, eval.getProbabilite());
                if (beta <= alpha)
                    break;
            }
            return coupAttaquant;
        } else {
            Coup coupDefenseur = null;
            Coup coupMin = new Coup(position.getProbabilite(), position.getAttaque());
            for (Coup coupD : this.coupsPossiblesDefenseur.getCoupsPossibles()) {
                Coup eval = MiniMax(etatDuJeu, coupD, attaquant, defenseur, profondeur - 1, alpha, beta, true);
                eval.setProbabilite((etatDuJeu.pvDresseurAttaquant() / (double) (etatDuJeu.pvDresseurAttaquant() + etatDuJeu.pvDresseurDefenseur())));

                if (coupMin.getProbabilite() <= eval.getProbabilite()) {
                    coupDefenseur = coupMin;
                } else {
                    coupDefenseur = new Coup(eval.getProbabilite(), coupD.getAttaque());
                    coupMin = coupDefenseur;
                }
                beta = Math.max(beta, eval.getProbabilite());
            }
            return coupDefenseur;
        }
    }

    private void addCoupsPossiblesAttaquant(EtatDuJeu X, IPokemon pokemonAttaquant) {
        this.coupsPossiblesAttaquant = new CoupsPossibles();
        for (ICapacite cap : pokemonAttaquant.getCapacitesApprises())
            coupsPossiblesAttaquant.addCoup(pokemonAttaquant, new Coup(0, cap));
        for (IPokemon pok : X.getPokemonsAttaquant()) {
            coupsPossiblesAttaquant.addCoup(pokemonAttaquant, new Coup(0, new Echange(pok)));
        }
    }

    private void addCoupsPossiblesDefenseur(EtatDuJeu X, IPokemon pokemonDefenseur) {
        this.coupsPossiblesDefenseur = new CoupsPossibles();
        for (ICapacite cap : pokemonDefenseur.getCapacitesApprises())
            coupsPossiblesDefenseur.addCoup(pokemonDefenseur, new Coup(0, cap));
        for (IPokemon pok : X.getPokemonsDefenseur()) {
            coupsPossiblesDefenseur.addCoup(pokemonDefenseur, new Coup(0, new Echange(pok)));
        }
    }
}

class Coup {
    private double probabilite;
    private IAttaque attaque;

    public Coup(double probabilite, IAttaque attaque) {
        this.probabilite = probabilite;
        this.attaque = attaque;
    }

    public double getProbabilite() {
        return probabilite;
    }

    public IAttaque getAttaque() {
        return attaque;
    }

    public void setAttaque(IAttaque attaque) {
        this.attaque = attaque;
    }

    public void setProbabilite(double probabilite) {
        this.probabilite = probabilite;
    }
}

class CoupsPossibles {
    private final List<Coup> coups = new ArrayList<>();
    private IPokemon pokemon;

    public List<Coup> getCoupsPossibles() {
        return coups;
    }

    public void addCoup(IPokemon pokemon, Coup coup) {
        this.pokemon = pokemon;
        this.coups.add(coup);
    }

    public IPokemon getPokemon() {
        return pokemon;
    }
}

class EtatDuJeu {
    private IDresseur dresseurAttaquant;
    private IDresseur dresseurDefenseur;

    public EtatDuJeu(IDresseur dresseurAttaquant, IDresseur dresseurDefenseur) {
        this.dresseurAttaquant = dresseurAttaquant;
        this.dresseurDefenseur = dresseurDefenseur;
    }

    public IDresseur getDresseurAttaquant() {
        return this.dresseurAttaquant;
    }

    public IDresseur getDresseurDefenseur() {
        return dresseurDefenseur;
    }

    public int pvDresseurAttaquant() {
        int total = 0;
        for (IPokemon p : this.dresseurAttaquant.getRanchCopy()) {
            total += p.getStat().getPV();
        }
        return total;
    }

    public int pvDresseurDefenseur() {
        int total = 0;
        for (IPokemon p : this.dresseurDefenseur.getRanchCopy()) {
            total += p.getStat().getPV();
        }
        return total;
    }

    public IPokemon[] getPokemonsAttaquant() {
        return this.dresseurAttaquant.getRanchCopy();
    }

    public IPokemon[] getPokemonsDefenseur() {
        return this.dresseurDefenseur.getRanchCopy();
    }
}