/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date Strategy.java
 */
package attaque;

import combat.Tour;
import dresseur.DresseurIA;
import interfaces.*;
import pokedex.Pokedex;
import pokemon.Pokemon;

import java.util.*;


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
        /*
        int choixAttaque = rand.nextInt(2 + 1) + 1;  // Read user input
        if (choixAttaque == 1) {
            ICapacite random = attaquant.getCapacitesApprises()[this.rand.nextInt(4)];
            while (random == null) {
                random = attaquant.getCapacitesApprises()[this.rand.nextInt(4)];
            }
            return random;
        } else {
            IEchange echange = new Echange(attaquant);
            echange.setPokemon(this.choisitCombattantContre(defenseur));
            return echange; // Change de Pokemon
        }
        */
        System.out.println("MiniMax");
        IAttaque attaque = P(new EtatDuJeu(this.dresseur, dresseurDefenseur), attaquant, defenseur, 7, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY).getAttaque();
        System.out.println("attaque : " + attaque);
        return attaque;
    }
    /*
        public static void main(String[] args) {
        IPokedex pokedex = new Pokedex();
        DresseurIA IA1 = new DresseurIA("IA1", pokedex);
        DresseurIA IA2 = new DresseurIA("IA2", pokedex);
        choixIA(IA1);
        choixIA(IA2);
        System.out.println(MiniMax(new EtatDuJeu(IA1, IA2), IA1.getPokemon(0), IA2.getPokemon(0), 7, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
    }
     */

    /**
     * @param X
     * @param pokemonAttaquant
     * @param pokemonDefenseur
     * @param profondeur
     * @param alpha
     * @param beta
     * @return
     */
    private Coup P(EtatDuJeu X, IPokemon pokemonAttaquant, IPokemon pokemonDefenseur, int profondeur, double alpha, double beta) {
        if (profondeur == 0 || X.pvDresseurAttaquant() == 0 || X.pvDresseurDefenseur() == 0) {
            return new Coup(X.pvDresseurAttaquant() / (double) (X.pvDresseurAttaquant() + X.pvDresseurDefenseur()), null);
        }

        this.addCoupsPossiblesAttaquant(X, pokemonAttaquant);
        this.addCoupsPossiblesDefenseur(X, pokemonDefenseur);

        Coup coupAttaquant = new Coup(0, null);
        for (Coup coupA : this.coupsPossiblesAttaquant.getCoupsPossibles()) {
            Coup coupDefenseur = new Coup(1, null);
            for (Coup coupD : this.coupsPossiblesDefenseur.getCoupsPossibles()) {
                IPokemon truePokemonAttaquant;
                IPokemon truePokemonDefenseur;

                IAttaque attaqueAttaquant;
                IAttaque attaqueDefenseur;

                truePokemonAttaquant = this.getPokemonFromRanch(X.getDresseurAttaquant().getRanchCopy(), this.coupsPossiblesAttaquant.getPokemon().getNom());
                truePokemonDefenseur = this.getPokemonFromRanch(X.getDresseurDefenseur().getRanchCopy(), this.coupsPossiblesDefenseur.getPokemon().getNom());

                // Si Echange des deux Dresseurs
                if (coupD.getAttaque().getClass() == Echange.class && coupA.getAttaque().getClass() == Echange.class) {
                    attaqueAttaquant = new Echange(pokemonAttaquant);
                    ((Echange) attaqueAttaquant).setPokemon(truePokemonAttaquant);

                    attaqueDefenseur = new Echange(pokemonDefenseur);
                    ((Echange) attaqueDefenseur).setPokemon(truePokemonDefenseur);
                } else {
                    // Si Echange du dresseur Attaquant
                    if (coupA.getAttaque().getClass() == Echange.class) {
                        attaqueAttaquant = new Echange(pokemonAttaquant);
                        ((Echange) attaqueAttaquant).setPokemon(truePokemonAttaquant);
                    } else {
                        attaqueAttaquant = coupA.getAttaque();
                    }

                    // Si Echange des du dresseur Defenseur
                    if (coupD.getAttaque().getClass() == Echange.class) {
                        attaqueDefenseur = new Echange(pokemonDefenseur);
                        ((Echange) attaqueDefenseur).setPokemon(truePokemonDefenseur);
                    } else {
                        attaqueDefenseur = coupD.getAttaque();
                    }
                }

                // Simulation de l'attaque
                Simulation simulation = new Simulation(truePokemonAttaquant, attaqueAttaquant, truePokemonDefenseur, attaqueDefenseur);
                simulation.commence();

                Coup coupC = P(X, truePokemonAttaquant, truePokemonDefenseur, profondeur - 1, alpha, beta);
                coupC.setAttaque(attaqueAttaquant);

                coupAttaquant = coupMax(coupMin(coupDefenseur, coupC), coupAttaquant);

                // Elagage Alpha-Beta
                if (coupAttaquant.getProbabilite() > alpha) {
                    alpha = coupAttaquant.getProbabilite();
                }

                if (beta <= alpha) {
                    break;
                }
            }
        }
        System.out.println("coup du dresseur Attaquant : " + coupAttaquant.getAttaque());
        return coupAttaquant;
    }

    /**
     * Algorithme Minimax minimiser les pertes de l'attaquant
     * @param coupDefenseur
     * @param coupC
     * @return
     */
    private Coup coupMin(Coup coupDefenseur, Coup coupC) {
        if (coupC.getProbabilite() < coupDefenseur.getProbabilite()) {
            coupDefenseur.setProbabilite(coupC.getProbabilite());
            coupDefenseur.setAttaque(coupC.getAttaque());
        }
        return coupDefenseur;
    }

    /**
     * Algorithme Minimax maximisant les gains de l'attaquant
     * @param coupDefenseur
     * @param coupAttaquant
     * @return
     */
    private Coup coupMax(Coup coupDefenseur, Coup coupAttaquant) {
        if (coupAttaquant.getProbabilite() > coupDefenseur.getProbabilite()) {
            coupDefenseur.setProbabilite(coupAttaquant.getProbabilite());
            coupDefenseur.setAttaque(coupAttaquant.getAttaque());
        }
        return coupDefenseur;
    }

    private IPokemon getPokemonFromRanch(IPokemon[] ranch, String nom) {
        IPokemon pokemon = null;
        for (IPokemon pok : ranch) {
            if (pok.getNom().equals(nom)) {
                pokemon = pok;
            }
        }
        return pokemon;
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

class Simulation {
    private IPokemon pokemon1;
    private IAttaque attaque1;
    private IPokemon pokemon2;
    private IAttaque attaque2;

    public Simulation(IPokemon pokemon1, IAttaque attaque1, IPokemon pokemon2, IAttaque attaque2) {
        this.pokemon1 = pokemon1;
        this.attaque1 = attaque1;
        this.pokemon2 = pokemon2;
        this.attaque2 = attaque2;
    }

    public void commence() {
        if (attaque1.getClass() == Echange.class && attaque2.getClass() != Echange.class) {
            this.pokemon1.subitAttaqueDe(pokemon2, attaque2);
        } else if (attaque1.getClass() != Echange.class && attaque2.getClass() == Echange.class) {
            this.pokemon2.subitAttaqueDe(pokemon1, attaque1);
        } else if (attaque1.getClass() == Echange.class) {
            // Rien faire
        } else {
            // Si vitesses P1 > P2 alors P1 commence sinon P2
            if (this.pokemon1.getStat().getVitesse() > this.pokemon2.getStat().getVitesse()) {
                this.pokemon2.subitAttaqueDe(pokemon1, attaque1);
                if (!this.pokemon2.estEvanoui())
                    this.pokemon1.subitAttaqueDe(pokemon2, attaque2);
            } else {
                this.pokemon1.subitAttaqueDe(pokemon2, attaque2);
                if (!this.pokemon1.estEvanoui())
                    this.pokemon2.subitAttaqueDe(pokemon1, attaque1);
            }
        }
    }        //Lance un tour de combat
}
