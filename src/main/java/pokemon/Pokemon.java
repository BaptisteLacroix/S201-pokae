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
     *
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
     * A getter for the stat attribute.
     * @return
     */
    @Override
    public IStat getStat() {
        return stat;
    }

    /**
     * A getter for the experience attribute.
     * @return
     */
    @Override
    public double getExperience() {
        return experience;
    }

    /**
     * A getter for the niveau attribute.
     * @return
     */
    @Override
    public int getNiveau() {
        return niveau;
    }

    /**
     * This function returns the id of the object.
     *
     * @return The id of the object.
     */
    @Override
    public int getId() {
        return id;
    }


    /**
     * > This function returns the name of the object
     *
     * @return The name of the person
     */
    @Override
    public String getNom() {
        return nom;
    }


    /**
     * It returns the percentage of the health of the player.
     *
     * @return The percentage of the health of the character.
     */
    @Override
    public double getPourcentagePV() {
        return pourcentagePV;
    }


    /**
     * It returns the species of the animal.
     *
     * @return The espece of the animal.
     */
    @Override
    public IEspece getEspece() {
        return espece;
    }


    /**
     * This function is not supported.
     *
     * @param esp The species to mutate into.
     */
    @Override
    public void vaMuterEn(IEspece esp) {
        throw new UnsupportedOperationException();
    }   //Modifie l'espèce du Pokemon en esp

    /**
     *
     * @return
     */
    @Override
    public ICapacite[] getCapacitesApprises() {
        return this.capacites;
    }    //Tableau des capacités que le Pokemon peut utiliser

    /**
     *
     * @param caps
     */
    @Override
    public void apprendCapacites(ICapacite[] caps) {
        this.capacites = caps;
    }    //Enseigne les capacités au Pokemon

    /**
     *
     * @param i
     * @param cap
     * @throws Exception
     */
    @Override
    public void remplaceCapacite(int i, ICapacite cap) throws Exception {
        if (i < 0 || i > 4)
            throw new Exception();
        this.capacites[i] = cap;
    }

    /**
     *
     * @param pok
     */
    @Override
    public void gagneExperienceDe(IPokemon pok) {
        throw new UnsupportedOperationException();
    } //Met à jour l'exprérience de this suite à la défaite de pok

    /**
     *
     * @param pok
     * @param atk
     */
    @Override
    public void subitAttaqueDe(IPokemon pok, IAttaque atk) {
        throw new UnsupportedOperationException();
    } //Met à jour les stats de this en tenant compte des dégats subits par l'attaque atk de pok

    /**
     *
     * @return
     */
    @Override
    public boolean estEvanoui() {
        return this.stat.getPV() == 0;
    }        //renvoie true si les pointes de vie du pokemonsont 0

    /**
     *
     * @return
     */
    @Override
    public boolean aChangeNiveau() {
        throw new UnsupportedOperationException();
    }        //renvoie true si le Pokemon vient de changer de niveau

    /**
     *
     * @return
     */
    @Override
    public boolean peutMuter() {
        throw new UnsupportedOperationException();
    }         //renvoie true si le Pokemon peut muter

    /**
     *
     */
    @Override
    public void soigne() {
        this.stat.setPV(this.espece.getBaseStat().getPV());
    }       // Remet les PV au maximum
}
