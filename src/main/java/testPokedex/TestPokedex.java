package main.java.testPokedex;

import main.interfaces.IType;
import main.java.pokedex.Pokedex;
import main.java.statsPokemon.Type;

public class TestPokedex {
    public static void main(String[] args) {
        Pokedex p = new Pokedex();
        p.engendreRanch();
        // IType typeA = Type.Insecte;
        // IType typeD = Type.Sol;
        // System.out.println(p.getEfficacite(typeA, typeD));
        //System.out.println("INFOOOO : " + p.getInfo("Bulbizarre").getNom()); // -> Fonctionne
        //System.out.println("INFOOOO : " + p.getInfo("Bulbizare").getNom()); // -> Erreur
        // System.out.println("INFOOOO : " + p.getCapacite("Écras'Face").getNom()); // -> Fonctionne
        // System.out.println("INFOOOO : " + p.getCapacite("Écras").getNom()); // -> Erreur
        // System.out.println("INFOOOO : " + p.getCapacite(1).getNom()); // -> Fonctionne
        // System.out.println("INFOOOO : " + p.getCapacite(0).getNom()); // -> Erreur
    }
}
