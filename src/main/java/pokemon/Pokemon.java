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

    public Pokemon(int id, String nom, int niveau, IStat stat, double experience, double pourcentagePV, IEspece espece) {
        this.id = id;
        this.nom = nom;
        this.niveau = niveau;
        this.stat = stat;
        this.experience = experience;
        this.pourcentagePV = pourcentagePV;
        this.espece = espece;
    }

    @Override
    public IStat getStat() {
        return stat;
    }

    @Override
    public double getExperience() {
        return experience;
    }

    @Override
    public int getNiveau() {
        return niveau;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public double getPourcentagePV() {
        return pourcentagePV;
    }

    @Override
    public IEspece getEspece() {
        return espece;
    }

    @Override
    public void vaMuterEn(IEspece esp) {
        throw new UnsupportedOperationException();
    }   //Modifie l'espèce du Pokemon en esp

    @Override
    public ICapacite[] getCapacitesApprises() {
        throw new UnsupportedOperationException();
    }    //Tableau des capacités que le Pokemon peut utiliser

    @Override
    public void apprendCapacites(ICapacite[] caps) {
        throw new UnsupportedOperationException();
    }    //Enseigne les capacités au Pokemon

    @Override
    public void remplaceCapacite(int i, ICapacite cap) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public void gagneExperienceDe(IPokemon pok) {
        throw new UnsupportedOperationException();
    } //Met à jour l'exprérience de this suite à la défaite de pok

    @Override
    public void subitAttaqueDe(IPokemon pok, IAttaque atk) {
        throw new UnsupportedOperationException();
    } //Met à jour les stats de this en tenant compte des dégats subits par l'attaque atk de pok


    @Override
    public boolean estEvanoui() {
        throw new UnsupportedOperationException();
    }        //renvoie true si les pointes de vie du pokemonsont 0

    @Override
    public boolean aChangeNiveau() {
        throw new UnsupportedOperationException();
    }        //renvoie true si le Pokemon vient de changer de niveau

    @Override
    public boolean peutMuter() {
        throw new UnsupportedOperationException();
    }            //renvoie true si le Pokemon peut muter

    @Override
    public void soigne() {
        throw new UnsupportedOperationException();
    }                //Remet les PV au maximum
}
