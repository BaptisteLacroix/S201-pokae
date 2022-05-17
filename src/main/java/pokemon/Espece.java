/**
 * Université Côte d'Azur
 * IUT Côte d'Azur
 * Département Informatique
 *
 * @date IEspece.java
 */
package main.java.pokemon;

import main.interfaces.ICapacite;
import main.interfaces.IEspece;
import main.interfaces.IType;
import main.interfaces.IStat;

/**
 * @author Leo Donati
 *
 */
public class Espece implements IEspece {
    private IStat baseStat;
    private String nom;
    private int niveauDepart;
    private int baseExp;
    private IStat gainsStat;
    private ICapacite[] capSet;
    private IStat evolution;
    private IType[] types;

    public Espece(IStat baseIStat, String nom, int niveauDepart, int baseExp, IStat gainsStat,
                  ICapacite[] capSet, IStat evolution, IType[] types) {
        this.baseStat = baseIStat;
        this.nom = nom;
        this.niveauDepart = niveauDepart;
        this.baseExp = baseExp;
        this.gainsStat = gainsStat;
        this.capSet = capSet;
        this.evolution = evolution;
        this.types = types;
    }



    @Override
    public IStat getBaseStat() {
        return baseStat;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public int getNiveauDepart() {
        return niveauDepart;
    }

    @Override
    public int getBaseExp() {
        return baseExp;
    }

    @Override
    public IStat getGainsStat() {
        return gainsStat;
    }

    @Override
    public ICapacite[] getCapSet() {
        return capSet;
    } //ensemble des capacités disponibles pour cette espèce

    @Override
    public IType[] getTypes() {
        return types;
    } //une espece de pokemon peut avoir un ou deux types

    @Override
    public IEspece getEvolution(int niveau) {
        throw new UnsupportedOperationException();
    }  //renvoie null si aucune evolution possible

}
