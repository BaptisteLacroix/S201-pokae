/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IPokemon.java
 */
package main.java.pokemon;

import main.interfaces.IEspece;
import main.interfaces.IPokemon;
import main.interfaces.IStat;
import main.interfaces.IAttaque;
import main.interfaces.ICapacite;

/**
 * @author Leo Donati
 */
public class Pokemon implements IPokemon {
    private int id;
    private String nom;
    private int niveau;
    private IStat stat;
    private double experience;
    private double pourcentagePV;
    private IEspece espece;
    private ICapacite[] capacites = new ICapacite[4];

    /**
     * Constucteur du Pokemon
     * @param id
     * @param nom
     * @param niveau
     * @param stat
     * @param experience
     * @param pourcentagePV
     * @param espece
     */
    public Pokemon(int id, String nom, int niveau, IStat stat, double experience, double pourcentagePV, IEspece espece) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.stat = stat;
        this.experience = experience;
        this.pourcentagePV = pourcentagePV;
        this.espece = espece;
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
     * > Cette fonction retourne le niveau du Pokemon
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
     * > Cette fonction retourne le nom du Pokemon
     *
     * @return Le nom de la personne
     */
    @Override
    public String getNom() {
        return nom;
    }



    /**
     * Il renvoie le pourcentage de la santé du Pokemon.
     *
     * @return Le pourcentage de la santé du personnage.
     */
    @Override
    public double getPourcentagePV() {
        return pourcentagePV;
    }



    /**
     * Il renvoie l'espèce du pokemon.
     *
     * @return L'espèce de l'animal.
     */
    @Override
    public IEspece getEspece() {
        return espece;
    }


    /**
     * Une méthode qui change l'espèce du pokémon.
     */
    @Override
    public void vaMuterEn(IEspece esp) {
        this.espece = esp;
    }   //Modifie l'espèce du Pokemon en esp


    /**
     * Un getter pour l'attribut `capacites`.
     */
    @Override
    public ICapacite[] getCapacitesApprises() {
        return this.capacites;
    }    //Tableau des capacités que le Pokemon peut utiliser


    /**
     * Méthode permettant de rajouter des capacitées au Pokemon
     */
    @Override
    public void apprendCapacites(ICapacite[] caps) {
        this.capacites = caps;
    }    //Enseigne les capacités au Pokemon


    /**
     * Il remplace une capacité de la créature par une autre capacité
     *
     * @param i l'indice de capacité à remplacer
     * @param cap le nouveau ICapacite pour remplacer l'ancien
     */
    @Override
    public void remplaceCapacite(int i, ICapacite cap) throws Exception {
        if (i < 0 || i > 4)
            throw new Exception();
        this.capacites[i] = cap;
    }


    @Override
    public void gagneExperienceDe(IPokemon pok) {
        throw new UnsupportedOperationException();
    } //Met à jour l'exprérience de this suite à la défaite de pok


    @Override
    public void subitAttaqueDe(IPokemon pok, IAttaque atk) {
        throw new UnsupportedOperationException();
    } //Met à jour les stats de this en tenant compte des dégats subits par l'attaque atk de pok

    /**
     * Une méthode qui renvoie vrai si les points de vie du pokémon sont à 0.
     * @return
     */
    @Override
    public boolean estEvanoui() {
        return this.stat.getPV() == 0;
    }        //renvoie true si les pointes de vie du pokemonsont 0


    @Override
    public boolean aChangeNiveau() {
        throw new UnsupportedOperationException();
    }        //renvoie true si le Pokemon vient de changer de niveau


    @Override
    public boolean peutMuter() {
        throw new UnsupportedOperationException();
    }         //renvoie true si le Pokemon peut muter

    /**
     * Méthode utilisée pour soigner les pokémons.
     */
    @Override
    public void soigne() {
        this.stat.setPV(this.espece.getBaseStat().getPV());
    }       // Remet les PV au maximum
}
