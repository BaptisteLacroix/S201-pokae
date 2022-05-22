package main;

import interfaces.IPokemon;
import interfaces.IType;
import pokedex.Pokedex;
import statsPokemon.Type;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Pokedex
        Pokedex p = new Pokedex();
        p.engendreRanch();
        System.out.println(p.getInfo("Bulbizarre").getNom());
        // System.out.println("INFOOOO : " + p.getInfo("Bulbizare").getNom()); // -> Erreur
        IType typeA = Type.Insecte;
        IType typeD = Type.Sol;
        System.out.println(p.getEfficacite(typeA, typeD));
        System.out.println(p.getCapacite("Écras'Face").getNom()); // -> Fonctionne
        // System.out.println("INFOOOO : " + p.getCapacite("Écras").getNom()); // -> Erreur
        System.out.println(p.getCapacite(1).getNom()); // -> Fonctionne
        // System.out.println("INFOOOO : " + p.getCapacite(0).getNom()); // -> Erreur


        // Pokemon
        System.out.println(Arrays.toString(p.getRanch()));
        IPokemon pokemon = p.getRanch()[0];
        System.out.println(pokemon.getStat());
        System.out.println(pokemon.getEspece());
        System.out.println(Arrays.toString(pokemon.getCapacitesApprises()));
        System.out.println(pokemon.peutMuter());
        System.out.println(pokemon.aChangeNiveau());
        System.out.println(pokemon.estEvanoui());

        // Espèce
        System.out.println(pokemon.getEspece().getGainsStat());
        System.out.println(pokemon.getEspece().getBaseExp());
        System.out.println(Arrays.toString(Arrays.stream(pokemon.getEspece().getCapSet()).toArray()));
        System.out.println(Arrays.toString(pokemon.getEspece().getTypes()));
        System.out.println(pokemon.getEspece().getEvolution(pokemon.getNiveau()));
    }
}
