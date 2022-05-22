/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IPokemon.java
 */
package pokemon;

import interfaces.IEspece;
import interfaces.IPokemon;
import interfaces.IStat;
import interfaces.IAttaque;
import interfaces.ICapacite;
import statsPokemon.Stat;

import java.util.Random;

/**
 * @author Lacroix Baptiste
 */
public class Pokemon implements IPokemon {
    private int id;
    private String nom;
    private int ancien_niveau;
    private int niveau;
    private IStat stat;
    private double experience;
    private double pourcentagePV;
    private IEspece espece;
    private ICapacite[] capacites = new ICapacite[4];
    private IStat DV;


    public Pokemon(int id, String nom, int niveau, IStat stat, double experience, double pourcentagePV, IEspece espece) {
        this.id = id;
        this.nom = nom;
        this.ancien_niveau = niveau;
        this.niveau = niveau;
        this.stat = stat;
        this.experience = experience;
        this.pourcentagePV = pourcentagePV;
        this.espece = espece;
        this.setDV();
    }

    /**
     * Création du DV du Pokémon lors de sa création
     */
    private void setDV() {
        Random rand = new Random();
        int[] lowWeight = new int[4];
        int force, defense, vitesse, special;
        force = rand.nextInt(16);
        defense = rand.nextInt(16);
        vitesse = rand.nextInt(16);
        special = rand.nextInt(16);
        lowWeight[0] = this.decimalToBinary(force)[3];
        lowWeight[1] = this.decimalToBinary(defense)[3];
        lowWeight[2] = this.decimalToBinary(vitesse)[3];
        lowWeight[3] = this.decimalToBinary(special)[3];

        this.DV = new Stat(this.binaryToDecimal(lowWeight), force, defense, special, vitesse);

        while (peutChangerDeNiveau()) {
            this.niveau++;
            this.miseAJourStats();
        }
    }


    /**
     * Il convertit un nombre décimal en binaire.
     *
     * @param num Le nombre à convertir en binaire
     * @return La représentation binaire du nombre.
     */
    private int[] decimalToBinary(int num) {
        int[] binary = new int[4];
        int i = 0;

        while (num > 0) {
            binary[i++] = num % 2;
            num = num / 2;
        }

        return binary;
    }

    /**
     * Il prend un tableau de nombres binaires et renvoie l'équivalent décimal
     *
     * @param binary le nombre binaire à convertir
     * @return La valeur décimale du nombre binaire.
     */
    private int binaryToDecimal(int[] binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length - 1; i++) {
            if (binary[i] == 1) {
                decimal += Math.pow(2, i);
            }
        }
        return decimal;
    }

    /**
     * Cette fonction renvoie les statistiques de l'objet courant.
     *
     * @return L'objet statistique.
     */
    @Override
    public IStat getStat() {
        return stat;
    }


    /**
     * Cette fonction renvoie l'expérience du joueur.
     *
     * @return La variable d'expérience est renvoyée.
     */
    @Override
    public double getExperience() {
        return experience;
    }


    /**
     * Cette fonction retourne le niveau du Pokemon
     *
     * @return Le niveau du Pokemon
     */
    @Override
    public int getNiveau() {
        return niveau;
    }


    /**
     * Cette fonction renvoie l'identifiant du Pokemon.
     *
     * @return L'identifiant de l'objet.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Cette fonction retourne le nom du Pokemon
     *
     * @return Le nom du Pokemon
     */
    @Override
    public String getNom() {
        return nom;
    }


    /**
     * Il renvoie le pourcentage de la santé du Pokemon.
     *
     * @return Le pourcentage de la santé du Pokemon.
     */
    @Override
    public double getPourcentagePV() {
        return pourcentagePV;
    }


    /**
     * Il renvoie l'espèce du pokemon.
     *
     * @return L'espèce du pokemon.
     */
    @Override
    public IEspece getEspece() {
        return espece;
    }


    /**
     * Une méthode qui change l'espèce du pokémon.
     *
     * @param esp Nouvelle espèce du Pokemon
     */
    @Override
    public void vaMuterEn(IEspece esp) {
        this.espece = esp;
        this.niveau = this.espece.getNiveauDepart();
        this.experience = this.espece.getBaseExp();
        this.pourcentagePV = 100.0;
        this.stat = new Stat(this.espece.getBaseStat().getPV(), this.espece.getBaseStat().getForce(),
                this.espece.getBaseStat().getDefense(), this.espece.getBaseStat().getSpecial(),
                this.espece.getBaseStat().getVitesse());
    }   //Modifie l'espèce du Pokemon en esp


    /**
     * Un getter pour l'attribut `capacites`.
     */
    @Override
    public ICapacite[] getCapacitesApprises() {
        return this.capacites;
    }    //Tableau des capacités que le Pokemon peut utiliser


    /**
     * Méthode permettant de rajouter des capacités au Pokemon
     *
     * @param caps Capacités à ajouter au Pokemon
     */
    @Override
    public void apprendCapacites(ICapacite[] caps) {
        this.capacites = caps;
    }    //Enseigne les capacités au Pokemon


    /**
     * Il remplace une capacité de la créature par une autre capacité
     *
     * @param i   l'indice de capacité à remplacer
     * @param cap le nouveau ICapacite pour remplacer l'ancien
     * @throws UnsupportedOperationException Si l'indice n'est pas contenu dans le tableau
     */
    @Override
    public void remplaceCapacite(int i, ICapacite cap) throws UnsupportedOperationException {
        if (i < 0 || i > 4)
            throw new UnsupportedOperationException();
        this.capacites[i] = cap;
    }


    /**
     * Mise à jour de l'expérience du pokémon après avoir vaincu un autre pokémon.
     *
     * @param pok le pokemon vaincu.
     */
    @Override
    public void gagneExperienceDe(IPokemon pok) {
        this.experience = (1.5 * pok.getNiveau() * pok.getEspece().getBaseExp()) / 7;
        while (peutChangerDeNiveau()) {
            this.niveau ++;
            this.miseAJourStats();
        }
    } //Met à jour l'exprérience de this suite à la défaite de pok

    private boolean peutChangerDeNiveau() {
        return this.experience >= (0.8 * Math.pow(this.niveau + 1, 3));
    }

    /**
     * Une méthode qui met à jour les stats du Pokémon après les dégâts subis par l'attaque atk de pok.
     *
     * @param pok pokemon attaquant
     * @param atk l'attaque subbies
     */
    @Override
    public void subitAttaqueDe(IPokemon pok, IAttaque atk) {
        if (atk instanceof ICapacite) {
            int degats = atk.calculeDommage(pok, this);
            this.stat.setPV(this.stat.getPV() - degats);
        }
    } //Met à jour les stats de this en tenant compte des dégats subits par l'attaque atk de pok

    /**
     * Une méthode qui renvoie vrai si les points de vie du pokémon sont à 0.
     *
     * @return Un boolean.
     */
    @Override
    public boolean estEvanoui() {
        return this.stat.getPV() == 0;
    }        //renvoie true si les pointes de vie du pokemon sont 0


    /**
     * Vérifier si le pokémon a changé de niveau.
     *
     * @return Un boolean.
     */
    @Override
    public boolean aChangeNiveau() {
        return this.niveau != this.ancien_niveau;
    }        //renvoie true si le Pokemon vient de changer de niveau


    /**
     * Cette fonction met à jour les stats du Pokemon après son gain de Niveau.
     */
    private void miseAJourStats() {
        int gainpv = (((2 * (this.espece.getBaseStat().getPV()) + this.espece.getGainsStat().getPV() / 4) *
                this.niveau) / 100) + this.niveau + 10;
        int gainforce = (((this.espece.getBaseStat().getForce() + this.DV.getForce()) +
                this.espece.getGainsStat().getForce() / 4) * this.niveau) / 100 + 5;
        int gaindefense = (((this.espece.getBaseStat().getDefense() + this.DV.getDefense()) +
                this.espece.getGainsStat().getDefense() / 4) * this.niveau) / 100 + 5;
        int gainvitesse = (((this.espece.getBaseStat().getVitesse() + this.DV.getVitesse()) +
                this.espece.getGainsStat().getVitesse() / 4) * this.niveau) / 100 + 5;
        int gainspecial = (((this.espece.getBaseStat().getSpecial() + this.DV.getSpecial()) +
                this.espece.getGainsStat().getSpecial() / 4) * this.niveau) / 100 + 5;
        this.stat = new Stat(this.getStat().getPV() + gainpv, this.getStat().getForce() + gainforce,
                this.getStat().getDefense() + gaindefense, this.getStat().getSpecial() + gainspecial,
                this.getStat().getVitesse() + gainvitesse);
    }

    /**
     * Vérifier si le pokémon peut muter.
     *
     * @return Un boolean.
     */
    @Override
    public boolean peutMuter() {
        IEspece especeM = this.espece.getEvolution(this.niveau);
        if (especeM == null)
            return false;
        else {
            return especeM.getNiveauDepart() >= this.niveau;
        }
    }         //renvoie true si le Pokemon peut muter

    /**
     * Méthode utilisée pour soigner les pokémons.
     */
    @Override
    public void soigne() {
        this.stat.setPV((((2 * (this.espece.getBaseStat().getPV()) + this.espece.getGainsStat().getPV() / 4) *
                this.niveau) / 100) + this.niveau + 10);
    }       // Remet les PV au maximum
}